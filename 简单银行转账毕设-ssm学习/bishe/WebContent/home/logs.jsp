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
             <!-- <div class="col-md-4"><a  href="/bishe/bank/getAllBank"class="btn btn-info btn-xs" type="button">申请新卡</a></div> -->
        </div>
        <!-- <div class="table-responsive"> -->
        <table class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th>#</th>
                    <th>日志内容</th>
                    <th>时间</th>
                    <th>操作者</th>
                    
                </tr>
            </thead>
            <tbody>
            <%-- ${listCards } --%>
            <c:forEach items="${listLog }" varStatus="sta" var="item">
                <tr>
                    <td>${sta.count }</td>
                    <td>${item.content }</td>
                    <td>${item.time }</td>
                    <td>
                    <button  class="btn btn-danger btn-xs" data-whatever="/bishe/log/deleteLogByLogId?log_id=${item.log_id }" data-toggle="modal" data-target="#myModal" type="button">删除</button>
                    </td>
                </tr>
                </c:forEach>
                
            </tbody>
        </table>
    </div>
    <div class="modal fade" tabindex="-1" id="myModal" role="dialog" aria-labelledby="gridSystemModalLabel">
  <div class="modal-dialog modal-sm" role="document" >
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">消息提示</h4>
      </div>
      <div class="modal-body">
         <div class="row">
         <div class="col-sm-9"> 你确定要删除吗？</div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-sm btn-primary" id="surebutton">确定</button>
      </div>
    </div>
  </div>
</div>

    <script src="../js/jquery-3.3.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script>
    var url="";
    $('#myModal').on('show.bs.modal',function (event) {
    	  var button = $(event.relatedTarget);
    	  url = button.data('whatever');  
    });  
    $("#surebutton").click(function(){
    window.location.href=url;
    //href="/bishe/log/deleteLogByLogId?log_id=${item.log_id }"
  });
    </script>
    </body>
</html>

