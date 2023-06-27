package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.StoricoDaoImpl;
import model.Storico;

class StoricoImplTest {

	@Test
	void testRicercaPerIdStorico() {
		Storico storico = new Storico();
		StoricoDaoImpl impl = new StoricoDaoImpl();
		storico.setIdStorico(6);
		storico.setMatricola(99);
		storico.setIdRuolo(4);
		impl.inserisci(storico);
		assertEquals(storico, impl.ricercaPerIdStorico(0));
		
	}



}
