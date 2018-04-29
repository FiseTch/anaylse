<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="/view/common/header.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
	<meta name="applicable-device" content="pc,mobile">
	<title>登录&copyFise</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css">	
	<link rel="stylesheet" type="text/css" href="../../css/index.css"> 
</head>
<script type="text/javascript">
function infoMsg(){
    alert("当前功能尚在开发中!敬请期待")
}
</script>
<body>
	<a class="a globalLoginBtn">登录</a>
	<img alt="" src="../../images/slide3.jpg" style = "width:1280px">
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" style="display: none;"><div style="display:table; width:100%; height:100%;"><div style="vertical-align:middle; display:table-cell;"><div class="modal-dialog modal-sm" style="width:540px;">
        <div class="modal-content" style="border: none;">
            <div class="col-left"></div>
            <div class="col-right">
                <div class="modal-header">
                    <button type="button" id="login_close" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="loginModalLabel" style="font-size: 18px;">登录</h4>
                </div>
                <div class="modal-body">
                    <section class="box-login v5-input-txt" id="box-login">
                        <form id="login_form" action = "${ctx}/teacher/login.do"method="post" autocomplete="off">                         
                            <ul>
                                <li class="form-group">
                                	<input class="form-control" id="id_account_l" maxlength="50" name="username" placeholder="请输入邮箱账号/手机号" type="text">
                                </li>
                                <li class="form-group">
                                	<input class="form-control" id="id_password_l" name="pwd" placeholder="请输入密码" type="password">
                                </li>                                
                            </ul>
                        </form>
                        <p class="good-tips marginB10">
	                        <a href="${ctx}/forgetPassword.jsp" class="fr">忘记密码？</a>还没有账号？
	                        <a href="${ctx}/teacher/checkUser.do" >立即注册</a>
	                    </p>
                        <div class="login-box marginB10">                        	
                            <button id="login_btn" type="button" class="btn btn-micv5 btn-block globalLogin">登录</button>
                            <div id="login-form-tips" class="tips-error bg-danger" style="display: none;">错误提示</div>
                        </div>
                       <div class="threeLogin">
	                       <span>其他方式登录</span>
	                       <a class="nqq" href="#" onclick="infoMsg()"></a>
	                       <a class="nwx" href="#" onclick="infoMsg()"></a><!--<a class="nwb"></a>-->
                       </div>                     
                    </section>
                </div>
            </div>
        </div>
    </div>
	<script type="text/javascript" src="../../js/jquery2.2.2.min.js"></script>
	<script type="text/javascript" src="../../js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../../js/common.js"></script>
	<script type="text/javascript" src="../../js/login.js"></script>
</body>
</html>