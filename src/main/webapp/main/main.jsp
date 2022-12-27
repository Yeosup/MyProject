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
    <link rel="stylesheet" href="main.css">
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
	                            	<img src="http://localhost:8080/Project/upload/user_icon.png">
	                            </c:when>
	                            <c:otherwise>
	                            	<img src="http://localhost:8080/Project/upload/${userInfo.profile }">
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
	                            	<img id=myImg src="http://localhost:8080/Project/upload/user_icon.png">
	                            </c:when>
	                            <c:otherwise>
	                            	<!--<img id=myImg src="http://localhost:8080/Project/upload/${userInfo.profile }">-->
	                            	<img id=myImg src="/home/ubuntu/upload/${userInfo.profile }">
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
	                    <form id="writeForm" action="writeOK.do" method="post" onsubmit="return formSubmit()">
	                    	<div id="text_cnt">0 / 30</div>
	                    	<input type="text" name="contents" id="writeOK" maxlength="30" placeholder="오늘의 한 줄을 남겨보세요">
	                    	<input type="submit" value="작성" id="writeMain">
	                    </form>
                    </div>
                    <div class="writeList">
	                    <table>
							<c:forEach var="dto" items="${list}">
								<tr>
									<td>
										<div class="writerImg">
				                    		<a href="main_profile.do?id=${userInfo.id }">
					                        	<c:choose>
						                            <c:when test="${dto.profile eq null }">
						                            	<img id=myImg src="http://localhost:8080/Project/upload/user_icon.png">
						                            </c:when>
						                            <c:otherwise>
						                            	<img id=myImg src="http://localhost:8080/Project/upload/${dto.profile }">
						                            </c:otherwise>
					                            </c:choose>
				                        	</a>
		                    			</div>
									</td>
									<td>
										<div class="writerInfo">
											<a href="main_profile.do?id=${userInfo.id }">
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
					                        <div id=settingContents>
					                        	<i class='bx bx-dots-vertical-rounded'></i>
					                        	<div class="settingContents">
					                        		<c:choose>
					                        			<c:when test="${dto.id eq userInfo.id || 'admin' eq userInfo.id}">
					                        				<p class="modify"><a href="mainModify.do?no=${dto.no }&id=${userInfo.id }">수정</a></p>
					                        				<p class="delete"><a href="mainDelete.do?no=${dto.no }&groupNum=${dto.groupNum }&id=${userInfo.id }" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a></p>
					                        			</c:when>
					                        			<c:otherwise>
					                        				<p class="share"><a href="#">공유</a></p>
					                        			</c:otherwise>
					                        		</c:choose>
					                        	</div>
					                        </div>
					                          <!-- /* 글 수정 삭제 버튼 추가해야함 */ -->
			                    		</div>
			                    		<div class="contents">${dto.contents }</div>									
										<div class="reply">
											<a href="reply.do?no=${dto.no}&id=${dto.id }"><i class='bx bx-message-rounded'></i></a>
											<c:choose>
												<c:when test="${dto.replyCnt eq 0 }">
													<span></span>
												</c:when>
												<c:otherwise>
													<a href="#"><span id="replyCnt"><b><c:out value="${dto.replyCnt }" /></b></span></a>
												</c:otherwise>
											</c:choose>										
											<a href="good.do?no=${dto.no }"><i class='bx bx-like' ></i></a>
											<c:choose>
												<c:when test="${dto.good eq 0 }">
													<span></span><!-- /* 한 번만 적용 가능하도록 수정 필요함 */ -->
												</c:when>
												<c:otherwise>
													<a href="#"><span id="good"><b><c:out value="${dto.good }" /></b></span></a>
												</c:otherwise>
											</c:choose>	
											<a href="bad.do?no=${dto.no }"><i class='bx bx-dislike' ></i></a>
											<c:choose>
												<c:when test="${dto.bad eq 0 }">
													<span></span><!-- /* 한 번만 적용 가능하도록 수정 필요함 */ -->
												</c:when>
												<c:otherwise>
													<a href="#"><span id="bad"><b><c:out value="${dto.bad }" /></b></span></a>
												</c:otherwise>
											</c:choose>	
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
						                    	<img id=myImg src="http://localhost:8080/Project/upload/user_icon.png">
						                	</c:when>
						            		<c:otherwise>
						                    	<img id=myImg src="http://localhost:8080/Project/upload/${memberList.profile }">
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
						                    	<img id=myImg src="http://localhost:8080/Project/upload/user_icon.png">
						                	</c:when>
						            		<c:otherwise>
						                    	<img id=myImg src="http://localhost:8080/Project/upload/${originList.profile }">
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
        function formSubmit() {
        	 if(document.getElementById("writeOK").value == "" ) {
        	  alert('내용을 입력해주세요');        	  
        	 }else{
        		 return true;
        	 }
        	 return false;
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