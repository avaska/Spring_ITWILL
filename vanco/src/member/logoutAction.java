package member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class logoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	// 로그아웃 요청이 들어왔을때 세션영역의 값을 초기화 한 후 로그아웃 메세지창 띄워주고 CarMain.jsp 페이지로 이동시킴
			HttpSession session=request.getSession(true);
			session.invalidate();
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그아웃 되었습니다.');");
			out.println("location.href='./vanco.me'");
			out.println("</script>");
					
			return null;
	}

}
