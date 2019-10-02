<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>-.-</title>
<link rel="stylesheet" href="../css/backstage.css">
</head>

<body>
<div id="showDetail"  style="display:none;">

</div>
<div class="details">
                    <div class="details_operation clearfix">
                        <div class="bui_select">
                                   <!-- <a href="/bishe/admin/addBank.jsp" target="mainFrame"><input type="button" value="添&nbsp;&nbsp;加" class="add" ></a> -->
                        </div>
                    <!--表格-->
                     <table class="table" cellspacing="0" cellpadding="0" style="margin-top:40px;">
                        <thead>
                            <tr>
                                <th width="10%">编号</th>
                                <th width="20%">用户名</th>
         						<th width="15%">邮箱</th>
                                <th width="15%">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                       <c:forEach items="${listUser }" varStatus="sta" var="item">
                            <tr>
                                <!--这里的id和for里面的c1 需要循环出来-->
                                <td align="center"><label for="c1" class="label">${sta.count }</label></td>
                                <td align="center">${item.username }</td>
								<td align="center">${item.email }</td>
                            <td align="center">
                          <%--   <a href="vtype-editform.action?type_id=${type_id }" target="mainFrame">
                            <input type="button" value="修改" class="btn"/></a> --%>
                            <a onclick="deleteUser(${item.uid },'${item.username }')" target="mainFrame">
                            <input type="button" value="删除" class="btn"/></a></td>
                          
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                       <script>
		function deleteUser(uid,username){
        var r = confirm("你确定要删除该用户吗？");
        var admin_name = "${admin.admin_name}";
        var aid = "${admin.aid}";
        if(r == true){
        	window.location.href="/bishe/user/deleteUser?uid="+uid+"&&username="+username+"&&aid="+aid+"&&admin_name="+admin_name; 
        }}
    </script>             
</body>
</html>


