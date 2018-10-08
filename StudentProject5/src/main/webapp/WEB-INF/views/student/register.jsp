<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script>
	function register(){
		document.form1.submit();
	}
</script>
<link rel = "stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<div align="center">

<jsp:include page="../header.jsp"></jsp:include>
<form name="form1" action="register" method="post"
enctype="multipart/form-data">

<h1>학생 등록</h1>
아이디 : <input type="text" name="id"> <br>

번 호 : <input type="text" name="no"> <br>

이 름 : <input type="text" name="name"> <br>

비밀번호 : <input type="text" name="password"> <br>

이메일 : <input type="text" name="email"><br>

사 진 : <input type="file" name="file">
<br>

<a href="javascript:register()">등 록</a>&nbsp;&nbsp;<a href="list">학생 리스트</a>&nbsp;&nbsp; 
<a href="imageList">학생사진리스트</a>
</div>
</form>

