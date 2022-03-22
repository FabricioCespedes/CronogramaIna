/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.controller;

import com.ina.ProyectoJavaFabricioJose.domain.DiaFeriado;
import com.ina.ProyectoJavaFabricioJose.domain.Motivo;
import com.ina.ProyectoJavaFabricioJose.services.IMotivoService;
import java.util.Calendar;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ina.ProyectoJavaFabricioJose.services.IFeriadoService;

@Controller
public class FeriadosController {

    @Autowired
    private IFeriadoService feriadoService;

    @Autowired
    private IMotivoService motivoService;

    @GetMapping("/feriados")
    public String listaCliente(String txtTexto, Model model) {

        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        List<DiaFeriado> lista;
        lista = feriadoService.listar(String.valueOf(year));
        model.addAttribute("feriados", lista);
        model.addAttribute("year", year);
        model.addAttribute("diaFeriado", null);
        return "listaDiasFeriados";

    }

    @PostMapping("/filtrarFeriados")
    public String filtar(String txtTexto, Model model) {
        List<DiaFeriado> lista = feriadoService.listar(txtTexto);
        model.addAttribute("feriados", lista);
        return "listaDiasFeriados";
    }

    @GetMapping("/nuevoFeriado")
    public String nuevo(DiaFeriado feriado, Model model) {

        List<Motivo> lista = motivoService.listar();
        model.addAttribute("motivos", lista);

        return "feriado";
    }

    @PostMapping("/guardarFeriado")
    public String guardar(@Valid DiaFeriado feriado, RedirectAttributes redir) {
        String msg = "";

        if (feriadoService.motivoRepetido(feriado.getMotivo().getIdMotivo()).isEmpty()) {
            if (feriadoService.guardar(feriado) != 0) {
                msg = "Día feriado insertado";
            } else {
                msg = "No se pudo insertar el día feriado";
            }
        } else {
            msg = "No se pudo insertar, ya existe ese motivo para esa año";
        }

        redir.addFlashAttribute("msg", msg);

        return "redirect:/feriados";
    }

    @PostMapping("/editarFeriados/{idDia}")
    public String editar(DiaFeriado diaFeriado, Model model) {

        diaFeriado = feriadoService.obtenerFeriado(diaFeriado.getIdDia());
        if (diaFeriado != null) {
            model.addAttribute("diaFeriado", diaFeriado);
        }
        String msg = "No se logró cargar el feriado";

        return "redirect:/feriados";
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET,RequestMethod.PUT })
    public String eliminar(Integer idDia, Model model) {
        DiaFeriado diaFeriado = feriadoService.obtenerFeriado(idDia);
        model.addAttribute("diaFeriado", diaFeriado);
        feriadoService.eliminar(idDia);
        return "redirect:/feriados";
    }

}
