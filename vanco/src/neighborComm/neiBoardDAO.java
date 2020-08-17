package neighborComm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import member.MemberDAO;
import member.MemberDTO;

public class neiBoardDAO {
	
	//*********** getConn()���� : Ŀ�ؼ�Ǯ�� ���� Ŀ�ؼ� ��ücon�� ����� ���� �޼ҵ�. 	
	private Connection getConn() throws Exception{
	
		Connection con = null;
		Context init = new InitialContext();

		// Ŀ�ؼ� Ǯ ���(context.xml������ <Resource> �±��� name������ ������)
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/vanco");
		con = ds.getConnection();
		return con;
	} // getConn() ����
	
	
	
	//*********** insertNeiBoard()���� : neiBoardWrite���� �Է¹��� �� DB�� �����ϴ� �޼ҵ�
	public void insertNeiBoard(neiBoardDTO ndto){
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";
		
		//�߰��� �� �ۿ� ���� �� ��ȣ�� ������ ����
		int num = 0;
		
		try {
			
		// �ֱ� �۹�ȣ ��������.
			con=getConn();
			sql="select max(num)from neiboard"; //���� �ֱ��� �� ��ȣ �˻�
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				num=rs.getInt(1)+1;   // �˻��� �ֽű۹�ȣ +1 �� ������ �۹�ȣ�� ����
			}else{
				num=1; //(���� ������ 1������ ����)
			}
				
		// ������ �����ϱ�
			sql="insert into neiboard(num, userId, userNickname, userPhoto, upPhoto1,  upPhoto2,  upPhoto3,  upPhoto4, subject, content, re_ref, re_lev, re_seq, "
					+ "readcount, replycount, writetime, ip) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";			
			
			pstmt.close();
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, ndto.getUserId());
			pstmt.setString(3, ndto.getUserNickname());
			pstmt.setString(4, ndto.getUserPhoto());
			pstmt.setString(5, ndto.getUpPhoto1());
			pstmt.setString(6, ndto.getUpPhoto2());
			pstmt.setString(7, ndto.getUpPhoto3());
			pstmt.setString(8, ndto.getUpPhoto4());
			pstmt.setString(9, ndto.getSubject());
			pstmt.setString(10, ndto.getContent());
			pstmt.setInt(11, num);
			pstmt.setInt(12, ndto.getRe_lev());
			pstmt.setInt(13, ndto.getRe_seq());
			pstmt.setInt(14, ndto.getReadCount());
			pstmt.setInt(15, ndto.getReplyCount());
			pstmt.setTimestamp(16, ndto.getWriteTime());
			pstmt.setString(17, ndto.getIp());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insertNeiBoard()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}		
	}	// insertBoard() ��
	
	
	
	//*********** getBoardCount()���� : �Խ��ǿ� ��ϵ� �� ���� ���� �Լ�	
	public int getBoardCount(){
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";
		
		int count = 0;
		
		try {
			con=getConn();
			sql="select count(*) from neiboard";	// ���̺��� �� ���� ���� �Լ�
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count=rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println("getBoardCount()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
		}		
		return count;
	} //getBoardCount() ����
	
	
	
	//*********** getBoardList()���� : ���� ��ü�� �˻��Ͽ� �������� ���� ������ �����ִ� ����	
		public ArrayList<neiBoardDTO> getBoardList(int startRow, int pageSize){			
			Connection con = null;
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			String sql = "";
			
			
			// ArrayList ��ü ����
			ArrayList<neiBoardDTO> boardList=new ArrayList<neiBoardDTO>();
			
			try {
				con=getConn();
				
				// DB�˻� ��� ���� : ����� �����Ͽ� re_ref�� ��������, �� ��ü ��ȣ�� ������������
					// & limit�� �� ���������� ù�� ��ȣ�� ���������� ������ �� ������ ?�� �����Ѵ�.
				
				sql="select * from neiboard order by re_ref desc, re_seq asc limit ?,?";
				pstmt=con.prepareStatement(sql);			
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, pageSize);			
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					neiBoardDTO ndto=new neiBoardDTO();
					
					ndto.setNum(rs.getInt("num"));
					ndto.setUserId(rs.getString("userId"));
					ndto.setUserNickname(rs.getString("userNickname"));
					ndto.setUserPhoto(rs.getString("userPhoto"));
					ndto.setUpPhoto1(rs.getString("upPhoto1"));
					ndto.setUpPhoto2(rs.getString("upPhoto2"));
					ndto.setUpPhoto3(rs.getString("upPhoto3"));
					ndto.setUpPhoto4(rs.getString("upPhoto4"));
					ndto.setSubject(rs.getString("subject"));
					ndto.setContent(rs.getString("content"));
					ndto.setRe_ref(rs.getInt("re_ref"));
					ndto.setRe_lev(rs.getInt("re_lev"));
					ndto.setRe_seq(rs.getInt("re_seq"));
					ndto.setReadCount(rs.getInt("readCount"));
					ndto.setReplyCount(rs.getInt("replyCount"));
					ndto.setWriteTime(rs.getTimestamp("writeTime"));
					ndto.setIp(rs.getString("ip"));
					

					boardList.add(ndto);
				}				
			} catch (Exception e) {
				System.out.println("getBoardList()����"+e);
			}finally{
				if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
				if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
				if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
			}			
			return boardList;			
		}  //getBoardList()��
		
		
		
	//*********** updateReplyCount()���� : ��۾��� / ���� �Ҷ� ��ۼ� 1 ����/���� ������
		
		public void updateReplyCount(int num){
			Connection con = null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			int replyCount = 0;
			String sql = "";		
			try {
				con=getConn();				
				
				sql="select count(*) from neireply where contentNum=?";	// ��� ������ ���� �Լ�
				
				pstmt=con.prepareStatement(sql);			
				pstmt.setInt(1, num);
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					replyCount=rs.getInt(1);
				}				
				System.out.println(replyCount);
				
				// ��� ���� ����
				pstmt.close();
				sql="update neiboard set replycount=? where num=?";
				pstmt=con.prepareStatement(sql);			
				pstmt.setInt(1, replyCount);
				pstmt.setInt(2, num);
				pstmt.executeUpdate();				
				
			} catch (Exception e) {
				System.out.println("updateReplyCount()()����"+e);
			}finally{
				if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
				if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}	
				if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
			}			
	}//updateReadCount()��
		
		
	
	//*********** updateReadCount()���� : �� ������ ��ȸ�� 1 ���� ������	
		public void updateReadCount(int num){
			Connection con = null;
			PreparedStatement pstmt=null;
			String sql = "";		
			try {
				con=getConn();				
				sql = "update neiboard set readcount=readcount+1 where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1,num);
				pstmt.executeUpdate();				
			} catch (Exception e) {
				System.out.println("updateReadCount����"+e);
			}finally{
				if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
				if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			}			
		}//updateReadCount()��
	
	//*********** getContent()���� : �� ������ DB���� ������	
		public neiBoardDTO getContent(int num){
			Connection con = null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql = "";	
			neiBoardDTO ndto=null;
			try {
				con=getConn();				
				sql = "select * from neiboard where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1,num);
				rs=pstmt.executeQuery();	
				
				if(rs.next()){
					ndto = new neiBoardDTO();
					
					ndto.setNum(rs.getInt("num"));
					ndto.setUserId(rs.getString("userId"));
					ndto.setUserNickname(rs.getString("userNickname"));
					ndto.setUserPhoto(rs.getString("userPhoto"));
					ndto.setUpPhoto1(rs.getString("upPhoto1"));
					ndto.setUpPhoto2(rs.getString("upPhoto2"));
					ndto.setUpPhoto3(rs.getString("upPhoto3"));
					ndto.setUpPhoto4(rs.getString("upPhoto4"));
					ndto.setSubject(rs.getString("subject"));
					ndto.setContent(rs.getString("content"));
					ndto.setRe_ref(rs.getInt("re_ref"));
					ndto.setRe_lev(rs.getInt("re_lev"));
					ndto.setRe_seq(rs.getInt("re_seq"));
					ndto.setReadCount(rs.getInt("readCount"));
					ndto.setReplyCount(rs.getInt("replyCount"));
					ndto.setWriteTime(rs.getTimestamp("writeTime"));
					ndto.setIp(rs.getString("ip"));				
				}
			} catch (Exception e) {
				System.out.println("getContent()����"+e);
			}finally{
				if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
				if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}
				if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
			}	
			
			return ndto;
		}//getContent()��
		
		
	//*********** delContent()���� : �ۻ��� �޼ҵ�	
		public int delContent(int num){
			int delCheck=0;
			Connection con=null;
			PreparedStatement pstmt=null;
			String sql="";
			
			try {
				con=getConn();
				sql="delete from neiboard where num=?";
				pstmt=con.prepareStatement(sql);				
				pstmt.setInt(1,num);
				pstmt.executeUpdate();	
				delCheck=1;
						
			} catch (Exception e) {
				System.out.println("delBoard()����"+e);
			}finally{
				if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
				if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			}	
			return delCheck;
		}


		
		
		//*********** getNeiBestList()���� : ���װԽ��� ������ ����Ʈ�� �Խÿ�		
		public ArrayList<neiBoardDTO> getNeiBestList(){			
			Connection con = null;
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			String sql = "";
			
			
			// ArrayList ��ü ����
			ArrayList<neiBoardDTO> list=new ArrayList<neiBoardDTO>();
			
			try {
				con=getConn();				
				// DB�˻� ��� ���� : DD���ڰ� ������ �� �� (readCount+replyCount*3)�� ���� ������ 5��			
				
				sql="select * from neiboard where writeTime > CURRENT_DATE( ) order by (readcount+replycount*3) desc limit 5;";
				pstmt=con.prepareStatement(sql);			
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					neiBoardDTO ndto=new neiBoardDTO();
					
					ndto.setNum(rs.getInt("num"));
					ndto.setUserId(rs.getString("userId"));
					ndto.setUserNickname(rs.getString("userNickname"));
					ndto.setUserPhoto(rs.getString("userPhoto"));
					ndto.setUpPhoto1(rs.getString("upPhoto1"));
					ndto.setUpPhoto2(rs.getString("upPhoto2"));
					ndto.setUpPhoto3(rs.getString("upPhoto3"));
					ndto.setUpPhoto4(rs.getString("upPhoto4"));
					ndto.setSubject(rs.getString("subject"));
					ndto.setContent(rs.getString("content"));
					ndto.setRe_ref(rs.getInt("re_ref"));
					ndto.setRe_lev(rs.getInt("re_lev"));
					ndto.setRe_seq(rs.getInt("re_seq"));
					ndto.setReadCount(rs.getInt("readCount"));
					ndto.setReplyCount(rs.getInt("replyCount"));
					ndto.setWriteTime(rs.getTimestamp("writeTime"));
					ndto.setIp(rs.getString("ip"));
					
					list.add(ndto);
				}				
			} catch (Exception e) {
				System.out.println("getNeiBestList()����"+e);
			}finally{
				if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
				if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
				if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
			}			
			return list;			
		}  //getBoardList()��
		
		

		//*********** modifyContent()���� : ��۾��� ��ۼ� 1 ���� ������
		
		public void modifyContent(neiBoardDTO ndto, int num){
			Connection con = null;
			PreparedStatement pstmt=null;
			String sql = "";		
			try {
				con=getConn();				
				
				sql="update neiboard set subject=?, content=?, upPhoto1=?,  upPhoto2=?,  upPhoto3=?,  upPhoto4=? where num=?";
				pstmt=con.prepareStatement(sql);			
				pstmt.setString(1, ndto.getSubject());
				pstmt.setString(2, ndto.getContent());
				pstmt.setString(3, ndto.getUpPhoto1());
				pstmt.setString(4, ndto.getUpPhoto2());
				pstmt.setString(5, ndto.getUpPhoto3());
				pstmt.setString(6, ndto.getUpPhoto4());
				pstmt.setInt(7, num);
				pstmt.executeUpdate();				
				
			} catch (Exception e) {
				System.out.println("updateReplyCount()()����"+e);
			}finally{
				if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
				if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}	
			}			
	}//updateReadCount()��
		
		
		
		
		
		
