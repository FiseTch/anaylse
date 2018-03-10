<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/view/common/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结果展示</title>
</head>
<body>
<table border="1" style ="color:red">
		<c:forEach var = "map" items = "${result}">
			<tr>
				<td> ${map.key}</td>
				<td> ${map.value}</td>
				
			</tr>
			
		</c:forEach>		
		</table>

</body>
</html>