<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/view/common/header.jsp"%>
<html>  
<head>  
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css">
<script type="text/javascript" src="${ctx}/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${ctx}/js/script.js"></script>
<script type="text/javascript" src="${ctx}/js/superfish.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.responsivemenu.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="${ctx}/js/FF-cash.js"></script>
<title>修改密码</title>  
</head>   
<body> 
<script>
function logOut() {  
	if(confirm("确认注销吗？")){
	    return $("#logout").submit();
	    alert("已返回主页！！！")
	}else{
	    return;
	}
} 
function checkOldPwd(){
	$("#emptyPwd").hide();
	$("#errorMsg").hide();
    var pwd = document.getElementById("oldPassword").value;
    var pwd1 = ${sessionScope.teacher.password}; 
    if(pwd != null  && pwd != "" && pwd != undefined){
		/* document.getElementById("emptyPwd").style.visibility = "hidden";
		document.getElementById("error").style.visibility = "hidden"; */
		$("#emptyPwd").hide();
		if(pwd1 == pwd){
		    /* document.getElementById("error").style.visibility = "hidden"; */
		    $("#errorMsg").hide();
		}else{
		    /* document.getElementById("error").style.visibility = "visible"; */
		    $("#errorMsg").show(); 
		}		
    }else{
		/* document.getElementById("emptyPwd").style.visibility = "visible"; */
		$("#errorMsg").hide();
		$("#emptyPwd").show();
    }   
}
function checkpas(){
    var newPassword = document.getElementById("newPassword").value;
    if(newPassword != null  && newPassword != "" && newPassword != undefined){
		$("#emptyNewPassword").hide();
    }else{
		$("#emptyNewPassword").show();
    }
}
function checkpasRe(){//当第二个密码框失去焦点时，触发checkpasRe时事件
	$("#tip").hide();
	var pas1=document.getElementById("newPassword").value;
    var pas2=document.getElementById("newPasswordRepeat").value;//获取两个密码框的值
    if(pas1 != null && pas1 != "" && pas1 != undefined){
    	$("#tip").hide();
	    if(pas1 != pas2){
	    	$("#tip").show();//当两个密码不相等时则显示错误信息
	    }else{
			$("#tip").hide();
		}
  	}else{
		$("#tip").hide();
	}
}
function checkUpdate(){//点击提交按钮时，触发checkpas2事件，会进行弹框提醒以防无视错误信息提交
	var pwd = document.getElementById("oldPassword").value;
    var pwd1 = ${sessionScope.teacher.password}; 
	var pas3=document.getElementById("newPassword").value;
	var pas4=document.getElementById("newPasswordRepeat").value;
	if(pwd != null  && pwd != "" && pwd != undefined){
	    if(pwd != pwd1){//先对原密码进行校验，若原密码输入错误，则清空输入框且不提交
			alert("原密码输入错误,请重新输入");
			document.getElementById("oldPassword").value = "";
			document.getElementById("oldPassword").focus();
			return;
	    }else{//如果输入正确,对新密码进行比对，若两次输入密码相同则提交
			if(pas3 != null && pas3 != "" && pas3 != undefined && 
			pas4 != null && pas4 != "" && pas4 != undefined ){//若两者都不为空，则进行比对
				if(pas3 == pas4){
				    if(confirm("确认修改密码？")){
					    alert("密码修改成功！！！页面刷新中...");
					}else{
					    return;
					}
				}else{
					alert("两次输入的密码不一致！请重新输入");
					document.getElementById("newPasswordRepeat").value = "";
					document.getElementById("newPasswordRepeat").focus();
					return;
				}
			}
	    }	    
	}
}
</script>
<header>
	<div class="container_24">
		<div class="grid_24">
			<h1 class="fleft"><a href="http://www.jxufe.edu.cn/" title="江西财经大学" target="_blank">学校</a></h1>
			<ul class="sf-menu">
				 <li class="current"><a href="${ctx}/login.jsp">返回首页</a></li>	
				 <li><a href="#">${sessionScope.teacher.name}<span class="arrow"></span></a><ul>
						<li><a href="${ctx}/myInformation.jsp">我的&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
						<li><a href="#">Profile</a><ul>
								<li><a href="#">Mission</a></li>
								<li><a href="#">Capabilities</a></li>
								<li><a href="#">Support</a></li>
								<li><a href="#">Partnership</a></li>
							</ul>
						</li>
						<li>
							<form action="${ctx}/teacher/logout.do"method = "post" name= "logout" id = "logout">	
								<!-- <a href="javascript:void(0)" onclick="javascript:document.logout.submit()">
								退出&nbsp;&nbsp;&nbsp;&nbsp;</a>	 -->
								<a href="javascript:void(0)" onclick="logOut()">
								退出&nbsp;&nbsp;&nbsp;&nbsp;
								</a>
							</form> 
						</li>
					</ul>
				</li>								    
			</ul>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
</header>
<section>
	<div class="bg">
		<div class="container_24">
			<div class="wrapper">
				<div class="grid_24 padtop33">
					<h4>用户 ${sessionScope.teacher.id}</h4>
					<form id="contact-form" onsubmit = "checkUpdate()"action = "${ctx}/teacher/changePassword.do" method = "post">
						<fieldset>
							<div class="wrapper">
								<div class="grid_8 suffix_1 alpha">
									<label class="name">
										请输入原密码：
										<input type="password" name = "oldPassword" id = "oldPassword" onblur = "checkOldPwd()" autofocus="autofocus" required = "required">	
										<span class="emptyPwd" id = "emptyPwd"style="color: red;display:none">请输入密码</span>
										<span class="errorMsg" id = "errorMsg" style="color: red;display:none">密码输入错误</span>
										<span class="empty">*This field is required.</span>
										<span class="clear"></span>							
									</label>
									<label class="email">
										请输入新密码：
										<input type="password" name = "newPassword" id = "newPassword" onblur = "checkpas()" required = "required">
										<span class="emptyNewPassword" id = "emptyNewPassword"style="color: red;">请输入新密码</span>
										<span class="error">*This is not a valid name.</span>
										<span class="empty">*This field is required.</span>
										<span class="clear"></span>
									</label>
									<label class="phone">
										确认密码：
										<input type="password" name = "newPasswordRepeat" id = "newPasswordRepeat" onblur = "checkpasRe()"required = "required">
										<span class="tip" id = "tip" style="color: red;display:none">两次输入的密码不一致</span><br>
										<span class="empty">*This field is required.</span>
										<span class="clear"></span>
									</label>
									<label class="phone">
										<span>
											<input class = "button" type = "submit" name = "submit" value = "确认修改" >											
										</span>
									</label>
								</div>
								
							</div>
						</fieldset>
					</form>
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
</body>
</html>