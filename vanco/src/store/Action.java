package store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Action �������̽��� �߻�޼ҵ带 ������ �ڽ�Ŭ�������� ���������� ����� Ʋ
public interface Action {	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;	
}
