package StepDefinition;

import Juego.Ahorcado;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdivinarUltimaLetraIncorrecta {
	
	private Ahorcado juego;

	@Given("que inició el juego del ahorcado con la palabra \"CASA\"")
	public void que_estoy_jugando_al_ahorcado() {
	    	juego.iniciarJuego("casa");
	}
    
	@When("el jugador intenta adivinar la letra \"E\","
			+ "y el jugador intenta adivinar la letra \"R\","
			+ "y el jugador intenta adivinar la letra \"T\","
			+ "y el jugador intenta adivinar la letra \"P\","
			+ "y el jugador intenta adivinar la letra \"L\","
			+ "y el jugador intenta adivinar la letra \"I\"")
	public void intento_adivinar_la_letra(String string) {
	    boolean resultado1 = juego.adivinarLetra('e');
	    boolean resultado2 = juego.adivinarLetra('r');
	    boolean resultado3 = juego.adivinarLetra('t');
	    boolean resultado4 = juego.adivinarLetra('p');
	    boolean resultado5 = juego.adivinarLetra('l');
	    boolean resultado6 = juego.adivinarLetra('i');
	    
	}
	@Then("el estado del juego debería ser \"CASA\"")
	public void debería_ver_la_letra_en_la_palabra_oculta(String string) {
	    juego.juegoTerminado();
	}

}
