<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Simple Board - 글 상세보기</title>
<link rel="stylesheet" href="resources/css/retrieve.css" />
</head>
<body>
	<div class="container">
		<h1>글 상세보기</h1>
		<form action="/update.do" method="post">
			<!-- 글 수정시 필요 -->
			<input type="hidden" name="num" value="${retrieve.num}" /> <span>글번호:
				${retrieve.num}</span> <span>조회수: ${retrieve.readcnt}</span><br /> <label
				for="title">제목</label> <input type="text" name="title" id="title"
				value="${retrieve.title}" /><br /> <label for="author">작성자</label>
			<input type="text" name="author" id="author"
				value="${retrieve.author}" /><br /> <label for="content">내용</label><br />
			<textarea name="content" id="content">${retrieve.content}</textarea>
			<br /> <input type="submit" value="수정" />
		</form>
		<a href="/list.do">목록</a> <a href="/delete.do?num=${retrieve.num}">삭제</a>
		<a href="/replyui.do?num=${retrieve.num}">답변달기</a>
	</div>
</body>
</html>
