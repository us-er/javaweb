package com.blog.service;

import java.util.List;

import com.blog.bean.BlogComment;

//博客评论的业务层接口
public interface BlogCommentService {

	// 添加
	public boolean insertBlogComment(BlogComment BlogComment);

	public boolean deleteBlogComment(Integer id);

	public boolean udpateBlogComment(BlogComment BlogComment);

	public List<BlogComment> queryBlogComment(Integer blogid, Integer status, Integer bloggerid);

	public BlogComment queryBlogCommentById(Integer id);
}