//////////////////////////////////��� �κ� �޼ҵ�////////////////////////////////////////////////		
	
		
		
		//*********** getReplyCount()���� : �Խ��ǿ� ��ϵ� �� ���� ���� �Լ�	
		public int getReplyCount(int contentNum){
			Connection con = null;
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			String sql = "";
			
			int count = 0;
			
			try {
				con=getConn();
				sql="select count(*) from neireply where contentNum=?";	// ���̺��� �� ���� ���� �Լ�
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, contentNum);
				/*pstmt.setInt(2, contentPageNum);*/
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					count=rs.getInt(1);
				}
				
			} catch (Exception e) {
				System.out.println("getReplyCount()����"+e);
			}finally{
				if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
				if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
				if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
			}		
			return count;
		} //getReplyCount() ����

		
	//*********** insertNeiReply()���� : ���â���� �Է¹��� �� DB�� �����ϴ� �޼ҵ�
	
		
	public void insertNeiReply(neiBoardDTO ndto, int contentNum){
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";
		
		//�߰��� �� �ۿ� ���� �� ��ȣ�� ������ ����
		int num = 0;
		
		try {
			
		// �ֱ� �۹�ȣ ��������.
			con=getConn();
			sql="select max(num)from neireply"; 
			pstmt=con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				num=rs.getInt(1)+1;   // �˻��� �ֽű۹�ȣ +1 �� ������ �۹�ȣ�� ����
			}else{
				num=1; //(���� ������ 1������ ����)
			}
			
		// ������ �����ϱ�
			sql="insert into neireply(num, userId, userNickname, userPhoto, subject, content, re_ref, re_lev, re_seq, "
					+ "writetime, ip, contentNum) values(?,?,?,?,?,?,?,?,?,?,?,?)";	
			
			pstmt.close();
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, ndto.getUserId());
			pstmt.setString(3, ndto.getUserNickname());
			pstmt.setString(4, ndto.getUserPhoto());
			pstmt.setString(5, ndto.getSubject());
			pstmt.setString(6, ndto.getContent());
			pstmt.setInt(7, num);	// 1�� ��� re_ref�� 1������� num��ȣ ����
			pstmt.setInt(8, 0);	// 1�� ��ۿ��� �鿩����� �켱 0
			pstmt.setInt(9, 0);	// 1�� ��ۿ��� ��ۼ����� �켱 0
			pstmt.setTimestamp(10, ndto.getWriteTime());
			pstmt.setString(11, ndto.getIp());
			pstmt.setInt(12,ndto.getContentNum());
			/*pstmt.setInt(13,ndto.getContentPageNum());*/
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insertNeiReply()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}		
	}	// insertNeiReply() ��
		
	
	
	
