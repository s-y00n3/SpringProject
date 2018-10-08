<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src="https://code.jquery.com/jquery-2.2.4.js"></script>
<script>
	$(document).ready(function() {

		$("#login").on("click", function() {

			var id = $("#id").val();
			var pw = $("#pw").val();
			var useCookie = $("#useCookie").is(":checked");
			alert(useCookie)
			$.ajax({
				type : 'post',
				url : '/user/loginAjax',
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				dataType : 'text',
				data : JSON.stringify({
					id : id,
					pw : pw,
					useCookie : useCookie
				}),
				success : function(result) {
					if (result == "FAIL")
						alert("아이디나 패스워드가 트립니다.");
					window.location.reload(true);
				}
			})
		});

		$("#logout").on("click", function() {

			$.ajax({
				type : 'post',
				url : '/user/logoutAjax',
				success : function(result) {
					alert("로그아웃 합니다.");
					window.location.href="imageList";
				}
			})
		});

	});
</script>
<%
	// 쿠키값 가져오기
	Cookie[] cookies = request.getCookies();
	String loginCookie = null;
	String loginIdCookie = "";
	boolean checked = false;
	if (cookies != null) {

		for (int i = 0; i < cookies.length; i++) {

			if(cookies[i].getName().equals("loginCookie")){
				loginCookie = cookies[i].getValue();
				if(loginCookie.equals("ok")){
					checked = true;
					break;
				}
			}
		}
		for (int j = 0; j < cookies.length; j++) {
			if(cookies[j].getName().equals("loginIdCookie")){				
				loginIdCookie = cookies[j].getValue();				
				break;
			}
		}	
	}
%>
<c:choose>
	<c:when test="${login != null }">
		${login.name }[아이디:${login.id }]님 반갑습니다.^^
		<input id="logout" type="button" value="로그아웃">
	</c:when>
	<c:otherwise>
		<div align="center">
			아이디 : <input type="text" name="id" id="id"  value="<%=loginIdCookie%>"> 비밀번호 : <input
				type="password" name="pw" id="pw"> 아이디 기억 <input
				type="checkbox" name="useCookie" id="useCookie"
				<c:if test='<%=checked %>'> 
			 checked="checked"
			</c:if>>
			<input id="login" type="button" value="로그인">
		</div>
	</c:otherwise>
</c:choose>

