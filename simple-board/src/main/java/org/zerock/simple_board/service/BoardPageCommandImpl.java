package org.zerock.simple_board.service;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.simple_board.entity.PageTO;
import org.zerock.simple_board.persistence.BoardDAO;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BoardPageCommandImpl implements BoardCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, NamingException {
		int curPage = 1; // 기본 페이지

		if (req.getParameter("curPage") != null) {
			curPage = Integer.parseInt(req.getParameter("curPage"));
		} // if

		BoardDAO dao = new BoardDAO();
		PageTO list = dao.page(curPage);

		// listPage.jsp에서 목록 리스트 데이터 저장
		req.setAttribute("list", list.getList());

		// page.jsp에서 페이징 처리 데이터 저장
		req.setAttribute("page", list);
		
		log.trace("page - list: {}", list);

	} // execute

} // end class
