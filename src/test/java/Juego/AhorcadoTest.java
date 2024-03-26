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
}
