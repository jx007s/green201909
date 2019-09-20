<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>center/notice 변경변경변경</title>
</head>
<body>
<table border="">
	<tr>
		<td>
			<jsp:include page="inc/top.jsp"/>
		</td>
	</tr>
	<tr>
		<td width="800px">
			<jsp:include page="center/${mainUrl }.jsp"/>
		</td>
	</tr>
	<tr>
		<td>
			<jsp:include page="inc/bottom.jsp"/>
		</td>
	</tr>
</table>
</body>
</html>