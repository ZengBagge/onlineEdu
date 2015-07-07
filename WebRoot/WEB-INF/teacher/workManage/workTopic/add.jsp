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
    <title>湖南科技大学在线学习系统|题库管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=basePath%>inc/css/bootstrap.min.css">
   <link rel="stylesheet" href="<%=basePath%>inc/css/bootstrap-theme.min.css">
   <link rel="stylesheet" href="<%=basePath%>inc/css/style.css">
   <script src="<%=basePath%>inc/js/jquery-1.11.2.min.js"></script>
     <script src="<%=basePath%>inc/js/ajaxfileupload.js"></script>
   <script src="<%=basePath%>inc/js/bootstrap.min.js"></script>
      <script src="<%=basePath%>inc/js/form.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

  <script>
   
	   var menunail=0;
	    $(function(){
		$("#menu-nail li").each(function(){
			$(this).click(function(){
			   if(menunail==0)
				{ 
                $("#main").addClass("menu-collapse");
                $("#menu-nail").attr("title","展开");
                $(".read_box").css("margin-left","80px");
				  menunail=1;
				}
				else
				{
				  $("#main").removeClass();
                $("#menu-nail").attr("title","折叠");
                $(".read_box").css("margin-left","322px");
				  menunail=0;
				}
			});
		});
	});
	$(function(){
	    $("#tagTreeForGroup_1_ul  li").each(function(){
	      $(this).hover(function(){
	          $(this).addClass("hover");
	      },function(){
	         $(this).removeClass("hover");
	      });
	    });
	});

	    $(function(){
		$(".level1").each(function(){
			$(this).click(function(){
			     courseId=$(this).attr("course-id");
			     courseTitle=$(this).attr("title");

			     $("#path").html("请为（"+courseTitle+"）添加组卷");
				$("#topic_iframe").attr("src","workTopic!addInfo?courseId="+courseId);
			});
		});
	});
	
  </script>
  </head>
  
  <body>
         <div id="main" class="">
             <section id="menu-tree" class="note group">
                       <div class="toolbar"><ul class="icon-hb-parent" id="menu-nail" title="折叠"><li class="icon icon-to-right icon-menu-switch"></li></ul></div>
                       <div id="menu-content" class="content lastModify">
                           <ul class="note">
                              <li id="category" class="menu-item-area icon-ab-parent">
                                 <i class="icon icon-msz icon-folders-fat" id="category-icon"></i>
                                  <div class="dcb pop-float pop-left">
                                      <ul id="tagTreeForGroup" class="ztree" indentlevel="2" style="-moz-user-select: none; display: block;">
                                            <li id="tagTreeForGroup_1" class="level0" treenode="" hidefocus="true" tabindex="0">
                                                <a style="" target="_blank" onclick="" treenode_a="" class="level0" id="course_Group" title="我的课程">
                                                    <span id="tagTreeForGroup_1_span">选择课程</span>
                                                    <span class="button icon-hb icon-ab setting" id="tagTreeForGroup_1_setting"></span>
                                                </a>
                                                <s:action name="course!getCourse" namespace="/" var="course"  ></s:action>
                                  
                                                 <ul id="tagTreeForGroup_1_ul" class="level0 " style="display: block;">
                                                  <s:iterator value="#course.courseList" var="u">
                                                    <li treenode="" hidefocus="true" tabindex="0" class="level1" course-id="<s:property  value="#u.id" />" title="<s:property  value="#u.titleString" />">
                                                    <a title="<s:property  value="#u.titleString" />" style="" target="_blank" onclick="" treenode_a="" class="level1" id="level1_<s:property  value="#u.id" />_a">
                                                    <span id="tagTreeForGroup_4_span"><s:property  value="#u.titleString" /></span>
                                                    <span class="button icon-hb icon-ab setting" id="tagTreeForGroup_4_setting"></span>
                                                    </a>
                                                  </li>
                                                      </s:iterator>
                                                 </ul>
                                            </li>
                                          
                                      </ul>
                                  </div>
                              </li>
                           </ul>
                       </div>
             </section>
             <section class="read_box">
                   <div class="toolbar">
                       <div id="path" title="全部消息">选择课程添加组卷</div>

                    </div>
                       <div class="content" style="">
                           <iframe id="topic_iframe" class="main_iframe topic_iframe" src=""></iframe>
                       </div>
             </section>
         </div>
  </body>
</html>
