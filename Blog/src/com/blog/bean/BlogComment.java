package com.blog.bean;

import java.util.Date;

public class BlogComment {
    private Integer id;

    private Date createdate;

    private Integer status;

    private Integer blogId;

    private Integer bloggerId;//当前评论的博主id

    private Integer ownBlogger;//博客所属的博主id

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getBloggerId() {
        return bloggerId;
    }

    public void setBloggerId(Integer bloggerId) {
        this.bloggerId = bloggerId;
    }

    public Integer getOwnBlogger() {
        return ownBlogger;
    }

    public void setOwnBlogger(Integer ownBlogger) {
        this.ownBlogger = ownBlogger;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}