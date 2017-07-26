<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<title>파일 목록</title>
<head>
<%@include file="/resources/common/head.jsp"%>
<script type="text/javascript">
window.resizeTo(600,500)
</script>
</head>
<body>
	<div class="w3-container w3-indigo">
		<span class="w3-large w3-padding">파일리스트</span>
	</div>
	<table class="w3-table w3-bordered">
		<colgroup>
			<col width="50%">
			<col width="20%">
			<col width="20%">
			<col width="10%">
		</colgroup>
		<tr>
			<th>파일명</th>
			<th>업로더</th>
			<th>파일크기</th>
			<th>다운</th>
		</tr>
		<c:forEach var="file" items="${files}" varStatus="sts">
			<tr>
				<td>${file.realFileName}</td>
				<td>${file.uploader}</td>
				<td>${file.fileSize}</td>
				<td class="w3-center"><a
					href="/Socket.web/file/download.do?roomNumber=${roomNumber}&loginId=${loginId}&fileName=${file.fileName}"
					class="fa fa-download" aria-hidden="true"></a></td>
			</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>