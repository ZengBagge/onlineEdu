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
      <link rel="stylesheet" href="<%=basePath%>inc/css/student.css">
   <script src="<%=basePath%>inc/js/jquery-1.11.2.min.js"></script>
   <script src="<%=basePath%>inc/js/bootstrap.min.js"></script>
       <script SRC="dwr/engine.js"></script>
		<script SRC="dwr/util.js"></script>
		<script SRC="dwr/interface/addTopicRedio.js"></script>
		<script SRC="dwr/interface/addTopicFill.js"></script>
		<script SRC="dwr/interface/addTopicJudge.js"></script>
		<script SRC="dwr/interface/addTopicCheckBox.js"></script>
	    <script SRC="dwr/interface/exam.js"></script>
	    <script SRC="dwr/interface/collect.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script>
    var examTime=<s:property value="session.examTime"/>;
    var timerc=examTime*60; //全局时间变量（秒数）
    $(function() { 
    add(); //首次调用add函数
   }); 
    function add(){ //加时函数
        if(timerc > 0){ //如果不到3分钟
            --timerc; //时间变量自剪1
            var min =parseInt(timerc/60)+"分"; //写入分钟数
           var sec =parseInt(timerc%60/10).toString()+(timerc%10)+"秒"; //写入秒数（两位）
           $(".tim").html(min+sec);
            if(timerc == 180){
                  alert("考试只剩下3分钟,请注意时间");
            }
            setTimeout("add()", 1000); //设置1000毫秒以后执行一次本函数   
       }else if(timerc == 5390){
           var html = '<div class="container jumbotron">'+
        '<h1>考试结束</h1>'+
       ' <h2>小样儿，考试已经结束啦，交卷吧。</h2>'+
       ' <p class="masthead-button-links">'+
       '   <a role="button" target="_blank"  class="btn btn-lg btn-primary btn-shadow" onclick="hand()">交卷</a>'+
       ' </p>'+
      '  <ul class="masthead-links">'+
        '  <li>'+
         '   <a role="button" target="_blank" href="/">感谢你对异形网络教育的支持</a>'+
        '  </li>'+
      '  </ul>'+
     ' </div>';
          $(".conduct").html(html);
       }
    };
window.onbeforeunload = function(){
         return "确定离开页面吗？没有交卷离开页面数据不会保留"; 
};

$(function(){
  $(".type_list li:first").addClass("active");
  $(".topic_box div:first-child").addClass("active");
  $(".topic_show div:first-child").addClass("active");
  $(".type_list li").each(function(){
    $(this).click(function(){
       $(this).siblings().removeClass("active");
       $(this).addClass("active");
       var id="#show"+$(this).attr("data-id");
       $(".topic_box").find(".topic_show").removeClass("active");
       $(id).addClass("active");
    });
  });
});
var result = new Array();
var re = <s:property value="topicFormatSize"/>;
$(function(){
		for(var i=0;i<re;i++){
			result.push(0);
		}
});

