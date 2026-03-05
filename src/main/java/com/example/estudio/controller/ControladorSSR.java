// Es un controlador que devuelve páginas HTML generadas en el servidor. Lo identificas porque usa @Controller

// CÓDIGO PARA EL MANEJO DE ENDPOINTS EN COSAS HTML

package com.example.estudio.controller;
import com.example.estudio.model.ModeloFormularioContacto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControladorSSR {


    //Model model siempre igual
    //required=false y defaultValue son opcionales
    //en model.addAttribute el nombre sin comillas es el nombre de la variable

    //name="nombre" -> es lo que pides en la URL -> /saludo?nombre=Diego
    //nombre -> es la variable que guarda el valor -> Diego
    //"nombre" en addAttribute -> etiqueta que busca en saludo.html para asignarle el valor nombre.

    @GetMapping("/saludo")
    public String saludo(@RequestParam(name="nombre", required=false, defaultValue="Mundo") String nombre, Model model)
    {
        if (true) throw new RuntimeException("Error provocado");
        // if(true) siempre va a ser verdadero, así que siempre se ejecuta
        // Crea un error a proposito para ver que mi página error funciona
        model.addAttribute("nombre", nombre);
        return "saludo";
    }







// ---------  PARA EL FORMULARIO DE CONTACTO  ---------



    @GetMapping("/contacto")
    public String muestraFormulario(Model model) {
        model.addAttribute("contacto", new ModeloFormularioContacto("",""));
        return "contacto";
        // Lo que va a hacer este bloque de código es mostrar el formulario VACIO al usuario
        // Vacio por esto -> new ModeloFormularioContacto("",""));
        // "contacto" hace que se busque contacto.html
    }



    @PostMapping("/contacto")
    public String procesaFormulario(
            @Valid @ModelAttribute("contacto")
            // @Valid le dice a Spring que valide este objeto antes de procesarlo
            // Esto hará que se valide el formulario de contacto antes de mandarlo
            // @ModelAttribute es como el @RequestBody pero para formularios HTML, este cogerá los datos
            //      que se han introducido en "contacto" y lo convertirá en un ModeloFormularioContacto
            ModeloFormularioContacto contacto,
            // Crea un objeto de ModeloFormularioContacto llamado contacto
            BindingResult result,
            // Aquí se guardan los errores de validación
            Model model
    ) {
        if (!result.hasErrors()) {
            model.addAttribute("exito", "Gracias " + contacto.email() + ", tu mensaje ha sido recibido.");
        }
        // Si no tiene errores, mensaje de agradeciiento y "exito"
        // Es un nombre que le hemos puesto a una variable que tiene que mirar luego el html
        return "contacto";
        // "contacto" hace que se busque contacto.html
    }







}





