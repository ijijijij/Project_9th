<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String path = request.getContextPath();
%>

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

#room-view {
	size: box-sizing;
	width: 100%;
}
/* 헤더 */
#room-header {
	height: 111px;
}

#room-myInfo{
	padding : 10px 20px;
	line-height : 2;
}
#room-myInfo span{
	font-weight : normal;
	font-size : 11pt;
	color : #424242;
}
#room-myInfo strong{
	padding-right : 20px;
}
/* 헤더 버튼 */
#room-btn{
	margin-left : auto;
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
}

#room-header-nav a {
	position: relative;
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
	margin : 50px auto;
	text-align : center;
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
				.querySelector("#room-header-nav a:first-child")
		present_tab.classList.add("active");
	}
</script>
</head>
<body>
	<article id="room-view">
		<!-- 헤더 -->
		<article id="room-header">
			<jsp:include page="../common/room_header.jsp">
				<jsp:param name="page_title" value="팔로우"/>
			</jsp:include>
			<div id="room-header-nav">
				<a href="myRecipes.jsp">팔로잉</a> <a href="#">팔로워</a>
			</div>
		</article>
		<!-- 본문 -->
		<article id="room-body">
			<div id="follow-list">
				<div id="follow-user">
					<h3><img id="user-img" src="../../image/myRoomImg/pimg5.jpg"></h3>
					<strong></strong>
				</div>
			</div>
		</article>
	</article>
</body>
</html>