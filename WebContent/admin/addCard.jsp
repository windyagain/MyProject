<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>-_-</title>
</head>
<body>
<h3>添加银行卡</h3>
<form action="/bishe/card/adInsertCard" method="post">
<table width="70%" border="1" cellpadding="5" cellspacing="0" bgcolor="#cccccc">
	<tr>
		<td align="right">开卡人</td>
		<td><input type="text" name="u_name" /></td>
	</tr>
	
	<tr>
		<td align="right">归属账户选择</td>
		<td>
		<select  name="uid">
       	<c:forEach items="${listUser }" varStatus="sta" var="item">
     	 <option value="${item.uid }">${item.username }</option>
      	</c:forEach>
   		 </select>		
		</td>
	</tr>
	
		<tr>
		<td align="right">请输入支付密码</td>
		<td><input type="password" name="pay_pwd" /></td>
	</tr>
		<tr>
		<td align="right">请确认支付密码</td>
		<td><input type="password" name="old_pay_pwd" /></td>
	</tr>
	
	<tr>
	<td align="right">银行选择</td>
	<td>
	<select  name="bank_id">
       <c:forEach items="${listBanks }" varStatus="sta" var="item">
      <option value="${item.bank_id }">${item.bank_name }</option>
      </c:forEach>
    </select>
	</td>
	</tr>
	
	
	<tr>
		<td colspan="2"><input type="submit"  value="添加"/></td>
	</tr>
		<input type="hidden" name="aid" value="${admin.aid }" />
		<input type="hidden" name="admin_name" value="${admin.admin_name }">
</table>
</form>
</body>
</html>
