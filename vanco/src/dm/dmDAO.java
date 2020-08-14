package dm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class dmDAO {

//*********** getConn()생성 : 커넥션풀로 부터 커넥션 객체con을 만들기 위한 메소드. 	
		private Connection getConn() throws Exception{
		
			Connection con = null;
			Context init = new InitialContext();

			// 커넥션 풀 얻기(context.xml파일의 <Resource> 태그의 name정보로 가져옴)
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/vanco");
			con = ds.getConnection();
			return con;
		}// getConn() 종료
		

//*********** sendDm()생성 : 메세지를 보내는 메소드 	
		public int sendDm(dmDTO ddto){
			Connection con = null;
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			String sql = "";
			int sendCheck=0;
			
			try {
				con=getConn();				
				
			// 데이터 삽입하기
				sql="insert into dmbox(dmContent, sendUserId, sendNickname, receiveUserId, receiveNickname, writeTime, ip)"
						+ "values(?,?,?,?,?,?,?)";	
				pstmt=con.prepareStatement(sql);
				
				pstmt.setString(1, ddto.getDmContent());
				pstmt.setString(2, ddto.getSendUserId());
				pstmt.setString(3, ddto.getSendNickname());
				pstmt.setString(4, ddto.getReceiveUserId());
				pstmt.setString(5, ddto.getReceiveNickname());		
				pstmt.setTimestamp(6, ddto.getWriteTime());
				pstmt.setString(7,ddto.getIp());					
				
				pstmt.executeUpdate();
				sendCheck=1;
				
			} catch (Exception e) {
				System.out.println("sendDm()오류"+e);
			}finally{
				if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
				if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
				if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
			}
			return sendCheck;
		}	// sendDm() 끝

		
		
//*********** getDmList()생성 : 받은 DM을 모두 뿌려주는 메소드	
		public ArrayList<dmDTO> getDmList(int startRow, int pageSize, String receiveUserId){			
			Connection con = null;
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			String sql = "";
			
			// ArrayList 객체 생성
			ArrayList<dmDTO> dmList=new ArrayList<dmDTO>();
			
			try {
				con=getConn();
								
				sql="select * from dmbox where receiveUserId=? order by writeTime DESC limit ?,?";
				pstmt=con.prepareStatement(sql);			
				pstmt.setString(1, receiveUserId);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, pageSize);
				rs=pstmt.executeQuery();				
				
				while(rs.next()){
					dmDTO ddto=new dmDTO();
					
					ddto.setSendUserId(rs.getString("sendUserId"));
					ddto.setReceiveUserId(rs.getString("receiveUserId"));
					ddto.setSendNickname(rs.getString("sendNickname"));
					ddto.setReceiveNickname(rs.getString("receiveNickname"));
					ddto.setDmContent(rs.getString("dmContent"));
					ddto.setWriteTime(rs.getTimestamp("writeTime"));
					ddto.setReadCheck(rs.getInt("readCheck"));
					dmList.add(ddto);
				}				
			} catch (Exception e) {
				System.out.println("getDmList()오류"+e);
			}finally{
				if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
				if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
				if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
			}			
			return dmList;			
		}  //getDmList()끝		

		
//*********** updateReadCheck()생성 : 글 읽을때 조회수 1 증가 시켜줌	
	public void updateReadCheck(String receiveUserId){
		Connection con = null;
		PreparedStatement pstmt=null;
		String sql = "";		
		try {
			con=getConn();				
			sql = "update dmbox set readCheck=readCheck+1 where receiveUserId =?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,receiveUserId);
			pstmt.executeUpdate();				
		} catch (Exception e) {
			System.out.println("updateReadCheck오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
		}			
	}//updateReadCheck()끝
	
	

//*********** countNewDm()생성 : 헤드 쪽지 아이콘에서 새로온 DM있는지 여부 체크
		public int countNewDm(String receiveUserId){
			Connection con = null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			int dmReadChk = 0;
			String sql = "";		
			try {
				con=getConn();				
				
				sql="select count(*) from dmbox where receiveUserId=? and readCheck=0";	// 댓글 갯수를 세는 함수
				
				pstmt=con.prepareStatement(sql);			
				pstmt.setString(1, receiveUserId);
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					dmReadChk=rs.getInt(1);
				}				
				System.out.println(dmReadChk);					
				
			} catch (Exception e) {
				System.out.println("countNewDm오류"+e);
			}finally{
				if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
				if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}	
				if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
			}	
			
		return dmReadChk;
	}//updateReadCount()끝
	
	
	
//*********** getDmCount()생성 : 쪽지 페이지에서 존재하는 모든 쪽지를 세는 함수
	public int getDmCount(String receiveUserId){
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";
		
		int count = 0;
		
		try {
			con=getConn();
			sql="select count(*) from dmbox where receiveUserId=? ";	// 테이블의 행 수를 세는 함수
			
			pstmt=con.prepareStatement(sql);	
			pstmt.setString(1, receiveUserId);
			rs=pstmt.executeQuery();			
			
			if(rs.next()){
				count=rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println("getDmCount()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
		}		
		return count;
	}//getDmCount()끝	
	
	
	
	
	
	
}//DAO 클래스 끝
