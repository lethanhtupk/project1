<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lý hướng dẫn ngữ pháp</title>
	
		<link rel="stylesheet" href="Template/Backend/css/bootstrap.min.css" />
		<link rel="stylesheet" href="Template/Backend/font-awesome/4.5.0/css/font-awesome.min.css" />


		<link rel="stylesheet" href="Template/Backend/css/jquery-ui.custom.min.css" />

		
		<link rel="stylesheet" href="Template/Backend/css/fonts.googleapis.com.css" />

		
		<link rel="stylesheet" href="Template/Backend/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />


			<link rel="stylesheet" href="Template/Backend/css/ace-part2.min.css" class="ace-main-stylesheet" />
	
		<link rel="stylesheet" href="Template/Backend/css/ace-skins.min.css" />
		<link rel="stylesheet" href="Template/Backend/css/ace-rtl.min.css" />
		  <link rel="stylesheet" href="Template/Backend/css/ace-ie.min.css" />
		

	
		<script src="Template/Backend//js/ace-extra.min.js"></script>
		

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
							
							<li>
								<a>Grammar Guideline Manage</a>
							</li>
							<li class="active">Add content</li>
						</ul><!-- /.breadcrumb -->

						
					</div>
					
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<div class="row">
								
									<div class="col-sm-7">
										<h4 class="header green">Edit Grammar Content</h4>
										<h4 class="pink">
											
											<a class= "red"> <%=request.getAttribute("msinsertGGlineContent")!=null?request.getAttribute("msinsertGGlineContent") :" " %></a>
												
										</h4>
										
										

										<div class="widget-box widget-color-blue">
											<div class="widget-header widget-header-small">  </div>

						<form action="GrammarGuidelineAddContent?grammarguidelineid=<%=request.getAttribute("grammarguidelineid")%>" method="POST">
											<div class="widget-body">
												<div class="widget-main no-padding">
												
													<textarea name="content" data-provide="markdown" data-iconlibrary="fa" rows="10"   data-hidden-buttons="Image" >

			
													
													</textarea>
												</div>

												<div class="widget-toolbox padding-4 clearfix">
																<!--  div class="btn-group pull-left">
																	<button class="btn btn-sm btn-info">
																		<i class="ace-icon fa fa-times bigger-125"></i>
																		Cancel
																	</button>
																</div-->

													<div class="btn-group pull-right">
														<button class="btn btn-sm btn-purple" type="submit">
															<i class="ace-icon fa fa-floppy-o bigger-125"></i>
															Save
														</button>
													</div>
												</div>
											</div>
											
								</form>
								
										</div>
									</div>
								
								</div>
							
							
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

		<!-- page specific plugin scripts -->
		<script src="Template/Backend/js/jquery-ui.custom.min.js"></script>
		<script src="Template/Backend/js/jquery.ui.touch-punch.min.js"></script>
		<script src="Template/Backend/js/markdown.min.js"></script>
		<script src="Template/Backend/js/bootstrap-markdown.min.js"></script>
		<script src="Template/Backend/js/jquery.hotkeys.index.min.js"></script>
		<script src="Template/Backend/js/bootstrap-wysiwyg.min.js"></script>
		<script src="Template/Backend/js/bootbox.js"></script>

		<!-- ace scripts -->
		<script src="Template/Backend/js/ace-elements.min.js"></script>
		<script src="Template/Backend/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($){
	
	$('textarea[data-provide="markdown"]').each(function(){
        var $this = $(this);

		if ($this.data('markdown')) {
		  $this.data('markdown').showEditor();
		}
		else $this.markdown()
		
		$this.parent().find('.btn').addClass('btn-white');
    })
	
	
	
	function showErrorAlert (reason, detail) {
		var msg='';
		if (reason==='unsupported-file-type') { msg = "Unsupported format " +detail; }
		else {
			//console.log("error uploading file", reason, detail);
		}
		$('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'+ 
		 '<strong>File upload error</strong> '+msg+' </div>').prependTo('#alerts');
	}

	//$('#editor1').ace_wysiwyg();//this will create the default editor will all buttons

	//but we want to change a few buttons colors for the third style
	$('#editor1').ace_wysiwyg({
		toolbar:
		[
			'font',
			null,
			'fontSize',
			null,
			{name:'bold', className:'btn-info'},
			{name:'italic', className:'btn-info'},
			{name:'strikethrough', className:'btn-info'},
			{name:'underline', className:'btn-info'},
			null,
			{name:'insertunorderedlist', className:'btn-success'},
			{name:'insertorderedlist', className:'btn-success'},
			{name:'outdent', className:'btn-purple'},
			{name:'indent', className:'btn-purple'},
			null,
			{name:'justifyleft', className:'btn-primary'},
			{name:'justifycenter', className:'btn-primary'},
			{name:'justifyright', className:'btn-primary'},
			{name:'justifyfull', className:'btn-inverse'},
			null,
			{name:'createLink', className:'btn-pink'},
			{name:'unlink', className:'btn-pink'},
			null,
			{name:'insertImage', className:'btn-success'},
			null,
			'foreColor',
			null,
			{name:'undo', className:'btn-grey'},
			{name:'redo', className:'btn-grey'}
		],
		'wysiwyg': {
			fileUploadError: showErrorAlert
		}
	}).prev().addClass('wysiwyg-style2');

	
	/**
	//make the editor have all the available height
	$(window).on('resize.editor', function() {
		var offset = $('#editor1').parent().offset();
		var winHeight =  $(this).height();
		
		$('#editor1').css({'height':winHeight - offset.top - 10, 'max-height': 'none'});
	}).triggerHandler('resize.editor');
	*/
	

	$('#editor2').css({'height':'200px'}).ace_wysiwyg({
		toolbar_place: function(toolbar) {
			return $(this).closest('.widget-box')
			       .find('.widget-header').prepend(toolbar)
				   .find('.wysiwyg-toolbar').addClass('inline');
		},
		toolbar:
		[
			'bold',
			{name:'italic' , title:'Change Title!', icon: 'ace-icon fa fa-leaf'},
			'strikethrough',
			null,
			'insertunorderedlist',
			'insertorderedlist',
			null,
			'justifyleft',
			'justifycenter',
			'justifyright'
		],
		speech_button: false
	});
	
	


	$('[data-toggle="buttons"] .btn').on('click', function(e){
		var target = $(this).find('input[type=radio]');
		var which = parseInt(target.val());
		var toolbar = $('#editor1').prev().get(0);
		if(which >= 1 && which <= 4) {
			toolbar.className = toolbar.className.replace(/wysiwyg\-style(1|2)/g , '');
			if(which == 1) $(toolbar).addClass('wysiwyg-style1');
			else if(which == 2) $(toolbar).addClass('wysiwyg-style2');
			if(which == 4) {
				$(toolbar).find('.btn-group > .btn').addClass('btn-white btn-round');
			} else $(toolbar).find('.btn-group > .btn-white').removeClass('btn-white btn-round');
		}
	});


	

	//RESIZE IMAGE
	
	//Add Image Resize Functionality to Chrome and Safari
	//webkit browsers don't have image resize functionality when content is editable
	//so let's add something using jQuery UI resizable
	//another option would be opening a dialog for user to enter dimensions.
	if ( typeof jQuery.ui !== 'undefined' && ace.vars['webkit'] ) {
		
		var lastResizableImg = null;
		function destroyResizable() {
			if(lastResizableImg == null) return;
			lastResizableImg.resizable( "destroy" );
			lastResizableImg.removeData('resizable');
			lastResizableImg = null;
		}

		var enableImageResize = function() {
			$('.wysiwyg-editor')
			.on('mousedown', function(e) {
				var target = $(e.target);
				if( e.target instanceof HTMLImageElement ) {
					if( !target.data('resizable') ) {
						target.resizable({
							aspectRatio: e.target.width / e.target.height,
						});
						target.data('resizable', true);
						
						if( lastResizableImg != null ) {
							//disable previous resizable image
							lastResizableImg.resizable( "destroy" );
							lastResizableImg.removeData('resizable');
						}
						lastResizableImg = target;
					}
				}
			})
			.on('click', function(e) {
				if( lastResizableImg != null && !(e.target instanceof HTMLImageElement) ) {
					destroyResizable();
				}
			})
			.on('keydown', function() {
				destroyResizable();
			});
	    }

		enableImageResize();

		/**
		//or we can load the jQuery UI dynamically only if needed
		if (typeof jQuery.ui !== 'undefined') enableImageResize();
		else {//load jQuery UI if not loaded
			//in Ace demo ./components will be replaced by correct components path
			$.getScript("assets/js/jquery-ui.custom.min.js", function(data, textStatus, jqxhr) {
				enableImageResize()
			});
		}
		*/
	}


});
		</script>

</body>
</html>