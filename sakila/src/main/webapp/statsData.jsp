<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import ="dao.*" %>
<%
	StatsDataDao statsDataDao = new StatsDataDao();

	// 1. customer 별 총액 (amount) 180이상
	List<Map<String,Object>> amountByCustomer = statsDataDao.amountByCustomer();
	// 2. RentalRate 별 영화개수(filmCount)
	List<Map<String,Object>> filmCountByRentalRate = statsDataDao.filmCountByRentalRate();
	// 3. Rating 별 영화 개수(filmCount)
	List<Map<String,Object>> filmCountByRating = statsDataDao.filmCountByRating();
	// 4. Customer 중 가장 많이 빌려간사람(RentalAmount)
	List<Map<String,Object>> rentalAmountByCustomer = statsDataDao.RentalAmountByCustomer();
	// 5. language 별 영화개수(FilmAmount)
	List<Map<String,Object>> filmAmountByLanguage = statsDataDao.FilmAmountByLanguage();
	// 6. length별 영화 개수(구간을 정해서)
	List<Map<String,Object>> filmAmountByLength = statsDataDao.FilmAmountByLength();
	// 7. actor 별 영화 개수 상위 10명
	List<Map<String,Object>> filmAmountByActor = statsDataDao.FilmAmountByActor();
	// 8. store별 가지고 있는 영화의 수
	List<Map<String,Object>> filmAmountByStore = statsDataDao.FilmAmountByStore();
	// 9. store 요일별 매출
	List<Map<String,Object>> weekAmountByStore = statsDataDao.WeekAmountByStore();
	// 10. staff가 customer에게 rental한 횟수
	List<Map<String,Object>> customerAmountByStaff = statsDataDao.CustomerAmountByStaff();
	// 11. 나라별 customer 수 상위 10개국
	List<Map<String,Object>> customerAmountByCountry = statsDataDao.CustomerAmountByCountry();
	// 12. rating별 film 개수
	List<Map<String,Object>> filmAmountByRating = statsDataDao.FilmAmountByRating();
	// 13. category별 film 개수
	List<Map<String,Object>> filmAmountByCategory = statsDataDao.FilmAmountByCategory();
	// 14. 나라별 선호하는 영화
	List<Map<String,Object>> bestFilmInCountry = statsDataDao.BestFilmInCountry();
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron">
				<h2><a href="<%=request.getContextPath()%>/index.jsp">Home</a></h2>
		</div>
		<h1>1. amountByCustomer</h1>
		<div class = "row">
			<div class="col-sm-8">
				<table class="table">
					<tr>
						<th>customerId</th>
						<th>name</th>
						<th>total</th>
					</tr>
					<%
						for(Map<String,Object> m : amountByCustomer) {
					%>
						<tr>
							<td><%=m.get("customerId")%></td>
							<td><%=m.get("name")%></td>
							<td><%=m.get("total")%></td>
						</tr>
					<%
						}
					%>
				</table>
		</div>
			<div class="col-sm-2"></div>
		</div>
		
		<h1>2. filmCountByRentalRate</h1>
		<div class = "row">
			<div class="col-sm-8">
				<table class="table">
					<tr>
						<th>rentalRate</th>
						<th>count</th>
					</tr>
					<%
						for(Map<String,Object> m : filmCountByRentalRate) {
					%>
						<tr>
							<td><%=m.get("rentalRate")%></td>
							<td><%=m.get("cnt")%></td>
						</tr>
					<%
						}
					%>
				</table>
			</div>
			<div class="col-sm-2"></div>
		</div>
		
		<h1>3. filmCountByRating</h1>
		<div class = "row">
			<div class="col-sm-8">
				<table class="table">
					<tr>
						<th>rating</th>
						<th>count</th>
					</tr>
					<%
						for(Map<String,Object> m : filmCountByRating) {
					%>
						<tr>
							<td><%=m.get("rating")%></td>
							<td><%=m.get("cnt")%></td>
						</tr>
					<%
						}
					%>
				</table>
			</div>
			<div class="col-sm-2"></div>
		</div>
		
		<h1>4. RentalAmountByCustomer</h1>
		<div class = "row">
			<div class="col-sm-8">
				<table class="table">
					<tr>
						<th>customerId</th>
						<th>name</th>
					</tr>
					<%
						for(Map<String,Object> m : rentalAmountByCustomer) {
					%>
						<tr>
							<td><%=m.get("customerId")%></td>
							<td><%=m.get("name")%></td>
						</tr>
					<%
						}
					%>
				</table>
			</div>
			<div class="col-sm-2"></div>
		</div>		
		
		<h1>5. FilmAmountByLanguage</h1>
		<div class = "row">
			<div class="col-sm-8">
				<table class="table">
					<tr>
						<th>name</th>
						<th>count</th>
					</tr>
					<%
						for(Map<String,Object> m : filmAmountByLanguage) {
					%>
						<tr>
							<td><%=m.get("name")%></td>
							<td><%=m.get("cnt")%></td>
						</tr>
					<%
						}
					%>
				</table>
			</div>
			<div class="col-sm-2"></div>
		</div>
		
		<h1>6. filmAmountByLength</h1>
		<div class = "row">
			<div class="col-sm-8">
				<table class="table">
					<tr>
						<th>length</th>
						<th>count</th>
					</tr>
					<%
						for(Map<String,Object> m : filmAmountByLength) {
					%>
						<tr>
							<td><%=m.get("length2")%></td>
							<td><%=m.get("cnt")%></td>
						</tr>
					<%
						}
					%>
				</table>
			</div>
			<div class="col-sm-2"></div>
		</div>
		
		<h1>7. filmAmountByActor</h1>
		<div class = "row">
			<div class="col-sm-8">
				<table class="table">
					<tr>
						<th>name</th>
						<th>count</th>
					</tr>
					<%
						for(Map<String,Object> m : filmAmountByActor) {
					%>
						<tr>
							<td><%=m.get("name")%></td>
							<td><%=m.get("cnt")%></td>
						</tr>
					<%
						}
					%>
				</table>
			</div>
			<div class="col-sm-2"></div>
		</div>
		
		<h1>8. filmAmountByStore</h1>
		<div class = "row">
			<div class="col-sm-8">
				<table class="table">
					<tr>
						<th>storeId</th>
						<th>count</th>
					</tr>
					<%
						for(Map<String,Object> m : filmAmountByStore) {
					%>
						<tr>
							<td><%=m.get("storeId")%></td>
							<td><%=m.get("cnt")%></td>
						</tr>
					<%
						}
					%>
				</table>
			</div>
			<div class="col-sm-2"></div>
		</div>
		
		<h1>9. weekAmountByStore</h1>
		<div class = "row">
			<div class="col-sm-8">
				<table class="table">
					<tr>
						<th>storeId</th>
						<th>DAYOFWEEK</th>
						<th>count</th>
					</tr>
					<%
						for(Map<String,Object> m : weekAmountByStore) {
					%>
						<tr>
							<td><%=m.get("storeId")%></td>
							<td><%=m.get("DAYOFWEEK")%></td>
							<td><%=m.get("sum")%></td>
						</tr>
					<%
						}
					%>
				</table>
			</div>
			<div class="col-sm-2"></div>
		</div>
		
		<h1>10. customerAmountByStaff</h1>
		<div class = "row">
			<div class="col-sm-8">
				<table class="table">
					<tr>
						<th>StaffName</th>
						<th>count</th>
					</tr>
					<%
						for(Map<String,Object> m : customerAmountByStaff) {
					%>
						<tr>
							<td><%=m.get("name")%></td>
							<td><%=m.get("cnt")%></td>
						</tr>
					<%
						}
					%>
				</table>
			</div>
			<div class="col-sm-2"></div>
		</div>
		
		<h1>11. customerAmountByCountry</h1>
		<div class = "row">
			<div class="col-sm-8">
				<table class="table">
					<tr>
						<th>Country</th>
						<th>count</th>
					</tr>
					<%
						for(Map<String,Object> m : customerAmountByCountry) {
					%>
						<tr>
							<td><%=m.get("country")%></td>
							<td><%=m.get("cnt")%></td>
						</tr>
					<%
						}
					%>
				</table>
			</div>
			<div class="col-sm-2"></div>
		</div>
		
		<h1>12. filmAmountByRating</h1>
		<div class = "row">
			<div class="col-sm-8">
				<table class="table">
					<tr>
						<th>rating</th>
						<th>count</th>
					</tr>
					<%
						for(Map<String,Object> m : filmAmountByRating) {
					%>
						<tr>
							<td><%=m.get("rating")%></td>
							<td><%=m.get("cnt")%></td>
						</tr>
					<%
						}
					%>
				</table>
			</div>
			<div class="col-sm-2"></div>
		</div>
		
		<h1>13. filmAmountByCategory</h1>
		<div class = "row">
			<div class="col-sm-8">
				<table class="table">
					<tr>
						<th>name</th>
						<th>count</th>
					</tr>
					<%
						for(Map<String,Object> m : filmAmountByCategory) {
					%>
						<tr>
							<td><%=m.get("name")%></td>
							<td><%=m.get("cnt")%></td>
						</tr>
					<%
						}
					%>
				</table>
			</div>
			<div class="col-sm-2"></div>
		</div>
		
		<h1>14. bestFilmInCountry</h1>
		<div class = "row">
			<div class="col-sm-8">
				<table class="table">
					<tr>
						<th>country</th>
						<th>title</th>
						<th>count</th>
					</tr>
					<%
						for(Map<String,Object> m : bestFilmInCountry) {
					%>
						<tr>
							<td><%=m.get("country")%></td>
							<td><%=m.get("title")%></td>
							<td><%=m.get("cnt")%></td>
						</tr>
					<%
						}
					%>
				</table>
			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>
</body>
</html>