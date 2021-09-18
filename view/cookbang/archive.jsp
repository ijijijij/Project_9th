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
#room-content {
	margin : 0 auto;
	padding : 20px 70px;
	display : inline-block;
	text-align : left;
}
#archive-indiv{
	text-align : center;
	padding : 20px 20px;
}
#archive-indiv a{
	color : #424242;
	font-weight : lighter;
}
#archive-thumb{
	width : 200px;
	height : 200px;
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
<article id="room-content">
<c:set var="visited_profile" value="${visited_profile}"/>
<c:set var="cbangList" value="${cookBangList}"/>
	<c:choose>
	<c:when test="${cbangList.size()>0}">
		<c:forEach var="cbang" items="${cbangList}">
			<div id="archive-indiv">
				<img src="${cbang.cbang_img}" id="archive-thumb">
				<h3><a href="cb?cbNum=${cbang.cbang_cd_pk}&visitor=${visited_profile.user_cd_pk}">${cbang.cbang_nm}</a></h3>
			</div>	
		</c:forEach>
	</c:when>
	<c:otherwise>
		<h3 id="noCookBang">쿡방이 없습니다</h3>
	</c:otherwise>
	</c:choose>	
</article>

<script type="text/javascript">
	
</script>