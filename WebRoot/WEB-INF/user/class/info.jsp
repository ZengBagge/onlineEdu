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
       <s:action name="class!getClassesByUserCommon" namespace="/" var="c">  </s:action>
        <table class="table info_table">
           <tbody>
                 <tr><td class="info_title">班级代码</td><td  class="info_ge">:</td><td class="info_value"><s:property value="#c.classes.classUid" /></td></tr>
               <tr><td class="info_title">班级名称</td><td  class="info_ge">:</td><td class="info_value"><s:property value="#c.classes.name" /></td></tr>
               <tr><td class="info_title">所属专业</td><td  class="info_ge">:</td><td class="info_value"><s:property value="#c.classes.major.nameString" /></td></tr>
                <tr><td class="info_title">班长</td><td  class="info_ge">:</td><td class="info_value"><s:property value="#c.classes.monitor.userCommon.tname" /></td><s:if test="#session.user.id == #c.classes.monitor.userCommon.id"><td><a href="">修改班级信息</a></td></s:if></tr>
           </tbody>
        </table>
        </s:if>
      
         <div class="info_banner"><img src="<%=basePath%>inc/image/banner.png" title="广告位" /></div>

  </body>
</html>
