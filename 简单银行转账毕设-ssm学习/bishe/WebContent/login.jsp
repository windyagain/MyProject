<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/bishe/css/bootstrap.min.css">
<title>Insert title here</title>
<style>
#main{
    margin:0 auto;
    padding:0;
    background:url('/bishe/img/01.jpg') no-repeat;
    background-size: 100% 100%;
    background-position: center 0;
    width:1348px;
    height:653px;
}
.mycontainer{
    height:280px;
    width:350px;
    background:rgba(10,10,10,0.4);
    position:absolute;
    top:150px;
    left:50%;
    color:white;
    padding:10px;
}
#myform{
    color:white;
    padding:10px 5px;
}

</style>
</head>
<body>

<div id="main">
<spring:htmlEscape defaultHtmlEscape="true" />
    <div class="mycontainer">
<!--         <button type="button" class="btn btn-default">default</button>
        <button type="button" class="btn btn-primary">primary</button>

        <div class="div">
            <button type="button" class="btn btn-info btn-lg" >info</button>
            <button type="button" class="btn btn-info" >info</button>
            <button type="button" class="btn btn-info btn-sm" >info</button>
            <button type="button" class="btn btn-info btn-xs" >info</button>
        </div> -->
        <div style="margin-bottom:30px;" >
        <label style="padding:5px 6px;font-size:24px;margin-left:138px;" href="./register.jsp" id="login">登陆</label>
      
        </div>
  
    <form class="form-horizontal" id="myform" method="post" action="/bishe/user/login" input path="name" htmlEscape="true">
      <div class="form-group"><%-- 欢迎您，${user.username} --%>
        <label for="inputEmail3" class="col-sm-2 control-label">用户</label>
        <div class="col-sm-10">
          <input type="text" name="username" class="form-control" id="inputEmail3" placeholder="请输入用户名">
        </div>
      </div>
      <div class="form-group">
        <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
        <div class="col-sm-10">
          <input type="password" name="password" class="form-control" id="inputPassword3" placeholder="请输入密码">
        </div>
      </div>

      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-default">登陆</button>
          <a type="button" style="margin-left:20px;" href="./register.jsp" class="btn btn-info btn-xs">去注册</a>
        </div>
      </div>
    </form>
    </div>
          <p style="font-size:28px;color:black;">
          <!-- 显示错误信息 -->
<c:if test="${fieldError!=null }">
 ${fieldError.username}
</c:if>

          </p>
     <script src="/bishe/js/jquery-3.3.1.min.js"></script>
     <script src="/bishe/js/bootstrap.min.js"></script>

<script>
$(document).ready(function(){
//   $("#login").click(function(){
//     $("#myform2").hide();
//     $("#myform").show();
//   });
//     $("#reg").click(function(){
//     $("#myform").hide();
//     $("#myform2").show();
//   });
 });
</script>
</div>

</body>
</html>