<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="side-menu">
    <div class='side-menu-body'>
        <ul>
            <li class="open">
                <a href="#"><i class="icon ti-home"></i> <span>个人博客</span> </a>
                <ul>
                    <li><a class="open" href="/blog/queryBlog?id=${nowBlogger.id}">博客列表</a></li>
                    <li><a href="/blog/toBlogInsert">发表博客</a></li>
                </ul>
            </li>
            <li><a href="#"><i class="icon ti-layers-alt"></i> <span>博客类型</span> </a>
                <ul>
                    <li><a href="/blog/queryType">类型列表 </a></li>
                    <li><a href="toTypeInsert">添加类型</a></li>
                </ul>
            </li>
            <li><a href="#"><i class="icon ti-paint-roller"></i> <span>博客评论</span> </a>
                <ul>
                    <li><a href="queryComment?bloggerid=${nowBlogger.id}">评论列表</a></li>
                    <li><a href="queryComment?bloggerid=${nowBlogger.id}&status=0">待审核评论</a></li>
                    <li><a href="queryComment?bloggerid=${nowBlogger.id}&status=2">被禁止评论</a></li>
                </ul>
            </li>
            <li><a href="#"><i class="icon ti-face-smile"></i> <span>Authentication</span> </a>
                <ul>
                    <li><a href="login.html">Login </a></li>
                    <li><a href="register.html">Register </a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>