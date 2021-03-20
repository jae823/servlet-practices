package com.bitacademy.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.mysite.dao.GuestbookDao;
import com.bitacademy.mysite.vo.GuestbookVo;
import com.bitacademy.web.mvc.WebUtil;

public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("a");
		if ("deleteform".equals(action)) {
			//response.getWriter().print("deleteform");
			WebUtil.forward("/WEB-INF/views/guestbook/deleteform.jsp", request, response);
		} else if ("insert".equals(action)) {
			//response.getWriter().print("add");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(content);
			new GuestbookDao().insert(vo);
			WebUtil.redirect(request.getContextPath()+"/guestbook?a=list", request, response);
		} else if ("delete".equals(action)) {
			//response.getWriter().print("delete");
			String no = request.getParameter("no");
			String password = request.getParameter("password");
			GuestbookVo vo = new GuestbookVo();
			vo.setNo(Long.parseLong(no));
			vo.setPassword(password);
			new GuestbookDao().delete(vo);
			WebUtil.redirect(request.getContextPath()+"/guestbook?a=list", request, response);
		} else {
			List<GuestbookVo> list = new GuestbookDao().findAll();
			request.setAttribute("list", list);
			// forwarding = request dispatch = request extension (여기서 매개변수로 받는 request가 jsp에서 사용하는 request와 동일한 request이다)
			WebUtil.forward("/WEB-INF/views/guestbook/list.jsp",request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
