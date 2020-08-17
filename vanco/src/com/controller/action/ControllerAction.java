package com.controller.action;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *	ControllerAction 
 *	
 *	Command.properties ?��?�� ?��?�� ?��/�? 불러?? 주소�? ?��?�� 처리
 *
 */

@SuppressWarnings("serial")
public class ControllerAction extends HttpServlet {
	
	private Map commandMap = new HashMap(); // 명령?��?? 명령?�� 처리 ?��?��?���? ?��?���? ???��
	
	public void init(ServletConfig config) throws ServletException {
		
		loadProperties("com/controller/action/Command");
		
	}
	
	private void loadProperties(String path) {
		// ?��구�?? ?��?��?���?�? rb?�� ???��
		ResourceBundle rbHome = ResourceBundle.getBundle(path);
		
		Enumeration<String> actionEnumHome = rbHome.getKeys();
		
		while (actionEnumHome.hasMoreElements()) {
			
			String command = actionEnumHome.nextElement();
			
			String className = rbHome.getString(command);
			
			try {
				// ?��?�� 문자?��?�� ?��?��?���? 만든?��
				Class commandClass = Class.forName(className);
				
				// ?��?�� ?��?��?��?�� 객체�? ?��?��
				Object commandInstance = commandClass.newInstance();
				
				commandMap.put(command, commandInstance);
				
			} catch (ClassNotFoundException e) {
				continue;
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
					  throws ServletException, IOException {
		requestPro(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
					   throws ServletException, IOException {
		requestPro(request, response);
	}
	
	public void requestPro(HttpServletRequest request, HttpServletResponse response)
						   throws ServletException, IOException {
		String view = null;
		CommandAction com = null;
		request.setCharacterEncoding("UTF-8");
		
		try {
			String command = request.getRequestURI();
			if(command.indexOf(request.getContextPath()) == 0) {
				command = command.substring(request.getContextPath().length());
			}
			
			com = (CommandAction) commandMap.get(command);
			
			if(com == null) {
				System.out.println("not found : " + command);
				return;
			}
			
			view = com.requestPro(request, response);
			if (view == null) {
				return;
			}
		
		} catch (Throwable e) {
			
			throw new ServletException(e);
		
		}

			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		
	}
	
}

