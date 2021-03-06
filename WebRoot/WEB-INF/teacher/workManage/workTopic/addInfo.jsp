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
   <link rel="stylesheet" href="<%=basePath%>inc/css/bootstrap-datetimepicker.min.css">
   <link rel="stylesheet" href="<%=basePath%>inc/css/style.css">
   <link href="<%=basePath%>inc/font/glyphicons-halflings-regular.eot">
   <script src="<%=basePath%>inc/js/jquery-1.11.2.min.js"></script>
   <script src="<%=basePath%>inc/js/bootstrap.min.js"></script>
       <script src="<%=basePath%>inc/js/bootstrap-datetimepicker.min.js"></script>
        <script src="<%=basePath%>inc/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
      <script src="<%=basePath%>inc/js/form.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
 <script> 
           $(function(){
                  var first=true;
                     $("#type_table").on("click","li",function(){
                        $("#type_before").remove();
                         $("#type_add").append(' <li id="'+$(this).attr("data-id")+'"><span class=" after_add_span">'+$(this).html()+'</span><i onclick="liremove('+$(this).attr("data-id")+');"  class="delete-attach icon icon-c icon-cross-red"></i></li>');
                         $(this).remove();
                         $("#course"+$(this).attr("data-id")).show();
                         if(first)
                           {
                           $("#course"+$(this).attr("data-id")).addClass("active");
                           $("#home"+$(this).attr("data-id")).addClass("active");
                           first=false;
                           }
                      });
                  });
              function liremove(id){
                $("#type_table").append('<li class="list-group-item" data-id="'+id+'">'+$("#"+id+" span").html()+'</li>');
                 $("#"+id).remove();
                 $("#course"+id).hide();
                 $("#home"+id).removeClass("active");
              }
             
 </script>
   <script SRC="dwr/engine.js"></script>
   <script SRC="dwr/util.js"></script>
   <script SRC="dwr/interface/addWorkTopic.js"></script>
  </head>
  
  <body class="fram_body">
           <div class="work_main">
               <form action="" method="post" class=" form-horizontal">
                    <div class="form-group">
                            <label for="courseName" class="col-sm-2 control-label">组卷名称</label>
                            <div class="col-sm-8">
                           <input type="text" class="form-control" id="workTopicName" placeholder="课程名称" check-type="required" required-message="请填写课程名" />
                            </div>
                    </div>
                   <div class="form-group">
                            <label for="courseMajor" class="col-sm-2 control-label">组卷题型</label>
                            <div class="col-sm-8">
                              <span class="addicon">
                              <i class="icon icon-b icon-add-circle" title="选取题型" data-toggle="modal" data-target="#course_questionType"></i>
                              </span>
                              <span class="before_add" id="type_before">未选取题型</span>
                              <ul class="after_add" id="type_add"></ul>
                            </div>
                    </div>
       <s:action name="questionType!getQuestionTypeByCourseId" namespace="/" var="m"  >
             <s:param name="courseId" value="courseId"></s:param>
         </s:action>
                    <div role="tabpanel">
							  <!-- Nav tabs -->
							  <ul class="nav nav-tabs" role="tablist">
							    <s:iterator value="#m.typeList" var="u">
							    <li role="presentation" style="display:none;" id="course<s:property  value="#u.id" />"><a href="#home<s:property  value="#u.id" />" aria-controls="home<s:property  value="#u.id" />" role="tab" data-toggle="tab"><s:property  value="#u.titleString" /></a></li>
							    </s:iterator>
							  </ul>
								     <s:action name="topic!getTopicByCourseId" namespace="/"   var="qq">
							                <s:param name="courseId" value="courseId"></s:param>
							         </s:action>
							  <!-- Tab panes -->
							  <div class="tab-content topic-tab">
							  			<s:iterator value="#m.typeList" var="u">
							    <div role="tabpanel" class="tab-pane" id="home<s:property  value="#u.id" />">

							         <table class="table table-bordered" >
							                 <thead><tr><th>选择</th><th>题干</th><th>练习次数</th><th>错误次数</th></tr></thead>
							                 <tbody>
							                 <s:iterator value="#qq.topics" var="qqt">
							                  <s:if test="#qqt.type.id==#u.id">
							                            <tr><td><input type="checkbox" name="topic" value="<s:property  value="#qqt.id" />"></td>
							                            <td><s:property  value="#qqt.titleString" /></td>
							                            <td><s:property  value="#qqt.sum" /></td>
							                            <td><s:property  value="#qqt.error" /></td>
							                             </tr>
							                    </s:if>         
							                    </s:iterator>
							                 </tbody>
							         </table>
							         
							    </div>
							    	    </s:iterator>
							  </div>
</div>
                    <div class="form-group">
                      <label for="courseIntroduce" class="col-sm-2 control-label"></label>
                            <div class="col-sm-8">
                    <button type="button" id="course_btn" class="btn btn-info btn-course" >提交</button>
                     </div>
                    </div>
               </form>
           </div>
       <script type="text/javascript">

			 $(function(){

			   //1. 简单写法：
			   $("form").validation();
			   $("#course_btn").on('click',function(event){
			     // 2.最后要调用 valid()方法。
			     if ($("form").valid(this,"error!")==false){
			       //$("#error-text").text("error!"); 1.0.4版本已将提示直接内置掉，简化前端。
			       return false;
			     }
			     else
			    {  
			           var topicIds="";
			          var courseQuestionType="";
			          var courseId = <s:property value="courseId"/>;
			          $(this).html("提交中。。"); 
			          var workTopicName=$("#workTopicName").val();
                      $(".after_add li").each(function(){
                         courseQuestionType=courseQuestionType+$(this).attr("id")+",";
                      });

			            $("input:checked").each(function(){  
                              topicIds += $(this).val()+",";
                      });

			          if(courseQuestionType !=null && topicIds !=null){
			               addWorkTopic.addForm(workTopicName,topicIds,courseQuestionType,courseId,function(result){
                         if(result){
                         
                               $("#course_btn").html("提交成功");
                                window.location="workTopic!index";
                          }else{
                          return false;
                               $("#course_btn").html("提交失败，请重试");
                         }
			      });
			        }
			        else
			        {
			                 $("#course_btn").html("提交失败，请重试");
			                return false;
			           }
			      }
			   });
			    });
</script>
  <!-- 选取开课专业 -->
          <div class="modal fade" id="course_questionType" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">题型</h4>
      </div>
      <div class="modal-body">

                   <div class="major_list">
			             <ul class="major_table list-group" id="type_table">
			                          <s:iterator value="#m.typeList" var="u">
			                            <li class="list-group-item" data-id="<s:property  value="#u.id" />"><s:property  value="#u.titleString" /></li>
			                         </s:iterator>
			            </ul>
			        </div>    
      </div>
    </div>
  </div>
</div>
  </body>
</html>
