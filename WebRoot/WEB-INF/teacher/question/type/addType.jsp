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
    <title>湖南科技大学在线学习系统|题型管理</title>
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
   <script SRC="dwr/interface/addQuestionType.js"></script>
  </head>
  
  <body class="fram_body">
           <div class="course_main">
               <form action="course!addForm" method="post" class=" form-horizontal">
                    <div class="form-group">
                            <label for="typeName" class="col-sm-2 control-label">题型名</label>
                            <div class="col-sm-8">
                           <input type="text" class="form-control" id="courseName" placeholder="题型名称" check-type="required" required-message="请填写题型名" />
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
			          $(this).html("提交中。。"); 
			          var typeName=$("#courseName").val();
			          addQuestionType.addForm(typeName,function(result){
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

  </body>
</html>
