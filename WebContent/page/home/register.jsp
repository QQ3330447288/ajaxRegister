<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet" href="<%=path%>/static/home/style/home.css">
</head>
<body>
	<div class="container" style="margin-top: 60px">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span class="glyphicon glyphicon-plus"></span>&nbsp;注册
						</h3>
					</div>
					<div class="panel-body">
						<fieldset>
							<div class="form-group">
								<label for="input_name"><span
									class="glyphicon glyphicon-user"></span>&nbsp;账号</label> <input
									type="text" class="form-control input" placeholder="" autofocus
									name="userName" id="userName" onblur="checkUserName(this)">
								<span id="tip" style="color: red; font-weight: bold"></span>
							</div>
							<div class="form-group">
								<label for="input_email"><span
									class="glyphicon glyphicon-lock"></span>&nbsp;密码 </label> <input
									type="password" class="form-control" placeholder="" name="pwd"
									id="pwd">
							</div>
							<div class="form-group">
								<label for="input_email"><span
									class="glyphicon glyphicon-lock"></span>&nbsp;确认密码 </label> <input
									type="password" class="form-control" placeholder=""
									name="pwd_sure" id="pwd_sure">
							</div>
							<div class="form-group">
								<label for="input_phone"><span
									class="glyphicon glyphicon-phone"></span>&nbsp;手机号</label> <input
									class="form-control" placeholder="" name="phone" type="text"
									id="phone">
							</div>
							<div class="form-group">
								<label for="input_password"><span
									class="glyphicon glyphicon-lock"></span>&nbsp;验证码</label>
								<div class="input-group">
									<input type="password" class="form-control" placeholder="">
									<span class="input-group-addon"><input type="button"
										value="发送" onclick="sendCode(this)"></span>
								</div>
							</div>
							<div class="col-md-12">
								<input type="submit" value="注册"
									class="btn btn-log btn-primary btn-block" onclick="register();">
							</div>
						</fieldset>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous">
	
</script>
<script>
	//检查用户名是否已经存在
	function checkUserName(input) {
		//alert(input.value);
		var name = input.value;
		$.post("<%=path%>/checkUserName.action", {
			userName : name
		}, function(data) {
			//alert(data);
			if (data == 1) {
				$('#tip').html("当前用户已经存在！");
			} else {
				$('#tip').html("");
			}
		});
	}
	//随机生成验证码
	function createCode(sum) {
		var code = "";
		for (var i = 0; i <sum; i++) {
			var num = parseInt(Math.random() * 10) % 10;
			code += num;
		}
		return code;
	}
	
	//发送手机验证码
	function sendCode(input) {
		input.setAttribute("disabled", "disabled");
		var count = 60;
		var time = setInterval(function(){
			input.value=count+"s";
			count--;
			if(count<0){
				input.removeAttribute("disabled");
				clearInterval(time);
				input.value="发送";
			}
		},1000);
		
		//生成6位验证码
		var phone = $("#phone").val();
		//alert(phone);
		var code = createCode(6);
		alert(code);
		$.post("<%=path%>/sendCode.action",{
			phone: phone,
			code: code
		},function(data){
			
		});
	}
	
	//键盘回车提交表单
	$(document).ready(function() {
		$(document).keydown(function(event) {
			if (event.keycode == 13) {
				register();
			}
		})
	})
	//注册
	function register() {
		var userName = $("#userName").val();
		var pwd = $("#pwd").val();
		var pwd_sure = $("#pwd_sure").val();
		var phone = $("#phone").val();
		if (pwd.length >= 6 && pwd == pwd_sure) {
			//alert("校验成功");
		} else {
			alert("密码少于6位数或者两次输入密码不一致！");
		}
		if(phone<11){
			alert("手机号格式不正确！");
		}
	}
</script>
</html>