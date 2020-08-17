package neighborComm;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


import member.MemberDAO;
import member.MemberDTO;

public class moimDAO {

//*********** getConn()���� : Ŀ�ؼ�Ǯ�� ���� Ŀ�ؼ� ��ücon�� ����� ���� �޼ҵ�. 	
	private Connection getConn() throws Exception{
	
		Connection con = null;
		Context init = new InitialContext();

		// Ŀ�ؼ� Ǯ ���(context.xml������ <Resource> �±��� name������ ������)
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/vanco");
		con = ds.getConnection();
		return con;
	} // getConn() ����
	
	

//*********** insertThunderUser() ���� : ����ڰ� ���� �����ϴ� �Լ�
	public void insertThunderUser(String userId, String moimNum, String thunderNum) {
	
		Connection con = null;
		PreparedStatement pstmt=null;		
		String sql = "";
		
		try {				
		// ������ �����ϱ�
			con = getConn();
			
			// ����� �߰����� ��������
			MemberDAO mdao = new MemberDAO();
			MemberDTO mdto = new MemberDTO();
					
			mdto = mdao.getUserInfo(userId);			
			
			sql="insert into thunderuser(thunderNum, moimNum, userId, userNickname, userPhoto) values(?,?,?,?,?)";			
			
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, thunderNum);
			pstmt.setString(2, moimNum);
			pstmt.setString(3, userId);
			pstmt.setString(4, mdto.getUserNickname());			
			pstmt.setString(5, mdto.getUserPhoto());
						
			int result = pstmt.executeUpdate();
			
			System.out.println("�μ�Ʈ ���"+1);
			
		} catch (Exception e) {
			System.out.println("insertThunderUser()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
		}			
	} //insertThunderUser()����


//*********** deleteThunderUser() ���� : ����ڰ� ���� ������ ���� �Լ�
	public void deleteThunderUser(String userId, String moimNum, String thunderNum) {
		
		Connection con = null;
		PreparedStatement pstmt=null;		
		String sql = "";
		
		try {				
			con = getConn();
						
			MemberDAO mdao = new MemberDAO();
			sql="delete from thunderuser where thunderNum=? and moimNum=? and userId=? ";			
			
			pstmt=con.prepareStatement(sql);
			
			System.out.println(userId+"���̿�");
			
			pstmt.setString(1, thunderNum);
			pstmt.setString(2, moimNum);
			pstmt.setString(3, userId);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("deleteThunderUser()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
		}			
	} //deleteThunderUser()����


//*********** getThunderUser() ���� : ������ ������ ����� ������ �������� �Լ�
	public ArrayList<thunderUserDTO> getThunderUser(String moimNum, String thunderNum) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";		
		ArrayList<thunderUserDTO> list = new ArrayList<thunderUserDTO>();
				
		try {
			con = getConn();
			sql="select * from thunderuser where moimNum=? and thunderNum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,moimNum);
			pstmt.setString(2,thunderNum);
			
			rs=pstmt.executeQuery();	
			
			while(rs.next()){				
				
				thunderUserDTO tdto=new thunderUserDTO();
				tdto.setUserId(rs.getString("userId"));
				tdto.setUserNickname(rs.getString("userNickname"));
				tdto.setUserPhoto(rs.getString("userPhoto"));
				tdto.setThunderNum(rs.getInt("thunderNum"));
				
				list.add(tdto);			
			}			
		} catch (Exception e) {
			System.out.println("getThunderUser()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
		}		
		return list;
		
	} //getThunderUser()����
	
	
	
	
//*********** getThunderJoinCount() 번개 참여 인원 정보 가져오기
	public ArrayList<thunderDTO> getThunderJoinCount(String moimNum, String thunderNum) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";				
		ArrayList<thunderDTO> list = new ArrayList<thunderDTO>();		
		
		try {
			con = getConn();
			sql="SELECT thunderPerson, "
					+ "(SELECT COUNT(*) FROM thunderuser WHERE moimNum=? AND thunderNum=?) AS thunderJoinCount  "
					+ "FROM thunder  "
					+ "WHERE moimNum=? AND thunderNum=?";
					
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,moimNum);
			pstmt.setString(2,thunderNum);
			pstmt.setString(3,moimNum);
			pstmt.setString(4,thunderNum);
			
			rs=pstmt.executeQuery();	
			
			while(rs.next()){				
				thunderDTO tdto = new thunderDTO();
				
				tdto.setThunderPerson(rs.getString("thunderPerson"));
				tdto.setThunderJoinCount(rs.getInt("thunderJoinCount"));
				
				list.add(tdto);				
			}			
		} catch (Exception e) {
			System.out.println("getThunderJoinCount()오류"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
			
		}		
		return list;
		
	} //getThunderJoinCount()


//*********** insertMoimMember() ���� : ȸ���� ���ӿ� �����ϴ� �Լ�
	public int insertMoimMember(String moimNum, String userId, int maxQuota) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";
		moimMemberDTO mdto = new moimMemberDTO();	
		int result=0;
		int caution=0;
		int memberCount=0;
		
						
		try {
			con = getConn();
			sql="select userId from moimblacklist where moimNum=? and userId=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,moimNum);
			pstmt.setString(2,userId);		
			
			rs=pstmt.executeQuery();	
						
			// �ش� ���̵� ���ӿ��� ����� ����� ������ ���� �Ұ�
			if(rs.next()){			
				result = -1;
			}else{
				// �����ʰ� ���� Ȯ�� �� ���� �̴޽� ���� ����				
				pstmt.close();
				rs.close();
				
				sql="select count(*) from moimmember where moimNum=?";	// ���̺��� �� ���� ���� �Լ�
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1,moimNum);
				rs = pstmt.executeQuery();				
				while(rs.next()){
					memberCount=rs.getInt(1);
				}
				
				System.out.println(maxQuota);
				System.out.println(memberCount);
				if(memberCount>=maxQuota){
					result=0;
				}else{
					pstmt.close();
					sql="insert into moimmember(moimNum, userId, userLevel) values(?,?,?)";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1,moimNum);
					pstmt.setString(2,userId);	
					pstmt.setInt(3,-1);	// ���� �� �̽��� ������ ȸ���� level -1���� ����
					pstmt.executeUpdate();
					result=1;
				}
			}
						
						
		} catch (Exception e) {
			System.out.println("insertMoimMember()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
		}		
		return result;	
		
	} //insertMoimMember ����


