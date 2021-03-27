<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
    <style>
        #mytable{
            padding-top:30px;
        }
    </style>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    </head>
    <body>

    <div class="container" id="mytable">
 <div class="row" style="margin-bottom:10px;">
             <div class="col-md-8"><h4><strong>我的银行卡</strong></h4></div>
             <div class="col-md-4"><a  href="/bishe/bank/getAllBank"class="btn btn-info btn-xs" type="button">申请新卡</a></div>
        </div>
        <!-- <div class="table-responsive"> -->
        <table class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th>#</th>
                    <th>卡号</th>
                    <th>账户余额(/元)</th>
                    <th>账号人</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            <%-- ${listCards } --%>
            <c:forEach items="${listCards }" varStatus="sta" var="item">
                <tr>
                
                    <td>${sta.count }</td>
                    <td>${item.card_num }</td>
                    <td>${item.money }</td>
                    <td>${item.u_name }</td>
                    <td>
                    <a  href="/bishe/home/transform.jsp?card_id=${item.card_id }"class="btn btn-primary btn-xs" type="submit">转账</a>
                    <a  href="/bishe/home/storeMoney.jsp?card_id=${item.card_id }"  class="btn btn-primary btn-xs" type="submit">模拟存款</a>
                    <a  href="/bishe/home/takeMoney.jsp?card_id=${item.card_id }" class="btn btn-primary btn-xs" type="submit">模拟取款</a>
                    <a  href="/bishe/home/editPayPwd.jsp?card_id=${item.card_id }" class="btn btn-primary btn-xs" type="submit">修改支付密码</a>
                    </td>
                </tr>
                </c:forEach>
                
            </tbody>
        </table>
    </div>

    </body>
</html>