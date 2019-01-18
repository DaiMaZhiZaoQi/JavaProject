<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		欢迎使用分发平台
		
		<form action="DispatchEleServlet" method="post" enctype="application/json">
			<input name="userName" value="张三"/>请输入参数
			<button type="submit">发送</button>
		</form>
</body>
</html>