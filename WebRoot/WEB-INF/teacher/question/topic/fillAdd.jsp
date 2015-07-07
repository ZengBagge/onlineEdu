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
    <title>湖南科技大学在线学习系统|添加多选题</title>
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
<script SRC="dwr/interface/addTopicFill.js"></script>
  </head>
     <body class="fram_body">
           <div class="course_main">
               <form action="course!addForm" method="post" class=" form-horizontal">
                     <div class="form-group">
                            <label for="topicTitle" class="col-sm-2 control-label">编号</label>
                            <div class="col-sm-8">
                           <input type="text" class="form-control" id="topicTid" placeholder="编号，格式（××-××-××××）" check-type="required" required-message="请填写编号" value="<s:property value="fillTopic.topic.tid" />"/>
                            </div>
                    </div>
                    <div class="form-group">
                            <label for="topicTitle" class="col-sm-2 control-label">题干</label>
                            <div class="col-sm-8">
                           <textarea  class="form-control" id="topicTitle" placeholder="题干，留空处使用括号" check-type="required" required-message="请填写题干" ><s:property value="fillTopic.topic.titleString" /></textarea>
                                                     <s:if test="fillTopic !=null" ><input type="hidden" id="id" value="<s:property value="fillTopic.id" />"></s:if>
                           <input type="hidden" id="course" value="<s:property value="addCourse" />">
                            </div>
                    </div>
                         <div class="form-group">
                            <label for="courseTest" class="col-sm-2 control-label">所属章节</label>
                            <div class="col-sm-8">              
                           <select class="form-control" id="courseChapter"  >      
                            <s:action name="courseChapter!getChapterList" namespace="/" var="t"  >
                              <s:param name="courseId" value="addCourse"></s:param>
                            </s:action>
                             <option value="<s:property  value="fillTopic.topic.chapter.id" />"><s:property  value="fillTopic.topic.chapter.title" /></option>
                             <s:iterator value="#t.chapters" var="tt">
                             <option value="<s:property  value="#tt.id" />"><s:property  value="#tt.title" /></option>
                             </s:iterator>
                           </select>
                            </div>
                    </div>
                     <div class="form-group">
                            <label for="courseTest" class="col-sm-2 control-label">题目等级</label>
                            <div class="col-sm-8">              
                           <select class="form-control" id="topicType"  >      
                            <s:action name="topicType!getTopicType" namespace="/" var="t"  ></s:action>
                             <option value="<s:property  value="fillTopic.topic.topicType.id" />"><s:property  value="fillTopic.topic.topicType.name" /></option>
                             <s:iterator value="#t.topicTypes" var="tt">
                             <option value="<s:property  value="#tt.id" />"><s:property  value="#tt.name" /></option>
                             </s:iterator>
                           </select>
                            </div>
                    </div>
                       <s:iterator value="fillTopic.answers" var="a2">
                     <div class="form-group" >
                            <label for="fillAnswer0" class="col-sm-2 control-label">答案</label>
                            <div class="col-sm-8">
                           <input type="text" class="form-control"  name="fillAnswer" data-id="<s:property value="#a2.orderInt" />" placeholder="答案"  value="<s:property value="#a2.body" />" />
                            </div>
                    </div>
                    </s:iterator>
                        <div class="form-group" id="add_group">
                        <div class="add" title="添加答案项" >
                          <label class="col-sm-2 control-label"></label>
                           <div class="col-sm-8">
                          <span class="add_input">
                              <i id="inputAdd"  title="添加题目" class="icon icon-b icon-add-circle"></i>
                          </span>
                          <span >有多个空？请添加答案项（答案顺序与空对应）</span>
                           </div>
                      </div>
                      </div>
                    <div class="form-group">
                            <label for="topicDescription" class="col-sm-2 control-label">题目解析</label>
                            <div class="col-sm-8">
                            <textarea id="topicDescription" name="topicDescription" class="form-control" placeholder="题目解析 "><s:property value="fillTopic.topic.description" /></textarea>
                            </div>
                    </div >
                      <div class="form-group">
                            <label for="topicDescription" class="col-sm-2 control-label">题目来源</label>
                            <div class="col-sm-8">
                            <textarea id="topicSource" name="topicSource" class="form-control" placeholder="题目来源 "><s:property value="fillTopic.topic.sourceString" /></textarea>
                            </div>
                    </div >
                    <div class="form-group">
                      <label for="courseIntroduce" class="col-sm-2 control-label"></label>
                            <div class="col-sm-8">
                     <s:if test="fillTopic !=null"><button type="button" id="course_btn_update" class="btn btn-info btn-course" >更新</button></s:if><s:else>       
                    <button type="button" id="course_btn" class="btn btn-info btn-course" >提交</button>
                    </s:else></div>
                    </div>
               </form>
           </div>
       <script type="text/javascript">
         var inputInt=1;
            $(function(){
               $("#inputAdd").on('click',function(event){
                  var html='<div class="form-group" >'
                            +'<label for="fillAnswer'+inputInt+'" class="col-sm-2 control-label">答案</label>'
                            +'<div class="col-sm-8">'
                           +'<input type="text" class="form-control"  name="fillAnswer" data-id="'+inputInt+'" placeholder="答案" '
                           +' </div>'
                    +'</div>';
                   $("#add_group").before(html);
                   inputInt=inputInt+1;
               });
            });//添加表单函数
             var answer = Array(); //定义一个答案数组
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
			                  var title=$("#topicTitle").val();
			                   $("input[name='fillAnswer']").each(function(){
			                      if($(this).val() != "")
			                         answer.push($(this).val());
			                   });
			                  var decription=$("#topicDescription").val();
			                   var topicType =$("#topicType").val();
			                  var topicSource =$("#topicSource").val();
			                  var courseChapter =$("#courseChapter").val();
			                  var tid=$("#topicTid").val();
			                  var courseId=$("#course").val();
			          addTopicFill.addForm(title,answer,decription,topicType,topicSource,courseChapter,tid,courseId,function(result){
                         if(result){
                               $("#course_btn").html("提交成功，请关闭窗口"); 
                               $("input").val("");
                               $("textarea").val("");
                          }else{
                               $("#course_btn").html("提交失败，请重试");
                         }
			      });
			    }
			   });
			    });
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
			                  var title=$("#topicTitle").val();
			                   $("input[name='fillAnswer']").each(function(){
			                      if($(this).val() != "")
			                         answer.push($(this).val());
			                   });
			                  var decription=$("#topicDescription").val();
			                  var id=$("#id").val();
			                 var topicType =$("#topicType").val();
			                  var topicSource =$("#topicSource").val();
			                  var courseChapter =$("#courseChapter").val();
			                  var tid=$("#topicTid").val();
			               addTopicFill.updateFrom(id,title,answer,decription,topicType,topicSource,courseChapter,tid,function(result){
                         if(result){
                               $("#course_btn_update").html("提交成功，请关闭窗口"); 
                               $("input").val("");
                               $("textarea").val("");
                          }else{
                               $("#course_btn_update").html("提交失败，请重试");
                         }
			      });
			    }
			   });
			    });
</script>
  </body>
</html>
