<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Risultati impiegati ricercati per cognome</title>
</head>
<body>
	<table border="1px">
		
		<c:forEach var = "r" items="${impTrovati}">
			
			<tr>
				
				<td> ${ r.getNome()} 
					<form action="ImpiegatoSrv" method="post">
						<input type="hidden" name="codiceFiscale" value="${r.getCodiceFiscale()}" />
						<input type="hidden" name="tipoOperazione" value="elimina" />
						<input type="hidden" name="cognome" value="${r.getCognome()}" />
						<input type="hidden" name="tornaAllaJsp" value="RisultatiRicercaImpiegatoPerCognome" />
						<input type="submit" value="elimina"/>
					</form>
					<form action="ImpiegatoSrv" method="post">
						<input type="hidden" name="codiceFiscale" value="${r.getCodiceFiscale()}" />
						<input type="hidden" name="tipoOperazione" value="aggiorna" />
						<input type="hidden" name="tornaAllaJsp" value="AggiornaImpiegato" />
						<input type="submit" value="aggiorna"/>
					</form>
				</td>
					
				<td> ${r.getCognome()} </td>
					
				<td> ${ r.getCodiceFiscale()} </td>
				
				<td> ${ r.getMatricola()} </td>
					
			</tr>
		
		</c:forEach>
	
	</table>
	

</body>
</html>