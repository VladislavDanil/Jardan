<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytaglib.tld" prefix="mytag"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <form name="form" action="JardanException" method="post">
		Введите количество строк
		<input type="text" name="x_start" value="" />
		Введите количество столбцов
		<input type="text" name="y_start" value="" />
		<input type="submit" value="Да" />
  </form>
  
  
</body>
</html>