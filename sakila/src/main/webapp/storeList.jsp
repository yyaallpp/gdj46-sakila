<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%
	StoreDao storeDao = new StoreDao();
	List<Map<String, Object>> list = storeDao.selectStoreList();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>StoreList</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron">
				<h2><a href="<%=request.getContextPath()%>/index.jsp">Home</a></h2>
		</div>
		
		<a href="">Index</a>
		<h1>Store List</h1>
		
		<div class = "row">
			<div class="col-sm-8">
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th>storeId</th>
							<th>staffId</th>
							<th>staffName</th>
							<th>addressId</th>
							<th>storeAddress</th>
							<th>lastUpdate</th>
						</tr>
					</thead>
					<tbody>
						<%
							for(Map m : list){
						%>
							<tr>
								<td><%=m.get("storeId") %></td>
								<td><%=m.get("staffId") %></td>
								<td><%=m.get("staffName") %></td>
								<td><%=m.get("addressId") %></td>
								<td><%=m.get("storeAddress") %></td>
								<td><%=m.get("lastUpdate") %></td>
							</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
			<div class="col-sm-2"></div>
	</div>
</body>
</html>