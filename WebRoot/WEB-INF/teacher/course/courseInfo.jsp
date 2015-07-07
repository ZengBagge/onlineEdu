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
       <script SRC="dwr/engine.js"></script>
   <script SRC="dwr/util.js"></script>
   <script SRC="dwr/interface/courseChapter.js"></script>
  </head>
  
  <body>
    <div class="container pub_introduction boxsh3 mt30">
      <div class="row ">
			    	<span style="border:1px solid #000000;padding:0;" class="tc col-xs-3 dib"><img src="<%=basePath%>inc/image/eg_1.png"></span>
		        <span class="dib vt fw col-xs-9" style="padding:0 15px;">
        	<strong class="f14">
            	<span data-id="<s:property  value="co.id" />" id="groupTitle" class="pub_title"><s:property  value="co.titleString" /></span><br>
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
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">章节信息</a></li>
  <li role="presentation"><a onclick="discuzCourse(<s:property value="co.id"/>)" data-id="<s:property value="#c2.id"/>" data-toggle="modal" data-target="#discuz" href="">课程讨论</a></li>
  </ul>
 <s:action name="courseChapter!getChapterList" namespace="/" var="c"  >
     <s:param name="courseId" value="courseId"></s:param>
 </s:action>
  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="home">
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
         <button type="button"class="btn btn-info btn-lg"  data-toggle="modal" data-target="#add_chapter"  >添加章节</button>
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
        <h4 class="modal-title" id="myModalLabel">同学讨论</h4>
      </div>
      <div class="modal-body">
          <iframe src="" id="discuz_iframe"></iframe> 
      </div>
    </div>
  </div>
</div>
<!--  添加章节-->
<div class="modal fade" id="add_chapter" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">添加章节信息</h4>
      </div>
      <div class="modal-body">
         <form action="course!addForm" method="post" class=" form-horizontal">
                    <div class="form-group">
                            <label for="courseName" class="col-sm-2 control-label">章节名称</label>
                            <div class="col-sm-8">
                           <input type="text" class="form-control" id="chapterName" placeholder="名称" check-type="required" required-message="请填写章节名" />
                           
                            </div>
                    </div>
                    <div class="form-group">
                            <label for="courseName" class="col-sm-2 control-label">排序</label>
                            <div class="col-sm-8">
                           <input type="text" class="form-control" id="chapterSort" placeholder="排序" check-type="number" required-message="请填写章节排序" />
                            </div>
                    </div>
                     <div class="form-group">
                            <label for="courseIntroduce" class="col-sm-2 control-label">章节介绍</label>
                            <div class="col-sm-8">
                            <textarea id="chapterMessage" name="chapterMessage" class="form-control" placeholder="章节简单介绍" ></textarea>
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
    </div>
  </div>
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
                       var title = $("#chapterName").val();
                       var sort = $("#chapterSort").val();
                       var message = $("#chapterMessage").val();
                       var courseId =$("#groupTitle").attr("data-id");
			          courseChapter.addChapter(title,sort,message,courseId,function(result){
                         if(result){
                               $("#course_btn").html("提交成功，请关闭窗口"); 
                               $("input").val("");
                               $("textarea").val("");
                          }else{
                               $("#course_btn").html("提交失败，请重试");
                         }
			      });
			    };
			   });
			    });
</script>
  </body>
