<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%
	StaffDao staffDao = new StaffDao();
	List<Map<String, Object>> list = staffDao.selectStaffList();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>staffList</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron">
			<h2><a href="<%=request.getContextPath()%>/index.jsp">Home</a></h2>
		</div>
		
		<h1>Staff List</h1>
		<div class="row">
			<div class="col-sm-8">
				<table class="table">
					<thead class="thead-dark">
						<th>staffId</th>
						<th>staffName</th>
						<th>storeId</th>
						<th>staffAddress</th>
						<th>userName</th>
						<th>email</th>
						<th>lastUpdate</th>
					</thead>
					<tbody>
						<%
							for(Map m : list){
						%>
							<tr>
								<td><%=m.get("staffId")%></td>
								<td><%=m.get("staffName")%></td>
								<td><%=m.get("storeId")%></td>
								<td><%=m.get("staffAddress")%></td>
								<td><%=m.get("userName")%></td>
								<td><%=m.get("email")%></td>
								<td><%=m.get("lastUpdate")%></td>
							</tr>
						<%	
							}
						%>
					</tbody>
				</table>
			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>
</body>
</html>