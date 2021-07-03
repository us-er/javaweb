package com.blog.service;

import java.util.List;

import com.blog.bean.Blogger;

//博主的业务层接口
public interface BloggerService {

	//添加
	public boolean insertBlogger(Blogger blogger);
	public boolean deleteBlogger(Integer id);
	public boolean udpateBlogger(Blogger blogger);
	public List<Blogger> queryBlogger();
	public Blogger queryBloggerById(Integer id);
	//登录查询
	public Blogger queryBloggerLogin(String name,String pass);
}
