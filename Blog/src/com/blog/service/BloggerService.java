package com.blog.service;

import java.util.List;

import com.blog.bean.Blogger;

//������ҵ���ӿ�
public interface BloggerService {

	//���
	public boolean insertBlogger(Blogger blogger);
	public boolean deleteBlogger(Integer id);
	public boolean udpateBlogger(Blogger blogger);
	public List<Blogger> queryBlogger();
	public Blogger queryBloggerById(Integer id);
	//��¼��ѯ
	public Blogger queryBloggerLogin(String name,String pass);
}
