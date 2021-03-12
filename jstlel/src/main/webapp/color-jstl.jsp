<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<c:choose>
		<c:when test="${'red'== param.c }">
			<h1 style='color: red'>Hello JSTL</h1>
		</c:when>
		<c:when test="${'blue'== param.c }">
			<h1 style='color: blue'>Hello JSTL</h1>
		</c:when>
		<c:when test="${'green'== param.c }">
			<h1 style='color: green'>Hello JSTL</h1>
		</c:when>
		<c:otherwise>
			<h1>Hello JSTL</h1>
		</c:otherwise>
	</c:choose>

</body>
</html>