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
    <title>湖南科技大学在线学习系统|消息中心</title>
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
  <script src="<%=basePath%>inc/js/message.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
 <script SRC="dwr/engine.js"></script>
<script SRC="dwr/util.js"></script>
<script>
var loginUser= <s:property value="#session.user.id" />;
var baseUrl="<%=basePath%>";
</script>
<script SRC="dwr/interface/message.js"></script>
<script>
	   var menunail=0;
	    $(function(){
		$("#menu-nail li").each(function(){
			$(this).click(function(){
			   if(menunail==0)
				{ 
                $("#main").addClass("menu-collapse");
                $("#menu-nail").attr("title","展开");
                $(".message_box").css("margin-left","438px");
				  menunail=1;
				}
				else
				{
				  $("#main").removeClass("menu-collapse");
                $("#menu-nail").attr("title","折叠");
                $(".message_box").css("margin-left","678px");
				  menunail=0;
				}
			});
		});
	var ou=0;	
	$(".class_li  .class_a").each(function(){
	     $(this).click(function(){
					 if(ou ==0)
					  {
					  	   $(this).find(".button").removeClass("ico_close").addClass("ico_open");
					   $(this).next().slideDown();
					   ou =1;
					  }else{
					       $(this).find(".button").removeClass("ico_open").addClass("ico_close");
					    $(this).next().slideUp();
					    ou=0;
					  }

	          });	
	    });
	 });	
	$(function(){
	 $("#main .note li").hover(function(){
	  $(this).find("div").addClass("active");
	 },function(){
	  $(this).find("div").removeClass("active");
	 });
	});
	
