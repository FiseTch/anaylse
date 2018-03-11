<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/view/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

1
		${basePath}
		1
    <!-- ${ctx} 代表的路径为 /anaylse -->
	<form action="${ctx}/excelTest/test.do" method="post">
			<input type="text" class="text" name="username">
			<div class="key">
				<input type="password" name="password">
			</div>

			<div class="signin">
				<input type="submit" value="登陆">
			</div>
		</form>

</body>
</html>