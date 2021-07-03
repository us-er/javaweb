package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.bean.BlogComment;
import com.blog.bean.BlogCommentExample;
import com.blog.mapper.BlogCommentMapper;
import com.blog.service.BlogCommentService;

// 添加注解	评论的实现类
@Service
public class BlogCommentServiceImpl implements BlogCommentService{
	
	// 1.0  添加注释 获取mapper
	@Autowired
	BlogCommentMapper blogCommentMapper;

	
	@Override
	public boolean insertBlogComment(BlogComment BlogComment) {
		// 添加评论数据
		int res = blogCommentMapper.insertSelective(BlogComment);
		return res > 0 ? true : false;
	}

	@Override
	public boolean deleteBlogComment(Integer id) {
		// 根据主键删除评论
		int res = blogCommentMapper.deleteByPrimaryKey(id);
		return res > 0 ? true : false;
	}

	@Override
	public boolean udpateBlogComment(BlogComment BlogComment) {
		// 根据id有选择的修改
		int res  = blogCommentMapper.updateByPrimaryKeySelective(BlogComment);
		return res > 0 ? true : false;
	}

	@Override									// 博客blogid，状态，博主id
	public List<BlogComment> queryBlogComment(Integer blogid, Integer status, Integer bloggerid) {
		
		BlogCommentExample blogCommentExample = new BlogCommentExample();
		
		
		if(blogid != null && blogid != 0){
			blogCommentExample.createCriteria().andBlogIdEqualTo(blogid);
		}
		if(bloggerid != null && bloggerid != 0){
			blogCommentExample.createCriteria().andBloggerIdEqualTo(bloggerid);
		}
		if(status != null){
			blogCommentExample.createCriteria().andStatusEqualTo(status);
		}
		
		//
		List<BlogComment> list = blogCommentMapper.selectByExampleWithBLOBs(blogCommentExample);
		return list;
	}

	@Override
	public BlogComment queryBlogCommentById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
