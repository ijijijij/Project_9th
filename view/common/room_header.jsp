<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<c:set var="path" value="${pageContext.request.contextPath}"/>
<fmt:requestEncoding value="utf-8"/>

<head>
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
/* 헤더 */
#room-header-main {
	height: 60px;
	padding: 0 50px;
	display: flex;
	align-items: center;
	background-color: white;
	color: #A9CF33;
}

#room-header-main #logo {
	width: 40px;
	height: 40px;
	padding-right: 10px;
}

/* 마이룸 이동*/
#room-header-move {
	margin-left : auto;
	display : flex;
	align-items : center;
}
/* 로그아웃 */
#logout{
	text-decoration : none;
	font-size : 11pt;
	font-weight : lighter;
	color : #A9CF33;
	margin-right : 10px;
}
#room-header-move #myRoom{
	width : 40px;
	height : 40px;
	border-radius : 50%;
}

</style>
</head>
		<!-- 헤더 -->
			<div id="room-header-main">
				<h2>
					<a href="main.do"><img id="logo" src="image/logo.png"></a>
				</h2>
				<h2>${param.page_title}</h2>
				<c:if test="${not empty user}">
				<h2 id="room-header-move">
					<a href="logOut" id="logout">로그아웃</a>
					<a href="myroom?visitor=${user.user_cd_pk}"><img id="myRoom" src="${param.my_thumb}"></a>
				</h2>
				</c:if>
			</div>
