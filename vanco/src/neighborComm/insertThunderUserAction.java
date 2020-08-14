package neighborComm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class insertThunderUserAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String moimNum = request.getParameter("moimNum");
		String thunderNum = request.getParameter("thunderNum");
				
		moimDAO mdao = new moimDAO();		
		mdao.insertThunderUser(userId, moimNum, thunderNum);		
		
		return null;
	}

}
