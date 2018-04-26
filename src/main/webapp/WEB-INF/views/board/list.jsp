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
            <table class="tbl-ex" style=TABLE-layout:fixed;>

                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>조회수</th>
                    <th>작성일</th>
                    <th>&nbsp;</th>
                </tr
                <%--for문 시작--%>
                <c:forEach items="${boardlist}" var="boardlist">
                    <tr>
                        <td>${boardlist.no}</td>
                        <td style="text-overflow : ellipsis;overflow : hidden;">
                            <nobr><a href="/boardview/${boardlist.no}">${boardlist.title}</a></nobr>
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
            <%--${requestScope.allPage.size()/10}--%>
            <%--<div class="pager">--%>
                <%--<ul>--%>
                    <%--<c:if test="${5개 페이지보다 작은 페이지가 있으면}">--%>
                    <%--<li><a href="">◀</a></li>--%>
                    <%--</c:if>--%>
                    <%--<c:forEach items="${requestScope.allPage.size()/10}" var="number">--%>
                        <%--<c:if test="${number}">--%>
                            <%--<li><a class="selected">${number}</a></li>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${number}">--%>
                            <%--<li><a href="/board&page=${number}">${number}</a></li>--%>
                        <%--</c:if>--%>
                    <%--</c:forEach>--%>
                    <%--<c:if test="${5개 페이지보다 큰 페이지가 있으면}">--%>
                    <%--<li><a href="">▶</a></li>--%>
                    <%--</c:if>--%>
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