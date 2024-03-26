package Juego;

public class Ahorcado {
	 private String palabraAAdivinar;
	    private StringBuilder palabraOculta;
	    private int intentosIncorrectos;
	    private int maxIntentosIncorrectos;

	    public Ahorcado() {
	        this.maxIntentosIncorrectos=6; //Número de intentos permitidos
	    }

	    public Ahorcado(int maxIntentos) {
	        this.maxIntentosIncorrectos = maxIntentos;
	        this.intentosIncorrectos = 0;
	    }

	    public String getPalabraAAdivinar() {
			return palabraAAdivinar;
		}

		public void setPalabraAAdivinar(String palabraAAdivinar) {
			this.palabraAAdivinar = palabraAAdivinar;
		}

		public StringBuilder getPalabraOculta() {
			return palabraOculta;
		}

		public void setPalabraOculta(StringBuilder palabraOculta) {
			this.palabraOculta = palabraOculta;
		}

		public int getIntentosIncorrectos() {
			return intentosIncorrectos;
		}

		public void setIntentosIncorrectos(int intentosIncorrectos) {
			this.intentosIncorrectos = intentosIncorrectos;
		}

		public int getMaxIntentosIncorrectos() {
			return maxIntentosIncorrectos;
		}

		public void setMaxIntentosIncorrectos(int maxIntentosIncorrectos) {
			this.maxIntentosIncorrectos = maxIntentosIncorrectos;
		}

		public void iniciarJuego(String palabraAAdivinar) {
	        this.palabraAAdivinar = palabraAAdivinar;
	        this.palabraOculta = new StringBuilder();
	        for (int i = 0; i < palabraAAdivinar.length(); i++) {
	            this.palabraOculta.append('_');
	        }
	    }

	    public String obtenerPalabraAAdivinar() {
	    	String ruta = "ListaPalabras.txt";
	    	PalabrasAleatorias pa = new PalabrasAleatorias();
	    	pa.cargarPalabrasDesdeArchivo(ruta);
	    	return pa.obtenerPalabraAleatoria();
	    }

	    public boolean adivinarLetra(char letra) {
	        boolean letraAdivinada = false;
	        if (!juegoTerminado()) {
	            boolean letraCorrecta = false;
	            for (int i = 0; i < palabraAAdivinar.length(); i++) {
	                if (palabraAAdivinar.charAt(i) == letra) {
	                    this.actualizarPalabraOculta(letra, i);
	                    letraCorrecta = true;
	                }
	            }

	            if (!letraCorrecta) {
	                intentosIncorrectos++;
	            }
	            letraAdivinada = letraCorrecta;
	        }
	        return letraAdivinada;
	    }
	    
	    public void actualizarPalabraOculta(char letra, int posicion) {
	    	if (palabraAAdivinar.charAt(posicion) == letra) {
	            palabraOculta.setCharAt(posicion, letra);}
	    }

	    public String obtenerPalabraOculta() {
	        return palabraOculta.toString();
	    }

	    public boolean juegoTerminado() {
	        return palabraOculta.toString().equals(palabraAAdivinar) || juegoTerminadoPorMaximosIntentos();
	    }

	    public boolean juegoTerminadoPorMaximosIntentos() {
	        return intentosIncorrectos >= maxIntentosIncorrectos;
	    }
}
