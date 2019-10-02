<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>登陆</title>
<link type="text/css" rel="stylesheet" href="../css/reset.css">
<link type="text/css" rel="stylesheet" href="../css/main.css">
<!--[if IE 6]>
<script type="text/javascript" src="../js/DD_belatedPNG_0.0.8a-min.js"></script>
<script type="text/javascript" src="../js/ie6Fixpng.js"></script>
<![endif]-->
</head>

<body>
<div class="headerBar">
    <div class="logoBar login_logo">
        <div class="comWidth">
<!--             <div class="logo fl">
    <a href="#"><img src="images/logo.jpg" alt="慕课网"></a>
</div> -->
            <h3 class="welcome_title">欢迎登陆银行身份认证系统后台管理平台</h3>
        </div>
    </div>
</div>

<div class="loginBox">
    <div class="login_cont">
    <form action="/bishe/admin/login" method="post">
            <ul class="login">
                <li class="l_tit">管理员帐号</li>
                <li class="mb_10"><input type="text"  name="admin_name" placeholder="请输入管理员帐号"class="login_input user_icon"></li>
                <li class="l_tit">密码</li>
                <li class="mb_10"><input type="password"  name="admin_pwd" class="login_input password_icon"></li>
<!--                 <li class="l_tit">验证码</li>
<li class="mb_10"><input type="text"  name="verify" class="login_input password_icon"></li> -->
                <img src="getVerify.php" alt="" />
    <!--             <li class="autoLogin"><input type="checkbox" id="a1" class="checked" name="autoFlag" value="1"><label for="a1">自动登陆(一周内自动登陆)</label></li> -->
                <li><input type="submit" value="" class="login_btn"></li>
            </ul>
        </form>
    </div>
</div>

<div class="hr_25"></div>
<!-- <div class="footer">
    <p>版权归本人所有</p>
</div> -->
</body>
</html>