//*********** getNeiReply()���� : �Էµ� ����� �˻��Ͽ� �ѷ��ִ� �޼ҵ�	
	public ArrayList<neiBoardDTO> getNeiReply(int contentNum){			
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";
		
		// ArrayList ��ü ����
		ArrayList<neiBoardDTO> boardList=new ArrayList<neiBoardDTO>();
		
		try {
			con=getConn();
			
			// DB�˻� ��� ���� : ����� �����Ͽ� re_ref�� ��������, �� ��ü ��ȣ�� ������������
				// & limit�� �� ���������� ù�� ��ȣ�� ���������� ������ �� ������ ?�� �����Ѵ�.
			
			sql="select * from neireply where contentNum=? order by re_ref asc, re_lev asc, re_seq desc";   /*and contentPageNum=? */
			pstmt=con.prepareStatement(sql);			
			pstmt.setInt(1, contentNum);
			/*pstmt.setInt(2, contentPageNum);*/			
			rs=pstmt.executeQuery();
			
			
			while(rs.next()){
				neiBoardDTO ndto=new neiBoardDTO();
				
				ndto.setNum(rs.getInt("num"));
				ndto.setUserId(rs.getString("userId"));
				ndto.setUserNickname(rs.getString("userNickname"));
				ndto.setUserPhoto(rs.getString("userPhoto"));
				ndto.setSubject(rs.getString("subject"));
				ndto.setContent(rs.getString("content"));
				ndto.setRe_ref(rs.getInt("re_ref"));
				ndto.setRe_lev(rs.getInt("re_lev"));
				ndto.setRe_seq(rs.getInt("re_seq"));
				ndto.setWriteTime(rs.getTimestamp("writeTime"));
				ndto.setIp(rs.getString("ip"));
				ndto.setContentNum(rs.getInt("contentNum"));
				ndto.setReOwnerNick(rs.getString("reOwnerNick"));
				/*ndto.setContentPageNum(rs.getInt("contentPageNum"));*/


				boardList.add(ndto);
			}				
		} catch (Exception e) {
			System.out.println("getBoardList()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
		}			
		return boardList;			
	}  //getBoardList()��
		
		
			
	public void insertNeiReply2(neiBoardDTO ndto, int contentNum){
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";
		
		//�߰��� �� �ۿ� ���� �� ��ȣ�� ������ ����
		int num = 0;
		
		try {
			
		// �ֱ� �۹�ȣ ��������.
			con=getConn();
			sql="select max(num)from neireply"; //���� �ֱ��� ��� ��ȣ �˻�
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				num=rs.getInt(1)+1;   // �˻��� �ֽű۹�ȣ +1 �� �� ������ �۹�ȣ�� ����
			}else{
				num=1; //(���� ������ 1������ ����)
			}
			
		//��� ���� ���ġ : ������� �׷�� ���� ��ȣ �� �θ�麸�� seq���� ū �۵��� seq+1�� ������Ų��.
			sql="update neireply set re_seq=re_seq+1 where contentNum=? and re_ref=? and re_seq>?";   /*and contentPageNum=? */
			pstmt.close();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, contentNum);
			/*pstmt.setInt(2, contentPageNum);*/
			pstmt.setInt(2, ndto.getRe_ref());
			pstmt.setInt(3, ndto.getRe_seq());
			pstmt.executeUpdate();
			
			
			
		// ������ �����ϱ�
			sql="insert into neireply(num, userId, userNickname, userPhoto, subject, content, re_ref, re_lev, re_seq, "
					+ "writetime, ip, contentNum, reOwnerNick) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";	 /* contentPageNum*/
			
			pstmt.close();
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, ndto.getUserId());
			pstmt.setString(3, ndto.getUserNickname());
			pstmt.setString(4, ndto.getUserPhoto());
			pstmt.setString(5, ndto.getSubject());
			pstmt.setString(6, ndto.getContent());
			pstmt.setInt(7, ndto.getRe_ref());
			pstmt.setInt(8, ndto.getRe_lev()+1);	// ��ۼ����� �θ�� +1
			pstmt.setInt(9, ndto.getRe_seq()+1);	// �鿩����� �θ�� +1	
			pstmt.setTimestamp(10, ndto.getWriteTime());
			pstmt.setString(11, ndto.getIp());
			pstmt.setInt(12, ndto.getContentNum());
			pstmt.setString(13, ndto.getReOwnerNick());			
			/*pstmt.setInt(13, ndto.getContentPageNum());*/
			
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insertNeiReply2()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}		
	}	// insertNeiReply2() ��
	
	
	//*********** delReply()���� : ��ۻ��� �޼ҵ�	
	public int delReply(int num){
		int delCheck=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		
		try {
			con=getConn();
			sql="delete from neireply where num=?";
			pstmt=con.prepareStatement(sql);				
			pstmt.setInt(1,num);
			pstmt.executeUpdate();	
			delCheck=1;
					
		} catch (Exception e) {
			System.out.println("delReply()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
		}	
		return delCheck;
	}		

	
	
	//*********** getRe_refCount()���� : ����� �޷��� ��� ����� ���� �ȵǵ��� ��� �޸� ���� ���� �Լ�
	public int getRe_refCount(int re_ref){
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";
		
		int count = 0;
		
		try {
			con=getConn();
			sql="select count(*) from neireply where re_ref=?";	// ���̺��� �� ���� ���� �Լ�
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count=rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println("getReplyCount()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
		}		
		return count;
	} //getRe_refCount() ����

	
	
/////////////////////*********** insertMoim()���� : ������ �����ϴ� �Լ�
	public void insertMoim(moimDTO mdto) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";
		
		//�߰��� �� �ۿ� ���� �� ��ȣ�� ������ ����
		int moimNum = 0;
		
		try {
			
		// �ֱ� �۹�ȣ ��������.
			con=getConn();
			sql="select max(moimNum)from moim"; //���� �ֱ��� �� ��ȣ �˻�
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				moimNum=rs.getInt(1)+1;   // �˻��� �ֽű۹�ȣ +1 �� ������ �۹�ȣ�� ����
			}else{
				moimNum=1; //(���� ������ 1������ ����)
			}
				
		// ������ �����ϱ�
			sql="insert into moim(moimNum, userId, moimCategory, moimTitle, moimIntro,  moimPhoto,  makingTime, userCity, userDistrict) values(?,?,?,?,?,?,?,?,?)";			
			
			pstmt.close();
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, moimNum);
			pstmt.setString(2, mdto.getUserId());
			pstmt.setString(3, mdto.getMoimCategory());
			pstmt.setString(4, mdto.getMoimTitle());
			pstmt.setString(5, mdto.getMoimIntro());
			pstmt.setString(6, mdto.getMoimPhoto());			
			pstmt.setTimestamp(7, mdto.getMakingTime());
			pstmt.setString(8, mdto.getUserCity());
			pstmt.setString(9, mdto.getUserDistrict());			
			pstmt.executeUpdate();
			
			
			//���Ӹ���� ������ ���� �Է��ϱ�
			sql="insert into moimmember(moimNum, userId, userLevel) values(?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,moimNum);
			pstmt.setString(2, mdto.getUserId());	
			pstmt.setInt(3, 3);
			pstmt.executeUpdate();
			
			
			
			
		} catch (Exception e) {
			System.out.println("insertMoim()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}	
		
	} //insertMoim() ����

/////////////////////*********** modifyMoim() : ������ �����ϴ� �Լ�
	public void modifyMoim(moimDTO mdto) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";	
	
		try {			
			con=getConn();			
			sql="update neiboard set subject=?, content=?, upPhoto1=?,  upPhoto2=?,  upPhoto3=?,  upPhoto4=? where num=?";
						
			// ���� ������ �����ϱ�
			sql="update moim set moimCategory=?, moimTitle=?, moimIntro=?,  moimPhoto=? where moimNum=?";			
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, mdto.getMoimCategory());
			pstmt.setString(2, mdto.getMoimTitle());
			pstmt.setString(3, mdto.getMoimIntro());
			pstmt.setString(4, mdto.getMoimPhoto());			
			pstmt.setInt(5, mdto.getMoimNum());
			
			pstmt.executeUpdate();
		
		} catch (Exception e) {
			System.out.println("modifyMoim()����"+e);
			}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}	
	
	} //modifyMoim() ����

	
	
	
