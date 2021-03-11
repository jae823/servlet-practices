<%@page import="com.bitacademy.emaillist.dao.EmaillistDao"%>
<%@page import="com.bitacademy.emaillist.vo.EmaillistVo"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	String firstName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	String email = request.getParameter("email");
	EmaillistVo vo = new EmaillistVo();
	vo.setFirst_name(firstName);
	vo.setLast_name(lastName);
	vo.setEmail(email);
	new EmaillistDao().insert(vo);
	
	response.sendRedirect("/emaillist01");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>성공적으로 등록 되었습니다.</h1>
</body>
</html>