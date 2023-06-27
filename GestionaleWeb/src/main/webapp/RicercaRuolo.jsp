<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ricerca il Ruolo</title>
</head>
<body>
	
	<form action="RuoloSrv" method="post">
	
		Cerca il Ruolo con il suo id <input type="text" name="idRuolo"/> <br> <br>
		<input type="hidden" name="tipoOperazione" value="ricerca"/>
		<input type="submit" value="Cerca Ruolo" />
	
	</form>

</body>
</html>