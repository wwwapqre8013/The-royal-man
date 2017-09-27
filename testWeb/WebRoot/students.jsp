<%@page import="java.sql.DriverManager"%>
<%@ page language="java" import="java.util.*,entity.*,dao.*"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'test1.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">

<style>
.table {
	margin: 50px auto;
	width: 300px;
}

td {
	text-align: center;
}

th {
	text-align: center;
}
</style>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
var oldColor;
$("thead tr").css("background","rgb(148,219,215)");
$("tbody tr:odd").css("background","rgb(208,239,191)");
$("tbody tr:even").css("background","rgb(241,241,241)");
$("tr").hover(function(){
   oldColor = $(this).css("background");
   $(this).css("background","pink");
   },function(){
   $(this).css("background",oldColor);
   })
})
</script>
</head>
<body>
<%
StudentDao stuDao = new StudentDao();
List<Student> list = stuDao.serchAll();
 %>
        <table class='table table-striped table-bordered'>
		<thead><tr>
		<th>ID</th>
		<th>姓名</th>
		<th>性别</th>
		<th>年龄</th>
		</tr>
		</thead>
		<tbody>
		<%
		for(int i =0;i<list.size();i++) {
            %>
            <tr>
            <td><%
			out.println(list.get(i).getId());
			%></td>
			<td><%
			out.println(list.get(i).getName());
			%></td>
			<td><%
			out.println(list.get(i).getSex());
			%>
			</td>
			<td><%
			out.println(list.get(i).getAge());
			%>
			</td>
			</tr>
		<%
		}
		%>
		</tbody>
		</table>
</body>
</html>
