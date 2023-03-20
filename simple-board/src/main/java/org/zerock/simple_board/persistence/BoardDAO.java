package org.zerock.simple_board.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.zerock.simple_board.domain.BoardDTO;
import org.zerock.simple_board.domain.BoardVO;
import org.zerock.simple_board.entity.PageTO;

import lombok.Cleanup;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString
@Log4j2
public class BoardDAO {
	private DataSource datasource;

	public BoardDAO() throws NamingException {
		log.trace("Default Constructor invoked.");

		String prefix = "java:comp/env/";
//		String name = "jdbc/OracleCloudATP";
		String name = "jdbc/OracleLocalDB";

		Context ctx = new InitialContext();
		log.info("ctx: {}", ctx);

		Object obj = ctx.lookup(prefix + name);
		log.info("obj: {}", obj);

		Objects.requireNonNull(obj);
		this.datasource = (DataSource) obj;
	} // default constructor

	public List<BoardVO> selectAll() throws SQLException { // 목록보기
		log.trace("selectAll() invoked.");

		final String sql = "SELECT num, author, title, content, " + "to_char(writeday, 'YYYY/MM/DD') AS writeday, "
				+ "readcnt, repRoot, repStep, repIndent FROM board";

		@Cleanup
		Connection conn = this.datasource.getConnection();
		@Cleanup
		PreparedStatement pstmt = conn.prepareStatement(sql);
		@Cleanup
		ResultSet rs = pstmt.executeQuery();

		List<BoardVO> list = new ArrayList<>();

		while (rs.next()) {
			Integer num = rs.getInt("num");
			String author = rs.getString("author");
			String title = rs.getString("title");
			String content = rs.getString("content");
			Integer readcnt = rs.getInt("readcnt");
			String writeday = rs.getString("writeday");
			Integer repRoot = rs.getInt("repRoot");
			Integer repStep = rs.getInt("repStep");
			Integer repIndent = rs.getInt("repIndent");

			BoardVO vo = new BoardVO(num, author, title, content, readcnt, writeday, repRoot, repRoot, repIndent);

			list.add(vo);
		} // while

		return list;
	} // selectAll

	public void write(String _title, String _author, String _content) throws SQLException { // 글쓰기
		log.trace("write(_title, _author, _content) invoked.");

		final String sql = "INSERT INTO board (num, title, author, content, repRoot, repStep, repIndent) VALUES (board_seq.nextval, ?, ?, ?, board_seq.currval, 0, 0)";

		@Cleanup
		Connection conn = this.datasource.getConnection();
		@Cleanup
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, _title);
		pstmt.setString(2, _author);
		pstmt.setString(3, _content);

