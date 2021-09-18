<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<fmt:requestEncoding value="utf-8" />


<head>
<style type="text/css">
#bbs-view {
	box-sizing: border-box;
	width: 100%
}

/* 게시판 공통 헤더 */
.bbs-view-header {
	padding: 0 20px;
	height: 35px;
	line-height: 35px;
}
/* 글쓰기 버튼 */
.bbs-view-header .btns {
	float: right;
	margin: 0;
	visibility: visible;
}
/* 타이틀 */
#bbs-view-title {
	text-align: left;
	display: block;
	font-size: 24px;
	padding-top: 20px;
	padding-bottom: 20px;
	padding-left: 20px;
	word-break: break-all;
	font-weight: medium;
	font-family: 'Noto Sans KR', sans-serif;
	background-color: #ffffff;
}
/* 작성자~조회수 */
#bbs-view-info {
	padding: 10px 20px;
	border-bottom: 1px solid #dae1e6;
	color: #777;
	display: flex;
	align-items: center;
	background-color: #ffffff;
}

#bbs-view-info strong {
	margin: 0 10px 0 0;
	font-weight: normal;
	font-size: 11pt;
}

#bbs-view-info span {
	color: black;
	font-weight: bold;
	font-size: 13pt;
}

#bbs-view-info .info-profile {
	width: 60px;
	height: 60px;
	border-radius: 50%;
}

#bbs-view-info .info-hit {
	margin-left: auto;
	color: #888
}
/*게시글 본문*/
#bbs-view-con-holder {
	min-height: 200px;
	height: auto !important;
	height: 200px;
	padding-top: 10px;
	padding-bottom: 20px;
	border-bottom: 1px solid #dae1e6;
	background-color: #ffffff;
}

/*게시글 본문 시작*/
#bbs-view-con {
	width: 100%;
	line-height: 1.7em;
	min-height: 200px;
	word-break: break-all;
	overflow: hidden;
	padding: 20px;
}

#bbs-view-con a {
	color: #000;
	text-decoration: underline
}

#bbs-view-con img {
	max-width: 100%;
	height: auto
}
/*게시물 끝 */

/*목록 버튼*/
#bbs-view-btn-holder {
	margin: 10px;
	display: flex;
	justify-content: flex-end;
}

#bbs-view-btn-holder #comment {
	margin-right: auto;
}
/* 댓글 */
#comment-w-holder {
	padding: 0 20px;
	padding-bottom: 20px;
	color: #777;
	background-color: #ffffff;
}

#comment-w-holder .comment-w {
	resize: none;
	border: 1px solid #dae1e6;
	width: 98%;
	height: 100px;
	margin: 10px auto;
	padding: 10px;
	vertical-align: middle;
}

#comment-w-holder #comment-btn {
	text-align: right;
	padding-right: 20px;
}

#comment-w-holder #submit {
	background-color: yellowgreen;
	width: 75px;
	height: 35px;
	color: white;
	border: none;
	cursor: pointer;
}

#comment-holder {
	padding: 10px 30px;
	border-top: 1px solid #dae1e6;
	color: #777;
	background-color: #ffffff;
}

#comment-holder #comment-info {
	display: flex;
	align-items: center;
}

#comment-holder .comment-info {
	font-weight: normal;
	margin-left: 10px;
	margin-right: 0px;
	font-size: 10pt;
}

#comment-holder .comment-profile {
	width: 40px;
	height: 40px;
	border-radius: 50%;
}

#comment-holder .comment-info span {
	color: black;
	font-weight: bold;
	font-size: 11pt;
}
#cmtDeleteBtn {
	padding : 4px 8px;
	background-color : #ffffff; 
	border : none;
	color : #424242;
	cursor : pointer;
	font-size : 10pt;
}
#cmtDeleteBtn:hover{
	text-decoration : underline;
}
.comment {
	padding: 10px 0;
	width: 100%;
	text-align : left;
	/* height: 412px; */
	display: inline-block;
	/* padding: 10px; */
}

#comment-view {
	display: block;
}
/* 버튼 */
.btns {
	box-shadow: 0 1px 2px 0 rgb(0 0 0/ 5%);
	background-color: white;
	color: #F29228;
	border: 1px solid rgba(0, 0, 0, .12);
	border-radius: 10px;
	display: inline-block;
	margin: 10px 5px;
	padding: 0px;
	width: 75px;
	height: 35px;
	font-size: 14px;
	font-weight: bold;
	cursor: pointer;
	opacity: 1;
	visibility: visible;
	-webkit-transition: all 0.3s ease;
}

