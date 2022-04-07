<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import ="dao.*" %>
<%@ page import ="vo.*" %>
<%
	int currentPage = 1;
	if(request.getParameter("currentPage") != null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		System.out.println(currentPage + "<-- filmSearchAction currentPage");
	}
	
	// filmSearchForm에서 값 받아오기 공백이라도 무조건 넘어옴
	String category = request.getParameter("category");
	String rating = request.getParameter("rating");
	double price = -1; // 0이라는 값이 존재 할 수도 있다. -> price데이터가 입력되지 않았을 때
	if(!request.getParameter("price").equals("")){
		price = Double.parseDouble(request.getParameter("price"));		
	}
	int length = -1; // 0이라는 값이 존재 할 수도 있다. -> length데이터가 입력되지 않았을 때
	if(!request.getParameter("length").equals("")){
		length = Integer.parseInt(request.getParameter("length"));	
	}
	String title = request.getParameter("title");
	String actor = request.getParameter("actor");
	
	// 페이징
	int rowPerPage = 10;
	int beginRow = (currentPage - 1) * rowPerPage;
	
	FilmDao filmDao = new FilmDao();
	List<FilmList> list = filmDao.selectFilmListSearch(beginRow, rowPerPage, category, rating, price, length, title, actor);
	System.out.println(list.size());
	
	int lastPage = 0;
	int totalCount = filmDao.totalRow(category, rating, price, length, title, actor);
	System.out.println(totalCount + " <-- filmSearchAction totalCount");
	lastPage = totalCount / rowPerPage;
	if(totalCount % rowPerPage !=0){
		lastPage++;
	}
	System.out.println(lastPage + " <-- filmSearchAction lastPage");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>filmSearchAction</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron">
				<h2><a href="<%=request.getContextPath()%>/index.jsp">Home</a></h2>
		</div>
		<table class="table">
			<%
				for(FilmList f : list){
			%>
				<tr>
					<td><%=f.getFilmId() %></td>
					<td><%=f.getTitle() %></td>
					<td><%=f.getDescription() %></td>
					<td><%=f.getCategory() %></td>
					<td><%=f.getPrice() %></td>
					<td><%=f.getLength() %></td>
					<td><%=f.getRating() %></td>
					<td><%=f.getActors() %></td>
				</tr>
			<%
				}
			%>
		</table>
		<div>
			<%
				if(currentPage > 1){
			%>
			<a href="<%=request.getContextPath()%>/search/filmSearchAction.jsp?currentPage=<%=currentPage-1 %>&category=<%=category%>&rating=<%=rating%>&price=<%=price%>&length=<%=length%>&title=<%=title%>&actor=<%=actor%>">이전</a>	
			<%
				}
			%>
			<%
				if(currentPage < lastPage){
			%>
			<a href="<%=request.getContextPath()%>/search/filmSearchAction.jsp?currentPage=<%=currentPage + 1 %>&category=<%=category%>&rating=<%=rating%>&price=<%=price%>&length=<%=length%>&title=<%=title%>&actor=<%=actor%>">다음</a>	
			<%
				}
			%>
		</div>
	</div>
</body>
</html>