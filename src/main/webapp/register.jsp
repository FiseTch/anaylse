<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/view/common/header.jsp"%>
<html>  
<head>  
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css">
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${ctx}/js/script.js"></script>
<script type="text/javascript" src="${ctx}/js/superfish.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.responsivemenu.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="${ctx}/js/FF-cash.js"></script>
<title>注册 &copyFise</title>  
</head>
<script type="text/javascript">
function checkId(){   
    var id = document.getElementById("id").value;
    if(id != null  && id != "" && id != undefined){	
		/* window.location.href = "${ctx}/teacher/checkUser.do&userId="+id;
		if(!${isReigster}){//为真，表示没被注册
		    $("#emptyRegister").show();
		}else{
		    $("#emptyRegister").show();
		} */
		$("#emptyId").hide();		
	}else{		
		$("#emptyId").show();
	} 
}
function checkUsername(){   
    var username = document.getElementById("username").value;
    if(username != null  && username != "" && username != undefined){		
		$("#emptyUsername").hide();		
	}else{		
		$("#emptyUsername").show();
	} 
}
function checkPassword(){   
    var password = document.getElementById("password").value;
    if(password != null  && password != "" && password != undefined){		
		$("#emptyPassword").hide();		
	}else{		
		$("#emptyPassword").show();
	} 
}
function checkpasRe(){//当第二个密码框失去焦点时，触发checkpasRe时事件
	var pas1=document.getElementById("password").value;
	var pas2=document.getElementById("confirmPassword").value;//获取两个密码框的值
	if(pas1 != null && pas1 != "" && pas1 != undefined){
		if(pas2 != null && pas2 != "" && pas2 != undefined){
		    $("#emptyConfirmPassword").hide();
		    if(pas1 != pas2){
		    	$("#tip").show();//当两个密码不相等时则显示错误信息
		    }else{
				$("#tip").hide();
			}
		}else{
		    $("#tip").hide();
		    $("#emptyConfirmPassword").show();
		}		
	}
}
function form_submit(){   
	var id = document.getElementById("id").value;
	var username = document.getElementById("username").value;
	var pas1 = document.getElementById("password").value;
	var pas2 = document.getElementById("confirmPassword").value;
	if(id == null || id == "" || id == undefined){
	    document.getElementById("id").focus();
	    return false;
	}
	if(username == null || username == "" || username == undefined){
	    document.getElementById("username").focus();
	    return false;
	}
	if(pas1 == null || pas1 == "" || pas1 == undefined){
	    document.getElementById("password").focus();
	    return false;
	}
	if(pas2 != null && pas2 != "" && pas2 != undefined){
	    $("#emptyConfirmPassword").hide();
	    if(pas1 == pas2){
			if(!document.getElementById("agree").checked){
			    alert("请同意我方公司协议");
				document.getElementById("agree").focus();
				return false;
			}else{
				alert("注册成功，登录中...");
			    return true;
			}
		}else{
			document.getElementById("confirmPassword").value = "";
			document.getElementById("confirmPassword").focus();
			return false; 
		}
	}else{
	    $("#emptyConfirmPassword").show();
		document.getElementById("confirmPassword").focus(); 					
	    return false;
	}		    
}
</script>   
<body>
<div class="bg">  
<header>
	<div class="container_24">
		<div class="grid_24">
			<h1 class="fleft"><a href="http://www.jxufe.edu.cn/" title="江西财经大学" target="_blank">学校</a></h1>			
			<ul class="sf-menu">
				<li class="current"><a href="${ctx}/index.html">主页</a></li>
				<li><a href="${ctx}/view/user/index.jsp">登录</a></li>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
</header>
<section>
	<div class="container_24">
		<div class="wrapper">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">欢迎注册</h3>
				</div>
				<div class="panel-body">
					<form id="signupForm" method="post" class="form-horizontal" action="${ctx}/teacher/register.do" onsubmit = "return form_submit()">
						<div class="form-group">
							<label class="col-sm-4 control-label" for="firstname">用户工号</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" onblur = "checkId()" autofocus = "autofocus" id="id" name="id" placeholder="工号" />
								<!-- <span class="emptyId" id = "emptyId"style="color: red;display:none">用户工号不允许为空</span> -->
								<span class="emptyId" id = "emptyId"style="color: red;">用户工号不允许为空</span>
								<span class="emptyRegister" id = "emptyRegister"style="color: red;display:none">用户已被注册</span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-4 control-label" for="lastname">用户名</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" onblur = "checkUsername()"id="username" name="username" placeholder="姓名" />
								<span class="emptyUsername" id = "emptyUsername"style="color: red;">用户名不允许为空</span>								
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-4 control-label" for="password">密码</label>
							<div class="col-sm-5">
								<input type="password" class="form-control" onblur = "checkPassword()" id="password" name="password" placeholder="请输入密码" />
								<span class="emptyPassword" id = "emptyPassword"style="color: red;">密码不允许为空</span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-4 control-label" for="confirmPassword">确认密码</label>
							<div class="col-sm-5">
								<input type="password" class="form-control" onblur = "checkpasRe()"id="confirmPassword" name="confirmPassword" placeholder="确认密码" />
								<span class="emptyConfirmPassword" id = "emptyConfirmPassword"style="color: red;display:none">请输入确认密码</span>
								<span class="tip" id = "tip" style="color: red;display:none">两次输入的密码不一致</span><br>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-5 col-sm-offset-4">
								<div class="checkbox">
									<label>
										<input type="checkbox" id="agree" name="agree" value="agree" />同意公司协议
									</label>
								</div>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-9 col-sm-offset-4">
								<button type="submit" class="btn btn-primary" name="signup" id = "signup" value="Sign up">注册</button>
							</div>
						</div>
					</form>
				</div>
			</div>			
		</div>
	</div>
	<div class="container_24"><!-- 用来撑开页面底部的空隙 -->
		<div class="wrapper">
			<div class="grid_7 suffix_1 padtop33"></div>
			<div class="grid_7 suffix_1 padtop33"></div>
			<div class="grid_7 suffix_1 padtop33"></div>
		</div>
	</div>
</section>

	
	
<footer>
	<div class="container_24">
		<div class="wrapper">
			<div class="grid_24">
				<a href="http://www.jxufe.edu.cn/" class="link" title="江西财经大学" target="_blank">
					<img src="${ctx}/images/logo-footer.png" alt="" width="80px">
				</a> 
					&copy; 2018 | 
				<a href="index-6.html">
					试卷分析系统
				</a>
				<a href="https://github.com/FiseTch" target="_blank" title="作者">
				 作者主页
				</a>
			 </div>
		</div>
	</div>
</footer> 
</div> 
</body>
</html>