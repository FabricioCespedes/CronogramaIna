/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.controller;

import com.ina.ProyectoJavaFabricioJose.domain.Centro;
import com.ina.ProyectoJavaFabricioJose.domain.Profesor;
import com.ina.ProyectoJavaFabricioJose.services.CentroService;
import com.ina.ProyectoJavaFabricioJose.services.ProfesorService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private CentroService centroService;

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

    @GetMapping("/nuevoProfesor")
    public String nuevo(Profesor profesor, Model model) {

        List<Centro> lista = centroService.listar();
        model.addAttribute("centros", lista);

        return "profesor";
    }

    @PostMapping("/guardarProfesor")
    public String guardar(@Valid Profesor profesor, RedirectAttributes redir) {
        String msg = "";

        if (profesorService.guardar(profesor) != 0) {

            msg = "Profesor insertado";

        } else {
            msg = "No se pudo insertar el profesor";
        }

        redir.addFlashAttribute("msg", msg);

        return "redirect:/profesores";
    }

    @GetMapping("/editarProfesor/{idProfesor}")
    public String editar(Profesor profesor, Model model, RedirectAttributes redir) {

        profesor = profesorService.obtenerProfesor(profesor.getIdProfesor());
        if (profesor != null) {
            List<Centro> lista = centroService.listar();
            model.addAttribute("centros", lista);
            model.addAttribute("profesor", profesor);
            return "profesor";
        }
        String msg = "No se logró cargar el profesor";

        redir.addFlashAttribute("msg", msg);

        return "redirect:/profesores";
    }

    @RequestMapping(value = "/deletePro", method = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT})
    public String eliminar(Long idProfesor, Model model, RedirectAttributes redir) {
        String msg = "Usuario eliminado";
        Profesor profesor = profesorService.obtenerProfesor(idProfesor);
        model.addAttribute("profesor", profesor);
        if (profesorService.eliminar(profesor) != 1) {
            msg = "No se pudo eliminar el usuario";
        }
        
        redir.addFlashAttribute("msg", msg);
        
        return "redirect:/profesores";
    }
}
