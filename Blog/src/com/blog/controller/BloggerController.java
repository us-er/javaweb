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


// 个人博主	控制层
@Controller
public class BloggerController {

	// 获取业务层对象
	@Autowired
	BloggerService bloggerService;

	@RequestMapping("/login")
	public String useLogin(String name, String pass, HttpSession session) {
		// 调用业务层操作方法
		Blogger blogger = bloggerService.queryBloggerLogin(name, pass);
		// 判断
		if (blogger != null) {
			// 登录并且保存对象到session
			session.setAttribute("nowBlogger", blogger);
			return "redirect:queryBlog";// 相当于 /index.jsp
		}
		return "login";// 相当于 /login.jsp
	}
	

	@RequestMapping("/insertBlogger")
	public String insertBlogger(Blogger blogger, @RequestParam(value = "file") MultipartFile file) throws IOException {// jsp页面中的key要和实体的变量名一致
		//上传图片
		String img = upLoad(file);
		//将图片名保存到对象中
		if(img!=null&&!img.equals("")) {
			blogger.setImage(img);
		}
		// 调用业务层操作方法
		boolean isok = bloggerService.insertBlogger(blogger);
		// 判断
		if (isok) {
			return "index";// 相当于 /index.jsp
		}
		return "rigister";// 相当于 /rigister.jsp
	}

	
	// 定义一个文件上传的方法，并返回图片名
	public String upLoad(MultipartFile detailImg) throws IOException {
		if (!detailImg.getOriginalFilename().equals("")) {
			// 上传文件路径
//			C:\Users\lenovo\Desktop\大二下 实训 ssm\img
			String path = "C:\\Users\\lenovo\\Desktop\\大二下 实训 ssm\\img";
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
	
	
	// 
	@RequestMapping("/updateBlogger")
	public String updateBlogger(Blogger blogger, @RequestParam(value = "file") 
		MultipartFile file, HttpSession session) throws IOException{
		
		// 上传图片
		String img = upLoad(file);
		
		// 将图片名 保存到对象
		if(img != null && !img.equals("")){
			blogger.setImage(img);
		}else{
			blogger.setImage(null);
		}
		
		// 调用业务层	修改
		boolean isok = bloggerService.udpateBlogger(blogger);
		
		// 判断是否成功
		if(isok){
			// 重新查询当前博主的信息
			blogger = bloggerService.queryBloggerById(blogger.getId());
			System.out.println(blogger);
			session.setAttribute("nowBlogger", blogger);
		}
		
		return "blogger_update";
	}



}
