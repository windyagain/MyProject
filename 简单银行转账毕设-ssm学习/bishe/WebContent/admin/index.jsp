<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
          <%@ page import="com.model.Admin" %>
<%-- <% 
   		if(admin ==null)
	   response.sendRedirect("login.jsp");
 %>
 --%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>-.-</title>
<link rel="stylesheet" href="../css/backstage.css">
</head>
    <% 
Admin admin = (Admin)session.getAttribute("admin");
if(null == admin)
{
    response.sendRedirect("/bishe/admin/login.jsp");
}
            
%>
<body>
    <div class="head">
           <!--  <div class="logo fl"><a href="#"></a></div> -->
            <h3 class="head_text fr">银行身份认证系统后台管理系统</h3>
    </div>
    <div class="operation_user clearfix">
       <!--   <div class="link fl"><a href="#">慕课</a><span>&gt;&gt;</span><a href="#">商品管理</a><span>&gt;&gt;</span>商品修改</div>-->
        <div class="link fr">
            <b>欢迎您  ,${admin.admin_name}

            
            </b>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/bishe/admin/index.jsp" class="icon icon_i">首页</a><span></span><a href="#" class="icon icon_j">前进</a><span></span><a href="#" class="icon icon_t">后退</a><span></span><a href="#" class="icon icon_n">刷新</a><span></span><a href="/bishe/admin/loginout" class="icon icon_e">退出</a>
        </div>
    </div>
    <div class="content clearfix">
        <div class="main">
            <!--右侧内容-->
            <div class="cont">
                <div class="title">后台管理</div>
                <!-- 嵌套网页开始 -->         
                <iframe src="main.jsp"  frameborder="0" name="mainFrame" width="100%" height="522"></iframe>
                <!-- 嵌套网页结束 -->   
            </div>
        </div>
        <!--左侧列表-->
        <div class="menu">
            <div class="cont">
                <div class="title">管理员</div>
                <ul class="mList">
                    <li>
                        <h3 onclick="show('menu1','change1')" ><span  id="change1">+</span>银行卡管理</h3>
                        <dl id="menu1" style="display:none;">
                            <dd><a href="/bishe/card/adInsertCardForm" target="mainFrame">添加银行卡</a></dd>
                            <dd><a href="/bishe/card/adGetAllCard" target="mainFrame">银行卡列表</a></dd>
                        </dl>
                    </li>
                    <li>
                        <h3  onclick="show('menu2','change2')" ><span id="change2">+</span>银行管理</h3>
                        <dl id="menu2" style="display:none;">
                            <dd><a href="/bishe/admin/addBank.jsp" target="mainFrame">添加银行</a></dd>
                            <dd><a href="/bishe/bank/adGetAllBank" target="mainFrame">银行列表</a></dd>
                        </dl>
                    </li>
                    <li>
                        <h3  onclick="show('menu3','change3')"><span  id="change3" >+</span>日志管理</h3>
                        <dl id="menu3" style="display:none;">
                            <dd><a href="/bishe/log/getLogs" target="mainFrame">日志列表</a></dd>
                            
                        </dl>
                    </li>
                    <li>
                        <h3 onclick="show('menu4','change4')" ><span  id="change4">+</span>用户管理</h3>
                        <dl id="menu4" style="display:none;">
                            <dd><a href="/bishe/admin/addUser.jsp" target="mainFrame">添加用户</a></dd>
                            <dd><a href="/bishe/user/findAllUser" target="mainFrame">用户列表</a></dd>
                            <dd><a href="/bishe/admin/updateAdminPwd.jsp" target="mainFrame">修改用户登陆密码</a></dd>
                        </dl>
                    </li>
<!--                     <li>
                        <h3 onclick="show('menu5','change5')"><span  id="change5">+</span>管理员管理</h3>
                        <dl id="menu5" style="display:none;">
                            <dd><a href="addAdmin.php" target="mainFrame">添加管理员</a></dd>
                            <dd><a href="listAdmin.php" target="mainFrame">管理员列表</a></dd>
                        </dl>
                    </li> -->
                    
<!--                          <li>
                        <h3 onclick="show('menu6','change6')" ><span  id="change6">+</span>商品图片管理</h3>
                        <dl id="menu6" style="display:none;">
                            <dd><a href="listProImages.php" target="mainFrame">商品图片列表</a></dd>
                        </dl>
                    </li> -->
                </ul>
            </div>
        </div>

    </div>
    <script type="text/javascript">
        function show(num,change){
                var menu=document.getElementById(num);
                var change=document.getElementById(change);
                if(change.innerHTML=="+"){
                        change.innerHTML="-";
                }else{
                        change.innerHTML="+";
                }
               if(menu.style.display=='none'){
                     menu.style.display='';
                }else{
                     menu.style.display='none';
                }
        }
    </script>
</body>
</html>

