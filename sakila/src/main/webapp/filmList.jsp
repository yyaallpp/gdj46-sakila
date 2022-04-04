<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="vo.*"%>
<%@ page import="dao.*" %>
<%
	FilmListDao filmListDao = new FilmListDao();
	// 페이징
	int currentPage = 1;
	if(request.getParameter("currentPage")!= null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		System.out.println(currentPage + "<-- filmList currentPage");
	}
	int rowPerPage = 10;
	int beginRow = (currentPage - 1) * rowPerPage;
	List<FilmList> list = filmListDao.selectFilmListByPage(beginRow, rowPerPage);
	int lastPage = 0;
	int totalCount = filmListDao.selectFilmListTotalRow();
	lastPage = totalCount / rowPerPage;
	if(totalCount % rowPerPage !=0 ){
		lastPage++;
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>filmList</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron">
			<h2><a href="<%=request.getContextPath()%>/index.jsp">Home</a></h2>
		</div>
		
		<h1>filmList(View)</h1>
		<table class="table">
			<thead class="thead-dark">
				<th>filmId</th>
				<th>title</th>
				<th>description</th>
				<th>category</th>
				<th>price</th>
				<th>length</th>
				<th>rating</th>
				<th>actors</th>
			</thead>
			<tbody>
				<%
					for(FilmList f : list){
				%>
					<tr>
						<td><%=f.getFilmId()%></td>
						<td><%=f.getTitle()%></td>
						<td><%=f.getDescription()%></td>
						<td><%=f.getCategory()%></td>
						<td><%=f.getPrice()%></td>
						<td><%=f.getLength()%></td>
						<td><%=f.getRating()%></td>
						<td><%=f.getActors()%></td>
					</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<div>
			<%
				if(currentPage > 1){
			%>
			<a href="<%=request.getContextPath()%>/filmList.jsp?currentPage=<%=currentPage-1 %>">이전</a>	
			<%
				}
			%>
			<%
				if(currentPage < lastPage){
			%>
			<a href="<%=request.getContextPath()%>/filmList.jsp?currentPage=<%=currentPage + 1 %>">다음</a>	
			<%
				}
			%>
		</div>
	</div>
</body>
</html>