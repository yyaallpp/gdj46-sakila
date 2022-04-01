<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>index</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron">
			<h1>Sakila</h1>
		</div>
		
		<div class="container-fluid row">
			<div class = "col-sm-2"> </div>
			<ol class="list-group col-sm-8 text-center">
					<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/storeList.jsp" >Store List</a></li>
					<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/staffList.jsp">Staff List</a></li>
			</ol>
			<div class = "col-sm-2"> </div>
		</div>	
	</div>
</body>
</html>