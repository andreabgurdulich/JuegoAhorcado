package StepDefinition;

import Juego.Ahorcado;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdivinarUnaLetraIncorrecta {
	
	private Ahorcado juego;

	@Given("que inició el juego del ahorcado con la palabra \"CASA\"")
	public void que_estoy_jugando_al_ahorcado() {
	    	juego.iniciarJuego("casa");
	}
	@When("el jugador intenta adivinar la letra \"J\"")
	public void intento_adivinar_la_letra(String string) {
	    boolean resultado = juego.adivinarLetra('j');
	}
	@Then("el estado del juego debería ser \"_ _ _ _\"")
	public void debería_ver_la_letra_en_la_palabra_oculta(String string) {
	    
	}

}
