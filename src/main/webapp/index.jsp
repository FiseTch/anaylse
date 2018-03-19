<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<%@include file="/view/common/header.jsp"%>
<title>试卷分析系统</title>  
 <link rel="stylesheet" href="css/style.css">
</head>  
<script src="js/jquery-2.1.4.min.js"></script> 
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script> 
<script src="js/jquery-form.js"></script>  
<body >  
<!-- <DIV class=jstime style="FLOAT: right; WIDTH: 45%; TEXT-ALIGN: right;">
	  ****************时间日历开始****************
      <SCRIPT>setInterval("clock.innerHTML=new Date().toLocaleString()+'&nbsp;&nbsp;星期'+'日一二三四五六'.charAt(new Date().getDay());",100)</SCRIPT>
      <SPAN id=clock></SPAN>
	  ****************时间日历结束****************&nbsp;&nbsp; 
</DIV>  -->
	 <form method="post" action="${ctx}/user/login.do" class="login">
    <p>
      <label for="login">Email:</label>
      <input type="text" name="username" id="login" value="name@example.com">
    </p>

    <p>
      <label for="password">Password:</label>
      <input type="password" name="pwd" id="password" value="10100010">
    </p>

    <p class="login-submit">
      <button type="submit" class="login-button" onclick="Login_Checked()">Login</button>
    </p>

    <p class="forgot-password"><a href="index.html">Forgot your password?</a></p>
  </form>

  <section class="about">
    <p class="about-links">
      <a href="http://www.cssflow.com/snippets/dark-login-form" target="_parent">View Article</a>
      <a href="http://www.cssflow.com/snippets/dark-login-form.zip" target="_parent">Download</a>
    </p>
    <p class="about-author">
      &copy; 2012&ndash;2013 <a href="http://thibaut.me" target="_blank">Thibaut Courouble</a>
      <a href="http://www.cssflow.com/mit-license" target="_blank">MIT License</a><br>
      Original PSD by <a href="http://365psd.com/day/2-234/" target="_blank">Rich McNabb</a>
  </section>
</body>  
</html> 
