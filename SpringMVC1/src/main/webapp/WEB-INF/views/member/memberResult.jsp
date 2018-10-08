<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 정보</h1>
	
		아이디 : ${memberVO.id }<br>
		이  름 : ${memberVO.name }<br>
		암  호 : ${memberVO.pw }<br>
		나  이 : ${memberVO.age }<br>
		<h3>${result }</h3>
		
	
</body>
</html>