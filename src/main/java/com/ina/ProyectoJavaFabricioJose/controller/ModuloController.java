/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.controller;

import com.ina.ProyectoJavaFabricioJose.domain.Modulo;
import com.ina.ProyectoJavaFabricioJose.services.ModuloService;
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
public class ModuloController {

    @Autowired
    private ModuloService moduloService;

    @GetMapping("/modulos")
    public String listaCliente(Model model, @ModelAttribute("msg") String msg) {
        List<Modulo> lista = moduloService.listar();
        model.addAttribute("modulos", lista);
        return "listaModulos";
    }

    @PostMapping("/filtrarModulos")
    public String filtar(String txtTexto, Model model) {
        List<Modulo> lista = moduloService.listar(txtTexto);
        model.addAttribute("modulos", lista);
        return "listaModulos";
    }

    @GetMapping("/nuevoModulo")
    public String nuevo(Modulo modulo, Model model) {

        List<Modulo> lista = moduloService.listar();
        model.addAttribute("modulos", lista);

        return "modulo";
    }

    @PostMapping("/guardarModulo")
    public String guardar(@Valid Modulo modulo, RedirectAttributes redir) {
        String msg = "";

        moduloService.guardar(modulo);

        msg = "modulo insertado insertado";

        redir.addFlashAttribute("msg", msg);

        return "redirect:/modulos";
    }
    
    @GetMapping("/editarModulo/{idModulo}")
    public String editar(Modulo modulo, Model model, RedirectAttributes redir) {

        modulo = moduloService.obtenerModulo(modulo.getIdModulo());
        if (modulo != null) {
            List<Modulo> lista = moduloService.listar();
            model.addAttribute("modulos", lista);
            model.addAttribute("modulo", modulo);
            return "modulo";
        }
        String msg = "No se logró cargar el modulo";

        redir.addFlashAttribute("msg", msg);

        return "redirect:/modulos";
    }

    @RequestMapping(value = "/deleteMod", method = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT})
    public String eliminar(Integer idModulo, Model model) {
        Modulo modulo = moduloService.obtenerModulo(idModulo);
        model.addAttribute("modulo", modulo);
        moduloService.eliminar(modulo);
        return "redirect:/modulos";
    }
    

}
