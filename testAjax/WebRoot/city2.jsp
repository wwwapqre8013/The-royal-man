<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'city.jsp' starting page</title>
    
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
	$(document).ready(function() {
    //$.是全局方法
    
    $.ajax({
    url:"city2",
    data:{type:"sheng"},
    async:false,
	type:"post",
	dataType:"text",
    success:function(data){
    var ops = "";
	var shis = data.split(";");
	for(var i=0;i<shis.length;i++){
	var id = shis[i].split(",")[0];
	var name = shis[i].split(",")[1];
	ops+="<option value="+id+">"+name+"</option>"
	}
	$("#sheng").html(ops);
    }
    })
    
   
    searchCitys($("#sheng").val());
    
    $("#sheng").change(function(){
    //location.href="city?sId="+ $(this).val();
	searchCitys($(this).val());
	})
	
	function searchCitys(ssId){
	$.ajax({
	url:"city2",
	data:{type:"shi",sId:ssId},
	type:"post",
	dataType:"text",
	success:function(data){
	var ops = "";
	var shis = data.split(";");
	for(var i=0;i<shis.length;i++){
	var id = shis[i].split(",")[0];
	var name = shis[i].split(",")[1];
	ops+="<option value="+id+">"+name+"</option>"
	}
	$("#shi").html(ops);
	}
	})
	}
	})
	  
	</script>
  </head>
  
  <body>
    	省：<select id="sheng">

</select> 
	市：<select id="shi">
	
	</select>
  </body>
</html>
