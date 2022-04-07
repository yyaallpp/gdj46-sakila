<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.RewardsReport" %>
<%@ page import="java.util.*" %>
<%
	request.setCharacterEncoding("utf-8"); // 한글 인코딩

	// rewardsReportsForm에서 값 받아오기 
	int minMonthlyPurchase = Integer.parseInt(request.getParameter("minMonthlyPurchase"));
	double minDollarAmountPurchase = Double.parseDouble(request.getParameter("minDollarAmountPurchase"));
	RewardsReport r = new RewardsReport();
	Map<String, Object> map = r.rewardsReport(minMonthlyPurchase, minDollarAmountPurchase);
	ArrayList<HashMap<String,Object>> list = (ArrayList<HashMap<String,Object>>)map.get("list"); 
	int countRewardees = (int) map.get("countRewardees"); // 총 몇명인지 알려주는 변수
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>rewardReport</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron">
			<h2><a href="<%=request.getContextPath()%>/index.jsp">Home</a></h2>
		</div>
		<h1>rewardReport</h1>
		<div><%=minMonthlyPurchase%>번 <%=minDollarAmountPurchase%>달러를 충족한 사람은 총 <%=countRewardees %>입니다.</div>
		<table class="table">
			<thead class="thead-dark">
				<th>customerId</th>
				<th>storeId</th>
				<th>firstName</th>
				<th>lastName</th>
				<th>email</th>
				<th>addressId</th>
				<th>active</th>
				<th>createDate</th>
				<th>updateDate</th>
			</thead>
			<tbody>
				<tr>
					<%
						for(Map m : list){
					%>
						<td><%=(Integer)m.get("customerId")%></td>
						<td><%=(Integer)m.get("storeId")%></td>
						<td><%=(String)m.get("firstName")%></td>
						<td><%=(String)m.get("lastName")%></td>
						<td><%=(String)m.get("email")%></td>
						<td><%=(Integer)m.get("addressId")%></td>
						<td><%=(Integer)m.get("active")%></td>
						<td><%=(String)m.get("createDate")%></td>
						<td><%=(String)m.get("updateDate")%></td>
					<%
						}
					%>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>