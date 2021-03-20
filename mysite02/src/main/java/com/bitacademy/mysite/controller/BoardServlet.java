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
import com.bitacademy.mysite.vo.PageVo;
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
			Long userno = userVo.getNo();

			BoardVo boardVo = new BoardVo();
			boardVo.setTitle(title);
			boardVo.setContent(content);
			boardVo.setName(name);
			boardVo.setUserNo(userno);
			
			if(!request.getParameter("group_no").equals("")) {
				boardVo.setGroup_no(Integer.parseInt(request.getParameter("group_no")));
			}
			if(!request.getParameter("order_no").equals("")) {
				boardVo.setOrder_no(Integer.parseInt(request.getParameter("order_no")));
			}
			if(!request.getParameter("depth").equals("")) {
				boardVo.setDepth(Integer.parseInt(request.getParameter("depth")));
			}
			
			String reply = "N";
			if(!request.getParameter("reply").equals("")) {
				reply = request.getParameter("reply");
			}
			
			new BoardDao().insert(boardVo, reply);

			WebUtil.redirect(request.getContextPath()+"/board?a=index", request, response);

		} else if ("view".equals(action)) {
			Long no = Long.parseLong(request.getParameter("no")); 

			BoardVo boardVo = new BoardDao().findOne(no); 
			request.setAttribute("boardVo", boardVo);
			WebUtil.forward("/WEB-INF/views/board/view.jsp", request, response);
		} else if ("updateform".equals(action)) {
			Long no = Long.parseLong(request.getParameter("no"));

			BoardVo boardVo = new BoardDao().findOne(no); 
			request.setAttribute("boardVo", boardVo);
			WebUtil.forward("/WEB-INF/views/board/modify.jsp", request, response);
		} else if ("update".equals(action)) {
			Long no = Long.parseLong(request.getParameter("no"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			BoardVo boardVo = new BoardVo();
			boardVo.setNo(no);
			boardVo.setTitle(title);
			boardVo.setContent(content);
			new BoardDao().update(boardVo);

			WebUtil.redirect(request.getContextPath()+"/board", request, response);
		} else if ("delete".equals(action)) {
			Long no = Long.parseLong(request.getParameter("no"));

			new BoardDao().delete(no);
			WebUtil.redirect(request.getContextPath()+"/board", request, response);
		} else {
			BoardDao dao = new BoardDao();
			int nowPage = 1;
			String search = "";
			
			if(request.getParameter("p") != null) {
				nowPage = Integer.parseInt(request.getParameter("p"));	
			}
			
			int totalCount = 0;
			List<BoardVo> list = null;
			PageVo pageVo = null;
			
			if(request.getParameter("search") != null) {
				search = request.getParameter("search");
				totalCount = dao.searchTotalCount(search);
				pageVo = new PageVo(totalCount, 5, nowPage);
				list = dao.search(search, pageVo);
			} else {
				totalCount = dao.getTotalCount();
				pageVo = new PageVo(totalCount, 5, nowPage); 
				list = dao.findAll(pageVo);
				for(int i=0; i < list.size(); i++) {
					BoardVo vo = (BoardVo) list.get(i);
					if(vo.getDel_gb().equals("Y")) {
						list.get(i).setTitle("삭제된 게시글 입니다.");
					}
				}
			}
	
//				j = i;
				
//				while(groupFlag) {
//					if(vo.getGroup_no() != list.get(++j).getGroup_no()) {
//						groupFlag = false;
//					}
//					if(vo.getDel_gb() == list.get(j).getDel_gb()) {
//						
//					}
//				}
			
			
			request.setAttribute("list", list);
			request.setAttribute("pageVo", pageVo);
			request.setAttribute("search", search);
			WebUtil.forward("WEB-INF/views/board/index.jsp", request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
