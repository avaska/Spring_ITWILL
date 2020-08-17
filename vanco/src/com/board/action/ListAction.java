package com.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.db.Board;
import com.board.db.BoardDAO;
import com.controller.action.CommandAction;

/*
 * board list Action
 */

public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		
		
		String page2 = request.getParameter("page") == null ? "0" : request.getParameter("page");
		
		int page = Integer.parseInt(page2);	
		int moimNum = Integer.parseInt(request.getParameter("moimNum"));
		
		ArrayList<Board> articleList = BoardDAO.getInstance().getArticleList(page,moimNum);
		
		request.setAttribute("array", articleList);
		request.setAttribute("page", page);
		// request.setAttribute("moimNum", moimNum);
		
		return "./neighborComm/moimContent.jsp?pageCall=moimBoard.jsp&moimNum="+moimNum;
	}

}

/*
 * ?˜›?‚  ì½”ë“œ
try {
	String driverName = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	ResultSet rs = null;
	
	Class.forName(driverName);
	Connection con = DriverManager.getConnection(url, "board", "12345");
	
	System.out.println("Oracle Database db connected");
	Statement stmt = con.createStatement();
	String sql = "SELECT * FROM board order by idx desc";
	
	rs = stmt.executeQuery(sql);

	ArrayList<Board> ar = new ArrayList<Board>();
	Board bd = null;
	
	while(rs.next()){
		
		bd = new Board();
		bd.setIdx(rs.getInt("idx"));
		bd.setContent(rs.getString("title"));
		bd.setCount(rs.getInt("count"));
		bd.setRegdate(rs.getString("regdate"));
		bd.setTitle(rs.getString("title"));
		bd.setWriter(rs.getString("writer"));
		ar.add(bd);
		
	}
	
	request.setAttribute("array", ar);
	
	stmt.close();
	rs.close();
	con.close();
	
} catch (Exception e){
	System.out.println("board.jsp ?˜¤ë¥?");
	System.out.println(e.getMessage());
	e.printStackTrace();	
}
*/