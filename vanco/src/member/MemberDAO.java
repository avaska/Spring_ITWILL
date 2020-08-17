package member;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.*;
import javax.sql.DataSource;

import timeLine.timeLineDTO;

public class MemberDAO {
	
	//*********** getConn()생성 : 커넥션풀로 부터 커넥션 객체con을 만들기 위한 메소드. 	
	private Connection getConn() throws Exception{
	
		Connection con = null;
		Context init = new InitialContext();

		// 커넥션 풀 얻기(context.xml파일의 <Resource> 태그의 name정보로 가져옴)
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/vanco");
		con = ds.getConnection();
		return con;
	}// getConn() 종료

	
	//*********** userLogin() 생성 : 회원 로그인 메소드
		// 리턴값 : 로그인성공(1), 비번틀림(0), ID없음(-1)
	public int userLogin(String userId, String userPwd) {
		int logResult=-1;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			con = getConn();
			sql="select * from vancomember where userId=?";
			pstmt=con.prepareStatement(sql);			
			
			pstmt.setString(1,userId);
			rs=pstmt.executeQuery();	
			
			if(rs.next()){//id값이 있으면(true) => 비번 비교하는 로그인 검사 실행
				if(userPwd.equals(rs.getString("userPwd"))){
					logResult=1;
				}else{
					logResult=0;
				}
			}else{
				logResult=-1;
			}			
		} catch (Exception e) {
			System.out.println("userLogin()에러"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}		
		return  logResult;
	} //userLogin() 종료
	
	
	//*********** idUserJoin() 생성 : 회원 가입 메소드	
	public String idUserJoin(MemberDTO mdto){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		String userPhoto="";
		
		
		try {
			con=getConn();
			sql="insert into vancomember(userId, userPwd, userNickname,joinDate,userGender,userPhoto) values(?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			
			// ?값 셋팅
			pstmt.setString(1,mdto.getUserId());
			pstmt.setString(2,mdto.getUserPwd());
			pstmt.setString(3,mdto.getUserNickname());
			pstmt.setTimestamp(4,mdto.getJoinDate());
			pstmt.setString(5,mdto.getUserGender());
			
			System.out.println(mdto.getUserId());
			
			if(mdto.getUserGender().equals("남")){
			pstmt.setString(6,"/vc/imageProfile/defaultMan.jpg");
			userPhoto="/vc/imageProfile/defaultMan.jpg";
			}else{
			pstmt.setString(6,"/vc/imageProfile/defaultWoman.jpg");
			userPhoto="/vc/imageProfile/defaultWoman.jpg";
			}
			
			// 쿼리 실행
			pstmt.executeUpdate();	
			pstmt.close();
			
			//회원가입 성공 후 바로 로그인 시키기			
			sql = "select userPwd from vancomember where userId=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,mdto.getUserId());
			rs=pstmt.executeQuery();			
			
			if(rs.next()){//id값이 있으면(true) => 비번 비교하는 로그인 검사 실행
				if(mdto.getUserPwd().equals(rs.getString("userPwd"))){
					
				}else{
					System.out.println("비번 틀림");
				}
			}else{
				System.out.println("아이디 없음");
			}					
		} catch (Exception e) {
			System.out.println("idUserJoin()오류"+e);
			e.printStackTrace();
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}
		return userPhoto;
	} // idUserJoin() 종료
	
	

	
//*********** getUserInfo() 생성 :(사용자 id로) myPage에서 모든 user정보 가져옴., 그외 session정보로만 부족한 개인 정보 필요할때 당겨씀
			// 로그인시에는 id값만 세션에 저장함, 사진/닉네임/나이 등정보는 여기서 끌고가기.
		
		public MemberDTO getUserInfo(String userId){
			
			Connection con = null;
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			String sql = "";
			MemberDTO mdto=new MemberDTO();
						
			try {
				con = getConn();
				sql="select * from vancomember where userId=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1,userId);
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					mdto=new MemberDTO();
					
					mdto.setUserNum(rs.getInt("userNum"));
					mdto.setUserId(rs.getString("userId"));
					mdto.setUserNickname(rs.getString("userNickname"));
					mdto.setUserPwd(rs.getString("userPwd"));
					mdto.setUserPhoto(rs.getString("userPhoto"));
					mdto.setDogPhoto(rs.getString("dogPhoto"));
					mdto.setUserCity(rs.getString("userCity"));
					mdto.setUserDistrict(rs.getString("userDistrict"));
					mdto.setUserBirth(rs.getInt("userBirth"));
					mdto.setUserGender(rs.getString("userGender"));
					mdto.setUserPosition(rs.getString("userPosition"));
					mdto.setUserComment(rs.getString("userComment"));	
					mdto.setJoinDate(rs.getTimestamp("joinDate"));
				}			
			} catch (Exception e) {
				System.out.println("getUserInfo()오류"+e);
			}finally{
				if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
				if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
				if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
			}		
			return mdto;
		} //getUserInfo() 종료
		


