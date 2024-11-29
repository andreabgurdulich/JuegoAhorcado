package StepDefinition;

import Juego.Ahorcado;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdivinarUnaLetraIncorrecta {
	
	private Ahorcado juego=new Ahorcado();

	@Given("que inició el juego del ahorcado con la palabra \"SAPO\"")
	public void que_estoy_jugando_al_ahorcado() {
	    	juego.iniciarJuego("sapo");
	}
	@When("el jugador intenta adivinar la letra \"J\"")
	public void intento_adivinar_la_letra() {
	    juego.adivinarLetra('j');
	}

	@Then("el jugador sumó un intento incorrecto")
	public void debería_ver_la_letra_en_la_palabra_oculta() {
	    juego.setIntentosIncorrectos(1);
	}

}
