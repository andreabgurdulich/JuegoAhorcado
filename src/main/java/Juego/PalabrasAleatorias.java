package Juego;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PalabrasAleatorias {

	 	private List<String> listaPalabras;
	    private Random random;

	    public PalabrasAleatorias() {
	        this.listaPalabras = new ArrayList<>();
	        this.random = new Random();
	    }

	    public void cargarPalabrasDesdeArchivo(String nombreArchivo) {
	        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(nombreArchivo);
	             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
	            String linea;
	            while ((linea = br.readLine()) != null) {
	                listaPalabras.add(linea.trim());
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public String obtenerPalabraAleatoria() {
	    	if (listaPalabras.isEmpty()) {
	            throw new IllegalStateException("La lista de palabras está vacía. Asegúrate de cargar las palabras desde un archivo.");
	        }

	        int indiceAleatorio = random.nextInt(listaPalabras.size());
	        return listaPalabras.get(indiceAleatorio);
	    }
}
