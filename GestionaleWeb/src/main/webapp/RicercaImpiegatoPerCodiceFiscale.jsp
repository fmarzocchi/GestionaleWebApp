<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ricerca il tuo impiegato</title>
</head>
<body>

	<form action="ImpiegatoSrv" method="post">
	
		Cerca l'impiegato con il suo codice fiscale <input type="text" name="codiceFiscale"/> <br> <br>
		<input type="hidden" name="tipoOperazione" value="cerca"/>
		<input type="submit" value="Submit" />
	
	</form>

</body>
</html>