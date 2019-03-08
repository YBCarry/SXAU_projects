<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.util.Util"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Bernadette在线实验室管理系统登录</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="iconfont/style.css" type="text/css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
    
    <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
	
	<link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>
	
    <!-- Demo page code -->

    <style>
			body{color:#fff; font-family:"微软雅黑"; font-size:14px;}
			.wrap1{position:absolute; top:0; right:0; bottom:0; left:0; margin:auto }/*把整个屏幕真正撑开--而且能自己实现居中*/
			.main_content{background:url(img/login_bg.png) repeat; margin-left:auto; margin-right:auto; text-align:left; float:none; border-radius:8px;}
			.form-group{position:relative;}
			.login_btn{display:block; background:#3872f6; color:#fff; font-size:15px; width:100%; line-height:50px; border-radius:3px; border:none; }
			.login_input{width:100%; border:1px solid #3872f6; border-radius:3px; line-height:40px; padding:2px 5px 2px 30px; background:none;}
			.icon_font{position:absolute; bottom:15px; left:10px; font-size:18px; color:#3872f6;}
			.font16{font-size:16px;}
			.mg-t20{margin-top:20px;}
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

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="lib/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
  
	<link href="css/bootstrap.min.css" title="" rel="stylesheet" />
	<link title="orange" href="css/login.css" rel="stylesheet" type="text/css">
	
	<link rel="stylesheet" href="css/jquery.slider.css" />

	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.slider.min.js"></script>
	
	<style>
	body, html {
		position: absolute;
		top: 0;
		bottom: 0;
		left: 0;
		right: 0;

		margin: 0;
		padding: 0;
	}
	#background {
		position: fixed;
		top: 0;
		left: 0;

		z-index: -100;
	}
</style>
  </head>

  <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!--> 
  <body class=""> 
  <!--<![endif]-->
    
				<canvas id="background"></canvas>

				<script async type="text/javascript" src="js/background.js"></script>

			    <div class="navbar">
			        <div class="navbar-inner" style="width:1440px">
                			<ul class="nav pull-right"></ul>
               			<a class="brand" href="login.jsp" style="color:#000"><span class="mg-b20 text-center">Bernadette在线实验室管理系统</span></a>
        				</div>
    				</div>	

				
				<div class="login" style="z-index: 1;">
				
				<div class="row-fluid">
		    			<div class="dialog">
		        		<div class="block">
		            <p class="block-heading">用&nbsp;&nbsp;户&nbsp;&nbsp;登&nbsp;&nbsp;录</p>
		            <div class="block-body">
		                <form name="form1" method="post" action="method!login">
		                    <label style="color:#000">用户名</label>
		                    <input type="text" class="span12" name="username">
		                    <label style="color:#000">密&nbsp;&nbsp;&nbsp;码</label>
		                    <input type="password" class="span12" name="password">
		                     <label style="color:#000">角色</label>
		                    <select name="role">
		                    <option value="2">教师</option>
		                    <option value="1">学生</option>
		                    <option value="0">管理员</option>
		                    </select>
		                    
		                    <div>
		                    <div id="slider1" class="slider" style="margin-top: 10px; width: 300px; float: left"></div>
							<script>
								$("#slider1").slider({callback: function(result) {$("#result1").text(result);}});
							</script>
		                    <div style="float: right; margin-top: 10px;"><a href="javascript:void(0)" onclick="javascript:form1.submit();" class="btn btn-primary pull-right">登录</a></div>
		                   </div>
		                   
		                    <div class="clearfix"></div>
		                </form>
		            </div>
		        </div>
		      </div> 
		
			</div>
			
				</div>
				
				<div style="height:30px"><br></div>
				<!-- footer start -->
				<div class="footer">
					<p style="font-family:arial;">
						Copyright&nbsp;&copy;&nbsp;2018&nbsp;Designed By Bernadette网络传媒-YBCaay&nbsp;版权所有
					</p>
				</div>
				<!-- footer end -->
			
			</div>	
			</div>
		</div>
	</div>

    

	<script type="text/javascript">
	    $(function(){
	        Victor("container", "output");   //登陆背景函数
	        $("#entry_name").focus();
	        $(document).keydown(function(event){
	            if(event.keyCode==13){
	                $("#entry_btn").click();
	            }
	        });
	    });
	</script>

    <script src="lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
    
  </body>
</html>



