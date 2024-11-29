package StepDefinition;

import Juego.Ahorcado;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdivinarUnaLetraCorrecta {
	
	private Ahorcado juego=new Ahorcado();

	@Given("que inició el juego del ahorcado con la palabra \"PELO\"")
	public void que_estoy_jugando_al_ahorcado() {
	    	juego.iniciarJuego("pelo");
	}
	@When("el jugador intenta adivinar la letra \"P\"")
	public void intento_adivinar_la_letra() {
	    juego.adivinarLetra('p');
	}
	@Then("la palabra oculta debería ser \"P___\"")
	public void debería_ver_la_letra_en_la_palabra_oculta() {
	    juego.actualizarPalabraOculta('P', 1);
	}
}
