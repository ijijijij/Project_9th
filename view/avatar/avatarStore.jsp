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
	text-decoration: none !important;
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
/* 아바타 */
.avatar-step-img {
	width: 100px;
	height: 100px;
}

#avatar-price {
	padding: 10px;
	color: #424242;
	font-size: 10pt;
}
/* 버튼 */
.btn {
	border: 1px solid #dae1e6 !important;
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

.btn:hover {
	background-color: #F29228;
	color: white;
}
</style>
<link rel="stylesheet" href="${path}/myRoom/css/bootstrap.min.css" >
<link rel="stylesheet" href="${path}/myRoom/css/jquery-ui.css" >
<script src="${path}/myRoom/css/jquery.min.js"></script>
<script src="${path}/myRoom/css/popper.min.js"></script>
<script src="${path}/myRoom/css/bootstrap.min.js"></script>
<script src="${path}/myRoom/css/jquery-ui.js"></script>
</head>
<c:set var="avtList" value="${avtList}" />
<!-- avatar메뉴 -->
<jsp:include page="../common/avatarMenu.jsp" />
<!-- avatar컨텐츠 -->
<article id="avatar-content">
	<c:forEach var="avt" items="${avtList}" varStatus="vst">
	<div id="avatar-indiv">
	
	
		<h3>
			<img class="avatar-step-img" src="${avt.stp_1st_img}"> <img
				class="avatar-step-img" src="${avt.stp_2nd_img}"> <img
				class="avatar-step-img" src="${avt.stp_3rd_img}">
		</h3>
		<h2>${avt.avt_nm}</h2>
		<h4 id="avatar-price">${avt.avt_prc} 포인트</h4>
		<p>${avt.avt_intro}</p>
		<br> <input type="button" id="takeBtn" class="btn" value="데려오기" 
		data-toggle="modal" data-target="#exampleModalCenter" data-notifyid="${avt.avt_cd_pk}">
	</div>
	</c:forEach>
</article>
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">아바타의 이름을 정해주세요</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<form id="frm02" class="form" action="takeAvatar?visitor=${param.visitor}" method="post">
	     <div class="row">
	      <div class="col">
	      	<input type="hidden" name="avt_cd_pk"/>
	        <input type="text" class="form-control" placeholder="아바타 이름 입력" name="mavt_nm">
	      </div>
	    </form> 
      </div>
      <div class="modal-footer">
      	<span id="message" style="font-size:9pt; text-align:left; color:#424242;">이름 입력 후 확인을 누르면 아바타 구매비용 200포인트가 차감됩니다.</span>
        <button type="button" class="btn btn-primary" id="buyBtn">확인</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
		//메뉴 색 변경
		var present_tab = document.querySelector("#avatar-menu a:nth-child(2)");
		present_tab.classList.add("active");
		//포인트 부족 시 버튼 비활성화
		var takeBtns = document.querySelectorAll("#takeBtn");
		"<c:if test='${profile.profile_point<200}'>" 
			takeBtns.forEach(function(btn,index,arr){
				btn.disabled = true;
			})
		"</c:if>" 
		//모달로 아바타 값 넘기기
		"<c:forEach var='avt' items='${avtList}' varStatus='vst'>"
			takeBtns["${vst.index}"].onclick=function(){
				frm02.avt_cd_pk.value = "${avt.avt_cd_pk}";
			}
		"</c:forEach>"
		//아바타 구매
		var buyBtn = document.querySelector("#buyBtn");
		buyBtn.onclick=function(){
			if(!validate()){
				return false;
			} else{
				frm02.submit();
			}
		}
		//이름 유효성 검사
		function validate(){
			var mavt_nm = frm02.mavt_nm.value;
			var regex = /^[a-zA-Z가-힣0-9]{1,10}$/;
			if(!regex.test(mavt_nm)){
				var message = document.querySelector("#message");
				message.innerText = "이름은 10글자 이내로 입력하세요";
				message.style.color = "red";
				return false;
			}
			return true;
		}
		
		
</script>