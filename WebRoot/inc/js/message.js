/**
 * 
 */
$(function(){
	

	 
	$(".studentUser_li").hover(function(){
		$(this).addClass("active");
	},function(){
		$(this).removeClass("active");
	});
	$(".studentUser_li").click(function(){
		$("#message_content #path").html("正在与("+$(this).attr("data-name")+")聊天。。。");
		$("#form_message").attr("data-id",$(this).attr("data-id"));
		$(".message_form").show();
		$(".message_list_ul").html("");
         message.getReceiveByUser($(this).attr("data-id"),loginUser,function(result){
        	 if(result != null){
        		 $.each(result,function(n,m){
        			 var html=' <li class="message_list_li receive">'+
        			 ' <div class="avatar" ><img id="avatar" src="'+baseUrl+'inc/image/avatar/'+m[0]+'"/></div>'+
        			 '   <div class="btn btn-success">'+m[1]+'</div>'+
        			 ' </li>'+
        			 '   <div style="clear:both;"></div>';
        			$(".message_list_ul").append(html);
        			
        		 });
        	 }
         });
		
	});
    	$("#form_message").click(function(){
    		var concent = $("#message_text").val();
    		var userId=$(this).attr("data-id");
    		var receives=new Array();
    		if(concent.length>0 && userId.length>0){
    			concent=concent.replace(/<(script)[\S\s]*?\1>|<\/?(a|img)[^>]*>/gi, "");
    			receives.push(Number(userId));	
    			message.publishMessage(concent,receives,function(m){
    		        if(m !=null)
    		        	{ 
    		        	$("#message_text").val("");
    		        	var html=' <li class="message_list_li send">'+
        			 ' <div class="avatar" ><img id="avatar" src="'+baseUrl+'inc/image/avatar/'+m[0]+'"/></div>'+
        			 '   <div class="btn btn-wi">'+m[1]+'</div>'+
        			 ' </li>'+
        			 '   <div style="clear:both;"></div>';
        			$(".message_list_ul").append(html);
    		        	}
    		        else
    		        	$("#info").html("发送失败");
    			});
    		}
    	});
	 $(".message .note li").click(function(){
		 $(this).parent().find("li").removeClass("active");
		 $(this).addClass("active");
		 var data = $(this).attr("data-id");
		 var title=$(this).find("p").html();
		 $("#note-list #path").attr("title",title).html(title);
		 $("#note-list-content div").removeClass("active");
		 $("#note-list-content_"+data).addClass("active");
	 });
   	 
});

function moreRubbish(page){
	message.getMorePublishMessages(loginUser,page,function(result){
		if(result !=null){
			$.each(result,function(n,m){
				var html=' <li class="studentUser_li" data-id="'+m[4]+'" data-name="'+m[1]+'">'+
                    '<div class="avatar" ><img id="avatar" src="'+baseUrl+'inc/image/avatar/'+m[0]+'"/></div>'+
                   ' <a class="studentUser_a"><span class="name">'+m[1]+'('+m[2]+')</span><br/><span>'+m[3]+'</span></a>'+
           '</li>';
				$("#note-list-content_4 ul").append(html);
			});
		}
	});
}

function moreReceive(page){
	message.getMoreReceiveNotes(loginUser,page,function(result){
		if(result !=null){
			$.each(result,function(n,m){
				var html=' <li class="studentUser_li" data-id="'+m[4]+'" data-name="'+m[1]+'">'+
                    '<div class="avatar" ><img id="avatar" src="'+baseUrl+'inc/image/avatar/'+m[0]+'"/></div>'+
                   ' <a class="studentUser_a"><span class="name">'+m[1]+'('+m[2]+')</span><br/><span>'+m[3]+'</span></a>'+
           '</li>';
				$("#note-list-content_2 ul").append(html);
			});
		}
	});
	
}

function moreSend(page){
	message.getMoreSendNotes(loginUser,page,function(result){
		if(result !=null){
			$.each(result,function(n,m){
				var html=' <li class="studentUser_li" data-id="'+m[4]+'" data-name="'+m[1]+'">'+
                    '<div class="avatar" ><img id="avatar" src="'+baseUrl+'inc/image/avatar/'+m[0]+'"/></div>'+
                   ' <a class="studentUser_a"><span class="name">'+m[1]+'('+m[2]+')</span><br/><span>'+m[3]+'</span></a>'+
           '</li>';
				$("#note-list-content_3 ul").append(html);
			});
		}
	});
}

















