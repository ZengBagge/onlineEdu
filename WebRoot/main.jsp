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
    <script>
     var mainfocs=0;
       $(function(){
		$(".avatar").each(function(){
			$(this).click(function(){
				$("#pop-user-setting").toggle();
			});
		});
	});
	 $(function(){
		$("#sc-entry").each(function(){
			$(this).click(function(){
			   if(mainfocs==0){
				$("aside").css("margin-left",0);
				$("#sc-entry").attr("title","关闭快捷菜单");
				$("body").addClass("focus-sidebar");
				mainfocs=1;
				}
			  else
			  {
			    $("aside").css("margin-left",-200);
				$("#sc-entry").attr("title","打开快捷菜单");
				$("body").removeClass("focus-sidebar");
				mainfocs=0;
			   }
			});
		});
	});
	  var levelfocs=0;
	    $(function(){
		$(".level1_a").each(function(){
			$(this).click(function(){
			   if(levelfocs==0)
				{ 
				$(this).find(".button").removeClass("ico_close").addClass("ico_open");
				$(this).next("ul").slideDown();
				  levelfocs=1;
				}
				else
				{
				$(this).find(".button").removeClass("ico_open").addClass("ico_close");
				$(this).next("ul").slideUp();
				  levelfocs=0;
				}
			});
		});
	});
	  
	    $(function(){
		$("#user_menu li").each(function(){
			$(this).click(function(){
				$("#iframe").attr("src",$(this).attr("data-url"));
				$("#myModalLabel").html($(this).html());
			});
		});
	});
	
		    $(function(){
		$(".ztree a").each(function(){
			$(this).click(function(){
			  if($(this).attr("data-url") != ""){
				$("#main_iframe").attr("src",$(this).attr("data-url"));
				$("#kbname").html($(this).attr("title"));
				}	
			});
		});
		$("#user-msg").click(function(){
		   $("#main_iframe").attr("src","message!index?moth=1");
				$("#kbname").html("消息中心");
		});
	});
	
	
    </script>
  </head>
  
  <body>
  <aside id="index_main"><div class="logo"><h3>异形网络教育</h3></div>
  <s:action name="menuTree!getTree" namespace="/" var="m"></s:action>
  <ul class="ztree" indentlevel="1" id="shortcut" style="-moz-user-select: none;">
   <s:iterator value="#m.menuList" var="u">
   <li treenode="" hidefocus="true" tabindex="0" class="level1" id="shortcut_<s:property  value="#u.id" />">
  <a data-url="<s:property  value="#u.urlString" />" title="<s:property  value="#u.titleString" />"  target="_blank"  class="level1_a" id="shortcut_<s:property  value="#u.id" />_a">
  <span treenode_switch="" class="button level0 switch noline_open" title="" id="shortcut_<s:property  value="#u.id" />_switch"></span>
  <s:if test="#u.chirden.isEmpty()">
  <span style="" class="button ico_docu" treenode_ico="" title="" id="shortcut_<s:property  value="#u.id" />_ico"></span>
  </s:if>
  <s:else>
  <span style="" class="button ico_close" treenode_ico="" title="" id="shortcut_<s:property  value="#u.id" />_ico"></span>
  </s:else>
  <span id="shortcut_<s:property  value="#u.id" />_span"><s:property  value="#u.titleString" /></span></a>
  <ul style="display:none;" class="level1_ul " id="shortcut_<s:property  value="#u.id" />_ul" >
			  <s:iterator value="#u.chirden"  var="c">
			  <li treenode="" hidefocus="true" tabindex="0" class="level2" id="shortcut_<s:property  value="#c.id" />">
			  <a data-url="<s:property  value="#c.urlString" />"  title="<s:property  value="#c.titleString" />" style="" target="_blank" class="level2_a" id="shortcut_<s:property  value="#c.id" />_a">
			  <span class="tmpIndent"></span>
			  <span treenode_switch="" class="button level1 switch noline_docu" title="" id="shortcut_<s:property  value="#c.id" />_switch"></span>
			  <span style="" class="button ico_docu" treenode_ico="" title="" id="shortcut_<s:property  value="#c.id" />_ico"></span>
			  <span id="shortcut_<s:property  value="#c.id" />_span"><s:property  value="#c.titleString" /></span></a></li>
			  </s:iterator>
  </ul>
  </li>
   </s:iterator>
</ul></aside>
  <div id="main">
     <header>
     <div id="sc-entry" title="启动快捷菜单"><ul><li class="iterm1"></li><li class="iterm2"></li><li class="iterm3"></li></ul></div>
     <div id="kb-info"><h3 id="kbname">异形网络教育</h3></div>
     <s:if test="#session.user==null">
           <a href="user!login">登录</a>
      </s:if>
      <s:else>
          <div class="user_info">
          <div class="" id="user-msg"><i class="icon-w icon-msz icon-bell-fill"></i><span  id="main_message_open">消息</span></div>
          <div class="avatar" >
          <img id="avatar" src="<%=basePath%>inc/image/avatar/<s:property value="#session.user.picString" />"/>
          <div class="menu context-menu has-icon showing" id="pop-user-setting" style="display:none;">
          <ul id="user_menu"><li data-id="user-name" class="wizui-contextmenu"><s:property value="#session.user.tname" /></li>
          <s:if test="#session.user.rule==1">
          <li id="bizManager" data-url="class!info" class="wizui-contextmenu" data-toggle="modal" data-target="#myModal"> <i class="icon icon-person-shield"></i>班级信息</li>
          <li id="account_setting" data-url="user!info" class="wizui-contextmenu" data-toggle="modal" data-target="#myModal"><i class="icon icon-cog"></i>帐户设置</li>
          <li id="helpDoc" class="wizui-contextmenu"><i class="icon icon-quz-circle"></i>使用帮助</li>
          </s:if>
          <s:elseif test="#session.user.rule==2">
             
          </s:elseif>
          <li id="logoff" class="wizui-contextmenu"><i class="icon icon-power-off"></i><a href="<%=basePath%>user!logout">注销</a></li></ul></div>
          </div>
          </div>
       </s:else>
        </header>
         <iframe id="main_iframe" class="main_iframe" src="index!show"></iframe>
     </div>
     <!-- 帐号管理-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog info-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"></h4>
      </div>
      <div class="modal-body">
          <div class="embed-responsive embed-responsive-16by9">
               <iframe id="iframe" class="embed-responsive-item" src=""></iframe>
          </div>
      </div>

    </div>
  </div>
</div>

     
  </body>
</html>
