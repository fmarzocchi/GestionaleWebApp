<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ricerca un Impiegato per il cognome</title>
</head>
<body>
	
	<form action="ImpiegatoSrv" method="post">
		
		<input type="text" name="cognome" />
		<input type="hidden" name="tipoOperazione" value="ricercaPerCognome" />
		<input type="submit" value="Submit" />
	
	</form>

</body>
</html>