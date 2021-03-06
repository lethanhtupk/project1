<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hướng dẫn từ vựng chi tiết</title>
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
      
      
            <script type="text/javascript">
      function Comment()
      {
    	  
    	  // get tên user.trong session
    	  //var membername = "${fullname}";
    	  
    	  var xhttp;
    	  var cmtvocabularycontent = document.formcomment.cmtvocabularycontent.value;
    	  var memberid="<%=session.getAttribute("memberid") %>";
    	  var vocabularyguidelineid ="<%= request.getAttribute("vocabularyguidelineid") %>";
    	  
    	  var url ="CommentVocabController?cmtvocabularycontent="+cmtvocabularycontent+"&memberid="+memberid+"&vocabularyguidelineid="+vocabularyguidelineid;
    	  
    	  if(window.XMLHttpRequest){
    		  xhttp = new XMLHttpRequest();
    	  }
    	  else{
    		  xhttp = new ActiveObject("Microsoft.XMLHTTP");
    	  }
    	 
    	  xhttp.onreadystatechange=function(){
    		  if(xhttp.readyState == 4){
    			  
    			  var data = xhttp.responseText;
    			  document.getElementById("listcomment").innerHTML = data;
    			
    			  
    		  }
    		  
    	  }
    	  
    	  xhttp.open("POST",url,true);
    	  xhttp.send();
    	  
      }
      
      
      
      </script>
      
      
        <style type="text/css">
      
      .showtext{
      	text-align: auto;
		padding-top: 0.5em;
		padding-right:1em;
		padding-bottom:0.5em;
		padding-left:1em;
		      
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
				Bài hướng dẫn học từ vựng
				</h1>
		</div>
		</div>
	</div>

  <!-- /. PAGE TITLE-->



	<div class="row">

<!--/Start Blog Post-->
	
		<div class="span9">
		

	
			<div class="blog-post">
			
		
			
			<h2 style="text-align:center; color:blue">Chủ đề: <%= request.getAttribute("vocabguidelinename") %></h2>
				
			
<!-- -->
				<div class="postmetadata">
					<ul>
							<li>
								<i class="icon-user"></i> <a href="#">SoICT-HUST</a>
							</li>

							<li>
								 <i class="icon-calendar"></i><a href="#">April, 2018</a>
							</li>

							<li>
							<!-- count comment in StudyGrammarController -->
								<i class="icon-comment"></i> <a href="#"><%= request.getAttribute("countComment") %> Comments</a>
							</li>
							
							
					</ul>
				</div>
			
					<img src="Template/Frontend/img/vocabulary.jpg" style="height: 330px;width: 870px" >
				
					
					<p>
						<c:forEach items="${listvct}" var="list">
						
							<div class="row" >
								<div class="span3">
									<b>${list.num}.<span style="color:blue"> ${list.vocabularycontentname } </span> </b>
									<br>
									<br>	
										<img src ="ImageAudioVocab/${list.image}" alt="image not found" style="height:210px;width:300px;"/>
										<br>
										
										<br>
								</div>
								<div class="span6">
								<br>
								<br>
								<p>	<b>NAmE:</b> 
									 &nbsp; <span style="color:red">${list.transcribe}</span>
								</p>
								
								<p><b>Từ loại: </b>${list.mean} </p>	
									
								<p><b>Ví dụ: </b>${list.sentence} </p>	
								
								<audio controls>
												<source src ="ImageAudioVocab/${list.audiogg}" type="audio/ogg">
												<source src ="ImageAudioVocab/${list.audiomp3}" type="audio/mpeg">
								</audio>
									
									<br>
								
								</div>
							
									
							
							</div>
						<br>
					
						</c:forEach>
						
					</p>	

						
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
	
<!-- Start Comment -->

  <div class="row">

  		<%
  		if(session.getAttribute("fullname") ==null)
  		{
  		
  		%>
  			<form>
				<fieldset>
					 <h3>Bình luận</h3>
					 
					 <textarea  class="input-xxlarge"  rows="3"   placeholder="Viết bình luận đánh giá về bài đăng này..." name="comment" disabled style="text-align: justify;">
					 
					 Đăng nhập để bình luận bài viết
					</textarea>
						<br>
					 <button type="button" class="btn btn-primary" disabled  ">Đăng bình luận</button>
				</fieldset> 
				
			</form>
		<% 
	  		}
	  		else
	  		{
  		%>
  		
  		
    	
  		<form  name="formcomment" >
  			<div class="blog-spot">
				<div>
					 <h3>Bình luận</h3>
					 <textarea  class="input-xxlarge"  rows="3"  name="cmtvocabularycontent" placeholder="Viết bình luận đánh giá về bài đăng này..." ></textarea>
						
				</div>
				<div>
					 <button type="button" class="btn btn-primary" onclick="Comment()" >Đăng bình luận</button>
				</div>
			</div>
		</form>
  		
  		
  		<% 
	  		}
  		%>
  	
  	<!-- Nội dung commnetn -->
  	
  

  		<div  id ="listcomment">
  		<c:forEach items="${listcommentvocab}" var="list">
  			<div>	
  					  
					  <h4 style="color:red">${list.membername}</h4>
					 <textarea disabled class="input-xxlarge showtext" rows="3" name="cmtvocabularycontent" >${list.cmtvocabularycontent}</textarea>
			</div>	
		 </c:forEach> 		
		</div>
	
  	
  <!-- Nội dung commnetn -->
  		
	  </div> 
	
<!-- End Comment -->
		




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