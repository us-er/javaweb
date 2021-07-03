<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Blogger - Admin Dashboard</title>

    <!-- begin::global styles -->
    <link rel="stylesheet" href="assets/vendors/bundle.css" type="text/css">
    <!-- end::global styles -->

    <!-- begin::datepicker -->
    <link rel="stylesheet" href="assets/vendors/datepicker/daterangepicker.css">
    <!-- begin::datepicker -->

    <!-- begin::vmap -->
    <link rel="stylesheet" href="assets/vendors/vmap/jqvmap.min.css">
    <!-- begin::vmap -->

    <!-- begin::custom styles -->
    <link rel="stylesheet" href="assets/css/app.css" type="text/css">
    <link rel="stylesheet" href="assets/css/custom.css" type="text/css">
    <!-- end::custom styles -->

</head>

<body>

<!-- begin::side menu -->
<jsp:include page="menu.jsp"></jsp:include>
<!-- end::side menu -->

<!-- begin::navbar -->
<nav class="navbar">
    <div class="container-fluid">

        <div class="header-logo">
            <a href="/">
                <img src="assets/media/image/light-logo.png" alt="...">
                <span class="logo-text d-none d-lg-block">Blogger</span>
            </a>
        </div>

        <div class="header-body">
            <ul class="navbar-nav">
                <li class="nav-item dropdown d-none d-lg-block">
                    <a href="#" class="nav-link" data-toggle="dropdown">
                        <i class="fa fa-th-large"></i>
                    </a>
                </li>
            </ul>
            <form class="search"id="form_query"
                  action="/blog/queryComment">
                <div class="input-group">
                    <input id="page" name="pageNo" type="hidden" value="1"/>
                    <input name="bloggerid" type="hidden" value="${nowBlogger.id}"/>
                    <input name="status" type="hidden" value="${status}"/>
                    <%--<input type="text" name="search" class="form-control" placeholder="Search ..."
                           aria-label="Recipient's username" aria-describedby="button-addon2">
                    <div class="input-group-append">
                        <button class="btn" type="submit" id="button-addon2">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>--%>
                </div>
            </form>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="#" class="d-lg-none d-sm-block nav-link search-panel-open">
                        <i class="fa fa-search"></i>
                    </a>
                </li>
                <li class="nav-item dropdown">
                    <a href="#" class="nav-link nav-link-notify">
                        <i class="fa fa-envelope"></i>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link nav-link-notify">
                        <i class="fa fa-bell"></i>
                    </a>
                </li>
                <li class="nav-item dropdown">
                    <a href="#" data-toggle="dropdown">
                        <figure class="avatar avatar-sm avatar-state-success">
                            <img class="rounded-circle" src="assets/media/image/avatar.jpg" alt="...">
                        </figure>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right">
                        <a href="profile.html" class="dropdown-item">Profile</a>
                        <a href="#" data-sidebar-target="#settings" class="sidebar-open dropdown-item">Settings</a>
                        <div class="dropdown-divider"></div>
                        <a href="/blog/logout" class="text-danger dropdown-item">Logout</a>
                    </div>
                </li>
            </ul>
        </div>

    </div>
</nav>
<!-- end::navbar -->

