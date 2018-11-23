<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wellcome Admin</title>
		<link rel="stylesheet" href="Template/Backend/css/bootstrap.min.css" />
		<link rel="stylesheet" href="Template/Backend/font-awesome/4.5.0/css/font-awesome.min.css" />
		
		<link rel="stylesheet" href="Template/Backend/css/colorbox.min.css" />
		<link rel="stylesheet" href="Template/Backend/css/fonts.googleapis.com.css" />
		<link rel="stylesheet" href="Template/Backend/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
		<link rel="stylesheet" href="Template/Backend/css/ace-skins.min.css" />
		<link rel="stylesheet" href="Template/Backend/css/ace-rtl.min.css" />
		<script src="Template/Backend/js/ace-extra.min.js"></script>
		
		
</head>
<body  class="no-skin">

	<!-- header -->
	
		<jsp:include page="HeaderAdmin.jsp"/>
		
	<!--end header -->
		
		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>
			
	<!-- Menu bar -->
	
			<jsp:include page="MenuBar.jsp"/>
			
	<!-- End Menu bar -->
			
		
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="Adminforward">Home</a>
							</li>
						
						</ul><!-- /.breadcrumb -->

						
					</div>
					
					<div class="page-content">
					
						<!--	<div class="page-header">
								<h1>
									Gallery
									<small>
										<i class="ace-icon fa fa-angle-double-right"></i>
										responsive photo gallery using colorbox
									</small>
								</h1>
							</div>
						-->
						
							<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								
								<center >
										<img width="1130" height="600" alt="ảnh nền bách khoa" src="Template/Backend/images/bkadmin.jpg" />
								</center>
								
								
							
							  </div>
							</div>
						
						
					
					</div>
					
					
					
				</div>
			
			</div><!-- /.main-content -->
			
			
		<!-- footer -->
		
			<jsp:include page="FooterAdmin.jsp"/>
			
		<!-- end footer -->
		

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
			
		</div><!-- /.main-container -->
		
		
		
	<script src="Template/Backend/js/jquery-2.1.4.min.js"></script>

		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='Template/Backend/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="Template/Backend/js/bootstrap.min.js"></script>

		<script src="Template/Backend/js/jquery.colorbox.min.js"></script>

		<script src="Template/Backend/js/ace-elements.min.js"></script>
		<script src="Template/Backend/js/ace.min.js"></script>

		<script type="text/javascript">
						jQuery(function($) {
				var $overflow = '';
				var colorbox_params = {
					rel: 'colorbox',
					reposition:true,
					scalePhotos:true,
					scrolling:false,
					previous:'<i class="ace-icon fa fa-arrow-left"></i>',
					next:'<i class="ace-icon fa fa-arrow-right"></i>',
					close:'&times;',
					current:'{current} of {total}',
					maxWidth:'100%',
					maxHeight:'100%',
					onOpen:function(){
						$overflow = document.body.style.overflow;
						document.body.style.overflow = 'hidden';
					},
					onClosed:function(){
						document.body.style.overflow = $overflow;
					},
					onComplete:function(){
						$.colorbox.resize();
					}
				};
			
				$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
				$("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange fa-spin'></i>");//let's add a custom loading icon
				
				
				$(document).one('ajaxloadstart.page', function(e) {
					$('#colorbox, #cboxOverlay').remove();
			   });
			})
		</script>

</body>
</html>