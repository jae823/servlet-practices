<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>�� �޾ƺ���</h1>
	${iVal } <br/>
	${lVal } <br/>
	${fVal } <br/>
	${bVal } <br/>
	${sVal } <br/>
	
	<h1>��ü �޾ƺ���</h1>
	
	${vo.no } <br/>
	${vo.name } <br/>
	
	${obj } <br/>

	<h1>�������</h1>
	${3*10+5 } <br/>
	${iVal+5 } <br/>
	
	<h1>���迬��</h1>
	${iVal == 10 }<br/>
	${iVal < 10 }<br/>
	${obj == null }<br/>
	${obj != null }<br/>
	${empty obj}<br/>
	${not empty obj }<br/>
	
	<h1>������</h1>
	${iVal == 10 && lVal < 10000 }<br/>
	${iVal < 5 || lVal - 10 == 0 }<br/>
	
	
	<h1>��û �Ķ����</h1>
	${param.a } <br/>
	${param.email } <br/>
	
	
	<h1>Map���� �� �޾ƺ���</h1>
	${map.iVal } <br/>
	${map.fVal } <br/>
	${map.bVal } <br/>
	${map.sVal } <br/>
	
	
	
</body>
</html>