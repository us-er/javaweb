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


// ���Ϳ��Ʋ�
@Controller
public class BlogController {

	// ��ȡҵ������
	@Autowired
	BlogService blogService;
	@Autowired
	BlogTypeService blogTypeService;
	@Autowired
	BlogImgService blogImgService;
	

	//�ڲ�ѯ���͵�ͬʱ�����ݲ���id��ѯͼƬ����
	@RequestMapping("/queryBlogHome")
	public String loginBlogger(@RequestParam(required = true, defaultValue = "1") Integer pageNo,
			HttpServletRequest request) {
		
		// ����	��ҳ���	���������ݵ������� ������ҳ��ѯ ҳ�룬 ÿҳ��¼����
		PageHelper.startPage(pageNo, PageTool.PAGESIZES);
		
		// ����ҵ����������
		List<Blog> BlogList = blogService.queryBlog();
		
		// ��ҳ	������ҳģ�Ͷ���
		PageInfo<Blog> pageInfo = new PageInfo<>(BlogList);
		
		//�����ݡ�BlogList��pageInfo�����浽�����������С�ע�⣺����jspҳ���key��
		request.setAttribute("IndexBlogList", BlogList);
		request.setAttribute("pageInfo", pageInfo);
		
		// ��תҳ��
		return "index";// �൱�� /index.jsp
	}
	
	
	// ��ѯ��ǰ�����Ĳ���
	@RequestMapping("/queryBlog")
	public String queryBlog(@RequestParam(required = true, defaultValue = "1") Integer pageNo,
			HttpServletRequest request) {
		
		// ��ȡ��ǰ��¼�ɹ��Ķ�������
		Blogger nowBlogger = (Blogger) request.getSession().getAttribute("nowBlogger");
		
		PageHelper.startPage(pageNo, PageTool.PAGESIZES);
		
		// ��ѯ
		List<Blog> BlogList = blogService.queryBlog(nowBlogger.getId());
		
		// ������ҳģ��
		PageInfo<Blog> pageInfo = new PageInfo<>(BlogList);
		
		// �����ҳģ�͵�����������
		request.setAttribute("BlogList", BlogList);
		request.setAttribute("pageInfo", pageInfo);
		
		
		// ��תҳ��
		return "blogger_index";	
	}
	
	
	//�ڲ�ѯ���͵�ͬʱ�����ݲ����ȵ㡾HotBlogList�������ͷ��ࡾIndexTypeList��
	@RequestMapping("/queryIndex")
	public String queryIndex(HttpSession session) {
		
		// 1.0 ��ѯ�������ȵ㡿
		// ����ҵ����������
				List<Blog> BlogList = blogService.queryBlog();
							
				//�����ݡ�BlogList��pageInfo�����浽�����������С�ע�⣺����jspҳ���key��
//				request.setAttribute("IndexBlogList", BlogList);
				session.setAttribute("HotBlogList", BlogList);
		
				
		// 2.0 ��ѯ�����ͷ��ࡿ
				// 2.1������ҵ����������	��ѯ�ȵ㲩������
//				List<Blog> BlogList = blogService.queryBlog();
				List<BlogType> BlogTypeList =  blogTypeService.queryBlogType();
					
				// �����ݡ�BlogList��pageInfo�����浽�����������С�ע�⣺����jspҳ���key��
				// 2.2����ѯ�ȵ㲩�ͷ�������
//				request.setAttribute("IndexBlogList", BlogList);
				session.setAttribute("IndexTypeList", BlogTypeList);
		
		
		// ��תҳ��
		return "redirect:queryBlogHome";	//  �ض���
	}
	
	
	//
	@RequestMapping("/upLoadBlogImage")		// ����·��
	@ResponseBody	// ��Ӧ json ����
	public String upLoadImg(@RequestParam(value = "file") MultipartFile file) throws IOException{
		
		// �ϴ��ļ�
		String img = upLoad(file);
		return img;
	}
	
	
	// �ṩһ������	ʵ�ֲ��ͷ���������ӿ�
	@RequestMapping("/insertBlog")
	@ResponseBody
	public boolean insertBlog(Blog blog, String img) {
		
		// 1.0 ��Ӳ�������
		System.out.println(blog);
		boolean isok = blogService.insertBlog(blog);
		
		if(!isok){
			return isok;
		}
		
		// 2.0 ���ͼƬ����
		System.out.println(img);
		isok = blogImgService.insertBlogImg(img.split(","), blog.getId());
		
		// 3.0 ��ת
		return isok;
	}
	
	
	// ����һ���ļ��ϴ��ķ�����������ͼƬ��
	public String upLoad(MultipartFile detailImg) throws IOException {
		if (!detailImg.getOriginalFilename().equals("")) {
			// �ϴ��ļ�·��
			String path = "E:\\ʵѵ\\�㶫����\\��ҵѧԺ\\ʵѵ׿Խ1��\\img";
			try {
				// ��ȡ�����
				OutputStream os = new FileOutputStream(path + "\\" + detailImg.getOriginalFilename());
				// ��ȡ������ CommonsMultipartFile �п���ֱ�ӵõ��ļ�����
				BufferedInputStream is = new BufferedInputStream(detailImg.getInputStream());
				byte[] buff = new byte[1024];
				int temp;
				// һ��һ���ֽڵĶ�ȡ��д��
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