//*********** editUserInfo() 생성 : 마이페이지에서 정보 수정하느 메소드, 성공하면 1을 리턴
	
	public int editUserInfo(MemberDTO mdto){
		
		Connection con = null;
		PreparedStatement pstmt=null;
		String sql = "";
		int result=0;
								
		try {
			con = getConn();
			sql="update vancomember set userNickname=?, userPwd=?, userBirth=?,userCity=?, userDistrict=?, userPosition=?, userComment=? where userid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,mdto.getUserNickname());
			pstmt.setString(2,mdto.getUserPwd());
			pstmt.setInt(3,mdto.getUserBirth());
			pstmt.setString(4,mdto.getUserCity());
			pstmt.setString(5,mdto.getUserDistrict());
			pstmt.setString(6,mdto.getUserPosition());
			pstmt.setString(7,mdto.getUserComment());
			pstmt.setString(8,mdto.getUserId());
			pstmt.executeUpdate();
			
			result=1;
										
		} catch (Exception e) {
			System.out.println("editUserInfo()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
		}		
	
		return result;
	} //editUserInfo() 종료

		
	
	
//*********** getSessionInfo() 생성 : 인덱스 화면에서 session값에 필요한 정보를 모두 추가해두는 메소드
		// 로그인시에는 id값만 세션에 저장함, 사진/닉네임/나이 등 추가입력 정보는 index에서 db접속하여 추가 저장 하면 되???
	
	public ArrayList<MemberDTO> getSessionInfo(String userId){
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<MemberDTO> sessionList = new ArrayList<MemberDTO>();
		
		try {
			con = getConn();
			sql="select * from vancomember where userId=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,userId);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				MemberDTO mdto=new MemberDTO();
				
				mdto.setUserNum(rs.getInt("userNum"));
				mdto.setUserId(rs.getString("userId"));
				mdto.setUserNickname(rs.getString("userNickname"));
				mdto.setUserPwd(rs.getString("userPwd"));
				mdto.setUserPhoto(rs.getString("userPhoto"));
				mdto.setDogPhoto(rs.getString("dogPhoto"));
				mdto.setUserCity(rs.getString("userCity"));
				mdto.setUserDistrict(rs.getString("userDistrict"));
				mdto.setUserBirth(rs.getInt("userBirth"));
				mdto.setUserGender(rs.getString("userGender"));
				mdto.setUserPosition(rs.getString("userPosition"));
				mdto.setUserComment(rs.getString("userComment"));	
				mdto.setJoinDate(rs.getTimestamp("joinDate"));
				
				sessionList.add(mdto);
			}			
		} catch (Exception e) {
			System.out.println("getSessionInfo()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
		}		
		return sessionList;
	} //getSessionInfo() 종료
	
	
	
	//*********** icCheck() 생성 : 회원가입 시 id 중복 확인 메소드 (중복시 0리턴, 가입가능1리턴)
	
	public int idCheck(String userId){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		int check = 1;
		
		try {
			con = getConn();
			sql="select * from vancomember where userId=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,userId);
			rs=pstmt.executeQuery();
			
			if(rs.next()){// 값이 있으면 (중복), 	
				check=0;	
			}else{ //없으면 가입 가능
				check=1;
			}			
		} catch (Exception e) {
			System.out.println("icCheck()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
		}		
		
		return check;
	}  // icCheck()끝
	
	
//*********** editMyPic() 생성 : 마이페이지 사진 수정	
	public int editMyPic(String dogPhoto, String userPhoto, String userId){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		int result=0;
		
		try {
			con=getConn();
			sql="update vancomember set dogPhoto=?, userPhoto=? where userId=?";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1,dogPhoto);
			pstmt.setString(2,userPhoto);
			pstmt.setString(3,userId);
			
			// 쿼리 실행
			pstmt.executeUpdate();	
			
			result=1;
					
		} catch (Exception e) {
			System.out.println("editMyPic()오류"+e);
			e.printStackTrace();
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}
		}
		
		return result;
	} // editMyPic() 종료	
	

