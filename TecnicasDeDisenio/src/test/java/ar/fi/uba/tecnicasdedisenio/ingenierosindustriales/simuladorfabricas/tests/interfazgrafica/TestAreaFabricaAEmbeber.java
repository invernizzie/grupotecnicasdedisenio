package ar.fi.uba.tecnicasdedisenio.ingenierosindustriales.simuladorfabricas.tests.interfazgrafica;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.fi.uba.tecnicasdedisenio.ingenierosindustriales.simuladorfabricas.interfazgrafica.*;

public class TestAreaFabricaAEmbeber {


		private AreaFabricaAEmbeber ventana;
		
		@Before
		public void setUp() throws Exception {
			this.ventana = new AreaFabricaAEmbeber();
		}

		@After
		public void tearDown() throws Exception {
		}

		@Test
		public void testPantalla() {
			this.ventana.run();
			}
}