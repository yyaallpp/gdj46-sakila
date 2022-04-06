<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "java.sql.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "vo.Category" %>
<%
	CategoryDao categoryDao = new CategoryDao();
	List<Category> categoryList = categoryDao.selectCategory();
	FilmDao filmDao = new FilmDao();
	List<Double> priceList = filmDao.selectfilmPriceList();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>filmSearchForm</title>	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron">
				<h2><a href="<%=request.getContextPath()%>/index.jsp">Home</a></h2>
		</div>
		<h1>filmSearchForm</h1>
		<form action="<%=request.getContextPath()%>/filmSearchAction.jsp" method="post">
			<table class="table">
				<tr>
					<td>카테고리</td>
					<td>
						<select name="category" class="form-select" >
							<option value="">카테고리 선택</option>
							<%
								for(Category c : categoryList){
							%>
								<option value="<%=c.getName()%>"><%=c.getName()%></option>
							<%
								}
							%>
						</select>
					</td>
				</tr>
				<tr>
					<td>등급</td>
					<td>
						<select name="rating" class="form-select">
						<option value="">등급 선택</option>
							<option value="G">G</option>
							<option value="PG">PG</option>
							<option value="PG-13">PG-13</option>
							<option value="R">R</option>
							<option value="NC-17">NC-17</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>대여료</td>
					<td>
						<div><input class="radio" type="radio" name="price" value="" checked="checked">선택안함</div>
						<%
							for(Double p : priceList){
						%>
							<div><input class="radio" type="radio" name="price" value="<%=p%>"><%=p%></div>
						<%
							}
						%>
					</td>
				</tr>
				<tr>
					<td>영화시간</td>
					<td>
						<div><input class="radio" type="radio" name="length" value="" checked="checked">선택안함</div>
						<div><input class="radio" type="radio" name="length" value="0">1시간 미만</div> <!-- length < 60 -->
						<div><input class="radio"type="radio" name="length" value="1">1시간 이상</div> <!-- length >= 60 -->
					</td>
				</tr>
				<tr>
					<td>제목</td>
					<td>
						<input class="form-control" type="text" name="title">
					</td>
				</tr>
				<tr>
					<td>배우 검색</td>
					<td>
						<input class="form-control" type="text" name="actor">
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