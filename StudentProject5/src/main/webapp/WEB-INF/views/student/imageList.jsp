<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.student.domain.StudentVO, java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div align="center">

<jsp:include page="../header.jsp"></jsp:include>
<link rel = "stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<h1>3학년 5반 학생 사진</h1>
<%
StudentVO student = null; 
ArrayList<StudentVO> students = (ArrayList<StudentVO>)request.getAttribute("students");
int size = 0;
if(students != null)
	size = students.size();
int count = -1;
%>
<%=size %>
<table border="1">	
<%  outer: 
	for(int i = 0; i < 4; i++){%>	
	<tr>
		<%for(int j = 0; j < 5; j++){ %>
			<% count++;
			if(count >= size)
				break outer;
			
			student = students.get(count);		
			%>
			<td>
			<% if (student != null && student.getSavedName() != null) {%>
				<img width = 150 src="display?fileName=<%=student.getSavedName() %>"><br>
							
			<%}%>
				<a href="read?id=<%=student.getId() %>"><%=student.getName()%></a>
			</td>
		<%} %>
	</tr>
	<%} %>
		
		
	
	
	
</table>
<br>
<a href="register">학생 등록</a>&nbsp;&nbsp; <a href="list">학생 리스트</a>
</div>