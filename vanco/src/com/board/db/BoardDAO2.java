package com.board.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.common.db.CommonDAO;

/*
 * board table DAO2 - test
 */

public class BoardDAO2 extends CommonDAO {

	// ?‹±ê¸??†¤ ?Œ¨?„´
	public static BoardDAO2 getInstance() {
		
		BoardDAO2 _instance = new BoardDAO2();
		
		return _instance;
		
	}
	
	/*
	 * ê¸°ëŠ¥ ë©”ì„œ?“œ
	 */
	
	public ArrayList<Board> getArticleList() throws SQLException {
		
		ResultSet rs = null;
		
		String sql = "select * from BOARD order by idx desc";
		
		rs = openConnection().executeQuery(sql);
		
		ArrayList<Board> articleList = new ArrayList<Board>();
		
		while (rs.next()) {
			Board article = new Board();
			
			article.setIdx(rs.getInt("idx"));
			article.setTitle(rs.getString("title"));
			article.setWriter(rs.getString("writer"));
			article.setRegdate(rs.getTimestamp("regdate"));
			articleList.add(article);
		}
		closeConnection();
		return articleList;
	}
	
}
