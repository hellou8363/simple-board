package org.zerock.simple_board.service;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.simple_board.persistence.BoardDAO;

public class BoardDeleteCommandImpl implements BoardCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, NamingException {
		String num = req.getParameter("num");
		BoardDAO dao = new BoardDAO();
		dao.delete(num);

	} // execute

} // end class