		int n = pstmt.executeUpdate();
	} // write

	public void readCount(String _num) throws SQLException { // 조회수 증가
		log.trace("readCount(_num) invoked.");

		final String sql = "UPDATE board SET readcnt = readcnt + 1 WHERE num = " + _num;

		@Cleanup
		Connection conn = this.datasource.getConnection();
		@Cleanup
		PreparedStatement pstmt = conn.prepareStatement(sql);

		int n = pstmt.executeUpdate();

	} // readCount

	public BoardDTO retrieve(String _num) throws SQLException { // 글 상세보기
		log.trace("retrieve(_num) invoked.");

		final String sql = "SELECT * FROM board WHERE num = ?";

		readCount(_num); // 조회수 증가

		@Cleanup
		Connection conn = this.datasource.getConnection();
		@Cleanup
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, Integer.parseInt(_num));

		@Cleanup
		ResultSet rs = pstmt.executeQuery();
		BoardDTO data = new BoardDTO();

		if (rs.next()) {
			Integer num = rs.getInt("num");
			String title = rs.getString("title");
			String author = rs.getString("author");
			String content = rs.getString("content");
			String writeday = rs.getString("writeday");
			Integer readcnt = rs.getInt("readcnt");

			data.setNum(num);
			data.setTitle(title);
			data.setAuthor(author);
			data.setContent(content);
			data.setWriteday(writeday);
			data.setReadcnt(readcnt);
		} // if

		return data;
	} // retrieve

	public void update(String _num, String _title, String _author, String _content) throws SQLException { // 글 수정하기
		log.trace("update(_num, _title, _author, _content) invoked.");

		final String sql = "UPDATE board SET title = ?, author = ?, content = ? WHERE num = ?";

		@Cleanup
		Connection conn = this.datasource.getConnection();
		@Cleanup
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, _title);
		pstmt.setString(2, _author);
		pstmt.setString(3, _content);
		pstmt.setInt(4, Integer.parseInt(_num));

		int n = pstmt.executeUpdate();
	} // update

	public void delete(String _num) throws SQLException { // 글 삭제하기
		log.trace("delete(_num) invoked.");

		final String sql = "DELETE FROM board WHERE num = ?";

		@Cleanup
		Connection conn = this.datasource.getConnection();
		@Cleanup
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, Integer.parseInt(_num));

		int n = pstmt.executeUpdate();
	} // delete

	public List<BoardDTO> search(String _searchName, String _searchValue) throws SQLException { // 글 검색하기
		log.trace("search(_searchName, _searchValue) invoked.");

		String sql = "SELECT num, author, title, content, to_char(writeday, 'YYYY/MM/DD') AS writeday, readcnt FROM board";

		if (_searchName.equals("title")) {
			sql += " WHERE title LIKE ?";
		} else {
			sql += " WHERE author LIKE ?";
		} // if-else
		log.trace("sql: {}", sql);

		@Cleanup
		Connection conn = this.datasource.getConnection();
		@Cleanup
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, "%" + _searchValue + "%");

		@Cleanup
		ResultSet rs = pstmt.executeQuery();
		

		List<BoardDTO> list = new ArrayList<>();
		BoardDTO data = new BoardDTO();

		while (rs.next()) {
			Integer num = rs.getInt("num");
			String title = rs.getString("title");
			String author = rs.getString("author");
			String content = rs.getString("content");
			String writeday = rs.getString("writeday");
			Integer readcnt = rs.getInt("readcnt");

			data.setNum(num);
			data.setTitle(title);
			data.setAuthor(author);
			data.setContent(content);
			data.setWriteday(writeday);
			data.setReadcnt(readcnt);
			
			log.trace("data: {}", data);

			list.add(data);
			
			log.trace("data - list: {}", list.toString());

		} // while

		return list;
	} // search

	public BoardDTO replyui(String _num) throws SQLException { // 답변글 입력 폼 보기
		log.trace("replyui(_num) invoked.");

		String sql = "SELECT * FROM board WHERE num = ?";

		@Cleanup
		Connection conn = this.datasource.getConnection();
		@Cleanup
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, Integer.parseInt(_num));

		@Cleanup
		ResultSet rs = pstmt.executeQuery();
		BoardDTO data = new BoardDTO();

		if (rs.next()) {
			data.setNum(rs.getInt("num"));
			data.setTitle(rs.getString("title"));
			data.setAuthor(rs.getString("author"));
			data.setContent(rs.getString("content"));
			data.setWriteday(rs.getString("writeday"));
			data.setReadcnt(rs.getInt("readcnt"));
			data.setRepRoot(rs.getInt("repRoot"));
			data.setRepStep(rs.getInt("repStep"));
			data.setRepIndent(rs.getInt("repIndent"));
		} // if

		return data;
	} // replyui

	public void makeReply(String _root, String _step) throws SQLException { // 답변글의 기존 repStep 1 증가
		log.trace("makeReply(_root, _step) invoked.");

		String sql = "UPDATE board SET repStep = repStep + 1 WHERE repRoot = ? AND repStep > ?";

		@Cleanup
		Connection conn = this.datasource.getConnection();
		@Cleanup
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, Integer.parseInt(_root));
		pstmt.setInt(2, Integer.parseInt(_step));

		int n = pstmt.executeUpdate();

	} // makeReply

	public void reply(String _num, String _title, String _author, String _content, String _repRoot, String _repStep,
			String _repIndent) throws SQLException { // 답변달기
		log.trace("reply(_num, _title, _author, _content, _repRoot, _repStep, _repIndent) invoked.");

		String sql = "INSERT INTO board(num, title, author, content, repRoot, repStep, repIndent)"
				+ "VALUES(board_seq.nextVal, ?, ?, ?, ?, ?, ?)";

		// repStep + 1
		makeReply(_repRoot, _repStep);

		@Cleanup
		Connection conn = this.datasource.getConnection();
		@Cleanup
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, _title);
		pstmt.setString(2, _author);
		pstmt.setString(3, _content);
		pstmt.setInt(4, Integer.parseInt(_repRoot));
		pstmt.setInt(5, Integer.parseInt(_repStep) + 1);
		pstmt.setInt(6, Integer.parseInt(_repIndent) + 1);

		int n = pstmt.executeUpdate();

	} // reply

	public int totalCount() throws SQLException { // 페이징 처리: 전체 레코드 개수 구하기
		log.trace("totalCount() invoked.");

		int count = 0;
		String sql = "SELECT count(num) FROM board";

		@Cleanup
		Connection conn = this.datasource.getConnection();
		@Cleanup
		PreparedStatement pstmt = conn.prepareStatement(sql);
		@Cleanup
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			count = rs.getInt(1);
		} // if

		return count;

	} // totalCount

	public PageTO page(int curPage) throws SQLException {
		log.trace("page(curPage) invoked.");

		String sql = "SELECT num, author, title, content, to_char(writeday, 'YYYY/MM/DD') AS writeday, "
				+ "readcnt, repRoot, repStep, repIndent " + "FROM board ORDER BY repRoot DESC, repStep ASC";

		PageTO to = new PageTO();
		int totalCount = totalCount();

		List<BoardDTO> list = new ArrayList<BoardDTO>();

		@Cleanup
		Connection conn = this.datasource.getConnection();
		@Cleanup
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		@Cleanup
		ResultSet rs = pstmt.executeQuery();

		int perPage = to.getPerPage(); // 5

		int skip = (curPage - 1) * perPage;

		if (skip > 0) {
			rs.absolute(skip);
		} // if

		for (int i = 0; (i < perPage) && rs.next(); i++) {
			Integer num = rs.getInt("num");
			String author = rs.getString("author");
			String title = rs.getString("title");
			String content = rs.getString("content");
			Integer readcnt = rs.getInt("readcnt");
			String writeday = rs.getString("writeday");
			Integer repRoot = rs.getInt("repRoot");
			Integer repStep = rs.getInt("repStep");
			Integer repIndent = rs.getInt("repIndent");

			BoardDTO data = new BoardDTO();

			data.setNum(rs.getInt("num"));
			data.setTitle(rs.getString("title"));
			data.setAuthor(rs.getString("author"));
			data.setContent(rs.getString("content"));
			data.setWriteday(rs.getString("writeday"));
			data.setReadcnt(rs.getInt("readcnt"));
			data.setRepRoot(rs.getInt("repRoot"));
			data.setRepStep(rs.getInt("repStep"));
			data.setRepIndent(rs.getInt("repIndent"));

			list.add(data);
		} // for

		to.setList(list); // ArrayList 저장
		to.setTotalCount(totalCount); // 전체 레코드 개수
		to.setCurPage(curPage); // 현재 페이지
		
		log.trace("to: {}", to.toString());

		return to;
	} // PageTO

} // end class
