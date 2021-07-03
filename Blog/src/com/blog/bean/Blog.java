package com.blog.bean;

import java.util.Date;
import java.util.List;

import javafx.scene.layout.BorderImage;

public class Blog {
	private Integer id;

	private String title;

	private Integer typeid;

	private Date createdate;

	private Integer bloggerId;

	private Integer likenum;

	private Integer cellnum;

	private String content;

	// 关联图片数据
	private List<BlogImg> imgs;
	//博客的类型   根据博客类型的id查询
	private BlogType type;
	//发布该博客的博主   根据博主的id查询
	private Blogger blogger;
	//评论的引用
	private List<BlogComment> comments;
	
	// get \set
	public List<BlogImg> getImgs() {
		return imgs;
	}

	public List<BlogComment> getComments() {
		return comments;
	}

	public void setComments(List<BlogComment> comments) {
		this.comments = comments;
	}

	public BlogType getType() {
		return type;
	}

	public void setType(BlogType type) {
		this.type = type;
	}

	public Blogger getBlogger() {
		return blogger;
	}

	public void setBlogger(Blogger blogger) {
		this.blogger = blogger;
	}

	public void setImgs(List<BlogImg> imgs) {
		this.imgs = imgs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Integer getBloggerId() {
		return bloggerId;
	}

	public void setBloggerId(Integer bloggerId) {
		this.bloggerId = bloggerId;
	}

	public Integer getLikenum() {
		return likenum;
	}

	public void setLikenum(Integer likenum) {
		this.likenum = likenum;
	}

	public Integer getCellnum() {
		return cellnum;
	}

	public void setCellnum(Integer cellnum) {
		this.cellnum = cellnum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	
	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", typeid=" + typeid + ", createdate=" + createdate
				+ ", bloggerId=" + bloggerId + ", likenum=" + likenum + ", cellnum=" + cellnum + ", content=" + content
				+ ", imgs=" + imgs + ", type=" + type + ", blogger=" + blogger + ", comments=" + comments + "]";
	}
	
	
	
}