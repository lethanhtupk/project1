<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kết quả bài tập nghe</title>
</head>
<body>
		<c:forEach items="${listcorrectanswerExListening}" var ="listcorrectanswer">
						<c:if test="${listcorrectanswer.correctanswer == 'A'}">
							<c:if test="${kq == 'A'}">
								<p>
									<b>${listcorrectanswer.num}. ${listcorrectanswer.question}</b>
								</p>
								<p>
									<img src= "ImageAudioExListening/${listcorrectanswer.imagename}" alt="img not found" style="width:350px;height:200px;"/>
								</p>
								<p>
									<audio controls>
										<source src="ImageAudioExListening/${listcorrectanswer.audiogg}" type="audio/ogg">
										<source src="ImageAudioExListening/${listcorrectanswer.audiomp3}" type="audio/mpeg">
									</audio>
								</p>
								<p>
								   A.&nbsp;${listcorrectanswer.option1} &nbsp;<img alt="img not found" src="Image/correct.png">
								
								</p>
								<p>
								  B.&nbsp;${listcorrectanswer.option2}
								</p>
								<p>
								  C.&nbsp;${listcorrectanswer.option3}
								</p>
								<p>
								  D.&nbsp;${listcorrectanswer.option4}
								</p>
							</c:if>
								
							<c:if test="${kq == 'B'}">
								<p><b>${listcorrectanswer.num}. ${listcorrectanswer.question}</b>
								</p>
								<p>
									<img src= "ImageAudioExListening/${listcorrectanswer.imagename}" alt="img not found" style="width:350px;height:200px;"/>
								</p>
								<p>
									<audio controls>
										<source src="ImageAudioExListening/${listcorrectanswer.audiogg}" type="audio/ogg">
										<source src="ImageAudioExListening/${listcorrectanswer.audiomp3}" type="audio/mpeg">
									</audio>
								</p>
								<p>
								  A.&nbsp;${listcorrectanswer.option1} &nbsp; <img alt="img not found" src="Image/correct.png">
								</p>
								<p>
								   B.&nbsp;${listcorrectanswer.option2} &nbsp; <img alt="img not found" src="Image/incorrect.png">
								</p>
								<p>
								  C.&nbsp;${listcorrectanswer.option3}
								</p>
								<p>
								   D.&nbsp;${listcorrectanswer.option4}
								</p>
							</c:if>
							
							<c:if test="${kq == 'C'}">
								<p><b>${listcorrectanswer.num}. ${listcorrectanswer.question}</b></p>
								<p>
									<img src= "ImageAudioExListening/${listcorrectanswer.imagename}" alt="img not found" style="width:350px;height:200px;"/>
								</p>
								<p>
									<audio controls>
										<source src="ImageAudioExListening/${listcorrectanswer.audiogg}" type="audio/ogg">
										<source src="ImageAudioExListening/${listcorrectanswer.audiomp3}" type="audio/mpeg">
									</audio>
								</p>
								<p>
								  A.&nbsp;${listcorrectanswer.option1} &nbsp;<img alt="img not found" src="Image/correct.png">
								</p>
								   B.&nbsp;${listcorrectanswer.option2}
								<p>
								  C.&nbsp;${listcorrectanswer.option3}&nbsp; <img alt="img not found" src="Image/incorrect.png">
								</p>
								<p>
								   D.&nbsp;${listcorrectanswer.option4}
								</p>
							</c:if>
							
							<c:if test="${kq == 'D'}">
								<p><b>${listcorrectanswer.num}. ${listcorrectanswer.question}</b></p>
								<p>
									<img src= "ImageAudioExListening/${listcorrectanswer.imagename}" alt="img not found" style="width:350px;height:200px;"/>
								</p>
								<p>
									<audio controls>
										<source src="ImageAudioExListening/${listcorrectanswer.audiogg}" type="audio/ogg">
										<source src="ImageAudioExListening/${listcorrectanswer.audiomp3}" type="audio/mpeg">
									</audio>
								</p>
								<p>  
								 A.&nbsp;${listcorrectanswer.option1} &nbsp; <img alt="img not found" src="Image/correct.png">
								</p>
								<p>
								   B.&nbsp;${listcorrectanswer.option2}
								</p>
								<p>
								  C.&nbsp;${listcorrectanswer.option3}
								</p>
								<p>
								   D.&nbsp;${listcorrectanswer.option4} &nbsp;<img alt="img not found" src="Image/incorrect.png">
								</p>
							</c:if>
					
					</c:if>
					
					<c:if test="${listcorrectanswer.correctanswer == 'B'}">
							<c:if test="${kq == 'A'}">
								<p><b>${listcorrectanswer.num}. ${listcorrectanswer.question}</b></p>
								<p>
									<img src= "ImageAudioExListening/${listcorrectanswer.imagename}" alt="img not found" style="width:350px;height:200px;"/>
								</p>
								<p>
									<audio controls>
										<source src="ImageAudioExListening/${listcorrectanswer.audiogg}" type="audio/ogg">
										<source src="ImageAudioExListening/${listcorrectanswer.audiomp3}" type="audio/mpeg">
									</audio>
								</p>
								<p>
								  A.&nbsp;${listcorrectanswer.option1}&nbsp;<img alt="img not found" src="Image/incorrect.png">
								</p>
								<p>
								   B.&nbsp;${listcorrectanswer.option2}&nbsp;<img alt="img not found" src="Image/correct.png">
								</p>
								<p>
								  C.&nbsp;${listcorrectanswer.option3}
								</p>
								<p>
								   D.&nbsp;${listcorrectanswer.option4}
								</p>
							</c:if>
								
							<c:if test="${kq == 'B'}">
								<p><b>${listcorrectanswer.num}. ${listcorrectanswer.question}</b></p>
								<p>
									<img src= "ImageAudioExListening/${listcorrectanswer.imagename}" alt="img not found" style="width:350px;height:200px;"/>
								</p>
								<p>
									<audio controls>
										<source src="ImageAudioExListening/${listcorrectanswer.audiogg}" type="audio/ogg">
										<source src="ImageAudioExListening/${listcorrectanswer.audiomp3}" type="audio/mpeg">
									</audio>
								</p>
								<p>  
								  A.&nbsp;${listcorrectanswer.option1}
								</p>
								<p>
								 B.&nbsp;${listcorrectanswer.option2}  &nbsp; <img alt="img not found" src="Image/correct.png">
								</p>
								<p>
								  C.&nbsp;${listcorrectanswer.option3}
								</p>
								<p>
								   D.&nbsp;${listcorrectanswer.option4}
								</p>
							</c:if>
							
							<c:if test="${kq == 'C'}">
								<p><b>${listcorrectanswer.num}. ${listcorrectanswer.question}</b></p>
								<p>
									<img src= "ImageAudioExListening/${listcorrectanswer.imagename}" alt="img not found" style="width:350px;height:200px;"/>
								</p>
								<p>
									<audio controls>
										<source src="ImageAudioExListening/${listcorrectanswer.audiogg}" type="audio/ogg">
										<source src="ImageAudioExListening/${listcorrectanswer.audiomp3}" type="audio/mpeg">
									</audio>
								</p>
								<p>  
								  A.&nbsp;${listcorrectanswer.option1}
								</p>
								<p>
								  B.&nbsp;${listcorrectanswer.option2} &nbsp; <img alt="img not found" src="Image/correct.png">
								</p>
								<p>
								 C.&nbsp;${listcorrectanswer.option3} &nbsp; <img alt="img not found" src="Image/incorrect.png">
								</p>
								<p>
								   D.&nbsp;${listcorrectanswer.option4}
								</p>
							</c:if>
							
							<c:if test="${kq == 'D'}">
								<p><b>${listcorrectanswer.num}. ${listcorrectanswer.question}</b></p>
								<p>
									<img src= "ImageAudioExListening/${listcorrectanswer.imagename}" alt="img not found" style="width:350px;height:200px;"/>
								</p>
								<p>
									<audio controls>
										<source src="ImageAudioExListening/${listcorrectanswer.audiogg}" type="audio/ogg">
										<source src="ImageAudioExListening/${listcorrectanswer.audiomp3}" type="audio/mpeg">
									</audio>
								</p>
								 <p> 
								  A.&nbsp;${listcorrectanswer.option1}
								</p>
								<p>
								   B.&nbsp;${listcorrectanswer.option2}&nbsp;<img alt="img not found" src="Image/correct.png">
								</p>
								<p>
								  C.&nbsp;${listcorrectanswer.option3}
								</p>
								<p>
								  D.&nbsp;${listcorrectanswer.option4}&nbsp; <img alt="img not found" src="Image/incorrect.png">
								</p>
							</c:if>	
					</c:if>
					
					
					<c:if test="${listcorrectanswer.correctanswer == 'C'}">
							<c:if test="${kq == 'A'}">
								<p><b>${listcorrectanswer.num}. ${listcorrectanswer.question}</b></p>
								<p>
									<img src= "ImageAudioExListening/${listcorrectanswer.imagename}" alt="img not found" style="width:350px;height:200px;"/>
								</p>
								<p>
									<audio controls>
										<source src="ImageAudioExListening/${listcorrectanswer.audiogg}" type="audio/ogg">
										<source src="ImageAudioExListening/${listcorrectanswer.audiomp3}" type="audio/mpeg">
									</audio>
								</p>
								<p>  
								A.&nbsp;${listcorrectanswer.option1}&nbsp; <img alt="img not found" src="Image/incorrect.png">
								</p>
								<p>
								   B.&nbsp;${listcorrectanswer.option2}
								</p>
								<p>
								C.&nbsp;${listcorrectanswer.option3}  &nbsp; <img alt="img not found" src="Image/correct.png">
								</p>
								<p>
								   D.&nbsp;${listcorrectanswer.option4}
								</p>
							</c:if>
								
							<c:if test="${kq == 'B'}">
								<p><b>${listcorrectanswer.num}. ${listcorrectanswer.question}</b></p>
								<p>
									<img src= "ImageAudioExListening/${listcorrectanswer.imagename}" alt="img not found" style="width:350px;height:200px;"/>
								</p>
								<p>
									<audio controls>
										<source src="ImageAudioExListening/${listcorrectanswer.audiogg}" type="audio/ogg">
										<source src="ImageAudioExListening/${listcorrectanswer.audiomp3}" type="audio/mpeg">
									</audio>
								</p>
								<p>  
								  A.&nbsp;${listcorrectanswer.option1}
								</p>
								<p>
								  B.&nbsp;${listcorrectanswer.option2} &nbsp; <img alt="img not found" src="Image/incorrect.png">
								</p>
								<p>
								  C.&nbsp;${listcorrectanswer.option3}&nbsp; <img alt="img not found" src="Image/correct.png">
								</p>
								<p>
								   D.&nbsp;${listcorrectanswer.option4}
								</p>
							</c:if>
							
							<c:if test="${kq == 'C'}">
								<p><b>${listcorrectanswer.num}. ${listcorrectanswer.question}</b></p>
								<p>
									<img src= "ImageAudioExListening/${listcorrectanswer.imagename}" alt="img not found" style="width:350px;height:200px;"/>
								</p>
								<p>
									<audio controls>
										<source src="ImageAudioExListening/${listcorrectanswer.audiogg}" type="audio/ogg">
										<source src="ImageAudioExListening/${listcorrectanswer.audiomp3}" type="audio/mpeg">
									</audio>
								</p>
								<p> 
								  A.&nbsp;${listcorrectanswer.option1}
								</p>
								<p>
								   B.&nbsp;${listcorrectanswer.option2}
								</p>
								<p>
								 C.&nbsp;${listcorrectanswer.option3} &nbsp; <img alt="img not found" src="Image/correct.png">
								</p>
								<p>
								   D.&nbsp;${listcorrectanswer.option4}
								</p>
							</c:if>
							
							<c:if test="${kq == 'D'}">
								<p><b>${listcorrectanswer.num}. ${listcorrectanswer.question}</b></p>
								<p>
									<img src= "ImageAudioExListening/${listcorrectanswer.imagename}" alt="img not found" style="width:350px;height:200px;"/>
								</p>
								<p>
									<audio controls>
										<source src="ImageAudioExListening/${listcorrectanswer.audiogg}" type="audio/ogg">
										<source src="ImageAudioExListening/${listcorrectanswer.audiomp3}" type="audio/mpeg">
									</audio>
								</p>
								<p>  
								  A.&nbsp;${listcorrectanswer.option1}
								</p>
								<p>
								   B.&nbsp;${listcorrectanswer.option2}
								</p>
								<p>
								C.&nbsp;${listcorrectanswer.option3} &nbsp; <img alt="img not found" src="Image/correct.png">
								</p>
								<p>
								   D.&nbsp;${listcorrectanswer.option4}&nbsp;<img alt="img not found" src="Image/incorrect.png">
								</p>
							</c:if>	
					</c:if>
					
					<c:if test="${listcorrectanswer.correctanswer == 'D'}">
							<c:if test="${kq == 'A'}">
								<p><b>${listcorrectanswer.num}. ${listcorrectanswer.question}</b></p>
								<p>
									<img src= "ImageAudioExListening/${listcorrectanswer.imagename}" alt="img not found" style="width:350px;height:200px;"/>
								</p>
								<p>
									<audio controls>
										<source src="ImageAudioExListening/${listcorrectanswer.audiogg}" type="audio/ogg">
										<source src="ImageAudioExListening/${listcorrectanswer.audiomp3}" type="audio/mpeg">
									</audio>
								</p>
								<p>  
								 A.&nbsp;${listcorrectanswer.option1} &nbsp; <img alt="img not found" src="Image/incorrect.png">
								</p>
								<p>
								  B.&nbsp;${listcorrectanswer.option2}
								</p>
								<p>
								 C.&nbsp;C.&nbsp;${listcorrectanswer.option3}
								</p>
								<p>
								   D.&nbsp;${listcorrectanswer.option4}&nbsp; <img alt="img not found" src="Image/correct.png">
								</p>
							</c:if>
								
							<c:if test="${kq == 'B'}">
								<p><b>${listcorrectanswer.num}. ${listcorrectanswer.question}</b></p>
								<p>
									<img src= "ImageAudioExListening/${listcorrectanswer.imagename}" alt="img not found" style="width:350px;height:200px;"/>
								</p>
								<p>
									<audio controls>
										<source src="ImageAudioExListening/${listcorrectanswer.audiogg}" type="audio/ogg">
										<source src="ImageAudioExListening/${listcorrectanswer.audiomp3}" type="audio/mpeg">
									</audio>
								</p>
								<p>
								  A.&nbsp;${listcorrectanswer.option1}
								</p>
								<p>
								   B.&nbsp;${listcorrectanswer.option2}&nbsp; <img alt="img not found" src="Image/incorrect.png">
								</p>
								<p>
								  C.&nbsp;${listcorrectanswer.option3}
								</p>
								<p>
								   D.&nbsp;${listcorrectanswer.option4}&nbsp; <img alt="img not found" src="Image/correct.png">
								</p>
							</c:if>
							
							<c:if test="${kq == 'C'}">
								<p><b>${listcorrectanswer.num}. ${listcorrectanswer.question}</b></p>
								<p>
									<img src= "ImageAudioExListening/${listcorrectanswer.imagename}" alt="img not found" style="width:350px;height:200px;"/>
								</p>
								<p>
									<audio controls>
										<source src="ImageAudioExListening/${listcorrectanswer.audiogg}" type="audio/ogg">
										<source src="ImageAudioExListening/${listcorrectanswer.audiomp3}" type="audio/mpeg">
									</audio>
								</p>
								<p> 
								  A.&nbsp;${listcorrectanswer.option1}
								</p>
								<p>
								   B.&nbsp;${listcorrectanswer.option2}
								</p>
								<p>
								  C.&nbsp;${listcorrectanswer.option3}&nbsp; <img alt="img not found" src="Image/incorrect.png">
								</p>
								<p>
								   D.&nbsp;${listcorrectanswer.option4}&nbsp;<img alt="img not found" src="Image/correct.png">
								</p>
							</c:if>
							
							<c:if test="${kq == 'D'}">
								<p><b>${listcorrectanswer.num}. ${listcorrectanswer.question}</b></p>
								<p>
									<img src= "ImageAudioExListening/${listcorrectanswer.imagename}" alt="img not found" style="width:350px;height:200px;"/>
								</p>
								<p>
									<audio controls>
										<source src="ImageAudioExListening/${listcorrectanswer.audiogg}" type="audio/ogg">
										<source src="ImageAudioExListening/${listcorrectanswer.audiomp3}" type="audio/mpeg">
									</audio>
								</p>
								<p>  
								 A.&nbsp;${listcorrectanswer.option1}
								</p>
								<p>
								   B.&nbsp;${listcorrectanswer.option2}
								</p>
								<p>
								  C.&nbsp;${listcorrectanswer.option3}
								</p>
								<p>
								   D.&nbsp;${listcorrectanswer.option4}&nbsp; <img alt="img not found" src="Image/correct.png">
								</p>
							</c:if>	
					</c:if>
		</c:forEach>
</body>
</html>