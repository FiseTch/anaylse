<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>文件上传&copyFise</title>  
</head>
<%	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String time = sdf.format(new Date());
%>   
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
function checkSubject(){
    var subject = document.getElementById("subject").value;
    if(subject != null  && subject != "" && subject != undefined){		
		$("#emptySubject").hide();		
	}else{		
		$("#emptySubject").show();
	} 
}
function checkScore(){
    var score = document.getElementById("score").value;
    if(score != null  && score != "" && score != undefined){		
	    var num1 = score.replace(/[^\d]/g,'');
	    if(num1 == score){
		    $("#replaceScore").hide();
		    $("#emptyScore").hide();
	    }else{
			$("#replaceScore").show();
			$("#emptyScore").hide();		
	    }						
	}else{		
		$("#emptyScore").show();
		$("#replaceScore").hide();
	} 
}
function checkSubjectPerson(){
    var subjectPerson = document.getElementById("subjectPerson").value;
    if(subjectPerson != null  && subjectPerson != "" && subjectPerson != undefined){		
		$("#emptySubjectPerson").hide();		
	}else{		
		$("#emptySubjectPerson").show();
	} 
}
function checkTeacher(){
    var teacher = document.getElementById("teacher").value;
    if(teacher != null  && teacher != "" && teacher != undefined){		
		$("#emptyTeacher").hide();		
	}else{		
		$("#emptyTeacher").show();
	} 
}
function checkPaperTime(){
    var paperTime = document.getElementById("paperTime").value;
    if(paperTime != null  && paperTime != "" && paperTime != undefined){	
		var num1 = paperTime.replace(/[^\d]/g,'');
	    if(num1 == paperTime){
		    $("#replacePaperTime").hide();
		    $("#emptyPaperTime").hide();
	    }else{
			$("#replacePaperTime").show();
			$("#emptyPaperTime").hide();		
	    }		
	}else{		
		$("#emptyPaperTime").show();
		$("#replacePaperTime").hide();
	} 
}
function checkNum(){
    var num = document.getElementById("num").value;
    if(num != null  && num != "" && num != undefined){	
		var num1 = num.replace(/[^\d]/g,'');
		if(num1 == num){
		    $("#replaceNum").hide();
		    $("#emptyNum").hide();
		}else{
		    $("#replaceNum").show();
			$("#emptyNum").hide();		
		}
	}else{		
		$("#emptyNum").show();
		$("#replaceNum").hide();
	} 
}
function checkEmpty(){
    var subject = document.getElementById("subject").value;    
    var score = document.getElementById("score").value;
    var score1 = score.replace(/[^\d]/g,'');
    var subjectPerson = document.getElementById("subjectPerson").value;
    var teacher = document.getElementById("teacher").value;
    var paperTime = document.getElementById("paperTime").value;
    var paperTime1 = paperTime.replace(/[^\d]/g,'');
    var num = document.getElementById("num").value;
    var num1 = num.replace(/[^\d]/g,'');
	var fileName = document.getElementById("upload").value;
    if(subject == null  || subject == "" || subject == undefined){
		document.getElementById("subject").focus();
		return false;
    }
    if(score == null  || score == "" || score == undefined || score != score1){
		document.getElementById("score").focus();
		return false;
	}
    if(subjectPerson == null  || subjectPerson == "" || subjectPerson == undefined){
		document.getElementById("subjectPerson").focus();	
		return false;
	}
    if(teacher == null  || teacher == "" || teacher == undefined){
		document.getElementById("teacher").focus();
		return false;
	}
    if(paperTime == null  || paperTime == "" || paperTime == undefined || paperTime != paperTime1){
		document.getElementById("paperTime").focus();
		return false;
	}
    if(num == null  || num == "" || num == undefined || num != num1){
		document.getElementById("num").focus();
		return false;
	}
    if(fileName != null && fileName != "" && fileName != undefined){
		var postfix = fileName.substring(fileName.lastIndexOf(".")+1);  
		if("xls" == postfix || "xlsx"  == postfix){
		    return true;
		}else{
		    alert("请输入.xls或者.xlsx格式的文件");
		    return false;
		}
    }else{
		alert("请选择文件");
		return false;
	
    }
}
</script>
<header>
	<div class="container_24">
		<div class="grid_24">
			<h1 class="fleft"><a href="http://www.jxufe.edu.cn/" title="江西财经大学" target="_blank">学校</a></h1>
			<ul class="sf-menu">
				<li class="current"><a href="${ctx}/login.jsp">主页</a></li>
				<li><a href="${ctx}/downTemple.jsp">模板下载</a></li>
				<li><a href="#">${sessionScope.teacher.name}<span class="arrow"></span></a><ul>
					<li><a href="${ctx}/myInformation.jsp">我的&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
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
			<div class="panel panel-default">
				<h4>文件上传</h4><br>
				<%-- <h4>上传模板格式<a href="${ctx}/downTemple.jsp">（模板下载）</a></h4>
				<img alt="" src="${ctx}/images/temple.png" width = "900px"> --%>
				<div class="panel-body">
				    <form id="upfile" action = "${ctx}/paper/upPaper.do" onsubmit = "return checkEmpty()" method = "post" enctype="multipart/form-data">  
				        <table>
							<tr>										
								<td class = "padbot2">
									<p class="padbot2">课程名称：</p>
								</td>
								<td>
									<label class="name">
										<input type="text" placeholder="eg:C语言程序设计" onblur = "checkSubject()" autofocus = "autofocus" name = "subject" id = "subject">
										<span class="emptySubject" id = "emptySubject"style="color: red;">*课程名称不允许为空</span>
									</label>									
								</td>
							</tr>
							<tr>	
								<td class = "padbot2">
									<p class="padbot2">试卷总分：</p>
								</td>
								<td>
									<label class="name">
										<input type="text" placeholder="eg:100" onblur = "checkScore()"name = "score" id = "score">
										<span class="replaceScore" id = "replaceScore"style="color: red;display:none">*当前输入含有非法字符，请输入数字</span>
										<span class="emptyScore" id = "emptyScore"style="color: red;">*试卷总分不允许为空</span>
									</label>
								</td>
							</tr>
							<tr>										
								<td class = "padbot2">
									<p class="padbot2">出题人：</p>
								</td>
								<td>
									<label class="name">
										<input type="text" placeholder="eg:张三" onblur = "checkSubjectPerson()" name = "subjectPerson" id = "subjectPerson">
										<span class="emptySubjectPerson" id = "emptySubjectPerson"style="color: red;">*出题人不允许为空</span>
									</label>									
								</td>
							</tr>
							<tr>										
								<td class = "padbot2">
									<p class="padbot2">试卷审核人：</p>
								</td>
								<td>
									<label class="name">
										<input type="text" placeholder="eg:李四" onblur = "checkTeacher()" name = "teacher" id = "teacher">
										<span class="emptyTeacher" id = "emptyTeacher"style="color: red;">*试卷审核人不允许为空</span>
									</label>									
								</td>
							</tr>
							<tr>										
								<td class = "padbot2">
									<p class="padbot2">考试时间：</p>
								</td>
								<td>
									<label class="name">
										<input type="date" value="<%=time%>" name ="time" id = "time">
									</label>									
								</td>
							</tr>
							<tr>										
								<td class = "padbot2">
									<p class="padbot2">考试用时（分钟）：</p>
								</td>
								<td>
									<label class="name">
										<input type="text" placeholder = "eg:120" onblur = "checkPaperTime()" name = "paperTime" id = "paperTime">
										<span class="replacePaperTime" id = "replacePaperTime"style="color: red;display:none">*当前输入含有非法字符，请输入数字</span>
										<span class="emptyPaperTime" id = "emptyPaperTime" style="color: red;">*考试用时不允许为空</span>
									</label>									
								</td>
							</tr>
							<tr>										
								<td class = "padbot2">
									<p class="padbot2">课程开设学期：</p>
								</td>
								<td>
									<label class="name">
										<select name = "term" id = "term">
										  <option value="141" >141</option>
										  <option value="142" >142</option>
										  <option value="151" >151</option>
										  <option value="152" >152</option>
										  <option value="161" >161</option>
										  <option value="162" >162</option>
										  <option value="171" selected = "selected">171</option>
										  <option value="172" >172</option>
										  <option value="181" >181</option>
										  <option value="182" >182</option>
										</select>
									</label>									
								</td>
							</tr>
							<tr>										
								<td class = "padbot2">
									<p class="padbot2">考试总人数：</p>
								</td>
								<td>
									<label class="name">
										<input type="text" placeholder = "eg:100" onblur = "checkNum()" name = "num" id = "num">
										<span class="replaceNum" id = "replaceNum"style="color: red;display:none">*当前输入含有非法字符，请输入数字</span>									
										<span class="emptyNum" id = "emptyNum"style="color: red;">*考试总人数不允许为空</span>
									</label>									
								</td>
							</tr>
							<tr>										
								<td class = "padbot2">
									<p class="padbot2">选择一个文件: </p>
								</td>
								<td>
									<label class="name">
										<input type="file" name="file" id="upload" /> 
									</label>									
								</td>
							</tr>
							<tr>
								<td>
									<label class="name">
										<input type="submit" value="上传">
									</label>									
								</td>
								<td>
									<label class="name">
										<input type="reset" value="重置">
									</label>									
								</td>
							</tr>
						</table>
				    </form>  
		    	</div>
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
				<a href="http://www.jxufe.edu.cn/" class="link"  title="江西财经大学" target="_blank">
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