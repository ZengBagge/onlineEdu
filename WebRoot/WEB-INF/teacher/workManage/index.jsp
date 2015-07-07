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
    <title>湖南科技大学在线学习系统|练习管理</title>
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
   <script SRC="dwr/interface/addWork.js"></script>
    <script>
    $(function(){
            var f=0;
              $(".kb").on("click","i",function(){
                if(f == 0)
                 {$(this).parent(".hr").next().slideUp();
                 $(this).parent(".hr").addClass("collapses");
                  f=1;
                 }
                 else
              {$(this).parent(".hr").next().slideDown();
                 $(this).parent(".hr").removeClass("collapses");
                  f=0;
                 
                 }
              });
    });
       function del(id,dom){
           if(confirm("确定你要删除它么？")){
          addWork.del(id,function(result){
             if(result){
               $(dom).parent().parent().remove();
             }else{
               alert("此任务不能删除");
             }
         });
         }
       }
    </script>
  </head>
  
  <body class="menu_body">
       <div class="course_group" id="groupIndex">
            <div class="course_self kb">
               <h3>正在进行中的任务</h3>
               <div class="hr">
                  <i class="icon icon-b icon-angle-double-up"></i>
               </div>
               <ul>
                            <s:action name="workManage!getWorkingManage" namespace="/" var="w"  ></s:action>
                             <s:iterator value="#w.workingManageList" var="u">
                 <li title="<s:property  value="#u.titleString" />" class="personal-group" data-id="<s:property  value="#u.id" />">
                 <div class="bookname"><h4><s:property  value="#u.titleString" /></h4></div>
                 <div class="personalgroup-setting" title="任务信息" data-title="<s:property  value="#u.titleString" />" data-toggle="modal" data-target="#work_info" data-id="<s:property  value="#u.id" />"><i class="icon icon-w icon-xsz icon-cog-fat"></i></div>
                 </li>
                 </s:iterator>
                 <li class="tbc" data-url="workManage!addWork" title="添加任务" data-toggle="modal" data-target="#work_info" ></li>
               </ul>
            </div>

            <div class="course_self kb">
               <h3>过去的任务</h3>
               <div class="hr">
                  <i class="icon icon-b icon-angle-double-up"></i>
               </div>
                       <s:action name="workManage!getWorkedManage" namespace="/" var="w"  ></s:action>
                 <div class="table-work">    
                   <table class="table table-bordered">
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
                                 <s:iterator value="#w.workedManageList" var="u">
                                    <tr>
                                       <td><s:property  value="#u.titleString" /></td>
                                            <td><s:date name="#u.startDate" format="yyyy-MM-dd hh:mm" /></td>
                                       <td><s:date name="#u.endDate" format="yyyy-MM-dd hh:mm" /></td>
                                       <td><s:property  value="#u.wan" />/<s:property  value="#u.zong" /></td>
                                       <td><a href="javascript:void(0)" onclick="del(<s:property  value="#u.id" />,this)">删除</a></td>
                                 </s:iterator>
                            </tbody>
                      </table>   
                             
                      </div>     
            </div>
                                 <div>
								  <ul class="pager">
								  　<s:if test="%{pageBean.currentPage == 1}">
								　　　    <li ><a href="workManage!getWorkedManage?page=<s:property value="%{pageBean.currentPage+1}"/>">下一页<span aria-hidden="true">&rarr;</span></a></li>
								      </s:if>
								      <s:elseif test="%{pageBean.currentPage < pageBean.totalPage}">
								    <li ><a href="workManage!getWorkedManage?page=<s:property value="%{pageBean.currentPage-1}"/>"><span aria-hidden="true">&larr;</span>上一页</a></li>
								    <li ><a href="workManage!getWorkedManage?page=<s:property value="%{pageBean.currentPage+1}"/>">下一页<span aria-hidden="true">&rarr;</span></a></li>
								       </s:elseif>
								       　<s:else>
								　　　　    <li ><a href="workManage!getWorkedManage?page=<s:property value="%{pageBean.currentPage-1}"/>"><span aria-hidden="true">&larr;</span>上一页</a></li>
								　　</s:else>
								  </ul>
								</div>
     </div>
       <!-- 课程信息框 -->
<div class="modal fade" id="work_info" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
				$("#course_iframe").attr("src","workManage!infoSet?workId="+$(this).attr("data-id"));
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
