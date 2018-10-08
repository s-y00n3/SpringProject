<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "student.domain.StudentVO, java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div align="center">
<div>
	<c:choose>
		<c:when  test="${login != null }">
			${login.name }(아이디 : ${login.id })님 반갑습니다!
			<a href = "../user/logout"><br>■ 로그아웃 ■</a>
		</c:when>
		<c:otherwise>
			<a href="../user/login">■ 로그인 ■</a>
		</c:otherwise>
	</c:choose>
</div>
<h1>우리반 학생 사진 리스트</h1>

<%
	StudentVO student = null;
	ArrayList<StudentVO> students
	= (ArrayList<StudentVO>)request.getAttribute("students");
	
	int size = students.size();
	int count = -1;
%>
<%= size %>
<table border = "1">
<% outer:
	for(int i = 0; i < 4; i++){%>
	<tr>
	<%
		for(int j = 0; j < 5; j++){
			count++;
			if(count >= size)
				break outer;
			student = students.get(count);
	%>
		<td>
	<% if(student != null && student.getSavedName() != null){%>
			<img width =  "150" alt ="학생사진"
			src = "display?fileName=<%=student.getSavedName() %>"><br>
		<%}%>
		<a href = "read?id=<%=student.getId() %>"><%=student.getName() %></a>
		</td>
		<%} %>
	</tr>
	<%} %>
</table>

<a href="register">학생 등록</a>
</div>