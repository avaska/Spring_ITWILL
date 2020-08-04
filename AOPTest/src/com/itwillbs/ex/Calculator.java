package com.itwillbs.ex;

public class Calculator {
	
	//사칙연산을 하는 객체
	
	// +
	public void add(int x, int y) {
		System.out.println("계산 결과 : " + (x + y));
	}
	
	// -
	public void sub(int x, int y) {
		System.out.println("계산 결과 : " + (x - y));
	}
	
	// * 
	public void mul(int x, int y) {
		System.out.println("계산 결과 : " + (x * y));
	}
	
	// /
	public void div(int x, int y) {
		System.out.println("계산 결과 : " + (x / y));
	}

}
