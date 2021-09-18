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

#room-view {
	size: box-sizing;
	width: 100%;
}

#room-header-main {
	border-bottom: 1px solid #dae1e6 !important;
}
/* 본문 */
#room-body {
	margin: 0 auto;
	text-align: center;
	display: flex;
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
/* 메인 컨텐츠 */
#avatar-content {
	background-image: url("image/myRoomImg/farm.jpg");
	background-size: cover;
	text-align: center;
	width: 100%;
}

#avatar-indiv {
	margin: 20px auto;
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
/* 업그레이드 */
#upgrade{
	margin-top : 10px;
	background-color : #ffffff;
	color : #689D79;
	visibility : hidden;
}
/* 버튼 */
.btn {
	background-color: #689D79;
	border: none;
	border-radius: 10px;
	box-shadow: 0 1px 2px 0 rgb(0 0 0/ 5%);
	padding: 5px 20px;
	width: 110px;
	height: 40px;
	cursor: pointer;
	color: white;
	font-weight: bold;
	font-size: 11pt;
}

.btn:hover {
	background-color: #F29228;
	color: white;
}
</style>
</head>
<c:set var="myAvt" value="${myAvt}" />
<!-- avatar메뉴 -->
<jsp:include page="../common/avatarMenu.jsp" />
<!-- avatar컨텐츠 -->
<article id="avatar-content">
	<div id="avatar-indiv">
		<h3>
			<img id="avatar-img" class="avatar-img" src="${myAvt.avt_img}">
		</h3>
		<h3 id="mavt-nm">${myAvt.mavt_nm}</h3>
		<div id="avatar-indiv-gauge">
			<h4 id="sun">
				햇빛 <span id="sun-c" class="gauge"> <c:forEach var="sun"
						begin="1" end="${myAvt.sun_stp}">●</c:forEach><c:forEach begin="1" end="${5-(myAvt.sun_stp)}">○</c:forEach>
				</span>
			</h4>
			<h4 id="water">
				수분 <span id="water-c" class="gauge"> <c:forEach var="water"
						begin="1" end="${myAvt.water_stp}">●</c:forEach><c:forEach begin="1" end="${5-(myAvt.water_stp)}">○</c:forEach>
				</span>
			</h4>
			<h4 id="nutrition">
				영양 <span id="nut-c" class="gauge"> <c:forEach var="nut"
						begin="1" end="${myAvt.nut_stp}">●</c:forEach><c:forEach begin="1" end="${5-(myAvt.nut_stp)}">○</c:forEach>
				</span>
			</h4>
		</div>
	</div>
	<div id="avatar-option">
		<input type="button" class="btn option" id="sun-offer" value="햇빛주기">
		<input type="button" class="btn option" id="water-offer" value="물주기">
		<input type="button" class="btn option" id="nut-offer" value="영양주기">
	</div>
	<h2 id="complete-gauge" style="visibility:hidden">멋진 작물이 되었어요!</h2>
	<input type="button" class="btn" id="upgrade" value="단계 UP">
</article>
<script type="text/javascript">
	//메뉴 색 설정
	var present_tab = document.querySelector("#avatar-menu a:first-child");
	present_tab.classList.add("active");
	//공통 함수
	var handler = function preOffer(event){
		var option = event.target.getAttribute("id");
		var optionVal = option.slice(0, -6);
		offer(optionVal);
	}
	//옵션 버튼 클릭 시 게이지 채우기 함수 호출
	var btns = document.querySelectorAll(".option");
	btns.forEach(function(btn, index, arr) {
		btn.addEventListener("click",handler, true);
	});

	//게이지 채우기 ajax
	function offer(e) {
		var xhr = new XMLHttpRequest();
		xhr.open("post", "${path}/offer?visitor=${myAvt.user_cd_pk}&option=" + e
				+ "&mavt_cd_pk=${myAvt.mavt_cd_pk}", true);
		xhr.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xhr.onreadystatechange = function() {
			try {
				if (xhr.readyState == 4 && xhr.status == 200) {
					var mavt = JSON.parse(xhr.responseText);
					//option에 따른 gauge 종류 선택
					var option = document.querySelector("#" + e + "-c");
					if (e == "sun")
						var stp = mavt.sun_stp;
					if (e == "water")
						var stp = mavt.water_stp;
					if (e == "nut")
						var stp = mavt.nut_stp;
					//gauge 채우기
					option.innerHTML = "<span>●</span>".repeat(stp)
							+ "<span>○</span>".repeat(5 - stp);
					//단계 업그레이드
					var stps = [mavt.sun_stp, mavt.water_stp, mavt.nut_stp];
					stps.forEach(function(stp, index, arr){
						if(stp==5){
							btns[index].removeEventListener("click",handler,true);
						}
					});
					var result = stps.every(function(stp, index, arr){
						return stp==5;
					});
					if(result && mavt.avt_stp==3){
						completeStp();
					}else if(result) {
						showUpgrade();
					}else{
						var nowPoint = document.querySelector("#nowPoint");
						nowPoint.innerText = mavt.profile_point;
					}
				}
			} catch (e) {
				if(e instanceof SyntaxError){
					alert("포인트가 부족합니다!");
				} else{
					alert(e.message);
				}
			}
		}
		xhr.send();		
	} 
	//단계 업그레이드 - 비동기
	if(${myAvt.sun_stp==5 &&myAvt.water_stp==5&&myAvt.nut_stp==5 &&myAvt.avt_stp==3}){
		completeStp();
	} else if(${myAvt.sun_stp==5 &&myAvt.water_stp==5&&myAvt.nut_stp==5}){
		showUpgrade();
		btns.forEach(function(btn, index, arr) {
			btn.removeEventListener("click",handler, true);
		});
	}
	//단계 업그레이드 -> 이미지 변경, 게이지 초기화 
	var upgradeBtn = document.querySelector("#upgrade");
	function showUpgrade(){
		document.querySelector("#upgrade").style.visibility="visible";
	}
	upgradeBtn.onclick=function(){
		location.href="${path}/upgrade?mavt_cd_pk=${myAvt.mavt_cd_pk}&visitor=${myAvt.user_cd_pk}";
		this.style.visibility="hidden";
	}
	//마지막 단계 -> 버튼 숨김처리
	function completeStp(){
		document.querySelector("#avatar-option").style.visibility="hidden";
		document.querySelector("#complete-gauge").style.visibility="visible";
	}
	
</script>