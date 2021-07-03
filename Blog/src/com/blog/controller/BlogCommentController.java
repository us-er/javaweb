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

// 添加注解	【博客评论】控制层
@Controller
public class BlogCommentController {

	// 获取业务层 对象【通过注解】
	@Autowired
	BlogCommentService blogCommentService;
	
	
	
	@RequestMapping("/queryComment")	// 根据当前登录博主查询评论
	public String queryComment(@RequestParam(required = true, defaultValue = "1") Integer pageNo,
			HttpServletRequest request) {
		
		// 开启分页
		PageHelper.startPage(pageNo, PageTool.PAGESIZES);
		// 从属性作用域	获取当前博主的信息
		Blogger nowBlogger = (Blogger) request.getSession().getAttribute("nowBlogger");
		
		// 调用业务层		根据当前 id 查询评论
		List<BlogComment> CommentList =  blogCommentService.queryBlogComment(null, null, nowBlogger.getId());
		
		// 创建分页对象
		PageInfo<BlogComment> pageInfo = new PageInfo<>(CommentList);
		
		// 把数据保存到请求作用域
		request.setAttribute("CommentList", CommentList);
		request.setAttribute("pageInfo", pageInfo);
		
		
		// 跳转页面
		return "blogcomment_index";
	}
	
	
	// 配置请求路径
	@RequestMapping("/insertComment")
	@ResponseBody		// 表示返回 json 格式的数据，不进行页面跳转
	public HashMap<String, Object> insertComment(BlogComment blogComment, HttpSession session) {
		
		// 
		HashMap<String, Object> map = new HashMap<>();
		
		// 要显示评论人【通过评论人的id】，所以要在【bloggerController】登录的时候，保存数据到作用域
		Blogger nowBlogger = (Blogger) session.getAttribute("nowBlogger");
		if(nowBlogger == null) {
			map.put("login", false);
			return map;
		}
		map.put("login", true);
		
		
		// 设置 评论的博主id
		blogComment.setOwnBlogger(nowBlogger.getId());
		// 设置默认 评论状态为0【未提供审核】
		blogComment.setStatus(0);
		
		// 调用 业务层 添加评论操作
		boolean isok = blogCommentService.insertBlogComment(blogComment);
		map.put("flag", isok);
		
		return map;
	}
	
	
	// 接口，返回 json 数据
	@RequestMapping("/passComment")
	@ResponseBody	// 表示返回 json 格式的数据，不进行页面跳转
	public boolean passComment(BlogComment comment, HttpSession session) {
		// 设置状态为1，表示提供审核
		comment.setStatus(1);
		// 调用业务层的方法
		boolean isok = blogCommentService.udpateBlogComment(comment);
		//
		return isok;
	}
	
	
	// 接口，返回 json 数据
	@RequestMapping("/bankComment")
	@ResponseBody	// 表示返回 json 格式的数据，不进行页面跳转
	public boolean bankComment(BlogComment comment, HttpSession session) {
		// 设置状态为1，表示提供审核
		comment.setStatus(2);
		// 调用业务层的方法
		boolean isok = blogCommentService.udpateBlogComment(comment);
		//
		return isok;
	}
	
	
	// 接口，返回 json 数据
		@RequestMapping("/deleteComment")
		@ResponseBody	// 表示返回 json 格式的数据，不进行页面跳转
		public boolean deleteComment(Integer id, HttpSession session) {
			
			// 调用业务层的方法
			boolean isok = blogCommentService.deleteBlogComment(id);
			//
			return isok;
		}
		
}
