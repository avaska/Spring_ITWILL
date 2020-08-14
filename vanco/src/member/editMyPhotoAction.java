package member;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class editMyPhotoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		
		//세션 저장을 위한 객체 생성	
		HttpSession session = request.getSession();

		/*
		 * 
		 // 모델1에서는 이렇게 경로설정 했으나, 모델2는 약간달라짐
		 String uploadPath=request.getRealPath("imageProfile");		
		
		ServletContext ctx = getServletContext();*/
		/*String uploadPath=ctx.getRealPath("imageProfile");*/
		
		//1. 현재 실행중인 웹프로젝트 정보 컨텍스트 객체 생성후, 
		ServletContext ctx = request.getServletContext();
		
		
		//2. 컨텍스트 객체에 실제 업로드될 서버측 경로 넣어서 저장하기 
		String realPath	= ctx.getRealPath("imageProfile");
		
		//3. 업로드 파일 최대 크기 설정(10mb)
		int max = 1024*1024*10;
		
		// 파일 업로드객체 호출		
		MultipartRequest multi=new MultipartRequest(request,realPath,max,"utf-8",new DefaultFileRenamePolicy());
		
		
		//request에 담아온 수정 정보를 가져온다. 	
		String userId=multi.getParameter("userId");
			
		String existDogPic= multi.getParameter("existDogPic");	// 파일 업데이트 안했을 경우 기존 파일명 넣기 위한 작업
		String existUserPic= multi.getParameter("existUserPic");
			
		// 파일 인풋태그의 name속성으로 서버에 업로드된 파일명을 받아와 가상경로를 설정해준다.
		//이때 파일을 수정했으면, 새로운 파일명을 가져오고, 수정이 없으면 기존의 파일명을 셋팅한다.	
		String upDogPhoto = (multi.getFilesystemName("upDogPhoto")!=null)? "/vc/imageProfile/"+multi.getFilesystemName("upDogPhoto") :existDogPic ;
		String upUserPhoto = (multi.getFilesystemName("upUserPhoto")!=null)? "/vc/imageProfile/"+multi.getFilesystemName("upUserPhoto") :existUserPic ;

		// 추출파일 DB에 저장
			MemberDAO mdao = new MemberDAO();	
			
		int result = mdao.editMyPic(upDogPhoto, upUserPhoto, userId); 
			
		// 세션값에 새로운 user사진 저장
		session.setAttribute("userPhoto", upUserPhoto);
			
			
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true); // true : 리다이렉트 방식으로 포워딩
		forward.setPath("./mypage.me");		
		
		return forward;
	}

}
