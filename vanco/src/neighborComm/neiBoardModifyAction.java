package neighborComm;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class neiBoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//neiBoardWrite.jsp에서 전달된 사진데이터+업로드 파일 정보는 request에 저장되어 있으므로 모든 전달값을 저장한다.
		// 그러나, 해당 정보들은 MultipartRequest타입이기 때문에 request.getParameter로는 받을 수 없다.

		request.setCharacterEncoding("utf-8");

		//1. 현재 실행중인 웹프로젝트 정보 컨텍스트 객체 생성후, 
		ServletContext ctx = request.getServletContext();
		
		
		//2. 컨텍스트 객체에 실제 업로드될 서버측 경로 넣어서 저장하기 
		String realPath	= ctx.getRealPath("imageUpload");
		
		//3. 업로드 파일 최대 크기 설정(10mb)
		int max = 1024*1024*10;
		
		// 파일 업로드객체 호출		
		MultipartRequest multi=new MultipartRequest(request,realPath,max,"utf-8",new DefaultFileRenamePolicy());
		
		
		//request에 담아온 수정 정보를 가져온다. 	
		String subject=multi.getParameter("subject");
		String content=multi.getParameter("content");
		String contentNum= multi.getParameter("num");	
		String pageNum= multi.getParameter("pageNum");
		
		String existPic1= multi.getParameter("existPic1");	// 파일 업데이트 안했을 경우 기존 파일명 넣기 위한 작업
		String existPic2= multi.getParameter("existPic2");
		String existPic3= multi.getParameter("existPic3");
		String existPic4= multi.getParameter("existPic4");
		
		int num = Integer.parseInt(contentNum);
			
		// 파일 인풋태그의 name속성으로 서버에 업로드된 파일명을 받아와 가상경로를 설정해준다.
		//이때 파일을 수정했으면, 새로운 파일명을 가져오고, 수정이 없으면 기존의 파일명을 셋팅한다.	
		String upPhoto1 = (multi.getFilesystemName("upPhoto1")!=null)? "/vc/imageUpload/"+multi.getFilesystemName("upPhoto1") :existPic1 ;
		String upPhoto2 = (multi.getFilesystemName("upPhoto2")!=null)? "/vc/imageUpload/"+multi.getFilesystemName("upPhoto2") :existPic2 ;
		String upPhoto3 = (multi.getFilesystemName("upPhoto3")!=null)? "/vc/imageUpload/"+multi.getFilesystemName("upPhoto3") :existPic3 ;
		String upPhoto4 = (multi.getFilesystemName("upPhoto4")!=null)? "/vc/imageUpload/"+multi.getFilesystemName("upPhoto4") :existPic4 ;

		// 추출파일 DTO에 저장
			neiBoardDTO ndto = new neiBoardDTO();
			ndto.setSubject(subject);
			ndto.setContent(content);
			ndto.setUpPhoto1(upPhoto1);
			ndto.setUpPhoto2(upPhoto2);	
			ndto.setUpPhoto3(upPhoto3);	
			ndto.setUpPhoto4(upPhoto4);	
			
		// 입력받은 글 DB에 삽입
			neiBoardDAO ndao = new neiBoardDAO();
			ndao.modifyContent(ndto, num); 	

			ActionForward forward = new ActionForward();
			forward.setRedirect(true); // true : 리다이렉트 방식으로 포워딩
			forward.setPath("./neighborComm.ne");		
		

		return forward;
	}

}
