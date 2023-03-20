<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="org.zerock.simple_board.entity.PageTO" %>

<%
	PageTO to = (PageTO)request.getAttribute("page");
	int curPage = to.getCurPage();
	int perPage = to.getPerPage();
	int totalCount = to.getTotalCount();
	
	int totalPage = totalCount / perPage; // 보여줄 페이지 번호 개수
	
	System.out.println("totalPage: " + totalPage);
	System.out.println("perPage: " + perPage);
	
	if (totalCount % perPage != 0) {
		totalPage++;
	} // if
	
	for (int i = 1; i <= totalPage; i++) {
		if (curPage == i) {
			out.print("<a href='#'>" + i + "</a>&nbsp;");
		} else {
			out.print("<a href='/list.do?curPage=" + i + "'>" + i + "</a>&nbsp;");
		} // if-else
	} // for
%>