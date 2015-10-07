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
	<form name="form" action="JardanExceptionCalc" method="post" onsubmit="return validate()">
		<mytag:in_tab x_start="${x_start}" y_start="${y_start}">
		</mytag:in_tab>
		Строка <input type="text" name="replacement_x" value="" /> Столбец 
		<input
			type="text" name="replacement_y" value="" /> 
			<input type="hidden"
			name="verification" value="0"> <br />
			<span style="color: red" id="nf2"></span><br />
		<span style="color: red" id="nf"></span><br />
			<input type="submit"
			value="Да" />
	</form>

<script type="text/javascript">
		function validate() {
			//Считаем значения из полей в переменные
			var x_start = document.forms["form"]["replacement_x"].value;
			var y_start = document.forms["form"]["replacement_y"].value;
			//Если поле пустое выведем сообщение и предотвратим отправку формы
			if (!form.x_start.value.match(/^[0-9]+$/) || replacement_x==0 || replacement_x<2 || replacement_y<2
					|| !form.y_start.value.match(/^[0-9]+$/)|| replacement_y==0) {
				document.getElementById("nf2").innerHTML = "вводить можно только цифры не меньше 2";
				return false;
			}
		}
	</script>
</body>
</html>