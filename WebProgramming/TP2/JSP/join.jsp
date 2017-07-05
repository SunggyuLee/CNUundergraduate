<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8R">
<link rel="stylesheet" type="text/css" href="all.css" />
<script type="text/javascript" src="event.js"></script>
<title>회원가입</title>
</head>
<body>
	<div id="header">
		<a onclick="location.href='first.jsp'"> <img
			src=".\images\headerimg.png" alt="header image"></img>
		</a>

	</div>
	<div id="joindp">
		<form action="joinq.jsp" method="post">
			<h2>회 원 가 입</h2>
			<br> ID : <input type="text" name="id"><br> <br>
			비밀번호 : <input type="password" name="password"><br> <br>
			이름 : <input type="text" name="name"><br> <br> 이메일 :
			<input type="email" name="email" placeholder="형식 : 이메일@사이트"><br>
			<br> 휴대폰 : <select name="ph1">
				<option value="010">010</option>
				<option value="011">011</option>
				<option value="016">016</option>
			</select> -<input type="text" name="ph2" size="5" maxlength="4"> - <input
				type="text" name="ph3" size="5" maxlength="4"><br> <br>
			생년월일 : <input type="date" name="date"><br> <br> 주소
			: <input type="text" name="address"><br> <br> <input
				type="submit" value="회원가입">&nbsp; <input type="reset"
				value="다시입력">&nbsp; <input type="button" value="취소"
				onclick="location.href='first.jsp'">
		</form>
	</div>
</body>
</html>