<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
	<h1>파일 업로드</h1>
	<!-- 파일을 서버로 업로드할 때는 enctype을 multipart/form-data방식으로 전송해야함 -->
	<form action="uploadForm2" method="post"
	enctype="multipart/form-data">
		<input type = "file" name = "files[0]"><br>
		<input type = "file" name = "files[1]"><br>
		<input type = "file" name = "files[2]"><br>
		<input type = "submit" value="파일업로드">
	</form>
	</div>
</body>
</html>