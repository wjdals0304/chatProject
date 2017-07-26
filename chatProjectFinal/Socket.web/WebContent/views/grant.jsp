<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>방장위임</title>
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
      })
   })
   $(document).ready(function(){
	     $("#grant").click(function(){
	    	var loginId = $(":radio:checked").data("loginid");
	    	var roomNumber =  $(":radio:checked").data("roomnumber");
	        var adminId="${room.admin.loginId}";
	       
			$.ajax({
   	 			url : "/Socket.web/roomEntry/grantAfterExit.do"
   	 			,type : "post"
   	 			,data : {"loginId" : loginId , "roomNumber" : roomNumber,"adminId" : adminId  }
   	 			,success : function(){
   	 			opener.parent.location.reload(); 
   	 				window.close();
   	 			}
   	 			,error : function(){
   	 				alert("error");
   	 			}
   	 		});
	     });
	     
    if($(".entrySize").data("entrysize") == 1  ){
   	 $.ajax({
   		url :"/Socket.web/roomEntry/exitRoom.do"
   		,type:"post"
   		,data: {"loginId" : "${room.admin.loginId}","roomNumber": "${room.roomNumber}","roomRemove" : "true"   }
    	,success : function(){
        opener.parent.location.reload(); 
    		window.close();
    	}	 
   		,error : function(){
   			alert("granterror");
   		}
   	 })    
    	
    }
        
	     
   });
   
</script>
</head>
<body>
  
  
  <div class="jstalkthemeInfo">
    <div class="w3-container w3-light-grey w3-text-dark-grey w3-medium w3-center" style="margin-bottom:15px; padding:12px; width:100%"> 
      <span id="ttalign">
        <a href="javascript:history.back();" class="fa fa-chevron-left w3-left w3-xlarge" ></a>
        <b>방장위임하기</b>
      </span>
      </div>
      <div class="w3-content" style="max-width:700px">
        <form id="form"  >
        <div class="infoContainer w3-container">
          <div class="w3-panel w3-pale-green w3-leftbar w3-border-indigo w3-">
            <span>방장</span>
          </div>
          <ul class="w3-ul">
            <li>${room.admin.nickName}(${room.admin.loginId})</li>
          </ul>
          <div data-entrySize="${fn:length(roomEntryList)}" class="entrySize  w3-panel w3-pale-green w3-leftbar w3-border-indigo">
            <span>참여자</span>
          </div>
          <ul class="w3-ul">
        
  <c:forEach items="${roomEntryList}" var="roomEntry">
    <c:if test="${roomEntry.user.loginId ne room.admin.loginId}" >
    
  <li>${roomEntry.user.nickName}(${roomEntry.user.loginId})
  <c:forEach items="${adminMap}" var="map">
   <c:if test="${map.key eq roomEntry.user.loginId }">
   <c:set var="check" value="true"/>
  </c:if>
  </c:forEach>
   
   <c:if test="${check ne true }">
   &nbsp;&nbsp;&nbsp;<input type="radio" name="radio" data-roomnumber="${room.roomNumber}"  data-loginid="${roomEntry.user.loginId}" style="width:20px;height:20px;">
   </c:if>
      </li>
   </c:if>
	<c:set var="check" value="false"/>
  </c:forEach>
 
          </ul>
        </div>
        <div class="w3-row w3-center">
      
          <a id="grant" class="w3-btn w3-red">위임하기</a>
        </div>
   				<input type="hidden" name="loginId" value="">
   				<input type="hidden" name="adminId" value="${room.admin.loginId}">
   				 <input type="hidden" name="roomNumber"value="${room.roomNumber}">
        </form>
      </div>

  </div>

</body>
</html>