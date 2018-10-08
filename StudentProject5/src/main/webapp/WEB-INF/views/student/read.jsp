<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div align="center">
<jsp:include page="../header.jsp"></jsp:include>
<link rel = "stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<h1>학생 정보</h1>
아이디 : ${student.id } <br>

번호 : ${student.no } <br>

이름 :${student.name } <br>

비밀번호 :${student.password } <br>

이메일 :${student.email}<br>
등록일 :<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${student.regdate }"/>
<br>
<c:if test="${student.savedName != null }">
사 진 : <img width = 300 src="display?fileName=${student.savedName }"><br>${originalName }<br>
</c:if>
<a href="modify?id=${student.id }">수 정</a>&nbsp;&nbsp; <a href="remove?id=${student.id }">삭 제</a>&nbsp;&nbsp; <a href="list">학생 리스트</a>&nbsp;&nbsp;
<a href="imageList">학생사진리스트</a>
</div>

<!-- jQuery 2.1.4 -->
<script src="https://code.jquery.com/jquery-2.2.4.js"></script>
<style>
#modDiv {
	width: 300px;
	height: 100px;
	background-color: gray;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-top: -50px;
	margin-left: -150px;
	padding: 10px;
	z-index: 1000;
}
#replyDiv {
	width: 500px;
	height: 100px;
	background-color: gray;
}
</style>
	<div id='modDiv' style='display: none;'>
		<div class='modal-title'></div>		
		<div id='modal-replyer'></div>
		<div>
			<input type='text' id='replytext'>
		</div>
		<div>
			<button type="button" id="replyModBtn">댓글수정</button>
			<button type="button" id="replyDelBtn">댓글삭제</button>
			<button type="button" id='closeBtn'>닫기</button>
		</div>
	</div>
	
	
	<c:if test="${login != null}">
	<%-- <div align="center" <c:if test="${login == null }">	style="display:none"</c:if>> --%>
	<div align="center">
	<h2>[${student.name}] 칭찬하기</h2>	
	<div>댓글 작성자: <input type='text' name='replyer' value="${login.id }" readonly id='newReplyWriter'></div>	
	<div>댓글 내용: <input type='text' name='replyText' id='newReplyText'></div>
	<div><button id="replyAddBtn">댓글 등록</button></div>
	<div align="left"><ul id="replies"></ul></div>	
	</div>
	</c:if>
	<script>
	$(document).ready(function(){
		var sid = "${student.id }";
		getAllList();
		var selected = 0;
		var loginId = "${login.id}";
		//alert(sid)
		function getAllList() {
			$.getJSON("replies/all/" + sid,
					function(data) {
					//console.log(data.length);
						var str = "";
						$(data).each(function() {
							str += "<div align='center' ><div id='replyDiv'><li data-no='"+this.no+"' class='replyLi'>"
									+ this.no + "(작성자 : <span id='no-replyer"
									+ this.no +	"'>" 
									+ this.replyer
									+ "</span>)"
									+ "<div id='no" + this.no + "'>"									
									+ this.replyText
									+ "</div><button>댓글수정</button></li></div></div>";
						});

						$("#replies").html(str);
			});
		}

		$("#replyAddBtn").on("click", function() {
			
			var replyer = $("#newReplyWriter").val();
			var replyText = $("#newReplyText").val();
			//var sid = $("#studentID").val();			
			$.ajax({
				type : 'post',
				url : 'replies',
				headers : {			
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				dataType : 'text',
				data : JSON.stringify({
					sid : sid,
					replyer : replyer,
					replyText : replyText
				}),
				success : function(result) {
					if (result == 'SUCCESS') {
						alert("등록 되었습니다.");
						getAllList();
					}
				}
			});
		});

		$("#replies").on("click", ".replyLi button", function() {
			var reply = $(this).parent();

			var no = reply.attr("data-no");
			var a = ".replyLi #no" + no;
			var replyer = "#no-replyer" + no;
			var replytext = $(a).text();// #"+reply.text();	
			
			selected = $(replyer).text();
			$(".modal-title").html(no);
			$("#modal-replyer").html($(replyer).text());
			$("#replytext").val(replytext);
			$("#modDiv").show("slow");

		});
		
		$("#replyModBtn").on("click",function(){			  
			  
			  if(loginId == selected){
				  var no = $(".modal-title").html();
				  var replytext = $("#replytext").val();
				  
				  $.ajax({
						type:'put',
						url:'replies/'+no,
						headers: { 
						      "Content-Type": "application/json",
						      "X-HTTP-Method-Override": "PUT" },
						data:JSON.stringify({replyText:replytext}), 
						dataType:'text', 
						success:function(result){
							//console.log("result: " + result);
							if(result == 'SUCCESS'){
								alert("수정 되었습니다.");
								 $("#modDiv").hide("slow");
								getAllList();							
							}
						}
				  });
			  }else{
				  alert("수정할 수 없습니다.");
				  $("#modDiv").hide("slow");
					getAllList();
			  }
		});		
		
		$("#replyDelBtn").on("click", function() {
			if(loginId == selected){

				var no = $(".modal-title").html();
				var replytext = $("#replytext").val();
	
				$.ajax({
					type : 'delete',
					url : 'replies/' + no,
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "DELETE"
					},
					dataType : 'text',
					success : function(result) {
						//console.log("result: " + result);
						if (result == 'SUCCESS') {
							alert("삭제 되었습니다.");
							$("#modDiv").hide("slow");
							getAllList();
						}
					}
				});
			}else{
				alert("삭제할 수 없습니다.");
				  $("#modDiv").hide("slow");
					getAllList();
			}
		});
		
		$("#closeBtn").on("click", function(){
			$("#modDiv").hide("slow");
			getAllList();
		});
	});
	</script>