<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kết quả bài làm Reading</title>

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

<!--/End Header-->

<!-- Page container -->

<div class="container">
	  <!--PAGE TITLE-->

	<div class="row">
		<div class="span12">
		<div class="page-header">
				<h1>
				Đáp án bài  Exercise Reading
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
				



		<form action="QuizForward">
		<c:forEach items = "${listcorrectanswer}" var = "listcorrectanswer">
			<c:forEach items = "${listansweruser}" var = "listansweruser">
			
				<c:if test="${listcorrectanswer.num == listansweruser.num}">
					<c:if test="${listcorrectanswer.correctanswer == 'A'}">
					
							<c:if test="${listansweruser.answer == 'A'}">
								<p>${listcorrectanswer.num}. ${listcorrectanswer.question}</p>
								   A.&nbsp;${listcorrectanswer.option1}&nbsp; <img alt="img not found" src="Image/correct.png">
								<br>
								  B.&nbsp;${listcorrectanswer.option2}
								<br>
								  C.&nbsp;${listcorrectanswer.option3}
								<br>
								  D.&nbsp;${listcorrectanswer.option4}
								<br>
							</c:if>
								
							<c:if test="${listansweruser.answer == 'B'}">
								<p>${listcorrectanswer.num}. ${listcorrectanswer.question}</p>
								 A.&nbsp;${listcorrectanswer.option1} &nbsp; <img alt="img not found" src="Image/correct.png">
								<br>
								  B.&nbsp;${listcorrectanswer.option2}&nbsp; <img alt="img not found" src="Image/incorrect.png">
								<br>
								  C.&nbsp;${listcorrectanswer.option3}
								<br>
								  D.&nbsp;${listcorrectanswer.option4}
								<br>
							</c:if>
							
							<c:if test="${listansweruser.answer == 'C'}">
								<p>${listcorrectanswer.num}. ${listcorrectanswer.question}</p>
								  A.&nbsp;${listcorrectanswer.option1}&nbsp;<img alt="img not found" src="Image/correct.png">
								<br>
								  B.&nbsp;${listcorrectanswer.option2}
								<br>
								  C.&nbsp;${listcorrectanswer.option3}&nbsp;<img alt="img not found" src="Image/incorrect.png">
								<br>
								  D.&nbsp;${listcorrectanswer.option4}
								<br>
							</c:if>
							
							<c:if test="${listansweruser.answer == 'D'}">
								<p>${listcorrectanswer.num}. ${listcorrectanswer.question}</p>
								  A.&nbsp;${listcorrectanswer.option1} &nbsp; <img alt="img not found" src="Image/correct.png">
								<br>
								  B.&nbsp;${listcorrectanswer.option2}
								<br>
								  C.&nbsp;${listcorrectanswer.option3}
								<br>
								  D.&nbsp;${listcorrectanswer.option4}&nbsp;<img alt="img not found" src="Image/incorrect.png">
								<br>
							</c:if>
							
							<c:if test="${listansweruser.answer == 'Không chọn'}"> 
								
								<p>${listcorrectanswer.num}. ${listcorrectanswer.question}</p>
								<p style="color:red"> Không chọn câu trả lời</p>
								  A.&nbsp;${listcorrectanswer.option1} &nbsp; <img alt="img not found" src="Image/correct.png">
								<br>
								  B.&nbsp;${listcorrectanswer.option2}
								<br>
								  C.&nbsp;${listcorrectanswer.option3}
								<br>
								  D.&nbsp;${listcorrectanswer.option4}
								<br>
							
							
							</c:if>
							
							
					
					</c:if>
					
					<c:if test="${listcorrectanswer.correctanswer == 'B'}">
						
							<c:if test="${listansweruser.answer == 'A'}">
								<p>${listcorrectanswer.num}. ${listcorrectanswer.question}</p>
								  A.&nbsp;${listcorrectanswer.option1}&nbsp;<img alt="img not found" src="Image/incorrect.png">
								<br>
								   B.&nbsp;${listcorrectanswer.option2}&nbsp;<img alt="img not found" src="Image/correct.png">
								<br>
								  C.&nbsp;${listcorrectanswer.option3}
								<br>
								  D.&nbsp;${listcorrectanswer.option4}
								<br>
							</c:if>
								
							<c:if test="${listansweruser.answer == 'B'}">
								<p>${listcorrectanswer.num}. ${listcorrectanswer.question}</p>
								  A.&nbsp;${listcorrectanswer.option1}
								<br>
								  B.&nbsp;${listcorrectanswer.option2}&nbsp;<img alt="img not found" src="Image/correct.png">
								<br>
								  C.&nbsp;${listcorrectanswer.option3}
								<br>
								  D.&nbsp;${listcorrectanswer.option4}
								<br>
							</c:if>
							
							<c:if test="${listansweruser.answer == 'C'}">
								<p>${listcorrectanswer.num}. ${listcorrectanswer.question}</p>
								  A.&nbsp;${listcorrectanswer.option1}
								<br>
								  B.&nbsp;${listcorrectanswer.option2}&nbsp;<img alt="img not found" src="Image/correct.png">
								<br>
								  C.&nbsp;${listcorrectanswer.option3}&nbsp;<img alt="img not found" src="Image/incorrect.png">
								<br>
								  D.&nbsp;${listcorrectanswer.option4}
								<br>
							</c:if>
							
							<c:if test="${listansweruser.answer == 'D'}">
								<p>${listcorrectanswer.num}. ${listcorrectanswer.question}</p>
								  A.&nbsp;${listcorrectanswer.option1}
								<br>
								  B.&nbsp;${listcorrectanswer.option2}&nbsp;<img alt="img not found" src="Image/correct.png">
								<br>
								  C.&nbsp;${listcorrectanswer.option3}
								<br>
								  D.&nbsp;${listcorrectanswer.option4}&nbsp;<img alt="img not found" src="Image/incorrect.png">
								<br>
							</c:if>
							
							<c:if test="${listansweruser.answer == 'Không chọn'}"> 
								
								<p>${listcorrectanswer.num}. ${listcorrectanswer.question}</p>
								<p style="color:red"> Không chọn câu trả lời</p>
								  A.&nbsp;${listcorrectanswer.option1}  
								<br>
								  B.&nbsp;${listcorrectanswer.option2}&nbsp;<img alt="img not found" src="Image/correct.png">
								<br>
								  C.&nbsp;${listcorrectanswer.option3}
								<br>
								  D.&nbsp;${listcorrectanswer.option4}
								<br>
							
							
							</c:if>
					
					
					</c:if>
					
					<c:if test="${listcorrectanswer.correctanswer == 'C'}">
						
							<c:if test="${listansweruser.answer == 'A'}">
								<p>${listcorrectanswer.num}. ${listcorrectanswer.question}</p>
								  A.&nbsp;${listcorrectanswer.option1}&nbsp;<img alt="img not found" src="Image/incorrect.png">
								<br>
								   B.&nbsp;${listcorrectanswer.option2}
								<br>
								  C.&nbsp;${listcorrectanswer.option3}&nbsp;<img alt="img not found" src="Image/correct.png">
								<br>
								  D.&nbsp;${listcorrectanswer.option4}
								<br>
							</c:if>
								
							<c:if test="${listansweruser.answer == 'B'}">
								<p>${listcorrectanswer.num}. ${listcorrectanswer.question}</p>
								  A.&nbsp;${listcorrectanswer.option1}
								<br>
								  B.&nbsp;${listcorrectanswer.option2}&nbsp;<img alt="img not found" src="Image/incorrect.png">
								<br>
								  C.&nbsp;${listcorrectanswer.option3}&nbsp;<img alt="img not found" src="Image/correct.png">
								<br>
								  D.&nbsp;${listcorrectanswer.option4}
								<br>
							</c:if>
							
							<c:if test="${listansweruser.answer == 'C'}">
								<p>${listcorrectanswer.num}. ${listcorrectanswer.question}</p>
								  A.&nbsp;${listcorrectanswer.option1}
								<br>
								  B.&nbsp;${listcorrectanswer.option2}
								<br>
								  C.&nbsp;${listcorrectanswer.option3}&nbsp;<img alt="img not found" src="Image/correct.png">
								<br>
								  D.&nbsp;${listcorrectanswer.option4}
								<br>
							</c:if>
							
							<c:if test="${listansweruser.answer == 'D'}">
								<p>${listcorrectanswer.num}. ${listcorrectanswer.question}</p>
								  A.&nbsp;${listcorrectanswer.option1}
								<br>
								  B.&nbsp;${listcorrectanswer.option2}
								<br>
								  C.&nbsp;${listcorrectanswer.option3}&nbsp;<img alt="img not found" src="Image/correct.png">
								<br>
								  D.&nbsp;${listcorrectanswer.option4}&nbsp;<img alt="img not found" src="Image/incorrect.png">
								<br>
							</c:if>
							
							<c:if test="${listansweruser.answer == 'Không chọn'}"> 
								
								<p>${listcorrectanswer.num}. ${listcorrectanswer.question}</p>
								<p style="color:red"> Không chọn câu trả lời</p>
								  A.&nbsp;${listcorrectanswer.option1}  
								<br>
								  B.&nbsp;${listcorrectanswer.option2}
								<br>
								  C.&nbsp;${listcorrectanswer.option3}&nbsp;<img alt="img not found" src="Image/correct.png">
								<br>
								  D.&nbsp;${listcorrectanswer.option4}
								<br>
							
							
							</c:if>
							
					
					
					</c:if>
					
					<c:if test="${listcorrectanswer.correctanswer == 'D'}">
						
					
							<c:if test="${listansweruser.answer == 'A'}">
								<p>${listcorrectanswer.num}. ${listcorrectanswer.question}</p>
								  A.&nbsp;${listcorrectanswer.option1} &nbsp;<img alt="img not found" src="Image/incorrect.png">
								<br>
								  B.&nbsp;${listcorrectanswer.option2}
								<br>
								  C.&nbsp;${listcorrectanswer.option3}
								<br>
								  D.&nbsp;${listcorrectanswer.option4}&nbsp;<img alt="img not found" src="Image/correct.png">
								<br>
							</c:if>
								
							<c:if test="${listansweruser.answer == 'B'}">
								<p>${listcorrectanswer.num}. ${listcorrectanswer.question}</p>
								  A.&nbsp;${listcorrectanswer.option1}
								<br>
								  B.&nbsp;${listcorrectanswer.option2}&nbsp;<img alt="img not found" src="Image/incorrect.png">
								<br>
								  C.&nbsp;${listcorrectanswer.option3}
								<br>
								 D.&nbsp;${listcorrectanswer.option4} &nbsp;<img alt="img not found" src="Image/correct.png">
								<br>
							</c:if>
							
							<c:if test="${listansweruser.answer == 'C'}">
								<p>${listcorrectanswer.num}. ${listcorrectanswer.question}</p>
								  A.&nbsp;${listcorrectanswer.option1}
								<br>
								  B.&nbsp;${listcorrectanswer.option2}
								<br>
								  C.&nbsp;${listcorrectanswer.option3}&nbsp;<img alt="img not found" src="Image/incorrect.png">
								<br>
								  D.&nbsp;${listcorrectanswer.option4}&nbsp;<img alt="img not found" src="Image/correct.png">
								<br>
							</c:if>
							
							<c:if test="${listansweruser.answer == 'D'}">
								<p>${listcorrectanswer.num}. ${listcorrectanswer.question}</p>
								  A.&nbsp;${listcorrectanswer.option1}
								<br>
								  B.&nbsp;${listcorrectanswer.option2}
								<br>
								  C.&nbsp;${listcorrectanswer.option3}
								<br>
								  D.&nbsp;${listcorrectanswer.option4}&nbsp;<img alt="img not found" src="Image/correct.png">
								<br>
							</c:if>
							
							<c:if test="${listansweruser.answer == 'Không chọn'}"> 
								
								<p>${listcorrectanswer.num}. ${listcorrectanswer.question}</p>
								<p style="color:red"> Không chọn câu trả lời</p>
								  A.&nbsp;${listcorrectanswer.option1}  
								<br>
								  B.&nbsp;${listcorrectanswer.option2}
								<br>
								  C.&nbsp;${listcorrectanswer.option3}
								<br>
								  D.&nbsp;${listcorrectanswer.option4}&nbsp;<img alt="img not found" src="Image/correct.png">
								<br>
							
							
							</c:if>
					
					</c:if>
				</c:if>	
			</c:forEach>
		</c:forEach>
			<br/>
			<a href="ScoreExReading?readexerciseid=<%=request.getAttribute("readexerciseid") %>" class="btn btn-primary" >Again</a>
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