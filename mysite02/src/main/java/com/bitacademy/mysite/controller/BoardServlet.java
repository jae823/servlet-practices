package com.bitacademy.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitacademy.mysite.dao.BoardDao;
import com.bitacademy.mysite.vo.BoardVo;
import com.bitacademy.mysite.vo.UserVo;
import com.bitacademy.web.mvc.WebUtil;

public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String action = request.getParameter("a");
		UserVo userVo = null;
		
		if ("writeform".equals(action)) {
			WebUtil.forward("/WEB-INF/views/board/write.jsp", request, response);
		} else if ("write".equals(action)) {
			HttpSession session = request.getSession();
			if(session != null && session.getAttribute("authUser") != null) {
				userVo = (UserVo) request.getSession(false).getAttribute("authUser");
			} else {
				WebUtil.forward("/WEB-INF/views/user/loginform.jsp", request, response);
				return;
			}

			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String name = userVo.getName();

			BoardVo boardVo = new BoardVo();
			boardVo.setTitle(title);
			boardVo.setContent(content);
			boardVo.setName(name);
			new BoardDao().insert(boardVo);

			WebUtil.redirect(request.getContextPath()+"/board?a=index", request, response);

		} else if ("update".equals(action)) {

		} else if ("delete".equals(action)) {

		} else if ("".equals(action)) {

		} else {
			List<BoardVo> list = new BoardDao().findAll();
			request.setAttribute("list", list);
			WebUtil.forward("WEB-INF/views/board/index.jsp", request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
