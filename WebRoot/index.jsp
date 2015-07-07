<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@  taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        
        <title>异形网络教育平台</title>
        
        <!-- Meta -->
        <meta name="description" content="异形网络教育平台由异形网络工作室创立，旨在提供最好本科网络在线教育与测评">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="异形网络工作室">
        <meta name="keywords" content="湖南科技大学 湖南科技大学在线学习平台 异形网络工作室 在线教育 在线考试">
        <!-- CSS, Normalize First, minify for production -->
        <link rel="stylesheet" type='text/css' href="<%=basePath%>inc/css/master.css"/>
<!--         
        Import web fonts using the link tag instead of CSS @import
        <link href='http://fonts.useso.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
        
        Icons from http://fontawesome.io/
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet"/> -->
                
    </head>
<body>
 <!-- Slider Section -->
        <section class="slider-main">
            <header style="color:#fff;font-size:1em;"><span style="position:fixed;left:15px;z-index:10;"><a href="index" style="color:#fff;font-size:1em; text-decoration:none;">湖南科技大学在线学习系统</a></span><span style="position:fixed;right:30px;z-index:10;"><a href="user!login" style="color:#fff;font-size:1em; text-decoration:none; ">登录</a></span></header>
            <div id="left-arrow">
                <a href="#" class="unslider-arrow prev">
                    <i class="fa fa-chevron-left fa-2x"></i>
                </a>
            </div>
            <div id="right-arrow">
                <a href="#" class="unslider-arrow next">
                    <i class="fa fa-chevron-right fa-2x"></i>
                </a>
            </div>
            <div class="slider" >
                <ul>
                    <li class="slide" id="slide1">
                        <h2 style="word-spacing:20px;letter-spacing: 20px;padding-left: 15px;">诚信</h2>
                       <%--  <p>It comes from <a href="<%=basePath%>index" >Unslider</a>, where you'll also find issue reports (their website is out of date).</p> --%>
                        <a class="btn" href="<%=basePath%>index">进入系统</a>
                    </li>
                    <li class="slide" id="slide2">
                        <h2 style="word-spacing:20px;letter-spacing: 20px;padding-left: 15px;">诚信</h2>
                       <%--  <p>It comes from <a href="<%=basePath%>index" >Unslider</a>, where you'll also find issue reports (their website is out of date).</p> --%>
                        <a class="btn" href="<%=basePath%>index">进入系统</a>
                    </li>
                </ul>
            </div>
             <footer style="position:fixed;bottom:0px;z-index:10;color:#fff;font-size:1em;text-align:center;width:100%;">异形网络工作室<br/>2015</footer>
        </section>
            <script type="text/javascript" src="<%=basePath%>inc/js/jquery-1.11.2.min.js"></script>
           <script type="text/javascript" src="<%=basePath%>inc/js/jquery.event.move.js"></script>
        <script type="text/javascript" src="<%=basePath%>inc/js/jquery.event.swipe.js"></script>
        <script src="<%=basePath%>inc/js/unslider.js"></script>
         <!-- Unslider script -->
        <script>
            $(document).ready(function () {
                var unslider = $('.slider').unslider();
                $('.unslider-arrow').click(function(event) {
                    event.preventDefault();
                    if ($(this).hasClass('next')) {
                        unslider.data('unslider')['next']();  
                    } else {
                        unslider.data('unslider')['prev']();  
                    };
                });
                var unslider = $('.slider').unslider();

                unslider.on('movestart', function(e) {
                    if((e.distX > e.distY && e.distX < -e.distY) || (e.distX < e.distY &&   e.distX > -e.distY)) {
                        e.preventDefault();
                    }
                });
        });
        </script>
        </body>