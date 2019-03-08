<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<link rel=stylesheet href="css/common.css">
		<link rel=stylesheet href="css/jdc-side-panel.css">
	</head>
	
<body>
<div class="sidebar-nav">






	<a href="#accounts-menu1" class="nav-header" data-toggle="collapse">
		<i class="icon-dashboard"></i>管理菜单</a>


	<c:if test="${user.role==0}">
        
        <a href="#accounts-menu2" class="nav-header" data-toggle="collapse">
        <i class="icon-briefcase"></i>用户信息</a>
        
        <ul id="accounts-menu2" class="nav nav-list collapse <c:if test="${menu==2}">in</c:if>">
             <li><a href="method!userlist?menu=2">学生用户管理</a></li>
            <li ><a href="method!useradd?menu=2">添加新学生</a></li>
            
             <li><a href="method!userlist2?menu=2">教师用户管理</a></li>
            <li ><a href="method!useradd10?menu=2">添加新教师</a></li>
          
        </ul>
        
        
        <a href="#accounts-menu3" class="nav-header" data-toggle="collapse">
        <i class="icon-briefcase"></i>实验室信息</a>
        
        <ul id="accounts-menu3" class="nav nav-list collapse <c:if test="${menu==3}">in</c:if>">
            <li><a href="method!lablist?menu=3">教师实验室管理</a></li>
            <li ><a href="method!labadd?menu=3">添加教师实验室</a></li>
            
            <li><a href="method!lablist3?menu=3">学生实验室管理</a></li>
            <li ><a href="method!labadd10?menu=3">添加学生实验室</a></li>
          
        </ul>
        
        
        <a href="#accounts-menu4" class="nav-header" data-toggle="collapse">
        <i class="icon-briefcase"></i>公告信息</a>
        
        <ul id="accounts-menu4" class="nav nav-list collapse <c:if test="${menu==4}">in</c:if>">
             <li><a href="method!gonggaolist?menu=4">公告管理</a></li>
            <li ><a href="method!gonggaoadd?menu=4">添加新公告</a></li>
          
        </ul>
        
        
        <a href="#accounts-menu5" class="nav-header" data-toggle="collapse">
        <i class="icon-briefcase"></i>预约</a>
        
        <ul id="accounts-menu5" class="nav nav-list collapse <c:if test="${menu==5}">in</c:if>">
           
           
          	<li><a href="method!yuyuelist2?menu=5">教师预约管理</a></li>
          	
          	  <li><a href="method!yuyuelist3?menu=5">教师预约记录查询</a></li>
          	  
          	  
          	  	<li><a href="method!yuyuelist20?menu=5">学生预约管理</a></li>
          	
          	  <li><a href="method!yuyuelist30?menu=5">学生预约记录查询</a></li>
          	  
        </ul>


		<a href="#accounts-menu6" class="nav-header" data-toggle="collapse">
        <i class="icon-briefcase"></i>留言</a>
        
        <ul id="accounts-menu6" class="nav nav-list collapse <c:if test="${menu==6}">in</c:if>">
             <li><a href="method!liuyanlist2?menu=6">留言回复</a></li>
           
          	
        </ul>

   </c:if>
   
   
   
   
   
   <c:if test="${user.role==1}">
        
        <a href="#accounts-menu2" class="nav-header" data-toggle="collapse">
        <i class="icon-briefcase"></i>公告信息</a>
        
        <ul id="accounts-menu2" class="nav nav-list collapse <c:if test="${menu==2}">in</c:if>">
             <li><a href="method!gonggaolist2?menu=2">公告查询</a></li>
           
          
        </ul>
        
        
        <a href="#accounts-menu3" class="nav-header" data-toggle="collapse">
        <i class="icon-briefcase"></i>预约</a>
        
        <ul id="accounts-menu3" class="nav nav-list collapse <c:if test="${menu==3}">in</c:if>">
             <li><a href="method!lablist4?menu=3">预约实验室</a></li>
           
          	<li><a href="method!yuyuelist10?menu=3">预约管理</a></li>
        </ul>
        
         <a href="#accounts-menu4" class="nav-header" data-toggle="collapse">
        <i class="icon-briefcase"></i>留言</a>
        
        <ul id="accounts-menu4" class="nav nav-list collapse <c:if test="${menu==4}">in</c:if>">
             <li><a href="method!liuyanlist?menu=4">我的留言</a></li>
           
          	<li><a href="method!liuyanadd?menu=4">留言</a></li>
        </ul>
       

   </c:if>
   
   
   <c:if test="${user.role==2}">
        
        <a href="#accounts-menu2" class="nav-header" data-toggle="collapse">
        <i class="icon-briefcase"></i>公告信息</a>
        
        <ul id="accounts-menu2" class="nav nav-list collapse <c:if test="${menu==2}">in</c:if>">
             <li><a href="method!gonggaolist2?menu=2">公告查询</a></li>
           
          
        </ul>
        
        
        <a href="#accounts-menu3" class="nav-header" data-toggle="collapse">
        <i class="icon-briefcase"></i>预约</a>
        
        <ul id="accounts-menu3" class="nav nav-list collapse <c:if test="${menu==3}">in</c:if>">
             <li><a href="method!lablist2?menu=3">预约实验室</a></li>
           
          	<li><a href="method!yuyuelist?menu=3">预约管理</a></li>
        </ul>
        
         <a href="#accounts-menu4" class="nav-header" data-toggle="collapse">
        <i class="icon-briefcase"></i>留言</a>
        
        <ul id="accounts-menu4" class="nav nav-list collapse <c:if test="${menu==4}">in</c:if>">
             <li><a href="method!liuyanlist?menu=4">我的留言</a></li>
           
          	<li><a href="method!liuyanadd?menu=4">留言</a></li>
        </ul>
       

   </c:if>
     

        <a href="method!changepwd" class="nav-header" ><i class="icon-briefcase"></i>修改密码</a>
        <a href="method!loginout" class="nav-header" ><i class="icon-briefcase"></i>退出系统</a>
    
    <div class="jdc-side" style="display: block;float: right ">
  <div class="mod_hang_qrcode jdc_feedback_qrcode">
    <div class="mod_hang_qrcode_btn"><i class="jdcfont"></i><span>扫码反馈</span></div>
    <div class="mod_hang_qrcode_show">
      <div class="mod-qr-tips"></div>
      <div class="mod_hang_qrcode_show_bg">
        <div id="canvas" style="height: 123px; width: 123px;"><img src="images/weixin.png"></div>
      </div>
      <p>扫一扫，反馈当前页面</p>
    </div>
  </div>
  
  <div class="mod_hang_qrcode jdc_hang_qrcode"><a class="mod_hang_qrcode_btn"><i class="jdcfont"></i><span>扫码关注</span></a>
    <div class="mod_hang_qrcode_show">
      <div class="mod_hang_qrcode_show_bg"></div>
      <p>山西农大微信公众号</p>
    </div>
  </div>
  <div class="mod_hang_qrcode mod_hang_top"><a href="#to-top" id="toTop" class="mod_hang_qrcode_btn"><i class="jdcfont"></i><span>返回顶部</span></a></div>
  <div class="el-dialog__wrapper" style="display: none;">
    <div class="el-dialog el-dialog--small" style="top: 15%;">
      <div class="el-dialog__header"><span class="el-dialog__title"></span>
        <div type="button" class="el-dialog__headerbtn"><i class="el-dialog__close el-icon el-icon-close"></i></div>
      </div>
      </div>
  </div>
</div>

    </div>
</body>
</html>