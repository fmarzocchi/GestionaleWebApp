package dao;



import model.Ruolo;

public interface RuoloDao {

	public Ruolo ricercaPerId(int id);
	public void inserisci(Ruolo r);
	public boolean aggiorna(Ruolo r);
	public boolean elimina(int id);
	
	
}
