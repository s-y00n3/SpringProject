<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
	<h1>로그인 페이지</h1>
	<form action="loginPost" method="post">
		아이디 : <input type="text" name="id"  value="${loginIdCookie}"><br>
		비밀번호 :<input type="password" name="pw"><br>
		아이디 기억 <input type="checkbox" name="useCookie"
		<c:if test="${loginCookie =='ok' }">
		 checked="checked"
		</c:if>
		><br>
		<input type="submit" value="로그인">
	</form>
	</div>
</body>
</html>