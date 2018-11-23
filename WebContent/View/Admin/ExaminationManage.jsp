<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Danh sách đề thi</title>

		<link rel="stylesheet" href="Template/Backend/css/bootstrap.min.css" />
		<link rel="stylesheet" href="Template/Backend/font-awesome/4.5.0/css/font-awesome.min.css" />
		<link rel="stylesheet" href="Template/Backend/css/colorbox.min.css" />
		<link rel="stylesheet" href="Template/Backend/css/fonts.googleapis.com.css" />
		<link rel="stylesheet" href="Template/Backend/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
		<link rel="stylesheet" href="Template/Backend/css/ace-skins.min.css" />
		<link rel="stylesheet" href="Template/Backend/css/ace-rtl.min.css" />
		<script src="Template/Backend/js/ace-extra.min.js"></script>
		
</head>
<body class="no-skin">


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
	<!-- Start main content -->
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="Adminforward">Home</a>
							</li>
							<li><a>Exam Manage</a></li>
							<li class="ative">List Examination</li>
						
						</ul><!-- /.breadcrumb -->

						
					</div>
					
					<div class="page-content">
							
						
							<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								
								
								<div class="row">
									<div class="col-xs-12">
									
									<!--  check lỗi -->
								<!--	<h1>so phan tu: <%=request.getAttribute("sophantucualist") %></h1> -->
								<!-- 	<h1>Loi: <%=request.getAttribute("error") %></h1> -->
								<!--	<h1>Xoas 1 comment:  <%=request.getAttribute("errordelete1") %></h1> -->
								<!--<h1>Xoas 2 grammarguideline:  <%=request.getAttribute("errordelete2") %></h1>-->
									
										<table id="simple-table" class="table  table-bordered table-hover">
												<thead>
															<tr>
																<th class="center">
																Exam Id
																</th>
																<th class="center">
																Exam Name
																</th>
																<th class="center">
																Exam Image
																</th>
																<th class="center">
																Add Content
																</th>
																<th class="center">
																Check Content
																</th>
																<th class="center">
																Delete
																</th>
															</tr>
												</thead>
											
											<tbody>
											
											<!-- dùng foreach để load dữ liệu lên -->
												<c:forEach items="${listexamination}" var="list">
													<tr>
														<td class="center">
															${list.examinationid}
														</td>
														<td class="center">
															${list.examinationname}
														</td>
														<td class="center">
															${list.examinationimage}
														</td>
														<td class="center">
														
						<!-- Content cua bai grammar -->
															<a class="green" href="ExaminationAddContent?examinationid=${list.examinationid}">
																<i class="ace-icon fa fa-pencil bigger-130"></i>
															</a>
														</td>
														
														<td class="center">
														
																<ul class="list-unstyled">
																	<c:if test="${list.checkexamination==1}">
																		<li>
																			<i class="ace-icon fa fa-check-square-o"></i>
																			
																		</li>
																	</c:if>
																	
																	<c:if test="${list.checkexamination==0}">
																		<li>
																			<i class="ace-icon fa fa-square-o"></i>
																			
																		</li>
																	</c:if>
																</ul>		
														
														
														
														</td>
														
														
														<td class="center">
															<a class="red" href="">
																<i class="ace-icon fa fa-trash-o bigger-130"></i>
															</a>
														</td>
														
													</tr>
												</c:forEach>
											
											</tbody>
										
										</table>
										
								
									</div>
								</div>
								
									<div class="row">
										<div class="col-xs-2">
											<button type="button" class="btn btn-white btn-info btn-bold" data-toggle="modal" data-target="#myModal">
												<i class="ace-icon fa fa-floppy-o bigger-120 blue"></i>
												Add Examination
											</button>
										</div>
										
									<form action = "ExamAddAudioImage" method="GET">
										<div class="col-xs-2">
											<button type="submit" class="btn btn-white btn-info btn-bold" >
												<i class="ace-icon fa fa-floppy-o bigger-120 blue"></i>
												Add Image & Audio
											</button>
										</div>
									</form>
									
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
		
		
		<!--  data-toggle="modal" data-target="#myModal" -->


		<!-- Create modal Add name for Exam-->
		<form action="InsertExaminationName" method="POST">
			 <div class="modal fade" id="myModal" role="dialog">
			    <div class="modal-dialog">
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title">Add name of Exam</h4>
			        </div>
			        
			        <div class="modal-body">
			       		<input type="text" id="form-field-1-1" placeholder="Add name here..." class="form-control"  name="examinationname"/>
			        </div>
			        
			        <div class="modal-footer">
			        
			            <button class="btn btn-info" type="submit">
							<i class="ace-icon fa fa-check bigger-110"></i>
								ADD NAME
						</button>
						
						<!--  >h1>get check loi add name: <%=request.getAttribute("msinsertGGname") %></h1-->
						
			        </div>
			      </div>
			      
			      </div>
			      
			      </div>

	</form>


