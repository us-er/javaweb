package com.blog.service;

import java.util.List;

import com.blog.bean.BlogType;

//博客的业务层接口
public interface BlogTypeService {

	//添加
	public boolean insertBlogType(BlogType BlogType);
	public boolean deleteBlogType(Integer id);
	public boolean udpateBlogType(BlogType BlogType);
	public List<BlogType> queryBlogType();
	public BlogType queryBlogTypeById(Integer id);
	
}
