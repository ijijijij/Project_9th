<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<c:set var="path" value="${pageContext.request.contextPath}"/>
<fmt:requestEncoding value="utf-8"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Title</title>
<style>
* {
	margin: 0;
	padding: 0;
}

a {
	text-decoration: none;
}

#account-manage {
	size: box-sizing;
	width: 100%;
}
/* 헤더 */
#account-header {
	height: 111px;
}

/*메뉴 네비게이션*/
#account-header-nav {
	height: 50px;
	border-bottom: 1px solid #dae1e6;
	display: flex;
	align-items: center;
	padding-left: 50px;
}

#account-header-nav a {
	position: relative;
	padding: 15px 10px 13px;
	font-weight: bold;
	color: #424242;
}

#account-header-nav a.active {
	color: #A9CF33;
}

#account-header-nav a.active::after {
	content: "";
	display: block;
	position: absolute;
	left: 0;
	right: 0;
	top: 100%;
	margin: -2px -4px 0;
	height: 2px;
	background-color: #A9CF33;
}

/* 본문 */
#account-body {
	margin: 50px auto;
	text-align: center;
}

#account-show-table {
	margin: 0 auto;
	border-collapse: collapse;
}

#account-show-table tr {
	border: 1px solid #dae1e6;
}

#account-show-table th {
	background-color: #A9CF33;
	color: white;
	width: 150px;
	padding: 10px 15px;
}

#account-show-table td {
	width: 400px;
	height: 50px;
	padding: 10px 15px;
	text-align: left;
}

#account-show-table .account-img {
	height: 260px;
}

.profile-Img {
	border: 1px solid #dae1e6;
	width: 150px;
	height: 150px;
	margin-bottom: 20px;
}

.profile-input {
	padding: 5px 8px;
	border: 1px solid #dae1e6;
	font-size: 12pt;
}

#profile-introduce {
	width: 400px;
	height: 116px;
	resize: none;
}

/* 버튼 */
.btn {
	border: 1px solid #dae1e6;
	background-color: #ffffff;
	border-radius: 10px;
	box-shadow: 0 1px 2px 0 rgb(0 0 0/ 5%);
	padding: 5px 20px;
	width: 100px;
	height: 30px;
	cursor: pointer;
	color: #424242;
	font-weight: bold;
	font-size: 11pt;
}

#saveBtn {
	border: 1px solid #A9CF33;
	margin-top: 20px;
}
input[type=file]{
	display: none;
}
</style>
<script type="text/javascript">
	window.onload = function() {
		var present_tab = document
				.querySelector("#account-header-nav a:first-child")
		present_tab.classList.add("active");
	}
</script>
</head>
<body>
	<c:set var="profile" value="${profile}"/>
	<!-- 프로필 변경 -->
	<article id="account-manage">
		<!-- 헤더 -->
		<article id="account-header">
			<jsp:include page="../common/room_header.jsp">
				<jsp:param name="page_title" value="내 정보"/>
				<jsp:param name="my_thumb" value="${profile.profile_img}"/>
			</jsp:include>
			<div id="account-header-nav">
				<a href="myProfile">프로필</a> 
				<a href="myPrivateInfo">개인정보 수정</a>
			</div>
		</article> 
		<!-- 본문 -->
		<article id="account-body">
			<form id="userform" method="post" onsubmit="return validChk()"
			enctype="multipart/form-data" action="myProfile_update">
				<table id="account-show-table">
					<tr class="account-img">
						<th>프로필 이미지</th>
						<td><img class="profile-Img"
							src="${profile.profile_img}"><br> 
							<input
							type="file" id="profile-hidden-chgBtn"  name="profile_img" onchange="preview(event)">
							<label for="profile-hidden-chgBtn" id="profileImgChgBtn" class="btn">사진변경</label> </td>
					</tr>
					<tr class="account-img">
						<th>배너 이미지</th>
						<td><img class="profile-Img"
							src="${profile.profile_bimg}"><br> 
							<input
							type="file" id="banner-hidden-chgBtn"  name="profile_bimg"onchange="preview(event)">
							<label for="banner-hidden-chgBtn" id="bannerImgChgBtn" class="btn">사진변경</label> </td>
					</tr>
					<tr>
						<th>닉네임</th>
						<td><input type="text" id="profile-nick" name="profile_nk"
							class="profile-input" value="${profile.profile_nk}"></td>
					</tr>
					<tr>
						<th>내 소개</th>
						<td><textarea id="profile-introduce" class="profile-input" name="profile_intro"
								placeholder="자기소개를 입력하세요">${profile.profile_intro}</textarea></td>
					</tr>
					<tr>
						<th>포인트</th>
						<td><span>${profile.profile_point} point</span></td>
					</tr>
				</table>
				<input type="submit" value="저장" id="saveBtn" class="btn">
			</form>
		</article>
	</article>
</body>
<script type="text/javascript">
"<c:if test='${visited_privateInfo.user_cd_pk!=privateInfo.user_cd_pk}'>"
try {
	throw new Error("접근 불가능한 페이지");
} catch (e) {
	alter(e.message);
}
"</c:if>"
// 썸네일 미리보기
function preview(e){
	if(e.target.files.length){
		var uploadSec = e.target.parentNode.firstChild;
		var reader = new FileReader();
		reader.onload=function(e){
			uploadSec.src=e.target.result;
		}
		reader.readAsDataURL(e.target.files[0]);
	}
}
// 유효성 체크
function validChk(){
	if(!nkChk()){
		return false;
	}
	if(!introChk()){
		return false;
	}
	return true;
}
function nkChk(){
	if(userform.profile_nk.value.trim()==""){
		alert("닉네임을 입력하세요.");
		userform.profile_nk.focus();
		return false;
	}
	return true;
}
function introChk(){
	if(userform.profile_intro.value.length > 30){
		alert("소개글은 30자 이내로 입력하세요");
	}
	return true;
}
</script>
</html>