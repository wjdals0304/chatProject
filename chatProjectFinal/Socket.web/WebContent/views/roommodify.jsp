<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
  <title>대화방 설정</title>
 <%@include file ="/resources/common/head.jsp" %>
 
 <script type="text/javascript">
 $(function(){
		
		$("input:checkbox[name='secret']").click(function(){
			if($("input:checkbox[name='secret']").is(":checked") ){
				$("input[name='password']").removeAttr("disabled");
			} else {
				$("input[name='password']").attr("disabled",true);
			}
		})
		
		var limitUser = $("#limitUser").val();
		$("#selectBox").val(limitUser);
		$(".pageForm").submit(function(){
			if($("select[name='limitUsers']").val() >= $("#inUsers").val()){
				
				return true;
			}else{
				alert("현재 참여한 인원보다 낮게 설정 할 수 없습니다.");
				return false;
			}
		})
	})
 </script>
</head>

<body>
  <input type="hidden" id="inUsers" value="${fn:length(entry)}">
  <input type="hidden" id="limitUser" value="${room.limitUsers }">
  <div class="jstalkthemeInfo" style="height: 100%;">
    <div class="msg" id="chatMessage">
      <div class="w3-container w3-light-grey w3-text-dark-grey w3-medium w3-center" id="panel" style="margin-bottom:15px; padding:12px; width:100%"> <span id="ttalign"><a href="javascript:history.back();" class="fa fa-chevron-left w3-left w3-xlarge" ></a><b>대화방 설정</b></span>
        <div class="w3-right w3-large"> <a href="#none"><span class="fa fa-bell"></span></a>&nbsp; <a href="#none"><span class="fa fa-ellipsis-v"></span></a> </div>
      </div>
    </div>
    <div class="w3-content" style="max-width:700px"> 
    <form action="${pageContext.request.contextPath}/room/roomSetting.do" method="post" class="pageForm w3-container">
      <div id="create" class="w3-margin-top-panel"> 
      
      	<span style="font-size:19px;"><b>방 제목</b>&nbsp;</span>
        <input class="w3-input w3-border w3-round-large w3-animate-input" name ="title"type="text" 
        	   style="width:50%" value="${room.title}"placeholder="대화방 제목을 입력하세요">
        		  
        <span style="font-size:19px;"><b>참여인원</b>&nbsp;</span><br>
        <select class="w3-selectedit w3-border w3-round-large" name="limitUsers" id="selectBox">
          <option disabled selected>인원 수 선택</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
          <option value="6">6</option>
          <option value="7">7</option>
          <option value="8">8</option>
          <option value="9">9</option>
          <option value="10">10</option>
          <option value="15">15</option>
          <option value="20">20</option>
          <option value="25">25</option>
        </select><br>
        
		<span style="font-size:19px;"><b>주제</b>&nbsp;</span><br>
        <select class="w3-selectedit w3-border w3-round-large" name="category">
          <option disabled selected>카테고리 선택</option>
          <option value="EDUCATION">교육</option>
          <option value="GAME">게임</option>
          <option value="COOKING">요리</option>
          <option value="TRAVEL">여행</option>
          <option value="SPORTS">스포츠</option>
          <option value="AREA">지역</option>
          <option value="ENTERTAINMENT">엔터테인먼트</option>
          <option value="CHILDCARE">육아</option>
          <option value="ETC">기타</option>
        </select><br><br>
        
        <span style="font-size:19px;"><b>공지사항</b>&nbsp;</span><br>
        <textarea id="notice"class="w3-round-large w3-animate-input" name="notice" cols="50" rows="5" placeholder="공지사항을 입력하세요" 
        		  style="width:60%">${room.notice}</textarea><br>
        		  
        <span style="font-size:19px;"><b>비밀방 설정</b>&nbsp;<i class="w3-large fa fa-lock"></i>&nbsp;
        <input type="checkbox" id="btnChk"name="secret" style="width:15px;height:15px" onClick="agreeCheck(this.form)"></span>
        <input class="w3-input w3-border w3-round-large w3-animate-input" name ="password" type="password" 
        	   id ="inputPassword" style="width:50%" placeholder="비밀번호를 입력하세요" value="${room.password}" maxlength="4" disabled>
      </div>
      	<input type="hidden" name="roomNumber" value="${roomNumber}">
       <p>
        <button class="w3-btn w3-indigo editbutton">확인</button>
        <button onclick="history.back()" class="w3-btn w3-red" style="margin-left: 15px;">취소</button>
      </p>
    </form>
      </div>
  </div>
</body>

</html>