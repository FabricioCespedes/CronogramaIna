/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.controller;

import com.ina.ProyectoJavaFabricioJose.domain.Profesor;
import com.ina.ProyectoJavaFabricioJose.services.ProfesorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfesorController {
    
    @Autowired
    private ProfesorService profesorService;
    
    @GetMapping("/profesores")
    public String listaCliente(Model model, @ModelAttribute("msg") String msg) {
        List<Profesor> lista = profesorService.listar();
        model.addAttribute("profesores", lista);
        return "listaProfesores";
    }
    
    @PostMapping("/filtrarProfesores")
    public String filtar(String txtTexto, Model model) {
        List<Profesor> lista = profesorService.listar(txtTexto);
        model.addAttribute("profesores", lista);
        return "listaProfesores";
    }
}
