/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcado.juegoahorcado;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Usuario
 */
@Controller
public class AhorcadoControlador 
    {
        @GetMapping("/miPagina")
        public String miPagina(Model model) 
        {
        
            //Instancio a las palabras aleatorias
            PalabrasAleatorias palabras = new PalabrasAleatorias();
            Ahorcado juego = new Ahorcado(); //Iniciamos el ahorcado con valores predeterminados
            
            //Cargamos Archivo de Palabras
            String rutaArchivo = "C:\\Users\\Usuario\\OneDrive\\Desktop\\ListaPalabras.txt";
            palabras.cargarPalabrasDesdeArchivo(rutaArchivo);
            
            // Devuelve el nombre de la vista Thymeleaf
            return "miPagina";
        }
    }