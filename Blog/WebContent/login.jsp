<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Gshop - Admin Dashboard</title>

<!-- begin::global styles -->
<link rel="stylesheet" href="assets/vendors/bundle.css" type="text/css">
<!-- end::global styles -->

<!-- begin::custom styles -->
<link rel="stylesheet" href="assets/css/app.css" type="text/css">
<!-- end::custom styles -->

</head>
<body class="bg-white h-100-vh p-t-0">

	<!-- begin::page loader-->
	<div class="page-loader">
		<div class="spinner-border"></div>
		<span>Loading ...</span>
	</div>
	<!-- end::page loader -->

	<div class="p-b-50 d-block d-lg-none"></div>

	<div class="container h-100-vh">
		<div class="row align-items-md-center h-100-vh">
			<div class="col-lg-6 d-none d-lg-block">
				<img class="img-fluid" src="assets/media/svg/login.svg" alt="...">
			</div>
			<div class="col-lg-4 offset-lg-1">
				<div class="d-flex align-items-center m-b-20">
					<img src="assets/media/image/dark-logo.png" class="m-r-15"
                         width="40" alt="">
					<h3 class="m-0"><a href="queryBlog">Blogger</a></h3>
				</div>
				<p>Sign in to continue.</p>
				<form action="/Blog/login" method="get">
					<div class="form-group mb-4">
						<input type="text" name="name" value="${name}"
							class="form-control form-control-lg" id="exampleInputEmail1"
							autofocus placeholder="Phone or username">
					</div>
					<div class="form-group mb-4">
						<input type="password" name="pass" value="${pass}"
							class="form-control form-control-lg" id="exampleInputPassword1"
							placeholder="Password">
					</div>

					<button type="submit"
						class="btn btn-primary btn-lg btn-block btn-uppercase mb-4">Sign
						In</button>
					<div class="row">
						<div class="col-md-6 mb-4">
							<a href="rigister.jsp"
								class="btn btn-outline-facebook btn-block"> <i
								class="fa fa-facebook-square m-r-5"></i>用户注册
							</a>
						</div>
						<div class="col-md-6 mb-4">
							<button class="form-control" type="reset"><i
									class="fa fa-google m-r-5"></i>重置</button>
						</div>
					</div>
					<h1>${message}</h1>
					<div class="text-center">
						Don't have an account? <a href="register.html"
							class="text-primary">Create</a>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- begin::global scripts -->
	<script src="assets/vendors/bundle.js"></script>
	<!-- end::global scripts -->

	<!-- begin::custom scripts -->
	<script src="assets/js/app.js"></script>
	<!-- end::custom scripts -->

</body>
</html>