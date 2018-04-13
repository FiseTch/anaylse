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
<title></title>  
</head>   
<body>  
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
					
				</div>				
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