package com.example.estudio.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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



}





