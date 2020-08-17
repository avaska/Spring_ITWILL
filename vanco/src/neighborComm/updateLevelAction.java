package neighborComm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class updateLevelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");		
		String userId = request.getParameter("userId");
		int moimNum = Integer.parseInt(request.getParameter("moimNum"));
		int level = Integer.parseInt(request.getParameter("level"));
		
		ActionForward forward = new ActionForward();
		
		moimDAO mdao = new moimDAO();
		mdao.updateMemberLevel(userId, level, moimNum);
		
		forward.setRedirect(true); 
		forward.setPath("./moimMemberInfo.ne?moimNum="+moimNum);
		
		return forward;
	}

}
