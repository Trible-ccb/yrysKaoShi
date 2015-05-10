<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>主页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
<!-- 	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"> -->
<!-- 	<meta http-equiv="description" content="This is my page"> -->
	<link rel="stylesheet" type="text/css" href="./css/globe.css">
	<link rel="stylesheet" type="text/css" href="./css/main.css">
	<link rel="stylesheet" type="text/css" href="./css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="./css/jquery-ui.structure.min.css">
	<link rel="stylesheet" type="text/css" href="./css/jquery-ui.theme.min.css">
  	<script src="./js/jquery-1.11.3.min.js"></script>
  	<script src="./js/jquery-ui.min.js"></script>
  	
  	<script>
 	  $(function() {
		$( "#accordion" ).accordion();
		$( document ).tooltip();
		$(input).autocomplete();
		$("input[type=submit]").button();
		$( ".accordion_section" ).position({
			of: $( "#accordion" ),
			my: "center center",
			at: "center center",
			collision: "flip flip"
		});
 	  });
   </script>
  </head>
  
<body>
	<div class="content">
		<div class="header">
			<img src="./img/logo.png" class="logo_img"/>
			<label class="header_name">个性化在线考试系统</label>
		</div>
		<div class="reg_login_box">
			<div id="accordion">
			  <h3 class="accordion_section">用户登录</h3>
			  <div id="login_tab">
			    <form action="user/login.do" method="post">
			    	<label for="#login_username">用户名:</label>
			  		<input id="login_username" name="username" title="用户名最长20字符。" />
			  		<br/>
			  		<label for="#login_password">密码:</label>
					<input id="login_password" name="password" type="password"/>
					<br/>
					<input type="submit" value="登录" id="submit_login"/>
			  	</form>
			  </div>
			  <h3 class="accordion_section">注册用户</h3>
			  <div id="reg_tab">
  			    <form action="user/add.do" method="post">
  			    	<label for="#reg_username">用户名:</label>
			  		<input id="reg_username" name="username" />
			  		<br/>
			  		<label for="#reg_password">密码:</label>
					<input id="reg_password" name="password" type="password"/>
					<br/>
					<label for="#con_password">确认密码:</label>
					<input id="con_password" name="password" type="password"/>
					<br/>
					<input type="submit" value="注册" id="submit_reg"/>
			  	</form>
			  </div>
			</div>
		</div>
	</div>
</body>
</html>
