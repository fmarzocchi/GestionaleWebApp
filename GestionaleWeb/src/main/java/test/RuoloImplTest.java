package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.RuoloDaoImpl;
import model.Ruolo;

class RuoloImplTest {

	@Test
	void testRicercaPerId() {
		
		RuoloDaoImpl ruoloImpl = new RuoloDaoImpl();
		Ruolo ruolo = new Ruolo();
		ruolo.setDescrizione("gestore");
		ruolo.setIdRuolo(78);
		ruoloImpl.inserisci(ruolo);
		Ruolo ruoloTrovato = ruoloImpl.ricercaPerId(78); 
		assertEquals(ruolo, ruoloTrovato);
	}

	

}
