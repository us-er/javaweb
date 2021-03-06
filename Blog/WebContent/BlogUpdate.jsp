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
<!-- begin::dropzone -->
<link rel="stylesheet" href="assets/vendors/dropzone/dropzone.css"
	type="text/css">
<!-- begin::dropzone -->
<!-- begin::datepicker -->
<link rel="stylesheet"
	href="assets/vendors/datepicker/daterangepicker.css">
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
			<a href="/"> <img src="assets/media/image/light-logo.png"
				alt="..."> <span class="logo-text d-none d-lg-block">Blogger</span>
			</a>
		</div>

		<div class="header-body">
			<ul class="navbar-nav">
				<li class="nav-item dropdown d-none d-lg-block"><a href="#"
					class="nav-link" data-toggle="dropdown"> <i
						class="fa fa-th-large"></i>
				</a></li>
			</ul>
			<form class="search">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search ..."
						aria-label="Recipient's username" aria-describedby="button-addon2">
					<div class="input-group-append">
						<button class="btn" type="button" id="button-addon2">
							<i class="fa fa-search"></i>
						</button>
					</div>
				</div>
			</form>
			<ul class="navbar-nav">
				<li class="nav-item"><a href="#"
					class="d-lg-none d-sm-block nav-link search-panel-open"> <i
						class="fa fa-search"></i>
				</a></li>
				<li class="nav-item dropdown"><a href="#"
					class="nav-link nav-link-notify"> <i class="fa fa-envelope"></i>
				</a></li>
				<li class="nav-item"><a href="#"
					class="nav-link nav-link-notify"> <i class="fa fa-bell"></i>
				</a></li>
				<li class="nav-item dropdown"><a href="#"
					data-toggle="dropdown"> <figure
							class="avatar avatar-sm avatar-state-success"> <img
							class="rounded-circle" src="assets/media/image/avatar.jpg"
							alt="..."> </figure>
				</a>
					<div class="dropdown-menu dropdown-menu-right">
						<a href="profile.html" class="dropdown-item">Profile</a> <a
							href="#" data-sidebar-target="#settings"
							class="sidebar-open dropdown-item">Settings</a>
						<div class="dropdown-divider"></div>
						<a href="/blog/logout" class="text-danger dropdown-item">Logout</a>
					</div></li>
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
				<div
					class="page-header d-md-flex justify-content-between align-items-center">
					<h4>Layout Container</h4>
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
			<div class="col-md-10">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">????????????</h5>
						<form action="/blog/upLoadBlogImage" class="dropzone"
							id="my-awesome-dropzone"></form>
					</div>
					<div class="row">
						<div class="col-md-1"></div>
						<%--           ??????????????????                                 --%>
						<c:forEach items="${updateBlog.imgs}" var="img">
							<div class="col-md-2 m-b-15">
								<img src="/photo/${img.name}" alt="..." class="img-thumbnail">
								<button type="button" onclick="deleteBlogImg(${img.id})"
									class="btn btn-danger">delete</button>
							</div>
						</c:forEach>
						<%--           ??????????????????                                 --%>
					</div>
				</div>
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">????????????</h5>
						<form id="blogForm">
							<div class="form-group">
								<label for="exampleFormControlInput1">????????????</label> <select
									name="enId" class="form-control">
									<c:forEach items="${IndexTypeList}" var="IndexType">
										<option value="${IndexType.id}"<c:if test="${IndexType.id==updateBlog.typeid}">selected</c:if>
                                        >${IndexType.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="exampleFormControlInput1">??????</label>
                                <input name="title" placeholder="???????????????" value="${updateBlog.title}"
                                       type="text" class="form-control" id="exampleFormControlInput1">
                            </div>
                            <div class="form-group">
                                <label for="exampleFormControlInput1">????????????</label>
                                <textarea rows="" cols="" name="content" id="exampleFormControlInput1" class="form-control">${updateBlog.content}</textarea>
                            </div>

                            <button type="button" onclick="updateBlog(${updateBlog.id})" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>

            </div>
        </div>

        <script type="application/javascript">

            <%--//?????????????????????????????????ajax??????--%>
            <%--$.post("/blog/queryBlog",{--%>
            <%--    "id":${nowBlogger.id}--%>
            <%--},function (){--%>
            <%--    location.reload();--%>
            <%--});--%>

            //?????????????????????
            function insertBlog(){
                var form = $("#blogForm").serializeArray();
                var typeid = form[0].value;
                var title = form[1].value;
                var content = $("textarea:eq(0)").val();
                console.log(typeid+":"+title+":"+content);
                var images = $(".dz-image img");
                var imgs = new Array();
                for(var i=0;i<images.length;i++){
                    imgs.push(images[i].alt);
                    console.log(images[i].alt);
                }
                console.log(imgs.toString());
                //??????ajax??????
                $.post("/blog/insertBlog",{
                    "typeid":typeid,
                    "title":title,
                    "bloggerId":${nowBlogger.id},
                    "content":content,
                    "img":imgs.toString()
                },function (result){
                    if(result.flag==true){
                        alert('????????????');
                        window.location.href='/blog/queryBlog?id='+${nowBlogger.id};
                    }else {
                        alert('????????????');
                    }
                });
            }
            //?????????????????????
            function updateBlog(id){
                var form = $("#blogForm").serializeArray();
                var typeid = form[0].value;
                var title = form[1].value;
                var content = $("textarea:eq(0)").val();
                console.log(typeid+":"+title+":"+content);
                var images = $(".dz-image img");
                var imgs = new Array();
                for(var i=0;i<images.length;i++){
                    imgs.push(images[i].alt);
                    console.log(images[i].alt);
                }
                console.log(imgs.toString());
                //??????ajax??????
                $.post("/blog/BlogUpdate",{
                    'id':id,
                    "typeid":typeid,
                    "title":title,
                    "content":content,
                    "img":imgs.toString()
                },function (result){
                    if(result==true){
                        alert('????????????');
                        window.location.href='/blog/queryBlog?id='+${nowBlogger.id};
                    }else {
                        alert('????????????');
                    }
                });
            }

            //??????????????????
            function deleteBlogImg(id){
                $.post('/blog/deleteBlogImg',{
                    'id':id
                },function(result){
                    if(result==true){
                        alert('????????????');
                        location.reload();
                    }else{
                        alert('??????????????????????????????');
                    }
                });
            }

        </script>

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
<!-- begin::dropzone -->
<script src="assets/vendors/dropzone/dropzone.js"></script>
<script src="assets/js/examples/dropzone.js"></script>
<!-- end::dropzone -->

<!-- begin::custom scripts -->
<script src="assets/js/custom.js"></script>
<script src="assets/js/app.js"></script>
<!-- end::custom scripts -->
</body>

</html>