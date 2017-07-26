<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>로그인</title>
<head>
<%@include file ="/resources/common/head.jsp" %>

<script>

	$(document).ready(function(){
	    // 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
	    var userInputId = getCookie("userInputId");
	    $("input[name='loginId']").val(userInputId); 
	     
	    if($("input[name='loginId']").val() != ""){ // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
	        $("#idSaveCheck").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
	    }
	     
	    $("#idSaveCheck").change(function(){ // 체크박스에 변화가 있다면,
	        if($("#idSaveCheck").is(":checked")){ // ID 저장하기 체크했을 때,
	            var userInputId = $("input[name='loginId']").val();
	            setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
	        }else{ // ID 저장하기 체크 해제 시,
	            deleteCookie("userInputId");
	        }
	    });
	     
	    // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
	    $("input[name='loginId']").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
	        if($("#idSaveCheck").is(":checked")){ // ID 저장하기를 체크한 상태라면,
	            var userInputId = $("input[name='loginId']").val();
	            setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
	        }
	    });
	    
	    $(document).keyup(function(key){
	    	if(key.keyCode == 13){
	    		$(".loginBtn").click();
	    	}
	    })
		
	    $(".loginBtn").click(function(){
			
			if($.trim($("input[name='loginId']").val()).length < 1){
				alert("아이디를 입력해주세요");
				$("input[name='loginId']").focus();
				return false;
			} else {
				$.ajax({
					url : "/Socket.web/user/login.do"
					,type : "post"
					,data : {
						"loginId" : $("input[name='loginId']").val()
						,"password" : $("input[name='password']").val()
						}
					,success : successCallback
					,error : errorCallback
				});
			}
		});
	});
	 
	function setCookie(cookieName, value, exdays){
	    var exdate = new Date();
	    exdate.setDate(exdate.getDate() + exdays);
	    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
	    document.cookie = cookieName + "=" + cookieValue;
	}
	 
	function deleteCookie(cookieName){
	    var expireDate = new Date();
	    expireDate.setDate(expireDate.getDate() - 1);
	    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
	}
	 
	function getCookie(cookieName) {
	    cookieName = cookieName + '=';
	    var cookieData = document.cookie;
	    var start = cookieData.indexOf(cookieName);
	    var cookieValue = '';
	    if(start != -1){
	        start += cookieName.length;
	        var end = cookieData.indexOf(';', start);
	        if(end == -1)end = cookieData.length;
	        cookieValue = cookieData.substring(start, end);
	    }
	    return unescape(cookieValue);
	}
	
	var successCallback = function(data){
		if(data){
			$(location).attr("href", "/Socket.web/room/roomListByCategory.do");
		}else{
			$('#loginCheck').text("아이디 또는 패스워드가 잘못되었습니다.");
		}
	}
	var errorCallback = function(){
		alert("수행중 오류가 발생했습니다.");
	}

</script>
</head>
<body>
<!-- Links (sit on top) -->
<!--head link-->
<%@include file ="/resources/common/navigation.jsp" %>
<%@include file ="/resources/common/slide.jsp" %>
<!-- Header with image -->


<div class="w3-container" id="menu">
  <div class="w3-content" style="max-width:700px">  
    <div class="w3-row w3-center  w3-padding">
      <form action="${pageContext.request.contextPath}/user/login.do" method="post" class="pageForm w3-container w3-card-4 w3-light-grey w3-text-teal w3-margin" style="max-width:700px">
      <div class="w3-row w3-center  w3-padding">
        <h2 class="w3-center">로그인</h2>

      <div class="w3-row w3-section">
        <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-id-card-o"></i></div>
          <div class="w3-rest">
            <input class="w3-input w3-border" name="loginId" type="text" placeholder="아이디를 입력해주세요" required>
             <span id="loginCheck" class="w3-left-align w3-text-red"></span>  
          </div>
      </div>

      <div class="w3-row w3-section">
        <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-key"></i></div>
          <div class="w3-rest">
            <input class="w3-input w3-borFder" name="password" type="password" placeholder="비밀번호를 입력해주세요" required>
          </div>
      </div>
      <div>
       <input type="checkbox" id="idSaveCheck">  
       <label class="" for="idSaveCheck">아이디 저장</label>
      </div>
        <a href="${pageContext.request.contextPath}/views/registerUser.jsp" class="w3-btn w3-section w3-blue-grey w3-ripple w3-padding w3-margin-right">회원가입</a>
        <a class="w3-btn w3-section w3-green w3-ripple w3-padding loginBtn">로그인</a>
       
        </div>
      </form> 
    </div>
  </div>
</div>


<!--Footer link-->
<%@include file ="/resources/common/footer.jsp" %>  


</body>
</html>