<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>Simple Talk</title>
<%@include file="/resources/common/head.jsp"%>
<script>
	//선영추가.
	var admin;
	var roomNumber;
	var ca;
	
	$(document).ready(function() {
		$(".infoBtn").click(function() {
			$("#adminUser").empty();
			$("#generalUser").empty();
			$("#optionRoomInfo").css({
				"display" : "block",
				'top' : $(this).offset().top,
				'left' : '45%'
			});
		})
	   
		$(".close").click(function(){
    	  $(".test_box").fadeOut("fast");
          $('#mask').fadeOut("fast");
          $('html, body').css({'overflow':'visible', 'height':'100%'});
          $('#inputPassword').val("");
          
      });
	
		$(".infoClose").click(function() {
			$("#optionRoomInfo").css("display", "none");
		})

		$("#btnMore").click(function() {

			more();
		})
		
		if($("#selCate").val() == ""){
		
			if($("#symbol").val() == 'true'){
				$("#selectCategory").val('ALL');
				
			}else{
		
				var userCategory = '${sessionScope.loginUser.category}'
				$("#selectCategory").val(userCategory);
					
			}
			
		}else{
			var selectCategory = $("#selCate").val();
			$("#selectCategory").val(selectCategory);
		
		}

		
		
		$("#selectCategory").change(function(){
			cate = $(this).val();
			location.href = '/Socket.web/room/roomListByCategory.do?selectCategory='+$(this).val()
		});
		
		$("#inputPassword").keydown(function (key) {
		    if (key.keyCode == 13) {
		        $("#btnSecretJoin").click();
		    }
		});
		
		$("#btnSecretJoin").click(function(){
			

			$.ajax({
				url : "/Socket.web/room/chkSecretPassword.do"
				,type : "post"
				,data : {"password" : $("#inputPassword").val()
						 ,"roomNumber" : roomNumber}
				
				,success : function(data){
							alert(data)
								if(data){
									moveJoin(roomNumber);
								}else{
									alert("비밀번호가 일치하지 않습니다");
								}
						   }
				,error : function(){
							alert("에러발생");
						}
			});
		});
	     		
	});
	
	
	$(document).on("click","#registerRoom",function(){
		
		$.ajax({
			url : "/Socket.web/room/chkAdmin.do",
			type : "post",
			data : {"loginId" : '${sessionScope.loginUser.loginId}'}
			,success : function(data){
							if(data){
								
								alert("방은 1개만 생성할 수 있습니다")
							}else{
								
								
								location.href = "${pageContext.request.contextPath}/room/registerRoom.do";
							}
								
						} 
			,error : function(){
						alert("수행중 에러")
					}

		});
	})
	
	var moveJoin = function(roomNumber){
		window.open('/Socket.web/roomEntry/joinRoom.do?roomNumber='+roomNumber+'&loginId='+'${sessionScope.loginUser.loginId}','newWindow'+roomNumber,'width=700,height=700');
	}
	
	

	var index = 1;
	var nextData;
	var more = function() {
		
		
		if('${sessionScope.cate}' == null || '${sessionScope.cate}' == ""){
			var ca = '${sessionScope.loginUser.category}'
		}else{
			var ca = '${sessionScope.cate}'
		}
		index++;
		next()
		index--;
		$.ajax({
			url : "/Socket.web/room/listMore.do"
			,type : "post"
			,data : {"more" : index
					 ,"cate" : ca }
			
			,success : successCallback
			,error : errorCallback
		
		});

	};
	
	var next = function(){

		index++;
		$.ajax({
				url : "/Socket.web/room/listMore.do"
				,type : "post"
				,data : {"more" : index
						 ,"cate" : '${sessionScope.cate}' }
				
				,success : testcall
				
				,error : errorCallback

		});
	};
	var testcall = function(data){
		nextData = data
	}

	
	function krCategory(category){
		
		switch(category){
		
		case "EDUCATION" :
			return "교육";
			break;
		case "GAME" :
			return "게임";
			break;
		case "COOKING" :
			return "요리";
			break;
		case "TRAVEL" :
			return "여행";
			break;
		case "SPORTS" :
			return "스포츠";
			break;
		case "AREA" :
			return "지역";
			break;
		case "ENTERTAINMENT" :
			return "엔터테인먼트";
			break;
		case "CHILDCARE" :
			return "육아";
			break;
		case "ETC" :
			return "기타";
			break;
		default :
			return "전체";
		
		}
		
		return category.krName;
	}
	
	function lock(e){
		if(e){
			var str = " <span class='fa fa-lock'>";
			return str;
		}else{
			
			return " ";
		}
	}
	
	function viewUser(inUser,limitUser){
		var inUserLength = inUser.length;
		var str = "("+ inUserLength+"/"+limitUser+")"
		return str;
		
	}
	
	$(document).on("click","#nomalJoin",function(){
		
		value = $(this).val();
		data = value.split(",")
		
		roomNumber = data[0]
		inRoom = data[1]
		
		
		$.ajax({
			url : "/Socket.web/roomEntry/chkJoinRoom"
			,type : "post"
			,data : {"loginId" : '${sessionScope.loginUser.loginId}' }
			
			,success : function(count){
						
					 	if(count==3 & inRoom == "false"){
					 		
					 			alert("최대 입장할 수 있는 방은 3개 입니다.")
							
						}else{
							
							window.open('/Socket.web/roomEntry/joinRoom.do?roomNumber='+roomNumber+'&loginId=${sessionScope.loginUser.loginId}','newWindow'+roomNumber,'width=700,height=700') 
								
						}
					}
			
			,error : function(){
						alert("수행중 에러가 발생했습니다.")
					}

		});
		
		
		
	})
	
    $(document).on("click",".pop_test", function() { 

        $(".close").click(function(){
      	  $(".test_box").fadeOut("fast");
            $('#mask').fadeOut("fast");
            $('html, body').css({'overflow':'visible', 'height':'100%'});
            $('#inputPassword').val("");
            
        });
        
    	value = $(this).find("button").val();
    	
		data = value.split(",")
		
		roomNumber = data[0]
		inRoom = data[1]
		
    	var join= $("#joinRooms").val();
		
		if(join == 3 && inRoom==false){
		
			alert("최대 입장할 수 있는 방은 3개 입니다.")
		}else{
			$(".test_box").css("display","block");
	        //스크롤 없앰
	        $('html, body').css({'overflow': 'hidden', 'height':'100%'});        
	        //마스크를 100%로 덮는다
	        $('#mask').css({'width':'100%','height':'100%'});  
	        //마스크 나타나게
	        $('#mask').fadeIn("fast");
	        
	        
		}
      });

      
	function enterRoom(inUser,limitUser,lock,roomNumber,inRoom){
		var inUserLength = inUser.length;
		console.log(inUserLength)
		console.log(limitUser)
		console.log(lock)
		console.log(roomNumber)
		console.log(inRoom)
		
		if(inUserLength<limitUser){

				if(lock){
					var str = "<a class='pop_test'><button class='w3-btn w3-blue-grey w3-tiny' value="+roomNumber+","+inRoom+"><b>입장</b></button></a>"
			          return str;
				}else{
					var str = "<button id='nomalJoin' class='w3-btn w3-blue-grey w3-tiny' value="+roomNumber+","+inRoom+" >"
							+ "<b>입장</b></button>"
					  return str;
				}
		
		}else{
			
			if(inRoom){
				
				if(lock){
					var str = "<a class='pop_test'><button class='w3-btn w3-blue-grey w3-tiny' value="+roomNumber+","+inRoom+"><b>입장</b></button></a>"
			          return str;
				}else{
					var str = "<button id='nomalJoin' class='w3-btn w3-blue-grey w3-tiny' value="+roomNumber+","+inRoom+" >"
							+ "<b>입장</b></button>"
					  return str;
				}
				
			}else{
				var str="<span><button class='w3-btn w3-red w3-tiny' disabled>"
						+"<b>입장불가</b></button></span>"
				return str;
				
			}
	
		}
		
	}
	
	var successCallback = function(data) {
				
		$.each(data, function(index, room) {
			if (Object.keys(data).length == 10 || nextData == "") {
				$("#btnMore").hide();
			};
			
			loginId = '${sessionScope.loginUser.loginId}'
			
			var inRoom = false;
			
			$.each(room.users,function(i){
				if(room.users[i].user.loginId == loginId){
					
					inRoom=true;
				}
			})
			var contents = "<li><div class='w3-text-grey w3-small'><span class='w3-text-naver w3-medium'>"+krCategory(room.category)+"</span>"
            +"<a id='test' data-admin='"+room.admin.loginId+"' data-roomNumber= '"+room.roomNumber+"'"
            +"class='userInfo fa fa-ellipsis-h w3-xlarge infoBtn'" 
            +" aria-hidden='true' style='float: right;'></a></div>"
            +"<div class='w3-text-dark-grey w3-large'><span><b>"+room.title+"</b></span>"
            +lock(room.secret)+"</div>"
			+"<div class='w3-text-grey'> <span class='w3-medium'>"
			+"참여인원"+viewUser(room.users,room.limitUsers)+"&nbsp;</span>&nbsp;&nbsp;"
			+"<span class = 'w3-white w3-border w3-border-black w3-small'>방장</span>&nbsp"
			+"<span class = 'w3-medium'>"+room.admin.nickName+"("+room.admin.loginId+")</span>"+"&nbsp;</div>"
			+"<div>"+enterRoom(room.users,room.limitUsers,room.secret,room.roomNumber,inRoom)
			
			$("#roomList").append(contents);

		});
	};

	var errorCallback = function() {

		alert("수행중 오류발생");
	};
	
	$(document).on("click",".infoBtn",function(){
		
		$("#optionRoomInfo").css({
			"display" : "block",
			'top' : $(this).offset().top,
			'left' : '45%'
		});
	
	});
		
	
	$(document).on("click","#test",function(){
		
		admin = $(this).data("admin");
		roomNumber = $(this).data("roomnumber");
		var contents=""
		$.ajax({
			url : "/Socket.web/roomEntry/userInfo.do"
			,type : "post"
			,data : {"roomNumber" : $(this).data("roomnumber") }
			,success : function(data){
				roomEntryList=data;
		    	 $.each(roomEntryList,function(i){
		    		  if(admin == roomEntryList[i].user.loginId){
		    			$("#adminUser").html(roomEntryList[i].user.nickName+"("+roomEntryList[i].user.loginId+")"); 
		    		  }
		    		  else{
		    			  $("#generalUser").append(roomEntryList[i].user.nickName+"("+roomEntryList[i].user.loginId+")<br>"); 
		    		  }
		    	 }); 
		    	 
				 }
			,error : function(){
						alert("에러");
			}
		});
		
 		$(".infoClose").click(function (){
			$("#adminUser").empty();
			$("#generalUser").empty();
		}); 

		$("#enterRoom").click(function(){
		window.open('/Socket.web/roomEntry/joinRoom.do?roomNumber='+roomNumber+'&loginId=${sessionScope.loginUser.loginId}','newWindow'+roomNumber,'width=700,height=700') 
		});
		
	})
	//정민 
