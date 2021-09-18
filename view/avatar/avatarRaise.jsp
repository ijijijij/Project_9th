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

/* 메인 컨텐츠 */
#avatar-content {
	display: flex;
	flex-wrap: wrap;
	text-align: center;
	width: 100%;
	justify-content: space-evenly;
}

#avatar-indiv {
	margin-top: 20px;
	width: 200px;
	height: 300px;
}

#avatar-indiv a {
	color: #424242;
}

#avatar-indiv-gauge h4 {
	padding: 10px 0;
	color: #424242;
}

#sun span {
	color: red;
}

#water span {
	color: blue;
}

#nutrition span {
	color: green;
}
#noAvatar {
	margin : 40px auto;
}
/* 버튼 */
.btn {
	border: 1px solid #dae1e6;
	background-color: #ffffff;
	border-radius: 10px;
	box-shadow: 0 1px 2px 0 rgb(0 0 0/ 5%);
	padding: 5px 20px;
	width: 110px;
	height: 40px;
	cursor: pointer;
	color: #424242;
	font-weight: bold;
	font-size: 11pt;
}
</style>
</head>
	<!-- avatar메뉴 -->
	<jsp:include page="../common/avatarMenu.jsp"/>
	<!-- avatar컨텐츠 -->
	<article id="avatar-content">
		<c:choose>
		<c:when test="${myAvtList.size()>0}">
			<c:forEach var="myAvt" items="${myAvtList}" varStatus="vst">
				<div id="avatar-indiv">
					<h3>
						<img id="avatar-img" class="avatar-img" src="${myAvt.avt_img}">
					</h3>
					<h3>
						<a href="raise?mavt=${myAvt.mavt_cd_pk}&visitor=${myAvt.user_cd_pk}" id="raiseLink"><c:out value="${myAvt.mavt_nm}" /></a>
					</h3>
					<div id="avatar-indiv-gauge">
						<h4 id="sun">
							햇빛 
							<c:forEach var="sun" begin="1" end="${myAvt.sun_stp}">
								<span>●</span>
							</c:forEach>
							<c:forEach var="sun-g" begin="1" end="${5-(myAvt.sun_stp)}">
								<span>○</span>
							</c:forEach>
						</h4>
						<h4 id="water">
							수분
							<c:forEach var="watern" begin="1" end="${myAvt.water_stp}">
								<span>●</span>
							</c:forEach>
							<c:forEach var="water-g" begin="1" end="${5-(myAvt.water_stp)}">
								<span>○</span>
							</c:forEach>
						</h4>
						<h4 id="nutrition">
							영양
							<c:forEach var="nut" begin="1" end="${myAvt.nut_stp}">
								<span>●</span>
							</c:forEach>
							<c:forEach var="nut-g" begin="1" end="${5-(myAvt.nut_stp)}">
								<span>○</span>
							</c:forEach>
						</h4>
					</div>
				</div>
			</c:forEach>
			</c:when>
			<c:otherwise>
				<h3 id="noAvatar">아바타가 없습니다. 상점에서 구매해보세요!</h3>
			</c:otherwise>
		</c:choose>
	</article>

<script type="text/javascript">
	/*
	window.onload = function() {*/
		var menu_tab = document.querySelector("#avatar-menu a:first-child")
		menu_tab.classList.add("active");
	/*}*/
	"<c:if test='${myAvtList[0].user_cd_pk!=user.user_cd_pk}'>"
		var links = document.querySelectorAll("#raiseLink");
		links.forEach(function(btn,index,arr){
			btn.removeAttribute("href");
		})
	"</c:if>"
</script>