/////////////*********** getMoimListByDisgtrict()���� : �츮���� ���� �� �������� ���� ��Ȳ �������� �Լ�
	public ArrayList<moimDTO> getMoimListByDisgtrict(String userId, String userCity, String userDistrict) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";
		
		
		// ArrayList ��ü ����
		ArrayList<moimDTO> list=new ArrayList<moimDTO>();
		
		try {
			con=getConn();				
						
			sql="SELECT *,(SELECT COUNT(*) FROM moimmember B where B.moimNum=A.moimNum) as memberCount "
			  + "from moim A "
			  + "WHERE userCity=? AND userDistrict=? ORDER BY moimNum DESC;";
			pstmt=con.prepareStatement(sql);	
			pstmt.setString(1, userCity);
			pstmt.setString(2, userDistrict);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				moimDTO mdto=new moimDTO();
				
				mdto.setMoimNum(rs.getInt("moimNum"));
				mdto.setUserId(rs.getString("userId"));
				mdto.setMoimCategory(rs.getString("moimCategory"));
				mdto.setMoimTitle(rs.getString("moimTitle"));
				mdto.setMoimIntro(rs.getString("moimIntro"));
				mdto.setMoimPhoto(rs.getString("moimPhoto"));
				mdto.setMakingTime(rs.getTimestamp("makingTime"));
				mdto.setUserCity(rs.getString("userCity"));
				mdto.setUserDistrict(rs.getString("userDistrict"));
				mdto.setMaxQuota(rs.getInt("maxQuota"));
				mdto.setMemberCount(rs.getInt("memberCount"));
				
				list.add(mdto);
			}				
		} catch (Exception e) {
			System.out.println("getMoimListByDisgtrict()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
		}			
		return list;			
		
	} //getMoimListByDisgtrict() ����

		
	