.btns:hover {
	transition: all 0.3s ease;
	border: none;
	background-color: #689D79;
	color: rgba(256, 256, 256, 1);
}

#btn-write {
	border: 1px solid #689D79;
}

.comment-view {
	display: none;
}

.comment-view.active {
	display: block;
}
/* 게시글 목록 */
#bbs-list {
	border-collapse : collapse;
	width : 100%; 
	text-align : center;
	color : #424242 ;
	margin-bottom : 80px;
	font-size : 10pt;
}
#bbs-list thead{
	border-radius : 30px;
	height : 40px;
	padding : 10px 15px;
	color : #424242;
	border-bottom : 1px solid #424242;
}

#bbs-list tbody tr{
	height : 40px;
	border-bottom : 1px solid #777;;
}
#bbs-list tbody td{
	width : 100px;
}
#bbs-list tbody td:nth-child(2){
	text-align : left;
	width : 400px;
}

#bbs-list tbody a{
	color : #424242;
}
#bbs-list tbody a:hover{
	text-decoration : underline;
}

#bbs-list tfoot {
	border-top : 1px solid #424242;
	padding : 10px;
	font-size : 11pt;
}
#bbs-list tfoot td{
	padding : 20px;
}
#bbs-list tfoot a{
	margin : 0 2px;
	padding: 4px 8px;
	color : #424242;
}
#bbs-list tfoot #shownPages:hover{
	border : 1px solid #424242;
}
.pageMove{
	font-size : 9pt;
	color : #424242;
}

