package com.bitacademy.emaillist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.emaillist.dao.EmaillistDao;
import com.bitacademy.emaillist.vo.EmaillistVo;
import com.bitacademy.web.mvc.WebUtil;


public class EmaillistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		현재 사용할 Url 방법
		req1: /emaillist02/el?a=list
		req2: /emaillist02/el?a=form
		req3: /emaillist02/el?a=add
		*/
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("a");
		if("form".equals(action)) {
			//response.getWriter().print("form");
			WebUtil.forward("/WEB-INF/views/form.jsp",request, response);
		} else if ("add".equals(action)) {
			//response.getWriter().print("add");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			EmaillistVo vo = new EmaillistVo();
			vo.setFirst_name(firstName);
			vo.setLast_name(lastName);
			vo.setEmail(email);
			new EmaillistDao().insert(vo);
			
			WebUtil.redirect(request.getContextPath()+"/el", request, response);
		} else {
			List<EmaillistVo> list = new EmaillistDao().findAll();
			
			request.setAttribute("list", list);
			// forwarding = request dispatch = request extension (여기서 매개변수로 받는 request가 jsp에서 사용하는 request와 동일한 request이다)
			WebUtil.forward("/WEB-INF/views/index.jsp",request, response);
		}
		
		/*
		req1: /emaillist02/el/list
		req2: /emaillist02/el/form
		req3: /emaillist02/el/add
		만약 Url을 위처럼 만들고 싶다면 아래방법을 써야한다. 	 
		String uri = request.getRequestURI();
		String[] paths = uri.split("/");
		String action = paths[paths.length-1];
		*/
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
