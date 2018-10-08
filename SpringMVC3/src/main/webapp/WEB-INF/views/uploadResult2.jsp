<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
	<h1>파일 업로드 결과 화면</h1>
	<c:forEach var = "file" items="${fileResults }">
	파일명 : <a href = "displayFile?fileName=${file.savedName }">${file.originalName }</a><br>
	</c:forEach>
	</div>
</body>
</html>