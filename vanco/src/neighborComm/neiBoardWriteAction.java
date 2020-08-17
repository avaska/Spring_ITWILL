package neighborComm;

import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class neiBoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//neiBoardWrite.jsp에서 전달된 사진데이터+업로드 파일 정보는 request에 저장되어 있으므로 모든 전달값을 저장한다.
		// 그러나, 해당 정보들은 MultipartRequest타입이기 때문에 request.getParameter로는 받을 수 없다.

		request.setCharacterEncoding("utf-8");

		// request.getRealPath("상대경로")를 통해 파일을 저장할 절대 경로를 구해온다;
		/*String uploadPath=request.getRealPath("imageUpload");*/
		
		//1. 현재 실행중인 웹프로젝트 정보 컨텍스트 객체 생성후, 
		ServletContext ctx = request.getServletContext();
		
		//2. 컨텍스트 객체에 실제 업로드될 서버측 경로 넣어서 저장하기 
		String realPath	= ctx.getRealPath("imageUpload");
		
		//3. 업로드 파일 최대 크기 설정(10mb)
		int max = 1024*1024*10;
		
		// 파일 업로드객체 호출
			// 내장객체 전달값
				//1. 폼태그에서 전달받은 request객체를 생성자로 전달
				//2. 실제 업로드 될 경로 위치 전달
				//3. max size
				//4. 파일이름이 한글일 경우 처리할 수 있도록 인코딩 타입 지정
				//5. 똑같은 이름의 파일을 업로드 할 시 자동 파일명 변환객체 전달 
		MultipartRequest multi=new MultipartRequest(request,realPath,max,"utf-8",new DefaultFileRenamePolicy());
		
		
		//request에 담아온 입력정보와 히든정보를 모두 가져온다.
		String userId=multi.getParameter("userId");
		String userNickname=multi.getParameter("userNickname");
		String userPhoto=multi.getParameter("userPhoto");
		String subject=multi.getParameter("subject");
		String content=multi.getParameter("content");		
		
		// 파일 인풋태그의 name속성으로 서버에 업로드된 파일명을 받아와 가상경로를 설정해준다.
		String upPhoto1 = "/vc/imageUpload/"+multi.getFilesystemName("upPhoto1");
		String upPhoto2 = "/vc/imageUpload/"+multi.getFilesystemName("upPhoto2");
		String upPhoto3 = "/vc/imageUpload/"+multi.getFilesystemName("upPhoto3");
		String upPhoto4 = "/vc/imageUpload/"+multi.getFilesystemName("upPhoto4");

		
		// 추출파일 DTO에 저장
			neiBoardDTO ndto = new neiBoardDTO();
			ndto.setUserId(userId);
			ndto.setUserNickname(userNickname);
			ndto.setUserPhoto(userPhoto);
			ndto.setSubject(subject);
			ndto.setContent(content);
			ndto.setUpPhoto1(upPhoto1);
			ndto.setUpPhoto2(upPhoto2);	
			ndto.setUpPhoto3(upPhoto3);	
			ndto.setUpPhoto4(upPhoto4);	
		
		// dto에 저장되지 않는 시스템 데이터 추가 저장 			
			ndto.setWriteTime(new Timestamp(System.currentTimeMillis()));
			ndto.setIp(request.getRemoteAddr());  	//request 객체 영역에는 ip, 사용자 브라우저 정보등이 다 들어있다
				
			
		// 입력받은 글 DB에 삽입
			neiBoardDAO ndao = new neiBoardDAO();
			ndao.insertNeiBoard(ndto); 	

		// 이동경로 설정
			ActionForward forward = new ActionForward();
			forward.setRedirect(true); // true : 리다이렉트 방식으로 포워딩
			forward.setPath("./neighborComm.ne");		
		
		
		return forward;
	}

}
