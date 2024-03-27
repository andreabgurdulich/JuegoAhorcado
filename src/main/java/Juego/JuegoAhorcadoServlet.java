package Juego;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/JuegoAhorcadoServlet")
public class JuegoAhorcadoServlet extends HttpServlet {
    public static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el objeto Ahorcado de la sesión
        HttpSession session = request.getSession();
        Ahorcado ahorcado = (Ahorcado) session.getAttribute("ahorcado");

        // Verificar si se ha enviado el parámetro "reset"
        String resetParam = request.getParameter("reset");
        if (resetParam != null && resetParam.equals("true")) {
            // Reiniciar el juego creando un nuevo objeto Ahorcado y guardándolo en la sesión
            ahorcado = new Ahorcado();
            String palabraAAdivinar = ahorcado.obtenerPalabraAAdivinar();
            ahorcado.setPalabraAAdivinar(palabraAAdivinar);
            ahorcado.setPalabraOculta(ocultarPalabra(palabraAAdivinar));
            ahorcado.iniciarJuego(palabraAAdivinar); // Inicializar el juego
            session.setAttribute("ahorcado", ahorcado);

            // Limpiar la lista de letras seleccionadas
            session.removeAttribute("letrasSeleccionadas");

            // Redirigir de vuelta al servlet para evitar problemas con el refresh del navegador
            response.sendRedirect(request.getContextPath() + "/JuegoAhorcadoServlet");    
        } else {
            // Verificar si el objeto Ahorcado es nulo y inicializarlo si es necesario
            if (ahorcado == null) {
                ahorcado = new Ahorcado();
                String palabraAAdivinar = ahorcado.obtenerPalabraAAdivinar();
                ahorcado.setPalabraAAdivinar(palabraAAdivinar);
                ahorcado.setPalabraOculta(ocultarPalabra(palabraAAdivinar));
                ahorcado.iniciarJuego(palabraAAdivinar); // Inicializar el juego
                session.setAttribute("ahorcado", ahorcado);
            }

            // Verificar si el juego ha terminado
            boolean juegoTerminado = ahorcado.juegoTerminado();

            // Establecer un atributo en la sesión para indicar si el juego ha terminado
            session.setAttribute("juegoTerminado", juegoTerminado);

            // Recuperar la lista de letras seleccionadas de la sesión
            @SuppressWarnings("unchecked")
			List<Character> letrasSeleccionadas = (List<Character>) session.getAttribute("letrasSeleccionadas");

            // Redirigir a la página de resultado si el juego ha terminado
            if (juegoTerminado) {
                request.getRequestDispatcher("JuegoAhorcado.jsp").forward(request, response);
                return; // Detener la ejecución para evitar que se ejecute la lógica adicional abajo
            }

            // Si el juego no ha terminado, continuar con la lógica existente
            // Establecer los atributos necesarios para el JSP
            request.setAttribute("palabraResaltada", ahorcado.getPalabraOculta()); // Palabra oculta al inicio
            request.setAttribute("maxIntentos", ahorcado.getMaxIntentosIncorrectos());
            request.setAttribute("letrasSeleccionadas", letrasSeleccionadas); // Pasar las letras seleccionadas al JSP
            request.getRequestDispatcher("JuegoAhorcado.jsp").forward(request, response);
        }
    }


    public StringBuilder ocultarPalabra(String palabra) {
        StringBuilder palabraOculta = new StringBuilder();
        for (int i = 0; i < palabra.length(); i++) {
            palabraOculta.append(palabra.charAt(i)).append(" ");
        }
        return palabraOculta;
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la letra ingresada por el usuario
        String letra = request.getParameter("letra");

        // Obtener el objeto Ahorcado de la sesión
        HttpSession session = request.getSession();
        Ahorcado ahorcado = (Ahorcado) session.getAttribute("ahorcado");

        // Procesar la letra ingresada y actualizar el estado del juego
        if (letra != null && letra.length() == 1) {
            char letraChar = letra.charAt(0);
            ahorcado.adivinarLetra(letraChar);

            // Agregar la letra a la lista de letras seleccionadas en la sesión
            @SuppressWarnings("unchecked")
			List<Character> letrasSeleccionadas = (List<Character>) session.getAttribute("letrasSeleccionadas");
            if (letrasSeleccionadas == null) {
                letrasSeleccionadas = new ArrayList<>();
            }
            letrasSeleccionadas.add(letraChar);
            session.setAttribute("letrasSeleccionadas", letrasSeleccionadas);
        }

        // Verificar si el juego ha terminado
        if (ahorcado.juegoTerminado()) {
            // Establecer un atributo en la sesión para indicar que el juego ha terminado
            session.setAttribute("juegoTerminado", true);

            // Si es true puede ser porque ganó o perdió, validamos si perdió
            if (ahorcado.juegoTerminadoPorMaximosIntentos()) {
                // Si el juego ha terminado por derrota, establecer el atributo juegoTerminadoPorVictoria en false
                session.setAttribute("juegoTerminadoPorVictoria", false);
            } else {
                // Si el juego ha terminado por victoria, establecer el atributo juegoTerminadoPorVictoria en true
                session.setAttribute("juegoTerminadoPorVictoria", true);
            }
        }

        // Actualizar el objeto Ahorcado en la sesión
        session.setAttribute("ahorcado", ahorcado);

        // Redirigir de vuelta al servlet para que se ejecute el doGet y actualice la vista
        response.sendRedirect(request.getContextPath() + "/JuegoAhorcadoServlet");    
    }


}
