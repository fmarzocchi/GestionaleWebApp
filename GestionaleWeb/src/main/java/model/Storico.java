package model;


public class Storico {

	int idStorico,matricola,idRuolo;
	java.sql.Date dataInizio,dataFine;
	
	
	
	public int getIdStorico() {
		return idStorico;
	}
	public void setIdStorico(int idStorico) {
		this.idStorico = idStorico;
	}
	public int getMatricola() {
		return matricola;
	}
	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}
	public int getIdRuolo() {
		return idRuolo;
	}
	public void setIdRuolo(int idRuolo) {
		this.idRuolo = idRuolo;
	}
	public java.sql.Date getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(java.sql.Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	public java.sql.Date getDataFine() {
		return dataFine;
	}
	public void setDataFine(java.sql.Date dataFine) {
		this.dataFine = dataFine;
	}
	@Override
	public boolean equals(Object o) {
		if (o == this) {
            return true;
        }
 
        if (!(o instanceof Ruolo)) {
            return false;
        }
         
        Storico c = (Storico) o;
         
        return Integer.compare(idStorico, c.idStorico) == 0;
	}
	
	
	
}
