/**
 * 
 */
$(function(){
	$("#publish_message").click(function(){
		var body = $("#publish .body").val();
		body = body.replace(/<(script)[\S\s]*?\1>|<\/?(a|img)[^>]*>/gi, "");
		var tid = $(this).attr("data-id");
		if(body.length>1){
			discuz.add(body,tid,function(result){
				if(result[0] == "1"){
					$("#publish").hide();
					var html=' <li>'+
			               '<div class="info">'+
	                '<div class="avatar">'+
	                 '<img src="http://127.0.0.1:8088/WebStudy/inc/image/avatar/'+result[1]+'" id="avatar">'+
	                 '</div>'+
	                ' <div class="user_date">'+
	                 '  <h4><a title="'+result[3]+'">'+result[2]+'('+result[4]+')</a></h4>'+
	                 '  <h5>'+result[5]+'</h5>'+
	                ' </div>'+
	                ' <div class="praise">'+
	                 ' <span class="icon-b icon-hand" onclick="zan('+result[8]+',this)"></span><span>'+result[6]+'</span>'+
	                ' </div>'+
	                ' </div>'+
	                ' <div class="body">'+
	                 '  <span>'+result[7]+'</span>'+
	                 '</div>'+
	            '  </li>';
					$(".list ul").append(html);
				}else
					{
					 alert("提交失败");
					}
			});
		}
		
	});
	
 $(".head .x").click(function(){
	 discuz.moreMessage(function(results){
		 if(results.length>0){
	         $.each(results,function(n,result) { 
	        	 if(result[0] != null){
	        	 var html="";
	         if(result[9] == "1"){ 
	        		  html=' <li>'+
		               '<div class="info">'+
	          '<div class="avatar">'+
	           '<img src="http://127.0.0.1:8088/WebStudy/inc/image/avatar/'+result[1]+'" id="avatar">'+
	           '</div>'+
	          ' <div class="user_date">'+
	           '  <h4 style="color:red"><a title="'+result[3]+'">'+result[2]+'('+result[4]+')</a>《教师点评》</h4>'+
	           '  <h5>'+result[5]+'</h5>'+
	          ' </div>'+
	          ' <div class="praise">'+
	           ' <span class="icon-b icon-hand" onclick="zan('+result[8]+',this)"></span><span>'+result[6]+'</span>'+
	          ' </div>'+
	          ' </div>'+
	          ' <div class="body">'+
	           '  <span>'+result[7]+'</span>'+
	           '</div>'+
	      '  </li>';
	        	 }else{
	        		 html=' <li>'+
		               '<div class="info">'+
		               '<div class="avatar">'+
		                '<img src="http://127.0.0.1:8088/WebStudy/inc/image/avatar/'+result[1]+'" id="avatar">'+
		                '</div>'+
		               ' <div class="user_date">'+
		                '  <h4><a title="'+result[3]+'">'+result[2]+'('+result[4]+')</a></h4>'+
		                '  <h5>'+result[5]+'</h5>'+
		               ' </div>'+
		               ' <div class="praise">'+
		                ' <span class="icon-b icon-hand" onclick="zan('+result[8]+',this)"></span><span>'+result[6]+'</span>'+
		               ' </div>'+
		               ' </div>'+
		               ' <div class="body">'+
		                '  <span>'+result[7]+'</span>'+
		                '</div>'+
		           '  </li>';
	        	 }
			$(".list ul").append(html);
	        	 }else{
	        		 $(this).html("没有更多内容了！");
	        	 }
	         });  
		 }else{
			 $(this).html("没有更多内容了！");
		 }
	 });
 });	
});

function pitch(location){
	javascript:document.getElementsByTagName('BODY')[0].scrollTop=document.getElementsByTagName('BODY')[0].scrollHeight;
}

function zan(id,dom){
	discuz.zan(id,function(result){
		if(result){
			var jq=$(dom).next().html();
			var i = new Number(jq);
			$(dom).next().html(i+1);
		}else{
			alert("赞失败了！");
		}
	});
	
}
