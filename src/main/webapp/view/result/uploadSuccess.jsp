<%@ page language="java" contentType="text/html; charset=UTF-8" import = "com.alibaba.fastjson.JSON,java.util.*"
    pageEncoding="UTF-8"%>
<%@include file="/view/common/header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.table_border{  
    border: solid 1px #B4B4B4;  
    border-collapse: collapse;     --折叠样式.  
    text-align:center;
}  
.table_border tr th{
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
		<table border="0.5"class = "table_border">
			<caption>学生成绩表</caption>  		
			<c:forEach var = "map" items = "${data}" begin = "1">
				<tr class = "table_border tr th">		
					<c:forEach var = "mList" items = "${map.value}"  varStatus="gradeStatus">
					<c:choose>
						<c:when test="${gradeStatus.last}">
							<td class = "table_border tr td" id = "td"  >
								<input name ="totalGrade" value = "${mList}" style = "border:none;text-align:center;"type = "text"/>
							</td>
						</c:when>
						<c:otherwise>
							<td class = "table_border tr td" id = "td" >
						 		<input name = "param${gradeStatus.index}" style = "border:none;text-align:center;"value = "${mList}" type= "text"/>
							</td>
						</c:otherwise>
					</c:choose>				 
					</c:forEach>
				</tr>			
			</c:forEach>		
		</table>
<c:set var="test" value = "${data}" scope = "session">
</c:set>
<%
Map<Integer,List<String>> test = (Map<Integer, List<String>>)session.getAttribute("test");
System.out.println(test);
Map<Integer,List<String>> data = (Map<Integer, List<String>>)session.getAttribute("data");
System.out.println(data);
%>
	<form action="${ctx}/anaylse/dealData.do" method = "post" name = "deal" id ="myForm">
		<input name = "deal" value = "提交" type = "submit">
	</form>
</body>
</html>