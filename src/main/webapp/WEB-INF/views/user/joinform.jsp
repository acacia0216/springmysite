<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="./assets/css/user.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/assets/js/jquery/jquery-1.12.4.js"></script>
    <title>Insert title here</title>
</head>
<body>

<div id="container">

    <c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
    <c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>

    <div id="wrapper">
        <div id="content">
            <div id="user">

                <form id="join-form" name="joinForm" method="post" action="/join">

                    <label class="block-label" for="name">이름</label>
                    <input id="name" name="name" type="text" value="">

                    <label class="block-label" for="email">이메일</label>
                    <input id="email" name="email" type="text" value="">
                    <input id="btnEmailCheck" type="button" value="id 중복체크">
                    <span id="msg"></span>

                    <label class="block-label">패스워드</label>
                    <input name="password" type="password" value="">

                    <fieldset>
                        <legend>성별</legend>
                        <label>여</label> <input type="radio" name="gender" value="female" checked="checked">
                        <label>남</label> <input type="radio" name="gender" value="male">
                    </fieldset>

                    <fieldset>
                        <legend>약관동의</legend>
                        <input id="agree-prov" type="checkbox" name="agreeProv" value="y">
                        <label>서비스 약관에 동의합니다.</label>
                    </fieldset>

                    <input type="submit" value="가입하기">

                </form>

            </div><!-- /user -->
        </div><!-- /content -->
    </div><!-- /wrapper -->

    <c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

</div> <!-- /container -->

</body>
<script type="text/javascript">
    $("#btnEmailCheck").on("click", function () {
        console.log("버튼찍기!!!!!!!!!!!!")
        var email = $("#email").val();
        console.log(email);
        $.ajax({
            url: "/emailcheck",
            type: "post",
            data: {email : email},

            dataType: "json",
            success: function (result) {
            if(result == true){
                $("#msg").html("<font color='red'>사용 가능한 아이디입니다.</font>")
            }else{
                $("#msg").html("<font color='red'>이미 사용중인 아이디입니다.</font>")
            }
            },
            error: function (XHR, status, error) {
                console.error(status + " : " + error);
            }
        })
    })
</script>
</html>
