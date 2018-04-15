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
<title>试卷分析系统&copyFise</title>  
</head>   
<body> 
<header>
	<div class="container_24">
		<div class="grid_24">
			<h1 class="fleft"><a href="http://www.jxufe.edu.cn/" title="江西财经大学" target="_blank">学校</a></h1>
			<ul class="sf-menu">
				<li class="current"><a href="${ctx}/index.html">主页</a></li>
				<!-- <li><a href="#">我的<span class="arrow"></span></a><ul>
						<li><a href="view/user/index.jsp">登录&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
						<li><a href="#">注册&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
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
				<li><a href="index-4.html">Projects</a></li> -->
				<li><a href="${ctx}/view/user/index.jsp">登录</a></li>
								    
			</ul>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
</header>
<div class="main-slider">
<div class="bg-slider">
	<div class="slider">
		<div class="flexslider">
			<ul class="slides">
				<li>
					<img src="${ctx}/images/slide1.jpg" alt="" width = "946px" height = "538px">
						<div class=" flex-caption">
							<span>信息管理学院</span>
						</div>
				</li>
				<li>
					<img src="${ctx}/images/slide2.jpg" alt="" width = "946px" height = "538px">
						<div class=" flex-caption">
							<span>麦园风光</span>
						</div>
				</li>
				<li>
					<img src="${ctx}/images/slide3.jpg" alt="" width = "946px" height = "538px">
						<div class=" flex-caption">
							<span>江西财经大学</span>
					</div>
				</li>
			</ul>
		</div>
	</div>
</div>
</div>	
	
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