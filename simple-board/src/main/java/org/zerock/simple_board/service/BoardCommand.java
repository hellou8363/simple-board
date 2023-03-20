package org.zerock.simple_board.service;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardCommand {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, NamingException;
	
} // end interface
