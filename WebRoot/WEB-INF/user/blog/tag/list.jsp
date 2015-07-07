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

				<main class="col-md-8 main-content">  
				      <s:iterator value="pageBean.list" var="pl">
						<article class="post tag-opensource" id="<s:property value="#pl.id"/>">
						
						    <div class="post-head">
						        <h1 class="post-title"><a href="blog!blog?bid=<s:property value="#pl.id"/>"><s:property value="#pl.titleString"/></a></h1>
						        <div class="post-meta">
						            <span class="author">作者：<a href="#"><s:property value="#pl.writer"/></a></span> •
						            <time datetime="<s:date name="#pl.publishDate"  format="yyyy-MM-dd" />" class="date"><s:date name="#pl.publishDate"  format="yyyy-MM-dd" /></time>
						        </div>
						    </div>
						    <div class="post-content">
						        <p><s:property value="#pl.shortMessage"/></p>
						    </div>
						    <div class="post-permalink">
						        <a class="btn btn-default" href="blog!blog?bid=<s:property value="#pl.id"/>">阅读全文</a>
						    </div>
						
						    <footer class="post-footer clearfix">
						        <div class="pull-left tag-list">
						            <i class="fa fa-folder-open-o"></i>
						      <s:iterator value="#pl.tags" var="pt"> 
						            <a href="/blog!tagList?tagId=<s:property value="#pt.id"/>"><s:property value="#pt.name"/></a>
						            ,
						           </s:iterator> 
						        </div>
						    </footer>
						</article>
                     </s:iterator>
<nav role="navigation" class="pagination">
    <s:if test="pageBean.totalPage==1 ">
    <span class="page-number">第 <s:property value="pageBean.currentPage"/> 页 ⁄ 共<s:property value="pageBean.totalPage"/> 页</span>
    </s:if>
    <s:elseif test="pageBean.currentPage==1 &&pageBean.totalPage!=0">
    <span class="page-number">第 <s:property value="pageBean.currentPage"/> 页 ⁄ 共<s:property value="pageBean.totalPage"/> 页</span>
     <a href="blog!index?page=<s:property value="page+1"/>" class="older-posts" title="下一页"><i class="fa fa-chevron-right fa-1x"></i></a>
    </s:elseif>
    <s:elseif test="pageBean.currentPage==pageBean.totalPage">
     <a href="blog!index?page=<s:property value="page-1"/>" class="older-posts" title="上一页"><i class="fa fa-chevron-left fa-1x"></i></a>
     <span class="page-number">第 <s:property value="pageBean.currentPage"/> 页 ⁄ 共<s:property value="pageBean.totalPage"/> 页</span>
    </s:elseif><s:else >
         <a href="blog!index?page=<s:property value="page-1"/>" class="older-posts" title="上一页"><i class="fa fa-chevron-left fa-1x"></i></a>
     <span class="page-number">第 <s:property value="pageBean.currentPage"/> 页 ⁄ 共<s:property value="pageBean.totalPage"/> 页</span>
     <a href="blog!index?page=<s:property value="page+1"/>" class="older-posts" title="下一页"><i class="fa fa-chevron-right fa-1x"></i></a>
    </s:else>
    
</nav>


</main>

                <aside class="col-md-4 sidebar">  
                    <!-- start tag cloud widget -->
                <div class="widget">
                	<h4 class="title">标签云</h4>
                	<div class="content tag-cloud">
                	 <s:action name="blogTag!getCloudTags" var="bg"/>
                	   <s:iterator value="#bg.blogTags" var="bb">
                		<a href="blogTag!list?tid=<s:property value="#bb.id"/>"><s:property value="#bb.name"/></a>
					  </s:iterator>
                	</div>
                </div>
         </aside>

            </div>
        </div>
    </section>
  </body>
</html>
