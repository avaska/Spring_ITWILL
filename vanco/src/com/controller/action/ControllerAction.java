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
 *	Command.properties ??Ό ?? ?€/κ°? λΆλ¬?? μ£Όμκ°? ?? μ²λ¦¬
 *
 */

@SuppressWarnings("serial")
public class ControllerAction extends HttpServlet {
	
	private Map commandMap = new HashMap(); // λͺλ Ή?΄?? λͺλ Ή?΄ μ²λ¦¬ ?΄??€λ₯? ??Όλ‘? ???₯
	
	public void init(ServletConfig config) throws ServletException {
		
		loadProperties("com/controller/action/Command");
		
	}
	
	private void loadProperties(String path) {
		// ?κ΅¬λ?? ?€?? μ§?λ₯? rb? ???₯
		ResourceBundle rbHome = ResourceBundle.getBundle(path);
		
		Enumeration<String> actionEnumHome = rbHome.getKeys();
		
		while (actionEnumHome.hasMoreElements()) {
			
			String command = actionEnumHome.nextElement();
			
			String className = rbHome.getString(command);
			
			try {
				// ?΄?Ή λ¬Έμ?΄? ?΄??€λ‘? λ§λ ?€
				Class commandClass = Class.forName(className);
				
				// ?΄?Ή ?΄??€? κ°μ²΄λ₯? ??±
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

