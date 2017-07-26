<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<title>MyPage</title>
<head>
<%@include file ="/resources/common/head.jsp" %>
</head>
<body>
<!-- Links (sit on top) -->
<!--head link-->
<%@include file ="/resources/common/navigation.jsp" %>
<%@include file ="/resources/common/slide.jsp" %>
<!-- Header with image -->
<script>
	$(function(){
		
		var nickCheck = true;
		var u_email = $('input[name=email]');
		
		$("#nickName").keyup(function(){
			var nickLength = $("#nickName").val().length;
			if(nickLength>0){
				$.ajax({
					url: "/Socket.web/user/nickNameCheck.do"
					,type: "post"
					,data: { 
						nickName : $("#nickName").val()
						,loginId : $("#loginId").val()
						}
					,success: function(data){
			    		if(data){
			    			$("#nickNamemessage").text("사용 가능한 대화명입니다.").removeClass("w3-text-red");
			    			nickCheck = true;
			    		} else {
			    			$("#nickNamemessage").text("존재하는 대화명입니다.").addClass("w3-text-red");
			    			nickCheck = false;
			    		}
			    		
			    	}
					,error: function(){
			    		alert("수행중 오류가 발생했습니다");
			    	}
				})
			} else {
				$("#nickNamemessage").text("대화명은 4글자로 제한됩니다.").removeClass("w3-text-red");
				nickCheck = false;
			}
		})
		
		$(".password").keyup(function(){
			if($("#password").val() ==  $("#checkpw").val()){
				$("#pwmessage").text("비밀번호가 일치합니다.").removeClass("w3-text-red");
			}else{
				$("#pwmessage").text("비밀번호가 일치하지 않습니다.").addClass("w3-text-red");
			}
		})
	
		$(".mypageForm").submit(function(){
			if($("#password").val() !=  $("#checkpw").val()){
				alert("비밀번호를 확인해주세요.");
				$("#checkpw").focus();
				return false;
			}
			
			if(nickCheck==false){
				alert("대화명을 확인해주세요.");
				$("#nickName").focus();
				return false;
			}
			
			var regEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
			
			if( !u_email.val() ){
	            alert('이메일주소를 입력 해 주세요');
	            u_email.focus();
	            return false;
	            
	        } else {
	        	
	            if(!regEmail.test(u_email.val())) {
	                alert('이메일 주소가 유효하지 않습니다');
	                u_email.focus();
	                return false;
	            }
	        }
			
			alert("수정이 완료되었습니다.");
		})
	})
</script>
<!-- Menu Container -->
<div class="w3-container" id="menu">
  <div class="w3-content" style="max-width:700px">  
    <div class="w3-row w3-center  w3-padding">  
		<form action="${pageContext.request.contextPath}/user/modify.do" method="post" class="mypageForm w3-container w3-card-4 w3-light-grey w3-text-teal w3-margin" style="max-width:700px">
		<input type="hidden" name="userId" value="${sessionScope.loginUser.userId}">
		<input type="hidden" name="loginId" id=loginId value="${sessionScope.loginUser.loginId}">
		<h2 class="w3-center">나의 정보</h2>
		 
		<div class="w3-row w3-section">
		  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user-circle-o"></i></div>
		    <div class="w3-rest">
		      <input class="w3-input w3-border fa fa-user-circle-o" maxlength="4" id="nickName" name="nickName" type="text" value="${sessionScope.loginUser.nickName}" placeholder="대화명을 입력해주세요">
		    <span id="nickNamemessage" class="w3-left-align">대화명은 4글자로 제한됩니다.</span>
		    </div>
		</div>
		<div class="w3-row w3-section">
		  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-envelope"></i></div>
		    <div class="w3-rest">
		      <input class="w3-input w3-border" name="email" type="text" value="${sessionScope.loginUser.email}" placeholder="이메일을 입력해주세요">
		    </div>
		</div>
		
		<div class="w3-row w3-section">
		  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-key"></i></div>
		    <div class="w3-rest">
		      <input class="password w3-input w3-border" id="password" name="password" type="password" value="${sessionScope.loginUser.password}" placeholder="패스워드를 입력해주세요" >
		    </div>
		</div>
		
		<div class="w3-row w3-section">
		  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-check-circle"></i></div>
		    <div class="w3-rest">
		      <input class="password w3-input w3-border" id="checkpw" name="confirm" type="password" value="${sessionScope.loginUser.password}" placeholder="패스워드를 한번 더 입력해주세요">
		      <span id="pwmessage"></span>
		    </div>
		</div>
		<div class="w3-row w3-section">
		  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-home"></i></div>
		    <div class="w3-rest">
		      <input class="w3-input w3-border" name="address" type="text" value="${sessionScope.loginUser.address}" placeholder="주소를 입력해주세요">
		    </div>
		</div>
		
		<div class="w3-row w3-section">
		  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-birthday-cake"></i></div>
		    <div class="w3-rest">
		      <input class="w3-input w3-border" name="age" type="text" value="${sessionScope.loginUser.age}" placeholder="나이를 입력해주세요">
		    </div>
		</div>
		<div class="w3-row w3-section">
		  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-heart"></i></div>
		    <div class="w3-rest">
		      <select name="category" class="w3-select">
		      	<option value="EDU" <c:if test="${'EDU' eq sessionScope.loginUser.category.code}">selected="selected"</c:if>>교육</option>
		      	<option value="GAM" <c:if test="${'GAM' eq sessionScope.loginUser.category.code}">selected="selected"</c:if>>게임</option>
		      	<option value="CUK" <c:if test="${'CUK' eq sessionScope.loginUser.category.code}">selected="selected"</c:if>>요리</option>
		      	<option value="TRA" <c:if test="${'TRA' eq sessionScope.loginUser.category.code}">selected="selected"</c:if>>여행</option>
		      	<option value="SPO" <c:if test="${'SPO' eq sessionScope.loginUser.category.code}">selected="selected"</c:if>>스포츠</option>
		      	<option value="ARE" <c:if test="${'ARE' eq sessionScope.loginUser.category.code}">selected="selected"</c:if>>지역</option>
		      	<option value="ENT" <c:if test="${'ENT' eq sessionScope.loginUser.category.code}">selected="selected"</c:if>>엔터테인먼트</option>
		      	<option value="CHI" <c:if test="${'CHI' eq sessionScope.loginUser.category.code}">selected="selected"</c:if>>육아</option>
		      	<option value="ETC" <c:if test="${'ETC' eq sessionScope.loginUser.category.code}">selected="selected"</c:if>>기타</option>
		      </select>
		    </div>
		</div>



<button class="w3-btn w3-section w3-green w3-ripple w3-padding w3-margin-right" type="submit">수정</button>
<a href="${pageContext.request.contextPath}/room/roomList.do" class="w3-btn w3-section w3-red w3-ripple w3-padding">취소</a>

      </form>
    </div>
  </div>
</div>
      
<!-- End page content -->



<!--Footer link-->
<%@include file ="/resources/common/footer.jsp" %>  


<script>


// Tabbed Menu
function openMenu(evt, menuName) {
  var i, x, tablinks;
  x = document.getElementsByClassName("menu");
  for (i = 0; i < x.length; i++) {
     x[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablink");
  for (i = 0; i < x.length; i++) {
     tablinks[i].className = tablinks[i].className.replace(" w3-dark-grey", "");
  }
  document.getElementById(menuName).style.display = "block";
  evt.currentTarget.firstElementChild.className += " w3-dark-grey";
}
document.getElementById("myLink").click();
</script>

</body>
</html>