</script>

 <script src="<%=basePath%>inc/js/examCheck.js"></script>
  </head>
  
  <body class="conduct"  >
     <header class="container-full">
             <div class="container">
                   <h2 class="hero-tip-timl">正在答题中...</h2>
                   <h1 class="her_ans_tip">考试倒计时&nbsp;:&nbsp;
                         <span class="yel1 fa f20">
                             <span class="tim"></span>
                             <span style="display: none" id="datetimeNow"><s:property value="exam.examTimes"/></span>
                       </span>
                </h1>
        </div>
    </header>
    <div class="container">
    <div class="main">
    <div class=" main_type">
    <ul class="list-group type_list">
       <s:iterator value="questionTypes" var="wq">
        <li data-id="<s:property value="#wq.id"/>"> <span>  <s:property value="#wq.titleString"/></span></li>
        
      </s:iterator>   
     </ul>                                
       <span class="handPaper" id="handPaper" data-id="<s:property value="examId"/> ">交卷</span>                           
     </div>
     <div class="topic_box">
            <s:iterator value="questionTypes" var="wq2">
	          <div class="topic_show" data-id="<s:property value="#wq2.id"/>" id="show<s:property value="#wq2.id"/>">

	          <s:iterator value="examTopic" var="wt">
	          	          <s:if test="#wq2.id == #wt.type.id">
	                       <div data-id="<s:property value="#wt.id"/>" class="topic_main">   
	                       
	                         <h2 class="topic_title" data-id="<s:property value="#wt.sort"/>"><s:property value="#wt.sort"/> . <s:property value="#wt.titleString"/></h2>    
	                         <div class="topic_answer">
	                           <s:if test="#wq2.id == 4">
	                           
	                            <s:iterator value="topicFormat" var="tf">
					                             <s:if test="#tf.topic.type.id == #wq2.id">
					                                  <s:if test="#wt.id == #tf.topic.id">
                                                       <div class="form-redio" data-id="<s:property value="#tf.id"/>">
							                            <input type="radio" name="answer<s:property value="#wt.id"/>" value="1"><span>正确</span>
							                            <input type="radio" name="answer<s:property value="#wt.id"/>" value="0"><span>错误</span>
							                             </div>
                                                      </s:if> 
					                             </s:if>
			                           </s:iterator>
	                           </s:if>
	                           <s:elseif test="#wq2.id == 1">
			                           <s:iterator value="topicFormat" var="tf">
					                             <s:if test="#tf.topic.type.id == #wq2.id">
					                               <s:if test="#wt.id == #tf.topic.id">
					                                  <h4><span>A . </span><s:property value="#tf.answerA"/></h4>
					                                 <h4><span>B . </span> <s:property value="#tf.answerB"/></h4>
					                                  <h4><span>C . </span><s:property value="#tf.answerC"/></h4>
					                                  <h4><span>D . </span><s:property value="#tf.answerD"/></h4>
					                                  <div class="form-redio" data-id="<s:property value="#tf.id"/>">
					                                     <input name="answer<s:property value="#wt.id"/>" type="radio"  value="1" ><span>A</span>
					                                      <input name="answer<s:property value="#wt.id"/>" type="radio"  value="2" ><span>B</span>
					                                       <input name="answer<s:property value="#wt.id"/>" type="radio"  value="3" ><span>C</span>
					                                        <input name="answer<s:property value="#wt.id"/>" type="radio"  value="4" ><span>D</span>
					                                  </div>
					                               </s:if>   
					                             </s:if>
			                           </s:iterator>
	                           </s:elseif>
	                             <s:elseif test="#wq2.id == 2">
			                           <s:iterator value="topicFormat" var="tf">
					                             <s:if test="#tf.topic.type.id == #wq2.id">
					                                <s:if test="#wt.id == #tf.topic.id">
                                                       <s:iterator value="#tf.answers" var="tfa">
                                                       <h4>
                                                          <span><s:if test="#tfa.orderInt == 0">A . </s:if>
                                                          <s:elseif test="#tfa.orderInt == 1">B .  </s:elseif>
                                                            <s:elseif test="#tfa.orderInt == 2">C .  </s:elseif>
                                                              <s:elseif test="#tfa.orderInt == 3">D .  </s:elseif>
                                                          </span>
                                                          <s:property value="#tfa.body"/>
                                                       </h4>   
                                                       </s:iterator>
                                                      <div class="form-redio" data-id="<s:property value="#tf.id"/>">
					                                     <input name="answer<s:property value="#wt.id"/>" type="checkbox"  value="0" ><span>A</span>
					                                      <input name="answer<s:property value="#wt.id"/>" type="checkbox"  value="1" ><span>B</span>
					                                       <input name="answer<s:property value="#wt.id"/>" type="checkbox"  value="2" ><span>C</span>
					                                        <input name="answer<s:property value="#wt.id"/>" type="checkbox"  value="3" ><span>D</span>
					                                  </div>
                                                    </s:if>   
					                             </s:if>
			                           </s:iterator>
	                           </s:elseif>
	                             <s:elseif test="#wq2.id == 3">
			                           <s:iterator value="topicFormat" var="tf">
					                             <s:if test="#tf.topic.type.id == #wq2.id">
					                                  <s:if test="#wt.id == #tf.topic.id">
					                                    <div class="form-fill" data-id="<s:property value="#tf.id"/>">
                                                       <s:iterator value="#tf.answers" var="tfa">
                                                           答案： <input type="text" value="" name="answer<s:property value="#wt.id"/>"/><br/>
                                                       </s:iterator>
                                                          </div>
                                                      </s:if> 
					                             </s:if>
			                           </s:iterator>
	                           </s:elseif>
	                         </div>
	            <div class="key">
	               <span>
                        标准解答： <s:property value="#wt.description"/>
	               </span>
	            </div>             
	           <div class="message">
	               <span class="m"></span>
	               <div class="action">
	                  <ul class="list-group">
	                     <li onclick="discuz(<s:property value="#wt.id"/>)" data-id="<s:property value="#wt.id"/>" data-toggle="modal" data-target="#discuz">同学讨论</li>
	                      <li onclick="answer(<s:property value="#wt.id"/>)" data-id="<s:property value="#wt.id"/>">本题解答</li>
	                       <li onclick="collectDown(<s:property value="#wt.id"/>,<s:property value="#session.user.id"/>,this)" data-id="<s:property value="#wt.id"/>">收藏本题</li>
	                  </ul>
	               </div>  
	         </div>
	                       </div>
                  	       </s:if>
	          </s:iterator>
	             <h2 class="vary" >
	              <span class="next" type-id="<s:property value="#wq2.id"/>">下一题</span>
	              </h2>
	          </div>   
          </s:iterator>
     </div>
    </div>
    </div>
    <!--同学讨论  -->
<div class="modal fade" id="discuz" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">试题分析</h4>
      </div>
      <div class="modal-body">
         <iframe src="" id="discuz_iframe"></iframe>
      </div>
    </div>
  </div>
</div>
  </body>
</html>
