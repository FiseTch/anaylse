<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/view/common/header.jsp"%>
<html>  
<head>  
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css">
<link rel="stylesheet" href="${ctx}/css/table.css" type="text/css">
<script type="text/javascript" src="${ctx}/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${ctx}/js/script.js"></script>
<script type="text/javascript" src="${ctx}/js/superfish.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.responsivemenu.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="${ctx}/js/FF-cash.js"></script>
<title>试卷分析结果&copyFise</title>  
</head> 
<body>  
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
				<li class="current"><a href="${ctx}/login.jsp">返回首页</a></li>
				<li><a href="#">${sessionScope.teacher.name}<span class="arrow"></span></a><ul>
					<li><a href="${ctx}/myInformation.jsp">我的&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
					<li>
						<a href="${ctx}/changePassword.jsp" onclick="return updatePassword();">
						修改密码&nbsp;&nbsp;&nbsp;&nbsp;</a>
					</li>
					<li><a href="#">我的试卷</a><ul>
							<li><a href="${ctx}/paper/getPaperRecord.do">试卷上传记录</a></li>
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

	<c:choose>
		<c:when test="${flag && flag1}">
			<div id="container"> 
				<div align="center">
					<form action="${ctx}/reviewResult/selectReviewResult.do" method = "post">
						<input type = "text" id = "keyWord" name = "keyWord" placeholder="请输入试卷编号(支持模糊查询）" 
						style = "width:200px;height:15px" autofocus="autofocus">&nbsp;&nbsp;
						<input type = "submit" value = "搜索" name ="submit" id = "submit"style = "width:80px;height:40px" > &nbsp;&nbsp;
					</form>			
				</div>
				<br><br>
				<table class="zebra">
				<caption>我的试卷分析结果记录</caption>
					<thead>				
						<tr>					
							<th>试卷id</th>	
							<th>上传时间</th>	
							<th>信度</th>	
							<th>难度</th>	
							<th>区分度</th>	
							<th>校标度</th>
							<th>操作</th>							
						</tr>					
					</thead>				
					<tbody>
					<c:forEach var = "reviewResultList" items = "${reviewResultList}" varStatus = "status">
						<tr>
							<td>${reviewResultList.pId}</td>
							<td>${reviewResultList.time}</td>
							<td>${reviewResultList.validityB}</td>
							<td>${reviewResultList.difficulty}</td>
							<td>${reviewResultList.distinction}</td>
							<td>${reviewResultList.reliability}</td>	
							<td>
								<div>
									<form action="${ctx}/downResult/downResult.do" method = "post">
										<input type = "hidden" name = "id" id = "id" value = "${reviewResultList.id}">
										<input type = "submit" value = "导出分析结果" name = "submit">
									</form>
								</div>
							</td>											
						</tr>			
					</c:forEach>
					</tbody>
				</table>				
			</div>
		</c:when>
		<c:when test="${flag && !flag1}">
			<div id="container"> 
				<div align="center">
					<form action="${ctx}/reviewResult/selectReviewResult.do" method = "post">
						<input type = "text" id = "keyWord" name = "keyWord" placeholder="请输入试卷编号(支持模糊查询）" 
						style = "width:200px;height:15px" autofocus="autofocus">&nbsp;&nbsp;
						<input type = "submit" value = "搜索" name ="submit" id = "submit"style = "width:80px;height:40px" > &nbsp;&nbsp;
					</form>			
				</div>
				<br><br><br>
				<h4>查询结果为空 </h4>
			</div>
		</c:when>
		<c:otherwise>
			<div id="container"> 
				<h4>当前用户暂未分析任何试卷，无分析结果    
				 <a href="${ctx}/upFile.jsp">上传文档</a> </h4>
			</div>
		</c:otherwise>
	</c:choose>
	
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