package com.member.db;

import java.sql.SQLException;

import com.common.db.IbatisDAO;

/*
 * community table DAO
 */

public class MemberDAO extends IbatisDAO {

	// ?���??�� ?��?��
	public static MemberDAO getInstance() {
		
		MemberDAO _instance = new MemberDAO();
		_instance.SetDB();
		
		return _instance;
	}

	/*
	 * 기능 메서?��
	 */
	
	public MemberDTO getUserNick(String userId) throws SQLException {
		
		return (MemberDTO)GetDB().queryForObject("getUserNick", userId);
	}

		
}
