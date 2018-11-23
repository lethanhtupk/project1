<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hướng dẫn học ngữ pháp</title>

 	  <link href="Template/Frontend/css/bootstrap.css" rel="stylesheet">
      <link href="Template/Frontend/font/font.css" rel="stylesheet">
	  <link href="Template/Frontend/css/bootstrap.min.css" rel="stylesheet">
	  <link href="Template/Frontend/css/bootstrap-responsive.min.css" rel="stylesheet">
	  <link href="Template/Frontend/css/style.css" rel="stylesheet">

      <script src="Template/Frontend/js/jquery.js"></script>
      <script src="Template/Frontend/js/bootstrap.min.js"></script>
      <script src="Template/Frontend/js/html5shiv.js"></script>
      <script src="Template/Frontend/js/jquery-1.js"></script>
      
   		<style type="text/css">
   		#Pagination1{
   			
   			padding-top:100px;
   		}
   		
   		</style>
      
 

</head>
<body>
<!--Header
==========================-->

<jsp:include page="HeaderHome.jsp"/>

<!--/End Headter-->

<!-- Page container -->
<div class="container">
	  <!--PAGE TITLE-->

	<div class="row">
		<div class="span12">
		<div class="page-header">
				<h1>
				Danh sách bài tập hướng dẫn ngữ pháp:
			</h1>
		</div>
		</div>
	</div>

  <!-- /. PAGE TITLE-->

	
	<div class="row">
	<c:forEach items="${listggl}" var="list">
	
	<div class="span6">
			<div class="media">
			<form action="StudyGrammarController?grammarguidelineid=${list.grammarguidelineid}" method="POST">
				 <a href="#" class="pull-left"><img src="ImageUpload/${list.grammarimage}" style="height:150px; width:250px" class="media-object" alt='not found image' /></a>
				
				
					<div class="media-body">
					
						<h4 class="media-heading" align="justify" >
						&nbsp;	<span>${list.grammarname}</span>
						</h4> 
						
						
						&nbsp;&nbsp;<input type="submit" value="Chi tiết" name="detailStudyGrammar" class="btn btn-primary"/>
					
					</div>
				
				</form>
				
				</div>
		</div>
	</c:forEach>			

</div>

	


	<!--Pagination-->
			<div class="pagination">
				<ul>
					<c:if test="${numberpage==1}">
						  <li class="disabled"><a href="#">Prev</a></li>
						  <li ><a href="StudyGrammarController?pageid=${numberpage+1}">Next</a></li>
					</c:if>
					
					<c:if test="${numberpage==maxpageid}">
						  <li><a href="StudyGrammarController?pageid=${numberpage- 1}">Prev</a></li>
						  <li class="disabled"><a href="#">Next</a></li>
					</c:if>
					
					<c:if test="${numberpage>1 && numberpage< maxpageid}">
						  <li ><a href="StudyGrammarController?pageid=${numberpage- 1}">Prev</a></li>
						  <li ><a href="StudyGrammarController?pageid=${numberpage+1}">Next</a></li>
					</c:if>
				</ul>
			</div>	

		<!--/.End Pagination-->
		
</div>

<!-- End Page container -->

<!--Footer
==========================-->
<div id ="Pagination1">
<jsp:include page="FooterHome.jsp"/>
</div>

<!--/.Footer-->



</body>
</html>