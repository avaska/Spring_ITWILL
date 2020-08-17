package timeLine;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class timeLineWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		ServletContext ctx = request.getServletContext();
		
		//2. 컨텍스트 객체에 실제 업로드될 서버측 경로 넣어서 저장하기 
		String realPath	= ctx.getRealPath("imageUpload");
		System.out.println(realPath);
		//3. 업로드 파일 최대 크기 설정(10mb)
		int max = 1024*1024*10;		
		
		MultipartRequest multi=new MultipartRequest(request,realPath,max,"utf-8",new DefaultFileRenamePolicy());
		
		//request에 담아온 입력정보와 히든정보를 모두 가져온다.
		String userId=multi.getParameter("userId");	
		String content=multi.getParameter("content");		
		
		// 파일 인풋태그의 name속성으로 서버에 업로드된 파일명을 받아와 가상경로를 설정해준다.
		String upPhoto1 = "/vc/imageUpload/"+multi.getFilesystemName("upPhoto1");
		String upPhoto2 = "/vc/imageUpload/"+multi.getFilesystemName("upPhoto2");
		String upPhoto3 = "/vc/imageUpload/"+multi.getFilesystemName("upPhoto3");
		String upPhoto4 = "/vc/imageUpload/"+multi.getFilesystemName("upPhoto4");
		
		// 추출파일 DTO에 저장 (사진1~4까지는 개별 DTO가 없으므로 임시로 다른 String DTO에 저장시켜서 보낸다)
		timeLineDTO tdto = new timeLineDTO();
		tdto.setUserId(userId);
		tdto.setContent(content);
		tdto.setPhotoUrl(upPhoto1);			
		tdto.setIp(upPhoto2);	// PhotoUrl2 대신
		tdto.setTitle(upPhoto3);	// PhotoUrl3 대신
		tdto.setReOwnerNick(upPhoto4);	// PhotoUrl4 대신
		
		// 입력받은 글 DB에 삽입
		timeLineDAO tdao = new timeLineDAO();
		tdao.insertTimeLine(tdto); 
		
		ActionForward forward=new ActionForward();	
		forward.setRedirect(true); // true : 리다이렉트 방식으로 포워딩
		forward.setPath("timeLine.ti");		
		return forward;
		
	}

}
