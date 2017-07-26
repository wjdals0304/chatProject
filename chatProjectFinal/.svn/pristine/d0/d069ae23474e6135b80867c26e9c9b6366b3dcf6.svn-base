<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>1번방</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
 <%@include file ="/resources/common/head.jsp" %>
<script>
  $(document).ready(function(){
      $(".noticeClose").click(function(){
        $(".notice").css({"display":"none"});  
        $(".noticeOpen").css({"display":"block"});
      })
      $(".noticeOpen").click(function(){
        $(".notice").css("display","block");
        $(".noticeOpen").css({"display":"none"});
      });
   });
  
  $(document).ready(function(){
		
	  $(".name").click(function(){
		  if( "${user.loginId}" != $(this).data("loginid")  ){  
	    	$(".user_box").css("display","block");
	        //스크롤 없앰
	        $('html, body').css({'overflow': 'hidden', 'height':'100%'});        
	        //마스크를 100%로 덮는다
	        $('#userMask').css({'width':'100%','height':'100%'});  
	        //마스크 나타나게
	        $('#userMask').show();   	
	        
	        var loginId = $(this).data("loginid");
	        $.ajax({
	            url:"/Socket.web/roomEntry/chatUserInfo.do",	
	            type:"post",
	            data:{"loginId":loginId},
	            success : function(data){
	         	   contents =  "<b>아이디</b>:"+"<span><b class='grantId' data-loginId="+data.loginId+">"+data.loginId+"</b></span><br>"
	         	            + "<b>이름</b>:"+"<span><b>"+data.name+"</b></span><br>"
	         	            + "<b>이메일</b>:"+"<span><b>"+data.email+"</b></span><br>"
	         	            + "<b>닉네임</b>:"+"<span><b>"+data.nickName+"</b></span>"
	         	  $("#userInfo").html(contents);
	            }
	            ,error:function(){
	         	   alert("error");
	            }
	         });
		  }
	    });
	
	    
	    $("#userMask").click(function(){
	        $(".user_box").css("display","none");
	        $('#userMask').css({'width':'0%','height':'0%'}); 
	        $('html, body').css({'overflow':'visible', 'height':'100%'});
	      })
	      

	/*       $(".grant").on("click",function(){

	    	  var loginId=$(".grantId").data("loginid");
	    
	    	  var roomNumber= ${room.roomNumber};
	       		    $.ajax({
	       		     url : "/Socket.web/roomEntry/grant.do"
	       		     ,type:"post"
	       		     ,data :{"loginId" : loginId,"roomNumber" : roomNumber}
	       		     ,success:function(data){
	       		   if(data){
	       			   alert("방장은 오직 한방만 가능합니다 !");
	       		   } else{
	       			opener.parent.location.reload();
	       		 	location.reload();
	       		   } 
	       		     }
	       		     ,error:function(){
	       		    	 alert("error");
	       		     }
	       		    })
	    }); */
	    
	    
  });
</script>
</head>
<body>

<div class="user_box w3-animate-zoom" >
  	<div class="w3-card-4" style="width:300px">
    <div class="w3-container w3-center w3-teal">
      <h4>사용자 정보 </h4></div>
    <div class="w3-container w3-white" >
      <ul class="w3-ul">
        	<li id="userInfo">
     	   </li>
      </ul>
    
    <%--  <c:if test="${user.loginId eq room.admin.loginId}" >
		 <a class="grant w3-btn w3-section w3-center w3-indigo w3-ripple w3-padding">위임</a>
	 </c:if> --%>
	 
    </div>
  </div>
 </div>
  
  <div class="jstalkthemeInfo" >
   <div id="userMask"></div> 
    <div class="w3-container w3-light-grey w3-text-dark-grey w3-medium w3-center" style="margin-bottom:15px; padding:12px; width:100%"> 
      <span id="ttalign">
        <a href="javascript:history.back();" class="fa fa-chevron-left w3-left w3-xlarge" ></a>
        <b>참여자보기</b>(${fn:length(room.users)}/${room.limitUsers})
      </span></div>
      </div>
      <div class="w3-content" style="max-width:700px">
        <div class="infoContainer w3-container">
          <div class="w3-panel w3-pale-green w3-leftbar w3-border-indigo w3-">
            <span>방장</span>
          </div>
          <ul class="w3-ul">
            <li class="name" data-loginId="${room.admin.loginId}"><a href="#none">${room.admin.nickName}(${room.admin.loginId})</a></li>
          </ul>
          <div class="w3-panel w3-pale-green w3-leftbar w3-border-indigo">
            <span>참여자</span>
          </div>
          <ul class="w3-ul">
          <c:forEach items="${roomEntryList}" var="roomEntry">
            <c:if test="${room.admin.nickName ne roomEntry.user.nickName}"> 
            <li class="name" data-loginId="${roomEntry.user.loginId}"><a href="#none">${roomEntry.user.nickName}(${roomEntry.user.loginId})</a></li>
          </c:if>
          </c:forEach>
          </ul>
        </div>
      </div>
  </body>
</html>