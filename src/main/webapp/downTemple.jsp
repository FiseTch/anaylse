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
<title>模板下载&copyFise</title>  
</head> 
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
		return $("#changePassword").submit();
    }else{
		return;
    }
}
function checkNum(){
    var num = document.getElementById("num").value;
    if(null != num && num != "" && num != undefined){
		$("#emptyNum").hide();
		var num1 = num.replace(/[^\d]/g,'');
		if(num1 == num){
		    $("#replaceNum").hide();
			if(num > 23){
			    $("#emptyMaxNum").show();
			}else{
			    $("#emptyMaxNum").hide();
			}
		}else{
		    $("#replaceNum").show();
		    $("#emptyMaxNum").hide();
		}
    }else{
		$("#emptyNum").show();
		$("#replaceNum").hide();
		$("#emptyMaxNum").hide();
    }
    
}
function checkForm(){
    var num = document.getElementById("num").value;
    if(null != num && num != "" && num != undefined){
		var num1 = num.replace(/[^\d]/g,'');
		if( num1 == num){		    
			if(num > 23){
			    document.getElementById("num").value = "";
			 	document.getElementById("num").focus();
			 	return false;
			}else{
			    return true;
			}		
		}else{
		    document.getElementById("num").value = "";
		 	document.getElementById("num").focus();
		 	return false;
		}
    }else{
		document.getElementById("num").value = "";
	 	document.getElementById("num").focus();
	 	return false;
    }
    
}
</script>  
<body>  
<header>
	<div class="container_24">
		<div class="grid_24">
			<h1 class="fleft"><a href="http://www.jxufe.edu.cn/">学校</a></h1>
			<ul class="sf-menu">
				<li class="current"><a href="${ctx}/login.jsp">主页</a></li>
				<li><a href="${ctx}/upFile.jsp">上传文档</a></li>
				<li><a href="#">${sessionScope.teacher.name}<span class="arrow"></span></a><ul>
					<li><a href="${ctx}/myInformation.jsp">我的&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
					<li>
						<a href="${ctx}/changePassword.jsp" onclick="updatePassword()">
						修改密码&nbsp;&nbsp;&nbsp;&nbsp;</a>
					</li>
					<li><a href="#">我的试卷</a><ul>
								<li><a href="${ctx}/paper/getPaperRecord.do">上传记录</a></li>
								<li><a href="${ctx}/reviewResult/getReviewResult.do">分析结果</a></li>
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
				<form id = "downExcel"action="${ctx}/excelTempleDown/downloadExcel.do"onsubmit = "return checkForm();" method = "post">
					<br>请输入题目数量（最多不超过23道题）<br><br>
					<input id = "num" type = "text" onblur = "checkNum()"placeholder = "请输入数字" name = "num" autofocus="autofocus"style ="width:190px;height:5px">
					<input id = "down" value = "下载" type = "submit"><br>
					<span class="emptyNum" id = "emptyNum"style="color: red;display:none">*请输入题目数量</span>
					<span class="replaceNum" id = "replaceNum"style="color: red;display:none">*当前输入含有非法字符，请输入数字</span>
					<span class="emptyMaxNum" id = "emptyMaxNum"style="color: red;display:none">*题目数量不允许超过23</span>
				</form>
								
				<br><h4>下载模板模板样式</h4>
				<img alt="" src="${ctx}/images/temple.png" width = "900px"><br>
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