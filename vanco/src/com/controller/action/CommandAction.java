package com.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *	Action ?��?��?��?�� 구현?��?�� 기본 CommandAction
 */

public interface CommandAction {

	public String requestPro(HttpServletRequest request, 
							HttpServletResponse response) throws Throwable;
}
