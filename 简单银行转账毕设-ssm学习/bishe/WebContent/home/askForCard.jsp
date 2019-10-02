<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
         <link rel="stylesheet" href="../css/bootstrap.min.css">  
    </head>
    <body>
 <%--   id= ${user.uid}--===${listBanks }  ==>  --%>
<div style="width:70%;height:400px;margin-top:40px;">
<form class="form-horizontal" method="post" action="/bishe/card/askForCard">
  <div class="form-group" >
    <label for="inputEmail3" class="col-sm-2 control-label">开卡人</label>
    <div class="col-sm-10">
      <input type="text" name="u_name" class="form-control" id="inputEmail3" placeholder="请输入开卡人姓名">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">请输入支付密码</label>
    <div class="col-sm-10">
      <input type="password" name="pay_pwd" class="form-control" id="inputPassword3" placeholder="请输入支付密码">
    </div>
  </div>
  
    <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">请确认支付密码</label>
    <div class="col-sm-10">
      <input type="password" name="old_pay_pwd" class="form-control" id="inputPassword3" placeholder="请输入支付密码">
    </div>
  </div>
  
    <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">银行</label>
    <div class="col-sm-10">
       <select class="form-control" name="bank_id">
       <c:forEach items="${listBanks }" varStatus="sta" var="item">
      <option value="${item.bank_id }">${item.bank_name }</option>
      </c:forEach>
    	</select>
    </div>
  </div><%-- ${user.uid} --%>
<input type="hidden" name="uid" value="${user.uid}">
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">开卡</button>
    </div>
  </div>
</form>
</div>
    </body>
</html>