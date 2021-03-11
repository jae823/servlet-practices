package com.bitacademy.guestbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.guestbook.dao.GuestbookDao;
import com.bitacademy.guestbook.vo.GuestbookVo;
import com.bitacademy.web.mvc.WebUtil;

public class GuestbookServlet extends HttpServlet {
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
		if("list".equals(action)) {
			//response.getWriter().print("list");
			WebUtil.forward("/WEB-INF/views/form.jsp",request, response);
		} else if ("deleteform".equals(action)) {
			//response.getWriter().print("deleteform");
			WebUtil.forward("/WEB-INF/views/deleteform.jsp", request, response);
		} else if ("add".equals(action)) {
			//response.getWriter().print("add");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("message");
			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContent(content);
			new GuestbookDao().insert(vo);
			WebUtil.redirect(request.getContextPath()+"/gb", request, response);
		} else if ("delete".equals(action)) {
			//response.getWriter().print("delete");
			String no = request.getParameter("no");
			String password = request.getParameter("password");
			GuestbookVo vo = new GuestbookVo();
			vo.setNo(Long.parseLong(no));
			vo.setPassword(password);
			new GuestbookDao().delete(vo);
			WebUtil.redirect(request.getContextPath()+"/gb", request, response);
		} else {
			List<GuestbookVo> list = new GuestbookDao().findAll();
			
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
