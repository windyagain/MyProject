<template>
<div id="login">
    
<form class="loginForm">
	<h1 class="col-sm-offset-1" style="font-size:18px;">用户登陆</h1>
	<hr style="margin-top:15px;margin-bottom:15px;"></hr>
	
  <div class="form-group">
    <label for="exampleInputEmail1">用户名称</label>
    <input type="email" class="form-control" id="exampleInputEmail1" placeholder="请输入管理员名称">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">用户密码</label>
    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="请输入管理员密码">
  </div>
  <div class="checkbox">
    <label>
      <input type="checkbox"> 记住密码
    </label>
  </div>
  <button type="submit" class="btn btn-info btn-block" @click="dologin">登录</button>
   <div class="form-group" style="margin-top:10px">
        <label class="col-md-8"><a href="#" class="mylink">忘记密码？</a></label>
		<label class="col-md-4"><a href="#" class="mylink">免费注册 </a></label>
  </div>
</form>
</div>
</template>



<script>
//测试jquery是否安装成功
// $(function () { 
//     alert(123); 
//   });
export default {
    methods:{
		dologin(){
			this.$router.push('/index');
		}
	}
}
</script>
<style lang="scss" scoped>
#login{
	width: 100%;
	height: 100%;
	background: url('../assets/images/03.jpg') ;
	background-size:cover;
	overflow:hidden;
	.loginForm{
	width: 320px;
	
	background-color: rgba(0, 0, 0, 0.5);
	padding: 10px 25px 30px 25px;
	position: relative;
	margin-left: 860px;
	margin-top: 160px;
	color: #fff;
	.mylink{
		text-decoration: none;
		color:#fff;
	}
}
}
 
</style>
