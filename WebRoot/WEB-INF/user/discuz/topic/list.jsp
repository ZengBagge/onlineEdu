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
	<meta http-equiv="keywords" content="异形网络,网络教育,高等教育">
	<meta http-equiv="description" content="用户交流页面">
	<link rel="stylesheet" href="<%=basePath%>inc/css/bootstrap.min.css">
   <link rel="stylesheet" href="<%=basePath%>inc/css/bootstrap-theme.min.css">
      <link rel="stylesheet" href="<%=basePath%>inc/css/student.css">
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
		<script SRC="dwr/interface/discuz.js"></script>
		 <script src="<%=basePath%>inc/js/discuz.js"></script>
  </head>
  
  <body class="discuz">
      
     
      <div class="head">
         <span class="d">同学讨论</span>
         <span class="o" onclick='pitch("#discuz")'>我来分析</span>
      </div>
      <div class="list">
          <ul>
             <s:iterator value="pageBean.list" var="p">
              <li>
               <div class="info">
                 <div class="avatar">
                 <img src="http://127.0.0.1:8088/WebStudy/inc/image/avatar/<s:property value="#p.userCommon.picString"/>" id="avatar">
                 </div>
                 <div class="user_date">
                   <h4 <s:if test="#p.isTeacher == 1"> style="color:red;"</s:if>><a title="<s:property value="#p.userCommon.uname"/>"  data-id="<s:property value="#p.userCommon.id"/>"><s:property value="#p.userCommon.tname"/>(<s:property value="#p.userCommon.loginIp"/>)</a><s:if test="#p.isTeacher == 1">《教师点评》</s:if></h4>
                   <h5><s:date name="#p.putTime" format="yyyy-MM-dd HH:mm:ss" /></h5>
                 </div>
                 <div class="praise">
                  <span class="icon-b icon-hand" onclick="zan(<s:property value="#p.id"/>,this)"></span><span><s:property value="#p.sort"/></span>
                 </div>
                 </div>
                 <div class="body">
                   <span><s:property value="#p.body"/></span>
                 </div>
              </li>
                </s:iterator>
          </ul>
      </div>
      <s:if test="pageBean.list.size()>7">
       <div class="head">
         <span class="x" >加载更多</span>
      </div>
      </s:if>
      <div class="publish" id="publish">
         <div class="form">
           <textarea rows="3" name="body" class="body" ></textarea>
           <button class="btn btn-lg" id="publish_message" data-id="<s:property value="tid"/>" >提交</button>
         </div>
      </div>
  </body>
</html>
