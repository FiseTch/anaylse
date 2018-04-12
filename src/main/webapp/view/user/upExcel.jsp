<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/view/common/header.jsp"%>
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>upExcel上传excel</title>  
</head>  
<body>  
<script type="text/javascript">
	if(${fail} != null ){
		alert(${fail})
	}
</script>
    <form id="upfile" action = "${ctx}/getExcel/upExcel.do" method = "post" enctype = "multipart/form-data">  
        	选择一个文件:  
      <input type="file" name="file" id="upload" />  
        <br/><br/> 
        <input id="uploadFile" value="上传" type="submit"/>  
    </form>      
</body>      
</html> 
