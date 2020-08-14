package neighborComm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class moimJoinCancelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");		
		String userId = request.getParameter("userId");
		int moimNum = Integer.parseInt(request.getParameter("moimNum"));
		
		
		moimDAO mdao = new moimDAO();
		mdao.deleteJoinWait(userId, moimNum);		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true); 
		forward.setPath("./moimInfo.ne?moimNum="+moimNum);
		
		return forward;
	}

}
