<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div align="center">
<link rel = "stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

<jsp:include page="../header.jsp"></jsp:include>


<h1>학생 리스트</h1>
<table border="1">
	<tr>
	<th>번호</th>
	<th>아이디</th>
	<th>이름</th>
	</tr>
	
	<c:forEach items="${students }" var="student">
	<tr>
		<td>${student.no }</td>
		<td>${student.id }</td>s
		<td><a href="read?id=${student.id }">${student.name }</a></td>		
	</tr>
	</c:forEach>
</table>
<br>
<a href="register">학생 등록</a>&nbsp;&nbsp;
<a href="imageList">학생사진리스트</a>
</div>