//*********** getMoimInfo() ���� : �츮���׿��� ���� ���� Ŭ�� �� ����num����  �ش���� �⺻���� ������., 
	
	public moimDTO getMoimInfo(String moimNum){
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";
		moimDTO mdto=new moimDTO();
		
					
		try {
			con = getConn();
			sql="select * from moim where moimNum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,moimNum);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				mdto.setMoimNum(rs.getInt("moimNum"));
				mdto.setUserId(rs.getString("userId"));
				mdto.setMoimCategory(rs.getString("moimCategory"));
				mdto.setMoimTitle(rs.getString("moimTitle"));
				mdto.setMoimIntro(rs.getString("moimIntro"));
				mdto.setMoimPhoto(rs.getString("moimPhoto"));
				mdto.setUserCity(rs.getString("userCity"));
				mdto.setUserDistrict(rs.getString("userDistrict"));
				mdto.setMakingTime(rs.getTimestamp("makingTime"));
				mdto.setMaxQuota(rs.getInt("maxQuota"));
			}			
		} catch (Exception e) {
			System.out.println("getMoimInfo()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
		}		
		return mdto;
	} //getMoimInfo() ����


//*********** insertThunder() ���� : ���ӿ��� ���� �����ϴ� �Լ�
	public void insertThunder(thunderDTO tdto) {
	
		Connection con = null;
		PreparedStatement pstmt=null;		
		String sql = "";
		ResultSet rs = null;
		int thunderNum=1;
		
		try {			
			con = getConn();
			
		// �ֽ� ���� ��ȣ ��������
			sql ="select max(thunderNum) from thunder";
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				thunderNum=rs.getInt(1)+1;   // �˻��� ������ȣ +1 �� ������ �۹�ȣ�� ����
			}else{
				thunderNum=1; //(������  ������ 1������ ����)
			}
			
		// ���� ���� �����ϱ�
			
			sql="insert into thunder(thunderName, thunderPlace, thunderDate,  thunderPerson,  makingTime, userId, moimNum, userPhoto, thunderNum) values(?,?,?,?,?,?,?,?,?)";			
			
			pstmt.close();
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, tdto.getThunderName());
			pstmt.setString(2, tdto.getThunderPlace());
			pstmt.setTimestamp(3, tdto.getThunderDate());
			pstmt.setString(4, tdto.getThunderPerson());				
			pstmt.setTimestamp(5, tdto.getMakingTime());
			pstmt.setString(6, tdto.getUserId());
			pstmt.setString(7, tdto.getMoimNum());
			pstmt.setString(8, tdto.getUserPhoto());
			pstmt.setInt(9, thunderNum);
			pstmt.executeUpdate();
			
			
		// ���� ������ ������ ��� �����ϱ�				
			// ���� ������ �߰����� ��������
			MemberDAO mdao = new MemberDAO();
			MemberDTO mdto = new MemberDTO();
					
			mdto = mdao.getUserInfo(tdto.getUserId());			
			
			sql="insert into thunderuser(thunderNum, moimNum, userId, userNickname, userPhoto) values(?,?,?,?,?)";			
			
			pstmt.close();
			pstmt=con.prepareStatement(sql);			
			
			pstmt.setInt(1, thunderNum);
			pstmt.setString(2, tdto.getMoimNum());
			pstmt.setString(3, tdto.getUserId());
			pstmt.setString(4, mdto.getUserNickname());			
			pstmt.setString(5, mdto.getUserPhoto());
						
			pstmt.executeUpdate();	
			
			
		} catch (Exception e) {
			System.out.println("insertThunder()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
		}	
		
	} //insertThunder()����


