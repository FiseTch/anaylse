<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/view/common/header.jsp"%>
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>用户登录成功页面</title>  
</head>   
<body>  
<script>
function newDoc(){
    window.location.assign("${ctx}/view/user/upExcel.jsp")
}
</script>
<div style ="margin-right">
	<marquee scrollAmount=2 width=450 direction="left">欢迎用户 “ ${user.username} ”登录</marquee>
</div>
<br>
<form id = "downExcel"action="${ctx}/excelTempleDown/downloadExcel.do" method = "post">
	请输入题目数量（最多不超过25道题）<input id = "num"type = "text" name = "num">
	<input id = "down" value = "下载" type = "submit">
</form>
	<input type="button" value="上传文档" onclick="newDoc()">
  
</body>
</html>