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
<%-- <c:set var = "teacher" value = "${teacher}" scope = "session"></c:set>	 --%><!-- 获取页面的值放入到seesion中 -->>
<script type="text/javascript">   
function logOut() {  
	if(confirm("确认注销吗？")){
	    return $("#logout").submit();
	    alert("已返回主页！！！")
	}else{
	    return;
	}
} 

function updatePassword(){
    if(confirm("是否修改密码?")){
		return true;
    }else{
		return false;
    }
}
</script>
<header>
	<div class="container_24">
		<div class="grid_24">
			<h1 class="fleft"><a href="http://www.jxufe.edu.cn/" title="江西财经大学" target="_blank">学校</a></h1>
			<ul class="sf-menu">
				<li><a href="${ctx}/downTemple.jsp">模板下载</a></li>
				<li><a href="${ctx}/upFile.jsp">上传文档</a></li>
				<li class="current"><a href="#">${sessionScope.teacher.name}<span class="arrow"></span></a><ul>
					<li><a href="${ctx}/myInformation.jsp">我的&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
					<li>
						<a href="${ctx}/changePassword.jsp" onclick="return updatePassword();">
						修改密码&nbsp;&nbsp;&nbsp;&nbsp;</a>
					</li>
					<li><a href="#">我的试卷</a><ul>
								<li><a href="#">上传记录</a></li>
								<li><a href="#">分析结果</a></li>
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
	<div class="container_24">
		<div class="wrapper">
			<div class="grid_24 padtop2">
				<h4 class="padbot2">基于信息技术环境下的教学方法</h4>
				<p class="text1 it">为了检验学生的学习效果,必须对学生的学习成绩进行测量。通过教学测量，一方面反映学生掌握知识的程度，
				另一方面反映教师施教的水平，以便达到促进学生的学习和教师改进教学方法的目的</p>
				<p class="padbot2">
				检测和评定学生对所学的课程知识和技能是否达到教学大纲的要求，这对于实行学分制管理的高校尤为重要，同时又是学校培养合格人才的保障和重要管理手段。<br>
				促进学生智力发展，提高学习水平和专业技能。
				通过科学的成绩测量和评价激励学生的进取精神。
				推动教学工作的改革。
				提高“教书育人”的水平。
				</p>
				<a href="https://github.com/FiseTch" target="_blank" title="作者" class="button1">了解更多</a>
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
</body>
</html>