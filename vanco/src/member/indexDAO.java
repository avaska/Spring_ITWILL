package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import timeLine.timeLineDTO;

public class indexDAO {


	//*********** getConn()생성 : 커넥션풀로 부터 커넥션 객체con을 만들기 위한 메소드. 	
	private Connection getConn() throws Exception{
	
		Connection con = null;
		Context init = new InitialContext();

		// 커넥션 풀 얻기(context.xml파일의 <Resource> 태그의 name정보로 가져옴)
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/vanco");
		con = ds.getConnection();
		return con;
	}// getConn() 종료

	
//*********** today 사진 얻기 메소드
	public ArrayList<indexDTO> getTodayPic() {
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";		
		
		ArrayList<indexDTO> list=new ArrayList<indexDTO>();
		
		try {				
		// 데이터 삽입하기
			con=getConn();				
			
			sql ="SELECT A.*, B.photoNum, B.photoUrl, C.userNickname, "
				+"	(SELECT COUNT(*) FROM timelinelikes where timeLineNum = A.timeLineNum) likesCount, "
				+"	(SELECT COUNT(*) FROM timelinereply where timeLineNum = A.timeLineNum) replyCount "
				+"	FROM timeline A, timelinephoto B, vancomember C "
				+"	WHERE A.userId = B.userId "
				+"	AND A.userId = C.userId "
				+"	AND A.timeLineNum = B.timeLineNum "
				+"	AND A.writeTime > current_date() "
				+"	group by A.timeLineNum "
				+"	ORDER BY (replyCount+likesCount*3) DESC  , B.photoNum limit 7 ";
			
			pstmt=con.prepareStatement(sql);			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				indexDTO idto=new indexDTO();
				
				idto.setTimeLineNum(rs.getInt("timeLineNum"));
				idto.setContent(rs.getString("content"));
				idto.setUserNickname(rs.getString("userNickname"));
				idto.setLikesCount(rs.getString("likesCount"));
				idto.setReplyCount(rs.getString("replyCount"));
				idto.setPhotoUrl(rs.getString("photoUrl"));		
				
				list.add(idto);
			}
		} catch (Exception e) {
			System.out.println("getTimeLine()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}	
		
		return list;
		
	}
	
	
//*********** 위클리 사진 얻기 메소드
	public ArrayList<indexDTO> getWeeklyPic() {
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";		
		
		ArrayList<indexDTO> list=new ArrayList<indexDTO>();
		
		try {				
		// 데이터 삽입하기
			con=getConn();				
			
			sql ="SELECT A.*, B.photoNum, B.photoUrl, C.userNickname, "
				+"	(SELECT COUNT(*) FROM timelinelikes where timeLineNum = A.timeLineNum) likesCount, "
				+"	(SELECT COUNT(*) FROM timelinereply where timeLineNum = A.timeLineNum) replyCount "
				+"	FROM timeline A, timelinephoto B, vancomember C "
				+"	WHERE A.userId = B.userId "
				+"	AND A.userId = C.userId "
				+"	AND A.timeLineNum = B.timeLineNum "
				+"	AND A.writeTime > current_date() - interval 7 day "
				+"	group by A.timeLineNum "
				+"	ORDER BY (replyCount+likesCount*3) DESC  , B.photoNum limit 4 ";
			
			pstmt=con.prepareStatement(sql);			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				indexDTO idto=new indexDTO();
				
				idto.setTimeLineNum(rs.getInt("timeLineNum"));
				idto.setContent(rs.getString("content"));
				idto.setUserNickname(rs.getString("userNickname"));
				idto.setLikesCount(rs.getString("likesCount"));
				idto.setReplyCount(rs.getString("replyCount"));
				idto.setPhotoUrl(rs.getString("photoUrl"));		
				
				list.add(idto);
			}
		} catch (Exception e) {
			System.out.println("getWeeklyPic()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}	
		
		return list;
		
	} //getWeeklyPic

	
//*********** 최신글 얻기 메소드
	public ArrayList<indexDTO> getLatestBoard() {
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";		
		
		ArrayList<indexDTO> list=new ArrayList<indexDTO>();
		
		try {				
		// 데이터 삽입하기
			con=getConn();				
			
			sql ="  SELECT B.name as boardName, A.moimNum, A.idx, A.title, A.regdate AS writeTime, A.writer AS userNickname "
				+"	FROM board A, community B "
				+"	WHERE A.moimNum >10000 AND A.moimNum = B.num "
				+"	ORDER BY regdate DESC "
				+"  LIMIT 7";
			
			pstmt=con.prepareStatement(sql);			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				indexDTO idto=new indexDTO();
				
				// 시간 형식 바꾸기				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm"); 
							
				String writeTime=sdf.format(rs.getTimestamp("writeTime"));				
				
				Date now = new Date();				
				Date wTime = sdf.parse(writeTime);
				long writetime = wTime.getTime();
				long nowTime=now.getTime();
				long timeGap = (nowTime-writetime)/1000/60;  // 밀리세컨드를 분으로 환산
				
				if(timeGap<2){ writeTime = "지금";}
				else if(timeGap>=2 && timeGap<60){writeTime = timeGap+"분전";}
				else if(timeGap>=60 && timeGap<1440){writeTime = timeGap/60+"시간전";}
				else if(timeGap>=1440 && timeGap<43200){writeTime = timeGap/60/24+"일전";}
				else if(timeGap>=43200){writeTime = timeGap/60/24/30+"개월전";}
				
				idto.setMoimNum(rs.getInt("moimNum"));
				idto.setIdx(rs.getInt("idx"));
				idto.setBoardName(rs.getString("boardName"));
				idto.setTitle(rs.getString("title"));
				idto.setWriteTimeStr(writeTime);
				idto.setUserNickname(rs.getString("userNickname"));
				
				list.add(idto);
				
			}
		} catch (Exception e) {
			System.out.println("getLatestBoard()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}	
		
		return list;
		
	}	//getLatestBoard 종료
	

//*********** 오늘의 인기글 얻기 메소드
	public ArrayList<indexDTO> getTodayBoard() {
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";		
		
		ArrayList<indexDTO> list=new ArrayList<indexDTO>();
		
		try {				
		// 데이터 삽입하기
			con=getConn();				
			
			sql ="  SELECT B.name as boardName, A.moimNum, A.idx, A.title, A.regdate AS writeTime, A.writer AS userNickname, "
				+"   (SELECT COUNT(*) FROM comments where moimNum = A.moimNum and idx = A.idx) replyCount " 
				+"	FROM board A, community B "
				+"	WHERE A.moimNum >10000 AND A.moimNum = B.num "
				+"  AND A.regdate > current_date() "
				+"	ORDER BY replyCount DESC "
				+"  LIMIT 10";
			
			pstmt=con.prepareStatement(sql);			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				indexDTO idto=new indexDTO();
				
				// 시간 형식 바꾸기				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm"); 
							
				String writeTime=sdf.format(rs.getTimestamp("writeTime"));				
				
				Date now = new Date();				
				Date wTime = sdf.parse(writeTime);
				long writetime = wTime.getTime();
				long nowTime=now.getTime();
				long timeGap = (nowTime-writetime)/1000/60;  // 밀리세컨드를 분으로 환산
				
				if(timeGap<2){ writeTime = "지금";}
				else if(timeGap>=2 && timeGap<60){writeTime = timeGap+"분전";}
				else if(timeGap>=60 && timeGap<1440){writeTime = timeGap/60+"시간전";}
				else if(timeGap>=1440 && timeGap<43200){writeTime = timeGap/60/24+"일전";}
				else if(timeGap>=43200){writeTime = timeGap/60/24/30+"개월전";}
				
				idto.setMoimNum(rs.getInt("moimNum"));
				idto.setIdx(rs.getInt("idx"));
				idto.setBoardName(rs.getString("boardName"));
				idto.setTitle(rs.getString("title"));
				idto.setWriteTimeStr(writeTime);
				idto.setUserNickname(rs.getString("userNickname"));
				idto.setReplyCount(rs.getString("replyCount"));
				
				list.add(idto);
				
			}
		} catch (Exception e) {
			System.out.println("getTodayBoard()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}	
		
		return list;
		
	}	//getTodayBoard 종료	
	
	
	
	
//*********** 커뮤니티 top6게시판 가져오기
	public ArrayList<indexDTO> getcommTop6() {
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";		
		
		ArrayList<indexDTO> list=new ArrayList<indexDTO>();
		
		try {				
		// 데이터 삽입하기
			con=getConn();				
			
			sql ="  SELECT A.name as boardName, A.num as moimNum, "
				+"   (SELECT COUNT(*) FROM board where moimNum = A.num) as contentCount " 
				+"	FROM community A "
				+"	ORDER BY contentCount DESC "
				+"  limit 6 ";
			
			pstmt=con.prepareStatement(sql);			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				indexDTO idto=new indexDTO();
				
				idto.setBoardName(rs.getString("boardName"));
				idto.setContentCount(rs.getInt("contentCount"));
				idto.setMoimNum(rs.getInt("moimNum"));
				
				list.add(idto);
				
			}
		} catch (Exception e) {
			System.out.println("getcommTop6()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}	
		
		return list;
		
	}	//getTodayBoard 종료		
	
	
	
	

	
} // DAO종료
