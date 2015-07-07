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
    <title>湖南科技大学在线学习系统</title>
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
 <script SRC="dwr/engine.js"></script>
<script SRC="dwr/util.js"></script>
<script SRC="dwr/interface/userPass.js"></script>
  </head>
  
  <body>
  <s:if test="#session.user.rule==1">
       <s:action name="user!getStudent" namespace="/" var="me">  </s:action>
        <table class="table info_table">
           <tbody>
              <tr><td class="info_title">学号</td><td class="info_ge">:</td><td class="info_value"><s:property value="#session.user.uid" /></td>
              <td class="info_action"><a data-toggle="modal" data-target="#password">修改密码</a>
              <td align="center" class="info_avatar" rowspan="9">
              <a class="modal-entry" data-modal="upload-avatar" href="javascript:void(0)" id="change-avatar-imglink">
              <img id="avatar_bg" src="<%=basePath%>inc/image/avatar.jpg"></a>
              <br>
<a id="change-avatar-textlink" class="modal-entry" href="javascript:void(0)">更换头像</a></td>
              </tr>
               <tr><td class="info_title">昵称</td><td  class="info_ge">:</td><td class="info_value"><s:property value="#session.user.uname" /></td></tr>
                <tr><td class="info_title">姓名</td><td  class="info_ge">:</td><td class="info_value"><s:property value="#session.user.tname" /></td></tr>
                <tr> <td class="info_title">邮箱</td><td  class="info_ge">:</td><td class="info_value"><s:property value="#session.user.mail" /></td></tr>
                  <tr> <td class="info_title">用户类型</td  ><td class="info_ge">:</td><td class="info_value">学生用户</td></tr>
                    <tr>  <td class="info_title">专业</td><td  class="info_ge">:</td><td class="info_value"><s:property value="#me.studentUser.major.nameString" /></td></tr>
                      <tr>   <td class="info_title">班级</td><td  class="info_ge">:</td><td class="info_value"><s:property value="#me.studentUser.classes.name" /></td></tr>
                         <tr>   <td class="info_title">上次登录</td><td  class="info_ge">:</td><td class="info_value"><s:date name="#session.user.loginTime" format="yyyy-MM-dd HH:mm:ss" /></td></tr>
                            <tr>   <td class="info_title">积分</td><td  class="info_ge">:</td><td class="info_value"><s:property value="#me.studentUser.credit" /></td></tr>
           </tbody>
        </table>
        </s:if>
        <s:elseif test="#session.user.rule==2">
        <table>
           <tbody>
              <tr><td>工号</td><td>:</td><td><s:property value="#session.user.uid"  /></td></tr>
               <tr><td>昵称</td><td>:</td><td><s:property value="#session.user.uname" /></td></tr>
                 <tr><td>姓名</td><td>:</td><td><s:property value="#session.user.tname" /></td></tr>
                 <tr> <td>邮箱</td><td>:</td><td><s:property value="#session.user.mail" /></td></tr>
                  <tr> <td>用户类型</td><td>:</td><td>教师用户</td> </tr>
           </tbody>
        </table>
        </s:elseif>
         <div class="info_banner"><img src="<%=basePath%>inc/image/banner.png" title="广告位" /></div>
        <!-- 修改密码 -->
        <div class="modal fade" id="password" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog pass-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">修改密码</h4>
      </div>
      <div class="modal-body">
          <form action="" method="post" role="form" >
       <div class="form-group">
      <input name="oldpwd" id="pwd" class="form-control" type="password" placeholder="旧密码" check-type="required" required-message="请填写原密码" minlength="6">
      </div>
       <div class="form-group">
      <input name="newpwd" id="newpwd" class="form-control"  type="password" placeholder="新密码"  check-type="required" minlength="6" required-message="请填写新密码"/>
      </div>
       <div class="form-group" id="ne">
      <input name="newpwd2" id="newpwd2" class="form-control"  type="password" placeholder="再次输入新密码"  check-type="same" minlength="6" required-message="密码验证不正确"/>
      </div>
      <div class="form-group submitBtn">
      <div class="col"><a class="btn btn-primary2" href="javascript:void(0);" id="passbtn">修改密码</a>
      <span id="validerrmsg" class="help-block" style="color: #FF0000;"></span>
      </div></div>
      </form>
       <script type="text/javascript">
 $(function(){
   //1. 简单写法：
   $("form").validation();
   $("#passbtn").on('click',function(event){
     // 2.最后要调用 valid()方法。
     if ($("form").valid(this,"error!")==false){
       //$("#error-text").text("error!"); 1.0.4版本已将提示直接内置掉，简化前端。
       return false;
     }
     else if($("#newpwd").val() != $("#newpwd2").val()){
       $(".col").after( '<span id="validerrmsg" class="help-block" style="color: #FF0000;">两次密码输入不统一</span>');
       return false;
     }
     else
    {
          var $btn = $("#passbtn").button('loading')
           
           $btn.button('reset')
    	userPass.changePass($("#pwd").val(),$("#newpwd").val(),function(result){
                    if(result){
                       $(".col").html( '<span id="validerrmsg" class="help-block" style="color: #FF0000;">修改成功，请关闭窗口！</span>');
                   }else{
                       $(".col").after( '<span id="validerrmsg" class="help-block" style="color: #FF0000;">原密码输入错误</span>');
                    }
			});
    }
   })  })
</script>
      </div>
     
      <div class="modal-footer">
      </div>
    </div>
  </div>
</div>
  </body>
</html>
