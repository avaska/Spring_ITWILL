package com.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *	Action ?´?˜?Š¤?— êµ¬í˜„?•˜?Š” ê¸°ë³¸ CommandAction
 */

public interface CommandAction {

	public String requestPro(HttpServletRequest request, 
							HttpServletResponse response) throws Throwable;
}
