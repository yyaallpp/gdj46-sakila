<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="dao.*"%>
<%@ page import ="java.util.*"%>
<%
	request.setCharacterEncoding("utf-8"); // 한글 인코딩

	// 페이징
	int currentPage = 1;
	if(request.getParameter("currentPage") != null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int rowPerPage = 10;
	int beginRow = (currentPage-1) * rowPerPage;
	
	// rentalSearchForm에서 값을 받아온다.
	int storeId = -1 ;
	if(storeId != -1){
	storeId = Integer.parseInt(request.getParameter("storeId"));		
	}
	String customerName =request.getParameter("customerName");
	String beginDate = request.getParameter("beginDate");
	String endDate = request.getParameter("endDate");
	
	RentalDao rentalDao = new RentalDao();
	int lastPage = 0;
	int totalRow = rentalDao.totalRow(storeId, customerName, beginDate, endDate);
	lastPage = totalRow / rowPerPage;
	if(totalRow % rowPerPage !=0){
		lastPage++;
	}
	
	List<Map<String,Object>> list =  rentalDao.selectRentalSearchList(storeId, customerName, beginDate, endDate, beginRow, rowPerPage);

%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>rentalSearchAction</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron">
				<h2><a href="<%=request.getContextPath()%>/index.jsp">Home</a></h2>
		</div>
		<h1>rentalSearchAction</h1>
		<table class="table">
			<thead class="thead-dark">
				<tr>
				<th>rentalId</th>
				<th>rentalDate</th>
				<th>returnDate</th>
				<th>rentalId</th>
				<th>customerId</th>
				<th>storeId</th>
				<th>cName</th>
				<th>title</th>
				</tr>
			</thead>
			<%
				for(Map<String,Object> m : list){
			%>
				<tr>
					<td><%=m.get("rentalId") %></td>
					<td><%=m.get("rentalDate") %></td>
					<td><%=m.get("returnDate") %></td>
					<td><%=m.get("customerId") %></td>
					<td><%=m.get("storeId") %></td>
					<td><%=m.get("cName") %></td>
					<td><%=m.get("title") %></td>
				</tr>
			<%
				}
			%>
		</table>
		<div>
			<%
				if(currentPage > 1){
			%>
				<a href="<%=request.getContextPath()%>/search/rentalSearchAction.jsp?currentPage=<%=currentPage-1%>&storeId=<%=storeId%>&customerName=<%=customerName%>&beginDate=<%=beginDate%>&endDate=<%=endDate%>">이전</a>
			<%
				}
			%>
			<%
				if(currentPage < lastPage){
					
				}
			%>
			<a href="<%=request.getContextPath()%>/search/rentalSearchAction.jsp?currentPage=<%=currentPage+1%>&storeId=<%=storeId%>&customerName=<%=customerName%>&beginDate=<%=beginDate%>&endDate=<%=endDate%>">다음</a>
		</div>
	</div>
</body>
</html>