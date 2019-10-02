<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ page import="com.model.User" %>

<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>首页</title>
	<link rel='stylesheet prefetch' href='../css/bootstrap.min.css'>
	<link rel="stylesheet" type="text/css" href="../css/htmleaf-demo.css">
	<link rel="stylesheet" href="../css/style.css">
	<!--[if IE]>
		<script src="http://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<![endif]-->
    <style>
        #frame{
            width:600px;
            height:500px;
            border: 0;
        }
    </style>
</head>
<body>
    <% 
User user = (User)session.getAttribute("user");
if(null == user)
{
    response.sendRedirect("/bishe/login.jsp");
}
            
%>
	<div id="wrapper" class="toggle toggled">
        <!-- <div class="overlay"></div> -->
    
        <!-- Sidebar -->
        <nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
            <ul class="nav sidebar-nav">
                <li class="sidebar-brand">
                    <a href="#">
                      欢迎您，${user.username}  <%-- ${user.uid} ${user.getUsername();} --%>
                    </a>
                </li>
                <li>
                
                    <a href="main.jsp" target="mainFrame"><i class="fa fa-fw fa-home"></i> 首页</a>
                </li>
                <li><%-- ${user.uid} --%>
                    <a href="/bishe/card/getAllCard?uid=${user.uid}" target="mainFrame"><i class="fa fa-fw fa-folder"></i> 我的银行卡</a>
                </li>
                <li><%-- ${user.uid} --%>
                    <a href="/bishe/log/getLogsByUid?uid=${user.uid}" target="mainFrame"><i class="fa fa-fw fa-file-o"></i> 操作日志</a>
                </li>
                <li><%-- ${user.uid } --%>
                    <a target="mainFrame" href="/bishe/home/updateUserPwd.jsp?uid=${user.uid}"><i class="fa fa-fw fa-cog"></i> 修改密码</a>
                </li>
<!--                 <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-fw fa-plus"></i> Dropdown <span class="caret"></span></a>
                  <ul class="dropdown-menu" role="menu">
                    <li class="dropdown-header">Dropdown heading</li>
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li><a href="#">Separated link</a></li>
                    <li><a href="#">One more separated link</a></li>
                  </ul>
                </li> -->
               <li>
                    <a href="/bishe/home/personal.jsp" target="mainFrame"><i class="fa fa-fw fa-dropbox"></i> 个人中心</a>
                </li>
                <li>
                    <a href="/bishe/user/loginout"><i class="fa fa-fw fa-bank"></i> 退出</a>
                </li>
    <!--             <li>
                    <a href="#"><i class="fa fa-fw fa-dropbox"></i> Page 5</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-fw fa-twitter"></i> Last page</a>
                </li> -->
            </ul>
        </nav>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->

    <!-- <iframe id="frame" src="login.html"></iframe> -->
      <!-- 嵌套网页开始 -->   
      <div  style="text-align:center;"> 
      <h3> 银行身份认证系统 </h3>
         <iframe src="main.jsp"   frameborder="0" name="mainFrame" width="100%" height="575"></iframe>
      </div> 
             
                <!-- 嵌套网页结束 -->   

	
	<!-- <script src="../js/jquery-3.3.1.min.js" type="text/javascript"></script> -->
	<!-- <script>window.jQuery || document.write('<script src="js/jquery-3.3.1.min.js"><\/script>')</script> -->
<!-- 	<script src='../js/bootstrap.min.js'></script> -->

</body>
</html>