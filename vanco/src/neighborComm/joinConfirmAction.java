package neighborComm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class joinConfirmAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");		
		String userId = request.getParameter("userId");
		int moimNum = Integer.parseInt(request.getParameter("moimNum"));
			
		ActionForward forward = new ActionForward();
		
		moimDAO mdao = new moimDAO();
		mdao.updateMemberLevel2(userId, moimNum);
		
		forward.setRedirect(true); 
		forward.setPath("./moimMemberInfo.ne?moimNum="+moimNum);
		
		return forward;
	}

}
