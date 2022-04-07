<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="vo.*"%>
<%@ page import="dao.*" %>
<%
	SalesByStoreDao salesByStoreDao = new SalesByStoreDao();
	List<SalesByStore> list = salesByStoreDao.selectSalesByStoreListByPage();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>salesByStore</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron">
			<h2><a href="<%=request.getContextPath()%>/index.jsp">Home</a></h2>
		</div>
		<h1>salesByStore(View)</h1>
		<table class="table">
			<thead class="thead-dark">
				<th>store</th>
				<th>manager</th>
				<th>totalSales</th>
			</thead>
			<tbody>
				<%
					for(SalesByStore s : list){
				%>
					<tr>
						<td><%=s.getStore()%></td>
						<td><%=s.getManager()%></td>
						<td><%=s.getTotalSales()%></td>
					</tr>
				<%
					}	
				%>
			</tbody>
		</table>
	</div>
</body>
</html>