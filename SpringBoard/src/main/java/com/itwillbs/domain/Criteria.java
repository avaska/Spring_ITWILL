package com.itwillbs.domain;

public class Criteria {
	// 페이징처리 하기위한 정보를 저장하는 객체 

	// 페이지 정보(시작지점)
	private int page;
	// 페이지 크기
	private int pageSize;
	
	//기본값(기본 생성자)
	public Criteria() {
		this.page = 1;
		this.pageSize = 10;
	}		
	
	// 사용자 정의 값 설정
	
	// 가져올 글의 시작 위치
	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
			return; //비정상적인 데이터 입력 시 1페이지로 고정하기
		}
		this.page = page;
	}
	
	
	public int getPage() {
		return page;
	}	
	
	// 한 번에 가져올 글의 개수를 지정
	public void setPageSize(int pageSize) {
		
		// 비정상적인 페이지 크기를 받았을 때 기본값으로 지정
		if(pageSize <= 0 || pageSize > 100) {
			this.pageSize = 10;
			return;
		}
		this.pageSize = pageSize;
		
	}
	
	// (* mapper에서 호출  #{pageSize} )
	public int getPageSize() {
		return pageSize;
	}
	
	
	// 0, 10, 20, 30,.....
	// 시작 데이터 번호 = (페이지 번호 - 1)*페이지에서 보여질 개수;
	// ex)page = (page - 1)*10;	
	// 1페이지 : 0,1,2...9번 글
	// 2페이지 : 10,11,12,....,19번 글
	// 3페이지 : 20,21,22,....,20번 글
	
	// limit - 시작값 계산 (* mapper에서 호출  #{pageStart})
	public int getPageStart() {
		return (this.page - 1)*pageSize;
	}
	
	
	
	// alt shift s + s
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", pageSize=" + pageSize + "]";
	}
	
}
