<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
 <%@include file ="head.jsp" %>
 <title>페이지를 찾을 수 없습니다.</title>
</head>
<body>
<div class="container">

    <!-- header -->
     
    <div class="error-header" style="margin-top:100px;">
    	<img src="${pageContext.request.contextPath}/resources/img/404error.png" style="margin-left:70px">
        <h4 class="fa error-heading" style="margin-left:70px"><br>요청하신 페이지를 찾을 수 없습니다.<br><br>
        다시 한번 확인하시고 시도해주세요.</h4>
        
    </div>
        <div class="row error-btn">
            <button onclick="history.back();" class="w3-btn w3-section w3-indigo w3-ripple w3-padding loginBtn">이전으로 이동</button>
        </div>

    <!-- footer -->
    <div class="error-footer">
        <p>© Simple talk 2016.<p>
    </div>
</div>
</body>
</html>