<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
function logout() {
	parent.parent.window.location.href = "${contextPath}/user/logout";
}
</script>
</head>
  <body> 
  <!--<![endif]-->
	<div class="navbar">
        <div class="navbar-inner">
        	
            <ul class="nav pull-right">
                <li><a href="#" class="hidden-phone visible-tablet visible-desktop" type="button">${sessionScope.loginUser.userName} 欢迎您登录 </a></li>
                <li><a href="javascript:logout();" class="hidden-phone visible-tablet visible-desktop" type="button">退出</a></li>
            </ul>
            <ul class="nav pull-right">
            </ul>
            <a class="brand" style="color: white" href="#"><span class="second">SNS管理后台</span></a>
        </div>
    </div>
  </body>
</html>
