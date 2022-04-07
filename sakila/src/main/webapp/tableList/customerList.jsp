<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="vo.*"%>
<%@ page import="dao.*" %>
<%
	CustomerListDao customerListDao = new CustomerListDao();
	// 페이징
	int currentPage = 1;
	if(request.getParameter("currentPage") != null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		System.out.println(currentPage + "<-- customerList currentPage");
	}
	
	int rowPerPage = 5;
	int beginRow = (currentPage - 1) * rowPerPage;
	List<CustomerList> list = customerListDao.selectCustomerListByPage(beginRow, rowPerPage);
	int lastPage = 0;
	int totalCount = customerListDao.selectCustomerListTotalRow();
	lastPage = totalCount / rowPerPage;
	if(lastPage % rowPerPage != 0){
		lastPage++;
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>customerList</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron">
			<h2><a href="<%=request.getContextPath()%>/index.jsp">Home</a></h2>
		</div>
		
		<h1>customerList(View)</h1>
		<table class="table">
			<thead class="thead-dark">
				<th>customerId</th>
				<th>name</th>
				<th>address</th>
				<th>phone</th>
				<th>city</th>
				<th>country</th>
				<th>notes</th>
				<th>storeId</th>
			</thead>
			<tbody>
				<%
					for(CustomerList c : list){
				%>
					<tr>
						<td><%=c.getCustomerId()%></td>
						<td><%=c.getName()%></td>
						<td><%=c.getAddress()%></td>
						<td><%=c.getPhone()%></td>
						<td><%=c.getCity()%></td>
						<td><%=c.getCountry()%></td>
						<td><%=c.getNotes()%></td>
						<td><%=c.getStoreId()%></td>
						
					</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<div>
			<%
				if(currentPage > 1){
			%>
			<a href="<%=request.getContextPath()%>/tableList/customerList.jsp?currentPage=<%=currentPage-1 %>">이전</a>	
			<%
				}
			%>
			<%
				if(currentPage < lastPage){
			%>
			<a href="<%=request.getContextPath()%>/tableList/customerList.jsp?currentPage=<%=currentPage + 1 %>">다음</a>	
			<%
				}
			%>
		</div>
	</div>
</body>
</html>