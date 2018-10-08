<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
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
</style>
</head>

<body>
	<div id='modDiv' style="display: none;">
		<div class='modal-title'></div>
		<div>
			<input type='text' id='replytext'>
		</div>
		<div>
			<button type="button" id="replyModBtn">댓글수정</button>
			<button type="button" id="replyDelBtn">댓글삭제</button>
			<button type="button" id='closeBtn'>닫기</button>
		</div>
	</div>
	<div align="center">
	<h2>Ajax Test Page</h2>
	<div>학생번호: <input type="text" name="sid" id="studentID"></div>	
	<div>댓글 작성자: <input type='text' name='replyer' id='newReplyWriter'></div>	
	<div>댓글 내용: <input type='text' name='replyText' id='newReplyText'></div>
	<div><button id="replyAddBtn">댓글 등록</button></div>
	<div align="left"><ul id="replies"></ul></div>	
	</div>
	<script>
	$(document).ready(function(){
	//	var sid = $("#sid").val();
		//alert(sid)
		function getAllList() {
			$.getJSON("replies/all/" + $("#studentID").val(),
					function(data) {
					//console.log(data.length);
						var str = "";
						$(data).each(function() {
							str += "<li data-no='"+this.no+"' class='replyLi'>"
									+ this.no
									+ ":"+"<div id='no" + this.no + "'>"
									+ this.replyText
									+ "</div><button>댓글수정</button></li>";
						});

						$("#replies").html(str);
			});
		}

		$("#replyAddBtn").on("click", function() {
			
			var replyer = $("#newReplyWriter").val();
			var replyText = $("#newReplyText").val();
			var sid = $("#studentID").val();
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
			var replytext = $(a).text();// #"+reply.text();

			$(".modal-title").html(no);
			$("#replytext").val(replytext);
			$("#modDiv").show("slow");

		});
		
		$("#replyModBtn").on("click",function(){
			  
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
		});		
		
		$("#replyDelBtn").on("click", function() {

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
		});
		
		$("#closeBtn").on("click", function(){
			$("#modDiv").hide("slow");
			getAllList();
		});
	});
	</script>
</body>
</html>