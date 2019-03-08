<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Bernadette在线实验室管理系统</title>
		<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
		<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/style1.css" media="screen" type="text/css" />
		<link href="iconfont/style.css" type="text/css" rel="stylesheet">
		
		<style>
			body{color:#fff; font-family:"微软雅黑"; font-size:14px;}
			.footer {width: 1280px;color: #fffff;text-align: center;margin:8px auto;padding-bottom: 10px;overflow: hidden;line-height: 25px;border-top: 1px solid #DBDBDB;}
	
			@media (min-width:200px){.pd-xs-20{padding:20px;}}
			@media (min-width:768px){.pd-sm-50{padding:50px;}}
			#grad {
			  background: -webkit-linear-gradient(#4990c1, #52a3d2, #6186a3); /* Safari 5.1 - 6.0 */
			  background: -o-linear-gradient(#4990c1, #52a3d2, #6186a3); /* Opera 11.1 - 12.0 */
			  background: -moz-linear-gradient(#4990c1, #52a3d2, #6186a3); /* Firefox 3.6 - 15 */
			  background: linear-gradient(#4990c1, #52a3d2, #6186a3); /* 标准的语法 */
			}
		</style>
	
	</head>

	<body style="background:url(img/index_bg2.jpg) no-repeat;">
	
	<br>

	<div style="text-align:center;clear:both;">
		<script src="/gg_bd_ad_720x90.js" type="text/javascript"></script>
		<script src="/follow.js" type="text/javascript"></script>
	</div>
	
	<div style="text-align: center;">
		<iframe frameborder="0" src="index2.html" width="200px" height="200px" z-index="1"
		scrolling="no"></iframe>
	</div>

	<h2 align="center">Bernadette在线实验室管理系统欢迎您~</h2>
	<br>
	<p align="center">
		<a href="http://localhost:8080/Bernadette_Laboratory/login.jsp" style="color:gray;font-size:18px">~~~START~~~</a>
	</p>
	<br>
	<div style="text-align: center;">
		<iframe frameborder="0" src="index3.html" width="800px" height="400px" 
		scrolling="no"></iframe>
	</div>
	
	        <!-- footer start -->
			<div class="footer">
				<p style="font-family:arial;">
					Copyright&nbsp;&copy;&nbsp;2018&nbsp;Designed By Bernadette网络传媒-YBCaay&nbsp;版权所有
				</p>
			</div>
			<!-- footer end -->
	    	</div>
	    
	</body>
</html>

