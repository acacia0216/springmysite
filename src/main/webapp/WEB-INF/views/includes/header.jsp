<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 이상화
  Date: 2018-04-19
  Time: 오전 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="header">
    <h1><b><a href="/index">MySite</a></b></h1>
    <ul>
        <c:if test="${sessionScope.authUser eq null}">
        <!-- 로그인 전 -->
        <li><a href="/loginform">로그인</a></li>
        <li><a href="/joinform">회원가입</a></li>
        </c:if>
        <c:if test="${sessionScope.authUser ne null}">
        <!-- 로그인 후 -->
        <li><a href="/modifyform">회원정보수정</a></li>
        <li><a href="/logout">로그아웃</a></li>
        <li> <b>${sessionScope.authUser.name}</b>님 안녕하세요^^;</li>
        </c:if>
    </ul>
</div>
<!-- /header -->