//*********** getThunderList() ���� : ���ӿ��� ���� ����Ʈ �ҷ����� �Լ�
	public ArrayList<thunderDTO> getThunderList(String moimNum) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";		
		ArrayList<thunderDTO> list = new ArrayList<thunderDTO>();
					
		try {
			con = getConn();
			sql="SELECT *, "
				+ "(SELECT COUNT(*) FROM thunderuser WHERE moimNum=? AND thunder.thunderNum=thunderuser.thunderNum) AS thunderJoinCount  "
				+ "FROM thunder  "
				+ "WHERE moimNum=? "
				+ "ORDER BY thunderDate DESC";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,moimNum);
			pstmt.setString(2,moimNum);
			rs=pstmt.executeQuery();
			
			while(rs.next()){	
				thunderDTO tdto=new thunderDTO();
				tdto.setThunderNum(rs.getString("thunderNum"));
				tdto.setThunderName(rs.getString("thunderName"));
				tdto.setThunderPerson(rs.getString("thunderPerson"));
				tdto.setThunderPlace(rs.getString("thunderPlace"));
				tdto.setUserId(rs.getString("userId"));
				tdto.setUserPhoto(rs.getString("userPhoto"));
				tdto.setMakingTime(rs.getTimestamp("makingTime"));
				tdto.setThunderDate(rs.getTimestamp("thunderDate"));	
				tdto.setThunderJoinCount(rs.getInt("thunderJoinCount"));	
								
				list.add(tdto);			
			}			
		} catch (Exception e) {
			System.out.println("getThunderList()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
		}		
		return list;
		
	} //getThunderList() ����
	

