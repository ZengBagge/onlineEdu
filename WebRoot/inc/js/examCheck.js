/**
 * 检查答案
 */
var isSuccess=false;
$(function(){
	$(".vary ").on("click",".next",function(){
		var moth=$(this).attr("type-id");
		switch(moth){
		case "1" : checkRadio($(this));break;
		case "2" : checkCheckBox($(this));break;
		case "3": checkFill($(this));break;
		case "4" : checkJudge($(this));break;
		default:break;
		}
	});
	
	$("#handPaper").on("click",function(){
		if(confirm("请确认是否做完题目开始交卷！")){
			var examId = Number($(this).attr("data-id"));
			hand(examId);
		}
		});
	
 $(".action ul li").hover(function(){
	 $(this).addClass("active");
 },function(){
	 $(this).removeClass("active");
 });	
});

function hand(examId){
	  if(result.length>0){
		    exam.handPaper(result,timerc,examId,function(result){
			  if(result == true)
				  {
				  alert("提交成功，本页面将关闭！");
			       closeWin(); 
				  }
			  else{
				  alert("提交失败，请重试");
			  }
		  });
		  }else{
			  alert("系统错误，请刷新页面重新提交");
		  }
}
function  checkRadio(dom){
	var oo =dom.parent().parent().find(".topic_main.active").find(".topic_answer").find(".form-redio");
	var ctid=dom.parent().parent().find(".topic_main.active").find(".topic_title").attr("data-id");
	var tid = oo.attr("data-id");
	var answer=oo.find(":checked").val();
    if( (tid != "undefined" && answer != "undefined"))
    {
			addTopicRedio.checkForAnswer(answer,tid,function(result){
				$(".topic_box .message .m").html(result[1]);
				if(result[0] == "1"){
					takeNote(1,ctid);
					success();	 
				}else{
					takeNote(-1,ctid);
					error();
				}
				
			});
   };
 }
function checkCheckBox(dom){
	var oo =dom.parent().parent().find(".topic_main.active").find(".topic_answer").find(".form-redio");
	var ctid=dom.parent().parent().find(".topic_main.active").find(".topic_title").attr("data-id");
	var tid = oo.attr("data-id");
	var answer=new Array();
	oo.find("input").each(function(){
        if ($(this).is(":checked"))
           {
             answer.push($(this).val());  
           }
    });
    if( tid != "undefined" && answer.length>0)
    {
		 addTopicCheckBox.checkForAnswer(answer,tid,function(result){
				$(".topic_box .message .m").html(result[1]);
				if(result[0] == "1"){
					takeNote(1,ctid);
					success();	 
				}else{
					takeNote(-1,ctid);
					error();
				}
			});
   };
}

function checkFill(dom){
	var oo =dom.parent().parent().find(".topic_main.active").find(".topic_answer").find(".form-fill");
	var ctid=dom.parent().parent().find(".topic_main.active").find(".topic_title").attr("data-id");
	var tid = oo.attr("data-id");
	var answer=new Array();
	oo.find("input").each(function(){
             answer.push($(this).val());  
    });
    if( tid != "undefined" && answer.length>0)
    {
		 addTopicFill.checkForAnswer(answer,tid,function(result){
				$(".topic_box .message .m").html(result[1]);
				if(result[0] == "1"){
					takeNote(1,ctid);
					success();	 
				}else{
					takeNote(-1,ctid);
					error();
				}
			});
   };
	
}
function checkJudge(dom){

	var oo =dom.parent().parent().find(".topic_main.active").find(".topic_answer").find(".form-redio");
	var ctid=dom.parent().parent().find(".topic_main.active").find(".topic_title").attr("data-id");
	var tid = oo.attr("data-id");
	var answer=oo.find(":checked").val();
    if( tid != "undefined" && answer !="undefined")
    {
		 addTopicJudge.checkForAnswer(answer,tid,function(result){
				$(".topic_box .message .m").html(result[1]);
				if(result[0] == "1"){
					takeNote(1,ctid);
					success();	 
				}else{
					takeNote(-1,ctid);
					error();
				}
				
			});
   };
	
}

function resetting(){
	isSuccess= false;
}
function success(){

var jq= $(".topic_show.active  .active");
jq.removeClass("error");
jq.addClass("success");
  if(jq.next().next().length>0)
  {
  jq.removeClass("active");
  jq.next().addClass("active");
  $(".topic_show.active .prev").show();
  $(".topic_show.active .next").show();
  }
  else{
     jq.removeClass("active");
     jq.next().addClass("active");
     $(".topic_show.active .prev").show();
     $(".topic_show.active .next").hide();
  }
	resetting();
}
function error(){

	  var jq= $(".topic_show.active  .active");
	  jq.removeClass("success");
	  jq.addClass("error");
	  $(".topic_show.active .topic_main.active  .message").slideDown();
		resetting();
		setTimeout("next()", 2000); //设置1000毫秒以后执行一次本函数
}
function next(){
	 var jq= $(".topic_show.active  .active");
	   if(jq.next().next().length>0)
	   {
	   jq.removeClass("active");
	   jq.next().addClass("active");
	   $(".topic_show.active .prev").show();
	   $(".topic_show.active .next").show();
	   }
	   else{
	      jq.removeClass("active");
	      jq.next().addClass("active");
	      $(".topic_show.active .prev").show();
	      $(".topic_show.active .next").hide();
	   }
}
function takeNote(is,tid){  // is==0 未做 ==-1错误  == 1 正确
	if(timerc>0){
	result[tid]=is;
	}else{
		alert("现在已超出考试时间,请交卷");
	}
}
function closeWin(){
	window.opener = null; 
	window.open('', '_self'); 
	window.close(); 
}
function discuz(id){
	$("#discuz_iframe").attr("src","discuz!topic?tid="+id);
}
function answer(){
	$(".topic_box .topic_show.active .topic_main.active .key").addClass("open");
}

function collectDown(id,uid,dom){
	
	collect.isCollected(id,uid,function(result){
		if(result)
			{
			  $(dom).html("已收藏").addClass("active");
			  $(dom).attr("onclick","");
			}
		else{
			collect.collectTopic(id,uid,function(result){
				if(result){
					  $(dom).html("已收藏").addClass("active");
					  $(dom).attr("onclick","");
				}else{
					alert("收藏失败");
				}
			});
		}
	});
}






