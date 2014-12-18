<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="base.jsp" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/style/login_style.css"/>
<script type="text/javascript">

$(function(){
	
});
</script>
</head>

<body>
	<form action="${contextPath}/user/login" method="post" id="userForm">
		<div class="wap">
			<font color="#ffffff"></font>
			<div class="top"></div>
			<div class="bot">
				<div class="con">
					<label>用户名</label><input name="userName" type="text" /><br> 
					<label class="secr">密码</label><input name="password" type="password" /><br>
					<input type="submit" id="submit" class="btn_land" value="登录" />
				</div>
			</div>
		</div>
	</form>
</body>
</html>
