<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Làm bài tập Ex-Listening</title>
	<link href="Template/Frontend/css/bootstrap.css" rel="stylesheet">
    <link href="Template/Frontend/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="Template/Frontend/css/style.css" rel="stylesheet"> 
    <!-- font -->
    <link rel="stylesheet" href="Template/Frontend/font/font-awesome.min.css" />
    
    <script src="Template/Frontend/js/code-jquery.js"></script>
    <script src="Template/Frontend/js/bootstrap.min.js"></script>
    
    	<script type="text/javascript">
			
			
					function DisplayResult()
					{
						
						var kq = document.myform.radio.value;
						
						
						if(kq == ""){
							
							alert("Bạn chưa trả lời câu hỏi");
							
						}
						
						
						else{
							
							var xhttp;
							
							
							var url = "StudyExListeningController2?kq="+kq+"&num="+<%=request.getAttribute("numberpage")%>+"&listenexerciseid="+<%=request.getAttribute("listenexerciseid")%>;
							
							if (window.XMLHttpRequest) 
							{        
							    xhttp = new XMLHttpRequest(); 
							} 
							
							xhttp.onreadystatechange= function()
							{
								if (xhttp.readyState == 4)
								{
									var data = xhttp.responseText;
									document.getElementById("ketqualambtnghe").innerHTML=data;
								}
								
							}
							
							xhttp.open("POST",url,true);
							xhttp.send();
							
						}
						

					}
						
			
			</script>
			
	
      	
</head>
<body>
	<!--HEADER ROW-->
	  	<jsp:include page="HeaderHome.jsp"></jsp:include>
	 <!-- /HEADER ROW -->
	 
		<div class="container">
			  <!--PAGE TITLE-->
		
			<div class="row">
				<div class="span12">
				<div class="page-header">
					<h3 style="color: blue">
						Photo / Practice Test
						
					</h3>
				</div>
				</div>
			</div>
			
		  	<!-- /. PAGE TITLE-->
		  	<br/>
		  	
			<div class="row">
				<div class="span9">
				<ul class="thumbnails">
					<li class="span9">
						<div class="thumbnail" >
						<form name="myform" id="ketqualambtnghe">
							<c:forEach items="${listquestionexerciselistening}" var ="list">
							<br>
								<p>
									<b>${list.num}. ${list.question}</b>
									
								</p>
								<br>
								<p>
									<img src= "ImageAudioExListening/${list.imagename}" alt="img not found" style="width:350px;height:200px;"/>
									
								</p>
								<p>
									
									<audio controls>
										<source src="ImageAudioExListening/${list.audiogg}" type="audio/ogg">
										<source src="ImageAudioExListening/${list.audiomp3}" type="audio/mpeg">
									</audio>
									
								</p>
								<p>
						  			<input type="radio" name="radio" value="A"/>
						  			A.&nbsp;
						  		</p>
						  		<p>
						  			<input type="radio" name="radio" value="B"/>
						  			B.&nbsp;
						  		</p>
						  		<p>
						  			<input type="radio" name="radio" value="C"/>
						  			C.&nbsp;
						  		</p>
						  		<p>
						  			<input type="radio" name="radio" value="D"/>
						  			D.&nbsp;
						  		</p>
					  		</c:forEach>
					  	</form>
					   </div>
					</li>
				  </ul>
				</div>			
			
			
			
			<!-- Start Categories -->

		<div class="span3 " >
		
			<div class="side-bar ">

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
			<!--Pagination-->
			<div class="row">
				<div class="span12">
					
					<div>
						
							<c:if test="${numberpage == 1}">
							   <a href = "#" class="btn btn-info disabled">Prev</a>
							   <input type="button" value="Score" class="btn btn-primary" onclick="DisplayResult()"/>
							   <a href = "StudyExListeningController2?pageid=${numberpage}&listenexerciseid=<%=request.getAttribute("listenexerciseid")%>" class="btn btn-primary" >Again</a>
							   <a href = "StudyExListeningController2?pageid=${numberpage+1}&listenexerciseid=<%=request.getAttribute("listenexerciseid")%>" class="btn btn-info">Next</a>
							   
						   </c:if>
						   <c:if test="${numberpage == maxpageid}">
							   <a href = "StudyExListeningController2?pageid=${numberpage-1}&listenexerciseid=<%=request.getAttribute("listenexerciseid")%>" class="btn btn-info">Prev</a>
							   <input type="button" value="Score" class="btn btn-primary" onclick="DisplayResult()"/>
							   <a href = "StudyExListeningController2?pageid=${numberpage}&listenexerciseid=<%=request.getAttribute("listenexerciseid")%>" class="btn btn-primary" >Again</a>
							   
							   <a href ="#" class="btn btn-info disabled">Next</a>
						   </c:if>
						   
						   <c:if test="${numberpage > 1 && numberpage < maxpageid}">
							   <a href = "StudyExListeningController2?pageid=${numberpage-1}&listenexerciseid=<%=request.getAttribute("listenexerciseid")%>" class="btn btn-info">Prev</a>
							   <input type="button" value="Score" class="btn btn-primary" onclick="DisplayResult()"/>
							   <a href = "StudyExListeningController2?pageid=${numberpage}&listenexerciseid=<%=request.getAttribute("listenexerciseid")%>" class="btn btn-primary" ">Again</a>
							   
							   <a href = "StudyExListeningController2?pageid=${numberpage+1}&listenexerciseid=<%=request.getAttribute("listenexerciseid")%>" class="btn btn-info">Next</a>
						   </c:if>
					
						   
						
					</div>	
					
			 	</div>
			</div>
			<!--/.Pagination-->
			
				
		</div>
		<br/>
		<br/>
	 <jsp:include page="FooterHome.jsp"></jsp:include>
</body>
</html>