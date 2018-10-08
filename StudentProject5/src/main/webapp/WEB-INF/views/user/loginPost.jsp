<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="loginPost" method="post">
		로그인 한 아이디 : ${login.id }<br>
		로그인 한 비밀번호 :${login.pw }<br>
	</form>
	<a href="otherPage">다른 페이지로</a>
</body>
</html>