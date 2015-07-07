<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@  taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
       <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>湖南科技大学在线学习系统|练习管理</title>
	<meta name="pragma" content="no-cache">
	<meta name="cache-control" content="no-cache">
	<meta name="expires" content="0">    
	<meta name="keywords" content="keyword1,keyword2,keyword3">
	<meta name="description" content="This is my page">
	<link rel="stylesheet" href="<%=basePath%>inc/css/bootstrap.min.css">
   <link rel="stylesheet" href="<%=basePath%>inc/css/bootstrap-theme.min.css">
   <link rel="stylesheet" href="<%=basePath%>inc/css/bootstrap-datetimepicker.min.css">
   <link rel="stylesheet" href="<%=basePath%>inc/css/style.css">
   <link href="<%=basePath%>inc/font/glyphicons-halflings-regular.eot">
   <script src="<%=basePath%>inc/js/jquery-1.11.2.min.js"></script>
   <script src="<%=basePath%>inc/js/bootstrap.min.js"></script>
      <script src="<%=basePath%>inc/js/form.js"></script>
 <script src="<%=basePath%>inc/js/bootstrap-datetimepicker.min.js"></script>
  <script src="<%=basePath%>inc/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
       <script SRC="dwr/engine.js"></script>
   <script SRC="dwr/util.js"></script>
   <script SRC="dwr/interface/addExamTopic.js"></script>
   <script SRC="dwr/interface/addExam.js"></script>
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
           
           $("#courseTest").on("change",function(){
               if($(this).val() !="")
                addExamTopic.getExamTopic($(this).val(),function(result){
                   if(result != null)
                     {
                         $("#workTopic").html(""); //清空组卷选项
                        $.each(result,function(n,value){
                           $("#workTopic").append('<option value="'+value[0]+'">'+value[1]+'</option>');
                        });
                     }
                });
           });
        });
 </script>

  </head>
  
  <body class="fram_body">
           <div class="course_main">
               <form action="course!addForm" method="post" class=" form-horizontal">
                    <div class="form-group">
                            <label for="workName" class="col-sm-2 control-label">任务名称</label>
                            <div class="col-sm-8">
                           <input type="text" class="form-control" id="workName" placeholder="任务名称" check-type="required" required-message="请填写任务名称" value="<s:property  value="exam.titleString" />"/>
                              <s:if test="exam != null">
                            <input type="hidden" value="<s:property  value="exam.id" />" id ="exam_id"/>
                            </s:if>
                            </div>
                    </div>
                     <div class="form-group">
                            <label for="courseMajor" class="col-sm-2 control-label">任务对象</label>
                            <div class="col-sm-8">
                              <span class="addicon">
                              <i class="icon icon-b icon-add-circle" title="选取班级" data-toggle="modal" data-target="#workMajor"></i>
                              </span>
                              <s:if test="exam != null">
                              <ul class="after_add" id="after_add">
                                <s:iterator value="exam.classes" var="w">
                                   <li id="<s:property  value="#w.classUid" />"><span class=" after_add_span"><s:property  value="#w.name" /></span><i onclick="liremove(<s:property  value="#w.classUid" />);"  class="delete-attach icon icon-c icon-cross-red"></i></li>
                                </s:iterator>
                              </ul>
                              </s:if>
                              <s:else>
                              <span class="before_add" id="before_add">未选取班级</span>
                              <ul class="after_add" id="after_add"></ul>
                              </s:else>
                            </div>
                    </div>
                      <div class="form-group">
                            <label for="courseTest" class="col-sm-2 control-label">所属课程</label>
                            <div class="col-sm-8">              
                           <select class="form-control" id="courseTest"  >      
                            <s:action name="course!getCourse" namespace="/" var="course"  ></s:action>
                                 <option value="<s:property  value="exam.course.id" />"><s:property  value="exam.course.titleString" /></option>
                             <s:iterator value="#course.courseList" var="u">
                             <option value="<s:property  value="#u.id" />"><s:property  value="#u.titleString" /></option>
                             </s:iterator>
                           </select>
                            </div>
                    </div>
                     
                      <div class="form-group">
                            <label for="courseTest" class="col-sm-2 control-label">匹配组卷</label>
                            <div class="col-sm-8">              
                           <select class="form-control" id="workTopic"  >      
                             <option value="<s:property  value="exam.examTopic.id" />"><s:property  value="exam.examTopic.titleString" /></option>
                           </select>
                            </div>
                    </div>
                    <div class="form-group">
                            <label for="courseName" class="col-sm-2 control-label">开始时间</label>
                            <div class="col-sm-8" >
                                 <div class="date form_datetime" id="startTime">
							    <input size="16" type="text"  readonly class="form-control"  id="firstDate" placeholder="点击选择" check-type="required" required-message="请选择开始时间" value="<s:date name="exam.startDate" format="yyyy-MM-dd hh:mm" />"> 
						         <span class="add-on"><i class="icon-th"></i></span>
						        </div>
                            </div>
                    </div>
                    		 <script type="text/javascript">
							    $("#startTime").datetimepicker({
							     language:'zh-CN',
							    format: 'yyyy-mm-dd hh:ii',
                               autoclose: true,
                               todayBtn: true,
                               todayHighlight: 1,
                                });
							    </script> 
                      <div class="form-group">
                            <label for="workIntroduce" class="col-sm-2 control-label">考试用时</label>
                            <div class="col-sm-8">
                             <input type="text" class="form-control" id="examTimes" placeholder="考试任务用时（分钟）" check-type="number" number-message="请填写考试任务用时整数（分钟）" value="<s:property  value="exam.examTimes" />"/>
                            </div>
                    </div >
                    <div class="form-group">
                            <label for="workIntroduce" class="col-sm-2 control-label">任务说明</label>
                            <div class="col-sm-8">
                            <textarea id="workIntroduce" name="workIntroduce" class="form-control" placeholder="对本次任务进行简单说明" ><s:property  value="exam.description" /></textarea>
                            </div>
                    </div >
                    <div class="form-group">
                      <label for="courseIntroduce" class="col-sm-2 control-label"></label>
                            <div class="col-sm-8">
                    <s:if test="exam != null">
                          <button type="button" id="course_btn_update" class="btn btn-info btn-course" >更新</button>
                           </s:if><s:else>
                             <button type="button" id="course_btn" class="btn btn-info btn-course" >提交</button>
                           </s:else></div>
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
                      var workName=$("#workName").val();
                      var workMajor="";
                      var courseId =$("#courseTest").val();
                      var beginTime=$("#firstDate").val();
                      var endTime=$("#lastDate").val();
                      var workTopic =$("#workTopic").val();
                      var workIntroduce=$("#workIntroduce").val();
                      var examTimes=$("#examTimes").val();
                        $(".after_add li").each(function(){
                         workMajor=workMajor+$(this).attr("id")+",";
                      });
			          addExam.addForm(workName,workMajor,courseId,beginTime,endTime,workTopic,workIntroduce,examTimes,function(result){
                         if(result){
                               $("#course_btn").html("提交成功，请关闭窗口"); 
                      			$("#workName").val("");
								$("#courseTest").val("");
								$("#firstDate").val("");
								$("#lastDate").val("");
								$("#workTopic").val("");
								$("#workIntroduce").val("");
								$(".after_add").html("");
								$("#examTimes").val("");
                          }else{
                               $("#course_btn").html("提交失败，请重试");
                         }
			      });
			    }
			   });
			    });
