package org.zerock.simple_board.service;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.simple_board.persistence.BoardDAO;

public class BoardReplyCommandImpl implements BoardCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, NamingException {
		String num = req.getParameter("num");
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String content = req.getParameter("content");
		String repRoot = req.getParameter("repRoot");
		String repStep = req.getParameter("repStep");
		String repIndent = req.getParameter("repIndent");

		BoardDAO dao = new BoardDAO();
		dao.reply(num, title, author, content, repRoot, repStep, repIndent);

	} // execute

} // end class
