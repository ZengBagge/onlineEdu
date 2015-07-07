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
       var levelfocs=0;
       var QuestionType=new Array("无此类型","单选题","多选题","填空题","判断题");
	    $(function(){
		$(".level1").each(function(){
			$(this).click(function(){
			   if(levelfocs==0)
				{ 
				$(this).find(".switch").removeClass("noline_close").addClass("noline_open");
				$(this).find("ul").slideDown();
				  levelfocs=1;
				}
				else
				{
				$(this).find(".switch").removeClass("noline_open").addClass("noline_close");
				$(this).find("ul").slideUp();
				  levelfocs=0;
				}
			});
		});
	});
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
	var typeId=0;
	var courseId=0;
	    $(function(){
		$(".level2 a").each(function(){
			$(this).click(function(){
			     courseId=parseInt($(this).attr("course_id"));
			     typeId=parseInt($(this).attr("type_id"));
			     $("#course-input").val(courseId);
			     $("#type-input").val(typeId);
			     $("#dropable-tip").html("请选择题型为“"+QuestionType[typeId]+"”的本课程题库");
				$("#topic_iframe").attr("src","topic!list?mode="+typeId+"&addCourse="+courseId);
				$("#topic_add").attr("type-id",typeId).attr("course-id",courseId);
				$(".add").css("display","block");
			});
		});
	});
	$(function(){
	  $("#topic_add").click(function(){
	    $("#addTopic_iframe").attr("src","topic!add?addMode="+$(this).attr("type-id")+"&addCourse="+$(this).attr("course-id"));
	  });
	})
  </script>
  
     <script SRC="dwr/engine.js"></script>
   <script SRC="dwr/util.js"></script>
<script SRC="dwr/interface/topicAction.js"></script>
  </head>
  
  
  <body>
         <div id="main" class="">
             <section id="menu-tree" class="note group">
                       <div class="toolbar"><ul class="icon-hb-parent" id="menu-nail" title="折叠"><li class="icon icon-to-right icon-menu-switch"></li></ul></div>
                       <div id="menu-content" class="content lastModify">
                           <ul class="note">
                              <li class="menu-item-area icon-ab-parent" id="lately">
                                  <i class="icon icon-msz icon-write-fat" id="lately-icon"></i>
                                  <div class="dcb pop-float pop-left" id="lately-dcb"><p data-lang="docMenu.label.lastModify">最近修改</p></div></li>
                              <li id="category" class="menu-item-area icon-ab-parent">
                                 <i class="icon icon-msz icon-folders-fat" id="category-icon"></i>
                                  <div class="dcb pop-float pop-left">
                                      <ul id="tagTreeForGroup" class="ztree" indentlevel="2" style="-moz-user-select: none; display: block;">
                                            <li id="tagTreeForGroup_1" class="level0" treenode="" hidefocus="true" tabindex="0">
                                                <a style="" target="_blank" onclick="" treenode_a="" class="level0" id="tagTreeForGroup_1_a" title="我的课程">
                                                    <span id="tagTreeForGroup_1_span">课程题库管理</span>
                                                    <span class="button icon-hb icon-ab setting" id="tagTreeForGroup_1_setting"></span>
                                                </a>
                                                <s:action name="course!getCourse" namespace="/" var="course"  ></s:action>
                                  
                                                 <ul id="tagTreeForGroup_1_ul" class="level0 " style="display: block;">
                                                  <s:iterator value="#course.courseList" var="u">
                                                    <li treenode="" hidefocus="true" tabindex="0" class="level1" id="level1_<s:property  value="#u.id" />">
                                                    <a title="<s:property  value="#u.titleString" />" style="" target="_blank" onclick="" treenode_a="" class="level1" id="level1_<s:property  value="#u.id" />_a">
                                                    <span id="tagTreeForGroup_4_switch" class="button level1 switch noline_open" treenode_switch="" title=""></span>
                                                   <span id="tagTreeForGroup_4_ico" class="button ico_open" style="" treenode_ico="" title=""></span>
                                                    <span id="tagTreeForGroup_4_span"><s:property  value="#u.titleString" /></span>
                                                    <span class="button icon-hb icon-ab setting" id="tagTreeForGroup_4_setting"></span>
                                                    </a>
                                                    <ul style="" class="level1 " id="tagTreeForGroup_<s:property  value="#u.id" />_ul">
                                                      <s:iterator value="#u.questionType" var="u2">
                                                    <li treenode="" hidefocus="true" tabindex="0" class="level2" id="level2_<s:property  value="#u2.id" />">
                                                    <a title="<s:property  value="#u2.titleString" />" style="" target="_blank" course_id="<s:property  value="#u.id" />" type_id="<s:property  value="#u2.id" />" class="level2" id="level2_<s:property  value="#u2.id" />_a">
                                                    <span class="tmpIndent"></span>
                                                    <span id="tagTreeForGroup_5_switch" class="button level2 switch noline_docu" treenode_switch="" title=""></span>
                                                    <span id="tagTreeForGroup_5_ico" class="button ico_docu" style="" treenode_ico="" title=""></span>
                                                    <span id="tagTreeForGroup_5_span"><s:property  value="#u2.titleString" /></span>
                                                    <span class="button icon-hb icon-ab setting" id="tagTreeForGroup_5_setting"></span></a></li>
                                                    </s:iterator>
                                                    </ul></li>
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
                       <div id="path" title="全部消息">题目信息</div>
                       <div class="add" title="添加题目" >
                          <span class="addicon">
                              <i id="topic_add" data-target="#add_topic" data-toggle="modal" course-id=""  type-id=""  title="添加题目" class="icon icon-b icon-add-circle"></i>
                          </span>
                          <span id="before_add" class="before_add">添加题目</span>
                           <span class="addicon">
                           <i id="topic_add" data-target="#batch_topic" data-toggle="modal" course-id=""  type-id=""  title="批量导入" class="icon icon-b icon-add-circle"></i>
                          </span>
                          <span id="before_add" class="before_add">导入题库</span>
                       </div>
                    </div>
                       <div class="content" style="">
                           <iframe id="topic_iframe" class="main_iframe topic_iframe" src=""></iframe>
                       </div>
             </section>
         </div>
          <!-- 添加题库-->
