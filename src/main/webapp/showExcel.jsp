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
<title>&copyFise</title>  
</head>   
<body>  
<header>
	<div class="container_24">
		<div class="grid_24">
			<h1 class="fleft"><a href="http://www.jxufe.edu.cn/" title="江西财经大学" target="_blank">学校</a></h1>
			<ul class="sf-menu">
			
				 <li class="current"><a href="${ctx}/index.html">主页</a></li>
				 <li><a href="${ctx}/view/user/index.jsp">登录</a></li>
			
				 <li><a href="#">${sessionScope.teacher.name}<span class="arrow"></span></a><ul>
						<li><a href="${ctx}/myInformation.jsp">我的&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
						<li><a href="#">修改密码&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
						<li><a href="#">我的试卷</a><ul>
								<li><a href="${ctx}/paper/getPaperRecord.do">上传记录</a></li>
								<li><a href="${ctx}/reviewResult/getReviewResult.do">分析结果</a></li>
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
	<div id="container"> 
		<table class="zebra">
		<caption>上传文件详细 试卷编号：${paperId}</caption>
			<thead>
				<c:forEach var = "headMap" items = "${data}" begin = "1" end ="1" step="1">
				<tr>
					<c:forEach var = "headList" items = "${headMap.value}" varStatus="status">
						<th>
							${headList}
						 	<%-- <input name = "param${status.index}" style = "border:none;text-align:center;"value = "${headList}" type= "text"/> --%>
						</th>
					</c:forEach>
				</tr>
				</c:forEach>
			</thead>
			
			<tbody>
			<c:forEach var = "map" items = "${data}" begin = "2">
				<tr>		
					<c:forEach var = "mList" items = "${map.value}"  varStatus="gradeStatus">
					<c:choose>
						<c:when test="${gradeStatus.last}">
							<td>
								${mList}
								<%-- <input name ="totalGrade" value = "${mList}" style = "border:none;text-align:center;"type = "text"/> --%>
							</td>
						</c:when>
						<c:otherwise>
							<td>
								${mList}
						 		<%-- <input name = "param${gradeStatus.index}" style = "border:none;text-align:center;"value = "${mList}" type= "text"/> --%>
							</td>
						</c:otherwise>
					</c:choose>				  
					</c:forEach>
				</tr>			
			</c:forEach>
			</tbody>
		</table>	
	</div>
	<form action="${ctx}/calc/dealData.do" method = "post">
		<input name = "deal" value = "提交" type = "submit" style = "margin-left: 1080px; margin-top: 20px;">
	</form>
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