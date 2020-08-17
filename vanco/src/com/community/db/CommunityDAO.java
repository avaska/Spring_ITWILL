package com.community.db;

import java.sql.SQLException;
import java.util.ArrayList;

import com.common.db.IbatisDAO;

/*
 * community table DAO
 */

public class CommunityDAO extends IbatisDAO {

	// ?���??�� ?��?��
	public static CommunityDAO getInstance() {
		
		CommunityDAO _instance = new CommunityDAO();
		_instance.SetDB();
		
		return _instance;
	}

	/*
	 * 기능 메서?��
	 */
	
	public void insertCommunity(Community com) throws SQLException {
		GetDB().insert("insertCommunity", com);	
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Community> getBoards() throws SQLException {
		ArrayList<Community> list = null;
		list = (ArrayList<Community>) GetDB().queryForList("getCommunity", null);
		return list;
	}

	public String getCommName(int moimNum) throws SQLException {
		String name = (String) GetDB().queryForObject("getCommName", moimNum);
		return name;
	}

	public int getCommCount() throws SQLException {
		int count = (Integer)GetDB().queryForObject("getCommCount", null);
		return count;
	}

	public String getCommPhoto(int moimNum) throws SQLException {
		return (String)GetDB().queryForObject("getCommPhoto", moimNum);
	}
		
}
