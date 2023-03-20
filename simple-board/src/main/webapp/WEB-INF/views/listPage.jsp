<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Simple Board</title>
<link rel="stylesheet" href="/resources/css/list.css" />
</head>
<body>
	<div class="container">
		<h1>Simple Board</h1>
		<form action="/search.do">
			<select name="searchName">
				<option value="author">작성자</option>
				<option value="title">글제목</option>
			</select> <input type="text" name="searchValue" /> <input type="submit"
				value="검색" />
		</form>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>날짜</th>
					<th>조회수</th>
				</tr>
			</thead>
			<c:forEach var="item" items="${list}">
				<tr>
					<td>${item.num}</td>
					<td><c:forEach begin="1" end="${item.repIndent}">
							<!-- <%="&nbsp;&nbsp;"%> -->
						</c:forEach> <a href="/retrieve.do?num=${item.num}">${item.title}</a></td>
					<td>${item.author}</td>
					<td>${item.writeday}</td>
					<td>${item.readcnt}</td>
				</tr>
			</c:forEach>
		</table>
		<!-- page -->
		<div class="page">
			<jsp:include page="page.jsp" flush="true" />
		</div>
		<a href="/writeui.do">글쓰기</a>
	</div>
</body>
</html>
