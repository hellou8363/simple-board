package org.zerock.simple_board.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.simple_board.domain.BoardVO;
import org.zerock.simple_board.persistence.BoardDAO;

public class BoardListCommandImpl implements BoardCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, NamingException {
		BoardDAO dao = new BoardDAO();
		List<BoardVO> list = dao.selectAll();
		
		req.setAttribute("list", list);
	} // execute
} // end class