<script src="Template/Backend/js/jquery-2.1.4.min.js"></script>
		
<script src="Template/Backend/js/jquery-1.11.3.min.js"></script>

		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='Template/Backend/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="Template/Backend/js/bootstrap.min.js"></script>

		
		<script src="Template/Backend/js/jquery.dataTables.min.js"></script>
		<script src="Template/Backend/js/jquery.dataTables.bootstrap.min.js"></script>
		<script src="Template/Backend/js/dataTables.buttons.min.js"></script>
		<script src="Template/Backend/js/buttons.flash.min.js"></script>
		<script src="Template/Backend/js/buttons.html5.min.js"></script>
		<script src="Template/Backend/js/buttons.print.min.js"></script>
		<script src="Template/Backend/js/buttons.colVis.min.js"></script>
		<script src="Template/Backend/js/dataTables.select.min.js"></script>

		
		<script src="Template/Backend/js/ace-elements.min.js"></script>
		<script src="Template/Backend/js/ace.min.js"></script>

		
		<script type="text/javascript">
			jQuery(function($) {
				//initiate dataTables plugin
				var myTable = 
				$('#dynamic-table')
				//.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
				.DataTable( {
					bAutoWidth: false,
					"aoColumns": [
					  { "bSortable": false },
					  null, null,null, null, null,
					  { "bSortable": false }
					],
					"aaSorting": [],
					
					
					select: {
						style: 'multi'
					}
			    } );
			
				
				
				$.fn.dataTable.Buttons.defaults.dom.container.className = 'dt-buttons btn-overlap btn-group btn-overlap';
				
				new $.fn.dataTable.Buttons( myTable, {
					buttons: [
					  {
						"extend": "colvis",
						"text": "<i class='fa fa-search bigger-110 blue'></i> <span class='hidden'>Show/hide columns</span>",
						"className": "btn btn-white btn-primary btn-bold",
						columns: ':not(:first):not(:last)'
					  },
					  {
						"extend": "copy",
						"text": "<i class='fa fa-copy bigger-110 pink'></i> <span class='hidden'>Copy to clipboard</span>",
						"className": "btn btn-white btn-primary btn-bold"
					  },
					  {
						"extend": "csv",
						"text": "<i class='fa fa-database bigger-110 orange'></i> <span class='hidden'>Export to CSV</span>",
						"className": "btn btn-white btn-primary btn-bold"
					  },
					  {
						"extend": "excel",
						"text": "<i class='fa fa-file-excel-o bigger-110 green'></i> <span class='hidden'>Export to Excel</span>",
						"className": "btn btn-white btn-primary btn-bold"
					  },
					  {
						"extend": "pdf",
						"text": "<i class='fa fa-file-pdf-o bigger-110 red'></i> <span class='hidden'>Export to PDF</span>",
						"className": "btn btn-white btn-primary btn-bold"
					  },
					  {
						"extend": "print",
						"text": "<i class='fa fa-print bigger-110 grey'></i> <span class='hidden'>Print</span>",
						"className": "btn btn-white btn-primary btn-bold",
						autoPrint: false,
						message: 'This print was produced using the Print button for DataTables'
					  }		  
					]
				} );
				myTable.buttons().container().appendTo( $('.tableTools-container') );
				
				//style the message box
				var defaultCopyAction = myTable.button(1).action();
				myTable.button(1).action(function (e, dt, button, config) {
					defaultCopyAction(e, dt, button, config);
					$('.dt-button-info').addClass('gritter-item-wrapper gritter-info gritter-center white');
				});
				
				
				var defaultColvisAction = myTable.button(0).action();
				myTable.button(0).action(function (e, dt, button, config) {
					
					defaultColvisAction(e, dt, button, config);
					
					
					if($('.dt-button-collection > .dropdown-menu').length == 0) {
						$('.dt-button-collection')
						.wrapInner('<ul class="dropdown-menu dropdown-light dropdown-caret dropdown-caret" />')
						.find('a').attr('href', '#').wrap("<li />")
					}
					$('.dt-button-collection').appendTo('.tableTools-container .dt-buttons')
				});
			
				////
			
				setTimeout(function() {
					$($('.tableTools-container')).find('a.dt-button').each(function() {
						var div = $(this).find(' > div').first();
						if(div.length == 1) div.tooltip({container: 'body', title: div.parent().text()});
						else $(this).tooltip({container: 'body', title: $(this).text()});
					});
				}, 500);
				
				
				
				
				
				myTable.on( 'select', function ( e, dt, type, index ) {
					if ( type === 'row' ) {
						$( myTable.row( index ).node() ).find('input:checkbox').prop('checked', true);
					}
				} );
				myTable.on( 'deselect', function ( e, dt, type, index ) {
					if ( type === 'row' ) {
						$( myTable.row( index ).node() ).find('input:checkbox').prop('checked', false);
					}
				} );
			
			
			
			
				/////////////////////////////////
				//table checkboxes
				$('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);
				
				//select/deselect all rows according to table header checkbox
				$('#dynamic-table > thead > tr > th input[type=checkbox], #dynamic-table_wrapper input[type=checkbox]').eq(0).on('click', function(){
					var th_checked = this.checked;//checkbox inside "TH" table header
					
					$('#dynamic-table').find('tbody > tr').each(function(){
						var row = this;
						if(th_checked) myTable.row(row).select();
						else  myTable.row(row).deselect();
					});
				});
				
				//select/deselect a row when the checkbox is checked/unchecked
				$('#dynamic-table').on('click', 'td input[type=checkbox]' , function(){
					var row = $(this).closest('tr').get(0);
					if(this.checked) myTable.row(row).deselect();
					else myTable.row(row).select();
				});
			
			
			
				$(document).on('click', '#dynamic-table .dropdown-toggle', function(e) {
					e.stopImmediatePropagation();
					e.stopPropagation();
					e.preventDefault();
				});
				
				
				
				//And for the first simple table, which doesn't have TableTools or dataTables
				//select/deselect all rows according to table header checkbox
				var active_class = 'active';
				$('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
					var th_checked = this.checked;//checkbox inside "TH" table header
					
					$(this).closest('table').find('tbody > tr').each(function(){
						var row = this;
						if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
						else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
					});
				});
				
				//select/deselect a row when the checkbox is checked/unchecked
				$('#simple-table').on('click', 'td input[type=checkbox]' , function(){
					var $row = $(this).closest('tr');
					if($row.is('.detail-row ')) return;
					if(this.checked) $row.addClass(active_class);
					else $row.removeClass(active_class);
				});
			
				
			
				/********************************/
				//add tooltip for small view action buttons in dropdown menu
				$('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
				
				//tooltip placement on right or left
				function tooltip_placement(context, source) {
					var $source = $(source);
					var $parent = $source.closest('table')
					var off1 = $parent.offset();
					var w1 = $parent.width();
			
					var off2 = $source.offset();
					//var w2 = $source.width();
			
					if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
					return 'left';
				}
				
				
				$('.show-details-btn').on('click', function(e) {
					e.preventDefault();
					$(this).closest('tr').next().toggleClass('open');
					$(this).find(ace.vars['.icon']).toggleClass('fa-angle-double-down').toggleClass('fa-angle-double-up');
				});	
			
			
			})
		</script>
	
	
	


</body>
</html>