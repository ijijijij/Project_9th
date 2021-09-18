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

#room-manage {
	size: box-sizing;
	width: 100%;
}

/* 헤더 배너 */
#room-bannerImg {
	width : 100%;
	height : 200px;
}

/* 헤더 계정 정보*/
#room-header-myInfo {
	display : flex;
	padding : 20px 100px;
	align-items : center;
}

#room-header-myInfo #room-profileImg{
	width : 150px;
	height : 150px;
	border-radius : 50%;
}
#room-myInfo{
	padding : 10px 20px;
	line-height : 2;
}
#room-myInfo strong{
	color : black;
	padding-right : 20px;
}
#room-myInfo span{
	font-weight : normal;
	font-size : 11pt;
	color : #424242;
}
#room-myInfo a{
	text-decoration : none;
}
/* 헤더 버튼 */
#room-btn{
	margin-left : auto;
}
#profile{
	background-color : yellowgreen;
	color : white;
	padding : 5px 0;
}
#follow:hover{
	transition: all 0.3s ease;
	background-color : orange;
	color : white;
}

/* 헤더 네비게이션*/
#room-header-nav {
	height: 50px;
	border-bottom: 1px solid #dae1e6;
	display: flex;
	align-items: center;
	padding: 0 50px;
	justify-content : space-evenly;
}

#room-header-nav a {
	position: relative;
	text-decoration : none;
	padding: 15px 10px 13px;
	font-weight: bold;
	color: #424242;
}
#room-header-nav a.active {
	color: #A9CF33;
}
#room-header-nav a.active::after {
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
#room-body{
	margin : 20px 100px;
}

/* 버튼 */
.btn{
	border : 1px solid #dae1e6;
	background-color : #ffffff;
	border-radius : 10px;
	box-shadow: 0 1px 2px 0 rgb(0 0 0 / 5%);
	padding : 5px 20px;
	width : 110px;
	height : 40px;
	cursor : pointer;
	color : #424242;
	font-weight : bold;
	font-size : 11pt;
}


</style>
<script type="text/javascript">
	window.onload = function() {
		var present_tab = document
				.querySelector("#room-header-nav a:nth-child(3)")
		present_tab.classList.add("active");
		console.log("visited_profile.user_cd_pk : ${visited_profile.user_cd_pk}, profile.user_cd_pk : ${profile.user_cd_pk}");
	}
</script>

</head>
<body>
	<article id="room-view">
		<!-- 헤더 -->
		<article id="room-header">
			<jsp:include page="../common/room_header.jsp">
				<jsp:param name="page_title" value="${visited_profile.profile_nk}"/>
				<jsp:param name="my_thumb" value="${profile.profile_img}"/>
			</jsp:include>
		<c:choose>
			<c:when test="${visited_profile.user_cd_pk!=profile.user_cd_pk}">
				<c:set var="profile" value="${visited_profile}"/>
				<c:set var="privateInfo" value="${visited_privateInfo}"/>
			</c:when>
			<c:otherwise>
				<c:set var="profile" value="${profile}"/>
				<c:set var="privateInfo" value="${privateInfo}"/>
			</c:otherwise>
		</c:choose>
			<div id="room-header-banner">
				<img id="room-bannerImg" src="${profile.profile_bimg}">
			</div>
			<div id="room-header-myInfo">
				<h2><img id="room-profileImg" src="${profile.profile_img}"></h2>
				<div id="room-myInfo">
					<h3>${profile.profile_nk} <span>(${privateInfo.user_id})</span></h3>
					<h4><span>${profile.profile_intro}</span></h4>
					<a href="follow"><strong>팔로잉 <span>13</span></strong></a>
					<a href="#"><strong>팔로워 <span>10</span></strong></a>
				</div>
				<div id="room-btn">
					<c:choose>
					<c:when test="${profile.user_cd_pk==user.user_cd_pk}">
						<a href="myProfile"><input type="button" value="내 정보" id="profile" class="btn"></a>
					</c:when>
					<c:otherwise>
						<input type="button" value="팔로우" id="follow" class="btn">
					</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div id="room-header-nav">
				<a href="myroom?visitor=${visited_profile.user_cd_pk}">레시피</a> 
				<a href="#">챌린지</a> 
				<a href="cookBang?visitor=${visited_profile.user_cd_pk}">쿡방</a>
				<a href="avatar?visitor=${visited_profile.user_cd_pk}">아바타</a>
			</div>
		</article>
		<!-- 본문 -->
		<article id="room-body">
			<c:choose>
			<c:when test="${proc=='archive'}">
				<c:import url="../cookbang/archive.jsp"/>
			</c:when>
			<c:when test="${proc=='room'}">
				<c:import url="../cookbang/room.jsp"/>
			</c:when>
			</c:choose>
		</article>
	</article>
</body>
</html>