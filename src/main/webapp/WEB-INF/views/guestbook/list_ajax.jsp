<%--
  Created by IntelliJ IDEA.
  User: 이상화
  Date: 2018-05-02
  Time: 오전 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="/assets/js/jquery/jquery-1.12.4.js"></script>

    <link href="/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
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
                            <td>이름</td>
                            <td><input id="name" type="text" name="name" value="${sessionScope.authUser.name}"/></td>
                            <td>비밀번호</td>
                            <td><input id="password" type="password" name="password"/></td>
                        </tr>
                        <tr>
                            <td colspan=4><textarea name="content" id="content"></textarea></td>
                        </tr>
                        <tr>
                            <td colspan=4 align=right><input id="btnAdd" type="submit" VALUE=" 확인 "/></td>
                        </tr>
                    </table>
                </form>
                <ul id="guestbook-list">
                    <%--<li>--%>
                    <%--<table>--%>
                    <%--&lt;%&ndash;반복문&ndash;%&gt;--%>
                    <%--<c:forEach items="${guestbooklist}" var="boardlist">--%>
                    <%--<tr>--%>
                    <%--<td>[${boardlist.no}]</td>--%>
                    <%--<td>${boardlist.name}</td>--%>
                    <%--<td>${boardlist.date}</td>--%>
                    <%--<td><a href="/guestbookdeleteform/${boardlist.no}">삭제</a></td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                    <%--<td colspan=4>${boardlist.content}</td>--%>
                    <%--</tr>--%>
                    <%--&lt;%&ndash;반복끝&ndash;%&gt;--%>
                    <%--</c:forEach>--%>
                    <%--</table>--%>
                    <%--<br>--%>
                    <%--</li>--%>
                </ul>

            </div><!-- /guestbook -->
        </div><!-- /content -->
    </div><!-- /wrapper -->

    <c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

</div> <!-- /container -->


<script type="text/javascript"
        src="/assets/bootstrap/js/bootstrap.min.js"></script>


<!-- 삭제팝업(모달)창 -->
<div class="modal fade" id="del-pop">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">방명록삭제</h4>
            </div>
            <div class="modal-body">
                <label>비밀번호</label> <input type="password" name="modalPassword" id="modalPassword"><br>
                <input type="hidden" name="modalPassword" value="" id="modalNo"> <br>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
                <button type="button" class="btn btn-danger" id="btn_del">삭제</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->


</body>
<script type="text/javascript">



    // $("#btnmodal").on("click",function () {
    //     console.log("모달");
    //     $("#del-pop").modal();
    // })


    $("ul").on("click", "input", function () {
        event.preventDefault();
        console.log("삭제버튼");
        $("#del-pop").modal();
        var dom = $(this);
        console.log(dom);
        $("#modalNo").val(dom.attr("id"));

    })


    $("#btn_del").on("click", function () {
        var no = $("#modalNo").val();
        var password = $("#modalPassword").val();

        $.ajax({
            url: "/api/guestbook/delete",
            type: "post",
            //contenttype,data
            data: {no: no, password: password},

            dataType: "json",
            success: function (result) {
                console.log(result);
                if (result == true) {
                    var del = "del" + no;
                    $("#" + del).remove();
                }else{
                    console.log("안지워졌으면 DB에서 삭제가 안됬나보지"+result);
                }
                $("#modalNo").val("");
                $("#modalPassword").val("");
            },
            error:

                function (XHR, status, error) {
                    console.error(status + " : " + error);
                }
        })
        $("#del-pop").modal("hide");
    })


    $("#btnAdd").on("click", function () {
        event.preventDefault();
        console.log("btnAdd");
        var text = $("textarea").val();
        var result = text.replace(/(\n|\r\n)/g, '<br>');
        console.log(result);


        var name = $("[name=name]").val();
        var password = $("[name=password]").val();
        // var content = $("[name=content]").val();
        console.log(name);
        console.log(password);
        // console.log(content);
        // guestbookVO = {name: $("[name=name]").val(), password: $("[name=password]").val(), content: $("[name=content]").val()}
        $.ajax({
            url: "/api/guestbook/add",
            type: "post",
            // contentType:"application/json",
            // data: JSON.stringify(guestbookVO),
            data:{name:name,password:password,content:result},

            dataType: "json",
            success: function (guestbookVO) {
                render(guestbookVO, "up");
                $("[name=name]").val("");
                $("[name=password]").val("");
                $("[name=content]").val("");
            },
            error: function (XHR, status, error) {
                console.error(status + " : " + error);
            }
        })
    })




    $(document).ready(function () {
        number = 1;
        console.log(number);
        fetchList(number);
    })

    function fetchList(number) {
        $.ajax({
            url: "/api/guestbook/list",
            type: "get",
            data:{num:number},
            //contenttype,data

            dataType: "json",
            success: function (list) {
                for (var i = 0; i < 10; i++) {
                    console.log(list[i]);
                    render(list[i], "down");
                }
            },
            error: function (XHR, status, error) {
                console.error(status + " : " + error);
            }
        })
    }

    function render(guestbookVO, updown) {
        var str = "";
        str += "<li id='del" + guestbookVO.no + "'>";
        str += "    <table>";
        str += "        <tr>";
        str += "            <td>[" + guestbookVO.no + "]</td>";
        str += "            <td>" + guestbookVO.name + "</td>";
        str += "            <td><input type='button' id='" + guestbookVO.no + "'value=삭제></td>";
        str += "        </tr>";
        str += "        <tr>";
        str += "            <td colspan=4>";
         // var text = guestbookVO.content;
        // var result = text.replace(/(<br>|<br\/>|<br \/>)/g, '<br>');
         str += guestbookVO.content;
        str += "            </td>";
        str += "        </tr>";
        str += "    </table>";
        str += "    <br>";
        str += "</li>";
        if (updown == "up") {
            $("#guestbook-list").prepend(str);
        } else if (updown == "down") {
            $("#guestbook-list").append(str);
        } else {
            console.log("오류")
        }
    }

    $(window).on("scroll",function () {
        console.log(number);
        // console.log($(window).scrollTop());
        // console.log($(document).height());
        // console.log($(window).height());

        if(Math.round($(window).scrollTop()) == $(document).height() - $(window).height()){
            number+=10;
            console.log(number);
            fetchList(number);
        }
    })




    // /api/guestbook/delete?no'+guestbookVO.no+'&password='+$('[name=modalPassword]').val()
</script>
</html>