var roomNumber;
$(document).ready(function(){
	$(".userInfo").click(function(){
		admin = $(this).data("admin");
		roomNumber = $(this).data("roomnumber");
		console.log(admin);
		$.ajax({
			url : "/Socket.web/roomEntry/userInfo.do"
			,type : "post"
			,data : {"roomNumber" : $(this).data("roomnumber") }
			,success : userCallback
			,error : userErrorCallback
		});
	});
	
	var roomEntryList=[];
	var contents=""
	var userCallback = function(data){
    	 roomEntryList=data;
    
    	 $.each(roomEntryList,function(i){
    		  if(admin == roomEntryList[i].user.loginId){
    			$("#adminUser").html(roomEntryList[i].user.nickName+"("+roomEntryList[i].user.loginId+")"); 
    		  }
    		  else{
    			  $("#generalUser").append(roomEntryList[i].user.nickName+"("+roomEntryList[i].user.loginId+")<br>"); 
    		  }
    	 }); 
    	 
	}
	var userErrorCallback = function(){
		alert("error");
	}
	
	$(".infoClose").click(function (){
		$("#adminUser").empty();
		$("#generalUser").empty();
	}); 

	$("#enterRoom").click(function(){
	
	window.open('/Socket.web/roomEntry/joinRoom.do?roomNumber='+roomNumber+'&loginId=${sessionScope.loginUser.loginId}','newWindow'+roomNumber,'width=700,height=700') 
		
	});
});	
	
