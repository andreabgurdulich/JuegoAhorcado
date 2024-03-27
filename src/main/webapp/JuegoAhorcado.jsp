<%@ page import="java.util.List" %>
<%@ page import="Juego.Ahorcado" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ahorcado</title>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="container">
        <h1 class="text-center">Ahorcado</h1>

        <% if (session.getAttribute("juegoTerminado") != null && (boolean) session.getAttribute("juegoTerminado")) { %>
            <div class="text-center">
                <% if ((boolean) session.getAttribute("juegoTerminadoPorVictoria")) { %>
                    <h2 class="text-success">¡Felicidades! Has ganado el juego.</h2>
                <% } else { %>
                    <h2 class="text-danger">¡Lo siento! Has perdido el juego.</h2>
                <% } %>
                <p>
				La palabra era:
				<%=((Ahorcado) session.getAttribute("ahorcado")).getPalabraAAdivinar()%></p>
                <button class="btn btn-info" onclick="reset()">Jugar de nuevo</button>
            </div>
        <% } else { %>
            <div class="float-right">
                Errores: <span id='errores'>${ahorcado.intentosIncorrectos}</span> de
                <span id='maxIntentos'>${ahorcado.maxIntentosIncorrectos}</span>
            </div>
            <div class="text-center">
                <img alt="ahorcado" src="assets/images/0.jpg" id="imgAhorcado">
                <p>Adivina la palabra:</p>
                <p id="palabraResaltada">
                    <%
                    StringBuilder palabraOculta = (StringBuilder) request.getAttribute("palabraResaltada");
                    for (int i = 0; i < palabraOculta.length(); i++) {
                        out.print("<span style=\"margin-right: 5px;\">" + palabraOculta.charAt(i) + "</span>");
                    }
                    %>
                </p>
                <form action="JuegoAhorcadoServlet" method="post" id="formLetra">
                    <% 
                    List<Character> letrasSeleccionadas = (List<Character>) request.getAttribute("letrasSeleccionadas");
                    for (char letra = 'a'; letra <= 'z'; letra++) {
                        String letraStr = Character.toString(letra);
                        boolean letraSeleccionada = letrasSeleccionadas != null && letrasSeleccionadas.contains(letra);
                    %>
                    <button type="submit" name="letra" value="<%=letra%>"
                        class="btn btn-lg btn-primary m-2"
                        <%= letraSeleccionada ? "disabled" : "" %>>
                        <%= letra %>
                    </button>
                    <% } %>
                </form>
                <button class="btn btn-info" onclick="reset()">Reiniciar</button>
            </div>
        <% } %>
    </div>

    <script>
        function reset() {
            window.location.href = "<%= request.getContextPath() %>/JuegoAhorcadoServlet?reset=true";
        }
    </script>
    
</body>
</html>
