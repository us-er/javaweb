package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.bean.Blog;
import com.blog.bean.BlogExample;
import com.blog.bean.BlogType;
import com.blog.bean.BlogTypeExample;
import com.blog.mapper.BlogMapper;
import com.blog.mapper.BlogTypeMapper;
import com.blog.service.BlogService;
import com.blog.service.BlogTypeService;


// ��������	ʵ����
@Service
public class BlogTypeServiceImpl implements BlogTypeService {
	
	//��ȡmapper
	@Autowired
	BlogTypeMapper blogTypeMapper;

	
	@Override
	public List<BlogType> queryBlogType() {
		// ��ѯ����	��������
		return blogTypeMapper.selectByExample(new BlogTypeExample());
	}
	
	
	@Override
	public boolean insertBlogType(BlogType BlogType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBlogType(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean udpateBlogType(BlogType BlogType) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public BlogType queryBlogTypeById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
