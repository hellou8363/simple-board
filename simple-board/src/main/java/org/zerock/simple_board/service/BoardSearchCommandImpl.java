package org.zerock.simple_board.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.simple_board.domain.BoardDTO;
import org.zerock.simple_board.persistence.BoardDAO;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BoardSearchCommandImpl implements BoardCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, NamingException {
		String searchName = req.getParameter("searchName");
		String searchValue = req.getParameter("searchValue");

		BoardDAO dao = new BoardDAO();
		List<BoardDTO> list = dao.search(searchName, searchValue);
		
		log.trace("executo - list: {}", list);

		req.setAttribute("list", list);
	} // execute

} // end class
