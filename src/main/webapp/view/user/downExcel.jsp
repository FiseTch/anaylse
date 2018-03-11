<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/view/common/header.jsp"%>
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>downExcel下载</title>  
</head>  
<script src="js/jquery-2.1.4.min.js"></script> 
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script> 
<script src="js/jquery.form.js"></script>  
<body>  
<script>
function newDoc(){
    window.location.assign("${ctx}/view/user/upExcel.jsp")
}
</script>
<form id = "downExcel"action="${ctx}/excelTempleDown/downloadExcel.do" method = "post">
	最多不超过25道题<input id = "num"type = "text" name = "num">
	<input id = "down" value = "下载" type = "submit">
</form>
	<input type="button" value="上传文档" onclick="newDoc()">

</body>
</html>