<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>rewardReportForm</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron">
				<h2><a href="<%=request.getContextPath()%>/index.jsp">Home</a></h2>
		</div>
		<h1>rewardReportForm</h1>
			<form class="form-inline" method="post" action="<%=request.getContextPath()%>/viewList/rewardsReport.jsp">
				<table class="table">
					<tr>
						<td>minMonthlyPurchase</td>
						<td><input type="text" name="minMonthlyPurchase"></td>
					</tr>
					<tr>
						<td>minDollarAmountPurchase</td>
						<td><input type="text" name="minDollarAmountPurchase"></td>
					</tr>
				</table>
				<button class="btn btn-primary" type="submit">입력 </button>
			</form>
	</div>
</body>
</html>