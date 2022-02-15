<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/forgot" method="post">
Username: <input type="text" name="username"><br>
Password: <input type="text" name="password"><br>
Confirm Password: <input type="text" name="confirm_password"><br>
<input type="submit" name="submit">

</form>
</body>
</html>