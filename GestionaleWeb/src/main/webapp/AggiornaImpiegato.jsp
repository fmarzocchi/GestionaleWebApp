<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aggiorna il tuo impiegato</title>
</head>
<body>

	<form action="ImpiegatoSrv" method="post">
	
		 nome : <input type="text" name="nome"  /> <br> <br>
		 cognome : <input type="text" name="cognome" /> <br> <br>
		 codice fiscale : <input type="text" name="codiceFiscale"  /> <br> <br>
		 matricola : <input type="number" name="matricola" /> <br> <br>
		
		<input type="hidden" name="tipoOperazione" value="aggiorna" />
		<input type="submit" value="Aggiorna Impiegato"/>
		
	</form>

</body>
</html>