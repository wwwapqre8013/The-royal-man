<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'LoginMD5.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
    <form action="doLogin" method="post">
   账号：<input type="text" name="account" value="${name}"/><br/>
   密码：<input type="password" name="password"/><br/>
  <input type="radio" name="time" value="1"/>不保存
  <input type="radio" name="time" value="2" checked/>保存
  <br/>
  <input type="submit" value="登录"/>
  <input type="button" value="注册"/>
   </form>
  </body>
</html>
