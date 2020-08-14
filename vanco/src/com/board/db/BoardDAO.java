package com.board.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.comments.db.Comments;
import com.common.db.IbatisDAO;

/*
 * board table DAO
 */

public class BoardDAO extends IbatisDAO {

	// ?���??�� ?��?��
	public static BoardDAO getInstance() {
		
		BoardDAO _instance = new BoardDAO();
		
		_instance.SetDB();
		
		return _instance;
		
	}
	
	/*
	 * 기능 메서?��
	 */
	
	@SuppressWarnings("unchecked")
	public ArrayList<Board> getArticleList(int page, int moimNum) throws SQLException {
		
		ArrayList<Board> articleList = null;
		articleList = (ArrayList<Board>)GetDB().queryForList("getArticleList", moimNum, page, 10);
		
		return articleList;
	}
	

	public Board getArticle(HashMap<String, Object> map) throws SQLException {
		
		Board article = null;
		article = (Board)GetDB().queryForObject("getArticle", map);
		
		return article;
	}

	public void insertArticle(Board article) throws SQLException {
		
		GetDB().insert("insertArticle", article);
		
	}
	
	public void setArticleCount(Board article) throws SQLException {
		
		GetDB().update("setArticleCount", article);
		
	}

	public void deleteArticle(HashMap<String, Object> map) throws SQLException {
		
		GetDB().delete("deleteArticle", map);
		
	}

	public int insertFile(Files myfile) throws SQLException {
		
		GetDB().insert("insertFile", myfile);
		int fileNum = (int)GetDB().queryForObject("sequence", null);
		
		return fileNum;
	}

	public void getFilesInsert(int moimNum) throws SQLException {
		GetDB().queryForList("getFiles", (Integer)moimNum);
	}

	public void updateFiles(Map<String, Object> map) throws SQLException {
		GetDB().update("updateFile", map);
		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Files> getFiles(HashMap<String, Object> map) throws SQLException {
		ArrayList<Files> list = null;
		list = (ArrayList<Files>) GetDB().queryForList("getFiles", map);
		return list;
	}

	public Files downFile(int idx) throws SQLException {
		
		Files myfile = null;
		myfile = (Files) GetDB().queryForObject("downFile", idx);
		return myfile;
		
	}

	public void deleteFile(int num) throws SQLException {
		GetDB().delete("deleteFile", num);
	}

	public void updateArticle(Board article) throws SQLException {
		GetDB().update("updateArticle", article);
	}

	public int getArticleNum(int moimNum) throws SQLException {
		int result = (Integer)GetDB().queryForObject("getArticleNum", moimNum);
		return result;
	}

}
