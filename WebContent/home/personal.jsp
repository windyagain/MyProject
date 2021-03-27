<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
         <link rel="stylesheet" href="../css/bootstrap.min.css">  
    </head>
    <body>
   <%-- id= ${user.uid} --%>
<div style="width:70%;height:400px;margin-top:40px;">
<form class="form-horizontal" method="post" enctype="multipart/form-data" action="/bishe/user/editUser">
  <div class="form-group" >
    <label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
    <div class="col-sm-10">
      <input type="text" value="${user.username}" name="u_name" class="form-control" id="inputEmail3" placeholder="" disabled>
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">用户邮箱</label>
    <div class="col-sm-10">
      <input type="text" name="pay_pwd" value="${user.email }" class="form-control" id="inputPassword3" disabled>
    </div>
  </div>
  
    <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">头像</label>
    <div class="col-sm-10">
      
<%-- <c:if test="${user ==null}">
			<img src="/pic/${itemsCustom.pic}" width=100 height=100/>
			<br/>
		</c:if> --%>
		<input type="file"  name="pictureFile"/>

    </div>
  </div>
    <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">修改</button>
    </div>
  </div>
</form>
</div>
    </body>
</html>