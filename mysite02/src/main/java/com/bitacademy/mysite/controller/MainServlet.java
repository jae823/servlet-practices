package com.bitacademy.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.web.mvc.WebUtil;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	public void init() throws ServletException {
		String configpath = this.getServletConfig().getInitParameter("config");
		System.out.println("init() called" + configpath);
		super.init();
	}	
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service() called");
		super.service(req, resp);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() called");
		
		int visitCount = 0;
		
		//getServletContext().setAttribute(getServletName(), response); // 서버가 내려가기 전까지 살아있음
		
		//쿠키 읽기
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if("visitCount".equals(cookie.getName())) {
					visitCount = Integer.parseInt(cookie.getValue());
				}
			}
		}
		//System.out.println(visitCount);
		
		//쿠키 쓰기
		visitCount++;
		Cookie cookie = new Cookie("visitCount", String.valueOf(visitCount));
		// DomainName의 경우 Tomcat(?)에서 자동으로 생성해준다 .. 따라서 Path&expretime만 세팅해주면 된다. 
		
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(24*60*60); // 1day
		response.addCookie(cookie);
		
		WebUtil.forward("/WEB-INF/views/main/index.jsp", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


	@Override
	public void destroy() {
		
		System.out.println("distory() called");
		super.destroy();
	}

	
	
}
