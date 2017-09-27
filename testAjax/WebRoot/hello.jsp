<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'hello.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="jquery/jquery.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
	$(":button").click(function(){
	$.ajax({
	url:"hello",
	async:true,//为true：则不管成功失败 异步一个线程继续执行。为false时：必须要等到success或者error后才可以继续执行
	data:{name:"tom",sex:"男"},
	type:"post",
	dataType:"text",
	//成功了会....
	success:function(data){
	$("#mes").html(data);
	console.log("开始");
	},
	//失败了会.....
	//error:function(data){$("#mes").html("出错");},
	});
	})
	console.log("最后");
	})
	
	</script>
  </head>
  
  <body>
    <input type="button" value="点击">
    <div id="mes"></div>
  </body>
</html>
