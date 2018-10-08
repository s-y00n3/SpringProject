<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	function modify(){
		document.form1.submit();
	}
</script>
<link rel = "stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<div align="center">

<jsp:include page="../header.jsp"></jsp:include>
<form name="form1" action="modify" method="post" enctype="multipart/form-data">

<h1>학생 정보</h1>
아이디 : <input type="text" name="id" value="${student.id }" readonly> <br>

번 호 : <input type="text" name="no" value="${student.no }" > <br>

이 름 : <input type="text" name="name" value="${student.name }"> <br>

비밀번호 : <input type="text" name="password" value="${student.password }"> <br>

이메일 : <input type="text" name="email" value="${student.email}"><br>

등록일 : <input type="text"
value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${student.regdate }"/>" 
readonly>
<br>
<c:if test="${student.savedName != null}">
사 진 : <img width = 300 src="display?fileName=${student.savedName }"><br>${originalName }<br>
</c:if>
<input type="file" name="file"><br>
<a href="javascript:modify()">수 정</a>&nbsp;&nbsp;<a href="list">학생 리스트</a> &nbsp;&nbsp;
<a href="imageList">학생사진리스트</a>
</form>
</div>