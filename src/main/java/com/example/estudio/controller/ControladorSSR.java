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
        model.addAttribute("nombre", nombre);
        return "saludo";
    }










    @GetMapping("/contacto")
    public String contacto(Model model) {
        model.addAttribute("contacto", new ModeloFormularioContacto("",""));
        return "contacto";
    }

    @PostMapping("/contacto")
    public String contacto(
            @Valid @ModelAttribute("contacto")
            ModeloFormularioContacto contacto,
            BindingResult result,
            Model model
    ) {
        if (!result.hasErrors()) {
            model.addAttribute("exito",
                    "Gracias " + contacto.email() + ", tu mensaje ha sido recibido.");
        }
        return "contacto";
    }







}





