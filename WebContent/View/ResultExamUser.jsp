<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kết quả thi</title>
	
	<link href="Template/Frontend/css/bootstrap.css" rel="stylesheet">
    <link href="Template/Frontend/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="Template/Frontend/css/style.css" rel="stylesheet"> 
    
   
   	<!-- font -->
	<link rel="stylesheet" href="Template/Frontend/font/font-awesome.min.css" />
    
    <script src="Template/Frontend/js/jquery-1.js"></script>
    <script src="Template/Frontend/js/bootstrap.min.js"></script>
    
   <style>
		mark { 
		    background-color: yellow;
		    color: black;
		}
	</style>
</head>
<body>
	<!--HEADER-->
	  	<jsp:include page="HeaderHome.jsp"></jsp:include>
	 <!-- /HEADER-->
	 
		<div class="container">
			  <!--PAGE TITLE-->
			<br/>
			
		
		  <!-- /. PAGE TITLE-->
		  
			<div class="row">	
						<div class="span9">	
							<h3>
							<mark>	Danh sách đáp án đề thi </mark>
							</h3>	
						</div>
						<div class="span3">	
							<h3>
							<mark>	Đáp án người dùng </mark>
							</h3>	
						</div>
						<div class="span9">			
								<div class="thumbnail">
									<div class="reading_description" style="overflow: auto; height: 400px" >
										<c:forEach items= "${listcorrectanswer}" var= "listcorrectanswer">
										
										<!-- part1 phần listening. có image và file audio -->
										
											<c:if test="${(listcorrectanswer.imagequestion!='')&&(listcorrectanswer.audiogg!='')&&(listcorrectanswer.audiomp3!='')}">
											
											
													<img src= "ImageAudioExam/${listcorrectanswer.imagequestion}" alt="not found image" style="width:250px;height:150px;"/>
													<br/>
													<br/>
													<p>
														<audio controls>
																<source src="ImageAudioExam/${listcorrectanswer.audiogg}" type="audio/ogg">
																<source src="ImageAudioExam/${listcorrectanswer.audiomp3}" type="audio/mpeg">
														</audio>
													</p>
													
													<c:if test="${listcorrectanswer.correctanswer == 'A'}">
													
														<p> <b>Câu ${listcorrectanswer.num}:</b> <span style="color:red">Đáp án A đúng</span> </p> 
	
													</c:if>
													
													<c:if test="${listcorrectanswer.correctanswer == 'B'}">
														
															
															<p> <b>Câu ${listcorrectanswer.num}:</b> <span style="color:red">Đáp án B đúng</span> </p> 
															
															
													</c:if>
													<c:if test="${listcorrectanswer.correctanswer == 'C'}">
														
															
															<p> <b>Câu ${listcorrectanswer.num}:</b> <span style="color:red">Đáp án C đúng</span> </p> 
															
													
													</c:if>
													<c:if test="${listcorrectanswer.correctanswer == 'D'}">
														
															
															<p> <b>Câu ${listcorrectanswer.num}:</b> <span style="color:red">Đáp án D đúng</span> </p> 
													
													</c:if>
															
											</c:if>
											
									<!-- part2 phần listening, chỉ có file audio, không có file image -->
											
											<c:if test="${(listcorrectanswer.imagequestion=='')&&(listcorrectanswer.audiogg!='')&&(listcorrectanswer.audiomp3!='')}">
													
													<br/>
													<br/>
													<p>
														<audio controls>
																<source src="ImageAudioExam/${listcorrectanswer.audiogg}" type="audio/ogg">
																<source src="ImageAudioExam/${listcorrectanswer.audiomp3}" type="audio/mpeg">
														</audio>
													</p>
													
													<c:if test="${listcorrectanswer.correctanswer == 'A'}">
													
														 <p style="color:red">Câu ${listcorrectanswer.num}. Đáp án A đúng</p>
													</c:if>
													
													<c:if test="${listcorrectanswer.correctanswer == 'B'}">
														
															
														<p style="color:red">Câu ${listcorrectanswer.num}. Đáp án B đúng</p>
															
															
													</c:if>
													<c:if test="${listcorrectanswer.correctanswer == 'C'}">
														
															
														<p style="color:red">Câu ${listcorrectanswer.num}. Đáp án C đúng</p>	
															
													
													</c:if>
													<c:if test="${listcorrectanswer.correctanswer == 'D'}">
														
															
														<p style="color:red">Câu ${listcorrectanswer.num}. Đáp án D đúng</p>
													
													</c:if>
															
											</c:if>
									
									<!-- part 1 phần đọc, ko có audio , image , khi load paragraph thì dùng c:set và c:out để sửa-->
									
											<c:if test="${(listcorrectanswer.imagequestion=='')&&(listcorrectanswer.audiogg=='')&&(listcorrectanswer.audiomp3=='')}">
													
													<c:if test="${listcorrectanswer.correctanswer == 'A'}">
															<p  align="justify" style="font-weight:bold"> Câu ${listcorrectanswer.num}:</p>
															<p>
																<c:set var ="kq" value="${fn:replace(listcorrectanswer.paragraph,kitutrongdatabase,kitutronghtml)}" />
																<c:out value= "${kq}" escapeXml="false"/>
															</p>
														<p>${listcorrectanswer.question}</p>
														 ${listcorrectanswer.option1}  <img alt="" src="Image/correct.png" alt="image not found">
														<br>
														<br>
														  ${listcorrectanswer.option2}
														<br>
														<br>
														  ${listcorrectanswer.option3}
														<br>
														<br>
														  ${listcorrectanswer.option4}
														<br>
														<br>
												
												
										
													</c:if>
										
													<c:if test="${listcorrectanswer.correctanswer == 'B'}">
														
															
																
																
																<p  align="justify" style="font-weight:bold"> Câu ${listcorrectanswer.num}:</p>
																<p>
																	<c:set var ="kq" value="${fn:replace(listcorrectanswer.paragraph,kitutrongdatabase,kitutronghtml)}" />
																	<c:out value= "${kq}" escapeXml="false"/>
																</p>
																<p>${listcorrectanswer.question}</p>
																  ${listcorrectanswer.option1}
																<br>
																<br>
																  ${listcorrectanswer.option2} <img alt="" src="Image/correct.png" alt="image not found">
																<br>
																<br>
																  ${listcorrectanswer.option3}
																<br>
																<br>
																  ${listcorrectanswer.option4}
																<br>
																<br>
															
															
													</c:if>
													<c:if test="${listcorrectanswer.correctanswer == 'C'}">
														
															
															
																
																<p  align="justify" style="font-weight:bold"> Câu ${listcorrectanswer.num}:</p>
																<p>
																	<c:set var ="kq" value="${fn:replace(listcorrectanswer.paragraph,kitutrongdatabase,kitutronghtml)}" />
																	<c:out value= "${kq}" escapeXml="false"/>
																</p>
																<p>${listcorrectanswer.question}</p>
																  ${listcorrectanswer.option1}
																<br>
																<br>
																  ${listcorrectanswer.option2}
																<br>
																<br>
																  ${listcorrectanswer.option3} <img alt="" src="Image/correct.png" alt="image not found">
																<br>
																<br>
																  ${listcorrectanswer.option4}
																<br>
																<br>
															
															
													
													</c:if>
													<c:if test="${listcorrectanswer.correctanswer == 'D'}">
														
													
															
															
																<p  align="justify" style="font-weight:bold"> Câu ${listcorrectanswer.num}:</p>
																<p>
																	<c:set var ="kq" value="${fn:replace(listcorrectanswer.paragraph,kitutrongdatabase,kitutronghtml)}" />
																	<c:out value= "${kq}" escapeXml="false"/>
																</p>
																<p>${listcorrectanswer.question}</p>
																  ${listcorrectanswer.option1}
																<br>
																<br>
																  ${listcorrectanswer.option2}
																<br>
																<br>
																  ${listcorrectanswer.option3}
																<br>
																<br>
																  ${listcorrectanswer.option4} <img alt="" src="Image/correct.png" alt="image not found">
																<br>
																<br>
															
															
													
													</c:if>
															
											</c:if>
										
								
									   </c:forEach>
									
									</div>
								</div>						
						</div>	
						
						
						<div class="span3">			
								<div class="thumbnail">
									<div class="reading_description" style="overflow: auto; height: 400px" >
											<c:forEach items="${listansweruser}" var="list">
													<div class="span1">
														${list.num}. 
													</div>
													
																
													${list.answer}
													
													<br/>
											</c:forEach>
									</div>
								</div>
								<br/>
								<input type="button" class="btn btn-primary" value="Xem kết quả thi" data-toggle="modal" data-target="#myModal"/>
								<a href="DisplayListFullExamToeic?pageid=1" role="button" class="btn btn-primary" >Làm lại</a>
								
								
						</div>
						
						
									
			</div>
			
			
			
			
		</div>
	 
	 <div>
	 	<jsp:include page="FooterHome.jsp"></jsp:include>
	 </div>
	 
	   <!-- start Modal -->
		  <div class="modal fade" id="myModal" role="dialog">
		    <div class="modal-dialog">
		    
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">Kết quả thi của người dùng</h4>
		        </div>
		        <div class="modal-body">
	         			
	         		<c:forEach items="${ketquathi}" var = "list">
						<div class="media">
							
							<div class="media-body">
								<h4>
									Số câu đúng: ${list.correctanswernum}
									<br/>
										- Số câu đúng phần nghe: ${list.correctanswerlisten}
									<br/>
										- Số câu đúng phần đọc: ${list.correctanswerread}
								</h4> 					
							</div>
						</div>
					
			          	<div class="media">
							
							<div class="media-body">
								<h4>
									Số câu sai: ${list.incorrectanswernum}
								</h4> 					
							</div>
						</div>
						
					
					</c:forEach>
		        </div>
		        <div class="modal-footer">
		          <button type="button" class="btn btn-default" data-dismiss="modal">Thoát</button>
		        </div>
		      </div>
		      
		    </div>
		  </div>
		  <!-- end modal -->
	 
	 
	  	 
</body>
</html>