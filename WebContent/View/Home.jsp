<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trang chủ</title>
	 <script src="Template/Frontend/js/jquery-1.js"></script>
  	<link href="Template/Frontend/css/bootstrap.css" rel="stylesheet">
    <link href="Template/Frontend/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="Template/Frontend/css/style.css" rel="stylesheet"> 
    
   
   	<!-- font -->
	<link rel="stylesheet" href="Template/Frontend/font/font-awesome.min.css" />
    

    <script src="Template/Frontend/js/bootstrap.min.js"></script>
    
    <style type="text/css">
    	.d-none{
    		display: none;
    	}
    </style>
    
    <script type="text/javascript">
    	$(function(){
    		$('#chung').click(function(event){
    			$('#myModal1').addClass('d-none');
    		});
    		
    		$('#chung1').click(function(event){
    			$('#myModal').addClass('d-none');
    		});
    	});
    
    </script>
    
    <script type="text/javascript">
    
    //study grammar and vocabulary
    function openModal(){
    	$('#myModal').modal();
    }
   
    
    </script>
    
    <script type="text/javascript">
    
    // do exam( listen & read)
    function openModal1(){
    	$('#myModal1').modal();
    }
    
    
    </script>
    
<!-- script xử lí khung tìm kiếm Ajax -->

	<script type="text/javascript">
	
	function Search(){
		
		var xhttp;
		var search = document.myform.search.value;
		
		if(search != "")
			
			{
			var url="SearchController?search="+search;
			
			if(window.XMLHttpRequest){
				xhttp = new XMLHttpRequest();
			}
			else{
				xhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			xhttp.onreadystatechange = function(){
				if(xhttp.readyState == 4){
					
					var data = xhttp.responseText;
					document.getElementById("resultsearch").innerHTML = data;
				}
			}
			
			xhttp.open("POST",url,true);
			xhttp.send();
			
		}
		
		else{
			document.getElementById("resultsearch").innerHTML ="";
		}
		
	}
	
	
	</script>

</head>
<body>

<!--Header
==========================-->

<jsp:include page="HeaderHome.jsp"/>

<!--/End Headter-->
  
  	
 <!-- Search -->
 
 <div class="container" >
 
 	<div class="row">
 		
 		<br>
 		<div class="span12" >
 			  <div class="navbar  pull-right">
	 			<div id="size">
	 				<form name="myform">
	 					
	 					<input type="text" class="form-control" placeholder="Tìm kiếm ..." style="width: 300px;"  name="search" onkeyup="Search()">	
	 					
	 				</form>
	 			</div>
	 		 </div>
 		</div>
 	</div>
 </div>
 
 <!-- End search -->

  <div class="container" id="resultsearch">

  <!--Carousel
  ==================================================-->
<!-- slide 1 là để cứng. 2 slide còn lại dùng for each. load từ database lên -->
  <div id="myCarousel" class="carousel slide">
    <div class="carousel-inner">

      <div class="active item">
        <div class="container">
          <div class="row">
            
              <div class="span6">

                <div class="carousel-caption">
                      <h1>Đào tạo chất lượng</h1>
                      <p class="lead">Chúng tôi cung cấp cho các bạn những kiến thức tốt nhất.</p>
                      
                    
                         	
                       		<a class="btn btn-large btn-primary" href="RegisterController">Tham gia</a>
                      
                     
                </div>

              </div>

                <div class="span6"> <img src="Template/Frontend/img/slide/aaa.jpg" alt="img not found"/></div>

          </div>
        </div>
       </div>
       
<c:forEach items="${listslidebanner}" var="list">
		      <div class="item">
		       	<div class="container">
		          <div class="row">
		            
		              <div class="span6">
						<div class="carousel-caption">
		                      <h1>${list.slidename}</h1>
		                      <p class="lead">${list.slidecontent}</p>
		                  
                       			<a class="btn btn-large btn-primary" href="RegisterController">Tham gia</a> 
		                </div>
		
		              </div>
		
		                <div class="span6"> <img src="Template/Frontend/img/slide/${list.slideimage}.jpg"></div>
		
		            </div>
		          </div>
				</div>
		
		
	</c:forEach>
		
		
		    </div>
       <!-- Carousel nav -->
      <a class="carousel-control left " href="#myCarousel" data-slide="prev"><i class="icon-chevron-left"></i></a>
      <a class="carousel-control right" href="#myCarousel" data-slide="next"><i class="icon-chevron-right"></i></a>
        <!-- /.Carousel nav -->

  </div>
    <!-- /Carousel -->



<!-- Feature 
  ==============================================-->


  <div class="row feature-box">
      <div class="span12 cnt-title">
       <h1>Cung cấp các giao diện học và thi thân thiện.</h1> 
        <span>--- Học thử, Làm bài tập, Thi thử ---</span>        
      </div>

      <div class="span4">
        <img src="Template/Frontend/img/feature-vocabulary1.jpg">
      
        <h2>Học từ vựng, ngữ pháp</h2>
        <p>
           Các bài hướng dẫn đơn giản, dễ hiểu.
        </p>

       <!--  <a href="#" onclick="openModal()">Chi tiết &rarr;</a>  -->
          <a href="#" id="chung" data-toggle="modal" data-target="#myModal">Chi tiết &rarr;</a>

      </div>

      <div class="span4">
        <img src="Template/Frontend/img/feature-listenandread.jpg" >
        <h2>Bài tập phần nghe, đọc</h2>
        <p>
            Sử dụng các dạng bài tập thường xuyên xuất hiện.
        </p>   
        
       <!--    <a href="#" onclick="openModal1()">Chi tiết &rarr;</a>  -->
          <a href="#" id="chung1" data-toggle="modal" data-target="#myModal1">Chi tiết &rarr;</a>
          
      </div>

      <div class="span4">
        <img src="Template/Frontend/img/feature-lamdethithu.jpg" height="170px" width="270px">
        <h2>Đề thi thử</h2>
        <p>
            Cập nhật, đổi mới liên tục, sát với đề thi thật nhất.
        </p>
          <a href="DisplayListFullExamToeic?pageid=1" >Chi tiết &rarr;</a>
      </div>
  </div>


<!-- /.Feature -->

  <div class="hr-divider"></div>

<!-- Row View -->


    <div class="row">
        <div class="span8"><img src="Template/Frontend/img/background3.png"></div>

        <div class="span4">
         <!--   <img class="hidden-phone" src="Template/Frontend/img/icon4.png" alt="img not found"> -->
          <h1 align="center">Tin cậy - uy tín</h1>
            <p align="justify">Mỗi năm, có hàng nghìn lượt học viên đã tham dự các khóa học tiếng Anh tại CFL và đạt kết quả như ý muốn. 
            Chúng tôi tự hào là một trong những trung tâm ngoại ngữ được yêu thích, là địa chỉ tin cậy của các bạn SV Bách Khoa, 
            Kinh tế, Xây dựng, Ngân hàng... và nhiều người đi làm trên địa bàn Hà Nội.
            </p>
            
        </div>
    </div>

    
</div>


<!-- /.Row View -->



<!--Footer
==========================-->

<jsp:include page="FooterHome.jsp"/>

<!--/.Footer-->

 

<!-- Start Modal -->

<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog">
		
		<!-- Modal content -->
		
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" >&times;</button>
				<h4 class="modal-title">DANH SÁCH LOẠT BÀI HƯỚNG DẪN</h4>
			</div>
			
			<div class ="modal-body">
				
				<div class="media">
					<a class="pull-left"><img src="Template/Frontend/img/tip-listening.png" class="media-object" alt=''/></a>
					<div class="media-body">
						<h3>
							<a href="StudyVocabularyController?pageid=1">Bài hướng dẫn từ vựng</a>
						</h3>
					</div>
			     </div>
			   
			    
			     
			      <div class="media">
					<a class="pull-left"><img src="Template/Frontend/img/tip-reading.png" class="media-object" alt=''/></a>
					<div class="media-body">
						<h3>
							<a href="StudyGrammarController?pageid=1">Bài hướng dẫn ngữ pháp</a>
						</h3>
					</div>
			     </div>
			     
			    </div>
			    
			    <div class="modal-footer" >
			    	<button type="button" class="btn btn-primary" data-dismiss="modal" >Thoát</button>
			    </div>
			    
			 </div>
		</div>
	</div>
 <!-- End Modal -->
			    
			    

<!-- Start Modal1 -->
 

<div class="modal fade" id="myModal1" role="dialog">
	<div class="modal-dialog">
		
		<!-- Modal content -->
		
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">DANH SÁCH LOẠT BÀI HƯỚNG DẪN</h4>
			</div>
			
			<div class ="modal-body">
				
				<div class="media">
					<a class="pull-left"><img src="Template/Frontend/img/exercise-listen.png" class="media-object" alt=''/></a>
					<div class="media-body">
						<h3>
							<a href="StudyExListeningController?pageid=1">Bài tập phần nghe</a>
						</h3>
					</div>
			     </div>
			   
			     <div class="media">
					<a class="pull-left"><img src="Template/Frontend/img/exercise-read.png" class="media-object" alt=''/></a>
					<div class="media-body">
						<h3>
							<a href="StudyExReadingController?pageid=1">Bài tập phần đọc</a>
						</h3>
					</div>
			     </div>
			     
			    </div>
			    
			    <div class="modal-footer">
			    	<button type="button" class="btn btn-primary" data-dismiss="modal">Thoát</button>
			    </div>
			    
			 </div>
		</div>
	</div> 
	<!-- End Modal1 -->






</body>
</html>