<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Làm bài thi Toeic hoàn chỉnh</title>

<link href="Template/Frontend/css/bootstrap.css" rel="stylesheet">  
<link href="Template/Frontend/css/bootstrap-responsive.css" rel="stylesheet">
<link href="Template/Frontend/css/style.css" rel="stylesheet">


<!-- font -->
<link rel="stylesheet" href="Template/Frontend/font/font-awesome.min.css" />

<script src="Template/Frontend/js/jquery-1.js"></script>
<script src="Template/Frontend/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css" href="Jqueryphantrang/css/style.css" media="screen"/>

	<style>
            .demo
            {
                width:580px;
                padding:10px;
                margin:10px auto;
                border: 1px solid #fff;
                background-color:#f7f7f7;
            }        
			.pagedemo{
				border: 1px solid #CCC;
				width:310px;
				margin:2px;
                padding:50px 10px;
                text-align:center;
				background-color:white;	
			}
			.khoangcach{
				padding-top:100px;
			}
		
    </style>
<!-- 
// boder cho label
label {display: block; padding: 5px; position: relative; padding-left: 20px;  float:left;}
		label input {display: none;}
		label span {border: 1px solid #ccc; width: 15px; height: 15px; position: absolute; overflow: hidden; line-height: 1; 
					text-align: center; border-radius: 100%; font-size: 10pt; left: 0; top: 50%; margin-top: -1.5px;}
		input:checked + span {background: #ccf; border-color: #ccf;}	

 -->

<!-- countdown -->


<script src="Countdown/countdown.js"></script>

		<script type="text/javascript">
        
        		function auto_submit()
        		{
        			document.form.submit();
        		}
        			
        		function auto_submit1()
        		{
        			setTimeout("auto_submit()",432000000);
        		}
        
        
        </script>

</head>
<body onLoad="auto_submit1();">


<!--Header
==========================-->

<jsp:include page="HeaderHome.jsp"/>

<!--/End Headter-->
 
 




<div class="container">

 
  	<div class="row">
	  <div class="span12">
	  <h2>Làm bài thi Toeic hoàn chỉnh</h2>
				<!-- Begin countdown -->
							
				<script>
					function doneHandler(result) {
				
					var year = result.getFullYear();
					var month = result.getMonth() + 1; // bump by 1 for humans
					var day = result.getDate();
					var h = result.getHours();
					var m = result.getMinutes();
					var s = result.getSeconds();
					
					var UTC = result.toString();
					
					var output = UTC + "\n";
					output += "year: " + year + "\n";
					output += "month: " + month + "\n";
					output += "day: " + day + "\n";
					output += "h: " + h + "\n";
					output += "m: " + m + "\n";
					output += "s: " + s + "\n";
					
				}
				
				var myCountdownTest = new Countdown({
												 	time: 7200,
													width	: 300, 
													height	: 50,
													onComplete : doneHandler
													});
				
				
				
				</script>
				<!-- END COUNTDOWN -->

	  </div>
	 </div>

	

	<div class="row">
		<div class="span12">

		
			<ul class="thumbnails">
			
				<li class="span8">
					<div class="thumbnail" id="paginationdemo" >
						<div class="reading_description" style="overflow: auto; height: 400px" >
							<div id="p1" class=" _current" style="">
								<h1 class="khoangcach">Nhấn page 2 để xem đề thi
								</h1>
							</div>
							
						<c:forEach items="${listExaminationQuestion}" var="list">
						
						<!--  nếu có câu hỏi, có file mp3 và file ogg thì show phần listen part1 -->
						
							<c:if test="${(list.imagequestion!='') && (list.audiogg!='') && (list.audiomp3!='')}">
								<div id="p${list.num+1}" style="display:none;">
									<p> Câu ${list.num}:</p>
									<p>${list.question}</p>
									<img src ="ImageAudioExam/${list.imagequestion}" alt="" style="width:350px;height:250px;"/>
									<br>
									<br> 
										<p>
										<audio controls>
											<source src ="ImageAudioExam/${list.audiogg}" type="audio/ogg">
											<source src ="ImageAudioExam/${list.audiomp3}" type="audio/mpeg">
										</audio>
										</p>
								
									<p>${list.option1}</p>
									<p>${list.option2}</p>
									<p>${list.option3}</p>
									<p>${list.option4}</p>
								</div>
							</c:if>
							
							<!-- ko có hình ảnh, file nghe  thì show  phần đọc  -->
							
							<c:if test="${(list.imagequestion=='') && (list.audiogg=='') && (list.audiomp3=='')}">
								<div id="p${list.num+1}" style="display:none;">
									<p> Câu ${list.num}:</p>
			
									<p align="justify">
									
									<!-- dùn c:set và c:out để chuyển kí tự /n thành xuống dòng khi load từ database lên -->
										<c:set var="ketqua1" value="${fn:replace(list.paragraph,kitutrongdatabase1,kitutronghtml1)}"/> 
										<c:out value="${ketqua1}" escapeXml="false"/>
									
									</p>
									
									<br> 
									<p>${list.question}</p>
									
									<p>${list.option1}</p>
									<p>${list.option2}</p>
									<p>${list.option3}</p>
									<p>${list.option4}</p>
									
								</div>
							
							</c:if>
						
						
						
						
						</c:forEach>
							
							
						
						</div>
					</div>
					<div id="demo5"> </div>
				</li>
				
			
			
			
			<li class="span4">
			
				<form  name="form" action="DoingFullExamToeic2?examinationid=${examinationid}&memberid=${memberid}" method="POST"> 
					<div class="thumbnail">
						<div class="reading_description " style="overflow: auto; height: 400px" >
						
						<c:forEach items="${listExaminationQuestion}" var="list">
						 &nbsp; &nbsp; &nbsp;
							 <div class ="span1" style="font-weight: bold;">
							 ${list.num }
							 </div >
							 
					<!-- 	<label><input type="radio" name="select" /><span>A</span></label>
							  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;
							 <label><input type="radio" name="select" /><span>B</span></label>
							  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
							 <label><input type="radio" name="select" /><span>C</span></label>
							  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
							  <label><input type="radio" name="select" /><span>D</span></label>
							  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
					 -->
					 
					 <input type="radio" name="ans[${list.num}]" value="A">  A
					  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;	  
					<input type="radio" name="ans[${list.num}]" value="B"> 	B
					 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;	  
					<input type="radio" name="ans[${list.num}]" value="C"> 	C
					&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;	  
					<input type="radio" name="ans[${list.num}]" value="D"> 	D
							<br>
							<br>
						
						
						
						</c:forEach>
					
						
						</div>
						
					</div>
					<br>
					
			 		<input type="submit" class="btn btn-primary" value="Score"/>
				</form>
			</li>
				
		
		
			</ul>
		</div>
	</div>

</div>


<!--Footer
==========================-->


<div id ="Pagination1">
<jsp:include page="FooterHome.jsp"/>
</div>

<!--/.Footer-->

		<script type="text/javascript" src="Jqueryphantrang/jquery-1.3.2.js"></script>
		<script src="Jqueryphantrang/jquery.paginate.js" type="text/javascript"></script>
		
		<script type="text/javascript">
		
			$(function() 
			{
				$("#demo5").paginate({
					count 		: 25, // full toeic thi để 201
					start 		: 1,
					display     : 5,
					border					: true,
					border_color			: '#fff',
					text_color  			: '#fff',
					background_color    	: 'black',	
					border_hover_color		: '#ccc',
					text_hover_color  		: '#000',
					background_hover_color	: '#fff', 
					images					: false,
					mouse					: 'press',
					onChange     			: function(page){
												$('._current','#paginationdemo').removeClass('_current').hide();
												$('#p'+page).addClass('_current').show();
											  }
				});
			});
		</script>


</body>
</html>