<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form name = "form1" action ="register" method="post">
<div align="center">
<h1>학생 정보</h1>
번  호 : ${studnet.no}<br>
아이디 : ${student.id}<br>
이  름 : ${student.name }<br>
비밀번호 : ${student.password }<br>
이메일 : ${student.email }<br>
등록일 : <fmt:formatDate value="${student.regdate }" pattern="yyyy-MM-dd HH:mm"/>
<br>
<br>
<c:if test="${student.savedName != null }">
사  진 : <img alt="학생사진" src="display?fileName=${student.savedName }" width="200" height="200">
<br>${originalName }<br>
</c:if>
<a href ="modify?id=${student.id }">수정</a>&nbsp;&nbsp;
<a href = "remove?id=${student.id }">삭제</a>&nbsp;&nbsp;
<a href = "list">학생리스트</a>&nbsp;&nbsp;
<a href = "imageList">학생사진리스트</a>
</div>
</form>