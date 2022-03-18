<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="index.css">
    <link rel="icon" href="${pageContext.request.contextPath}/icon/favicon.png" type="image/x-icon">
    <title>한줄 고민 해결 | 렌미노</title>
</head>
<body>
    <div id="container">
        <header></header>
        <aside>
            <img src="${pageContext.request.contextPath}/image/main_page.png" alt="main_page">
        </aside>        
        <article>
            <div class="article_box"></div>
        </article>
        <section>
            <div class="main_text">
                <h1>한 줄로 고민 해결</h1><br/>
                <h3>결정이 힘들 때 친구들에게 추천 받아 해결해요</h3>
                <form action="login/login.do" method="post" id="login">
                    <input type="submit" value="로그인" id="login_btn" /><br>
                    <br/><br/>
                    <p>아직 회원이 아니신가요?</p>
                    <span>ⅴⅴⅴ</span><br/><br/>
                    <a href="member/terms.do"><input type="button" value="회원가입" id="member_btn" /></a>
                </form>
            </div>
        </section>    
        <footer></footer>
    </div>
</body>
</html>