//*********** getMoimMember() ���� : ���Ӹ�� ������ �������� �Լ�
	public ArrayList<moimMemberDTO> getMoimMember(String moimNum) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";		
		ArrayList<moimMemberDTO> list = new ArrayList<moimMemberDTO>();
				
		try {
			con = getConn();
			sql="select * from moimmember NATURAL JOIN vancomember where moimNum=? and userLevel>0 order by userLevel DESC";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,moimNum);
			
			rs=pstmt.executeQuery();				
			while(rs.next()){							
				moimMemberDTO mdto=new moimMemberDTO();
				//���Ӹ�� ���̺� �κ�
				mdto.setMoimMemberNum(rs.getInt("moimMemberNum"));
				mdto.setMoimNum(rs.getInt("moimNum"));
				mdto.setUserId(rs.getString("userId"));
				mdto.setUserLevel(rs.getInt("userLevel"));
				mdto.setCaution(rs.getInt("caution"));
				
				//���ڸ�� ���̺� �κ�
				mdto.setUserNickname(rs.getString("userNickname"));
				mdto.setUserPhoto(rs.getString("userPhoto"));
				mdto.setDogPhoto(rs.getString("dogPhoto"));
				mdto.setUserCity(rs.getString("userCity"));
				mdto.setUserDistrict(rs.getString("userDistrict"));
				mdto.setUserBirth(rs.getInt("userBirth"));
				mdto.setUserGender(rs.getString("userGender"));
				mdto.setUserPosition(rs.getString("userPosition"));
				mdto.setUserComment(rs.getString("userComment"));
				
				list.add(mdto);			
			}			
		} catch (Exception e) {
			System.out.println("getMoimMember()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
		}		
		return list;		
	} //getMoimMember()����

	
	
	
//*********** getWaitingMember() ���� : ���δ�� ��� ������ �������� �Լ�
	public ArrayList<moimMemberDTO> getWaitingMember(String moimNum) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";		
		ArrayList<moimMemberDTO> list = new ArrayList<moimMemberDTO>();
				
		try {
			con = getConn();
			sql="select * from moimmember NATURAL JOIN vancomember where moimNum=? and userLevel=-1";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,moimNum);
			
			rs=pstmt.executeQuery();				
			while(rs.next()){							
				moimMemberDTO mdto=new moimMemberDTO();
				//���Ӹ�� ���̺� �κ�
				mdto.setMoimMemberNum(rs.getInt("moimMemberNum"));
				mdto.setMoimNum(rs.getInt("moimNum"));
				mdto.setUserId(rs.getString("userId"));
				mdto.setUserLevel(rs.getInt("userLevel"));
				mdto.setCaution(rs.getInt("caution"));
				
				//���ڸ�� ���̺� �κ�
				mdto.setUserNickname(rs.getString("userNickname"));
				mdto.setUserPhoto(rs.getString("userPhoto"));
				mdto.setDogPhoto(rs.getString("dogPhoto"));
				mdto.setUserCity(rs.getString("userCity"));
				mdto.setUserDistrict(rs.getString("userDistrict"));
				mdto.setUserBirth(rs.getInt("userBirth"));
				mdto.setUserGender(rs.getString("userGender"));
				mdto.setUserPosition(rs.getString("userPosition"));
				mdto.setUserComment(rs.getString("userComment"));
				
				list.add(mdto);			
			}			
		} catch (Exception e) {
			System.out.println("getWaitingMember()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
		}		
		return list;		
	} //getWaitingMember()����
	
	
