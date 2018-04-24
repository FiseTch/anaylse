<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="tch.model.Teacher"%>
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
<title>我的信息&copyFise</title>  
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
		return $("#changePassword").submit();
    }else{
		return;
    }
}
function check(){
    if(confirm("确认修改吗？")){
		alert("信息已更新成功！页面刷新中...")
		return true;
    }else{
		return false;
    }
}
</script>
<% SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	Teacher t = (Teacher)session.getAttribute("teacher");
	if(t.getBirthday() != null){
		String birthday = sdf.format(t.getBirthday());
		request.setAttribute("birthday", birthday);		
	}
	if(t.getHiredate() != null){
		String hiredate = sdf.format(t.getHiredate());
		request.setAttribute("hiredate", hiredate);
	}
	%>
<header>
	<div class="container_24">
		<div class="grid_24">
			<h1 class="fleft"><a href="http://www.jxufe.edu.cn/" title="江西财经大学" target="_blank">学校</a></h1>
			<ul class="sf-menu">				 
				<li><a href="${ctx}/login.jsp">返回首页</a></li>	
				<li><a href="#">${sessionScope.teacher.name}<span class="arrow"></span></a><ul>
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

	<div class="container_24">
		<div class="wrapper">
			<div class="grid_16 padtop2">
				<form action ="${ctx}/teacher/update.do"method = "post" onsubmit="return check()">				
					<table>
						<tr>										
							<td class = "padbot2">
								<p class="padbot2">用户名：</p>
							</td>
							<td>
								<label class="name">
									<input type="text" value="${sessionScope.teacher.name}" name = "name">
									<span class="empty">*This field is required.</span>
									<span class="clear"></span>
								</label>									
							</td>
						</tr>
						<tr>	
							<td class = "padbot2">
								<p class="padbot2">性别：</p>
							</td>
							<td>
								<label class="name">
									<input type="text" value="${sessionScope.teacher.sex}" name = "sex">
									<span class="empty">*This field is required.</span>
									<span class="clear"></span>
								</label>
							</td>
						</tr>
						<tr>										
							<td class = "padbot2">
								<p class="padbot2">生日：</p>
							</td>
							<td>
								<label class="name">
									<input type="date" value="${requestScope.birthday}" name = "birthday">
									<span class="empty">*This field is required.</span>
									<span class="clear"></span>
								</label>									
							</td>
						</tr>
						<tr>										
							<td class = "padbot2">
								<p class="padbot2">电话：</p>
							</td>
							<td>
								<label class="name">
									<input type="text" value="${sessionScope.teacher.tel}" name ="tel">
									<span class="empty">*This field is required.</span>
									<span class="clear"></span>
								</label>									
							</td>
						</tr>
						<tr>										
							<td class = "padbot2">
								<p class="padbot2">职称：</p>
							</td>
							<td>
								<label class="name">
									<input type="text" value="${sessionScope.teacher.prof}" name = "prof">
									<span class="empty">*This field is required.</span>
									<span class="clear"></span>
								</label>									
							</td>
						</tr>
						<tr>										
							<td class = "padbot2">
								<p class="padbot2">入职日期：</p>
							</td>
							<td>
								<label class="name">
									<input type="date" value="${requestScope.hiredate}" name = "hiredate">
									<span class="empty">*This field is required.</span>
									<span class="clear"></span>
								</label>									
							</td>
						</tr>
						<tr>										
							<td class = "padbot2">
								<p class="padbot2">院系：</p>
							</td>
							<td>
								<label class="name">
									<input type="text" value="${sessionScope.teacher.depart}" name = "depart">
									<span class="empty">*This field is required.</span>
									<span class="clear"></span>
								</label>									
							</td>
						</tr>
						<tr>										
							<td class = "padbot2">
								<p class="padbot2">专业科目：</p>
							</td>
							<td>
								<label class="name">
									<input type="text" value="${sessionScope.teacher.subject}" name = "subject">
									<span class="empty">*This field is required.</span>
									<span class="clear"></span>
								</label>									
							</td>
						</tr>
						<tr>
							<td>
								<label class="name">
									<input type="submit" value="修改">
									<span class="empty">*This field is required.</span>
									<span class="clear"></span>
								</label>									
							</td>
							<td>
								<label class="name">
									<input type="reset" value="恢复默认">
									<span class="empty">*This field is required.</span>
									<span class="clear"></span>
								</label>									
							</td>
						</tr>
						
					</table>
				</form>
			</div>
			<div class="grid_8 padtop2">
				<h4 class="padbot2">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					用户&nbsp;${sessionScope.teacher.id}
				</h4>
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