</script>
  <script type="text/javascript">
			 $(function(){
			   //1. 简单写法：
			   $("form").validation();
			   $("#course_btn_update").on('click',function(event){
			     // 2.最后要调用 valid()方法。
			     if ($("form").valid(this,"error!")==false){
			       //$("#error-text").text("error!"); 1.0.4版本已将提示直接内置掉，简化前端。
			       return false;
			     }
			     else
			    {  
			          $(this).html("提交中。。"); 
                      var workName=$("#workName").val();
                      var workMajor="";
                      var courseId =$("#courseTest").val();
                      var beginTime=$("#firstDate").val();
                      var endTime=$("#lastDate").val();
                      var workTopic =$("#workTopic").val();
                      var workIntroduce=$("#workIntroduce").val();
                      var examTimes=$("#examTimes").val();
                        $(".after_add li").each(function(){
                         workMajor=workMajor+$(this).attr("id")+",";
                      });
                      var id=$("#exam_id").val();
			          addExam.setForm(id,workName,workMajor,courseId,beginTime,endTime,workTopic,workIntroduce,examTimes,function(result){
                         if(result){
                               $("#course_btn_update").html("更新成功，请关闭窗口"); 
                      			$("#workName").val("");
								$("#courseTest").val("");
								$("#firstDate").val("");
								$("#lastDate").val("");
								$("#workTopic").val("");
								$("#workIntroduce").val("");
								$(".after_add").html("");
								$("#examTimes").val("");
                          }else{
                               $("#course_btn").html("提交失败，请重试");
                         }
			      });
			    }
			   });
			    });
</script>
  <!-- 选取开课专业 -->
          <div class="modal fade" id="workMajor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
  </body>
</html>
