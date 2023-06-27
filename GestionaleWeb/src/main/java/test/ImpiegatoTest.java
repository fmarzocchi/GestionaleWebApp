package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.ImpiegatoDaoImpl;
import model.Impiegato;

class ImpiegatoTest {

	@Test
	void testRicercaPerCodiceFiscale() {
		ImpiegatoDaoImpl imp = new ImpiegatoDaoImpl();
		Impiegato mario = new Impiegato();
		mario.setNome("Mario");
		mario.setCodiceFiscale("mr10");
		imp.inserisci(mario);
		assertNotNull(imp.ricercaPerCodiceFiscale("mr10"));
		assertEquals(mario, imp.ricercaPerCodiceFiscale("mr10"));
	}

	

}