</script>	
  </head>
  
  <body class="message">
         <div id="main" class="">
             <section id="menu-tree" class="note group">
                       <div class="toolbar"><ul class="icon-hb-parent" id="menu-nail" title="折叠"><li class="icon icon-to-right icon-menu-switch"></li></ul></div>
                       <div id="menu-content" class="content lastModify">
                           <ul class="note">
                              <li class="menu-item-area icon-ab-parent active" id="send" data-id="1">
                                  <i class="icon icon-msz icon-write-fat" id="lately-icon" title="发送信息"></i>
                                  <div class="dcb pop-float pop-left" ><p>发送消息</p></div></li>
                                 <li class="menu-item-area icon-ab-parent" id="receiveBox" data-id="2">
                                  <i class="icon icon-msz icon-bubble view-msg-btn" title="收件箱"></i>
                                  <div class="dcb pop-float pop-left" ><p >收件箱</p><s:if test="newNumber != 0"><span class="badge"><s:property value="newNumber"/></span></s:if></div>
                                  </li>   
                                   <li class="menu-item-area icon-ab-parent" id="sendBox" data-id="3">
                                  <i class="icon icon-msz icon-back-circle view-msg-btn" title="发件箱"></i>
                                  <div class="dcb pop-float pop-left" ><p >发件箱</p></div></li>
                                      <li class="menu-item-area icon-ab-parent" id="lazyBox" data-id="4">
                                 <i class="icon icon-msz icon-bubble view-msg-btn"  title="垃圾箱"></i>
                                  <div class="dcb pop-float pop-left" ><p >垃圾箱</p></div></li>            
                                      </ul>
                          
                        </div>
             </section>
              <section class="msglist" id="note-list">
              <div class="toolbar">
              <div id="path" title="联系人">联系人</div>
              <div  id="notelist-tool"></div>
             </div>
             <div id="note-list-content" class="content">
             <div class="user active uu" id="note-list-content_1">
             <s:action name="message!getLinkmanList" var="m"></s:action>
               <ul class="class_ul ztree"  id="shortcut">
                   <li class="class_li">
                    <a class="class_a" data-id="">
                    <span id="shortcut_21_ico" class="button ico_close" title="" style=""></span>
                    <span>我的老师</span>
                    </a>
                    <ul class="studentUser_ul">
                       <s:iterator value="#m.teachers" var="mt">
		                      <li class="studentUser_li" data-id="<s:property value="#mt.userCommon.id" />" data-name="<s:property value="#mt.userCommon.tname" />">
		                      <div class="avatar" ><img id="avatar" src="<%=basePath%>inc/image/avatar/<s:property value="#mt.userCommon.picString" />"/></div>
		                      <a class="studentUser_a"><span class="name"><s:property value="#mt.userCommon.tname" />(<s:property value="#mt.userCommon.uid" />)</span><br/><span></span></a>
		                      </li>
                      </s:iterator>
                   </ul>
                 </li>
                 <s:iterator value="#m.classes" var="mc">
                 <li class="class_li">
                    <a class="class_a" data-id="<s:property  value="#mc.id" />">
                    <span id="shortcut_21_ico" class="button ico_close" title="" style=""></span>
                    <span><s:property  value="#mc.name" /></span>
                    </a>
                   <ul class="studentUser_ul">
                       <s:iterator value="#m.studentSet" var="ms">
                               <s:if test="#ms.classes.id == #mc.id">
		                      <li class="studentUser_li" data-id="<s:property value="#ms.userCommon.id" />" data-name="<s:property value="#ms.userCommon.tname" />">
		                      <div class="avatar" ><img id="avatar" src="<%=basePath%>inc/image/avatar/<s:property value="#ms.userCommon.picString" />"/></div>
		                      <a class="studentUser_a"><span class="name"><s:property value="#ms.userCommon.tname" />(<s:property value="#ms.userCommon.uid" />)</span><br/><span><s:property value="#ms.autograph" /></span></a>
		                      </li>
                               </s:if>
                      </s:iterator>
                   </ul>
                 </li>
                </s:iterator>
               </ul>
             </div>
             <div class="receiveBox uu  dd"  id="note-list-content_2">
                <ul class="studentUser_ul ztree">
                    <s:iterator value="receiveBox.list" var="rl">
                      <li class="studentUser_li" data-id="<s:property value="#rl.sender.id" />" data-name="<s:property value="#rl.sender.tname" />">
		                      <div class="avatar" ><img id="avatar" src="<%=basePath%>inc/image/avatar/<s:property value="#rl.sender.picString" />"/></div>
		                      <a class="studentUser_a"><span class="name"><s:property value="#rl.sender.tname" />(<s:property value="#rl.sender.uid" />)<s:if test="#rl.readed == 0"><span style="color:red;">*</span></s:if></span><br/><span><s:property value="#rl.message.content" /></span></a>
		                <span class="button icon-hb icon-ab setting activeShow" data-id="<s:property value="#rl.sender.id" />" title="操作"></span>
		             </li>
                    </s:iterator>
                  
                </ul>
                  <s:if test="receiveBox.list.size() == 10">
                    <div class="head" onclick="moreReceive(<s:property value="receivePage+1" />)"><span class="x">加载更多</span></div>
                    </s:if>
             </div>
             <div class="sendBox uu dd"  id="note-list-content_3">
                <ul class="studentUser_ul ztree">
                    <s:iterator value="sendBox.list" var="rl">
                      <li class="studentUser_li" data-id="<s:property value="#rl.receiver.id" />" data-name="<s:property value="#rl.receiver.tname" />">
		                      <div class="avatar" ><img id="avatar" src="<%=basePath%>inc/image/avatar/<s:property value="#rl.sender.picString" />"/></div>
		                      <a class="studentUser_a"><span class="name"><s:property value="#rl.receiver.tname" />(<s:property value="#rl.receiver.uid" />)</span><br/><span><s:property value="#rl.message.content" /></span></a>
		             </li>
                    </s:iterator>
                   
                </ul>
                 <s:if test="sendBox.list.size() == 10">
                    <div class="head" onclick="moreSend(<s:property value="sendPage+1" />)"><span class="x">加载更多</span></div>
                    </s:if>
             </div>
              <div class="rubbishBox uu dd"  id="note-list-content_4">
                <ul class="studentUser_ul ztree">
                    <s:iterator value="rubbishBox.list" var="rl">
                      <li class="studentUser_li" data-id="<s:property value="#rl.sender.id" />" data-name="<s:property value="#rl.sender.tname" />">
		                      <div class="avatar" ><img id="avatar" src="<%=basePath%>inc/image/avatar/<s:property value="#rl.sender.picString" />"/></div>
		                      <a class="studentUser_a"><span class="name"><s:property value="#rl.sender.tname" />(<s:property value="#rl.sender.uid" />)</span><br/><span><s:property value="#rl.message.content" /></span></a>
		             </li>
                    </s:iterator>
                    
                </ul>
                 <s:if test="rubbishBox.list.size() == 10">
                    <div class="head" onclick="moreRubbish(<s:property value="rubbishPage+1" />)"><span class="x">加载更多</span></div>
                    </s:if>
             </div> 
             </div>
             </section>
             <section id="message_content" class="message_box">
                <div class="toolbar">
              <div id="path" title="全部消息">消息正文</div>
              <div  id="notelist-tool"></div>
             </div>
             <div id="note-list-content" class="content">
               <div class="message_list">
                  <ul class="message_list_ul">
                  </ul>
               </div>
              <div class="message_form">
                <textarea rows="2" cols="" id="message_text" ></textarea>
                <div class="submit">
                   <button class="btn btn-lg btn-message" id="form_message" data-id="">发送</button>
                </div>
              </div>
             </div>
             </section>
             
          </div>

  </body>
</html>
