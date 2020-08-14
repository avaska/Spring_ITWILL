package neighborComm;

import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class moimMakingAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		
		//1. 현재 실행중인 웹프로젝트 정보 컨텍스트 객체 생성후, 
		ServletContext ctx = request.getServletContext();
		
		//2. 컨텍스트 객체에 실제 업로드될 서버측 경로 넣어서 저장하기 
		String realPath	= ctx.getRealPath("images/moim/");
		System.out.println(realPath);
		
		//3. 업로드 파일 최대 크기 설정(10mb)
		int max = 1024*1024*10;
		
		MultipartRequest multi=new MultipartRequest(request,realPath,max,"utf-8",new DefaultFileRenamePolicy());
		
		
		//request에 담아온 입력정보와 히든정보를 모두 가져온다.
		String userId=multi.getParameter("userId");
		String moimCategory=multi.getParameter("moimCategory");
		String moimTitle=multi.getParameter("moimTitle");
		String moimIntro=multi.getParameter("moimIntro");
		String userCity=multi.getParameter("userCity");
		String userDistrict=multi.getParameter("userDistrict");
		
		
		// 파일 인풋태그의 name속성으로 서버에 업로드된 파일명을 받아와 가상경로를 설정해준다.
		String moimPhoto = "/vc/images/moim/"+multi.getFilesystemName("moimPhoto");
		

		
		// 추출파일 DTO에 저장
			moimDTO mdto = new moimDTO();
			mdto.setUserId(userId);
			mdto.setMoimCategory(moimCategory);
			mdto.setMoimTitle(moimTitle);
			mdto.setMoimIntro(moimIntro);
			mdto.setMoimPhoto(moimPhoto);
			mdto.setUserCity(userCity);
			mdto.setUserDistrict(userDistrict);
		
		// dto에 저장되지 않는 시스템 데이터 추가 저장 			
			mdto.setMakingTime(new Timestamp(System.currentTimeMillis()));
			
		// DB에 삽입
			neiBoardDAO ndao = new neiBoardDAO();
			ndao.insertMoim(mdto); 	

		// 이동경로 설정
			ActionForward forward = new ActionForward();
			forward.setRedirect(true); // true : 리다이렉트 방식으로 포워딩
			forward.setPath("./neighborComm.ne");		
		
		
		return forward;
	}

}
