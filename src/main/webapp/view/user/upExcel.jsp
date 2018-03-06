<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/view/common/header.jsp"%>
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>Insert title here</title>  
</head>  
<script src="js/jquery-2.1.4.min.js"></script> 
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script> 
<script src="js/jquery.form.js"></script>  
<body>  
<script type="text/javascript">
if(${fail} != null ){
alert(${fail})
}</script>
    <form id="upfile" action = "${ctx}/getExcel/upExcel.do" method = "post" enctype = "multipart/form-data">  
        选择一个文件:  
      <input type="file" name="file" id="upload" />  
        <br/><br/> 
        <input id="uploadFile" value="上传" type="submit"/>  
    </form>  
      
    <div id="upFile"></div>  
</body>  
<script type="text/javascript">  
  
$("#uploadFile").click(function(){  
    var formData = new FormData($("#upfile")[0]);  
    //formData.set('file', document.getElementById("upload").files[0]);  
    $.ajax({  
        url: '${pageContext.request.contextPath}/uploadFile/upload',  
        type: 'POST',  
        cache: false,  
        data: formData,  
        processData: false,  
        contentType: false  
    }).done(function(res) {  
          
    });  
});  
</script>  
</html> 
