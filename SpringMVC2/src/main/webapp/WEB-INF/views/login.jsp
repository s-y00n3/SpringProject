
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
</head>
<body>
<form action="loginPost" method="post">
	아이디 : <input type="text" name="id" value="${loginIdCookie }"><br>
	비밀번호 : <input type="password" name="pw"><br>
	아이디 기억 <input type="checkbox" name="useCookie"
	<c:if test="${loginCookie == 'ok' }">
		checked="checked"	
	</c:if>
	>
	<input type="submit" value="로그인">
</form>
</body>
</html>

