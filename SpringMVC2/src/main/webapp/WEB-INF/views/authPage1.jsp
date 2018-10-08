<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>로그인한 사용자 페이지1</h1>
	로그인한 아이디: ${login.id }<br>
 	로그인한 비밀번호 : ${login.pw }<br>
 	<c:if test="${login.id != null }">
		<a href = "logout2">로그 아웃</a>
	</c:if>
</body>
</html>