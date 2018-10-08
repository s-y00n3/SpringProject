<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div align="center">
<link rel = "stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<div>
	<c:choose>
		<c:when test="${login != null }">
		${login.name }(아이디:${login.id })님 반갑습니다.^^ 
		<a href="../user/logout">■ 로그아웃 ■</a>
		</c:when>
		<c:otherwise>
		<a href="../user/login">■ 로그인하기 ■</a>
		</c:otherwise>
	</c:choose>
</div>

</div>