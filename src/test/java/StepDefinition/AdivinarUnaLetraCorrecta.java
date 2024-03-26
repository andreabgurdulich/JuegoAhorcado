package StepDefinition;

import Juego.Ahorcado;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdivinarUnaLetraCorrecta {
	
	private Ahorcado juego;

	@Given("que inició el juego del ahorcado con la palabra \"CASA\"")
	public void que_estoy_jugando_al_ahorcado() {
	    	juego.iniciarJuego("casa");
	}
	@When("el jugador intenta adivinar la letra \"A\"")
	public void intento_adivinar_la_letra(String string) {
	    boolean resultado = juego.adivinarLetra('a');
	}
	@Then("el estado del juego debería ser \"_A_A\"")
	public void debería_ver_la_letra_en_la_palabra_oculta(String string) {
	    juego.actualizarPalabraOculta('A', 2);
	    juego.actualizarPalabraOculta('A', 4);
	}

}
