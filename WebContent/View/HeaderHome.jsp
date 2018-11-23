<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<link href="Template/Frontend/css/bootstrap.css" rel="stylesheet">
    <link href="Template/Frontend/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="Template/Frontend/css/style.css" rel="stylesheet"> 
    
    <link href="Template/Frontend/font/font.css" rel="stylesheet">
     
    <script src="Template/Frontend/js/jquery-1.js"></script>
    <script src="Template/Frontend/js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
    
    function openModal(){
    	$('#myModal').modal();
    }
    
    </script>

</head>
<body>

<!--HEADER ROW-->
  <div id="header-row">
    <div class="container">
      <div class="row">
              <!--LOGO-->
              <div class="span3"><a class="brand" href="Homeforward"><img src="Template/Frontend/img/logotest.png" /></a></div>
              <!-- /LOGO -->

            <!-- MAIN NAVIGATION -->  
              <div class="span9">
                <div class="navbar  pull-right">
                  <div class="navbar-inner">
                    <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></a>
                    <div class="nav-collapse collapse navbar-responsive-collapse">
                    <ul class="nav">
                        
                        
                       <c:if test="${fullname==null}">
                         	<li><a href="LoginController">Đăng nhập</a></li>
	                        <li><a href="RegisterController">Đăng ký</a></li>
                       
                        </c:if>      
                       
                 
                        <c:if test="${fullname!=null}">
                        
	                         <li><a>Xin chào: ${fullname}</a></li>
	                         <li></li>
	                         <li><a href="LogoutController">Thoát</a></li>
                        
                        </c:if>         
                                
                                  
                            
                                  
                      </ul>            
                                   
                 
                    
                  </div>

                  </div>
                </div>
              </div>
            <!-- MAIN NAVIGATION -->  
      </div>
    </div>
  </div>
  <!-- /HEADER ROW -->
  
</body>
</html>