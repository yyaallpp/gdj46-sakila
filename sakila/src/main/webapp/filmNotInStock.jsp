<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.FilmDao" %>
<%@ page import="java.util.*" %>
<%
	request.setCharacterEncoding("utf-8"); // 한글 인코딩
	// filmNotInStock에서 값 받아오기
	int filmId = Integer.parseInt(request.getParameter("filmId"));
	int storeId = Integer.parseInt(request.getParameter("storeId"));
	FilmDao filmDao = new FilmDao();
	Map<String, Object> map = filmDao.filmNotInStock(filmId, storeId);
	List<Integer> list = (List<Integer>) map.get("list");
	int count = (int) map.get("count");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>filmNotInStock</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron">
			<h2><a href="<%=request.getContextPath()%>/index.jsp">Home</a></h2>
		</div>
		<h1>filmNotInStock</h1>
		<div><%=filmId %>번 영화가 <%=storeId %>번 가게에 <%=count %>개 없음</div>
		<%
			for(int id : list){
		%>
			<%=id %>번,
		<%
			}
		%>
		<div><%=count %>개</div>
	</div>
</body>
</html>