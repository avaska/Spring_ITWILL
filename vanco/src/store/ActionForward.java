package store;

/*
ActionForward 클래스는 Action 인터페이스를 구현한 자식객체의 명령을 수행하고
 결과값을 가지고 페이지를 포워딩(재요청)할때 사용되는 클래스이다
	하는일1. 페이지 이동방식 여부값을 저장후 반환해준다
		- 페이지 이동방식 여부값 true 일때 => response.rendredirect();리다이렉트방식
		- 페이지 이동방식 여부값 false일때 => forward(); 디스패치 방식으로 전달 		 
	하는일2. 이동할 페이지 경로 주소값을 저장하여 반환해주는 역할.
*/

public class ActionForward {
	// 페이지 이동방식 여부값을 저장할 변수
	private boolean isRedirect=false;	
	// 이동할 페이지 경로 주소값을 저장할 변수
	private String path=null;
	
	// 페이지 이동방식 여부값을 저장할 메소드
	public void setRedirect(boolean isRedirect){
		this.isRedirect=isRedirect;	}
	
	// 페이지 이동방식 여부값을 리턴해주는 메소드
	public boolean isRedirect(){
		return isRedirect;	}
	
	// 이동할 페이지 경로 주소값을 리턴해주는 메소드
	public String getPath() {
		return path;	}

	// 이동할 페이지 경로 주소값을 저장할 메소드
	public void setPath(String path) {
		this.path = path;	}

}// 클래스 종료

