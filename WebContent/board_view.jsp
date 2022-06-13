<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="js/httpRequest.js"></script>
<script>
	function reply(){
		location.href="reply_form.jsp?idx=${vo.idx}&page=${param.page}";
	}
	function del(){
		if(!confirm("삭제하시겠습니까?")){
			return;
		}
		
		var pwd = "${vo.pwd}";
		var c_pwd = document.getElementById("c_pwd").value;
		
		if(pwd != c_pwd){
			alert("비밀번호 불일치");
			return;
		}
		
		console.log(pwd);
		console.log(c_pwd);
		var url = "del.do";
		var param = "idx=${vo.idx}";
		
		sendRequest(url,param,delCheck,"post");
	}
	
	function delCheck(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var data = xhr.responseText;
			//문자열 형태로 데이터를 받아온다 json 형태로 변환 필요
			var json = eval(data);
			
			if(json[0].param=='yes'){
				alert("삭제 완료했습니다.");
				location.href="board_list.do";
			}else{
				alert("삭제 실패");
			}
		}
	}
</script>
</head>
<body>
	<table border="1">
		<caption>:::게시글 상세보기:::</caption>
		<tr>
			<th>제목</th>
			<td>${vo.subject}</td>
		<tr>
		<tr>
			<th>작성자</th>
			<td>${vo.name}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${vo.regdate}</td>
		</tr>
		<tr>
			<th>ip</th>
			<td>${vo.ip}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td width="500px" height="200px"><pre>${vo.content}</pre></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td ><input type="password" id="c_pwd" value=""></td>
		</tr>	
		<tr>
			<td colspan="2">
				<!-- 목록보기 -->
				<img src="img/btn_list.gif" onclick="location.href='board_list.do?page=${param.page}'" style="cursor:pointer;">
				<!-- 2개이상 답변금지 -->
				<c:if test="${vo.depth lt 2}">
				<!-- 답변 -->
				<img src="img/btn_reply.gif" onclick="reply()" style="cursor:pointer;">
				</c:if>
				<!-- 삭제 -->
				<img src="img/btn_delete.gif" onclick="del()" style="cursor:pointer;">
		</tr>
	</table>
</body>
</html>