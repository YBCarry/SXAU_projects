<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Bernadette在线实验室管理系统</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">    
    <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
    
    <script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>
	
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="css/font.css">
	<link rel="stylesheet" href="css/xadmin.css">
	<link rel="stylesheet" href="https://cdn.bootcss.com/Swiper/3.4.2/css/swiper.min.css">
	<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdn.bootcss.com/Swiper/3.4.2/js/swiper.jquery.min.js"></script>
	<script src="lib/layui/layui.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/xadmin.js"></script>
	
	
    <!-- Demo page code -->

   

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="lib/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
  
    
  </head>

  <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!--> 
  <body class=""> 
  <!--<![endif]-->
    
    <%@ include file="top.jsp" %>
    


    
    <%@ include file="left.jsp" %>
    

    
    <div class="content">
        <br/>
        <div align="center" style="font-weight: bold;font-size: 20px;">
       

            <blockquote class="layui-elem-quote" style="color:#fff">
                	您好,${user.xingming },欢迎使用Bernadette在线实验室管理系统~
            </blockquote>
            <blockquote class="layui-elem-quote" style="color:#fff">
                	请关注官方微信号&nbsp;&nbsp;: 山西农业大学
            </blockquote>
        </div>
        
            <fieldset class="layui-elem-field layui-field-title site-title">
              <legend><a name="default" style="color:#fff">大学生实验守则</a></legend>
            </fieldset>
            <font color=“#ffffff”>
            1.学生应该按照课程教学计划，准时上实验课，不得迟到早退。<br><br>

			2.实验前应认真阅读实验指导书，明确实验目的、步骤、原理，预习相关的理论知识，并接受实验教师的提问和检查。<br><br>

			3.进入实验室必须遵守实验室的规章制度。不得高声喧哗和打闹，不准抽烟、随地吐痰和乱丢纸屑杂物。有净化要求的实验室，进试必须换拖鞋。<br><br>

			4.做实验时必须严格遵守仪器设备的操作规程，爱护仪器设备，节约使用材料，服从实验教师和技术人员的指导。未经许可不得动用与本实验无关的仪器设备及其他物品。<br><br>

			5.实验中要细心观察，认真记录各种试验数据。不准敷衍，不准抄袭别组数据，不得擅自离开操作岗位。<br><br>

			6.实验时必须注意安全，防止人身和设备事故的发生。若出现事故，应立即切断电源，及时向指导教师报告，并保护现场，不得自行处理。<br><br>

			7.实验完毕，应清理实验现场。经指导教师检查仪器设备、工具、材料和实验记录后方可离开。<br><br>

			8.实验后要认真完成实验报告，包括分析结果、处理数据、绘制曲线及图表。在规定的时间内交指导教师批改。<br><br>

			9.在实验过程中，由于不慎造成仪器设备、器皿工具损坏者，应写出损坏情况报告，并接受检查，由领导根据情况进行处理。<br><br>

			10.凡违反操作规程、擅自动用与本实验无关的仪器设备、私自拆卸仪器而造成事故和损失的，肇事者必须写出书面检查，视情节轻重和认识程度，按章予以赔偿。<br><br>
            </font>
            <!-- 右侧内容框架，更改从这里结束 -->


		<br><br><br>
	<%@ include file="bottom.jsp" %>
	
    <script src="lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
    
  </body>
</html>
