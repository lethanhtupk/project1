<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Header Page</title>
</head>
<body>
			<div id="navbar" class="navbar navbar-default ace-save-state">
			<div class="navbar-container ace-save-state" id="navbar-container">
			
				<div class="navbar-header pull-left">
					<a href="Adminforward" class="navbar-brand">
						<small>
							<i class="fa fa-leaf"></i>
							Admin Page
						</small>
					</a>
				</div>
				
				<div class="navbar-buttons navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li class="light-blue dropdown-modal">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="Template/Backend/images/avatars/user.jpg" alt="Jason's Photo" />
								<span class="user-info">
									<small>Welcome</small>
									<!--  
									<%=session.getAttribute("adminname") %>
									-->
									
									<!-- hoặc có thể dùng cách khác để get name admin -->
									
									 ${adminname}
								</span>

								<i class="ace-icon fa fa-caret-down"></i>
							</a>

							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								
										<!-- 
										<li>
											<a href="#">
												<i class="ace-icon fa fa-cog"></i>
												Settings
											</a>
										</li>
		
										<li>
											<a href="profile.html">
												<i class="ace-icon fa fa-user"></i>
												Profile
											</a>
										</li>
		
										<li class="divider"></li>
										
										 -->

								<li>
									<a href="LogoutController">
										<i class="ace-icon fa fa-power-off"></i>
										Thoát
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
				
			</div>
		</div>
</body>
</html>