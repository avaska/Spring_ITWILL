package com.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *	Action ?΄??€? κ΅¬ν?? κΈ°λ³Έ CommandAction
 */

public interface CommandAction {

	public String requestPro(HttpServletRequest request, 
							HttpServletResponse response) throws Throwable;
}
