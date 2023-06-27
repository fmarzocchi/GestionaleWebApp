<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inserisci Ruolo</title>
</head>
<body>
	
	<form action="RuoloSrv" method="post">
	
		 descrizione : <input type="text" name="descrizione"  /> <br> <br>
		 id ruolo : <input type="number" name="idRuolo" /> <br> <br>
		
		<input type="hidden" name="tipoOperazione" value="inserisci" />
		<input type="submit" value="Inserisci Ruolo" />
	
	</form>
	
</body>
</html>