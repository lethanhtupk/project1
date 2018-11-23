<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<link rel="stylesheet" href="Template/Backend/css/bootstrap.min.css" />
		<link rel="stylesheet" href="Template/Backend/font-awesome/4.5.0/css/font-awesome.min.css" />
		<link rel="stylesheet" href="Template/Backend/css/fonts.googleapis.com.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="Template/Backend/css/ace.min.css" />
		<link rel="stylesheet" href="Template/Backend/css/ace-part2.min.css" />
		<link rel="stylesheet" href="Template/Backend/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="Template/Backend/css/ace-ie.min.css" />
		<script src="Template/Backend/js/html5shiv.min.js"></script>
		<script src="Template/Backend/js/respond.min.js"></script>
		
<script type="text/javascript">

function Validate() {
	
	var fullname = document.FormRegister.fullname.value;
	var membername = document.FormRegister.membername.value;
	var memberpass = document.FormRegister.memberpass.value;
	
	if(fullname == ""){
		document.getElementById("errorfullname").innerHTML = "Không để trống tên đầy đủ";
	}
	else{
		document.getElementById("errorfullname").innerHTML = "";
	}
	
	if(membername == ""){
		document.getElementById("errormembername").innerHTML ="Không để trống tên đăng nhập";
			
	}
	else{
		document.getElementById("errormembername").innerHTML ="";
		
	}
	
	if(memberpass ==""){
		document.getElementById("errormemberpass").innerHTML ="Không để trống mật khẩu";

	}
	else if (memberpass.length < 7){
		document.getElementById("errormemberpass").innerHTML ="Độ dài phải lớn hơn 6 kí tự";
	}
	else{
		document.getElementById("errormemberpass").innerHTML ="";
	}
	
}

</script>
		
<title>Trang đăng ký</title>
</head>
<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="ace-icon fa fa-leaf green"></i>
									 <span class="red">JSP</span>
									
									<span class="white" id="id-text2">SERVLET</span>
								</h1>
								<h4 class="blue" id="id-company-text">&copy; SoICT-HUST</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								


				<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="ace-icon fa fa-coffee green"></i>
												Đăng kí tài khoản mới
											</h4>

											<div class="space-6"></div>

											<form name="FormRegister" action="RegisterController" method="POST">
												<fieldset>
												
													<label class="block clearfix" style="color:red">
													
													<!--  nếu đk thành công sẽ hiện thông báo, mặc định để " "-->
													<%=request.getAttribute("msgregister")!=null?request.getAttribute("msgregister") :" " %>
												
													</label>
												
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="Nhập tên đầy đủ" name="fullname" onkeyup="Validate()"/>
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>
													
														<label class="block clearfix" style="color:red" id="errorfullname"></label>
													
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="Nhập tên đăng nhập" name="membername" onkeyup="Validate()"/>
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>
													
													<label class="block clearfix" style="color:red" id="errormembername"></label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="Nhập mật khẩu" name="memberpass" onkeyup="Validate()"/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>
													
													<label class="block clearfix" style="color:red" id="errormemberpass"></label>

													<div class="space"></div>

													<div class="clearfix">
														
<!-- fix size < width of button from 35 to 100> ok? -->
 
														<button type="submit" class="width-100 pull-right btn btn-sm btn-primary" name="dangky" onclick="Validate()">
															<i class="ace-icon fa fa-key"></i>
															
															<span class="bigger-110">ĐĂNG KÝ</span>
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>
											

											
										</div><!-- /.widget-main -->

										<div class="toolbar clearfix">
											<div>
												<a href="Homeforward"  class="forgot-password-link">
												
													<i class="ace-icon fa fa-arrow-left"></i>
													
													Quay về trang chủ
												</a>
											</div>

											
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.login-box -->
							

							<div class="navbar-fixed-top align-right">
								<br />
								&nbsp;
								<a id="btn-login-dark" href="#">Dark</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-blur" href="#">Blur</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-light" href="#">Light</a>
								&nbsp; &nbsp; &nbsp;
							</div>
						</div>
						
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
				
			</div> <!-- /.main-content -->
			
		</div>  <!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="Template/Backend/js/jquery-2.1.4.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="Template/Backend/js/jquery-1.11.3.min.js"></script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='Template/Backend/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
			 $(document).on('click', '.toolbar a[data-target]', function(e) {
				e.preventDefault();
				var target = $(this).data('target');
				$('.widget-box.visible').removeClass('visible');//hide others
				$(target).addClass('visible');//show target
			 });
			});
			
			
			
			//you don't need this, just used for changing background
			jQuery(function($) {
			 $('#btn-login-dark').on('click', function(e) {
				$('body').attr('class', 'login-layout');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'blue');
				
				e.preventDefault();
			 });
			 $('#btn-login-light').on('click', function(e) {
				$('body').attr('class', 'login-layout light-login');
				$('#id-text2').attr('class', 'grey');
				$('#id-company-text').attr('class', 'blue');
				
				e.preventDefault();
			 });
			 $('#btn-login-blur').on('click', function(e) {
				$('body').attr('class', 'login-layout blur-login');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'light-blue');
				
				e.preventDefault();
			 });
			 
			});
		</script>
	</body>
</html>