//*********** getMoimMemberCount() ���� : ���Ӹ�� ��� ���� �Լ�
	public int getMoimMemberCount(String moimNum) {
	
		Connection con = null;
		PreparedStatement pstmt=null;		
		String sql = "";
		ResultSet rs = null;
		int memberCount=0;
		
		try {				
			con = getConn();
					
			
			sql="select count(*) from moimmember where moimNum=? and userLevel>0";	// ���̺��� �� ���� ���� �Լ�
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,moimNum);
			rs = pstmt.executeQuery();				
			while(rs.next()){
				memberCount=rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println("getMoimMemberCount()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}	
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
		}	
		
		return memberCount;
	} //getMoimMemberCount()����


//*********** updateMemberLevel() ���� : ��� ���� �� �ϴ� �Լ�
	public void updateMemberLevel(String userId, int level, int moimNum) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;		
		String sql = "";		
		try {
			con=getConn();				
			
			if(level==1){			
				sql="update moimmember set userLevel=? where moimNum=? and userId=?";
				pstmt=con.prepareStatement(sql);			
				pstmt.setInt(1, 2);
				pstmt.setInt(2, moimNum);
				pstmt.setString(3, userId);
				pstmt.executeUpdate();
			}else{
				sql="update moimmember set userLevel=? where moimNum=? and userId=?";
				pstmt=con.prepareStatement(sql);			
				pstmt.setInt(1, 1);
				pstmt.setInt(2, moimNum);
				pstmt.setString(3, userId);
				pstmt.executeUpdate();	
			}
			
		} catch (Exception e) {
			System.out.println("updateMemberLevel()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}	
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
		}			
		
	} //updateMemberLevel()����
	


//*********** getMyLevel() ���� : ���δ�� ��� ������ �������� �Լ�
	public int getMyLevel(String userId, String moimNum) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";	
		int myLevel=0;
				
		try {
			con = getConn();
			sql="select userLevel from moimmember where moimNum=? and userId=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,moimNum);
			pstmt.setString(2,userId);
			
			
			rs=pstmt.executeQuery();				
			while(rs.next()){
				myLevel = rs.getInt("userLevel");
			}			
		} catch (Exception e) {
			System.out.println("getMyLevel()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
		}		
		return myLevel;		
	} //getMyLevel()����


//*********** deleteJoinWait() ���� : ���δ�� ��� �����ϴ� �Լ�
	public void deleteJoinWait(String userId, int moimNum) {
		Connection con = null;
		PreparedStatement pstmt=null;		
		String sql = "";
		
		try {				
			con = getConn();						
			MemberDAO mdao = new MemberDAO();
			sql="delete from moimmember where moimNum=? and userId=? ";			
			
			pstmt=con.prepareStatement(sql);
						
			pstmt.setInt(1, moimNum);
			pstmt.setString(2, userId);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("deleteJoinWait()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
		}			
	}//deleteJoinWait()����


	
//*********** updateMemberLevel2() ���� : ���δ�� ��� ���Խ��� �ϴ� �Լ�
	public void updateMemberLevel2(String userId, int moimNum) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;		
		String sql = "";		
		try {
			con=getConn();				
					
				sql="update moimmember set userLevel=1 where moimNum=? and userId=?";
				pstmt=con.prepareStatement(sql);			
				
				pstmt.setInt(1, moimNum);
				pstmt.setString(2, userId);
				pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateMemberLevel2()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}	
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
		}			
	}//updateMemberLevel2()����
	
	
//*********** deleteForcedMoimMember() ���� : ���� ���� �� ���� �� ������Ʈ �ø��� �Լ�
	public void deleteForcedMoimMember(String userId, int moimNum) {
		Connection con = null;
		PreparedStatement pstmt=null;		
		String sql = "";
		
		try {				
			con = getConn();						
			
			// ��� �����ϱ�
			sql="delete from moimmember where moimNum=? and userId=? ";		
			pstmt=con.prepareStatement(sql);						
			pstmt.setInt(1, moimNum);
			pstmt.setString(2, userId);			
			pstmt.executeUpdate();
			
			//��� ������Ʈ �ø���
			sql="insert into moimblacklist(moimNum, userId) values(?,?)";			
			pstmt.close();			
			pstmt=con.prepareStatement(sql);			
			pstmt.setInt(1, moimNum);
			pstmt.setString(2, userId);			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("deleteForcedMoimMember()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
		}			
	}//deleteForcedMoimMember()����
	

//*********** updateCaution() ���� : ��� ������ �Լ�
	public void updateCaution(String userId, int moimNum, int caution) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;		
		String sql = "";		
		try {
			con=getConn();				
					
				if(caution<2){
			
				sql="update moimmember set caution=? where moimNum=? and userId=?";
				pstmt=con.prepareStatement(sql);				
				pstmt.setInt(1, caution+1);
				pstmt.setInt(2, moimNum);
				pstmt.setString(3, userId);
				pstmt.executeUpdate();
				}else{
					moimDAO mdao = new moimDAO();
					mdao.deleteForcedMoimMember(userId, moimNum);					
				}
		} catch (Exception e) {
			System.out.println("updateCaution()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}	
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
		}			
	}//updateCaution()����	
	
	
	
	
	
	
	
	
	
} // DAOŬ���� ����
