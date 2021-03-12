<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	String color = (String)request.getParameter("color");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		if("red".equals(color)) {
	%>
		<h1 style='color:red'>Hello JSTL</h1>
	<%
		}
	%>
	<%
		if("blue".equals(color)) {
	%>
		<h1 style='color:blue'>Hello JSTL</h1>
	<%
		}
	%>
	<%
		if("green".equals(color)) {
	%>
		<h1 style='color:green'>Hello JSTL</h1>
	<%
		}
	%>

	<h1 style="color:${param.c }">Hello JSTL</h1>
	
</body>
</html>