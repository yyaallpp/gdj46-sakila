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
		
		<div class="container-fluid">
			<h3>테이블 리스트</h3> 
			<ol class="list-group col-sm-8 text-center">
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/tableList/storeList.jsp" >Store List</a></li>
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/tableList/staffList.jsp">Staff List</a></li>
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/tableList/actorInfoList.jsp">ActorInfoList(View)</a></li>
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/tableList/customerList.jsp">CustomerList(View)</a></li>
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/tableList/filmList.jsp">filmList(View)</a></li>
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/tableList/nicerButSlowerFilmList.jsp">nicerButSlowerFilmList(view)</a></li>
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/tableList/salesByFilmCategory.jsp">salesByFilmCategory(view)</a></li>
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/tableList/salesByStore.jsp">salesByStore(view)</a></li>
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/tableList/staffListView.jsp">staffList(view)</a></li>
			</ol>
			<h3>뷰 리스트</h3>
			<ol class="list-group col-sm-8 text-center">		
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/viewList/filmInStockForm.jsp">filmInStockForm</a></li>
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/viewList/filmNotInStockForm.jsp">filmNotInStockForm</a></li>
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/viewList/rewardsReportForm.jsp">rewardsReport</a></li>
			</ol>
			<h3>상세 검색</h3>
			<ol class="list-group col-sm-8 text-center">
					<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/search/filmSearchForm.jsp">filmSearchForm</a></li>
					<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/search/rentalSearchForm.jsp">rentalSearchForm</a></li>
			</ol>
			<h3>통계 데이터</h3>
			<ol class="list-group col-sm-8 text-center">
					<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/statsData.jsp">statsDate</a></li>
			</ol>
		</div>	
	</div>
</body>
</html>