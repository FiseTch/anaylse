<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!--第一步：引入.js文件 ajaxSubmit需要jquery.form.js-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Excel 测试</title>
    <script type="text/javascript" src="/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src ="/js/jquery-form.js"></script>

	<script type="text/javascript">
	    $("#createForm").validate({
	        //做表单验证
	
	        //验证成功后提交参数
	        submitHandler : function(form) {
	            ajaxSubmit();
	        }
	
	    });
	    function ajaxSubmit() {
	
	        /*用ajaxSubmit()方法提交文件*/
	        $("#createForm").ajaxSubmit({
	            type : 'post',
	            url : "emergencyNotice/create",
	            error : function() {//请求失败处理函数  
	                alert("失败");
	            },
	            success : function(data) { //请求成功后处理函数。
	               alert("成功");
	            }
	        });
	    };
	</script>
	
</head>
<body>

<form action="src/main/java/tch/util/HandleFile" method = "post" id = "batchAdd" name = "batchAdd" target ="result" enctype="multipart/form-data"
onsubmit="return check()">
 <ul>
            <li><label>标题：</label> <input type="text" class="input-box"
                placeholder="请输入标题" name="title"></li>

            <li><label>内容：</label> <textarea class="textarea-box"
                    name="content"></textarea></li>

            <li><label>上传附件：</label> 
          <!--   <input type="file" class="img-btn" name="file"> -->
        	<input id="excel_file" type="file" name="excelFile" size="50" class="img-btn" /> 
            </li>

            <!-- 点击保存按钮提交form -->
            <li><input type="submit" class="btn save-btn" value="保存">
            </li>
        </ul>  
</form>  


</body>
</html>
