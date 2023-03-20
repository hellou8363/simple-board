package org.zerock.simple_board.service;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.simple_board.persistence.BoardDAO;

public class BoardWriteCommandImpl implements BoardCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, NamingException {
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String content = req.getParameter("content");
		
		BoardDAO dao = new BoardDAO();
		dao.write(title, author, content);
	} // execute
	
} // end class