</style>
<!--<link rel="stylesheet" href="../css/bbs_style.css"/>-->
</head>
<section>
<c:set var="rcpList" value="${rcpList}"/> 
<c:set var="cmtList" value="${cmtList}"/>
<c:set var="bbsPage" value="${bbsPage}"/>
<c:set var="recipe" value="${recipe}"/>
<c:set var="profile" value="${profile}"/>
<c:set var="visited_profile" value="${visited_profile}"/>
	<div class="wrap">
		<article>
			<c:choose>
			<c:when test="${rcpList.size()>0}">
			<table id="bbs-list">
				<thead><tr><th>번호</th><th style="width:400px">제목</th><th>작성일</th><th>조회수</th></tr></thead>
				<tbody>
					<c:forEach var="rcp" items="${rcpList}">
					<tr><td>${rcp.serial_num}</td><td><a href="myroom?visitor=${visited_profile.user_cd_pk}&recipe_cd_no=${rcp.recipe_cd_no}">${rcp.recipe_nm}</a></td><td>${rcp.recipe_reg_dt}</td><td>${rcp.recipe_cnt}</td></tr>
					</c:forEach>
				</tbody>	
				<tfoot>
					<tr>
						<td colspan="4">
							<c:if test="${bbsPage.prevPage}">
								<a href="myroom?page=${bbsPage.firstPage-1}&visitor=$visited_profile.user_cd_pk}" class="pageMove"><span>◁ 이전</span></a>
							</c:if>
							<c:forEach var="pnum" begin="${bbsPage.firstPage}" end="${bbsPage.lastPage}" varStatus="vst">
								<a href="myroom?page=${vst.index}&visitor=${visited_profile.user_cd_pk}" id="shownPages">${vst.index}</a>
							</c:forEach>
							<c:if test="${bbsPage.nextPage}">
								<a href="myroom?page=${bbsPage.lastPage+1}&visitor=${visited_profile.user_cd_pk}" class="pageMove"><span>다음 ▷</span></a>
							</c:if>
						</td>
					</tr>
				</tfoot>
			</table>
			</c:when>
			<c:otherwise>
				<h1>작성된 레시피가 없습니다</h1>
			</c:otherwise>
			</c:choose>
			<!-- 게시판 시작 -->
			<article id="bbs-view">
				<!-- 게시판 헤더 -->
				<section class="bbs-view-header">
					<button id="btn-write" class="btns" onclick="글쓰기함수()" style="">글쓰기</button>
				</section>
			<c:choose>
			<c:when test="${rcpList.size()>0}">	
				<!-- 게시물 읽기 시작 -->
				<!-- 게시물 제목 -->
				<span id="bbs-view-title">${recipe.recipe_nm}</span>
				<!-- 게시물 정보 -->
				<article id="bbs-view-info">
					<strong><img class="info-profile"
						src="${visited_profile.profile_img}"></strong> <strong><span
						class="info-author">${visited_profile.profile_nk}</span></strong> <strong class="info-date">${recipe.recipe_reg_dt}</strong> 
						<strong class="info-hit">조회 ${recipe.recipe_cnt}</strong>
				</article>
				<!-- 게시물 본문 내용 -->
				<section id="bbs-view-con-holder">
					<div id="bbs-view-con">
						<p>${recipe.recipe_detail}</p>
					</div>
				</section>

				<!-- 게시물 공통 푸터 -->
				<!-- 버튼 -->
				<article id="bbs-view-footer">
					<div id="bbs-view-btn-holder">
						<button class="btns" id="comment" onclick="showComment()">댓글
							(${cmtList.size()})</button>
						<c:if test="${visited_profile.user_cd_pk==user.user_cd_pk}">	
						<button class="btns" id="edit" onclick="수정()">수정</button>
						<button class="btns" id="delete" onclick="delRecipe()">삭제</button>
						</c:if>
						<button class="btns" id="list">목록</button>
					</div>
					<!-- 댓글 영역 -->
					<div class="comment-view">
						<!-- 댓글 입력 -->
						<div id="comment-w-holder">
							<form method="post" action="inputComment?recipe_cd_no=${recipe.recipe_cd_no}&visitor=${visited_profile.user_cd_pk}" id="commentForm" onsubmit="return addComment()">
								<textarea rows="3" cols="60" class="comment-w" id="comment-w"
									name="comment_w" placeholder="댓글을 입력하세요"></textarea>
								<div id="comment-btn">
									<button class="comment-btn" id="submit">등록</button>
								</div>
							</form>
						</div>
						<!-- 작성된 댓글 -->
						<section id="comment-holder">
							<c:choose>
							<c:when test="${cmtList.size()>0}">
							<c:forEach var="cmt" items="${cmtList}">
								<div id="comment-info">
									<strong class="comment-info"><img
										class="comment-profile" src="${cmt.profile_img}"></strong> <strong
										class="comment-info"><span>${cmt.profile_nk}</span></strong> <strong
										class="comment-info">${cmt.comment_reg_dt}</strong>
										<c:if test="${cmt.profile_cd_pk == profile.profile_cd_pk}">
										<a href="deleteComment?comment_cd_no=${cmt.comment_cd_no}&recipe_cd_no=${recipe.recipe_cd_no}&visitor=${visited_profile.user_cd_pk}" 
										onclick="deleteCmt(event)" id="cmtDeleteBtn">삭제</a>
										</c:if>
								</div>
								<div class="comment">${cmt.comment_detail}</div>
							</c:forEach>
							</c:when>
							<c:otherwise>
								<h3 style="text-align:center">작성된 댓글이 없습니다.</h3>
							</c:otherwise>
							</c:choose>
						</section>
					</div>
				</article>
				</c:when>
				</c:choose>
				<!-- 게시물 읽기 끝 -->
			</article>
			
		</article>
	</div>
</section>
<script type="text/javascript">
	//레시피 삭제 함수
	function delRecipe() {
		if(confirm("삭제하시겠습니까?")){
			location.href="deleteRecipe?recipe_cd_no=${recipe.recipe_cd_no}&visitor=${visited_profile.user_cd_pk}";
		}
	}
	//목록 버튼 클릭 시 위로 이동
	var listBtn = document.querySelector("#list");
	var topLoc = document.querySelector("#bbs-list").offsetTop;
	listBtn.onclick=function(){
		window.scrollTo({top:topLoc, behavior:'smooth'});
	}
	//댓글 보이기/숨기기
	function showComment() {
		var commentSec = document.querySelector(".comment-view");
		commentSec.classList.toggle("active");
	}
	//댓글 달기
	function addComment(){
		"<c:if test='${empty user.user_cd_pk}'>"
			alert("로그인 회원만 가능합니다.");
			return false;
		"</c:if>"
		var maxLength = 100;
		var cmtContent = commentForm.comment_w;
		if(cmtContent.value.length > maxLength){
			alert("댓글은 100자 이내로 입력하세요.");
			return false;
		}
	}
	//댓글 삭제
	function deleteCmt(event){
		if(!confirm("댓글을 삭제하시겠습니까?")){
			event.target.href = "";
		}
	}
	
</script>
