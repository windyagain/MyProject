<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
    </head>
      <link rel="stylesheet" href="../css/bootstrap.min.css">  
      <!-- <link rel="stylesheet" href="../css/style.css"> -->
    <body>

<%  
    String textContent = request.getParameter("card_id");  
%>  
<div style="width:70%;height:400px;margin-top:40px;">
<form class="form-horizontal" method="post" action="/bishe/card/storeMoney">

  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">金额</label>
    <div class="col-sm-10">
          <div class="input-group">
      <div class="input-group-addon">$</div>
      <input type="text" name="money" class="form-control" id="exampleInputAmount" placeholder="请输入存款金额">
      <div class="input-group-addon">.00</div>
    </div>
    </div>
  </div>
<!-- <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">密码</label>
    <div class="col-sm-10">
    <input type="password" name="pay_pwd" class="form-control" id="exampleInputPassword1" placeholder="请输入存款密码">
  </div>
</div> -->
<!--   <div class="form-group">
    <label class="sr-only" for="exampleInputAmount">Amount (in dollars)</label>
    <div class="input-group">
      <div class="input-group-addon">$</div>
      <input type="text" class="form-control" id="exampleInputAmount" placeholder="Amount">
      <div class="input-group-addon">.00</div>
    </div>
  </div> -->
  
<input type="hidden" name="card_id" value="<%=textContent%>">
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">存款</button>
    </div>
  </div>
</form>
</div>

    </body>
</html>