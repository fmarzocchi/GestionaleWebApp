<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inserisci il tuo Storico</title>
</head>
<body>
	
	<form action="StoricoSrv" method="post">
	
		 idStorico : <input type="number" name="idStorico"  /> <br> <br>
		 data inizio : <input type="datetime" name="dataInizio"  /> <br> <br>
		 data fine : <input type="datetime" name="dataFine"  /> <br> <br>
		
		<input type="hidden" name="tipoOperazione" value="inserisci" />
		<input type="submit" value="Inserisci Storico"/>
		
	</form>
	
</body>
</html>