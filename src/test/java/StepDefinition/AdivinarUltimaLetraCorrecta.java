package StepDefinition;

import Juego.Ahorcado;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdivinarUltimaLetraCorrecta {
	
	private Ahorcado juego;

	@Given("que inició el juego del ahorcado con la palabra \"CASA\"")
	public void que_estoy_jugando_al_ahorcado() {
	    	juego.iniciarJuego("casa");
	}
	@When("el jugador intenta adivinar la letra \"C\", y el jugador intenta adivinar la letra \"A\", y el jugador intenta adivinar la letra \"S\"")
	public void intento_adivinar_la_letra(String string) {
	    boolean resultado1 = juego.adivinarLetra('c');
	    boolean resultado2 = juego.adivinarLetra('a');
	    boolean resultado3 = juego.adivinarLetra('s');
	}
	@Then("el estado del juego debería ser \"CASA\"")
	public void debería_ver_la_letra_en_la_palabra_oculta(String string) {
	    juego.actualizarPalabraOculta('C', 1);
	    juego.actualizarPalabraOculta('A', 2);
	    juego.actualizarPalabraOculta('S', 3);
	    juego.actualizarPalabraOculta('A', 4);
	    juego.juegoTerminado();
	}

}
