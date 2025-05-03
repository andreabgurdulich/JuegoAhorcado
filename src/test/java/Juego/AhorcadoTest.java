package Juego;

import static org.junit.Assert.*;

import org.junit.Test;

public class AhorcadoTest {
	@Test
	public void pruebaAdivinarLetra() {
		Ahorcado juego = new Ahorcado();
		juego.iniciarJuego("hola");

		assertTrue(juego.adivinarLetra('h'));
		assertFalse(juego.adivinarLetra('e'));
	}

	@Test
	public void pruebaObtenerPalabraOculta() {
		Ahorcado juego = new Ahorcado();
		juego.iniciarJuego("mundo");

		assertEquals("_____", juego.obtenerPalabraOculta());

		juego.adivinarLetra('m');
		assertEquals("m____", juego.obtenerPalabraOculta());
	}

	@Test
	public void pruebaPalabraOcultaInicial() {
		Ahorcado juego = new Ahorcado();
		juego.iniciarJuego("hola");

		assertEquals("____", juego.obtenerPalabraOculta());
	}

	@Test
	public void pruebaJuegoTerminado() {
		Ahorcado juego = new Ahorcado();
		juego.iniciarJuego("hola");

		juego.adivinarLetra('h');
		juego.adivinarLetra('o');
		juego.adivinarLetra('l');
		juego.adivinarLetra('a');

		assertTrue(juego.juegoTerminado());
	}

	@Test
	public void pruebaJuegoNoTerminado() {
		Ahorcado juego = new Ahorcado();
		juego.iniciarJuego("hola");

		juego.adivinarLetra('h');
		juego.adivinarLetra('x');
		juego.adivinarLetra('o');
		juego.adivinarLetra('l');

		assertFalse(juego.juegoTerminado());
	}

	@Test
	public void pruebaJuegoTerminadoPorMaximosIntentos() {
		Ahorcado juego = new Ahorcado(4);
		juego.iniciarJuego("hola");

		juego.adivinarLetra('x');
		juego.adivinarLetra('y');
		juego.adivinarLetra('z');
		juego.adivinarLetra('w');

		assertTrue(juego.juegoTerminadoPorMaximosIntentos());
	}

	@Test
	public void pruebaAdivinarConCaracterEspecial() {
		Ahorcado juego = new Ahorcado();
		juego.iniciarJuego("niño");

		assertTrue(juego.adivinarLetra('ñ'));
		assertEquals("__ñ_", juego.obtenerPalabraOculta());
	}

	@Test
	public void pruebaLetraRepetidaNoCuentaDoble() {
		Ahorcado juego = new Ahorcado();
		juego.iniciarJuego("casa");

		juego.adivinarLetra('c');
		juego.adivinarLetra('c'); // repetir letra

		assertEquals("c___", juego.obtenerPalabraOculta());
		assertEquals(0, juego.getIntentosIncorrectos()); // No debería contar como error
	}

	@Test
	public void pruebaLetraConTilde() {
		Ahorcado juego = new Ahorcado();
		juego.iniciarJuego("café");

		juego.adivinarLetra('a');
		assertEquals("_a__", juego.obtenerPalabraOculta());

		juego.adivinarLetra('é');
		assertEquals("_a_é", juego.obtenerPalabraOculta());
	}

	@Test
	public void pruebaCantidadErroresAlFallar() {
		Ahorcado juego = new Ahorcado();
		juego.iniciarJuego("sol");

		juego.adivinarLetra('a');
		juego.adivinarLetra('b');

		assertEquals(2, juego.getIntentosIncorrectos());
	}

	@Test
	public void pruebaGanarConUltimaLetra() {
		Ahorcado juego = new Ahorcado();
		juego.iniciarJuego("sol");

		juego.adivinarLetra('s');
		assertFalse(juego.juegoTerminado());

		juego.adivinarLetra('o');
		assertFalse(juego.juegoTerminado());

		juego.adivinarLetra('l');
		assertTrue(juego.juegoTerminado());
	}

	@Test
	public void pruebaMultiplesInstanciasLetra() {
		Ahorcado juego = new Ahorcado();
		juego.iniciarJuego("coco");

		juego.adivinarLetra('c');
		assertEquals("c_c_", juego.obtenerPalabraOculta());

		juego.adivinarLetra('o');
		assertEquals("coco", juego.obtenerPalabraOculta());
		assertTrue(juego.juegoTerminado());
	}

	@Test
	public void pruebaAdivinarDespuesDeGanar() {
		Ahorcado juego = new Ahorcado();
		juego.iniciarJuego("sol");

		juego.adivinarLetra('s');
		juego.adivinarLetra('o');
		juego.adivinarLetra('l');

		assertTrue(juego.juegoTerminado());
		boolean resultado = juego.adivinarLetra('x');
		assertFalse(resultado); 
	}

	@Test
	public void pruebaAdivinarSinIniciarJuego() {
		Ahorcado juego = new Ahorcado();

		boolean resultado = juego.adivinarLetra('a');
		assertFalse(resultado);
	}
	
	@Test
	public void pruebaGanarSinErrores() {
	    Ahorcado juego = new Ahorcado();
	    juego.iniciarJuego("pan");

	    juego.adivinarLetra('p');
	    juego.adivinarLetra('a');
	    juego.adivinarLetra('n');

	    assertEquals(0, juego.getIntentosIncorrectos());
	    assertTrue(juego.juegoTerminado());
	}
}
