/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.controller;

import com.ina.ProyectoJavaFabricioJose.domain.DiaAusente;
import com.ina.ProyectoJavaFabricioJose.domain.Motivo;
import com.ina.ProyectoJavaFabricioJose.domain.Profesor;
import com.ina.ProyectoJavaFabricioJose.services.DiaAusenteService;
import com.ina.ProyectoJavaFabricioJose.services.MotivoService;
import com.ina.ProyectoJavaFabricioJose.services.ProfesorService;
import java.util.Calendar;
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
public class DiaAusenteController {

    @Autowired
    private DiaAusenteService diaAusenteService;

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private MotivoService motivoService;

    @GetMapping("/diasAusentes")
    public String listaCliente(Profesor profesor, String txtTexto, Model model) {

        Calendar cal = Calendar.getInstance();
        List<Profesor> listaPro;
        List<DiaAusente> lista;
        lista = diaAusenteService.listar();
        listaPro = profesorService.listar();
        model.addAttribute("listaPro", listaPro);
        model.addAttribute("diasAusentes", lista);
        return "listaDiasAusentes";

    }

    @PostMapping("/filtrarAusentes")
    public String filtar(Profesor profesor, Model model, RedirectAttributes redir) {

        List<DiaAusente> lista = diaAusenteService.listar(profesor.getIdProfesor());
        List<Profesor> listaPro = profesorService.listar();
        Profesor profesor2 = profesorService.obtenerProfesor(profesor.getIdProfesor());
        model.addAttribute("profesor", profesor2);

        model.addAttribute("listaPro", listaPro);
        model.addAttribute("diasAusentes", lista);
        redir.addFlashAttribute("listaPro", listaPro);

        redir.addFlashAttribute("diasAusentes", lista);

        return "listaDiasAusentes";
    }

    @GetMapping("/nuevoDiaAusente")
    public String nuevo(DiaAusente diaAusente, Model model) {

        List<Profesor> listaPr = profesorService.listar();

        List<Motivo> listaMot = motivoService.listar();

        model.addAttribute("profesores", listaPr);
        model.addAttribute("motivos", listaMot);

        return "diaAusente";
    }

    @PostMapping("/guardarAusencia")
    public String guardar(@Valid DiaAusente diaAusente, RedirectAttributes redir) {
        String msg = "";
        
        if (diaAusente.getFechaInicio().before(diaAusente.getFechaFin())) {
            if (diaAusenteService.guardar(diaAusente) != 0) {
                msg = "Día de ausencia insertado";
            } else {
                msg = "No se pudo insertar la fecha de ausencia";
            }
        }else {
            msg = "No se pudo insertar la fecha de ausencia porque la fecha de fin es primero que la fecha inicial";
        }

        redir.addFlashAttribute("msg", msg);

        return "redirect:/diasAusentes";
    }

    @GetMapping("/editarAusencia/{idDiaAusente}")
    public String editar(DiaAusente diaAusente, Model model) {

        diaAusente = diaAusenteService.obtenerAusencia(diaAusente.getIdDiaAusente());
        if (diaAusente != null) {
            List<Profesor> listaPr = profesorService.listar();
            List<Motivo> listaMot = motivoService.listar();
            model.addAttribute("diaAusente", diaAusente);
            model.addAttribute("profesores", listaPr);
            model.addAttribute("motivos", listaMot);
            return "diaAusente";
        }
        String msg = "No se logró cargar la fecha de ausencia";

        return "redirect:/diasAusentes";
    }

    @RequestMapping(value = "/deleteDiaA", method = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT})
    public String eliminar(Integer idDiaAusente, Model model, RedirectAttributes redir) {
        int result = 0;
        String msg = "No se pudo eliminar la fecha de ausencia";
        DiaAusente diaAusente = diaAusenteService.obtenerAusencia(idDiaAusente);
        model.addAttribute("diaAusente", diaAusente);
        result = diaAusenteService.eliminar(diaAusente.getIdDiaAusente());

        if (result == 1) {
            msg = "Motivo eliminado con exito";
        }

        redir.addFlashAttribute("msg", msg);

        return "redirect:/diasAusentes";
    }

}