//*********** delThunder()���� : �������ӻ��� �޼ҵ�	
	public int delThunder(int thunderNum){
		int delCheck=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		
		try {
			con=getConn();
			sql="delete from thunder where thunderNum=?";
			pstmt=con.prepareStatement(sql);				
			pstmt.setInt(1,thunderNum);
			pstmt.executeUpdate();	
			delCheck=1;
					
		} catch (Exception e) {
			System.out.println("delThunder()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
		}	
		return delCheck;
	} //delThunder()��
	

	
/////////////*********** getMyMoimList()���� : �츮���� ���� �� ���� ������ ���� ��Ȳ �������� �Լ�
	
	public ArrayList<moimDTO> getMyMoimList(String userId) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";		
	
		// ArrayList ��ü ����
		ArrayList<moimDTO> myList=new ArrayList<moimDTO>();
		
		try {
			con=getConn();				
			// DB�˻� ��� ���� :		
						
			sql = "SELECT A.moimNum, A.userId, B.moimTitle, MAX(C.thunderDate) AS thunderDate "
					+ " FROM moimmember A, moim B, thunder C"
					+ " WHERE A.userId = ? "
					+ " AND A.moimNum = B.moimNum "
					+ " AND B.moimNum = C.moimNum "
					+ " GROUP BY A.moimNum "
					+ " ORDER BY C.thunderDate DESC";
			
			pstmt=con.prepareStatement(sql);	
			pstmt.setString(1, userId);			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				moimDTO mdto=new moimDTO();				
				mdto.setMoimNum(rs.getInt("moimNum"));
				mdto.setUserId(rs.getString("userId"));
				mdto.setMoimTitle(rs.getString("moimTitle"));				
				mdto.setMakingTime(rs.getTimestamp("thunderDate"));			
				myList.add(mdto);
			}				
		} catch (Exception e) {
			System.out.println("getMyMoimList()����"+e);
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
		}			
		return myList;				
	} //getMyMoimList() ����	
	
	
	
	
	
	
}  //DAO Ŭ���� ��
