<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board" method="post">
					<input type="text" id="kwd" name="kwd" value="${kwd }">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
					<c:forEach items="${list }" var="vo" varStatus="status">
					<tr>
						<td>${totalCount - (10*(currentPage-1)+status.index) }</td>
						<td style="padding-left:${30*vo.dept }px" >
						<c:if test="${vo.dept != 0 }">
						<img src="/mysite2/assets/images/reply.png"/></c:if>
						<a href="${pageContext.servletContext.contextPath }/board?a=view&no=${vo.no}">${vo.title }</a>
						
						</td>
						<td>${vo.user_name }</td>
						<td>${vo.hit }</td>
						<td>${vo.write_date }</td>
						<td>
						<c:if test="${vo.user_no==authuser.no }">
						<a href="${pageContext.servletContext.contextPath }/board?a=delete&no=${vo.no}" class="del">삭제</a>
						</c:if>
						</td>
					</tr>
					</c:forEach>
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
					<li><c:if test="${currentPage !=1 }"><a href="${pageContext.servletContext.contextPath }/board?kwd=${kwd}&currentPage=${currentPage-1}">◀</a></c:if></li>
					<c:forEach var="numPage" begin="${prevPage }" end="${postPage }" step="1">
						<c:choose>
						<c:when test="${currentPage == numPage }">
						<li class="selected"><a href="${pageContext.servletContext.contextPath }/board?kwd=${kwd}&currentPage=${numPage }">${numPage }</a></li>
						</c:when>
						<c:otherwise>
						<li><a href="${pageContext.servletContext.contextPath }/board?kwd=${kwd}&currentPage=${numPage }">${numPage }</a></li>
						</c:otherwise>
						</c:choose>
						</c:forEach>
						<li><c:if test="${currentPage !=maxPage }"><a href="${pageContext.servletContext.contextPath }/board?kwd=${kwd}&currentPage=${currentPage+1}">▶</a></c:if></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
					<c:if test="${! empty authuser }">
					<a href="${pageContext.servletContext.contextPath }/board?a=writeform" id="new-book">글쓰기</a>
					</c:if>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>