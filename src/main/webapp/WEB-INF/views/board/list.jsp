<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>mysite</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link href="/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">

    <c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
    <c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>

    <div id="content">
        <div id="board">
            <form id="search_form" action="/searchkwd" method="post">
                <input type="text" id="kwd" name="kwd" value="">
                <input type="submit" value="찾기">
            </form>
            <table class="tbl-ex">

                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>조회수</th>
                    <th>작성일</th>
                    <th>&nbsp;</th>
                </tr
                <%--for문 시작--%>
                <c:forEach items="${map.list}" var="boardlist">
                    <tr>
                        <td>${boardlist.no}</td>
                        <td>
                            <a href="/boardview/${boardlist.no}">${boardlist.title}</a>
                        </td>
                        <td>${boardlist.name}</td>
                        <td>${boardlist.hit}</td>
                        <td>
                                ${boardlist.reg_date}
                        </td>
                        <td>
                            <c:if test="${authUser.no eq boardlist.user_no}">
                                <a href="/boarddelete/${boardlist.no}" class="del">삭제</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>

            </table>

            <div class="pager">
                <ul>
                    <c:if test="${map.crtPage > map.pageBtnCount}">
                    <li><a href="/boardlist/${map.startPageBtnNo-1}">◀</a></li>
                    </c:if>

                    <c:forEach begin="${map.startPageBtnNo}" end="${map.endPageBtnNo}" step="1" var="i">
                        <c:if test="${map.crtPage ne i}">
                            <li><a href="/boardlist/${i}">${i}</a></li>
                        </c:if>
                        <c:if test="${map.crtPage eq i}">
                            <li><a class="selected">${i}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${map.endPageBtnNo*10 < map.totalCount}">
                    <li><a href="/boardlist/${map.endPageBtnNo+1}">▶</a></li>
                    </c:if>
                </ul>
            </div>
            <div class="bottom">
                <a href="/boardwriteform" id="new-book">글쓰기</a>
            </div>
        </div>
    </div>

    <c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

</div>
</body>
</html>