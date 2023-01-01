<%@ page import="kr.co.vanlife.www.dto.MemberDTO" %>
<%@ page import="kr.co.vanlife.www.dao.MemberDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="messageWrite.css">
    <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
    <link rel="icon" href="${pageContext.request.contextPath}/icon/favicon.png" type="image/x-icon">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <title>한줄 고민 해결 | 렌미노</title>
</head>
<body>
<c:choose>
	<c:when test="${userInfo ne null }">
    <div id="container">
        <header></header>
        <aside>
            <div class="sidebar">
                <div class="log_content">
                    <div class="logo">
                        <a href="mainOK.do?id=${userInfo.id }">
                            <img src="${pageContext.request.contextPath}/image/logo3.png" alt="logo">
                        </a>
                    </div>
                </div>
                <ul class="nav_list">
                    <li>
                        <a href="mainOK.do?id=${userInfo.id }">
                            <i class='bx bx-home-heart'></i>
                            <span class="links_name">렌미노</span>
                        </a>
                    </li>
                    <li>
                        <a href="mainFriend.do?id=${userInfo.id }">
                            <i class='bx bx-heart' ></i>
                            <span class="links_name">내 친구</span>
                        </a>
                    </li>
                    <li>
                        <a href="mainMessage.do?id=${userInfo.id }">
                            <i class='bx bx-envelope'></i>
                            <span class="links_name">메세지</span>
                        </a>
                    </li>
                    <li>
                        <a href="mainMyList.do?id=${userInfo.id }">
                            <i class='bx bx-bookmark' ></i>
                            <span class="links_name">나의 한 줄</span>
                        </a>
                    </li>
                    <li>
                        <a href="mainFriendList.do?id=${userInfo.id }">
                            <i class='bx bx-book-reader' ></i>
                            <span class="links_name">친구 한 줄</span>
                        </a>
                    </li>
                    <li>
                        <a href="main_profile.do?id=${userInfo.id }">
                            <i class='bx bx-user'></i>
                            <span class="links_name">프로필</span>
                        </a>
                    </li>
                </ul>
                <div class="profile_content">
                    <div class="profile">
                        <div class="profile_details">
                            <div class="profile_info">
                            <c:choose>
	                            <c:when test="${userInfo.profile eq null }">
	                            	<img src="/Project/upload/user_icon.png">
	                            </c:when>
	                            <c:otherwise>
	                            	<img src="/Project/upload/${userInfo.profile }">
	                            </c:otherwise>
                            </c:choose>
	                            <!-- <div id="myModal" class="modal">
		                            <span class="close">&times;</span>
		                            <img class="modal-content" id="img01">
		                            <div id="caption"></div>
                        		</div> -->
                            </div>
                            <div class="user_info">
                            <c:choose>
                            	<c:when test="${userInfo.nickname ne null}">
                            		<div class="name">${userInfo.nickname } </div>
                            	</c:when>
                            	<c:otherwise>
                            		<div class="name">${userInfo.id } </div>
                            	</c:otherwise>
                            </c:choose>  
                                <i class='bx bxs-circle'></i>
                                <div class="log">나의 한 줄&nbsp;&nbsp;<a href="mainMyList.do?id=${userInfo.id }"><b>${count.myList }</b></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;나의 친구&nbsp;&nbsp;<a href="mainFriend.do?id=${userInfo.id }"><b>${friendCnt.friendCnt }</b></a></div>
                            </div>
                        </div>
                        <a href="logout.do">
                            <i class='bx bx-log-out-circle' id="log_out"></i>
                        </a>
                    </div>
                </div>
            </div>
        </aside>
        <article>
            <div class="home_content">
                <div class="profile_box">
                    <div id="back">
                        <a onclick="goBack()"><i class='bx bx-left-arrow-alt'></i></a>
                    </div>
                    <div class="welcomeMsg">
                    <c:choose>
                    	<c:when test="${addDirectMessagefriendId.nickname ne null }">
                        	<h3>${addDirectMessagefriendId.friendId }</h3>
                    	</c:when>
                    	<c:otherwise>
                        	<h3>${addDirectMessagefriendId.friendId }</h3>
                    	</c:otherwise>
                    </c:choose> 
                    </div>
                    <div class="profile_img">
                        <c:choose>
	                            <c:when test="${userInfo.profile eq null }">
	                            	<img id=myImg src="/Project/upload/user_icon.png">
	                            </c:when>
	                            <c:otherwise>
	                            	<img id=myImg src="/Project/upload/${userInfo.profile }">
	                            </c:otherwise>
                            </c:choose>
                       	<div id="myModal" class="modal">
	                    	<span class="close">&times;</span>
	                    	<img class="modal-content" id="img01">
	                    	<div id="caption"></div>
                        </div>
                    </div>
                    <div class="profileEdit">
                    	<form id="writeForm" action="sendMessage.do" method="post" onsubmit="return formSubmit()">
                    		<div class="messageBox"><textarea id="writeOK" rows="10" cols="65" name="message"></textarea></div>
	                    	<input type="hidden" name="id" value="${userInfo.id }">
	                    	<input type="hidden" name="friendId" value="${addDirectMessagefriendId.friendId  }">
	                    	<input type="hidden" name="messageProfile" value="${addDirectMessagefriendId.profile }">
	                    	<input type="hidden" name="messageNickname" value="${addDirectMessagefriendId.nickname }">
	                    	<input type="submit" value="메세지 보내기 >>" id="profileEditBtn">
                    	</form>
                    </div>
                    <div class="backgroundImg"></div>
                    <div class="backgroundImgUnder">
                    	<div class="friendList">
                    		<table>
                    			<c:forEach var="friendList" items="${friendList }">
                    				<tr>
                    					<td>
                    						<div class="friendImg">
					                    		<a href="main_profile.do?id=${friendList.friendId }">
						                        	<c:choose>
							                            <c:when test="${friendList.profile eq null }">
							                            	<img id=myImg src="/Project/upload/user_icon.png">
							                            </c:when>
							                            <c:otherwise>
							                            	<img id=myImg src="/Project/upload/${friendList.profile }">
							                            </c:otherwise>
						                            </c:choose>
					                        	</a>
		                    				</div>
                    					</td>
                    					<td>
                    						<div class="writerInfo">
												<a href="main_profile.do?id=${friendList.friendId }">
						                            <c:choose>
						                            	<c:when test="${friendList.nickname ne null}">
						                            		<div class="name">${friendList.nickname } </div>
						                            	</c:when>
						                            	<c:otherwise>
						                            		<div class="name">${friendList.friendId } </div>
						                            	</c:otherwise>
						                            </c:choose>
						                        </a>
						                        <div class="writerPromise">${friendList.promise }</div>
						                        <a href="messageWrite.do?id=${userInfo.id }&friendId=${friendList.friendId }"><i class='bx bx-plus'></i></a>  <!-- /* 글 수정 삭제 버튼 추가해야함 */ -->
			                    			</div>
                    					</td>
                    				</tr>
                    			</c:forEach>
                    		</table>
                    	</div>
                    </div>
                </div>
            </div>
        </article>
        <section></section>
        <div class="rightSide"></div>
        <div class="rightMenu">
        	<div class="searchBox">
        		<form action="mainSearch.do" method="post">
        			<i class='bx bx-search'></i>
        			<button type="submit"><i class='bx bx-check'></i></button>
        			<input class="searchContents" type="text" name="searchKeyword" placeholder="한 줄 찾기">
	        		<label class="box-radio-input"><input type="radio" name="kindOfSearch" value="searchId" checked="checked"><span>아이디로</span></label>
	        		<label class="box-radio-input"><input type="radio" name="kindOfSearch" value="searchNickname"><span>닉네임으로</span></label>
	        		<label class="box-radio-input"><input type="radio" name="kindOfSearch" value="searchContents"><span>한 줄로</span></label>
        		</form>
        	</div>
        	<div class="friendBox">
        		<p>받은 메세지</p>
        		<table>
        			<c:forEach var="read" items="${read }">
        				<tr>
        					<td>
        						<div class="writerImg">
				                	<a href="main_profile.do?id=${read.friendId }">
					                	<c:choose>
						                	<c:when test="${read.profile eq null }">
						                    	<img id=myImg src="/Project/upload/user_icon.png">
						                	</c:when>
						            		<c:otherwise>
						                    	<img id=myImg src="/Project/upload/${read.profile }">
						                	</c:otherwise>
					                	</c:choose>
				                	</a>
		                    	</div>
        					</td>
        					<td>
        						<div class="writerInfo">
									<a href="main_profile.do?id=${read.friendId }">
					                	<c:choose>
					                    	<c:when test="${read.nickname ne null}">
					                        	<div class="name">${read.nickname } </div>
					                     	</c:when>
					                    	<c:otherwise>
					                        	<div class="name">${read.friendId } </div>
					                    	</c:otherwise>
					                	</c:choose>
					            	</a>
					            	<div class="writerPromise"><a href="messageRead.do?no=${read.no }&id=${userInfo.id }&friendId=${read.id }">${read.message }</a></div>
					            	<form action="messageRead.do" method="post">
					            		<input type="hidden" name="no" value="${read.no }">
					            		<input type="hidden" name="id" value="${userInfo.id }">
					            		<input type="hidden" name="friendId" value="${read.id }">
					            		<input type="submit" value="답장" id="makeFriendBTN">  <!-- /* 글 수정 삭제 버튼 추가해야함 */ -->
					            	</form>
			                	</div>
        					</td>
        				</tr>
        			</c:forEach>
        		</table>
        	</div>
        	<div class="miniList">
        		<p>보낸 메세지</p>
        		<table>
        			<c:forEach var="send" items="${send }">
        				<tr>
        					<td>
        						<div class="writerImg">
				                	<a href="main_profile.do?id=${send.friendId }">
					                	<c:choose>
						                	<c:when test="${send.messageProfile eq null }">
						                    	<img id=myImg src="/Project/upload/user_icon.png">
						                	</c:when>
						            		<c:otherwise>
						                    	<img id=myImg src="/Project/upload/${send.messageProfile }">
						                	</c:otherwise>
					                	</c:choose>
				                	</a>
		                    	</div>
        					</td>
        					<td>
        						<div class="writerInfo">
									<a href="main_profile.do?id=${send.friendId }">
					                	<c:choose>
					                    	<c:when test="${send.messageNickname ne null}">
					                        	<div class="name">${send.messageNickname } </div>
					                     	</c:when>
					                    	<c:otherwise>
					                        	<div class="name">${send.messageNickname } </div>
					                    	</c:otherwise>
					                	</c:choose>
					            	</a>
					            	<div class="miniContents"><a href="myMessageRead.do?no=${send.no }&id=${userInfo.id }&friendId=${send.friendId }">${send.message }</a></div>
					            	<div id=settingContents>
					                	<i class='bx bx-dots-vertical-rounded'></i>
						            	<div class="settingContents">
						                	<p class="delete"><a href="deleteMyMessage.do?no=${send.no }&id=${userInfo.id }" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a></p>
						            	</div>
					            	</div>  <!-- /* 글 수정 삭제 버튼 추가해야함 */ -->
			                	</div>
        					</td>
        				</tr>
        			</c:forEach>
        		</table>
        	</div>
        </div>           
        <footer></footer>
    </div>
    <script>
       /*  $(document).ready(function() {
            $('#writeOK').on('keyup', function() {
                $('#text_cnt').html(""+$(this).val().length+" / 30");
         
                if($(this).val().length > 30) {
                    $(this).val($(this).val().substring(0, 30));
                    $('#text_cnt').html("30 / 30");
                    alert('30자 이하로 작성해주세요');
                }
            });
        }); */
        function formSubmit() {
        	 if(document.getElementById("writeOK").value == "" ) {
        	  alert('내용을 입력해주세요');        	  
        	 }else{
        		 return true;
        	 }
        	 return false;
        }
     // Get the modal
        let modal = document.getElementById("myModal");
        
        // Get the image and insert it inside the modal - use its "alt" text as a caption
        let img = document.getElementById("myImg");
        let modalImg = document.getElementById("img01");
        let captionText = document.getElementById("caption");
        img.onclick = function(){
          modal.style.display = "block";
          modalImg.src = this.src;
          captionText.innerHTML = this.alt;
        }
        
        // Get the <span> element that closes the modal
        let span = document.getElementsByClassName("close")[0];
        let image = document.getElementsByClassName("modal-content")[0];
        // When the user clicks on <span> (x), close the modal
        span.onclick = function() { 
          modal.style.display = "none";
        }
        image.onclick = function() { 
          modal.style.display = "none";
        }
        
        function goBack(){
            window.history.back();
        }
        $(document).on("click","#settingContents > i",function(){
            if($(this).next().css("display")=="none"){
              $(this).next().show();
            }else{
              $(this).next().hide();
            }
      });
    </script>
    </c:when>
    <c:when test="${userInfo eq null }">
    	<script type="text/javascript">
    		alert("아이디 혹은 비밀번호가 틀렸습니다.");
    		history.back(-1);
    	</script>
    </c:when>
</c:choose>
</body>
</html>