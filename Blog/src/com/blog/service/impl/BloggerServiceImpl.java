package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.bean.Blogger;
import com.blog.bean.BloggerExample;
import com.blog.mapper.BloggerMapper;
import com.blog.service.BloggerService;

@Service
public class BloggerServiceImpl implements BloggerService{
	
	//获取mapper
	@Autowired
	BloggerMapper bloggerMapper;
	
	@Override
	public boolean insertBlogger(Blogger blogger) {
		//执行添加操作
		int res = bloggerMapper.insertSelective(blogger);
		//
		return res>0?true:false;
	}

	@Override
	public boolean deleteBlogger(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean udpateBlogger(Blogger blogger) {
		// 调用 mapper 中间的方法实现修改操作
		int res = bloggerMapper.updateByPrimaryKeySelective(blogger);
		return res > 0 ? true : false;
	}

	@Override
	public List<Blogger> queryBlogger() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blogger queryBloggerById(Integer id) {
		// TODO Auto-generated method stub
		return bloggerMapper.selectByPrimaryKey(id);
	}

	@Override
	public Blogger queryBloggerLogin(String name, String pass) {
		//
		BloggerExample example = new BloggerExample();
		//使用内部类来添加查询条件
		example.createCriteria().andNameEqualTo(name).andPasswordEqualTo(pass);
		//根据条件进行查询
		List<Blogger> list = bloggerMapper.selectByExample(example);
		//      判断集合是否为空？空则返回null：不空返回第一个数据
		if(list.isEmpty()&&list.size()<=0) {
			return null;
		}else {
			return list.get(0);
		}
	}

}
