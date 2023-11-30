package ahorcado.juegoahorcado;

public class Ahorcado {
    private String palabraAAdivinar;
    private StringBuilder palabraOculta;
    private int intentosIncorrectos;
    private int maxIntentosIncorrectos;

    public Ahorcado() {
        this(6); //Número de intentos permitidos
    }

    public Ahorcado(int maxIntentos) {
        this.maxIntentosIncorrectos = maxIntentos;
        this.intentosIncorrectos = 0;
    }

    public void iniciarJuego(String palabraAAdivinar) {
        this.palabraAAdivinar = palabraAAdivinar;
        this.palabraOculta = new StringBuilder();
        for (int i = 0; i < palabraAAdivinar.length(); i++) {
            this.palabraOculta.append('_');
        }
    }

    public String obtenerPalabraAAdivinar() {
        return palabraAAdivinar;
    }

    public boolean adivinarLetra(char letra) {
        boolean letraAdivinada = false;
        if (!juegoTerminado()) {
            boolean letraCorrecta = false;
            for (int i = 0; i < palabraAAdivinar.length(); i++) {
                if (palabraAAdivinar.charAt(i) == letra) {
                    palabraOculta.setCharAt(i, letra);
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