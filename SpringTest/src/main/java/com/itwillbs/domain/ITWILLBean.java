package com.itwillbs.domain;

public class ITWILLBean {
	// VO(Value Object) : 객체를 사용해서 데이터를 저장하고 이동시키기 위한 객체
	
	private String name;
	private String tel;
	private int age;	
	
	public ITWILLBean() { } //JavaBean 만들 때 기본 생성자는 필수로 만들어야 한다
	public ITWILLBean(String name, String tel, int age) {
		this.name = name;
		this.tel = tel;
		this.age = age;		
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "ITWILLBean [name=" + name + ", tel=" + tel + ", age=" + age + "]";
	}	
	
	
	
}
