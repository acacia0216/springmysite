<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
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
            <div id="guestbook" class="delete-form">

                <form method="post" action="/delete/${no}">
                    <label>비밀번호</label>
                    <input type="password" name="password">
                    <input type="submit" value="확인">
                </form>
                <a href="/guestbooklist">방명록 리스트</a>

            </div>
        </div><!-- /content -->
    </div><!-- /wrapper -->

    <c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

</div> <!-- /container -->

</body>
</html>
