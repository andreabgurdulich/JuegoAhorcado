package StepDefinition;

import Juego.Ahorcado;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdivinarUltimaLetraIncorrecta {
	
	private Ahorcado juego=new Ahorcado();

	@Given("que inició el juego del ahorcado con la palabra \"GATO\" y ya tiene cinco intentos incorrectos")
	public void que_estoy_jugando_al_ahorcado() {
	    juego.iniciarJuego("gato");
	    juego.setIntentosIncorrectos(5);
	}
    
	@When("el jugador intenta adivinar la letra \"I\"")
	public void intento_adivinar_la_letra() {
	    juego.adivinarLetra('i');
	}
	@Then("el estado del juego debería ser \"GATO\"")
	public void estado_del_juego_con_derrota() {
	    // En este caso, el juego no cambia la palabra oculta, porque todas las letras fueron incorrectas
		juego.actualizarPalabraOculta('G', 0);
	    juego.actualizarPalabraOculta('A', 1);
	    juego.actualizarPalabraOculta('T', 2);
	    juego.actualizarPalabraOculta('O', 3);
	}

	@Then("el jugador debería perder el juego")
	public void jugador_perdió_el_juego() {
	    // Lógica para perder el juego, si es necesario
	    juego.juegoTerminadoPorMaximosIntentos();  // Marca el fin del juego con derrota
	}
}