<!-- begin::main content -->
<main class="main-content">

    <div class="container-fluid">
        <!-- begin::page header -->
        <div class="row">
            <div class="col-md-11">
                <div class="page-header d-md-flex justify-content-between align-items-center">
                    <h4>博客评论</h4>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb m-t-0">
                            <li class="breadcrumb-item"><a href="#">Dashboard</a></li>
                            <li class="breadcrumb-item"><a href="#">Layouts</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Container</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
        <!-- end::page header -->

        <div class="row">
            <div class="col-md-11">
                <div class="card">

                    <div class="table-email-list">
                        <div class="table-responsive">
                            <table class="table table-hover" style="margin-top: 30px;">
                                <tbody>
                                <tr>
                                    <td>博客标题</td>
                                    <td>评论</td>
                                    <td>评论人</td>
                                    <td>时间</td>
                                    <td>状态</td>
                                    <td>操作</td>
                                </tr>

                                <c:forEach items="${CommentList}" var="CommentItem">
                                    <tr>
                                        <td><a href="#">${CommentItem.blogTitle}</a></td>
                                        <td><span class="badge-primary badge">
                                                ${CommentItem.content} </span></td>
                                        <td><span class="badge-info badge">
                                                ${CommentItem.blogger.name} </span></td>
                                        <td><fmt:formatDate value="${CommentItem.createdate}"
                                                            pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>
                                            <c:if test="${CommentItem.status==0}">
                                                <span class="badge-danger badge">未审核</span>
                                            </c:if>
                                            <c:if test="${CommentItem.status==1}">
                                                <span class="badge-success badge">已审核</span>
                                            </c:if>
                                            <c:if test="${CommentItem.status==2}">
                                                <span class="badge-danger badge">已屏蔽</span>
                                            </c:if>
                                        </td>
                                        <td>
                                            <div class="dropdown">
                                                <c:if test="${CommentItem.status!=1}">
                                                    <span class="badge-success badge">
                                                <a onclick="passComment(${CommentItem.id})">通过</a></span>
                                                </c:if>
                                                <c:if test="${CommentItem.status==1}">
                                                    <span class="badge-success badge">
                                                <a onclick="bankComment(${CommentItem.id})">屏蔽</a></span>
                                                </c:if>
                                                <span class="badge-danger badge">
                                                <a onclick="deleteCommen(${CommentItem.id})">删除</a></span>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>


                    <div class="card-body">
                        <nav aria-label="Page navigation example"
                             style="margin-left: 530px;">
                            <ul class="pagination">
                                <li class="page-item"><a class="page-link"
                                                         href="#">【${pageInfo.pageNum}/${pageInfo.pages}，共${pageInfo.total}条记录】
                                </a></li>
                                <li class="page-item"><a class="page-link"
                                                         href="javascript:jumpPage(1)">首页</a></li>
                                <c:if test="${pageInfo.prePage!=0}">
                                    <li class="page-item"><a class="page-link"
                                                             href="javascript:jumpPage(${pageInfo.prePage})">上一页</a>
                                    </li>
                                </c:if>
                                <li class="page-item"><a class="page-link"
                                                         href="javascript:jumpPage(${pageInfo.pageNum})">${pageInfo.pageNum}</a>
                                </li>
                                <c:if test="${pageInfo.nextPage!=0}">
                                    <li class="page-item"><a class="page-link"
                                                             href="javascript:jumpPage(${pageInfo.nextPage})">下一页</a>
                                    </li>
                                </c:if>
                                <li class="page-item"><a class="page-link"
                                                         href="javascript:jumpPage(${pageInfo.pages})">尾页</a></li>
                            </ul>
                        </nav>
                    </div>

                </div>
            </div>

        </div>


    </div>

</main>
<!-- end::main content -->

<!-- begin::global scripts -->
<script src="assets/vendors/bundle.js"></script>
<!-- end::global scripts -->

<!-- begin::charts -->
<script src="assets/vendors/charts/chartjs/chart.min.js"></script>
<script src="assets/vendors/charts/peity/jquery.peity.min.js"></script>
<script src="assets/js/examples/charts/chartjs.js"></script>
<script src="assets/js/examples/charts/peity.js"></script>
<!-- end::charts -->

<!-- begin::daterangepicker -->
<script src="assets/vendors/datepicker/daterangepicker.js"></script>
<!-- end::daterangepicker -->

<!-- begin::dashboard -->
<script src="assets/js/examples/dashboard.js"></script>
<!-- end::dashboard -->

<!-- begin::vamp -->
<script src="assets/vendors/vmap/jquery.vmap.min.js"></script>
<script src="assets/vendors/vmap/maps/jquery.vmap.usa.js"></script>
<script src="assets/js/examples/vmap.js"></script>
<!-- end::vamp -->

<!-- begin::custom scripts -->
<script src="assets/js/custom.js"></script>
<script src="assets/js/app.js"></script>
<!-- end::custom scripts -->
<script type="application/javascript">
    function passComment(id) {
        $.post('/blog/passComment', {
            'id': id
        }, function (result) {
            if (result) {
                alert('评论已通过');
                location.reload();
            } else {
                alert('评论审核失败，请重新操作');
            }
        });
    }

    function deleteCommen(id) {
        $.post('/blog/deleteCommen', {
            'id': id
        }, function (result) {
            if (result) {
                alert('删除成功');
                location.reload();
            } else {
                alert('删除失败，请重新操作');
            }
        });
    }
    function bankComment(id) {
        $.post('/blog/bankComment', {
            'id': id
        }, function (result) {
            if (result) {
                alert('屏蔽成功');
                location.reload();
            } else {
                alert('屏蔽失败，请重新操作');
            }
        });
    }

    function jumpPage(page) {
        //要修改访问的页码
        //document.getElementById("job_TYPE").value = page;
        document.getElementById("page").value = page;
        document.getElementById("form_query").submit();
    }
</script>
</body>

</html>