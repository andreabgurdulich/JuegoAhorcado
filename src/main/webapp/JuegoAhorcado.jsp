<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Ahorcado</title>
<link href="assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div class="container">
		<h1 class="text-center">Ahorcado</h1>
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
				for (char letra = 'a'; letra <= 'z'; letra++) {
					String letraStr = Character.toString(letra);
				%>
				<button type="submit" name="letra" value="<%=letra%>"
					class="btn btn-lg btn-primary m-2"
					<%=request.getAttribute(letraStr) != null && !(Boolean) request.getAttribute(letraStr) ? "disabled" : ""%>>
					<%=letra%>
				</button>
				<%
				}
				%>
			</form>
			<button class="btn btn-info" onclick="reset()">Reiniciar</button>
		</div>
</body>
</html>
