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
	<link rel="stylesheet" type="text/css" href="./css/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="./css/jquery-ui.structure.css">
	<link rel="stylesheet" type="text/css" href="./css/jquery-ui.theme.css">
  	<script src="./js/jquery-1.11.3.js"></script>
  	<script src="./js/jquery-ui.js"></script>
  	
  	<script>
 	  $(function() {
 		function showInputTip(tip){
 			$( "#input_tip" )
 			.text( tip ).show()
 			.fadeOut(5000,function(){
 				$(this).html("&nbsp").show();
 			})
 		}
 		$("input[title]").tooltip();
		var tips = $("#dialog_content").text();
		if (tips != ''){
			$("#dialog_mask").addClass("dialog_mask");
			$("#dialog").dialog({
				close:function(){
					$("#dialog_mask").removeClass("dialog_mask");
				}
			});
		} else {
			$("#dialog_mask").removeClass("dialog_mask");
		} 
		$( "#accordion" ).accordion();
		$("input[type=submit]").button();
		$( "fieldset select" ).selectmenu();
		
		var input_tip_text = '';
		$("#login_form").submit(function(event){
			var name = $("#login_username").val();
			var psw = $("#login_password").val();
			if ( name != '' && psw != '' ){
				return;
			}
			if ( name == '' ){
				input_tip_text = '用户名不能为空!';
			} else if ( psw == '' ){
				input_tip_text = '密码不能为空!';
			}
			showInputTip(input_tip_text);
			event.preventDefault();
		});
		$("#reg_form").submit(function(event){
			var name = $("#reg_username").val();
			var psw = $("#reg_password").val();
			var cpsw = $("#con_password").val();
			if ( name != '' && psw != '' && cpsw != '' && psw == cpsw){
				return;
			}
			if ( name == '' ){
				input_tip_text = '用户名不能为空!';
			} else if ( psw == '' ){
				input_tip_text = '密码不能为空!';
			} else if ( psw != cpsw ){
				input_tip_text = '两次密码不一致!';
			}
			showInputTip(input_tip_text);
			event.preventDefault();
		});
		
		$(input).autocomplete();
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
			<p id="input_tip">&nbsp</p>
			<div id="accordion">
			  <h3 class="accordion_section">用户登录</h3>
			  <div id="login_tab">
			    <form action="user/login.do" method="post" id="login_form">
			    	<label for="#login_username">用户名：</label>
			  		<input id="login_username" name="username"  />
			  		<br/>
			  		<label for="#login_password">密　码：</label>
					<input id="login_password" name="password" type="password" />
					<br/>
					<fieldset>
					    <label for="usertype">身　份：</label>
					    <select name="usertype" id="login_usertype">
					      <option>学生</option>
					      <option>老师</option>
					      <option>管理员</option>
					    </select>
					</fieldset>
					<input type="submit" value="登录" id="submit_login"/>
			  	</form>
			  </div>
			  <h3 class="accordion_section">注册用户</h3>
			  <div id="reg_tab">
  			    <form action="user/register.do" method="post" id="reg_form">
  			    	<label for="#reg_username">用户名：</label>
			  		<input id="reg_username" name="username"  />
			  		<br/>
			  		<label for="#reg_password">密　码：</label>
					<input id="reg_password" name="password" type="password" title="密码长度6-20个字符"/>
					<br/>
					<label for="#con_password">确认密码:</label>
					<input id="con_password" name="con_password" type="password"/>
					<br/>
					<fieldset>
					    <label for="usertype">身　份：</label>
					    <select name="usertype" id="reg_usertype">
					      <option>学生</option>
					      <option>老师</option>
					    </select>
					</fieldset>
					<input type="submit" value="注册" id="submit_reg"/>
			  	</form>
			  </div>
			</div>
		</div>
	</div>
	<div class="dialog_mask" id="dialog_mask" >
		<div id="dialog" class="dialog" title="提示！">
			<p id="dialog_content">${result}</p>
		</div>
	</div>
</body>
</html>
