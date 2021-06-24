<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/maincommon.jsp" />
		<form class="login-form" method="post" action="${pageContext.request.contextPath }/user/auth">
      		<label>아이디</label> <input type="text" name="id">
      		<label>패스워드</label> <input type="text" name="password">
      		<c:if test='${result == "fail"}'>
				<p>
					로그인이 실패 했습니다.
				</p>
			</c:if>
      		<input type="submit" value="로그인">
		</form>
	</div>
</body>
</html>