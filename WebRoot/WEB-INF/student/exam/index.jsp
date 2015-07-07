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
      <link rel="stylesheet" href="<%=basePath%>inc/css/student.css">
   <script src="<%=basePath%>inc/js/jquery-1.11.2.min.js"></script>
   <script src="<%=basePath%>inc/js/bootstrap.min.js"></script>
      <script src="<%=basePath%>inc/js/form.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
     <div class="container-full">
      <h2 style="text-align:center;">选择课程（模拟考试）</h2>
      <div class="my_course_box midle_bg row">
      <s:iterator value="courses" var="c">
         <div class="inline_two mb30 col-sm-4">
				<a  href="course!courseIndex?courseId=<s:property  value="#c.id" /> &moth=1"><img width="230" height="150" src="<%=basePath%>inc/image/eg_1.png" class="mr10 bd3cc l"></a>
                <div class="cell g9">
                    <div class=" g5 f16"><a  href="course!courseIndex?courseId=<s:property  value="#c.id" /> &moth=1"><s:property  value="#c.titleString" /></a></div>
                    <div class="mt5">授课老师：<s:property  value="#c.teacherUser.userCommon.tname" /></div>
                    <div class="mt5">考核方式：<s:if test="#c.test == 0">考试</s:if><s:else>考查</s:else></div>
                    <%-- <div class="mt5">开课专业：<s:iterator value="#c.majors" var="c2"><s:property  value="#c2.nameString" />|</s:iterator></div> --%>
                </div>
        </div>
       </s:iterator> 
      </div>
      </div>
  </body>
