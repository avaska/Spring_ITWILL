package com.itwillbs.domain;

public class SampleVO {

	private String name;
	private String tel;
	private int age;
	
	// alt shift s + r
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
	
	// alt shift s + s
	@Override
	public String toString() {
		return "SampleVO [name=" + name + ", tel=" + tel + ", age=" + age + "]";
	}
	
	
	
}
