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

    <!-- begin::custom styles -->
    <link rel="stylesheet" href="assets/css/app.css" type="text/css">
    <link rel="stylesheet" href="assets/css/custom.css" type="text/css">
    <!-- end::custom styles -->

</head>

<body class="hidden-side-menu dark-side-menu">

<!-- begin::page loader-->
<div class="page-loader">
    <div class="spinner-border"></div>
    <span>Loading ...</span>
</div>
<!-- end::page loader -->

<form class="search" action="/blog/queryBlogHome" id="form_query">
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
                        <select class="form-control" name="tid" style="width: 100px">
                            <option value="0">所有博客</option>
                            <c:forEach items="${IndexTypeList}" var="TypeLitem">
                                <option value="${TypeLitem.id}"
                                >
                                        ${TypeLitem.name}
                                </option>
                            </c:forEach>
                        </select>
                    </li>
                </ul>

                <div class="input-group">
                    <input id="page" name="pageNo" type="hidden" value="1"/>
                    <input id="tid" name="tid" type="hidden" value="0"/>
                    <input type="text" name="search" value="${search}" class="form-control" placeholder="Search ..."
                           style="width: 350px"
                           aria-label="Recipient's username" aria-describedby="button-addon2">
                    <div class="input-group-append">
                        <button class="btn" type="submit" id="button-addon2">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>

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
                        <c:if test="${nowBlogger==null}">
                            <a href="/blog/tologin">
                                <figure class="avatar avatar-sm avatar-state-success">
                                    <img class="rounded-circle" src="assets/media/image/avatar.jpg" alt="...">
                                </figure>
                            </a>
                        </c:if>
                        <c:if test="${nowBlogger!=null}">
                            <a href="#" data-toggle="dropdown">
                                <figure class="avatar avatar-sm avatar-state-success">
                                    <img class="rounded-circle" src="assets/media/image/avatar.jpg" alt="...">
                                </figure>
                            </a>
                        </c:if>
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
</form>
<!-- end::navbar -->

