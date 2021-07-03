package com.blog.service;

import java.util.List;

import com.blog.bean.BlogImg;

public interface BlogImgService {

	//Ìí¼Ó
	public boolean insertBlogImg(String[] imgs, Integer blogid);
	public boolean deleteBlogImg(Integer id);
	public boolean udpateBlogImg(BlogImg BlogImg);
	public List<BlogImg> queryBlogImg(Integer id);
	public BlogImg queryBlogImgById(Integer id);
}
