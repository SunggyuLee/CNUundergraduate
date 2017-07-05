<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="all.css" />
<script type="text/javascript" src="event.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div id="header">
		<a onclick="location.href='first.jsp'"> <img
			src=".\images\headerimg.png" alt="header image"></img>
		</a>

	</div>
	<div id="logindp">
		<form method="post" action="loginq.jsp">
			아 이 디 :<input type="text" name="id"><br> <br> 비밀번호
			: <input type="text" name="password"><br> <br> <input
				type="submit" value="로그인">&nbsp; <input type="submit"
				value="회원가입" formaction="join.jsp">&nbsp; <input
				type="submit" value="홈으로" formaction="first.jsp"><br>
		</form>
	</div>
</body>
</html>