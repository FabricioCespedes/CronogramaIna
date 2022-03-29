/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.controller;

import com.ina.ProyectoJavaFabricioJose.domain.Centro;
import com.ina.ProyectoJavaFabricioJose.domain.Distrito;
import com.ina.ProyectoJavaFabricioJose.services.CentroService;
import com.ina.ProyectoJavaFabricioJose.services.DistritoService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CentroController {

    @Autowired
    private CentroService centroService;

    @Autowired
    private DistritoService distritoService;

    @GetMapping("/centros")
    public String listaCliente(Model model) {

        List<Centro> lista = centroService.listar();
        model.addAttribute("centros", lista);
        return "listaCentros";
    }

    @PostMapping("/filtrarCentros")
    public String filtar(String txtTexto, Model model) {
        List<Centro> lista = centroService.listar(txtTexto);
        model.addAttribute("centros", lista);
        return "listaMotivos";
    }

    @GetMapping("/nuevoCentro")
    public String nuevo(Centro centro, Model model) {

        List<Distrito> lista = distritoService.listar();
        model.addAttribute("distritos", lista);

        return "centro";
    }

    @PostMapping("/guardarCentro")
    public String guardar(@Valid Centro centro, RedirectAttributes redir) {
        String msg = "";
        
        if (centroService.verificarNombre(centro.getNombre()).size() < 1) {
            if (centroService.verificarRuta(centro.getUbicacion()).size() < 1) {
                if (centroService.guardar(centro) == 1) {
                    msg = "Centro insertado";
                }else{
                    msg = "El centro no se pudo insertar";
                }
            }else{
                msg = "El centro no se pudo insertar porque ya hay un centro con esa ubicación";
            }
        }else{
            msg = "El centro no se pudo insertar porque ya hay un centro con esa ubicación";
        }
        redir.addFlashAttribute("msg", msg);
        return "redirect:/centros";
    }
    
    @GetMapping("/editarCentro/{idCentro}")
    public String editar(Centro centro, Model model, RedirectAttributes redir) {

        centro = centroService.obtenerCentro(centro.getIdCentro());
        if (centro != null) {
             List<Distrito> lista = distritoService.listar();
            model.addAttribute("distritos", lista);
            model.addAttribute("centro", centro);
            return "centro";
        }
        String msg = "No se logró cagar el centro";

        redir.addFlashAttribute("msg", msg);

        return "redirect:/centros";
    }

    @RequestMapping(value = "/deleteCen", method = {RequestMethod.DELETE, RequestMethod.GET,RequestMethod.PUT })
    public String eliminar(Integer idCentro, Model model) {
        Centro centro = centroService.obtenerCentro(idCentro);
        model.addAttribute("centro", centro);
        centroService.eliminar(idCentro);
        return "redirect:/centros";
    }
}
