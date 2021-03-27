<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>-_-</title>
</head>
<body>
<h3>添加用户</h3>
<form action="/bishe/user/adInsertUser" method="post">
<table width="70%" border="1" cellpadding="5" cellspacing="0" bgcolor="#cccccc">
	<tr>
		<td align="right">用户名称</td>
		<td><input type="text" name="username" /></td>
		
	</tr>
		<tr>
		<td align="right">用户密码</td>
		<td><input type="password" name="password" /></td>
		
	</tr>
			<tr>
		<td align="right">确认用户密码</td>
		<td><input type="password" name="checkPwd" /></td>
		
	</tr>
		<tr>
		<td align="right">用户邮箱</td>
		<td><input type="email" name="email" /></td>
		
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