package com.blog.service;

import java.util.List;

import com.blog.bean.BlogType;

//���͵�ҵ���ӿ�
public interface BlogTypeService {

	//���
	public boolean insertBlogType(BlogType BlogType);
	public boolean deleteBlogType(Integer id);
	public boolean udpateBlogType(BlogType BlogType);
	public List<BlogType> queryBlogType();
	public BlogType queryBlogTypeById(Integer id);
	
}
