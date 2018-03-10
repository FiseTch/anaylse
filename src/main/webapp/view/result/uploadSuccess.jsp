<%@ page language="java" contentType="text/html; charset=UTF-8" import = "com.alibaba.fastjson.JSON"
    pageEncoding="UTF-8"%>
<%@include file="/view/common/header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="/js/jquery-3.1.1.min.js"/>
<head>
<style type="text/css">
.table_border{  
    border: solid 1px #B4B4B4;  
    border-collapse: collapse;     --折叠样式.  
    text-align:center;
}  
.table_border tr th{  
   /*  background:url("../../images/gray/bg_table_th.gif") repeat;   */
    padding-left:4px;  
    height:27px;  
    border: solid 1px #B4B4B4;  
    text-align:center;
}  
.table_border tr td{  
    height:25px;  
    padding:4px;  
    border: solid 1px #B4B4B4;  
    text-align:center;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>页面成功</title>
</head>
<body>
<script type="text/javascript">
	//在页面加载时候，就使td节点具有click点击能力
	$(document).ready(function(){
	    var tdNods = $("td");
	    tdNods.click(tdClick);
	});
	//td的点击事件
	function tdClick(){
	    //将td的文本内容保存
	    var td = $(this);
	    var tdText = td.text();
	    //将td的内容清空
	    td.empty();
	    //新建一个输入框
	    var input = $("<input>");
	    //将保存的文本内容赋值给输入框
	    input.attr("value",tdText);
	    //将输入框添加到td中
	    td.append(input);
	    //给输入框注册事件，当失去焦点时就可以将文本保存起来
	    input.blur(function(){
	        //将输入框的文本保存
	        var input = $(this);
	        var inputText = input.val();
	        //将td的内容，即输入框去掉,然后给td赋值
	        var td = input.parent("td");
	        td.html(inputText);
	        //让td重新拥有点击事件
	        td.click(tdClick);
	    });
	    //将输入框中的文本高亮选中
	    //将jquery对象转化为DOM对象
	    var inputDom = input.get(0);
	    inputDom.select();
	    //将td的点击事件移除
	    td.unbind("click");
	}
	 
	3.对上面的效果进行进一步的修改，使之更加人性化（红色字体是修改过的地方）
	通过修改使你在修改的过程中文本的内容可以按Esc键撤销还原到修改前的状态
	//在页面加载时候，就使td节点具有click点击能力
	$(document).ready(function(){
	    var tdNods = $("td");
	    tdNods.click(tdClick);
	});
	//td的点击事件
	function tdClick(){
	    //将td的文本内容保存
	    var td = $(this);
	    var tdText = td.text();
	    //将td的内容清空
	    td.empty();
	    //新建一个输入框
	    var input = $("<input>");
	    //将保存的文本内容赋值给输入框
	    input.attr("value",tdText);
	    //将输入框添加到td中
	    td.append(input);
	    //给输入框注册事件，当失去焦点时就可以将文本保存起来
	    input.blur(function(){
	        //将输入框的文本保存
	        var input = $(this);
	        var inputText = input.val();
	        //将td的内容，即输入框去掉,然后给td赋值
	        var td = input.parent("td");
	        td.html(inputText);
	        //让td重新拥有点击事件
	        td.click(tdClick);
	    });
	    input.keyup(function(event){
	        //1.获取当前用户按下的键值
	              //解决不同浏览器获得事件对象的差异,
	             // IE用自动提供window.event，而其他浏览器必须显示的提供，即在方法参数中加上event
	           var myEvent = event || window.event;
	           var keyCode = myEvent.keyCode;
	           //2.判断是否是ESC键按下
	           if(keyCode == 27){
	               //将input输入框的值还原成修改之前的值
	               input.val(tdText);
	           }
	    });
	    //将输入框中的文本高亮选中
	    //将jquery对象转化为DOM对象
	    var inputDom = input.get(0);
	    inputDom.select();
	    //将td的点击事件移除
	    td.unbind("click");
	}
</script>
<%-- 	<c:forEach var = "list" items = "${data}"> --%>
		<table border="0.5"class = "table_border">
			<c:forEach var = "map" items = "${data}" begin = "1">
				<tr class = "table_border tr th">		
					<c:forEach var = "mList" items = "${map.value}">
						<td class = "table_border tr td" id = "td"> ${mList}</td>
					</c:forEach>
				</tr>			
			</c:forEach>		
		</table>
<%-- 	</c:forEach> --%>
<script type="text/javascript">
	function dataSubmit(){
		var jsonArr = [];
		$(#)
	}
</script>
	<form action="${ctx}/anaylse/dealData.do" method = "post" name = "deal" id ="myForm">
		<input name = "data" type = "hidden" value = "${map}"> 
		<input name = "deal" value = "提交" type = "button">
	</form>
</body>
</html>