package store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Action 인터페이스의 추상메소드를 구현한 자식클래스에서 공통적으로 사용할 틀
public interface Action {	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;	
}
