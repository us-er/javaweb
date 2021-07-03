package com.blog.controller;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.blog.bean.Blog;
import com.blog.bean.BlogImg;
import com.blog.bean.BlogType;
import com.blog.bean.Blogger;
import com.blog.service.BlogImgService;
import com.blog.service.BlogService;
import com.blog.service.BlogTypeService;
import com.blog.utils.PageTool;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


// 博客控制层
@Controller
public class BlogController {

	// 获取业务层对象
	@Autowired
	BlogService blogService;
	@Autowired
	BlogTypeService blogTypeService;
	@Autowired
	BlogImgService blogImgService;
	

	//在查询博客的同时，根据博客id查询图片数据
	@RequestMapping("/queryBlogHome")
	public String loginBlogger(@RequestParam(required = true, defaultValue = "1") Integer pageNo,
			HttpServletRequest request) {
		
		// 调用	分页插件	【保存数据到作用域】 开启分页查询 页码， 每页记录条数
		PageHelper.startPage(pageNo, PageTool.PAGESIZES);
		
		// 调用业务层操作方法
		List<Blog> BlogList = blogService.queryBlog();
		
		// 分页	创建分页模型对象
		PageInfo<Blog> pageInfo = new PageInfo<>(BlogList);
		
		//把数据【BlogList，pageInfo】保存到请求作用域中【注意：遵守jsp页面的key】
		request.setAttribute("IndexBlogList", BlogList);
		request.setAttribute("pageInfo", pageInfo);
		
		// 跳转页面
		return "index";// 相当于 /index.jsp
	}
	
	
	// 查询当前博主的博客
	@RequestMapping("/queryBlog")
	public String queryBlog(@RequestParam(required = true, defaultValue = "1") Integer pageNo,
			HttpServletRequest request) {
		
		// 获取当前登录成功的对象数据
		Blogger nowBlogger = (Blogger) request.getSession().getAttribute("nowBlogger");
		
		PageHelper.startPage(pageNo, PageTool.PAGESIZES);
		
		// 查询
		List<Blog> BlogList = blogService.queryBlog(nowBlogger.getId());
		
		// 创建分页模型
		PageInfo<Blog> pageInfo = new PageInfo<>(BlogList);
		
		// 保存分页模型到请求作用域
		request.setAttribute("BlogList", BlogList);
		request.setAttribute("pageInfo", pageInfo);
		
		
		// 跳转页面
		return "blogger_index";	
	}
	
	
	//在查询博客的同时，根据博客热点【HotBlogList】及博客分类【IndexTypeList】
	@RequestMapping("/queryIndex")
	public String queryIndex(HttpSession session) {
		
		// 1.0 查询【博客热点】
		// 调用业务层操作方法
				List<Blog> BlogList = blogService.queryBlog();
							
				//把数据【BlogList，pageInfo】保存到请求作用域中【注意：遵守jsp页面的key】
//				request.setAttribute("IndexBlogList", BlogList);
				session.setAttribute("HotBlogList", BlogList);
		
				
		// 2.0 查询【博客分类】
				// 2.1、调用业务层操作方法	查询热点博客数据
//				List<Blog> BlogList = blogService.queryBlog();
				List<BlogType> BlogTypeList =  blogTypeService.queryBlogType();
					
				// 把数据【BlogList，pageInfo】保存到请求作用域中【注意：遵守jsp页面的key】
				// 2.2、查询热点博客分类数据
//				request.setAttribute("IndexBlogList", BlogList);
				session.setAttribute("IndexTypeList", BlogTypeList);
		
		
		// 跳转页面
		return "redirect:queryBlogHome";	//  重定向
	}
	
	
	//
	@RequestMapping("/upLoadBlogImage")		// 请求路径
	@ResponseBody	// 响应 json 数据
	public String upLoadImg(@RequestParam(value = "file") MultipartFile file) throws IOException{
		
		// 上传文件
		String img = upLoad(file);
		return img;
	}
	
	
	// 提供一个方法	实现博客发布的请求接口
	@RequestMapping("/insertBlog")
	@ResponseBody
	public boolean insertBlog(Blog blog, String img) {
		
		// 1.0 添加博客数据
		System.out.println(blog);
		boolean isok = blogService.insertBlog(blog);
		
		if(!isok){
			return isok;
		}
		
		// 2.0 添加图片数据
		System.out.println(img);
		isok = blogImgService.insertBlogImg(img.split(","), blog.getId());
		
		// 3.0 跳转
		return isok;
	}
	
	
	// 定义一个文件上传的方法，并返回图片名
	public String upLoad(MultipartFile detailImg) throws IOException {
		if (!detailImg.getOriginalFilename().equals("")) {
			// 上传文件路径
			String path = "E:\\实训\\广东工商\\产业学院\\实训卓越1班\\img";
			try {
				// 获取输出流
				OutputStream os = new FileOutputStream(path + "\\" + detailImg.getOriginalFilename());
				// 获取输入流 CommonsMultipartFile 中可以直接得到文件的流
				BufferedInputStream is = new BufferedInputStream(detailImg.getInputStream());
				byte[] buff = new byte[1024];
				int temp;
				// 一个一个字节的读取并写入
				while ((temp = is.read(buff)) != (-1)) {
					os.write(buff, 0, temp);
				}
				os.flush();
				os.close();
				is.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return detailImg.getOriginalFilename();
	}

}
