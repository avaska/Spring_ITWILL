package timeLine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class timeLineDAO {

//*********** getConn()생성 : 커넥션풀로 부터 커넥션 객체con을 만들기 위한 메소드. 	
	private Connection getConn() throws Exception{
	
		Connection con = null;
		Context init = new InitialContext();

		// 커넥션 풀 얻기(context.xml파일의 <Resource> 태그의 name정보로 가져옴)
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/vanco");
		con = ds.getConnection();
		return con;
	} // getConn() 종료
	
	

//*********** getTimeLine() 생성 : 타임라인 접속 시 타임라인 본문 가져오는 메소드
	public ArrayList<timeLineDTO> getTimeLine() {
	
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";		
		
		ArrayList<timeLineDTO> list=new ArrayList<timeLineDTO>();
		
		try {				
		// 데이터 삽입하기
			con=getConn();				
			
			sql ="SELECT A.*, B.userNickname, B.userPhoto, B.userCity, B.userDistrict, "	
				+ "(SELECT COUNT(*) from timelinephoto C where A.timeLineNum = C.timeLineNum) AS photoCount "
				+ "FROM timeline A, vancomember B, timelinephoto C "
				+ "WHERE A.userId = B.userId "
				+ "GROUP BY A.timeLineNum "
				+ "ORDER BY A.writeTime DESC "
				+ "limit 3";
			
			pstmt=con.prepareStatement(sql);			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				timeLineDTO tdto=new timeLineDTO();
				
				// 본문글 엔터 처리 하기(JSTL 사용시 자바단에서 처리 하는 것이 편리함)
				String content = "";
				if(rs.getString("content")!=null){
					content = rs.getString("content").replace("\r\n","<br>").replace("\t","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				}
				
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
				
				
				tdto.setTimeLineNum(rs.getInt("timeLineNum"));
				tdto.setTitle(rs.getString("title"));
				tdto.setContent(content);
				tdto.setWriteTimeStr(writeTime);
				tdto.setUserId(rs.getString("userId"));	
				tdto.setUserPhoto(rs.getString("userPhoto"));	
				tdto.setUserCity(rs.getString("userCity"));	
				tdto.setUserDistrict(rs.getString("userDistrict"));	
				tdto.setUserNickname(rs.getString("userNickname"));	
				tdto.setPhotoCount(rs.getInt("photoCount"));	
				
				list.add(tdto);
			}
		} catch (Exception e) {
			System.out.println("getTimeLine()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}	
		
		return list;
	} //getTimeLine()종료

	

//*********** getTimeLineInfo() 생성 : 타임라인 접속 시 타임라인 본문 가져오는 메소드
	public ArrayList<timeLineDTO> getTimeLineInfo(int startPage, int pageCount) {
	
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";		
		
		ArrayList<timeLineDTO> list=new ArrayList<timeLineDTO>();
		
		try {				
		// 데이터 삽입하기
			con=getConn();				
			
			sql ="SELECT A.*, B.userNickname, B.userPhoto, B.userCity, B.userDistrict, "
				+ "(SELECT COUNT(*) from timelinereply C where A.timeLineNum = C.timeLineNum) AS replyCount "
				+ "FROM timeline A, vancomember B, timelinereply C "
				+ "WHERE A.userId = B.userId "
				+ "GROUP BY A.timeLineNum "
				+ "ORDER BY A.writeTime DESC "
				+ "limit ?,?";
			
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startPage);
			pstmt.setInt(2, pageCount);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				timeLineDTO tdto=new timeLineDTO();
				
				// 본문글 엔터 처리 하기(JSTL 사용시 자바단에서 처리 하는 것이 편리함)
				String content = "";
				if(rs.getString("content")!=null){
					content = rs.getString("content").replace("\r\n","<br>").replace("\t","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				}
				
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
				
				
				tdto.setTimeLineNum(rs.getInt("timeLineNum"));
				tdto.setTitle(rs.getString("title"));
				tdto.setContent(content);
				tdto.setWriteTimeStr(writeTime);
				tdto.setUserId(rs.getString("userId"));	
				tdto.setUserPhoto(rs.getString("userPhoto"));	
				tdto.setUserCity(rs.getString("userCity"));	
				tdto.setUserDistrict(rs.getString("userDistrict"));	
				tdto.setUserNickname(rs.getString("userNickname"));	
				tdto.setReplyCount(rs.getInt("replyCount"));
				
				list.add(tdto);
			}
		} catch (Exception e) {
			System.out.println("getTimeLine()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}	
		
		return list;
	} //getTimeLine()종료	
	
	
	

//*********** getTimePhoto() 생성 : 타임라인 접속 시 타임라인 사진 1장만 가져오는 메소드
	public ArrayList<timeLineDTO> getTimePhoto() {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";
			
		ArrayList<timeLineDTO> photoList=new ArrayList<timeLineDTO>();
		
		try {				
		// 데이터 삽입하기
			con=getConn();				
			
			sql ="SELECT A.photoUrl, A.timeLineNum, A.userId "
				+ "FROM timelinephoto A, timeline B "
				+ "WHERE A.userId = B.userId "
				+ "AND A.timeLineNum = B.timeLineNum "
				+ "GROUP BY timeLineNum "
				+ "ORDER BY A.photoNum;";
			
			pstmt=con.prepareStatement(sql);			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				timeLineDTO tdto=new timeLineDTO();
				
				tdto.setTimeLineNum(rs.getInt("timeLineNum"));
				tdto.setPhotoUrl(rs.getString("photoUrl"));	
				tdto.setUserId(rs.getString("userId"));
								
				photoList.add(tdto);
			}
		} catch (Exception e) {
			System.out.println("getTimePhoto()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}	
			if(con!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}	
		
		return photoList;
	}//getTimePhoto 종료
	

//*********** getTimePhotoCount() 생성 : 타임라인 접속 시 타임라인 사진 가져오는 메소드
	public ArrayList<timeLineDTO> getTimePhotoCount() {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";
			
		ArrayList<timeLineDTO> photoList=new ArrayList<timeLineDTO>();
		
		try {				
		// 데이터 삽입하기
			con=getConn();				
			
			sql ="SELECT timeLineNum, COUNT(timeLineNum) AS photoCount FROM timelinephoto group by timeLineNum";
							
			pstmt=con.prepareStatement(sql);			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				timeLineDTO tdto=new timeLineDTO();
				
				tdto.setTimeLineNum(rs.getInt("timeLineNum"));
				tdto.setPhotoCount(rs.getInt("photoCount"));	
												
				photoList.add(tdto);
			}
		} catch (Exception e) {
			System.out.println("getTimePhotoCount()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}	
			if(con!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}	
		
		return photoList;
	}//getTimePhotoCount 종료
			
	
//*********** getTimeReply() 생성 : 타임라인 접속 시 타임라인 댓글 가져오는 메소드
	public ArrayList<timeLineDTO> getTimeReply(String timeLineNum) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";
		
		ArrayList<timeLineDTO> replyList=new ArrayList<timeLineDTO>();
		
		try {				
			con=getConn();				
			
			sql = "SELECT A.*, B.userNickname, B.userPhoto "
				+ "FROM timelinereply A, vancomember B  "
				+ "WHERE timeLineNum =? "
				+ "AND A.userId = B.userId "
				+ "ORDER BY re_ref DESC, re_lev, writeTime";
			
			pstmt=con.prepareStatement(sql);	
			pstmt.setString(1, timeLineNum);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				timeLineDTO tdto=new timeLineDTO();
				
				// 시간 형식 바꾸기				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd"); 
							
				String writeTime=sdf.format(rs.getTimestamp("writeTime"));
				String writeTime1=sdf1.format(rs.getTimestamp("writeTime"));
				
				Date now = new Date();				
				Date wTime = sdf.parse(writeTime);
				long writetime = wTime.getTime();
				long nowTime=now.getTime();
				long timeGap = (nowTime-writetime)/1000/60;  // 밀리세컨드를 분으로 환산
				
				if(timeGap<2){ writeTime = "지금";}
				else if(timeGap>=2 && timeGap<60){writeTime = timeGap+"분전";}
				else if(timeGap>=60 && timeGap<1440){writeTime = timeGap/60+"시간전";}
				else{writeTime=writeTime1;}		
								
				//reply  정보
				tdto.setTimeLineReplyNum(rs.getInt("timeLineReplyNum"));
				tdto.setTimeLineNum(rs.getInt("timeLineNum"));
				tdto.setUserId(rs.getString("userId"));
				tdto.setContent(rs.getString("content"));
				tdto.setWriteTimeStr(writeTime);
				tdto.setRe_ref(rs.getInt("re_ref"));
				tdto.setRe_lev(rs.getInt("re_lev"));
				tdto.setRe_seq(rs.getInt("re_seq"));				
				tdto.setReOwnerNick(rs.getString("reOwnerNick"));
				
				// 회원정보에서 가져온 댓글단 사람 정보				
				tdto.setUserPhoto(rs.getString("userPhoto"));	
				tdto.setUserNickname(rs.getString("userNickname"));	
				
				replyList.add(tdto);
			}
		} catch (Exception e) {
			System.out.println("getTimeReply()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}	
			if(con!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}			
		return replyList;
	
	}//getTimeReply()종료


//*********** addLikeCount() 생성 : 좋아요 카운트 추가
	public void addLikeCount(String timeLineNum, String userId, String likeId) {
		Connection con = null;
		PreparedStatement pstmt=null;		
		String sql = "";
		
		try {				
		// 데이터 삽입하기
			con=getConn();				
			
			sql = "insert into timelinelikes(timeLineNum, userId, likeId ) values(?,?,?)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, timeLineNum);
			pstmt.setString(2, userId);
			pstmt.setString(3, likeId);
			pstmt.executeUpdate();			
		
		} catch (Exception e) {
			System.out.println("addLikeCount()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}	
		}	
		
	} //addLikeCount()종료



//*********** delLikeCount() 생성 : 좋아요 카운트 삭제
	public void delLikeCount(String timeLineNum, String likeId) {
		Connection con = null;
		PreparedStatement pstmt=null;		
		String sql = "";
		
		
		try {				
		// 데이터 삽입하기
			con=getConn();				
			
			sql = "delete from timelinelikes where timeLineNum = ? and likeId = ?";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, timeLineNum);
			pstmt.setString(2, likeId);	
			pstmt.executeUpdate();
						
			
		} catch (Exception e) {
			System.out.println("delLikeCount()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
		}	
		
	} //delLikeCount()종료

		
//*********** getLikeCount() 생성 : 좋아요 카운트 삭제
	public int getLikeCount(String timeLineNum) {
		Connection con = null;
		PreparedStatement pstmt=null;		
		String sql = "";
		ResultSet rs = null;
		int likesCount=0;
		
		try {				
			con=getConn();				
		
			// 좋아요 갯수 세기
			sql = "SELECT COUNT(*) FROM timelinelikes where timeLineNum ="+timeLineNum;

			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				likesCount = rs.getInt(1);				
			}
			
		} catch (Exception e) {
			System.out.println("getLikeCount()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}	
			if(con!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}	
		
		return likesCount;
		
	} //getLikeCount()종료
	
	
	
//*********** getReplyCount() 생성 : 좋아요 카운트 삭제
	public int getReplyCount(String timeLineNum) {
		Connection con = null;
		PreparedStatement pstmt=null;		
		String sql = "";
		ResultSet rs = null;
		int replyCount=0;
		
		try {				
			con=getConn();				
		
			// 좋아요 갯수 세기
			sql = "SELECT COUNT(*) FROM timelinereply where timeLineNum ="+timeLineNum;

			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				replyCount = rs.getInt(1);				
			}
			
		} catch (Exception e) {
			System.out.println("getReplyCount()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}	
			if(con!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}	
		
		return replyCount;
		
	} //getReplyCount()종료
	


//*********** getLikeClickId() 생성 : 좋아요 눌렀는지 여부확인
	public int getLikeClickId(String timeLineNum, String likeId) {
		Connection con = null;
		PreparedStatement pstmt=null;		
		String sql = "";
		ResultSet rs = null;
		int clickCheck=0;
		
		try {				
			con=getConn();				
		
			// 좋아요 갯수 세기
			sql = "SELECT likeId FROM timelinelikes where timeLineNum =? and likeId=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,timeLineNum);
			pstmt.setString(2,likeId);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				clickCheck = 1;				
			}
			
		} catch (Exception e) {
			System.out.println("getLikeClickId()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}	
			if(con!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}	
		
		return clickCheck;
		
	} //getLikeClickId()종료



	public void insertTimeLine(timeLineDTO tdto) {
		
		Connection con = null;
		PreparedStatement pstmt=null;		
		String sql = "";
		ResultSet rs = null;
		int timeLineNum = 0;
				
		try {
		// 최근 글번호 가져오기.
			con=getConn();
			sql="select max(timeLineNum)from timeline"; //가장 최근의 글 번호 검색
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				timeLineNum=rs.getInt(1)+1;   // 검색한 최신글번호 +1 을 새글의 글번호로 지정
			}else{
				timeLineNum=1; //(글이 없으면 1번부터 시작)
			}			
			
		// 데이터 삽입하기1 (글정보 타임라인 테이블 삽입)			
			String userId = tdto.getUserId();
			String content = tdto.getContent();				
			
			sql = "insert into timeline(timeLineNum, content, userId) values(?,?,?)";
			pstmt.close();
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, timeLineNum);
			pstmt.setString(2, content);
			pstmt.setString(3, userId);
			pstmt.executeUpdate();	

		// 데이터 삽입하기2 (사진정보 타임라인 사진 테이블 삽입)			
			String photoUrl1 = tdto.getPhotoUrl();
			String photoUrl2 = tdto.getIp();
			String photoUrl3 = tdto.getTitle();
			String photoUrl4 = tdto.getReOwnerNick();

			
			if(!(photoUrl1.equals("/vc/imageUpload/null"))){
				sql = "insert into timelinephoto(timeLineNum, userId, photoUrl) values (?,?,?)";
				
				pstmt.close();
				pstmt=con.prepareStatement(sql);
				
				pstmt.setInt(1, timeLineNum);
				pstmt.setString(2, userId);
				pstmt.setString(3, photoUrl1);
				pstmt.executeUpdate();
			}
			
			if(!(photoUrl2.equals("/vc/imageUpload/null"))){
				sql = "insert into timelinephoto(timeLineNum, userId, photoUrl) values (?,?,?)";
				
				pstmt.close();
				pstmt=con.prepareStatement(sql);
				
				pstmt.setInt(1, timeLineNum);
				pstmt.setString(2, userId);
				pstmt.setString(3, photoUrl2);
				pstmt.executeUpdate();		
			}
			
			if(!(photoUrl3.equals("/vc/imageUpload/null"))){
				sql = "insert into timelinephoto(timeLineNum, userId, photoUrl) values (?,?,?)";
				
				pstmt.close();
				pstmt=con.prepareStatement(sql);
				
				pstmt.setInt(1, timeLineNum);
				pstmt.setString(2, userId);
				pstmt.setString(3, photoUrl3);
				pstmt.executeUpdate();		
			}
			
			if(!(photoUrl4.equals("/vc/imageUpload/null"))){
				sql = "insert into timelinephoto(timeLineNum, userId, photoUrl) values (?,?,?)";
				
				pstmt.close();
				pstmt=con.prepareStatement(sql);
				
				pstmt.setInt(1, timeLineNum);
				pstmt.setString(2, userId);
				pstmt.setString(3, photoUrl4);
				pstmt.executeUpdate();		
			}			
			
			
		
		} catch (Exception e) {
			System.out.println("insertTimeLine()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}	
			if(con!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}			
		
	}//insertTimeLine()종료
		

//*********** insertTimeReply() 생성 : 댓글달기
	public void insertTimeReply(String timeLineNum, String content, String userId) {
		Connection con = null;
		PreparedStatement pstmt=null;		
		String sql = "";
		ResultSet rs = null;
		int timeLineReplyNum = 0;
		
		try {
		// 최근 글번호 가져오기.
			con=getConn();
			sql="select max(timeLineReplyNum)from timelinereply"; //가장 최근의 글 번호 검색
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				timeLineReplyNum=rs.getInt(1)+1;   // 검색한 최신글번호 +1 을 새글의 글번호로 지정
			}else{
				timeLineReplyNum=1; //(글이 없으면 1번부터 시작)
		}				
			
			
		// 데이터 삽입하기						
			sql = "insert into timelinereply(timeLineReplyNum, timeLineNum, content, userId, re_ref) values(?,?,?,?,?)";
			pstmt.close();
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, timeLineReplyNum);
			pstmt.setString(2, timeLineNum);
			pstmt.setString(3, content);
			pstmt.setString(4, userId);
			pstmt.setInt(5, timeLineReplyNum);
			
			pstmt.executeUpdate();			
		
		} catch (Exception e) {
			System.out.println("insertTimeReply()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}	
		
	} //insertTimeReply()종료
	
	

//*********** insertTimeReReply() 생성 : 대댓글달기
	public void insertTimeReReply(String timeLineNum, String re_ref, String content, String userId, String reOwnerNick) {
		Connection con = null;
		PreparedStatement pstmt=null;		
		String sql = "";
		
		try {				
		// 데이터 삽입하기
			con=getConn();	
			
			System.out.println(userId);
			
			sql = "insert into timelinereply(timeLineNum, re_ref, content, userId, reOwnerNick, re_lev) values(?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, timeLineNum);
			pstmt.setString(2, re_ref);
			pstmt.setString(3, content);
			pstmt.setString(4, userId);
			pstmt.setString(5, reOwnerNick);
			pstmt.setInt(6, 1);
			pstmt.executeUpdate();			
		
		} catch (Exception e) {
			System.out.println("insertTimeReReply()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}	
		}	
		
	} //insertTimeReReply()종료	
	
	
//*********** getTimeLineIn() 생성 : 타임라인 접속 시 타임라인 본문 가져오는 메소드
	public timeLineDTO getTimeLineIn(String timeLineNum) {
	
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";		
		timeLineDTO tdto=new timeLineDTO();
		
		try {				
		// 데이터 삽입하기
			con=getConn();				
			
			sql ="SELECT * from timeline natural join vancomember WHERE timeLineNum ="+timeLineNum;
				
			pstmt=con.prepareStatement(sql);			
			rs=pstmt.executeQuery();
			
			while(rs.next()){				
				
				// 본문글 엔터 처리 하기(JSTL 사용시 자바단에서 처리 하는 것이 편리함)
				String content = "";
				if(rs.getString("content")!=null){
					content = rs.getString("content").replace("\r\n","<br>").replace("\t","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				}
				
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
				
				
				tdto.setTimeLineNum(rs.getInt("timeLineNum"));
				tdto.setTitle(rs.getString("title"));
				tdto.setContent(content);
				tdto.setWriteTimeStr(writeTime);
				tdto.setUserId(rs.getString("userId"));	
				tdto.setUserPhoto(rs.getString("userPhoto"));	
				tdto.setUserCity(rs.getString("userCity"));	
				tdto.setUserDistrict(rs.getString("userDistrict"));	
				tdto.setUserNickname(rs.getString("userNickname"));	
								
			}
		} catch (Exception e) {
			System.out.println("getTimeLineIn()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}	
		
		return tdto;
	} //getTimeLineIn()종료
	
	
//*********** getTimeInPhoto() 생성 : 타임라인 접속 시 타임라인 사진 가져오는 메소드
	public ArrayList<timeLineDTO> getTimeInPhoto(String timeLineNum) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";
			
		ArrayList<timeLineDTO> photoList=new ArrayList<timeLineDTO>();
		
		try {				
		// 데이터 삽입하기
			con=getConn();				
			
			sql ="SELECT photoUrl FROM timelinephoto WHERE timeLineNum=? ORDER BY photoNum";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, timeLineNum);
						
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				timeLineDTO tdto=new timeLineDTO();
								
				tdto.setPhotoUrl(rs.getString("photoUrl"));					
								
				photoList.add(tdto);
			}
		} catch (Exception e) {
			System.out.println("getTimeInPhoto()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}	
			if(con!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}			
		return photoList;
	}//getTimeInPhoto 종료
	
	
//*********** getTimeInReply() 생성 : 타임라인 접속 시 타임라인 댓글 가져오는 메소드
	public ArrayList<timeLineDTO> getTimeInReply(String timeLineNum) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";
		
		ArrayList<timeLineDTO> replyList=new ArrayList<timeLineDTO>();
		
		try {				
			con=getConn();				
			
			sql ="SELECT * from timelinereply natural join vancomember "
				+"WHERE timeLineNum=? "
				+"ORDER BY re_ref DESC, re_lev, writeTime ";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, timeLineNum);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				timeLineDTO tdto=new timeLineDTO();
				
				// 시간 형식 바꾸기				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd"); 
							
				String writeTime=sdf.format(rs.getTimestamp("writeTime"));
				String writeTime1=sdf1.format(rs.getTimestamp("writeTime"));
				
				Date now = new Date();				
				Date wTime = sdf.parse(writeTime);
				long writetime = wTime.getTime();
				long nowTime=now.getTime();
				long timeGap = (nowTime-writetime)/1000/60;  // 밀리세컨드를 분으로 환산
				
				if(timeGap<2){ writeTime = "지금";}
				else if(timeGap>=2 && timeGap<60){writeTime = timeGap+"분전";}
				else if(timeGap>=60 && timeGap<1440){writeTime = timeGap/60+"시간전";}
				else{writeTime=writeTime1;}		
								
				//reply  정보
				tdto.setTimeLineReplyNum(rs.getInt("timeLineReplyNum"));
				tdto.setTimeLineNum(rs.getInt("timeLineNum"));
				tdto.setUserId(rs.getString("userId"));
				tdto.setContent(rs.getString("content"));
				tdto.setWriteTimeStr(writeTime);
				tdto.setRe_ref(rs.getInt("re_ref"));
				tdto.setRe_lev(rs.getInt("re_lev"));
				tdto.setRe_seq(rs.getInt("re_seq"));				
				tdto.setReOwnerNick(rs.getString("reOwnerNick"));
				
				// 회원정보에서 가져온 댓글단 사람 정보				
				tdto.setUserPhoto(rs.getString("userPhoto"));	
				tdto.setUserNickname(rs.getString("userNickname"));	
				
				replyList.add(tdto);
			}
		} catch (Exception e) {
			System.out.println("getTimeInReply()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}	
			if(con!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}			
		return replyList;
	
	}//getTimeInReply()종료
	
	
//*********** getTimeImg() 생성 : 타임라인 접속 시 타임라인 사진 1장만 가져오는 메소드
	public timeLineDTO getTimeImg(String timeLineNum) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";		
		timeLineDTO tdto=new timeLineDTO();
	
		//ArrayList<timeLineDTO> photoList=new ArrayList<timeLineDTO>();
		
		try {				
		// 데이터 삽입하기
			con=getConn();				
			
			sql ="SELECT photoUrl, photoNum, COUNT(timeLineNum) AS photoCount "
				+ "FROM timelinephoto "
				+ "WHERE timeLineNum = ? "
				+ "ORDER BY photoNum ";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, timeLineNum);
			rs=pstmt.executeQuery();
			
			
			while(rs.next()){
				
				tdto.setPhotoCount(rs.getInt("photoCount"));
				tdto.setPhotoUrl(rs.getString("photoUrl"));	
				//tdto.setUserId(rs.getString("userId"));
				
				//photoList.add(tdto);
			}
		} catch (Exception e) {
			System.out.println("getTimeImg()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}	
			if(con!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}	
		
		return tdto;
	}//getTimeImg 종료	
	
//*********** getMoreImg() 생성 : 타임라인 사진 더보기 클릭시 나머지 사진 가져오는 메소드
	public ArrayList<timeLineDTO> getMoreImg(String timeLineNum, String photoCount) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";		
		int limit = Integer.parseInt(timeLineNum) -1;
	
		ArrayList<timeLineDTO> list = new ArrayList<timeLineDTO>();
		
		try {				
		// 데이터 삽입하기
			con=getConn();				
			
			sql ="SELECT photoUrl FROM timelinephoto "
				+ "WHERE timeLineNum = ? "
				+ "ORDER BY photoNum "
				+ "limit 1, ?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, timeLineNum);
			pstmt.setInt(2, limit);
			rs=pstmt.executeQuery();
			
			
			while(rs.next()){
				timeLineDTO tdto = new timeLineDTO();
				tdto.setPhotoUrl(rs.getString("photoUrl"));					
				list.add(tdto);
			}
		} catch (Exception e) {
			System.out.println("getMoreImg()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}	
			if(con!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}	
		
		return list;
	}//getMoreImg 종료	
	
	
	
	
	
} // DAO클래스 종료
