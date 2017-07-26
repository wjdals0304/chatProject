<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="bgimg w3-display-container w3-grayscale-min" id="home">
  <a class="w3-btn-floating w3-hover-dark-grey w3-display-left" onclick="plusDivs(-1)">&#10094;</a>
  <a class="w3-btn-floating w3-hover-dark-grey w3-display-right" onclick="plusDivs(1)">&#10095;</a>


  <div class="w3-display-container mySlides">
    <img src="${pageContext.request.contextPath}/resources/img/study.jpg" style="height:100%; width:100%;" class="w3-hover-grayscale">
    <div class="w3-display-bottomleft w3-large w3-container w3-padding-16 w3-text-white" style="opacity:0.9">
      같이 공부시작해요!
    </div>
  </div>
  
  <div class="w3-display-container mySlides ">
    <img src="${pageContext.request.contextPath}/resources/img/music.jpg" style="height:100%; width:100%;" class="w3-hover-grayscale">
    <div class="w3-display-middle w3-large w3-container w3-padding-16 w3-text-white" style="opacity:0.9">
      음악을 사랑하는 당신들과...
    </div>
  </div>

  <div class="w3-display-container mySlides">
    <img src="${pageContext.request.contextPath}/resources/img/trip.jpg" style="height:100%; width:100%;" class="w3-hover-grayscale">
    <div class="w3-display-bottomright w3-large w3-container w3-padding-16 w3-text-white" style="opacity:0.9">
      힐링 여행!
    </div>
  </div>
  
<%--   <div class="w3-display-container mySlides">
    <img src="${pageContext.request.contextPath}/resources/img/trip.jpg" style="height:100%; width:100%;">
    <div class="w3-display-bottomright w3-small w3-container w3-padding-16 w3-dark-grey" style="opacity:0.9">
      힐링여행을 떠나봅시다~!!
    </div>
  </div> --%>
</div>
<script>
  var slideIndex = 1;
  showDivs(slideIndex);

  function plusDivs(n) {
    showDivs(slideIndex += n);
  }

  function currentDiv(n) {
    showDivs(slideIndex = n);
  }

  function showDivs(n) {
    var i;
    var x = document.getElementsByClassName("mySlides");
    var dots = document.getElementsByClassName("demo");
    if (n > x.length) {
      slideIndex = 1
    }
    if (n < 1) {
      slideIndex = x.length
    }
    for (i = 0; i < x.length; i++) {
      x[i].style.display = "none";
    }
    for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" w3-white", "");
    }
    x[slideIndex - 1].style.display = "block";
    dots[slideIndex - 1].className += " w3-white";
  }
</script>