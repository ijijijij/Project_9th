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
	display : flex;
}
#cookBang-video{
	width : 700px;
	height : 500px;
}
#cookBang{
	texti-align : center;
}
#cookBang h2{
	padding : 10px 10px;
	color : #424242;
}
/* 채팅 */
#chat{
	padding : 50px 20px;
}
#chat h3{
	padding : 5px 5px;
}
#chat fieldset{
	height : 430px;
	border : 1px solid #dae1e6;
}
#messageWindow{
	font-size : 11pt;
	height : 350px;
	color : #424242;
	padding : 5px 10px;
	resize : none;
	border : none;
}
#sending{
	text-align : center;
	padding-top : 20px;
	border-top : 1px solid #dae1e6;
}
#inputMessage{
	width : 80%;
	height : 30px;
	padding : 0 5px;
	border : none;
	border-bottom : 1px solid #dae1e6;
}
#inputMessage:focus{
	outline : none;
}
#sendBtn{
	border : none;
	padding : 6px 10px;
	background-color : #A9CF33;
	color : #ffffff;
	font-size : 11pt;
	cursor : pointer;
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
<c:set var="profile" value="${profile}"/>
<c:set var="cbang" value="${cbang}"/>
	<!-- 영상 화면  -->
	<div id="cookBang">
		<video id="cookBang-video" autoplay controls>
			<source src="${cbang.cbang_vid}" type="video/mp4">
		</video>
		<h2>${cbang.cbang_nm}</h2>
	</div>
	
	<!-- 채팅 화면  -->
	<div id="chat">
		<h3>실시간 채팅</h3>
		<fieldset>
			<textarea id="messageWindow" rows="15" cols="50" readonly="true"></textarea>
			<br/>
			<div id="sending">
				<input id="inputMessage" type="text" placeholder="메시지를 입력하세요" />
				<input id="sendBtn" type="submit" value="send" onclick="send()"/>
			</div>
		</fieldset>
	</div>
</article>

<script type="text/javascript">
//채팅
var textarea = document.getElementById("messageWindow");
var webSocket = new WebSocket('ws://106.10.104.82:7080/Project_9th/broadcasting');
//var webSocket = new WebSocket('ws://localhost:8080/jspexp/broadcasting');
var inputMessage = document.getElementById("inputMessage");
//권한정책
"<c:if test='${empty user.user_cd_pk}'>"
	inputMessage.placeholder = "로그인 회원만 이용가능합니다";
	inputMessage.disabled="true";
	var sendBtn = document.querySelector("#sendBtn");
	sendBtn.disabled = "true";
"</c:if>"
//채팅 시작
webSocket.onerr  = function(event){
	onError(event);
}
webSocket.onopen = function(event){
	onOpen(event);
}
webSocket.onmessage = function(event){
	onMessage(event);
}
function onMessage(event){
	console.log(event.data);
	textarea.value += event.data +"\n\n";
}
function onOpen(event){
	textarea.value+="채팅방에 입장했습니다\n\n";
}
function onError(event){
	alert(event.data);
}
function send(){
	textarea.value += "${profile.profile_nk} : "+inputMessage.value + "\n\n";
	webSocket.send("${profile.profile_nk} : "+inputMessage.value);
	inputMessage.value="";
} 

inputMessage.onkeydown = function(event){
	if(event.keyCode==13){
		send();
	}
}
</script>