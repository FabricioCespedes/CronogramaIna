/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.controller;

import com.ina.ProyectoJavaFabricioJose.domain.Motivo;
import com.ina.ProyectoJavaFabricioJose.services.MotivoService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MotivosController {

    @Autowired
    private MotivoService motivoService;

    @GetMapping("/motivos")
    public String listaCliente(Model model, @ModelAttribute("msg") String msg) {
        List<Motivo> lista = motivoService.listar();
        model.addAttribute("motivos", lista);
        return "listaMotivos";
    }

    @PostMapping("/filtrarMotivos")
    public String filtar(String txtTexto, Model model) {
        List<Motivo> lista = motivoService.listar(txtTexto);
        model.addAttribute("motivos", lista);
        return "listaMotivos";
    }

    @GetMapping("/nuevoMotivo")
    public String nuevo(Motivo motivo) {

        return "motivo";
    }

    @PostMapping("/guardarMotivo")
    public String guardar(@Valid Motivo motivo, RedirectAttributes redir) {
        String msg = "";

        if (motivoService.guardar(motivo) != 0) {
            msg = "Motivo insertado";
        } else {
            msg = "No se pudo insertar el motivo";
        }

        redir.addFlashAttribute("msg", msg);

        return "redirect:/motivos";
    }

    @GetMapping("/editarMotivo/{idMotivo}")
    public String editar(Motivo motivo, Model model, RedirectAttributes redir) {

        motivo = motivoService.obtenerMotivo(motivo.getIdMotivo());
        if (motivo != null) {
            model.addAttribute("motivo", motivo);
            return "motivo";
        }
        String msg = "No se logró cagar el motivo";

        redir.addFlashAttribute("msg", msg);

        return "redirect:/motivos";
    }

    @GetMapping("/confirmarEliminacion/{idMotivo}")
    public String eliminar(Motivo motivo, Model model, RedirectAttributes redir) {
        
        motivo = motivoService.obtenerMotivo(motivo.getIdMotivo());
        if (motivo != null) {
            model.addAttribute("motivo", motivo);
            return "eliminarMotivo";
        }
        String msg = "No se logró cagar el motivo";

        redir.addFlashAttribute("msg", msg);

        return "redirect:/motivos";
    }
    
    @PostMapping("/eliminarMotivo")
    public String eliminar(@Valid Motivo motivo, RedirectAttributes redir) {
        String msg = "";

        if (motivoService.eliminar(motivo) != 0) {
            msg = "Motivo eliminado";
        } else {
            msg = "No se pudo eliminar el motivo";
        }

        redir.addFlashAttribute("msg", msg);

        return "redirect:/motivos";
    }
}
