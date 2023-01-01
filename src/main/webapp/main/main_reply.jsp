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
    <link rel="stylesheet" href="main_reply.css">
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
                        <a href="#">
                            <i class='bx bx-heart' ></i>
                            <span class="links_name">내 친구</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
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
                     <%-- <div id="back">
                        <a onclick="goBack()"><i class='bx bx-left-arrow-alt'></i></a>
                    </div>
                    <div class="welcomeMsg">
                        <h3>${userInfo.id }</h3>
                    </div> --%>
                    <div class="profile_img">
                        <a href="main_profile.do?id=${userInfo.id }">
                        	<c:choose>
	                            <c:when test="${userInfo.profile eq null }">
	                            	<img id=myImg src="/Project/upload/user_icon.png">
	                            </c:when>
	                            <c:otherwise>
	                            	<img id=myImg src="/Project/upload/${userInfo.profile }">
	                            </c:otherwise>
                            </c:choose>
                        </a>
                       	<!-- <div id="myModal" class="modal">
	                    	<span class="close">&times;</span>
	                    	<img class="modal-content" id="img01">
	                    	<div id="caption"></div>
                        </div> -->
                    </div>
                    <div class="writeMain">
	                    <form action="writeOK.do" method="post">
	                    	<div id="text_cnt">0 / 30</div>
	                    	<input type="text" name="contents" id="writeOK" maxlength="50" placeholder="오늘의 한 줄 고민을 남겨보세요">
	                    	<input type="submit" value="작성" id="writeMain">
	                    </form>
                    </div>
                    <div class="writeList">
	                    <table>
							<c:forEach var="dto" items="${list}">
								<tr>
									<td>
										<div class="writerImg">
				                    		<a href="main_profile.do?id=${dto.id }">
					                        	<c:choose>
						                            <c:when test="${dto.profile eq null }">
						                            	<img id=myImg src="/Project/upload/user_icon.png">
						                            </c:when>
						                            <c:otherwise>
						                            	<img id=myImg src="/Project/upload/${dto.profile }">
						                            </c:otherwise>
					                            </c:choose>
				                        	</a>
		                    			</div>
									</td>
									<td>
										<div class="writerInfo">
											<a href="main_profile.do?id=${dto.id }">
					                            <c:choose>
					                            	<c:when test="${dto.nickname ne null}">
					                            		<div class="name">${dto.nickname } </div>
					                            	</c:when>
					                            	<c:otherwise>
					                            		<div class="name">${dto.id } </div>
					                            	</c:otherwise>
					                            </c:choose>
					                        </a>
					                        <div class="writerPromise">${dto.promise }</div>
					                        <div class="writeTime">${dto.wtime }</div>
			                    		</div>
			                    		<div class="contents">${dto.contents }</div>									
										<div class="reply">
											<a href="#"><i class='bx bx-message-rounded'></i></a>
											<a href="#"><i class='bx bx-like' ></i></a>
											<a href="#"><i class='bx bx-dislike' ></i></a>
											<a href="#"><i class='bx bx-share'></i></a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</table>
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
        		<p>친구를 찾아봐요</p>
        		<table>
        			<c:forEach var="memberList" items="${memberList }">
        				<tr>
        					<td>
        						<div class="writerImg">
				                	<a href="main_profile.do?id=${memberList.id }">
					                	<c:choose>
						                	<c:when test="${memberList.profile eq null }">
						                    	<img id=myImg src="/Project/upload/user_icon.png">
						                	</c:when>
						            		<c:otherwise>
						                    	<img id=myImg src="/Project/upload/${memberList.profile }">
						                	</c:otherwise>
					                	</c:choose>
				                	</a>
		                    	</div>
        					</td>
        					<td>
        						<div class="writerInfo">
									<a href="main_profile.do?id=${memberList.id }">
					                	<c:choose>
					                    	<c:when test="${memberList.nickname ne null}">
					                        	<div class="name">${memberList.nickname } </div>
					                     	</c:when>
					                    	<c:otherwise>
					                        	<div class="name">${memberList.id } </div>
					                    	</c:otherwise>
					                	</c:choose>
					            	</a>
					            	<div class="writerPromise">${memberList.promise }</div>
					            	<form action="addFriend.do" method="post">
					            		<input type="hidden" name="friendId" value="${memberList.id }">
					            		<input type="hidden" name="profile" value="${memberList.profile }">
					            		<input type="hidden" name="nickname" value="${memberList.nickname }">
					            		<input type="hidden" name="promise" value="${memberList.promise }">
					            		<input type="submit" value="친구 추가" id="makeFriendBTN">  <!-- /* 글 수정 삭제 버튼 추가해야함 */ -->
					            	</form>
			                	</div>
        					</td>
        				</tr>
        			</c:forEach>
        		</table>
        	</div>
        	<div class="miniList">
        		<p>오늘의 한 줄</p>
        		<table>
        			<c:forEach var="originList" items="${originList }">
        				<tr>
        					<td>
        						<div class="writerImg">
				                	<a href="main_profile.do?id=${originList.id }">
					                	<c:choose>
						                	<c:when test="${originList.profile eq null }">
						                    	<img id=myImg src="/Project/upload/user_icon.png">
						                	</c:when>
						            		<c:otherwise>
						                    	<img id=myImg src="/Project/upload/${originList.profile }">
						                	</c:otherwise>
					                	</c:choose>
				                	</a>
		                    	</div>
        					</td>
        					<td>
        						<div class="writerInfo">
									<a href="main_profile.do?id=${originList.id }">
					                	<c:choose>
					                    	<c:when test="${originList.nickname ne null}">
					                        	<div class="name">${originList.nickname } </div>
					                     	</c:when>
					                    	<c:otherwise>
					                        	<div class="name">${originList.id } </div>
					                    	</c:otherwise>
					                	</c:choose>
					            	</a>
					            	<div class="miniContents">${originList.contents }</div>
					            	<i class='bx bx-check'></i>
			                	</div>
        					</td>
        				</tr>
        			</c:forEach>
        		</table>
        	</div>
        </div>
        <div class="replyPage">
            <div class="editBox">
            	<div id="close">
                	<a onclick="goBack()"><i class='bx bx-x'></i></a>
            	</div>
                <div class="profile_boxEdit">
                    <div class="profile_imgEdit">
                        <c:choose>
	                    	<c:when test="${dto.profile eq null }">
	                        	<img id=myImgEdit src="/Project/upload/user_icon.png">
	                        </c:when>
	                        <c:otherwise>
	                            <img id=myImgEdit src="/Project/upload/${dto.profile }">
	                    	</c:otherwise>
                        </c:choose>                        
                    </div>
                    <div class="nickname">
                    	<c:choose>
					    	<c:when test="${dto.nickname ne null}">
					        	<div class="name">${dto.nickname } </div>
					    	</c:when>
					    	<c:otherwise>
					        	<div class="name">${dto.id } </div>
					    	</c:otherwise>
					    </c:choose>
                    </div>
                </div>
                <div class="upload">
	            	<form action="replyOK.do" method="post" onsubmit="return formSubmit()">
	            		<div class="originText">
	            			${dto.contents }
	            		</div>
		                <div class="replyBox">
		                	<p>댓글 남기기</p>
		                    <input type="text" id="replyBox" name="contents" maxlength="50" value=">> ">
		                </div>
						<input type="hidden" name="id" value="${userInfo.id }" />
						<input type="hidden" name="no" value="${dto.no}" />
						<input type="hidden" name="groupNum" value="${dto.groupNum}" />
						<input type="hidden" name="stepNum" value="${dto.stepNum}" />
						<input type="hidden" name="indentNum" value="${dto.indentNum}" />
	                	<input type="submit" value="저장" id="uploadBtn" onclick="reload()">
	            	</form>
                </div>
            </div>
        </div>
        <footer></footer>
    </div>
    <script>
        $(document).ready(function() {
            $('#writeOK').on('keyup', function() {
                $('#text_cnt').html(""+$(this).val().length+" / 30");
         
                if($(this).val().length > 30) {
                    $(this).val($(this).val().substring(0, 30));
                    $('#text_cnt').html("30 / 30");
                    alert('30자 이하로 작성해주세요');
                }
            });
        });
        function goBack(){
            window.history.back();
        }
        function reload(){
        	opener.location.reload();
        }
        function formSubmit() {
       	 if(document.getElementById("replyBox").value == ">> " ) {
       	  alert('내용을 입력해주세요');
       	 }else{
       		 return true;
       	 }
       	  return false;
       }
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