</script>

</head>
<body>

	<!-- Links (sit on top) -->

	<%@include file="/resources/common/navigation.jsp"%>
	<%@include file="/resources/common/slide.jsp"%>
	<!-- Header with image -->


	<!-- 비번방 팝업창 -->
		<div id="mask" class="close"></div>
		<div id="popup" class="test_box w3-round-large w3-container w3-animate-zoom">
  			<div class="w3-modal-content w3-card-8" style="width:230px">
		     <header class="w3-container w3-teal"> 
		       <span class="w3-closebtn close closeInfo">&times;</span>
		       <h4>비공개방 입장</h4>
		     </header>
			    <div class="w3-container w3-white w3-center">
					<p>
					<b>비밀번호 입력</b> <input id="inputPassword" type="password" style="width:100px" maxlength="4" >
					</p>
					<p>비밀번호는 기본 4자로 제한되어있습니다.</p>
			    	<button id="btnSecretJoin" class="w3-btn-block w3-section w3-center w3-green w3-ripple w3-padding">입장</button>  
			    </div>
		      </div>   
			</div>
		<!-- End page content -->

	<!-- Menu Container -->
	<div class="w3-container">
		<div class="w3-content" style="max-width: 700px">
			<div class="w3-card-4 w3-animate-zoom" id="optionRoomInfo">
			  
				<header class="w3-container w3-teal"> 
		        <span class="w3-closebtn infoClose close">&times;</span>
		        <h4>대화방 정보</h4>
		   
		      </header>
			
				<div class="userList w3-container w3-white w3-small"
					style="overflow-y: scroll; height: 250px">
					<div class="w3-panel w3-pale-green w3-leftbar w3-border-green">
						<span>방장</span>
					</div>
					<ul class="w3-ul ">
						<li id=adminUser></li>
					</ul>
					<div class="w3-panel w3-pale-green w3-leftbar w3-border-green">
						<span>참여자</span>
					</div>
					<ul class="w3-ul ">
						<li id=generalUser></li>
					</ul>
				</div>
				
			</div>
			<div class="w3-row w3-padding" id="category">
				<select id="selectCategory" class="w3-select-main w3-medium" id="categoryBox">
					<option disabled selected>카테고리 선택</option>
					<option value="ALL">전체</option>
		            <option value="EDUCATION">교육</option>
		            <option value="GAME">게임</option>
		            <option value="COOKING">요리</option>
		            <option value="TRAVEL">여행</option>
		            <option value="SPORTS">스포츠</option>
		            <option value="AREA">지역</option>
		            <option value="ENTERTAINMENT">엔터테인먼트</option>
		            <option value="CHILDCARE">육아</option>
		            <option value="ETC">기타</option>
				</select>
				
				<button id="registerRoom"class="w3-btn w3-teal w3-medium"style="float: right;"><b>방만들기</b></button>
			</div>

			<hr>
			<div class="w3-row w3-padding" id="chatList">

				<!-- 내가 참여한 방 -->
				<div class="w3-panel w3-naver w3-round-large w3-border">현재 참여한 채팅방</div>
				<input type="hidden" id="joinRooms" value="${fn:length(roomEntryList)}">
				<div>
					<ul class="w3-ul w3-large">
					<c:forEach items="${roomEntryList}" var="roomEntry">	
						<li>
							<div class="w3-text-grey w3-small">
								<span class="w3-text-naver w3-medium">${roomEntry.room.category.krName}</span> <a data-admin="${roomEntry.room.admin.loginId}" data-roomNumber="${roomEntry.room.roomNumber}" class="userInfo fa fa-ellipsis-h w3-xlarge infoBtn"
									aria-hidden="true" style="float: right;"></a>
							</div>
							
							<div class="w3-text-dark-grey w3-large">
								<a href="#none"><span><b>${roomEntry.room.title}</b></span></a> 
								<c:if test="${roomEntry.room.secret}">
								<span class="fa fa-lock"></span>
								</c:if>
							</div>
							<div class="w3-text-grey">
								<span class="w3-medium">참여인원(${fn:length(roomEntry.room.users)}/${roomEntry.room.limitUsers})</span>&nbsp;&nbsp; <span 
									class = 'w3-white w3-border w3-border-black w3-small'>방장</span>
									<span class = 'w3-medium'>${roomEntry.room.admin.nickName}(${roomEntry.room.admin.loginId})</span>
							</div>
							
							<div>
								<c:choose>
									<c:when test="${ fn:length(roomEntry.room.users) lt roomEntry.room.limitUsers} ">
									<span>
										<c:choose>
											<c:when test="${roomEntry.room.secret}">
											<a class="pop_test">
											<button class="w3-btn w3-blue-grey w3-tiny" value="${roomEntry.room.roomNumber}" ><b>${roomEntry.room.roomNumber}입장</b></button></a>
											</c:when>
											<c:otherwise>
												<a onclick="window.open('/Socket.web/roomEntry/joinRoom.do?roomNumber=${roomEntry.room.roomNumber}&loginId=${sessionScope.loginUser.loginId}','newWindow${roomEntry.room.roomNumber }','width=700,height=700')" class="w3-btn w3-blue-grey w3-tiny">
												<b>입장</b></a>
											</c:otherwise>
										</c:choose>
									</span>
									</c:when>
									<c:otherwise>
						
									<c:if test="${roomEntry.user.loginId eq sessionScope.loginUser.loginId}">
										<c:set var="inRoom" value="true"/>
									</c:if>
										<c:choose>
											<c:when test="${inRoom eq true}">
											<c:set var="inRoom" value="false"/>
												<span>
													<c:choose>
														<c:when test="${roomEntry.room.secret}">
														<a class="pop_test">
														<button class="w3-btn w3-blue-grey w3-tiny" value="${roomEntry.room.roomNumber}"><b>입장</b></button></a>
														</c:when>
														<c:otherwise>
														<a onclick="window.open('/Socket.web/roomEntry/joinRoom.do?roomNumber=${roomEntry.room.roomNumber}&loginId=${sessionScope.loginUser.loginId}','newWindow${roomEntry.room.roomNumber }','width=700,height=700')" class="w3-btn w3-blue-grey w3-tiny">
														<b>입장</b></a>
														</c:otherwise>
													</c:choose>
												</span>
											</c:when>
											<c:otherwise>
												<span><button class="w3-btn w3-red w3-tiny" disabled>
													<b>입장불가</b>
												</button></span>
											
											</c:otherwise>
										
										</c:choose>
									</c:otherwise>
								</c:choose>
								</div>
							</li>
					</c:forEach>
					</ul>
				</div>
				<!-- 채팅방리스트 -->
				<div class="w3-panel w3-naver w3-round-large w3-border">채팅방 목록</div>
				<div>
					<ul id="roomList" class="w3-ul w3-large">
						<c:forEach items="${roomList}" var="room" varStatus="sts">
							<li>
								<div class="w3-text-grey w3-small">     
									<span class="w3-text-naver w3-medium">${room.category.krName} </span> <a data-admin="${room.admin.loginId}" data-roomNumber="${room.roomNumber}"
										class="userInfo fa fa-ellipsis-h w3-xlarge infoBtn" aria-hidden="true"
										style="float: right;"></a>
								</div>
								<div class="w3-text-dark-grey w3-large">
									<a href="#none"><span><b>${room.title }</b></span></a> 
									<c:if test="${room.secret}">
									<span class="fa fa-lock"></span>
									</c:if>
								</div>
								<div class="w3-text-grey">
									<span class="w3-medium">참여인원(${fn:length(room.users)}/${room.limitUsers })</span>&nbsp;&nbsp;
									<span class = 'w3-white w3-border w3-border-black w3-small'>방장</span>
									<span class = 'w3-medium'>${room.admin.nickName}(${room.admin.loginId})&nbsp;</span>
								</div>
								<div>
									<c:forEach items="${roomEntryList}" var="entry">
									<c:if test="${entry.user.loginId eq sessionScope.loginUser.loginId}">
										<c:if test="${entry.room.roomNumber eq room.roomNumber }">
										<c:set var="inRoom" value="true"/>
										</c:if>
									</c:if>
									</c:forEach>
								<c:choose>
									<c:when test="${ fn:length(room.users) lt room.limitUsers}">
									<span>
										<c:choose>
											<c:when test="${room.secret}">
											<a class="pop_test">
											<button class="w3-btn w3-blue-grey w3-tiny" value="${room.roomNumber},${inRoom}" ><b>입장</b></button></a>
											</c:when>
											<c:otherwise>
												<button id="nomalJoin" class="w3-btn w3-blue-grey w3-tiny" value="${room.roomNumber},${inRoom}" >
												<b>입장</b></button>
											</c:otherwise>
										</c:choose>
									</span>
									</c:when>
									<c:otherwise>
							
										<c:choose>
											<c:when test="${inRoom eq true}">
												<span>
													<c:choose>
														<c:when test="${room.secret}">
														<a class="pop_test">
														<button class="w3-btn w3-blue-grey w3-tiny" value="${room.roomNumber},${inRoom}"><b>입장</b></button></a>
														</c:when>
														<c:otherwise>
														<button id="nomalJoin" class="w3-btn w3-blue-grey w3-tiny"  value="${room.roomNumber},${inRoom}">
														<b>입장</b></button>
														</c:otherwise>
													</c:choose>
												</span>
											</c:when>
											<c:otherwise>
												<span><button class="w3-btn w3-red w3-tiny" disabled>
													<b>입장불가</b>
												</button></span>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
								<c:set var="inRoom" value="false"/>
								</div>
							</li>
						</c:forEach>
					</ul>
					
				</div>
			</div>
			<div class="w3-row w3-padding" id="listMore">
				<c:if test="${fn:length(roomList) ge 10}">
				<span><button id="btnMore"
						class="w3-btn-block w3-teal w3-medium">
						<b>더보기</b>
					</button></span>
				</c:if>
				
			</div>
			<div class="w3-tooltip w3-center">
				<a class="w3-text-dark-grey" href="#home"><span
					class="w3-xlarge"> <i class="fa fa-chevron-circle-up"></i></span></a>
			</div>
		</div>
		
	</div>
			
	<input type ="hidden" value="${selectCategory}" id="selCate">
	<input type ="hidden" value="${symbol}" id="symbol">
	<!--Footer link-->
	<%@include file="/resources/common/footer.jsp"%>

</body>
</html>