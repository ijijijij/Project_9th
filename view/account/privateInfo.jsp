<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<fmt:requestEncoding value="utf-8" />

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

#account-manage2 {
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
	width: 200px;
	padding: 10px 15px;
}

#account-show-table td {
	width: 300px;
	height: 50px;
	padding: 10px 15px;
	text-align: left;
}

.account-info {
	width: 250px;
	padding: 5px 8px;
	border: 1px solid #dae1e6;
	font-size: 12pt;
}

/* 이름 */
#account-show-table span {
	border: none;
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
#out{
	color : #424242;
	font-weight : lighter;
	margin : 0px auto;
	padding-top : 5px;
	width : 562px;
	text-align : right;
}
#out a {
	color : #424242;
	text-decoration : none;
}
#out a:hover {
	text-decoration : underline;
}
</style>
<script type="text/javascript">
	window.onload = function() {
		var present_tab = document
				.querySelector("#account-header-nav a:last-child")
		present_tab.classList.add("active");
	}
</script>
</head>
<body>
	<c:set var="profile" value="${profile}" />
	<c:set var="privateInfo" value="${privateInfo}" />
	<!-- 프로필 변경 -->
	<article id="account-manage2">
		<!-- 헤더 -->
		<article id="account-header">
			<jsp:include page="../common/room_header.jsp">
				<jsp:param name="page_title" value="내 정보"/>
				<jsp:param name="my_thumb" value="${profile.profile_img}"/>
			</jsp:include>
			<div id="account-header-nav">
				<a href="myProfile">프로필</a> <a href="myPrivateInfo">개인정보 수정</a>
			</div>
		</article>
		<!-- 본문 -->
		<article id="account-body">
			<form id="account-form" name="userform" method="post" onsubmit="return validChk()" action="myPrivateInfo_update">
				<table id="account-show-table">
					<tr>
						<th>이름</th>
						<td><span class="account-info">${privateInfo.user_nm }</span></td>
					</tr>
					<tr>
						<th>아이디</th>
						<td><span class="account-info">${privateInfo.user_id }</span></td>
					</tr>
					<tr>
						<th>현재 비밀번호</th>
						<td><input type="password" id="account-pw" name="pw" 
							class="account-info" value="${privateInfo.user_pw}"></td>
					</tr>
					<tr>
						<th>새로운 비밀번호</th>
						<td><input type="password" id="account-new-pw" name="newPw" 
							class="account-info" value=""></td>
					</tr>
					<tr>
						<th>새로운 비밀번호 확인</th>
						<td><input type="password" id="account-new-pw-chk"  name="newPwChk" 
							class="account-info" value=""></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="email" id="account-mail" name="mail" 
							class="account-info" value="${privateInfo.user_mail}"></td>
					</tr>
					<tr>
						<th>연락처</th>
						<td><input type="tel" id="account-phone" class="account-info" name="tel" 
							value="${fn:substring(privateInfo.user_tel,0,3)}-${fn:substring(privateInfo.user_tel,3,7)}-${fn:substring(privateInfo.user_tel,7,11)}"></td>
					</tr>
				</table>
				<h5 id="out"><span>회원탈퇴를 원하시면? </span><a href="signOut" id="signOutBtn">회원탈퇴</a></h5>
				<input type="submit" value="저장" id="saveBtn" class="btn">
			</form>
		</article>
	</article>
</body>
<script type="text/javascript">
//계정주 외 접근 방지
"<c:if test='${visited_privateInfo.user_cd_pk!=privateInfo.user_cd_pk}'>"
try {
	throw new Error("접근 불가능한 페이지");
} catch (e) {
	alter(e.message);
}
"</c:if>"
//유효성 체크
function validChk(){
	if(!pwChk()){
		return false;
	}
	return true;
}
function pwChk(){
	var pwRegex = /^[a-zA-Z0-9]{6,12}$/;
	if(userform.pw.value!="${privateInfo.user_pw}"){
		alert("비밀번호를 확인하세요");
		userform.pw.focus();
		return false;
	}
	if(userform.pw.value == userform.newPw.value){
		alert("기존 비밀번호와 같은 비밀번호는 사용할 수 없습니다.");
		userform.newPw.focus();
		return false;
	}
	if(userform.newPw.value!=userform.newPwChk.value){
		alert("입력한 비밀번호와 일치하지 않습니다.");
		userform.newPwChk.focus();
		return false;
	}
	if(userform.newPw.value==""){
		return true;
	}
	if(!pwRegex.test(userform.newPw.value)){
		alert("비밀번호는 영숫자 5자 이상 12자 이하로 입력하세요.");
		userform.newPw.focus();
		return false;
	}
	return true;
}
function mailChk(){
	if(userform.mail.value.trim()==""){
		alert("이메일을 입력하세요.");
		userform.mail.focus();
		return false;
	}
	return true;
}
function telChk(){
	var telRegex = /^[0-9]{9,14}$/;
	if(userform.tel.value.trim()==""){
		alert("연락처를 입력하세요.");
		return false;
	}
	if(telRegex.test(userform.tel.value)){
		aelrt("연락처 형식이 아닙니다.");
		userform.tel.focus();
		return false;
	}
}
//회원 탈퇴
function signOut(event){
	if(!confirm("회원 탈퇴를 진행하시겠습니까?")){
		event.target.href="";
	}
}
</script>
</html>