<!-- begin::main content -->
<main class="main-content">

    <div class="container-fluid">

        <!-- begin::page header -->
        <div class="row">
            <div class="col-md-1">
            </div>
            <div class="col-md-10">
                <div class="page-header d-md-flex justify-content-between align-items-center">
                    <h4>博客列表</h4>
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
            <div class="col-md-1"></div>
            <div class="col-md-7">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Blog</h5>

                        <%--           个人博客列表                 --%>
                        <c:forEach items="${IndexBlogList}" var="BlogItem">
                            <div class="accordion" id="accordionExample">

                                <div class="card">
                                    <div class="card-header" id="headingOne">
                                        <button class="btn btn-link" type="button" data-toggle="collapse"
                                                data-target="#collapseOne${BlogItem.id}" aria-expanded="true"
                                                aria-controls="collapseOne">
                                                ${BlogItem.title}  #${BlogItem.type.name}
                                        </button>
                                    </div>
                                    <div id="collapseOne${BlogItem.id}" class="collapse show"
                                         aria-labelledby="headingOne">
                                        <div class="card-body">
                                                ${BlogItem.content}
                                            <div class="row">
                                                    <%--           博客中的图片         --%>                     
                                                <c:forEach items="${BlogItem.imgs}" var="img">
                                                    <div class="col-md-2 m-b-15">
                                                        <img src="/photo/${img.name}" alt="..."
                                                             class="img-thumbnail">
                                                    </div>
                                                </c:forEach>   
                                                    <%--           博客中的图片                                 --%>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-5"></div>
                                                <div class="col-md-3">
                                                    时间：
                                                    <fmt:formatDate value="${BlogItem.createdate}"
                                                                    pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                                                </div>
                                                <div class="col-md-2"> <%--<a href="#"> 修改 </a>  |  <a href="#">删除</a>--%> </div>
                                                <div class="col-md-2">博主：${BlogItem.blogger.name}</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                    <%--           评论列表                 --%>
                                <div class="card">
                                    <div class="card-header" id="headingTwo">
                                        <button class="btn btn-link collapsed" type="button" data-toggle="collapse"
                                                data-target="#collapseTwo${BlogItem.id}" aria-expanded="false"
                                                aria-controls="collapseTwo">
                                            点击展开评论列表
                                        </button>
                                    </div>
                                    <div id="collapseTwo${BlogItem.id}" class="collapse" aria-labelledby="headingTwo">
                                        <div class="card-body">
                                           <c:forEach items="${BlogItem.comments}" var="commentItem">
                                                <div class="row">
                                                    <div class="col-md-1">
                                                        <a href="#">${commentItem.bloggerId} : </a>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-8">
                                                            ${commentItem.content}
                                                    </div>
                                                </div>

                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                    <%--           评论列表                --%>

                                    <%--           进行评论                 --%>
                                <div class="card">
                                    <div class="card-header" id="headingThree">
                                        <button class="btn btn-link collapsed" type="button" data-toggle="collapse"
                                                data-target="#collapseThree${BlogItem.id}" aria-expanded="false"
                                                aria-controls="collapseThree">
                                            点击进行评论
                                        </button>
                                    </div>
                                    <div id="collapseThree${BlogItem.id}" class="collapse" aria-labelledby="headingTwo">
                                        <div class="card-body">
                                            <form id="insertBlogComment">
                                                <div class="form-group">
                                                    <label for="Commentarea${BlogItem.id}">评论内容</label>
                                                    <textarea id="Commentarea${BlogItem.id}"
                                                              class="form-control"></textarea>
                                                </div>
                                                <c:if test="${nowBlogger==null}">
                                                    <button class="btn btn-primary" type="button" onclick="toLogin()">
                                                        Submit
                                                    </button>
                                                </c:if>
                                                <c:if test="${nowBlogger!=null}">
                                                    <button class="btn btn-primary" type="button"
                                                            onclick="insertBlogComment(${BlogItem.id},${BlogItem.bloggerId})">
                                                        Submit
                                                    </button>
                                                </c:if>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                    <%--           进行评论                --%>

                            </div>
                            <br/>
                        </c:forEach>
                        <%--           个人博客列表                 --%>

                        <div class="card-body">
                            <nav aria-label="Page navigation example"
                                 style="margin-left: 300px;">
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

            <div class="col-md-3">
                <div class="card">
                    <div class="card-body">
                        <a href="#" class="btn btn-danger btn-block">热点话题</a>
                        <ul class="links m-t-20">
                            <a href="#" class="active">
                                热点 <span class="ml-1">(4)</span>
                            </a>
                            <a href="/blog/queryBlogHome?search=${HotBlogList[0].title}">${HotBlogList[0].title}</a>
                            <a href="/blog/queryBlogHome?search=${HotBlogList[1].title}">${HotBlogList[1].title}</a>
                            <a href="/blog/queryBlogHome?search=${HotBlogList[2].title}">${HotBlogList[2].title}</a>
                            <a href="/blog/queryBlogHome?search=${HotBlogList[3].title}">${HotBlogList[3].title}</a>
                        </ul>
                        <h6 class="m-t-30 font-weight-bold">热点主题</h6>
                        <ul class="links m-t-20">
                            <a href="/blog/queryBlogHome?tid=${IndexTypeList[0].id}" class="d-flex align-items-center">
                                ${IndexTypeList[0].name}
                                <span class="text-warning fa fa-circle ml-auto"></span>
                            </a>
                            <a href="/blog/queryBlogHome?tid=${IndexTypeList[1].id}" class="d-flex align-items-center">
                                ${IndexTypeList[1].name}
                                <span class="text-danger fa fa-circle ml-auto"></span>
                            </a>
                            <a href="/blog/queryBlogHome?tid=${IndexTypeList[2].id}" class="d-flex align-items-center">
                                ${IndexTypeList[2].name}
                                <span class="text-info fa fa-circle ml-auto"></span>
                            </a>
                            <a href="/blog/queryBlogHome?tid=${IndexTypeList[3].id}" class="d-flex align-items-center">
                                ${IndexTypeList[3].name}
                                <span class="text-secondary fa fa-circle ml-auto"></span>
                            </a>
                            <a href="/blog/queryBlogHome?tid=${IndexTypeList[4].id}" class="d-flex align-items-center">
                                ${IndexTypeList[4].name}
                                <span class="text-success fa fa-circle ml-auto"></span>
                            </a>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-1"></div>
        </div>


    </div>

</main>
<!-- end::main content -->

<!-- begin::global scripts -->
<script src="assets/vendors/bundle.js"></script>
<!-- end::global scripts -->

<!-- begin::custom scripts -->
<script src="assets/js/custom.js"></script>
<script src="assets/js/app.js"></script>
<!-- end::custom scripts -->
<script type="application/javascript">
    //发表评论
    function insertBlogComment(blogid, bloggerid) {
        var comment = $("#Commentarea" + blogid).val();
        console.log(comment + ":" + blogid + ":" + bloggerid);
        //提交ajax请求
        $.post("/blog/insertComment", {
            "content": comment,
            "blogId": blogid,
            "bloggerId": bloggerid
        }, function (result) {
            if (result.login == false) {
                alert("请先登录");
                location.href = "/blog/tologin";
            }
            if (result.flag == true) {
                alert('评论成功，请等待博主审核！');
            } else {
                alert('评论失败，请重新操作！');
            }
        });
    }

    //没有登录
    function toLogin() {
        location.href = "/blog/tologin";
    }

    function jumpPage(page, tid) {
        //要修改访问的页码
        //document.getElementById("job_TYPE").value = page;
        document.getElementById("page").value = page;
        document.getElementById("tid").value = tid;
        document.getElementById("form_query").submit();
    }
</script>
</body>

</html>