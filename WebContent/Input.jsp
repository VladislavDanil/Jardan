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
  <h1>Введите значения коэффициентов</h1>
  <form name="form" action="JardanExceptionCalc" method="post">
  <mytag:in_tab x_start="${x_start}" y_start="${y_start}">
  </mytag:in_tab>
  Строка <input type="text" name="replacement_x" value="" />
  Столбец <input type="text" name="replacement_y" value="" />
  <input type="hidden" name="verification" value="0">
  <input type="submit" value="Да" />
  </form>
</body>
</html>