<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/includes/header.jsp' />
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="ã��">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>��ȣ</th>
						<th>����</th>
						<th>�۾���</th>
						<th>��ȸ��</th>
						<th>�ۼ���</th>
						<th>&nbsp;</th>
					</tr>				
					<tr>
						<td>3</td>
						<td><a href="">�� ��° ���Դϴ�.</a></td>
						<td>�ȴ���</td>
						<td>3</td>
						<td>2015-10-11 12:04:20</td>
						<td><a href="" class="del">����</a></td>
					</tr>
					<tr>
						<td>2</td>
						<!-- <td><a href="" style='text-align:left; padding-left:${(vo.depth-1)*20}px' >�� ��° ���Դϴ�.</a></td> -->
						<!-- depth�� 0���� Ŭ��� �̹��� �߰� -->
						<td><a href="" style='text-align:left; padding-left:20px' ><img src='${pageContext.request.contextPath }/assets/images/reply.png'/>�� ��° ���Դϴ�.</a></td>
						<td>�ȴ���</td>
						<td>3</td>
						<td>2015-10-02 12:04:12</td>
						<td><a href="" class="del">����</a></td>
					</tr>
					<tr>
						<td>1</td>
						<td><a href="" style='text-align:left; padding-left:40px' ><img src='${pageContext.request.contextPath }/assets/images/reply.png'/>ù ��° ���Դϴ�.</a></td>
						<td>�ȴ���</td>
						<td>3</td>
						<td>2015-09-25 07:24:32</td>
						<td><a href="" class="del">����</a></td>
					</tr>
				</table>
				
				<!-- pager �߰� -->
				<!-- ����������/������������ �߰��ϱ� ���ؼ� ����¡üũó�� -->
				<!-- �̸� ����� �����͸� map�� �����ؼ� �����ֱ� -->
				<div class="pager">
					<ul>
						<li><a href="">��</a></li>
						<li><a href="/mysite02/board?p=1">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">��</a></li>
					</ul>
				</div>					
				<!-- pager �߰� -->
				
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board?a=writeform" id="new-book">�۾���</a>
				</div>				
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp' />
		<c:import url='/WEB-INF/views/includes/footer.jsp' />
	</div>
</body>
</html>