<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="./assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<title>Insert title here</title>
</head>
<body>
	<div id="container">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="wrapper">
			<div id="content">
				<div id="guestbook">
					
					<form action="/guestbookinsert" method="post">
						<table>
							<tr>
								<td>이름</td><td><input type="text" name="name" value="${sessionScope.authUser.name}"/></td>
								<td>비밀번호</td><td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE=" 확인 " /></td>
							</tr>
						</table>
					</form>
					<ul>
						<li>
							<table>
								<%--반복문--%>
								<c:forEach items="${guestbooklist}" var="boardlist">
								<tr>
                                    <td>[${boardlist.no}]</td>
                                    <td>${boardlist.name}</td>
                                    <td>${boardlist.date}</td>
									<td><a href="/guestbookdeleteform/${boardlist.no}">삭제</a></td>
								</tr>
								<tr>
                                    <td colspan=4>${boardlist.content}</td>
								</tr>
								<%--반복끝--%>
								</c:forEach>
							</table>
							<br>
						</li>
					</ul>
					
				</div><!-- /guestbook -->
			</div><!-- /content -->
		</div><!-- /wrapper -->

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div> <!-- /container -->

</body>
</html>