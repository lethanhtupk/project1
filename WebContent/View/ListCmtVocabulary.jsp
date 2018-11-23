<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:forEach items="${listcommentvocab}" var="list">
			<div>
				<!--  check loi <%= request.getAttribute("errorInsertComment") %> -->
				<h4 style="color:red">${list.membername}</h4>
				<textarea disabled class="input-xxlarge showtext" rows="2" name="cmtvocabularycontent" >${list.cmtvocabularycontent}</textarea>
			
			</div>	
	</c:forEach>
</body>
</html>