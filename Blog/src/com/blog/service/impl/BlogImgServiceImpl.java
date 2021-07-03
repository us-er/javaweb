package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.bean.BlogImg;
import com.blog.mapper.BlogImgMapper;
import com.blog.service.BlogImgService;


@Service
public class BlogImgServiceImpl implements BlogImgService {

	@Autowired
	BlogImgMapper blogImgmapper;
	
	
	@Override
	public boolean insertBlogImg(String[] imgs, Integer blogid) {
		
		// 遍历
		for(String string : imgs){
			// 1.0 创建对象
			BlogImg img = new BlogImg();
			img.setName(string);
			img.setBlogId(blogid);
			
			// 2.0 添加数据
			blogImgmapper.insertSelective(img);
		}
		
		return true;
	}

	@Override
	public boolean deleteBlogImg(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean udpateBlogImg(BlogImg BlogImg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<BlogImg> queryBlogImg(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlogImg queryBlogImgById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}



}
