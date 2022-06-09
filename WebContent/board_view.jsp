<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
			<td colspan="2">
				<!-- 목록보기 -->
				<img src="img/btn_list.gif" onclick="location.href='board_list.do'">
				<!-- 답변 -->
				<img src="img/btn_reply.gif" onclick="reply()">
				<!-- 삭제 -->
				<img src="img/btn_delete.gif" onclick="del()">
		</tr>
	</table>
</body>
</html>