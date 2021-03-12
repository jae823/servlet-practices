package com.bitacademy.mysite.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
	private static final long serialVersionUID = 1L;

	
	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// request 처리 
		request.setCharacterEncoding("utf-8");
		
		chain.doFilter(request, response);
		
		// response 처리
	}

	public void destroy() {
	}
}
	
//	public void init(FilterConfig fConfig) throws ServletException {
//		
//	}
//	
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
//		// request 처리
//		
//		chain.doFilter(request, response);
//		
//		// response 처리
//	}
//		
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}


