<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" /> 
<title>消息提示</title> 
<script type="text/javascript">
window.onload=function(){
  var myspan=document.getElementById("myspan");
  var url=document.getElementById("url");
  console.log(url.value);
  var timer=3;
  var flag;
  function daoji(){
     timer=timer-1;
     myspan.innerHTML=timer;
     if(timer==0){
       location.href="${url}";
       location.target="mainFrame";
       clearInterval(flag);
     }
  }
  flag=setInterval(daoji,1000);
}
</script> 
</head>
<body> 
<div>${mes}  剩余 <span id="myspan">3</span>跳转</div>
<input type="hidden" id="url" value="${url}">
<%-- ${url} --%>
</body> 
</html>