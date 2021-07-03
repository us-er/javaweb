package com.blog.service;

import java.util.List;

import com.blog.bean.Blog;

//博客的业务层接口
public interface BlogService {

	//添加
	public boolean insertBlog(Blog Blog);
	public boolean deleteBlog(Integer id);
	public boolean udpateBlog(Blog Blog);
	
	// 查询博客、也提供可以根据条件查询
	public List<Blog> queryBlog(Integer bloggerid);
	public Blog queryBlogById(Integer id);
}
