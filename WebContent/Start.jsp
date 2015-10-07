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
	<form name="form" action="JardanException" method="post" onsubmit="return validate()">
		Введите количество строк <input type="text" name="x_start" value="" />
		Введите количество столбцов <input type="text" name="y_start" value="" /><br />
		<span style="color: red" id="nf2"></span><br />
		<span style="color: red" id="nf"></span><br />
		<input type="submit" value="Да" />
	</form>
	<script type="text/javascript">
		function validate() {
			//Считаем значения из полей в переменные
			var x_start = document.forms["form"]["x_start"].value;
			var y_start = document.forms["form"]["y_start"].value;
			//Если поле пустое выведем сообщение и предотвратим отправку формы
			if (!form.x_start.value.match(/^[0-9]+$/) || x_start==0 || x_start<2 || y_start<2
					|| !form.y_start.value.match(/^[0-9]+$/)|| y_start==0) {
				document.getElementById("nf2").innerHTML = "вводить можно только цифры не меньше 2";
				return false;
			}
		}
	</script>

</body>
</html>