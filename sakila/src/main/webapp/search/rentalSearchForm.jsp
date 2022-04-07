<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<%
	StoreDao storeDao = new StoreDao();
	List<Integer> storeIdList = storeDao.selectStoreIdList();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>rentalSearchForm</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron">
				<h2><a href="<%=request.getContextPath()%>/index.jsp">Home</a></h2>
		</div>
		<h1>rentalSearchForm</h1>
		<form action="<%=request.getContextPath()%>/search/rentalSearchAction.jsp" method="post">
			<table class="table" >
				<tr>
					<!-- storeId 검색 -->
					<td>Store Id</td>
					<td>
						<%
							for(int i : storeIdList){
						%>
								<div><input class="radio" type="radio" name="storeId" value="<%=i%>"><%=i%>번 가게</div>
						<%
							}
						%>
					</td>
				</tr>
				<!--  customer 검색 -->
				<tr>
					<td>customerName</td>
					<td>
						<input class="form-control" type="text" name="customerName">
					</td>
				</tr>
				<!-- rentalDate 검색 -->
				<tr>
					<td>rentalDate</td>
					<td>
						<input class="form-control" type="date" name="beginDate"> ~ 
						<input class="form-control" type="date" name="endDate">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button class="btn btn-primary" type="submit">검색</button>
					</td>
				</tr>
			</table>		
		</form>
	</div>
</body>
</html>