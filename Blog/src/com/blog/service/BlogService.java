package com.blog.service;

import java.util.List;

import com.blog.bean.Blog;

//���͵�ҵ���ӿ�
public interface BlogService {

	//���
	public boolean insertBlog(Blog Blog);
	public boolean deleteBlog(Integer id);
	public boolean udpateBlog(Blog Blog);
	
	// ��ѯ���͡�Ҳ�ṩ���Ը���������ѯ
	public List<Blog> queryBlog(Integer bloggerid);
	public Blog queryBlogById(Integer id);
}