//*********** snsLoginIdChk() 생성 : 회원 로그인 메소드
	// 리턴값 : true(아이디없음), false(아이디있음)
public boolean snsLoginIdChk(String userId) {
		
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	boolean idChk=false;
	
	try {
		con = getConn();
		sql="select userId from vancomember where userId=?";
		pstmt=con.prepareStatement(sql);			
		
		pstmt.setString(1,userId);
		rs=pstmt.executeQuery();	
		
		if(rs.next()){//id값이 있으면(true) => idChk=false
			idChk=true;
		}			
	} catch (Exception e) {
		System.out.println("snsLoginIdChk에러"+e);
	}finally{
		if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
		if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
		if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
	}		
	return  idChk;
} //snsLoginIdChk() 종료
	
//*********** snsUserLogin() 생성 : SNS회원 로그인 메소드
	// 리턴값 : 로그인성공(1), 비번틀림(0), ID없음(-1)
	public int snsUserLogin(String userId) {
	int logResult=-1;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	try {
		con = getConn();
		sql="select * from vancomember where userId=?";
		pstmt=con.prepareStatement(sql);			
		
		pstmt.setString(1,userId);
		rs=pstmt.executeQuery();	
		
		if(rs.next()){
			
			logResult=1;
		}			
	} catch (Exception e) {
		System.out.println("snsUserLogin()에러"+e);
	}finally{
		if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
		if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
		if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
	}		
	return  logResult;
	} //snsUserLogin() 종료


	
//*********** snsUserJoin() 생성 : 회원 가입 메소드	
	public int snsUserJoin(MemberDTO mdto){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		int logResult=-1;
		
		try {
			con=getConn();
			sql="insert into vancomember(userId, userPwd, userNickname,joinDate,userPhoto) values(?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			
			// ?값 셋팅
			pstmt.setString(1,mdto.getUserId());
			pstmt.setString(2,mdto.getUserPwd());
			pstmt.setString(3,mdto.getUserNickname());
			pstmt.setTimestamp(4,mdto.getJoinDate());
			pstmt.setString(5,mdto.getUserPhoto());
			
			System.out.println(mdto.getUserId());
			
			/*if(mdto.getUserGender().equals("남")){
			pstmt.setString(6,"/vanco/imageProfile/defaultMan.jpg");
			userPhoto="/vanco/imageProfile/defaultMan.jpg";
			}else{
			pstmt.setString(6,"/vanco/imageProfile/defaultWoman.jpg");
			userPhoto="/vanco/imageProfile/defaultWoman.jpg";
			}*/
			
			// 쿼리 실행
			pstmt.executeUpdate();	
			pstmt.close();
			
			//회원가입 성공 후 바로 로그인 시키기			
			sql = "select userPwd from vancomember where userId=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,mdto.getUserId());
			rs=pstmt.executeQuery();			
			
			if(rs.next()){//id값이 있으면(true) => 비번 비교하는 로그인 검사 실행
				if(mdto.getUserPwd().equals(rs.getString("userPwd"))){
					logResult=1;
				}else{
					logResult=0;
				}
			}else{
				logResult=-1;
			}					
		} catch (Exception e) {
			System.out.println("snsUserJoin()오류"+e);
			e.printStackTrace();
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}
		return logResult;
	} // snsUserJoin() 종료


//*********** getMyTimePic() : 마이페이지에서 내 사진 가져오는 함수	

	public ArrayList<timeLineDTO> getMyTimePic(String userId){
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";	
		ArrayList<timeLineDTO> list = new ArrayList<timeLineDTO>();
		timeLineDTO tdto = null;
					
		try {
			con = getConn();
			sql="select * from timelinephoto where userId=? ORDER BY photoNum";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,userId);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				tdto = new timeLineDTO();
				
				tdto.setPhotoNum(rs.getInt("photoNum"));
				tdto.setPhotoUrl(rs.getString("photoUrl"));
				tdto.setTimeLineNum(rs.getInt("timeLineNum"));
					
				list.add(tdto);
				
			}			
		} catch (Exception e) {
			System.out.println("getMyTimePic()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
		}		
		return list;
	} //getMyTimePic() 종료

	

	
	
	
	
} //DAO 클래스 종료
