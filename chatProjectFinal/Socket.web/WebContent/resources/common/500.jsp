<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
 <%@include file ="head.jsp" %>
 <title>페이지 오류</title>
</head>
<body>
<div class="container">

    <!-- header -->
     
    <div class="error-header">
    	<img src="${pageContext.request.contextPath}/resources/img/500error.png">
        <p class="error-heading" style="margin-left:70px"><br>일시적 오류가 발생했습니다.<br>
       서비스 이용에 불편을 끼쳐드려 죄송합니다.</p>
        
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