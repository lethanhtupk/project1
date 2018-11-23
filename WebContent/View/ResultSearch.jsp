<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ket qua tim kiem</title>
</head>
<body>
 	
 	<div class="row">
 		<div class="span12">
 			<div class="page-header">
 				<h3>
 					Kết quả tìm kiếm:
 					
 				</h3>
 			</div>
 		</div>
 	</div>
 	
 <c:if test="${ketqua != null}">
			${ketqua}
</c:if>
	
<c:if test="${ketqua == null}">
	
 	<div class="row">
	 		<c:forEach items="${listsearch}" var="list">
	 		
		 		<div class="span6">
		 			
		 	<form action="StudyGrammarController?grammarid=${list.grammarguidelineid}" method="POST">
		 			<div class="media">
		 		
		 				<a href="#" class="pull-left"><img src="ImageUpload/${list.grammarimage}" class="media-object" style="height:150px; width:250px"/></a>
		 				<div class="media-body">
		 					<p>
		 						${list.grammarname}
		 					</p>
		 						<input type="submit" value="Chi tiết" name="detailStudyGrammar" class="btn btn-primary"/>
		 				</div>
		 				
		 			</div>
		 	</form>	
		 		
		 		</div>
		 	
		 	</c:forEach>
		 	
		<c:forEach items="${listsearchvocab}" var="list">
 		
	 		<div class="span6">
	 			
	 			<form action="StudyVocabularyController?vocabularyguidelineid=${list.vocabularyguidelineid}" method="POST">
	 				<div class="media">
	 		
	 				<a href="#" class="pull-left"><img src="ImageAudioVocab/${list.vocabularyimage}" class="media-object" style="height:150px; width:250px"/></a>
	 				<div class="media-body">
	 					<p>
	 						${list.vocabularyname}
	 					</p>
	 					<input type="submit" value="Chi tiết" name="detailStudyGrammar" class="btn btn-primary"/>
	 				</div>
	 				
	 				</div>
	 		</form>	
	 		
	 	</div>
	 	
	 </c:forEach>
	</div>
	</c:if>
	
	
	


</body>
</html>