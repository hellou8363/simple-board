<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Simple Board - Write</title>
    <link rel="stylesheet" href="/resources/css/write.css">
  </head>
  <body>
    <div class="container">
      <h1>게시판 글쓰기</h1>
      <form action="/write.do" method="post">
        <label for="title">제목</label>
        <input type="text" name="title" id="title" placeholder="제목을 입력해 주세요." /><br>
        <label for="author">작성자</label>
        <input type="text" name="author" id="author" placeholder="작성자를 입력해 주세요." /><br>
        <label for="content">내용</label>
        <textarea name="content" id="content" rows="5" placeholder="내용을 입력하세요."></textarea><br>
        <input type="submit" value="등록">
      </form>
      <a href="/list.do">목록보기</a>
    </div>
  </body>
</html>
