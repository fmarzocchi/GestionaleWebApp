package dao;

import model.Storico;

public interface StoricoDao {
	
	public Storico ricercaPerIdStorico(int idStorico);
	public void inserisci(Storico s);
	public boolean aggiorna(Storico s);
	public boolean elimina(int idStorico);
		
}
