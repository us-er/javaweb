package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.bean.Blog;
import com.blog.bean.BlogExample;
import com.blog.mapper.BlogMapper;
import com.blog.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService{
	
	//获取mapper
	@Autowired
	BlogMapper blogMapper;
	
	@Override
	public boolean insertBlog(Blog Blog) {
		//执行添加操作
		int res = blogMapper.insertSelective(Blog);
		//
		return res > 0 ? true : false;
	}

	@Override
	public boolean deleteBlog(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean udpateBlog(Blog Blog) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Blog> queryBlog(Integer bloggerid) {
//		//  text 使用selectByExampleWithBLOBs
//		List<Blog> list = blogMapper.selectByExampleWithBLOBs(new BlogExample());
//		//
//		return list;
		
		BlogExample example = new BlogExample();
		
		// 判断 bloggerid 数据是否为空，不为空，则添加查询操作
		if(bloggerid != null && bloggerid != 0){
			example.createCriteria().andBloggerIdEqualTo(bloggerid);
		}
		
		
		List<Blog> list = blogMapper.selectByExampleWithBLOBs(example);
		return list;
	}
	

	@Override
	public Blog queryBlogById(Integer id) {
		// 
		return null;
	}

}
