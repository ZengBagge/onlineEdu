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
    <title>湖南科技大学在线学习系统</title>
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
<script SRC="dwr/interface/classes.js"></script>
<script>
  $(function(){
	  $("#classes_btn").click(function(){
		  $(".addClasses").show();
		  $("input[name='classesUid']").focus();
	  });
	  
	  $("#classes_form").click(function(){
		  var classUid = $("input[name='classesUid']").val();
		  var name = $("input[name='name']").val();
		  var major = $("select[name='major']").val();

		  classes.addClasses(classUid,name,major,function(result){
			  if(result){
				  location.reload();
			  }else{
				  alert("添加失败");
			  }
		  });
	  });
  });
</script>
  </head>
  
  <body>
        <table class="table table-bordered">
          <thead>
          <tr>
          <th>班级代码</th>
         <th>班级名称</th>
           <th>所属专业</th>
            <th>班长</th>
             <th width="10%">操作</th>
                   </tr>
          </thead>
           <tbody>
           <s:iterator value="classesList" var="c">
               <tr>
                <td class="info_value"><s:property value="#c.classUid" /></td>
               <td class="info_value"><s:property value="#c.name" /></td>
               <td class="info_value"><s:property value="#c.major.nameString" /></td>
               <td class="info_value"><s:property value="#c.monitor.userCommon.tname" /></td>
               </tr>
          </s:iterator>
          <s:action name="major!getAllMajor" var="m"></s:action>
               <tr class="addClasses" style="display:none;">
                <td class="info_value"><input name="classesUid" value="" style="width:100%;"/></td>
               <td class="info_value"><input name="name" value="" style="width:100%;"/></td>
               <td class="info_value"><select name="major" style="width:100%;"><s:iterator value="#m.majorList" var="mm"><option value="<s:property value="#mm.id"/>"><s:property value="#mm.nameString"/></option></s:iterator></select></td>
               <td class="info_value"><button type="button" id="classes_form" class="btn btn-info btn-course">提交</button></td>
               </tr>
           </tbody>
        </table>
        <div class="form-group">
            <div class="col-sm-10">
                    <button type="button" id="classes_btn" class="btn btn-info btn-course">添加班级</button>
            </div>
       </div>

  </body>
</html>
