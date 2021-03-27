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
<!-- <link rel="stylesheet" href="../css/bootstrap.min.css"> -->
<style>
/* .btn{
background:#E8E8E8;
color:black;
border:0px;
}
.btn:hover{
background:#E8E8E8;color:black;
} */

</style>
</head>

<body>
<div id="showDetail"  style="display:none;">

</div>
<div class="details">
                    <div class="details_operation clearfix">
                        <div class="bui_select">
                                   <a href="/bishe/admin/addBank.jsp" target="mainFrame"><input type="button" value="添&nbsp;&nbsp;加" class="add" ></a>
                        </div>
                    <!--表格-->
                     <table class="table" cellspacing="0" cellpadding="0" style="margin-top:40px;">
                        <thead>
                            <tr>
                                <th width="20%">编号</th>
                                <th width="20%">银行名称</th>
         
                                <th width="30%">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                       <c:forEach items="${listBanks }" varStatus="sta" var="item">
                            <tr>
                                <!--这里的id和for里面的c1 需要循环出来-->
                                <td align="center"><label for="c1" class="label">${sta.count }</label></td>
                                <td align="center">${item.bank_name }</td>

                            <td align="center">
                            <%-- <a href="vtype-editform.action?type_id=${type_id }" target="mainFrame">
                            <input type="button" value="修改" class="btn"/></a> --%>
                            <a href="#" onclick="deleteBank(${item.bank_id})" 
                            target="mainFrame">
                            <input type="button" value="删除" class="btn"></a></td>
                            <!-- href="/bishe/bank/delBank?bank_id=${item.bank_id }" -->
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                    
<!--     <script src="../js/jquery-3.3.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script> -->
    <script>
   // href="/bishe/bank/delBank?bank_id=${item.bank_id }&&aid=${admin.aid}&&admin_name=${admin.admin_name}"
		function deleteBank(bank_id){
        var r = confirm("你确定要删除吗？");
        var admin_name = "${admin.admin_name}";
        var admin_id = "${admin.aid}";
        if(r == true){
        	window.location.href="/bishe/bank/delBank?bank_id="+bank_id+"&&aid="+admin_id+"&&admin_name="+admin_name; 
        }
   		}
    </script>
</body>
</html>