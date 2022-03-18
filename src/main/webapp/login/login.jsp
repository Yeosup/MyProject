<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/login/login.css">
    <!-- Boxicons link -->
    <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
    <link rel="icon" href="${pageContext.request.contextPath}/icon/favicon.png" type="image/x-icon">
    <title>로그인 | 렌미노</title>
</head>
<body>
    <div id="login_box">
        <div id="inner_box">
            <div id="close">
                <a onclick="goBack()"><i class='bx bx-x'></i></a>
            </div>
            <div class="logo_box">
                <a href="../index.do">
                    <img src="${pageContext.request.contextPath}/image/logo.png" alt="logo">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span id="title">로그인 하기</span>
                </a>
            </div>
            <form action="../main/main.do" method="post" id="login">
                <input type="text" name="id" id="id" placeholder="아이디"><br/><br/>
                <input type="password" name="pw" id="pw" placeholder="비밀번호"><br/><br/>
                <button type="submit" id="login_btn">로그인</button><br/>
                <input type="checkbox" name="rememberId" id="stay_Login"><span class="stay_Login">&nbsp;&nbsp;로그인 상태 유지</span><br/>
                <a href="findId.do" class="find"><span>아이디 찾기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a>
                <a href="findPw.do" class="find"><span>비밀번호 찾기</span></a><br>
                <img src="${pageContext.request.contextPath}/image/password.png" alt="password">
            </form>
        </div>
    </div>
    <script>
        function goBack(){
            window.history.back();
        }
    </script>
</body>
</html>