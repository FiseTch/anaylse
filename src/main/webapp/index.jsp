
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
	<%@include file="/view/common/header.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="applicable-device" content="pc,mobile">
	<title>登录</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">	
	<link rel="stylesheet" type="text/css" href="css/index.css"> 
	<link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
	<script src="js/jquery-1.7.1.min.js"></script>
	<script src="js/script.js"></script>
	<script src="js/superfish.js"></script>
	<script src="js/jquery.responsivemenu.js"></script>
	<script src="js/jquery.flexslider-min.js"></script>
	<script src="js/FF-cash.js"></script>

<!--[if lt IE 8]>
   <div style=' clear: both; text-align:center; position: relative;'>
     <a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode">
       <img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." />
    </a>
  </div>
<![endif]-->
<!--[if lt IE 9]>
	<script src="js/html5.js"></script>
	<link rel="stylesheet" href="css/ie.css"> 
<![endif]-->
</head>
<body>
<!-- header -->
<header>
	<div class="container_24">
		<div class="grid_24">
			<h1 class="fleft"><a href="http://www.jxufe.edu.cn/">学校</a></h1>
			<ul class="sf-menu">
				<li class="current"><a href="index.html">主页</a></li>
				<li><a href="index-1.html">Company<span class="arrow"></span></a><ul>
						<li><a href="#">History</a></li>
						<li><a href="#">Work Team</a></li>
						<li><a href="#">Profile</a><ul>
								<li><a href="#">Mission</a></li>
								<li><a href="#">Capabilities</a></li>
								<li><a href="#">Support</a></li>
								<li><a href="#">Partnership</a></li>
							</ul>
						</li>
						<li><a href="#">Testimonials</a></li>
					</ul>
				</li>
				<li><a href="index-2.html">Services</a></li>
				<li><a href="index-3.html">Solutions</a></li>
				<li><a href="index-4.html">Projects</a></li>
				<li>
				<a class="a globalLoginBtn">登录</a>
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
				                        <form id="login_form" action = "${ctx}/user/login.do"method="post" autocomplete="off">                         
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
					                        <a href="${ctx}/view/user/forgetPassword.jsp" class="fr">忘记密码？</a>还没有账号？
					                        <a href="${ctx}/view/user/register.jsp" target="_blank" >立即注册</a>
					                    </p>
				                        <div class="login-box marginB10">                        	
				                            <button id="login_btn" type="button" class="btn btn-micv5 btn-block globalLogin">登录</button>
				                            <div id="login-form-tips" class="tips-error bg-danger" style="display: none;">错误提示</div>
				                        </div>
				                       <div class="threeLogin">
					                       <span>其他方式登录</span>
					                       <a class="nqq" href="javascript:;"></a>
					                       <a class="nwx" href="javascript:;"></a><!--<a class="nwb"></a>-->
				                       </div>                     
				                    </section>
				                </div>
				            </div>
				        </div>
				    </div>
					
				</li>			    
			</ul>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
</header>
<!--  图片轮播区-->
<div class="main-slider">
<div class="bg-slider">
	<div class="slider">
		<div class="flexslider">
			<ul class="slides">
				<li>
					<img src="images/slide1.jpg" alt="" width = "946px" height = "538px">
						<div class=" flex-caption">
							<span>信息管理学院</span>
						</div>
				</li>
				<li>
					<img src="images/slide2.jpg" alt="" width = "946px" height = "538px">
						<div class=" flex-caption">
							<span>麦园风光</span>
						</div>
				</li>
				<li>
					<img src="images/slide3.jpg" alt="" width = "946px" height = "538px">
						<div class=" flex-caption">
							<span>江西财经大学</span>
					</div>
				</li>
			</ul>
		</div>
	</div>
</div>
</div>
<!-- footer 页面底部设置 -->
<footer>
	<div class="container_24">
		<div class="wrapper">
			<div class="grid_24">
				<a href="http://www.jxufe.edu.cn/" class="link">
					<img src="images/logo-footer.png" alt="" width="80px">
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
	<script type="text/javascript" src="js/jquery2.2.2.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/login.js"></script> 
</body>
</html>
