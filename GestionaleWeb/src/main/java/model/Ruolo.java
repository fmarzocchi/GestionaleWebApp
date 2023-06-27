package model;

public class Ruolo {

	int idRuolo;
	String descrizione;
	

	public int getIdRuolo() {
		return idRuolo;
	}
	public void setIdRuolo(int idRuolo) {
		this.idRuolo = idRuolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	@Override
	public boolean equals(Object o) {
		if (o == this) {
            return true;
        }
 
        if (!(o instanceof Ruolo)) {
            return false;
        }
         
        Ruolo c = (Ruolo) o;
         
        return Integer.compare(idRuolo, c.idRuolo) == 0;
    }
	
	
}
