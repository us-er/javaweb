package com.blog.service;

import java.util.List;

import com.blog.bean.BlogComment;

//�������۵�ҵ���ӿ�
public interface BlogCommentService {

	// ���
	public boolean insertBlogComment(BlogComment BlogComment);

	public boolean deleteBlogComment(Integer id);

	public boolean udpateBlogComment(BlogComment BlogComment);

	public List<BlogComment> queryBlogComment(Integer blogid, Integer status, Integer bloggerid);

	public BlogComment queryBlogCommentById(Integer id);
}
