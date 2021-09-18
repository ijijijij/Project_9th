<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<fmt:requestEncoding value="utf-8" />

<head>
<style>
* {
	margin: 0;
	padding: 0;
}

a {
	text-decoration: none;
}

/* 왼쪽 사이드 */
#avatar-aside {
	height: 100vh;
	width: 230px;
	background-color: #A9CF33;
	padding: 10px 10px;
}

#aside-user-info {
	margin: 0 auto;
	width: 180px;
	padding: 20px 10px;
	background-color: white;
	border-radius: 10px;
}

.avatar-img {
	width: 150px;
	height: 150px;
	border-radius: 50%;
}

#avatar-menu {
	padding: 20px 0;
	font-size: 15pt;
}

#avatar-menu a {
	font-size : 13.55pt !important;
	display: block;
	color: white;
	font-weight: bold;
	padding: 10px 10px;
}

#avatar-menu a.active {
	background-color: white;
	color: #F29228;
	border-radius: 20px;
}
</style>
</head>
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
	<article id="avatar-aside">
		<div id="aside-user-info">
			<h3>${profile.profile_nk}</h3>
			<h5>
				포인트 : <span id="nowPoint">${profile.profile_point}</span>
			</h5>
		</div>
		<div id="avatar-menu">
			<a href="avatar?visitor=${visited_profile.user_cd_pk}">아바타 키우기</a> 
			<c:if test="${visited_profile.user_cd_pk==user.user_cd_pk}">
				<a href="store?visitor=${visited_profile.user_cd_pk}" id="storeLink">아바타 상점</a> 
			</c:if>
			<a href="#">포인트 게임</a>
		</div>
	</article>
