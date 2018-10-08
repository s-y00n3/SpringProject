<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
	<link rel="stylesheet" href="/resources/css/style.css">
</head>


<div id="header">
	<div id="logo" class="box3">
		<img class="noborder" src="/resources/front.png" width="100%" height="150"/>
	</div>
	<div id="auth" class="box3">
		<jsp:include page="user/loginAjax.jsp"/>
	</div>
</div>

