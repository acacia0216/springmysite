<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="/assets/js/jquery/jquery-1.12.4.js"></script>
    <link href="/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="./assets/css/gallery.css" rel="stylesheet" type="text/css">
    <title>Mysite</title>
</head>
<body>

<div id="container">

    <c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
    <c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>

    <div id="wrapper">
        <div id="content">
            <div id="gallery">

                <c:if test="${authUser ne null}">
                    <form method="post" action="/upload"
                          enctype="multipart/form-data">
                        <table>
                            <tr>
                                <td colspan="3">
                                    제목 : <input type="text" name="imgtitle" size="100%">
                                </td>
                            </tr>
                            <tr>
                                <td>첨부파일</td>
                                <td><input type="file" name="file"></td>
                                <td><input type="submit" value="파일업로드">
                                    <input type="hidden" name="user_no" value="${authUser.no}">
                                    <input type="hidden" id="user_no_check" name="user_no" value="${authUser.no}"></td>
                            </tr>
                        </table>
                    </form>
                </c:if>

                <ul>
                    <c:forEach items="${list}" var="fileVo">
                        <li id="title${fileVo.no}" class="${fileVo.title}">${fileVo.title}
                            <div>
                                <a id="${fileVo.saveName}" value="${fileVo.no}"><img
                                        src="${pageContext.request.contextPath }/upload/${fileVo.saveName}"></a>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>


        </div><!-- /content -->
    </div><!-- /wrapper -->

    <c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

</div><!-- /container -->

<script type="text/javascript"
        src="/assets/bootstrap/js/bootstrap.min.js"></script>
<!-- 삭제팝업(모달)창 -->
<div class="modal fade" id="view_modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <%--<h4 class="modal-title"></h4>--%>
            </div>
            <div class="modal-body">
                <div id="imgview">
                    <img id="view" src="" style="width:500px">
                    <input type="hidden" id="img_del">
                </div>
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>--%>
                <%--<button type="button" class="btn btn-danger" id="btn_del">삭제</button>--%>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

</body>
<script type="text/javascript">

    $("li").on("click", "a", function () {
        var name = $(this).attr("id");
        console.log(name);
        $("#view_modal").modal();
        $("#view").attr("src", "upload/" + name);

        var no = $(this).attr("value");
        $("#img_del").val(no);
        console.log("클릭한 이미지 히든에 저장된 값 : " + $("#img_del").val());

        var title = $("#title"+no).attr("class");
        console.log("타이틀 : "+title);
        var titlestr = "<h4 class='modal-title'>"+title+"</h4>";
        $(".modal-body").prepend(titlestr);

        var user_no = $("#user_no_check").val();
        $.ajax({
            url: "/delbutton",
            data: {user_no:user_no,no:no},
            type: "post",

            dataType: "json",
            success: function (result) {
                if (result == true) {
                    var str = "";
                    str += "<button type='button' class='btn btn-danger' id='btn_del'>삭제</button>";
                     $(".modal-footer").append(str);
                     console.log("일치")
                } else {
                    console.log("유저정보 불일치");
                }
            }, error: function (XHR, status, error) {
                console.error(status + " : " + error);
            }
        })
    })


    $(".modal-footer").on("click","button", function () {
        var no = $("#img_del").val();
        // var user_no =
        $.ajax({
            url: "/imgdel",
            data: {no: no},
            type: "post",

            dataType: "json",
            success: function (result) {
                if (result == "") {
                    console.log("안지워졌다")
                } else {
                    console.log("지워졌지롱~")
                    $("#" + no).remove();
                }
            }, error: function (XHR, status, error) {
                console.error(status + " : " + error);
            }
        })
        $("#view_modal").modal("hide");
    })

    $("#view_modal").on("click",function () {
        $("#view_modal").modal("hide");
        $("#btn_del").remove();
        $(".modal-title").remove();
    })

</script>
</html>

