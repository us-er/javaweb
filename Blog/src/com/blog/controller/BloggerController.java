package com.blog.controller;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.blog.bean.Blogger;
import com.blog.service.BloggerService;


// ���˲���	���Ʋ�
@Controller
public class BloggerController {

	// ��ȡҵ������
	@Autowired
	BloggerService bloggerService;

	@RequestMapping("/login")
	public String useLogin(String name, String pass, HttpSession session) {
		// ����ҵ����������
		Blogger blogger = bloggerService.queryBloggerLogin(name, pass);
		// �ж�
		if (blogger != null) {
			// ��¼���ұ������session
			session.setAttribute("nowBlogger", blogger);
			return "redirect:queryBlog";// �൱�� /index.jsp
		}
		return "login";// �൱�� /login.jsp
	}
	

	@RequestMapping("/insertBlogger")
	public String insertBlogger(Blogger blogger, @RequestParam(value = "file") MultipartFile file) throws IOException {// jspҳ���е�keyҪ��ʵ��ı�����һ��
		//�ϴ�ͼƬ
		String img = upLoad(file);
		//��ͼƬ�����浽������
		if(img!=null&&!img.equals("")) {
			blogger.setImage(img);
		}
		// ����ҵ����������
		boolean isok = bloggerService.insertBlogger(blogger);
		// �ж�
		if (isok) {
			return "index";// �൱�� /index.jsp
		}
		return "rigister";// �൱�� /rigister.jsp
	}

	
	// ����һ���ļ��ϴ��ķ�����������ͼƬ��
	public String upLoad(MultipartFile detailImg) throws IOException {
		if (!detailImg.getOriginalFilename().equals("")) {
			// �ϴ��ļ�·��
//			C:\Users\lenovo\Desktop\����� ʵѵ ssm\img
			String path = "C:\\Users\\lenovo\\Desktop\\����� ʵѵ ssm\\img";
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
	
	
	// 
	@RequestMapping("/updateBlogger")
	public String updateBlogger(Blogger blogger, @RequestParam(value = "file") 
		MultipartFile file, HttpSession session) throws IOException{
		
		// �ϴ�ͼƬ
		String img = upLoad(file);
		
		// ��ͼƬ�� ���浽����
		if(img != null && !img.equals("")){
			blogger.setImage(img);
		}else{
			blogger.setImage(null);
		}
		
		// ����ҵ���	�޸�
		boolean isok = bloggerService.udpateBlogger(blogger);
		
		// �ж��Ƿ�ɹ�
		if(isok){
			// ���²�ѯ��ǰ��������Ϣ
			blogger = bloggerService.queryBloggerById(blogger.getId());
			System.out.println(blogger);
			session.setAttribute("nowBlogger", blogger);
		}
		
		return "blogger_update";
	}



}
