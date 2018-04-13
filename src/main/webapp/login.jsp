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
<title>用户登录成功页面</title>  
</head>   
<body>  
<script>
function newDoc(){
    window.location.assign("${ctx}/view/user/upExcel.jsp")
}
</script>
<header>
	<div class="container_24">
		<div class="grid_24">
			<h1 class="fleft"><a href="http://www.jxufe.edu.cn/">学校</a></h1>
			<ul class="sf-menu">
				 <li><a href="#">${user.username}<span class="arrow"></span></a><ul>
						<li><a href="view/user/index.jsp">我的&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
						<li><a href="#">修改密码&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
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
				<div class="grid_8 padtop3">
					<form id = "downExcel"action="${ctx}/excelTempleDown/downloadExcel.do" method = "post">
						请输入题目数量（最多不超过25道题）<input id = "num"type = "text" name = "num">
						<input id = "down" value = "下载" type = "submit">
					</form>
					<input type="button" value="上传文档" onclick="newDoc()">
				</div>				
			</div>
		</div>
	</div>
	<div class="container_24">
		<div class="wrapper">
			<div class="grid_16 padtop2">
				<h4 class="padbot2">Solutions, that you need!</h4>
				<p class="text1 it">Praesent vestibulum molestie lacus. Aenean nmmy hendrerit mauris. Phasellus porta. Fusce suscipit varius mi. Cum sociis natoque penatibus et magnis dis 
					parturient montes, nascetur ridiculus.</p>
				<p class="padbot2">Praesent vestibulum molestie lacus. Aenean nonummy hendrerit mauris. Phasellus porta. Fusce suscipit varius mi. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nulla dui. Fusce feugiat malesuada odio. Morbi nunc odio, gravida at, cursus nec, luctus a, lorem. Maecenas tristique orci ac sem. Duis ultricies pharetra magna. Donec accumsan malesuada orci. Donec sit amet eros. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Mauris fermentum dictum magna. Sed laoreet aliquam leo. Ut tellus dolor, dapibus eget, elementum vel, cursus eleifend, elit.</p>
				<a href="#" class="button1">Read More</a>
			</div>
			<div class="grid_8 padtop2">
				<h4 class="padbot2">Contact Info</h4>
				<p class="text2">The Company Name Inc.<br>
				8901 Marmora Road,Glasgow,<br>
				D04 89GR.</p>
				<p class="text1">Freephone: +1 800 559 6580<br>
				FAX: +1 504 889 9898<br>
				E-mail: <a href="#">mail@demolink.org</a></p>
				<h4 class="pad">Get in Touch</h4>
				<ul class="icons">
					<li><a href="#"><img src="images/icon1.jpg" alt=""></a></li>
					<li><a href="#"><img src="images/icon2.jpg" alt=""></a></li>
					<li><a href="#"><img src="images/icon3.jpg" alt=""></a></li>
					<li><a href="#"><img src="images/icon4.jpg" alt=""></a></li>
				</ul>
			</div>
		</div>
	</div>
</section>

	
	
<footer>
	<div class="container_24">
		<div class="wrapper">
			<div class="grid_24">
				<a href="http://www.jxufe.edu.cn/" class="link">
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