<div class="modal fade" id="add_topic" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog info-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">添加题目</h4>
      </div>
      <div class="modal-body">
          <div class="embed-responsive embed-responsive-16by9">
               <iframe id="addTopic_iframe" class="embed-responsive-item" src=""></iframe>
          </div>
      </div>

    </div>
  </div>
</div>
          <!-- 导入题库-->
<div class="modal fade" id="batch_topic" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog info-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">导入题库</h4>
      </div>
      <div class="modal-body">
          <div id="upload-ctn">
          <form enctype="multipart/form-data" target="att_upload_iframe" method="POST" id="upload-form" class="dropable" action="">
          <p class="tip" data-lang="docDocument.label.attachmentDropUpload" id="dropable-tip">选择EXCEL03文件上传</p>
          <input type="file" name="file" id="file"/>
          <input type="hidden" name="course_ID" id="course-input" value="">
          <input type="hidden" name="type_ID" id="type-input" value="">
          <a class="btn btn-upload" data-lang="docDocument.label.attachmentChooseFile" href="javascript:void(0)" id="ghost-file-btn">选择文件</a>
          <p class="tip" id="upload-filename">附件大小不能超过 5M</p>
          </form>
        <table class="table table-bordered" style="display:none;">
            <tr><td>上传状态</td><td >成功</td></tr>
            <tr><td>文件名</td><td id="Filetitle" ></td></tr>
            <tr><td>导入课程</td><td id="FcourseId"></td></tr>
            <tr><td>导入题型</td><td id="FtypeId"></td></tr>
        </table>
          <a class="btn btn-upload" data-dismiss="modal"  href="javascript:void(0)" id="cancel-upload">取消</a>
          <a class="btn btn-upload" data-lang="common.label.upload" href="javascript:void(0)" id="submit-upload">上传</a>
            <a class="btn btn-upload" data-lang="common.label.upload" href="javascript:void(0)" id="import" style="display:none;">导入</a>
          <div id="msg"></div>
          </div>
      </div>

    </div>
  </div>
</div>
<script>
    $(function(){
       $("#ghost-file-btn").click(function(){
         $("#file").trigger("click");
       });
    });
    $(function(){
        $("#file").change(function(){
          var fileName=$(this).val();
          var att = Array();
         att= fileName.split(".");
         if(att[1]!="xls" && att[1]!="xlsx")
            {$(this).val("");
            $("#upload-filename").html("选择文件格式不正确，请选择xls文件");
            }
         else
         $("#upload-filename").html(fileName);
       });
    });
</script>
<script type="text/javascript">
$(function(){   
   $("#submit-upload").click(function(){
           if($("#file").val() !=""){
			     $.ajaxFileUpload( {
							url : 'fileUpload!fileUpload',//用于文件上传的服务器端请求地址
							secureuri : false,          //一般设置为false
							fileElementId : 'file',     //文件上传空间的id属性  <input type="file" id="file" name="file" />
							dataType : 'json',          //返回值类型 一般设置为json
							data : {courseId : $("#course-input").val(),typeId:$("#type-input").val()}, // 其它请求参数 
							success : function(data) {  
							          $("#FtypeId").html(QuestionType[$("#type-input").val()]);
							          $("#FcourseId").html($("#course-input").val());
							          $("#Filetitle").html(data.fileFileName);
							          $("form").hide();
							          $("table").show();
							          $("#import").show();
							          $("#submit-upload").hide();
                                       },
                            error : function(data, status, e) {  
                                    $("#msg").html("上传失败");
                            }  
               });   
               }
               else{  $("#msg").html("请选择文件");}
   });
});

$(function(){
  $("#import").click(function(){
   if($("#Filetitle").html()!="" && $("#FcourseId").html()!="" &&$("#FtypeId").html()!="")
   {
     topicAction.batchTopic($("#Filetitle").html(),$("#course-input").val(),$("#type-input").val(),function(result){
         if(result){
          var html='<tr><td>导入状态</td><td>导入成功</td></tr>';
          $("table").append(html);
             $("#import").hide();
         }else{
            var html='<tr><td>导入状态</td><td>导入失败，请检查数据格式</td></tr>';
             $("table").append(html);
         }
     });
   }
      else
      {  $("#msg").html("请选择文件");}
  });
});
</script>
  </body>
</html>
