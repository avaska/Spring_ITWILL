package store;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class storeServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doProcess(request,response);System.out.println("�ΰ�");}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doProcess(request,response);System.out.println("������Ʈ");}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("���� �۵�");
		
		// ��û�� �����ּҰ� ���(���ؽ�Ʈ �н������ּ�-���ؽ�Ʈ���� = ���� ���)
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		int contextPathLength= contextPath.length();
		String command = RequestURI.substring(contextPathLength);// ��ü�ּҿ��� ��û�ּҸ� �߶� ���
		System.out.println("1:"+RequestURI);
		System.out.println("2:"+contextPath);
		System.out.println("3:"+contextPathLength);
		System.out.println("command��������:"+command);
		
		//doProcess �������� ����
		ActionForward forward = null;
		Action action=null;		

		
// ����� ���� ����(/store.st)////////////////////////////////////////////////////////////////////
	if(command.equals("/store.st")){
		//������ �̵���� ����(true=�����̷�Ʈ, false=����ġ(��� ��������))
		forward=new ActionForward();
		forward.setRedirect(false);		
		forward.setPath("/store/store.jsp");  //�̵��� ������ ��� �ּҰ� ����(ȸ������ �Է� ������)
	}else if(command.equals("/product.st")){
		forward=new ActionForward();
		forward.setRedirect(false);		
		forward.setPath("/store/product.jsp"); 
	
// �츮���� ����////////////////////////////////////////////////////////////////////	
	}else if(command.equals("/dmSend.dm")){
		String receiveUserId=request.getParameter("receiveUserId");
		request.setAttribute("receiveUserId", receiveUserId);
		String receiveNickname=request.getParameter("receiveNickname");
		request.setAttribute("receiveNickname", receiveNickname);
		String url=request.getParameter("url");
		request.setAttribute("url", url);
		
		forward=new ActionForward();
		forward.setRedirect(false);			
		forward.setPath("/dm/dmSend.jsp");  //�̵��� ������ ��� �ּҰ� ����(idȸ������ �Է� ������)

// ���� ������ �׼�dmSendAction.dm
	}else if(command.equals("/dmSendAction.dm")){
		/*action=new dmSendAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		*/
	}
	
	
	
	
	
	
	// ������� ó�� (������)////////////////////////////////////////////////////////////////////
	if(forward!=null){
		if(forward.isRedirect()){
			response.sendRedirect(forward.getPath());
		}else{//����ġ ������� ������ ��� ���� ���� ������
			RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);}
		
	}// ������� if ����		
} // doProcess() ����	
} // ���� ����

