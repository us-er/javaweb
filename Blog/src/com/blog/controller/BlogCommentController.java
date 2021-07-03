package com.blog.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.bean.BlogComment;
import com.blog.bean.Blogger;
import com.blog.service.BlogCommentService;
import com.blog.utils.PageTool;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

// ���ע��	���������ۡ����Ʋ�
@Controller
public class BlogCommentController {

	// ��ȡҵ��� ����ͨ��ע�⡿
	@Autowired
	BlogCommentService blogCommentService;
	
	
	
	@RequestMapping("/queryComment")	// ���ݵ�ǰ��¼������ѯ����
	public String queryComment(@RequestParam(required = true, defaultValue = "1") Integer pageNo,
			HttpServletRequest request) {
		
		// ������ҳ
		PageHelper.startPage(pageNo, PageTool.PAGESIZES);
		// ������������	��ȡ��ǰ��������Ϣ
		Blogger nowBlogger = (Blogger) request.getSession().getAttribute("nowBlogger");
		
		// ����ҵ���		���ݵ�ǰ id ��ѯ����
		List<BlogComment> CommentList =  blogCommentService.queryBlogComment(null, null, nowBlogger.getId());
		
		// ������ҳ����
		PageInfo<BlogComment> pageInfo = new PageInfo<>(CommentList);
		
		// �����ݱ��浽����������
		request.setAttribute("CommentList", CommentList);
		request.setAttribute("pageInfo", pageInfo);
		
		
		// ��תҳ��
		return "blogcomment_index";
	}
	
	
	// ��������·��
	@RequestMapping("/insertComment")
	@ResponseBody		// ��ʾ���� json ��ʽ�����ݣ�������ҳ����ת
	public HashMap<String, Object> insertComment(BlogComment blogComment, HttpSession session) {
		
		// 
		HashMap<String, Object> map = new HashMap<>();
		
		// Ҫ��ʾ�����ˡ�ͨ�������˵�id��������Ҫ�ڡ�bloggerController����¼��ʱ�򣬱������ݵ�������
		Blogger nowBlogger = (Blogger) session.getAttribute("nowBlogger");
		if(nowBlogger == null) {
			map.put("login", false);
			return map;
		}
		map.put("login", true);
		
		
		// ���� ���۵Ĳ���id
		blogComment.setOwnBlogger(nowBlogger.getId());
		// ����Ĭ�� ����״̬Ϊ0��δ�ṩ��ˡ�
		blogComment.setStatus(0);
		
		// ���� ҵ��� ������۲���
		boolean isok = blogCommentService.insertBlogComment(blogComment);
		map.put("flag", isok);
		
		return map;
	}
	
	
	// �ӿڣ����� json ����
	@RequestMapping("/passComment")
	@ResponseBody	// ��ʾ���� json ��ʽ�����ݣ�������ҳ����ת
	public boolean passComment(BlogComment comment, HttpSession session) {
		// ����״̬Ϊ1����ʾ�ṩ���
		comment.setStatus(1);
		// ����ҵ���ķ���
		boolean isok = blogCommentService.udpateBlogComment(comment);
		//
		return isok;
	}
	
	
	// �ӿڣ����� json ����
	@RequestMapping("/bankComment")
	@ResponseBody	// ��ʾ���� json ��ʽ�����ݣ�������ҳ����ת
	public boolean bankComment(BlogComment comment, HttpSession session) {
		// ����״̬Ϊ1����ʾ�ṩ���
		comment.setStatus(2);
		// ����ҵ���ķ���
		boolean isok = blogCommentService.udpateBlogComment(comment);
		//
		return isok;
	}
	
	
	// �ӿڣ����� json ����
		@RequestMapping("/deleteComment")
		@ResponseBody	// ��ʾ���� json ��ʽ�����ݣ�������ҳ����ת
		public boolean deleteComment(Integer id, HttpSession session) {
			
			// ����ҵ���ķ���
			boolean isok = blogCommentService.deleteBlogComment(id);
			//
			return isok;
		}
		
}
