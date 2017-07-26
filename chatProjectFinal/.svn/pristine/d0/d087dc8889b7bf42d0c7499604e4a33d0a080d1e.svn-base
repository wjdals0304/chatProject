<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>사용자 정보</title>
<head>
 <%@include file ="/resources/common/head.jsp" %>
     <style>
      
    .test_box{
      position: fixed;
      left:40%;
      top:50%;
      z-index: 30;
      display: none;
    }
    #mask {  
      position:absolute;  
      left:0;
      top:0;
      z-index:20;  
      background-color:#000;  
      display:none;  
      opacity: 0.5;
    }
    </style>
  <script>
  

  $(document).ready(function(){
      $(".pop_test").click(function(){
        $(".test_box").css("display","block");
        //스크롤 없앰
        $('html, body').css({'overflow': 'hidden', 'height':'100%'});        
        //마스크를 100%로 덮는다
        $('#mask').css({'width':'100%','height':'100%'});  
        //마스크 나타나게
        $('#mask').fadeIn("fast");      
      })
      $(".close").click(function(){
        $(".test_box").fadeOut("fast");
        $('#mask').fadeOut("fast");
        $('html, body').css({'overflow':'visible', 'height':'100%'});
      })
   })
  </script>
</head>
</head>


<body>
  <div id="mask" class="close"></div>
  <a class="pop_test">팝업창창창</a>
  <div id="popup" class="test_box w3-round-large w3-container w3-animate-zoom">
  <div class="w3-modal-content w3-card-8" style="width:230px">
    <header class="w3-container w3-teal"> 
        <span class="w3-closebtn close">&times;</span>
        <h4>비공개방 입장</h4>
      </header>
    <div class="w3-container w3-white">
	     <p><b>비밀번호 : </b> <input id="inputPassword"type="password" style="width:100px"></p>
        <button  id="btnSecretJoin" class="w3-btn-block w3-section w3-center w3-light-blue w3-ripple w3-padding w3-text-white">입장</button>
    </div>
  </div>
  </div>
  

  <button onclick="document.getElementById('id01').style.display='block'" class="w3-btn">Open Animated Modal</button>

  <div id="id01" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom w3-card-8">
      <header class="w3-container w3-teal"> 
        <span onclick="document.getElementById('id01').style.display='none'" 
        class="w3-closebtn">&times;</span>
        <h4>Modal Header</h4>
      </header>
      <div class="w3-container">
        <p>Some text..</p>
        <p>Some text..</p>
      </div>
    </div>
  </div>
</body>
</html>