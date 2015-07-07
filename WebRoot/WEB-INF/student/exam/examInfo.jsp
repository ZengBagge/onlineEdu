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
              <div class="container" id="logo">
	<div class="row">
		<div class="col-lg-8 col-lg-push-2">
		    <h1 class="blog-title">
		    	<a ><s:property  value="exam.titleString" /></a>
		    </h1>
		    <h2 class="blog-desc">
		    	 <p>任务起止时间：<s:date name="exam.startDate" format="yyyy-MM-dd HH:mm:ss" />—<s:date name="exam.endDate" format="yyyy-MM-dd HH:mm:ss" /></p>
		    	 <p>任务所属专业：<s:iterator value="exam.majors" var="c2"><s:property  value="#c2.nameString" />|</s:iterator></p>
		    	 <p>任务发布教师：<s:property  value="exam.teacherUser.userCommon.tname" /></p>
		    </h2>
		    <div class="social mt30">
			    <a  target="_blank" title="异形网络教育|开始任务" class="btn btn-outline btn-lg" href="exam!conduct?examId=<s:property  value="exam.id" />&token=<s:property  value="token" />">开始任务</a>
			   <!--  <a   title="异形网络教育|开始任务" class="btn btn-outline btn-lg" > 问答社区</a> -->
		</div>
		</div>
    </div>
</div>


  <div class="banner container"><h2>学海无涯苦作舟</h2></div>
 
  </body>
</html>
