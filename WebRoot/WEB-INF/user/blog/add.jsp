<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@  taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
     <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>湖南科技大学在线学习系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="异形网络工作室，异形网络教育最佳平台">
	<meta http-equiv="description" content="异形网络工作室，异形网络教育最佳平台">
	<link rel="stylesheet" href="<%=basePath%>inc/css/bootstrap.min.css">
   <link rel="stylesheet" href="<%=basePath%>inc/css/bootstrap-theme.min.css">
  <link href="//netdna.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
      <link rel="stylesheet" href="<%=basePath%>inc/css/show.css">
   <script src="<%=basePath%>inc/js/jquery-1.11.2.min.js"></script>
   <script src="<%=basePath%>inc/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>inc/js/form.js"></script>
       <script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor/ueditor.all.min.js"> </script>
    <link rel="stylesheet" href="<%=basePath%>ueditor/themes/default/ueditor.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
  <body>

    <section class="content-wrap">
        <div class="container">
            <div class="row">
              	<form action="blog!publish" method="post" class="blog_form">
				<main class="col-md-8 main-content">  
			        <div class="form-group row">
                            <label for="titleString" class="col-sm-2 control-label" style="text-align:center;height:34px;margin:0;line-height:34px; border: 1px solid #ccc;">标题</label>
                            <div class="col-sm-10">
                           <input type="text" class="form-control" name="titleString" placeholder="新博客" check-type="required" required-message="请填写标题" value="<s:property value="titleString"/>"/>
                            </div>
                    </div>
                    <div class="form-group row">
				      <textarea id="contentString" style="width:100%;height:450px;" name="contentString"><s:property value="contentString"/></textarea>
						<script type="text/javascript">
						    var editor = new baidu.editor.ui.Editor();
						    editor.render("contentString");
						</script>
				 </div>		
              </main>

                <aside class="col-md-4 sidebar">
                    <div class="form-group row">
                            <label for="tagString" class="col-sm-2 control-label" style="text-align:center;height:34px;margin:0;line-height:34px; border: 1px solid #ccc;">标签</label>
                            <div class="col-sm-10">
                           <input type="text" class="form-control" name="tagString" placeholder="标签" check-type="required" required-message="请填写关键词" value="<s:property value="tagString"/>" />
                            </div>
                    </div>
                     <div class="form-group row">
                            <label class="col-sm-2 control-label" style="text-align:center;height:34px;margin:0;line-height:34px; border: 1px solid #ccc;">分类</label>
                            <div class="col-sm-10">
                            <s:action name="blogType!getBlogType" var="bg"/>
                           <select class="form-control" name="typeId"  > 
                            <s:if test="typeId != 0"> <option value="<s:property value="typeId"/>">分类已选</option></s:if>        
                              <s:iterator value="#bg.typeList" var="bt">
                              <option value="<s:property value="#bt.id"/>"><s:if test="#bt.grade==2">----<s:property value="#bt.typename"/></s:if><s:else><s:property value="#bt.typename"/></s:else></option>
                              </s:iterator>
                           </select>
                            </div>
                    </div>
                     <div class="form-group row">
                            <label class="col-sm-2 control-label" style="text-align:center;height:34px;margin:0;line-height:34px; border: 1px solid #ccc;">摘要</label>
                            <div class="col-sm-10">
                                <textarea id="courseIntroduce" name="shortMessage" class="form-control" placeholder="本文摘要" value="<s:property value="titleString"/>"></textarea>
                            </div>
                    </div>
                     <div class="form-group row">
                            <label for="writer" class="col-sm-2 control-label" style="text-align:center;height:34px;margin:0;line-height:34px; border: 1px solid #ccc;">作者</label>
                            <div class="col-sm-10">
                           <input type="text" class="form-control" name="writer" placeholder="作者" check-type="required" required-message="请填写作者" value=" <s:property value="#session.user.tname"/>"/>
                            </div>
                    </div>
                     <div class="form-group row">
                            <label for="decription" class="col-sm-2 control-label" style="text-align:center;height:34px;margin:0;line-height:34px; border: 1px solid #ccc;">描述</label>
                            <div class="col-sm-10">
                                <textarea id="courseIntroduce" name="decription" class="form-control" placeholder="内容描述" ></textarea>
                            </div>
                    </div>
                     <div class="form-group row">
                            <label for="keyword" class="col-sm-2 control-label" style="text-align:center;height:34px;margin:0;line-height:34px; border: 1px solid #ccc;">关键</label>
                            <div class="col-sm-10">
                                <textarea id="courseIntroduce" name="keyword" class="form-control" placeholder="关键词" ></textarea>
                            </div>
                    </div>
                    <div class="form-group">
                      <label for="courseIntroduce" class="col-sm-2 control-label"></label>
                            <div class="col-sm-10">
                    <button type="button" id="course_btn" class="btn btn-info btn-course" style="width:100%;">提交</button>
                     </div>
                    </div>
               </aside>
              </form>
            </div>
        </div>
    </section>
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
	                  $("form").submit();
			    }
			   });
			    });
</script>
  </body>
</html>
