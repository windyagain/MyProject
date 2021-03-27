<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>-_-</title>
</head>
<body>
<h3>修改管理员密码</h3>
<form action="/bishe/admin/updateAdminPwd" method="post">
<table width="70%" border="1" cellpadding="5" cellspacing="0" bgcolor="#cccccc">
	<tr>
		<td align="right">原始密码</td>
		<td><input type="text" name="old_admin_pwd" /></td>
		
	</tr>
	<tr>
		<td align="right">新密码</td>
		<td><input type="text" name="admin_pwd" /></td>
		
	</tr>
	<tr>
		<td colspan="2"><input type="submit"  value="修改"/></td>
	</tr>
		<input type="hidden" name="aid" value="${admin.aid }" />
		<input type="hidden" name="admin_name" value="${admin.admin_name }">
</table>
</form>
</body>
</html>