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

<%-- <div style ="margin-right">设置用户滚动屏
	<marquee scrollAmount=2 width=450 direction="left">欢迎用户 “ ${user.username} ”登录</marquee>
</div> --%>
	 <form method="post" action="${ctx}/user/login.do" class="login">
    <p>
      <label for="login">Email:</label>
      <input type="text" name="username" id="login" value="tel">
    </p>

    <p>
      <label for="password">Password:</label>
      <input type="password" name="pwd" id="password" value="10100010">
    </p>

    <p class="login-submit">
      <button type="submit" class="login-button" onclick="Login_Checked()">Login</button>
    </p>

    <p class="forgot-password"><a href="${ctx}/view/user/forgetPassword.jsp">忘记密码</a></p>
  </form>

  <section class="about">
    <p class="about-links">
      <a href="https://github.com/FiseTch" target="_parent">联系作者</a>
      <a href="https://github.com/FiseTch/anaylse" target="_parent">源码下载</a>
    </p>
    <p class="about-author">
      &copy; 2018 备案<br>    
                <!-- 作者<a href="http://365psd.com/day/2-234/" target="_blank">Tch</a> -->
  </section>
</body>  
</html> 
