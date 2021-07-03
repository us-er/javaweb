<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
            <form class="search" id="form_query" action="/blog/queryType" >
                <div class="input-group">
                    <input id="page" name="pageNo" type="hidden" value="1" />
                    <input type="text" name="search" value="${search}" class="form-control" placeholder="Search ..."
                           aria-label="Recipient's username" aria-describedby="button-addon2">
                    <div class="input-group-append">
                        <button class="btn" type="submit" id="button-addon2">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
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
                    <h4>博客类型</h4>
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
            <div class="col-md-8">
                <div class="card">

                    <div class="table-email-list">
                        <div class="table-responsive">
                            <table class="table table-hover" style="margin-top: 30px;">
                                <tbody>
                                <tr >
                                    <td>类型id</td>
                                    <td>类型名称</td>
                                    <td>操作</td>
                                </tr>

                                <c:forEach items="${TypeList}" var="TypeItem">
                                    <tr>
                                        <td><a href="#">${TypeItem.id}</a></td>
                                        <td><span class="badge-primary badge">
                                                ${TypeItem.name} </span></td>
                                        <td>
                                            <div class="dropdown">
                                            <span class="badge-info badge">
                                                <a c href="/blog/toTypeUpdate?id=${TypeItem.id}">修改</a></span>
                                            <span class="badge-danger badge">
                                                <a  href="/blog/deleteType?id=${TypeItem.id}">删除</a></span>
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
                             style="margin-left: 330px;">
                            <ul class="pagination">
                                <li class="page-item"><a class="page-link" href="#">【${pageInfo.pageNum}/${pageInfo.pages}，共${pageInfo.total}条记录】
                                </a></li>
                                <li class="page-item"><a class="page-link"
                                                         href="javascript:jumpPage(1)">首页</a></li>
                                <c:if test="${pageInfo.prePage!=0}">
                                    <li class="page-item"><a class="page-link"
                                                             href="javascript:jumpPage(${pageInfo.prePage})">上一页</a></li>
                                </c:if>
                                <li class="page-item"><a class="page-link"
                                                         href="javascript:jumpPage(${pageInfo.pageNum})">${pageInfo.pageNum}</a></li>
                                <c:if test="${pageInfo.nextPage!=0}">
                                    <li class="page-item"><a class="page-link"
                                                             href="javascript:jumpPage(${pageInfo.nextPage})">下一页</a></li>
                                </c:if>
                                <li class="page-item"><a class="page-link"
                                                         href="javascript:jumpPage(${pageInfo.pages})">尾页</a></li>
                            </ul>
                        </nav>
                    </div>

                </div>
            </div>

            <div class="col-md-3">
                <div class="card">
                    <div class="card-body">
                        <a href="./compose-email.html" class="btn btn-danger btn-block">Compose</a>
                        <ul class="links m-t-20">
                            <a href="#" class="active">
                                Inbox <span class="ml-1">(18)</span>
                            </a>
                            <a href="#">Starred</a>
                            <a href="#">Important</a>
                            <a href="#">Draft</a>
                            <a href="#">Trash</a>
                        </ul>
                        <h6 class="m-t-30 font-weight-bold">Labels</h6>
                        <ul class="links m-t-20">
                            <a href="#" class="d-flex align-items-center">
                                Theme Support
                                <span class="text-warning fa fa-circle ml-auto"></span>
                            </a>
                            <a href="#" class="d-flex align-items-center">
                                Freelance
                                <span class="text-danger fa fa-circle ml-auto"></span>
                            </a>
                            <a href="#" class="d-flex align-items-center">
                                Social
                                <span class="text-info fa fa-circle ml-auto"></span>
                            </a>
                            <a href="#" class="d-flex align-items-center">
                                Friends
                                <span class="text-secondary fa fa-circle ml-auto"></span>
                            </a>
                            <a href="#" class="d-flex align-items-center">
                                Chat
                                <span class="text-success fa fa-circle ml-auto"></span>
                            </a>
                        </ul>
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

    function jumpPage(page) {
        //要修改访问的页码
        //document.getElementById("job_TYPE").value = page;
        document.getElementById("page").value = page;
        document.getElementById("form_query").submit();
    }
</script>
</body>

</html>