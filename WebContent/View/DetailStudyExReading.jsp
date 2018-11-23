<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Làm bài tập Reading</title>
 	<link href="Template/Frontend/css/bootstrap.css" rel="stylesheet">
      <link href="Template/Frontend/font/font.css" rel="stylesheet">
	  <link href="Template/Frontend/css/bootstrap.min.css" rel="stylesheet">
	  <link href="Template/Frontend/css/bootstrap-responsive.min.css" rel="stylesheet">
	  <link href="Template/Frontend/css/style.css" rel="stylesheet">
	 
	  <link href="https://fonts.googleapis.com/css?family=Montserrat:300" rel="stylesheet">
  
      <script src="Template/Frontend/js/jquery.js"></script>
      <script src="Template/Frontend/js/bootstrap.min.js"></script>
      <script src="Template/Frontend/js/html5shiv.js"></script>
      <script src="Template/Frontend/js/jquery-1.js"></script>
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
				Bài hướng dẫn Exercise Reading
				</h1>
		</div>
		</div>
	</div>

  <!-- /. PAGE TITLE-->



	<div class="row">

<!--/Start Blog Post-->
	
		<div class="span9">
		

	
			<div class="blog-post">
			
		
			
			<h2 style="text-align:center; color:blue">Chủ đề: <%= request.getAttribute("ExReadingName") %></h2>
				
			
<!-- -->
				<div class="postmetadata">
					<ul>
							<li>
								<i class="icon-user"></i> <a href="#">SoICT-HUST</a>
							</li>

							<li>
								 <i class="icon-calendar"></i><a href="#">April, 2018</a>
							</li>

						
							
					</ul>
				</div>
			
					<img src="Template/Frontend/img/ex-reading.jpg" >
				
				<form action="ScoreExReading?readexerciseid=<%=request.getAttribute("readexerciseid") %>" method="POST" style="float: left;">
					<p>
						<c:forEach items="${listexercisereadingdetail}" var="list">
						
							<div class="row" >
								<div class="span9">
								
										<p><b>${list.num}. </b>${list.question}</p>
									 	<input type="radio" name="ans[${list.num}]" value="A"> <b>A.</b> ${list.option1}
								    	<br>
										<input type="radio" name="ans[${list.num}]" value="B"> <b>B.</b>	${list.option2}
								  		 <br>
										<input type="radio" name="ans[${list.num}]" value="C"> <b>C.</b>	${list.option3}
								  		<br>
										<input type="radio" name="ans[${list.num}]" value="D"> <b>D.</b>	${list.option4}
										<br>
								</div>
								
							
							</div>
						<br>
					
						</c:forEach>
						
					</p>	
						
							<div >
								
								<input type="submit" class="btn btn-primary" value="Score"/>
								
							</div>
						
							</form>
							
							

						
			</div>
			
			
		
			
		</div>	
		
<!--/End Blog Post-->

<!-- Start Categories -->

		<div class="span3">
		<br>
		<br>
		<br>
		<br>
		
		
			<div class="side-bar">

				<h3>Danh mục</h3>
				<ul class="nav nav-list">
					<li><a href="StudyGrammarController?pageid=1">Cách học ngữ pháp</a></li>
					<li><a href="StudyVocabularyController?pageid=1">Cách học từ vựng</a></li>
					<li><a href="StudyExReadingController?pageid=1">Làm bài tập phần đọc</a></li>
					<li><a href="StudyExListeningController?pageid=1">Làm bài tập phần nghe</a></li>
					<li><a href="DisplayListFullExamToeic?pageid=1">Thi thử Toiec</a></li>
				</ul>

			</div>

		</div>	
		
<!-- End Categories -->
		
	
	
		
	</div>



</div>
<!-- End Page Container -->

<!--Footer
==========================-->
<div id ="Pagination1">
<jsp:include page="FooterHome.jsp"/>
</div>

<!--/.Footer-->

</body>
</html>