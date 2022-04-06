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
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/storeList.jsp" >Store List</a></li>
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/staffList.jsp">Staff List</a></li>
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/actorInfoList.jsp">ActorInfoList(View)</a></li>
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/customerList.jsp">CustomerList(View)</a></li>
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/filmList.jsp">filmList(View)</a></li>
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/nicerButSlowerFilmList.jsp">nicerButSlowerFilmList(view)</a></li>
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/salesByFilmCategory.jsp">salesByFilmCategory(view)</a></li>
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/salesByStore.jsp">salesByStore(view)</a></li>
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/staffListView.jsp">staffList(view)</a></li>
			</ol>
			<h3>뷰 리스트</h3>
			<ol class="list-group col-sm-8 text-center">		
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/filmInStockForm.jsp">filmInStockForm</a></li>
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/filmNotInStockForm.jsp">filmNotInStockForm</a></li>
				<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/rewardsReportForm.jsp">rewardsReport</a></li>
			</ol>
			<h3>상세 검색</h3>
			<ol class="list-group col-sm-8 text-center">
					<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/filmSearchForm.jsp">filmSearchForm</a></li>
			</ol>
		</div>	
	</div>
</body>
</html>