package org.zerock.simple_board.service;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.simple_board.domain.BoardDTO;
import org.zerock.simple_board.persistence.BoardDAO;

public class BoardRetrieveCommandImpl implements BoardCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, NamingException {
		String num = req.getParameter("num");
		BoardDAO dao = new BoardDAO();
		BoardDTO data = dao.retrieve(num);

		req.setAttribute("retrieve", data);

	} // execute

} // end class
