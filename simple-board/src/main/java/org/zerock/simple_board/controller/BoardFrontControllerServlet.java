package org.zerock.simple_board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.simple_board.service.BoardCommand;
import org.zerock.simple_board.service.BoardDeleteCommandImpl;
import org.zerock.simple_board.service.BoardListCommandImpl;
import org.zerock.simple_board.service.BoardPageCommandImpl;
import org.zerock.simple_board.service.BoardReplyCommandImpl;
import org.zerock.simple_board.service.BoardReplyUICommandImpl;
import org.zerock.simple_board.service.BoardRetrieveCommandImpl;
import org.zerock.simple_board.service.BoardSearchCommandImpl;
import org.zerock.simple_board.service.BoardUpdateCommandImpl;
import org.zerock.simple_board.service.BoardWriteCommandImpl;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
@WebServlet("*.do")
public class BoardFrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.trace("service(req, res) invoked.");

		try {
			req.setCharacterEncoding("UTF-8");

			String requestURI = req.getRequestURI();
			String contextPath = req.getContextPath();
			String com = requestURI.substring(contextPath.length());

			BoardCommand command = null;
			String nextPage = null;
			
			if(com.equals("/list.do")) { // 목록보기 - 페이징 처리
				command = new BoardPageCommandImpl();
				command.execute(req, res);
				nextPage = "/WEB-INF/views/listPage.jsp";
			} // if

			if (com.equals("/writeui.do")) { // 글쓰기 폼
				nextPage = "/WEB-INF/views/write.jsp";
			} // if

			if (com.equals("/write.do")) { // 글쓰기
				command = new BoardWriteCommandImpl();
				command.execute(req, res);
				nextPage = "/list.do";
			} // if

			if (com.equals("/retrieve.do")) { // 글 상세보기
				command = new BoardRetrieveCommandImpl();
				command.execute(req, res);
				nextPage = "/WEB-INF/views/retrieve.jsp";
			} // if

			if (com.equals("/update.do")) { // 글 수정하기
				command = new BoardUpdateCommandImpl();
				command.execute(req, res);
				nextPage = "/list.do";
			} // if

			if (com.equals("/delete.do")) { // 글 삭제하기
				command = new BoardDeleteCommandImpl();
				command.execute(req, res);
				nextPage = "/list.do";
			} // if

			if (com.equals("/search.do")) { // 글 검색하기
				command = new BoardSearchCommandImpl();
				command.execute(req, res);
				nextPage = "/WEB-INF/views/listPage.jsp";
			} // if
			
			if (com.equals("/replyui.do")) { // 글 검색하기
				command = new BoardReplyUICommandImpl();
				command.execute(req, res);
				nextPage = "/WEB-INF/views/reply.jsp";
			} // if
			
			if (com.equals("/reply.do")) { // 답변글 쓰기
				command = new BoardReplyCommandImpl();
				command.execute(req, res);
				nextPage = "/list.do";
			} // if

			RequestDispatcher dis = req.getRequestDispatcher(nextPage);
			dis.forward(req, res);

		} catch (Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
