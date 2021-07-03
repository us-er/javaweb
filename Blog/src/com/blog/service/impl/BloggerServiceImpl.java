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
	
	//��ȡmapper
	@Autowired
	BloggerMapper bloggerMapper;
	
	@Override
	public boolean insertBlogger(Blogger blogger) {
		//ִ����Ӳ���
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
		// ���� mapper �м�ķ���ʵ���޸Ĳ���
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
		//ʹ���ڲ�������Ӳ�ѯ����
		example.createCriteria().andNameEqualTo(name).andPasswordEqualTo(pass);
		//�����������в�ѯ
		List<Blogger> list = bloggerMapper.selectByExample(example);
		//      �жϼ����Ƿ�Ϊ�գ����򷵻�null�����շ��ص�һ������
		if(list.isEmpty()&&list.size()<=0) {
			return null;
		}else {
			return list.get(0);
		}
	}

}
