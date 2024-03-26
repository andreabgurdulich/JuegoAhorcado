package Juego;

import java.io.IOException;
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

            // Redirigir a la página de resultado si el juego ha terminado
            if (juegoTerminado) {
                request.getRequestDispatcher("JuegoAhorcado.jsp").forward(request, response);
                return; // Detener la ejecución para evitar que se ejecute la lógica adicional abajo
            }

            // Si el juego no ha terminado, continuar con la lógica existente
            // Establecer los atributos necesarios para el JSP
            request.setAttribute("palabraResaltada", ahorcado.getPalabraOculta()); // Palabra oculta al inicio
            request.setAttribute("maxIntentos", ahorcado.getMaxIntentosIncorrectos());
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
            // Desactivar el botón de la letra seleccionada
            request.setAttribute(letra, false);
        }
        
        // Verificar si el juego ha terminado
        if (ahorcado.juegoTerminado()) {
            // Establecer un atributo en la sesión para indicar que el juego ha terminado
            session.setAttribute("juegoTerminado", true);
        }

        // Actualizar el objeto Ahorcado en la sesión
        session.setAttribute("ahorcado", ahorcado);

        // Redirigir de vuelta al servlet para que se ejecute el doGet y actualice la vista
        response.sendRedirect(request.getContextPath() + "/JuegoAhorcadoServlet");    
    }

}
