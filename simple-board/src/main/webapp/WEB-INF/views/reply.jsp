<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/resources/css/write.css">
<title>Simple Board - 답변글 작성</title>
</head>
<body>
	<div class="container">
		<h1>답변글 작성</h1>
		<form action="/reply.do" method="post">
			<input type="hidden" name="num" value="${retrieve.num}"> <input
				type="hidden" name="repRoot" value="${replyui.repRoot}"> <input
				type="hidden" name="repStep" value="${replyui.repStep}"> <input
				type="hidden" name="repIndent" value="${replyui.repIndent}">
			<p>
				<span>원글번호: </span> <span>${replyui.num}</span>
			</p>
			<p>
			<span>조회수: </span>
			<span>${replyui.readcnt}</span>
			</p>
			<br> <label
				for="title">제목</label> <input type="text" name="title" id="title"
				value="${replyui.title}"><br> <label for="author">작성자</label>
			<input type="text" name="author" id="author"
				value="${replyui.author}"><br> <label for="content">내용</label>
			<textarea name="content" id="content" rows="10">${replyui.content}</textarea>
			<br> <input type="submit" value="답변등록">
		</form>
		<a href="/list.do">목록보기</a>
	</div>
</body>
</html>