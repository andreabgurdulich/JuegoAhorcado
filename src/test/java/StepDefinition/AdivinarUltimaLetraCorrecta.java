package StepDefinition;

import Juego.Ahorcado;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdivinarUltimaLetraCorrecta {
	
	private Ahorcado juego=new Ahorcado();
	
	@Given("que inició el juego del ahorcado con la palabra \"CASA\"")
	public void que_estoy_jugando_al_ahorcado() {
	    juego.iniciarJuego("casa");
	}
	
	@When("el jugador intenta adivinar la letra \"C\"")
	public void primer_intento() {
	    juego.adivinarLetra('c');
	}
	
	@When("el jugador intenta adivinar la letra \"A\"")
	public void segundo_intento() {
	    juego.adivinarLetra('a');
	}
	
	@When("el jugador intenta adivinar la letra \"S\"")
	public void tercer_intento() {
	    juego.adivinarLetra('s');
	}
	
	@Then("el estado del juego debería ser \"CASA\"")
	public void debería_ver_la_palabra_completa() {
	    juego.actualizarPalabraOculta('C', 0);
	    juego.actualizarPalabraOculta('A', 1);
	    juego.actualizarPalabraOculta('S', 2);
	    juego.actualizarPalabraOculta('A', 3);
	}

	@Then("el jugador debería ganar el juego")
	public void jugador_ganó_el_juego() {
	    // Lógica para ganar el juego
	    juego.juegoTerminado();  // Marca el fin del juego con victoria
	}
}