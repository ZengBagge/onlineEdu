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
    <title>湖南科技大学在线学习系统|体型管理</title>
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

  </head>
  
  <body class="menu_body">
       <div class="course_group">
            <div class="course_self">
               <h3>题型管理</h3>
               <div class="hr">
                  <i class="icon icon-b icon-angle-double-up"></i>
               </div>
               <ul>
               <s:action name="questionType!getQuestionType" namespace="/" var="type"  ></s:action>
                 <s:iterator value="#type.typeList" var="u">
                 <li title="<s:property  value="#u.titleString" />" class="questiontype-group" data-id="<s:property  value="#u.id" />">
                 <div class="bookname"><h4><s:property  value="#u.titleString" /></h4></div>
                 <div class="personalgroup-setting" title="体型设置" data-title="<s:property  value="#u.titleString" />" data-toggle="modal" data-target="#course_info" data-id="<s:property  value="#u.id" />"><i class="icon icon-w icon-xsz icon-cog-fat"></i></div>
                 </li>
                 </s:iterator>
                 <li class="tbc" data-url="questionType!addType" title="添加体型" data-toggle="modal" data-target="#course_info" ></li>
               </ul>
            </div>
       </div>
       <!-- 课程信息框 -->
<div class="modal fade" id="course_info" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog info-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="course_info_name"></h4>
      </div>
      <div class="modal-body">
        <div class="embed-responsive embed-responsive-16by9">
               <iframe id="course_iframe" class="embed-responsive-item" src=""></iframe>
          </div>
      </div>

    </div>
  </div>
</div>
<!-- 信息框结束 -->
<script type="text/javascript">
   	    $(function(){
		$(".course_self ul li .personalgroup-setting").each(function(){
			$(this).click(function(){
				$("#course_iframe").attr("src","questionType!infoSet?typeId="+$(this).attr("data-id"));
				$("#course_info_name").html($(this).attr("data-title"));
			});
		});
	});
	
	   	    $(function(){
		$(".course_self ul .tbc").each(function(){
			$(this).click(function(){
				$("#course_iframe").attr("src",$(this).attr("data-url"));
				$("#course_info_name").html($(this).attr("title"));
			});
		});
	});
</script>
  </body>
</html>
