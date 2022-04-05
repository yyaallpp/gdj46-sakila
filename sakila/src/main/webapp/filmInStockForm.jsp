<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>filmInStockForm</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
	<body>
		<div class="container-fluid">
			<div class="jumbotron">
					<h2><a href="<%=request.getContextPath()%>/index.jsp">Home</a></h2>
			</div>
			<h1>filmInStock</h1>
			<form class="form-inline" method="post" action="<%=request.getContextPath()%>/filmInStock.jsp">
				<table class="table">
					<tr>
						<td>filmId</td>
						<td><input type="text" name="filmId"></td>
					</tr>
					<tr>
						<td>storeId</td>
						<td><input type="text" name="storeId"></td>
					</tr>
				</table>
				<button class="btn btn-primary" type="submit">입력 </button>
			</form>
		</div>
	</body>
</html>