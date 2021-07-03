package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.bean.BlogComment;
import com.blog.bean.BlogCommentExample;
import com.blog.mapper.BlogCommentMapper;
import com.blog.service.BlogCommentService;

// ���ע��	���۵�ʵ����
@Service
public class BlogCommentServiceImpl implements BlogCommentService{
	
	// 1.0  ���ע�� ��ȡmapper
	@Autowired
	BlogCommentMapper blogCommentMapper;

	
	@Override
	public boolean insertBlogComment(BlogComment BlogComment) {
		// �����������
		int res = blogCommentMapper.insertSelective(BlogComment);
		return res > 0 ? true : false;
	}

	@Override
	public boolean deleteBlogComment(Integer id) {
		// ��������ɾ������
		int res = blogCommentMapper.deleteByPrimaryKey(id);
		return res > 0 ? true : false;
	}

	@Override
	public boolean udpateBlogComment(BlogComment BlogComment) {
		// ����id��ѡ����޸�
		int res  = blogCommentMapper.updateByPrimaryKeySelective(BlogComment);
		return res > 0 ? true : false;
	}

	@Override									// ����blogid��״̬������id
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
