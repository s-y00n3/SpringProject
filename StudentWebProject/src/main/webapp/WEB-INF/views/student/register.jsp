<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script>
	function register(){
		document.form1.submit();
	}
</script>
<form name = "form1" action ="register" method="post" enctype="multipart/form-data">
<div align="center">
<h1>학생 등록</h1>
번  호 : <input type = "text" name = "no"><br>
아이디 : <input type = "text" name = "id"><br>
이  름 : <input type = "text" name = "name"><br>
비밀번호 : <input type = "password" name = "password"><br>
이메일 : <input type = "email" name = "email"><br>
사  진 : <input type = "file" name = "file">
<br>
<a href ="javascript:register()">등록</a>&nbsp;&nbsp;<a href = "list">학생리스트</a>&nbsp;&nbsp;<a href = "imageList">학생사진리스트</a>
</div>
</form>