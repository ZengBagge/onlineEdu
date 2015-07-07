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
    <title>湖南科技大学在线学习系统|课程信息</title>
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
    <script>
      function discuzCourse(id){
         $("#discuz_iframe").attr("src","discuz!discuzCourse?cid="+id);
      }
    </script>
  </head>
  
  <body>
    <div class="container pub_introduction boxsh3 mt30">
      <div class="row ">
			    	<span style="border:1px solid #000000;padding:0px;" class="tc col-xs-3 dib" ><img src="<%=basePath%>inc/image/eg_1.png"></span>
		        <span class="dib vt fw col-xs-9" style="padding:0 15px;">
        	<strong class="f14">
            	<span rel="885182" id="groupTitle" class="pub_title"><s:property  value="co.titleString" /></span><br>
                <span class="dib mt5">授课老师：<s:property  value="co.teacherUser.userCommon.tname" /></span><br>
                <span class="dib mt5">考核方式：<s:if test="co.test == 0">考试</s:if><s:else>考查</s:else></span><span class="dib mt5 ml15">创建时间：<s:date name="co.addDate" format="yyyy-MM-dd HH:mm:ss" /></span><br>
                <span class="dib mt5">开课班级：<s:iterator value="co.classes" var="c2"><s:property  value="#c2.name" />|</s:iterator></span><br>
                <span class="dib mt5">课程介绍：<s:property  value="co.introduce" /></span><br>
            </strong>
        </span>
    </div>
    <div class="row course_work_box mt20">
             <div role="tabpanel">

  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
   <li role="presentation" class="active"><a href="#chapter" aria-controls="home" role="tab" data-toggle="tab">章节</a></li>
    <li role="presentation" ><a href="#home" aria-controls="home" role="tab" data-toggle="tab"><s:if test="moth == 1">考试ing</s:if><s:else>练习ing</s:else></a></li>
    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab"><s:if test="moth == 1">考试ed</s:if><s:else>练习ed</s:else></a></li>
     <li role="presentation"><a onclick="discuzCourse(<s:property value="co.id"/>)" data-id="<s:property value="#c2.id"/>" data-toggle="modal" data-target="#discuz" href="">课程讨论</a></li>
  </ul>
  <s:if test="moth ==1">
    <s:action name="exam!getExaming" namespace="/" var="w"  >
    <s:param name="courseId" value="courseId"></s:param>
  </s:action>
  </s:if>
  <s:else>
  <s:action name="work!getWorking" namespace="/" var="w"  >
    <s:param name="courseId" value="courseId"></s:param>
  </s:action>
  </s:else>
   <s:action name="courseChapter!getChapterList" namespace="/" var="c"  >
     <s:param name="courseId" value="courseId"></s:param>
 </s:action>
  <!-- Tab panes -->
  <div class="tab-content">
      <div role="tabpanel" class="tab-pane active" id="chapter">
        <div class="bs-example" data-example-id="default-media">
         <s:iterator value="#c.chapters" var="cc">
             <div class="media">
             <div class="media-body">
              <h4 class="media-heading" id="media-heading"><s:property value="#cc.sort"/>、<s:property value="#cc.title"/></h4>
            <s:property value="#cc.shortMessage"/>
              </div>
             </div>
         
         </s:iterator>
       </div>
    </div>
    <div role="tabpanel" class="tab-pane " id="home">
        <table class="table table-bordered td-center">
					        <thead>
					          <tr>
					            <th width="40%"><s:if test="moth == 1">模考名称</s:if><s:else>任务名称</s:else></th>
					            <th width="17%">开始时间</th>
					            <th width="17%">结束时间</th>
					            <th width="10%">完成情况</th>
					            <th width="16%">操作</th>
					          </tr>
					        </thead>
                            <tbody>
                                 <s:iterator value="#w.workingList" var="u">
                                    <tr>
                                       <td><s:property  value="#u.titleString" /></td>
                                            <td><s:date name="#u.startDate" format="yyyy-MM-dd hh:mm" /></td>
                                       <td><s:date name="#u.endDate" format="yyyy-MM-dd hh:mm" /></td>
                                       <s:if test="#u.IsComplate == 1">
                                             <td>已完成</td><td></td>
                                       </s:if>
                                       <s:else>
                                         <td style="color:red;">未完成</td><td><s:if test="moth == 1"><a href="exam!examInfo?examId=<s:property  value="#u.id" />" >开始任务</a></s:if><s:else><a href="work!workInfo?workId=<s:property  value="#u.id" />" >开始任务</a></s:else></td>
                                       </s:else>
                                       </tr>
                                       </s:iterator>
                            </tbody>
            </table>   
    </div>
    
    <div role="tabpanel" class="tab-pane" id="profile">
  <s:if test="moth ==1">
    <s:action name="exam!getExamed" namespace="/" var="w"  >
    <s:param name="courseId" value="courseId"></s:param>
  </s:action>
  </s:if>
  <s:else>
  <s:action name="work!getWorked" namespace="/" var="w"  >
    <s:param name="courseId" value="courseId"></s:param>
  </s:action>
  </s:else>
         <table class="table table-bordered td-center">
					        <thead>
					          <tr>
					            <th width="40%">任务名称</th>
					            <th width="17%">开始时间</th>
					            <th width="17%">结束时间</th>
					            <th width="10%">完成情况</th>
					            <th width="16%">操作</th>
					          </tr>
					        </thead>
                            <tbody>
                                 <s:iterator value="#w.workedList" var="u">
                                    <tr>
                                       <td><s:property  value="#u.titleString" /></td>
                                            <td><s:date name="#u.startDate" format="yyyy-MM-dd hh:mm" /></td>
                                       <td><s:date name="#u.endDate" format="yyyy-MM-dd hh:mm" /></td>
										        <s:if test="#u.IsComplate == 1">
                                         <td>已完成</td><td></td>
                                       </s:if>
                                       <s:else>
                                         <td style="color:red;">未完成</td><td></td>
                                       </s:else>
                                 </s:iterator>
                            </tbody>
                      </table>   
    </div>

  </div>

</div>
   </div>
       </div>
<!--同学讨论  -->
<div class="modal fade" id="discuz" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">课程讨论</h4>
      </div>
      <div class="modal-body">
         <iframe src="" id="discuz_iframe"></iframe>
      </div>
    </div>
  </div>
</div>
  </body>
