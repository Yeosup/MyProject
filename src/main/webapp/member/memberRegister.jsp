<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="memberRegister.css">
    <!-- Boxicons link -->
    <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
    <link rel="icon" href="${pageContext.request.contextPath}/icon/favicon.png" type="image/x-icon">
    <script src="memberRegister.js"></script>
    <title>회원 가입 | 렌미노</title>
</head>
<body>
    <div id="member_box">
        <div id="back">
            <a onclick="goBack()"><i class='bx bx-left-arrow-alt'></i></a>
        </div>
        <div class="logo_box">
            <a href="../index.jsp">
                <img src="${pageContext.request.contextPath}/image/logo.png" alt="logo">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span id="title">회원 가입</span>
            </a>
        </div>
        <div id="form_box">
            <form action="memberRegisterOK.do" method="post" onkeyup="welcome()">
                <div class="id">
                    <p>아이디</p>
                    <input id="id" name="id" type="text" required placeholder="아이디를 입력해 주세요.">
                    <div id="idError" class="error"></div>
                </div>
                <div class="pw">
                    <p>비밀번호</p>
                    <input id="pw" name="pw" type="password" required placeholder="비밀번호를 입력해 주세요.">
                    <div id="pwError" class="error"></div>
                </div>
                <div class="pwChk">
                    <p>비밀번호 재확인</p>
                    <input id="pwChk" name="pwChk" type="password" required placeholder="비밀번호를 다시 입력해 주세요.">
                    <div id="pwChkError" class="error"></div>
                </div>
                <div class="name">
                    <p>이름</p>
                    <input id="name" name="name" type="text" required placeholder="이름을 입력해 주세요.">
                    <div id="nameError" class="error"></div>
                </div>
                <div class="birth">
                    <p>생년월일</p><input type="text" name="birthYear" required id="birthYear" max="4" placeholder="년(4자)">
                    <select name="birthMonth" id="birthMonth">
                        <option value="birthMonth">월</option>
                        <c:forEach var="i" begin="1" end="12" step="1">
                            <option value="${i }">${i }</option>
                        </c:forEach>
                    </select>                
                    <input type="text" name="birthDate" required id="birthDate" placeholder="일">
                </div>
                <div class="gender">
                    <p>성별</p><select name="gender" id="gender">
                        <option value="gender">성별</option>
                        <option value="male">남</option>
                        <option value="female">여</option>
                    </select>
                    <div id="genderError" class="error"></div>
                </div>            
                <div class="email">
                    <p>본인 확인 이메일</p>
                    <input id="email" name="email" type="email" required placeholder="이메일을 입력해 주세요.">
                    <div id="emailError" class="error"></div>
                </div>            
                <div class="mobile">
                    <p>휴대전화</p><input type="text" name="mobile" required id="mobile" placeholder="숫자만 입력하세요">
                    <button disabled id="sendMessage" onclick="getToken()">인증번호 전송</button>
                    <div id="mobileError" class="error"></div>
                </div>
                <div class="timer">
                    <input type="text" name="timer" id="timer" placeholder="03:00">
                </div>
                <div class="registerBtn">
                	<input type="hidden" name="profile" value="user_icon.png">
                	<button id="registerBtn" type="submit">가입하기</button>
                </div>
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