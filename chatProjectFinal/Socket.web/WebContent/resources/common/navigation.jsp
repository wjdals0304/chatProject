<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.w3-navbar li a {
    padding: 12px;
    float: left;
}
</style>
<div class="w3-top">
  <ul class="w3-navbar w3-naver " id="myNavbar" style="height: 48px;">
    <li>
      <a href="${pageContext.request.contextPath}/room/roomList.do?symbol=true" class="w3-wide">Simple Talk</a>
    </li>
    <!-- Right-sided navbar links -->
    <li class="w3-right w3-hide-small">
    <c:choose>
      <c:when test="${!empty loginUser}">
	      <a href="${pageContext.request.contextPath}/views/mypage.jsp"><i class="fa fa-user"></i>&nbsp;나의정보</a>
	      <a href="${pageContext.request.contextPath}/user/logout.do"><i class="fa fa-sign-out"></i>&nbsp;로그아웃</a>
      </c:when>
      <c:otherwise>
		 <a href="${pageContext.request.contextPath}/views/login.jsp"><i class="fa fa-sign-in"></i>&nbsp;로그인</a>
      </c:otherwise>
    </c:choose>
    <a href="${pageContext.request.contextPath}/room/search.do"><i class="fa fa-search"></i></a>
    </li>
    <!-- Hide right-floated links on small screens and replace them with a menu icon -->
    <li>
      <a href="javascript:void(0)" class="w3-right w3-hide-large w3-hide-medium" onclick="w3_open()">
        <i class="fa fa-bars w3-padding-right w3-padding-left"></i>
      </a>
    </li>
  </ul>
</div>