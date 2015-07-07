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
	<meta http-equiv="keywords" content="<s:property value="blog.keywords"/>">
	<meta http-equiv="description" content="<s:property value="blog.description"/>">
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
    <section class="content-wrap" style="margin-bottom:20px;">
        <div class="container">
            <div class="row">

				<main class="col-md-8 main-content">  
				      <article class="post tag-opensource">
				           <div class="post-head">
						        <h1 class="post-title"><a href="blog!blog?bid=<s:property value="#pl.id"/>"><s:property value="blog.titleString"/></a></h1>
						        <div class="post-meta">
						            <span class="author">作者：<a href="#"><s:property value="blog.writer"/></a></span> •
						            <time datetime="<s:date name="blog.publishDate"  format="yyyy-MM-dd" />" class="date"><s:date name="blog.publishDate"  format="yyyy-MM-dd" /></time>
						        </div>
						    </div>
						    <div class="post-content" id="post-content">
						       <s:property value="blog.contentString" escape="false"/>
						    </div>
				          <footer class="post-footer clearfix">
						        <div class="pull-left tag-list">
						            <i class="fa fa-folder-open-o"></i>
						            <s:iterator value="blog.tags" var="pt"> 
						            <a href="/blog!tagList?tagId=<s:property value="#pt.id"/>"><s:property value="#pt.name"/></a>
						            ,
						           </s:iterator> 
						        </div>

					        <div class="pull-right share">
					           <div class="bshare-custom icon-medium-plus"><a title="分享到" href="http://www.bShare.cn/" id="bshare-shareto" class="bshare-more">分享到</a><a title="分享到QQ空间" class="bshare-qzone"></a><a title="分享到新浪微博" class="bshare-sinaminiblog"></a><a title="分享到人人网" class="bshare-renren"></a><a title="分享到腾讯微博" class="bshare-qqmb"></a><a title="分享到网易微博" class="bshare-neteasemb"></a><a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a><span class="BSHARE_COUNT bshare-share-count">0</span></div><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=2&amp;lang=zh"></script><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
                        </div>
    </footer>
				      </article>
               </main>

                <aside class="col-md-4 sidebar">
                <!-- start widget -->
                <!-- end widget -->	
                
                <!-- start tag cloud widget -->
                <div class="widget">
                	<h4 class="title">博文分类</h4>
                	<div class="content community blogTyleList">
                		<s:action name="blogType!getBlogType" var="bg"/>
                		<ul>
                		  <li class="nav_type_1_li">
                		      <a class="nav_type_1_a" href="blog!list?typeId=<s:property value="blog.blogType.id"/>" ><s:property value="blog.blogType.typename"/></a>
                		   </li> 
                		 <s:iterator value="#bg.typeList" var="bt">
                		 
                		  <s:if test="#bt.father.id==blog.blogType.father.id">
                		   <li class="nav_type_1_li">
                		      <a class="nav_type_1_a" href="blog!list?typeId=<s:property value="#bt.id"/>" ><s:property value="#bt.typename"/></a>
                		   </li> 
                		   </s:if>
                		 </s:iterator>  
                		</ul>
                	</div>
                </div>
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
