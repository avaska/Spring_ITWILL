package com.common.db;

import com.db.sqlconfig.IBatisDBConnector;
import com.ibatis.sqlmap.client.SqlMapClient;

/*
 * iBatis DAO 공통 모듈
 */

public class IbatisDAO {

	private SqlMapClient myDB;
	
	public void SetDB() {
		
		myDB = IBatisDBConnector.getSqlMapInstance();
		
	}
	
	protected SqlMapClient GetDB() {
		
		return myDB;

	}
	
}
