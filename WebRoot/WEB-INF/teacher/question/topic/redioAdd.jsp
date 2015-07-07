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
    <title>湖南科技大学在线学习系统|添加单选题</title>
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
<script SRC="dwr/interface/addTopicRedio.js"></script>
  </head>
     <body class="fram_body">
           <div class="course_main">
               <form action="course!addForm" method="post" class=" form-horizontal">
               <div class="form-group">
                            <label for="topicTitle" class="col-sm-2 control-label">编号</label>
                            <div class="col-sm-8">
                           <input type="text" class="form-control" id="topicTid" placeholder="编号，格式（××-××-××××）" check-type="required" required-message="请填写编号" value="<s:property value="redioTopic.topic.tid" />"/>
                            </div>
                    </div>
                    <div class="form-group">
                            <label for="topicTitle" class="col-sm-2 control-label">题干</label>
                            <div class="col-sm-8">
                           <input type="text" class="form-control" id="topicTitle" placeholder="题干" check-type="required" required-message="请填写题干" value="<s:property value="redioTopic.topic.titleString" />"/>
                          <s:if test="redioTopic !=null" ><input type="hidden" id="id" value="<s:property value="redioTopic.id" />"></s:if>
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
                             <option value="<s:property  value="redioTopic.topic.chapter.id" />"><s:property  value="redioTopic.topic.chapter.title" /></option>
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
                             <option value="<s:property  value="redioTopic.topic.topicType.id" />"><s:property  value="redioTopic.topic.topicType.name" /></option>
                             <s:iterator value="#t.topicTypes" var="tt">
                             <option value="<s:property  value="#tt.id" />"><s:property  value="#tt.name" /></option>
                             </s:iterator>
                           </select>
                            </div>
                    </div>
                     <div class="form-group">
                            <label for="topicAnswerA" class="col-sm-2 control-label">选项A</label>
                            <div class="col-sm-8">
                           <input type="text" class="form-control" id="topicAnswerA" placeholder="选项A" check-type="required" required-message="请填写答案A" value="<s:property value="redioTopic.answerA" />"/>
                            </div>
                    </div>
                     <div class="form-group">
                            <label for="topicAnswerB" class="col-sm-2 control-label">选项B</label>
                            <div class="col-sm-8">
                           <input type="text" class="form-control" id="topicAnswerB" placeholder="选项B" check-type="required" required-message="请填写答案B" value="<s:property value="redioTopic.answerB" />"/>
                            </div>
                    </div>
                     <div class="form-group">
                            <label for="topicAnswerC" class="col-sm-2 control-label">选项C</label>
                            <div class="col-sm-8">
                           <input type="text" class="form-control" id="topicAnswerC" placeholder="选项C" check-type="required" required-message="请填写答案C" value="<s:property value="redioTopic.answerC" />"/>
                            </div>
                    </div>
                     <div class="form-group">
                            <label for="topicAnswerD" class="col-sm-2 control-label">选项D</label>
                            <div class="col-sm-8">
                           <input type="text" class="form-control" id="topicAnswerD" placeholder="选项D" check-type="required" required-message="请填写答案D" value="<s:property value="redioTopic.answerD" />"/>
                            </div>
                    </div>
                     <div class="form-group">
                            <label for="topicAnswerD" class="col-sm-2 control-label">选项E</label>
                            <div class="col-sm-8">
                           <input type="text" class="form-control" id="topicAnswerE" placeholder="选项E,没有请留空"   value="<s:property value="redioTopic.answerE" />"/>
                            </div>
                    </div>
                      <div class="form-group">
                            <label for="topicAnswer" class="col-sm-2 control-label">答案</label>
                            <div class="col-sm-8">              
                           <select class="form-control" id="topicAnswer"  >      
                           <option  value="<s:property value="redioTopic.answer" />" selected><s:property value="redioTopic.answer" /></option>   
                             <option value="1">A</option>
                               <option value="2">B</option>
                                <option value="3">C</option>
                                 <option value="4">D</option>
                                 <option value="5">E</option>
                           </select>
                            </div>
                    </div>
                    <div class="form-group">
                            <label for="topicDescription" class="col-sm-2 control-label">题目解析</label>
                            <div class="col-sm-8">
                            <textarea id="topicDescription" name="topicDescription" class="form-control" placeholder="题目解析 "><s:property value="redioTopic.topic.description" /></textarea>
                            </div>
                    </div >
                    <div class="form-group">
                            <label for="topicDescription" class="col-sm-2 control-label">题目来源</label>
                            <div class="col-sm-8">
                            <textarea id="topicSource" name="topicSource" class="form-control" placeholder="题目来源 "><s:property value="redioTopic.topic.sourceString" /></textarea>
                            </div>
                    </div >
                    <div class="form-group">
                      <label for="courseIntroduce" class="col-sm-2 control-label"></label>
                            <div class="col-sm-8">
                     <s:if test="redioTopic !=null"><button type="button" id="course_btn_update" class="btn btn-info btn-course" >更新</button></s:if><s:else>       
                    <button type="button" id="course_btn" class="btn btn-info btn-course" >提交</button>
                    </s:else>
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
			         var title=$("#topicTitle").val();
			         var answerA=$("#topicAnswerA").val();
			            var answerB=$("#topicAnswerB").val();
			               var answerC=$("#topicAnswerC").val();
			                  var answerD=$("#topicAnswerD").val();
			                 var answerE=$("#topicAnswerE").val();
			               var answer=$("#topicAnswer").val();
			                  var decription=$("#topicDescription").val();
			                  var topicSource = $("#topicSource").val();
			                  var topicType = $("#topicType").val();
			                   var courseChapter = $("#courseChapter").val();
			                   var tid=$("#topicTid").val();
			                   var courseId = $("#course").val();
			          addTopicRedio.addForm(title,answerA,answerB,answerC,answerD,answerE,answer,decription,topicType,topicSource,courseChapter,tid,courseId,function(result){
                         if(result){
                               $("#course_btn").html("提交成功，请关闭窗口"); 
                               $("input").val("");
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
			         var title=$("#topicTitle").val();
			         var answerA=$("#topicAnswerA").val();
			            var answerB=$("#topicAnswerB").val();
			               var answerC=$("#topicAnswerC").val();
			                  var answerD=$("#topicAnswerD").val();
			                      var answerE=$("#topicAnswerE").val();
			               var answer=$("#topicAnswer").val();
			                  var decription=$("#topicDescription").val();
			                  var id=$("#id").val();
			                  var topicSource = $("#topicSource").val();
			                  var topicType = $("#topicType").val();
			                   var courseChapter = $("#courseChapter").val();
			                   var tid=$("#topicTid").val();
			          addTopicRedio.updateFrom(id,title,answerA,answerB,answerC,answerD,answerE,answer,decription,topicType,topicSource,courseChapter,tid,function(result){
                         if(result){
                               $("#course_btn_update").html("提交成功，请关闭窗口"); 
                               $("input").val("");
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
