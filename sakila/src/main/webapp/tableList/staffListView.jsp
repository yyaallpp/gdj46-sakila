<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="vo.*"%>
<%@ page import="dao.*" %>
<%
	StaffListViewDao staffListViewDao = new StaffListViewDao();
	List<StaffList> list = staffListViewDao.selectStaffListByPage();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>staffListView(View)</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron">
			<h2><a href="<%=request.getContextPath()%>/index.jsp">Home</a></h2>
		</div>
		<h1>staffListView(View)</h1>
		<table class="table">
			<thead class="thead-dark">
				<th>staffId</th>
				<th>name</th>
				<th>address</th>
				<th>phone</th>
				<th>city</th>
				<th>country</th>
				<th>storeId</th>
			</thead>
			<tbody>
				<%
					for(StaffList s : list){
				%>
					<tr>
						<td><%=s.getStaffId()%></td>
						<td><%=s.getName()%></td>
						<td><%=s.getAddress()%></td>
						<td><%=s.getPhone()%></td>
						<td><%=s.getCity()%></td>
						<td><%=s.getCountry()%></td>
						<td><%=s.getStoreId()%></td>
					</tr>
				<%
					}	
				%>
			</tbody>
		</table>
	</div>
</body>
</html>