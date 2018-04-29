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
<title>试卷上传记录&copyFise</title>  
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
/* function seacherForm(){
    var keyWord = document.getElementById("keyWord").value;
    if(keyWord != null  && keyWord != "" && keyWord != keyWord){	
		return true;
    }else{
		alert("请输入关键词");
		document.getElementById("keyWord").focus();
		return false;
    }
} */

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
	<c:choose>
		<c:when test="${flag && flag1}">
			<div id="container">
				<div align="center">
					<form action="${ctx}/paper/selctPaperRecord.do" method = "post">
						<input type = "text" id = "keyWord" name = "keyWord" style = "width:200px;height:15px"
						placeholder="请输入关键字（支持模糊查询）" autofocus="autofocus">&nbsp;&nbsp;
						<select name = "classfiy" id = "classfiy"style = "width:120px;height:40px">
							<option value = "paperId" selected="selected">试卷编号</option>
							<option value = "subject">科目</option>
							<option value = "term">课程开设学期</option>
							<option value = "subjectPerson">出题人</option>
						</select>&nbsp;&nbsp;
						<input type = "submit" value = "搜索" name ="submit" style = "width:80px;height:40px" id = "submit">&nbsp;&nbsp; 
					</form>	
				</div> 
				<br>		
				<table class="zebra">
				<caption>我的试卷上传记录</caption>
					<thead>				
						<tr>
							<th>试卷编号</th>
							<th>科目</th>
							<th>试卷总分</th>
							<th>考试时间</th>
							<th>上传时间</th>
							<th>出题人</th>
							<th>试卷审核人</th>
							<th>测试人数</th>
							<th>课程开设学期</th>
							<th>考试用时</th>					
						</tr>				
					</thead>
					
					<tbody>
						<c:forEach var = "paperDetailList" items = "${paperDetailList}">
							<tr>
							    <td>${paperDetailList.paperid}</td>
								<td>${paperDetailList.subject}</td>
								<td>${paperDetailList.score}</td>
								<td>${paperDetailList.time}</td>	
								<td>${paperDetailList.uptime}</td>
								<td>${paperDetailList.subjectperson}</td>
								<td>${paperDetailList.teacher}</td>
								<td>${paperDetailList.num}</td>
								<td>${paperDetailList.term}</td>
								<td>${paperDetailList.papertime}</td>
		   					</tr>			
						</c:forEach>
					</tbody>
				</table>	
			</div>
		</c:when>
		<c:when test="${flag && !flag1}">
			<div id="container">
				<div align="center">
					<form action="${ctx}/paper/selctPaperRecord.do" method = "post">
						<input type = "text" id = "keyWord" name = "keyWord" style = "width:200px;height:15px"
						placeholder="请输入关键字（支持模糊查询）" autofocus="autofocus">&nbsp;&nbsp;
						<select name = "classfiy" id = "classfiy"style = "width:120px;height:40px">
							<option value = "paperId" selected="selected">试卷编号</option>
							<option value = "subject">科目</option>
							<option value = "term">课程开设学期</option>
							<option value = "subjectPerson">出题人</option>
						</select>&nbsp;&nbsp;
						<input type = "submit" value = "搜索" name ="submit" style = "width:80px;height:40px" id = "submit">&nbsp;&nbsp; 
					</form>	
				</div>
				<br><br><br>
				<h4>
					当前查询无结果   					
				</h4>
			</div>
		</c:when>			
		<c:otherwise>
			<div id="container"> 
				<h4>当前用户暂无数据    
					<a href="${ctx}/upFile.jsp">上传文档</a>
				</h4>
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