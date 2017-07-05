<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset="utf-8">
		<title>TP2</title>
		<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAt6BBBktuxywonLx39plI_S4sqTiEz7SA"></script>
		<link rel="stylesheet" href="./CSS/TP2_201202149_백승진.css" type="text/css">
		<script type="text/javascript" src="./JS/MapAPI.js"></script>
	</head>

	<body>
		<header>
			<div class="Member">
				<%
					String id = (String) session.getAttribute("id");
					if (id != null) {
				%>
				<%=id%>님&nbsp; <a onclick="location.href='logout.jsp'">로그아웃</a>
				<%
					} else {
				%>
				<a onclick="location.href='join.jsp'">회원가입</a> <a
					onclick="location.href='login.jsp'">로그인</a>
				<%
					}
				%>
			</div>
		
			<div class="Bar">
				<button>거리</button>
				<button>문화재</button>
				<button>놀이공원</button>
				<button>명소</button>
			</div>
			<div>
			
			</div>
		</header>
		<main>
			<!-- 앞으로 본격적인 구글 맵 API작업을 하게 될 공간 -->
			<div id="googleMap"></div>
		 </main>

		<footer>
			<!-- footer -->
			<pre>copyright by 201202149 Seung Jin Baek</pre>
		</footer>
	</body>
</html>