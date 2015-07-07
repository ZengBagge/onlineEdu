<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@  taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>湖南科技大学在线学习系统|用户登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=basePath%>inc/css/bootstrap.min.css">
   <link rel="stylesheet" href="<%=basePath%>inc/css/bootstrap-theme.min.css">
   <link rel="stylesheet" href="<%=basePath%>inc/css/style.css">
   <script src="<%=basePath%>inc/js/jquery-1.11.2.min.js"></script>
   <script src="<%=basePath%>inc/js/bootstrap.min.js"></script>
      <script src="<%=basePath%>inc/js/form.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script>
       
    </script>
  </head>
  
  <body>
  <header>
     <div class="container"><div class="logo"><font>湖南科技大学在线学习系统</font></div></div>
  </header>
    <div class="container login">
 <div class="login_title"><h2>用户注册</h2></div>
       <div class="login_form">
      <form action="user!zhuCe" method="post"role="form" >
              <div class="form-group">
              <select id="s1_text1_bold" name="rule" placeholder="帐号类型" >
                                    <option value="1" selected="">学生用户</option>
                                    <option value="2">教师用户</option>
                  </select>
              </div>
            <div class="form-group">
      <input name="uid" class="form-control" type="text" placeholder="学号或者工号" check-type="number" required-message="请填写你的学号或工号">
            </div>
            <div class="form-group">
          <input name="uname" value=""  placeholder="昵称"  type="text"check-type="required" required-message="请填写你的称呼" />
          </div>
          <div class="form-group">
           <input name="tname" value=""  placeholder="真实姓名"  type="text" check-type="required" required-message="请填写你的姓名"/>
           </div>
           <div class="form-group">
            <input name="mail" value=""  placeholder="邮箱"  type="text"  check-type="mail" required-message="邮箱格式不正确"/>
            </div>
             <div class="form-group">
             <input name="pwd" value=""  placeholder="密码"  type="password" check-type="required" required-message="请填写你的密码"/>
             </div>
             <div class="form-group" id="pwd2">
             <input name="pwd2" value="" placeholder="确认密码" type="password" check-type="required" required-message="请正确填写你的密码"/>
             </div>
             
<div class="form-group submitBtn">
      <div class="col"><a class="btn btn-primary2" data-lang="common.label.login" href="javascript:void(0);" id="loginbtn" style="background:#359bdc;">注册</a>
      <span id="validerrmsg" class="help-block" style="color: #FF0000;"><s:property  value="errorMesageString" /></span>
      </div></div>
      </form>
      <script type="text/javascript">
 $(function(){
   //1. 简单写法：
   $("form").validation();
   $("#loginbtn").on('click',function(event){
     // 2.最后要调用 valid()方法。
     if ($("form").valid(this,"error!")==false){
       //$("#error-text").text("error!"); 1.0.4版本已将提示直接内置掉，简化前端。
       return false;
     }else if($("input[name='pwd']").val() != $("input[name='pwd2']").val()){
    	 $("#pwd2").removeClass("has-success").addClass("has-error").find(".help-block").html("密码不一致");
    	 return false;
     }
     else
     $("form").submit();
   });  });
</script>
      </div>
      </div>
      <div class="footer container-full">
         <div class="container"><h5>copyright 2015 <a hef="http://yxweb.cc">异形网络工作室</a></h5></div>
     </div>
  </body>
</html>
