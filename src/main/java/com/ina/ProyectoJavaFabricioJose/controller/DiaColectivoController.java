/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.controller;

import com.ina.ProyectoJavaFabricioJose.domain.DiasAusentesColectivos;
import java.util.Calendar;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiaColectivoController {
    
    
    
    @GetMapping("/colectivos")
    public String listaColectivos(String txtTexto, Model model) {

        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        List<DiasAusentesColectivos> lista;
        lista = feriadoService.listar(String.valueOf(year));
        model.addAttribute("feriados", lista);
        model.addAttribute("year", year);
        model.addAttribute("diaFeriado", null);
        return "listaDiasFeriados";

    }
    
}
