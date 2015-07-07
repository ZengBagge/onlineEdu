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
    <title>湖南科技大学在线学习系统|单选题</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
<script SRC="dwr/interface/addTopicJudge.js"></script>
        <script>
    function discuz(id){
	$("#discuz_iframe").attr("src","discuz!topic?tid="+id);
}

function del(id,dom){
if(confirm("确定要删除么？")){
 addTopicJudge.del(id,function(result){
   if(result){
     $(dom).parent().parent().remove();
   }else{
   alert("此题应用中，不能删除");
   }
 });
} 
}
function update(id){
  $("#addTopic_iframe").attr("src","topicJudge!update?judgeTopicId="+id);
}
</script>
  </head>
  
  
  <body>
       <table class="table table-bordered">
        <thead>
          <tr>
           <th width="10%">编号</th>
            <th width="60%">题干</th>
            <th width="15%">答案</th>
            <th width="15%">操作</th>
          </tr>
        </thead>
        <tbody>

      <s:iterator value="pageBean.list" var="u">
          <tr>
          <td style="text-align:center"><s:property  value="#u.topic.tid" /></td>
            <td scope="row"><s:property  value="#u.topic.titleString" /></td>
            <td style="text-align:center;"><s:if test="#u.answer == 0">错误</s:if>
            <s:else>正确</s:else>
            </td>
      <td style="text-align:center;"><a onclick="discuz(<s:property  value="#u.topic.id" />)" data-toggle="modal" data-target="#discuz" href="javascript:void(0)">讨论</a>|<a href="javascript:void(0)" onclick="del(<s:property  value="#u.id" />,this)">删除</a>|<a href="javascript:void(0)" onclick="update(<s:property  value="#u.id" />)" data-target="#update_topic" data-toggle="modal" >修改</a></td>
            </tr>
       </s:iterator>   
        
        </tbody>
      </table>
       <nav>
  <ul class="pager">
  　<s:if test="%{pageBean.currentPage == 1}">
　　　    <li ><a href="topicJudge!getJudgeList?courseId=<s:property value="%{courseId}"/>&page=<s:property value="%{pageBean.currentPage+1}"/>">下一页<span aria-hidden="true">&rarr;</span></a></li>
      </s:if>
      <s:elseif test="%{pageBean.currentPage < pageBean.totalPage}">
    <li ><a href="topicJudge!getJudgeList?courseId=<s:property value="%{courseId}"/>&page=<s:property value="%{pageBean.currentPage-1}"/>"><span aria-hidden="true">&larr;</span>上一页</a></li>
    <li ><a href="topicJudge!getJudgeList?courseId=<s:property value="%{courseId}"/>&page=<s:property value="%{pageBean.currentPage+1}"/>">下一页<span aria-hidden="true">&rarr;</span></a></li>
       </s:elseif>
       　<s:else>
　　　　    <li ><a href="topicJudge!getJudgeList?courseId=<s:property value="%{courseId}"/>&page=<s:property value="%{pageBean.currentPage-1}"/>"><span aria-hidden="true">&larr;</span>上一页</a></li>
　　</s:else>
  </ul>
</nav>
 <!--同学讨论  -->
<div class="modal fade" id="discuz" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">试题分析</h4>
      </div>
      <div class="modal-body">
         <iframe src="" id="discuz_iframe"></iframe>
      </div>
    </div>
  </div>
</div>
        <!-- 修改题库-->
<div class="modal fade" id="update_topic" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog info-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">修改题目</h4>
      </div>
      <div class="modal-body">
          <div class="embed-responsive embed-responsive-16by9">
               <iframe id="addTopic_iframe" class="embed-responsive-item" src=""></iframe>
          </div>
      </div>

    </div>
  </div>
</div>
  </body>
</html>
