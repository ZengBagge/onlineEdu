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
    <title>湖南科技大学在线学习系统|课程管理</title>
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
 <script> 
            $(function(){

                      $("#major_table").on("click","li",function(){
                        $("#before_add").remove();
                         $("#after_add").append(' <li id="'+$(this).attr("data-id")+'"><span class=" after_add_span">'+$(this).html()+'</span><i onclick="liremove('+$(this).attr("data-id")+');"  class="delete-attach icon icon-c icon-cross-red"></i></li>');
                         $(this).remove();
                      });
                  });

              function liremove(id){
                $("#major_table").append('<li class="list-group-item" data-id="'+id+'">'+$("#"+id+" span").html()+'</li>');
                 $("#"+id).remove();
              }
                $(function(){

                      $("#typetable").on("click","li",function(){
                        $("#type_before").remove();
                         $("#type_after").append(' <li id="type'+$(this).attr("data-id")+'" data-id="'+$(this).attr("data-id")+'"><span class=" after_add_span">'+$(this).html()+'</span><i onclick="lireTypemove('+$(this).attr("data-id")+');"  class="delete-attach icon icon-c icon-cross-red"></i></li>');
                         $(this).remove();
                      });
                  });
              function lireTypemove(id){
                $("#typetable").append('<li class="list-group-item" data-id="'+id+'">'+$("#type"+id+" span").html()+'</li>');
                 $("#type"+id).remove();
              }
 </script>
   <script SRC="dwr/engine.js"></script>
   <script SRC="dwr/util.js"></script>
   <script SRC="dwr/interface/addCourse.js"></script>
  </head>
  
  <body class="fram_body">
           <div class="course_main">
               <form action="course!addForm" method="post" class=" form-horizontal">
                    <div class="form-group">
                            <label for="courseName" class="col-sm-2 control-label">课程名</label>
                            <div class="col-sm-8">
                           <input type="text" value="<s:property  value="co.titleString" />" class="form-control" id="courseName" placeholder="课程名称" check-type="required" required-message="请填写课程名" />
                            <input type="hidden" value="<s:property  value="co.id" />" id="courseId">
                            </div>
                    </div>
                    <div class="form-group">
                            <label for="courseMajor" class="col-sm-2 control-label">开课班级</label>
                            <div class="col-sm-8">
                              <span class="addicon">
                              <i class="icon icon-b icon-add-circle" title="选取班级" data-toggle="modal" data-target="#course_major"></i>
                              </span>
                           
                              <ul class="after_add" id="after_add">
                                 <s:iterator value="co.classes" var="u">
                                    <li id="<s:property  value="#u.classUid" />"><span class=" after_add_span"><s:property  value="#u.name" /></span><i class="delete-attach icon icon-c icon-cross-red" onclick="liremove(<s:property  value="#u.classUid" />);"></i></li>
                                 </s:iterator>
                              </ul>
                            </div>
                    </div>
                      <div class="form-group">
                            <label for="courseTest" class="col-sm-2 control-label">考核方式</label>
                            <div class="col-sm-8">              
                           <select class="form-control" id="courseTest"  > 
                           <s:if test="co.test==0">        
                             <option value="0">考试</option>
                             <option value="1">考查</option>
                             </s:if>
                             <s:else>
                             <option value="1">考查</option>
                             <option value="0">考试</option>
                             </s:else>
                           </select>
                            </div>
                    </div>
                    <div class="form-group">
                            <label for="courseMajor" class="col-sm-2 control-label">考核题型</label>
                            <div class="col-sm-8">
                              <span class="addicon">
                              <i class="icon icon-b icon-add-circle" title="选取题型" data-toggle="modal" data-target="#course_questionType"></i>
                              </span>
                             
                              <ul class="after_add " id="type_after">
                                 <s:iterator value="co.questionType" var="u">
                                    <li id="type<s:property  value="#u.id" />"  data-id="<s:property  value="#u.id" />"><span class=" after_add_span"><s:property  value="#u.titleString" /></span><i class="delete-attach icon icon-c icon-cross-red" onclick="lireTypemove(<s:property  value="#u.id" />);"></i></li>
                                 </s:iterator>
                              </ul>
                            </div>
                    </div>
                    <div class="form-group">
                            <label for="courseIntroduce" class="col-sm-2 control-label">课程介绍</label>
                            <div class="col-sm-8">
                            <textarea id="courseIntroduce" name="courseIntroduce" class="form-control" placeholder="课程简单介绍" ><s:property  value="co.introduce" /></textarea>
                            </div>
                    </div >
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
			          $(this).html("提交中。。"); 
			          var courseName=$("#courseName").val();
			          var courseTest=$("#courseTest").val();
			          var courseIntroduce=$("#courseIntroduce").val();
			          var courseMajors="";
			          var courseQuestionType ="";
			          var courseId=$("#courseId").val();
			         $("#after_add li").each(function(){
			            courseMajors=courseMajors+$(this).attr("id")+",";
			         });
			           $("#type_after  li").each(function(){
			            courseQuestionType=courseQuestionType+$(this).attr("data-id")+",";
			         });
			          addCourse.setForm(courseId,courseName,courseTest,courseIntroduce,courseMajors,courseQuestionType,function(result){
                         if(result){
                               $("#course_btn").html("提交成功，请关闭窗口"); 
                          }else{
                               $("#course_btn").html("提交失败，请重试");
                         }
			      });
			    }
			   });
			    });
</script>
           <!-- 选取开课专业 -->
          <div class="modal fade" id="course_major" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">开课班级</h4>
      </div>
      <div class="modal-body">
                  <s:action name="class!myClasses" namespace="/" var="m"  ></s:action>
                   <div class="major_list">
			             <ul class="major_table list-group" id="major_table">
			                          <s:iterator value="#m.classesList" var="u">
			                            <li class="list-group-item" data-id="<s:property  value="#u.classUid" />"><s:property  value="#u.name" /></li>
			                         </s:iterator>
			            </ul>
			        </div>    
      </div>
    </div>
  </div>
</div>
    <!-- 选取考核题型 -->
          <div class="modal fade" id="course_questionType" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">考核题型</h4>
      </div>
      <div class="modal-body">
         <s:action name="questionType!getQuestionType" namespace="/" var="m2"  ></s:action>
                   <div class="major_list">
			             <ul class="major_table list-group " id="typetable">
			                          <s:iterator value="#m2.typeList" var="u2">
			                            <li class="list-group-item" data-id="<s:property  value="#u2.id" />"><s:property  value="#u2.titleString" /></li>
			                         </s:iterator>
			            </ul>
			        </div>    
      </div>
    </div>
  </div>
</div>
  </body>
</html>
