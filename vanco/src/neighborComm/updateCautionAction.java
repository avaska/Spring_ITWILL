package neighborComm;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class updateCautionAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");		
		String userId = request.getParameter("userId");
		int moimNum = Integer.parseInt(request.getParameter("moimNum"));
		int caution = Integer.parseInt(request.getParameter("caution"));
		
		moimDAO mdao = new moimDAO();
		mdao.updateCaution(userId, moimNum, caution);		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./moimMemberInfo.ne?moimNum="+moimNum);
		
		return forward;
	}

}
