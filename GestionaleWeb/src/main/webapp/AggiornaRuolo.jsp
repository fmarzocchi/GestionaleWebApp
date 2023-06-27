<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aggiorna il Ruolo</title>
</head>
<body>
	
	<form action="RuoloSrv" method="post">
		
		descrizione : <input type="text" name="descrizione"  /> <br> <br>
		 id ruolo : <input type="number" name="idRuolo" /> <br> <br>
		
		<input type="hidden" name="tipoOperazione" value="aggiorna" />
		<input type="submit" value="Aggiorna Ruolo" />
	
	</form>

</body>
</html>