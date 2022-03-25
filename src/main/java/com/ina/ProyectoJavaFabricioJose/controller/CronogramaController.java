/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.controller;

import com.ina.ProyectoJavaFabricioJose.domain.Cronograma;
import com.ina.ProyectoJavaFabricioJose.domain.Modulo;
import com.ina.ProyectoJavaFabricioJose.domain.PCronograma;
import com.ina.ProyectoJavaFabricioJose.domain.Programa;
import com.ina.ProyectoJavaFabricioJose.services.ICronogramaService;
import com.ina.ProyectoJavaFabricioJose.services.IModuloService;
import com.ina.ProyectoJavaFabricioJose.services.IProfesorService;
import com.ina.ProyectoJavaFabricioJose.services.IProgramaService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CronogramaController {

    @Autowired
    private IProfesorService profesorService;
    @Autowired
    private IProgramaService programaService;

    @Autowired
    private ICronogramaService cronogramaService;

    @Autowired
    private IModuloService moduloService;

    @GetMapping("/cronogramas")
    public String listar(Programa programa, Model model) {

        List<Programa> programasConCronograma = programaService.listar();

        model.addAttribute("programas", programasConCronograma);

        return "buscarCronogramas";
    }

    @GetMapping("/admCronograma")
    public String crear(Cronograma cronograma, Model model) {
        List<Programa> programasConCronograma = programaService.listar();

        model.addAttribute("programas", programasConCronograma);
        return "cronograma";
    }

    @GetMapping("/agregarModulo/{cronograma}")
    public String agregarModulo(Cronograma cronograma, Model model, RedirectAttributes redir) {
        String msg;
        if (cronograma.getFechaInicio() == null) {
            List<Programa> programasConCronograma = programaService.listar();
            msg = "Debe ingresar la fecha de inicio";
            redir.addFlashAttribute("programa", cronograma.getPrograma());

            redir.addFlashAttribute("msg", msg);
            model.addAttribute("programas", programasConCronograma);
            return "redirect:/admCronograma";
        }

        PCronograma pCronograma = new PCronograma();
        pCronograma.setIdPrograma(cronograma.getPrograma().getIdPrograma());
        model.addAttribute("pCronograma", pCronograma);
        model.addAttribute("modulos", moduloService.listar());
        model.addAttribute("profesores", profesorService.listar());
        return "insertarModulos";
    }

    @PostMapping("/seleccionarCronograma")
    public String generarCronograma(Cronograma cronograma, Model model, RedirectAttributes redir) {
        String msg = "";
        if (cronograma.getPrograma().getCodigo().isEmpty()) {
            msg = "Debe seleccionar un programa";
            redir.addFlashAttribute("msg", msg);
            return "redirect:/admCronograma";
        }
        List<Cronograma> cronogramas = cronogramaService.listarCronogramas(cronograma.getPrograma().getIdPrograma());
        model.addAttribute("cronogramas", cronogramas);
        model.addAttribute("cronograma", cronograma);
        redir.addFlashAttribute("cronograma", cronograma);
        redir.addFlashAttribute("programa", cronograma.getPrograma());
        redir.addFlashAttribute("cronogramas", cronogramas);
        redir.addFlashAttribute("msg", msg);
        return "redirect:/admCronograma";
    }

    @PostMapping("/guardarModulo")
    public String guardarModulo(@Valid PCronograma pCronograma, Errors er, Model model, RedirectAttributes redir) {
        String msg = "";
        if (pCronograma.getIdModulo() == null || pCronograma.getIdPrograma() == null || pCronograma.getHoraInicio().isEmpty() || pCronograma.getHoraFin().isEmpty()) {
            msg = "Por favor, incluya todos los datos";
            redir.addFlashAttribute("msg", msg);

            pCronograma.setIdPrograma(pCronograma.getIdPrograma());
            model.addAttribute("pCronograma", pCronograma);
            model.addAttribute("modulos", moduloService.listar());
            model.addAttribute("profesores", profesorService.listar());
            return "redirect:/agregarModulo/" + pCronograma.getIdPrograma();
        }
        if (!pCronograma.isLunes() && !pCronograma.isMartes() && !pCronograma.isMiercoles() && !pCronograma.isJueves() && !pCronograma.isViernes() && !pCronograma.isSabado()) {
            msg = "Por favor, seleccione como mínimo un día en el cuál se brindará el curso";
            redir.addFlashAttribute("msg", msg);

            pCronograma.setIdPrograma(pCronograma.getIdPrograma());
            model.addAttribute("pCronograma", pCronograma);
            model.addAttribute("modulos", moduloService.listar());
            model.addAttribute("profesores", profesorService.listar());
            return "redirect:/agregarModulo/" + pCronograma.getIdPrograma();
        }

        if (pCronograma == null) {
            msg = "Ha ocurrido un error inesperado";
            redir.addFlashAttribute("msg", msg);

            pCronograma.setIdPrograma(pCronograma.getIdPrograma());
            model.addAttribute("pCronograma", pCronograma);
            model.addAttribute("modulos", moduloService.listar());
            model.addAttribute("profesores", profesorService.listar());
            return "redirect:/agregarModulo/" + pCronograma.getIdPrograma();
        }
        if (cronogramaService.ingresarDias(pCronograma.isLunes(), pCronograma.isMartes(), pCronograma.isMiercoles(), pCronograma.isJueves(), pCronograma.isViernes(), pCronograma.isSabado(), pCronograma.getIdModulo(), pCronograma.getIdPrograma(), 0) == 0) {
            Programa programa = programaService.obtenerPrograma(pCronograma.getIdPrograma());
            String fechaInicioR = cronogramaService.guardar(pCronograma.getIdModulo(),
                    pCronograma.getIdPrograma(),
                    pCronograma.getIdProfesor(),
                    pCronograma.getHorasDia(),
                    pCronograma.getHoraInicio(),
                    pCronograma.getHoraFin(),
                    pCronograma.getEstado(),
                    programa.getCentro().getIdCentro(),
                    pCronograma.getFechaInicio()
            );

        } else {
            msg = "Error en la conexión a la base de datos";
            redir.addFlashAttribute("msg", msg);

            pCronograma.setIdPrograma(pCronograma.getIdPrograma());
            model.addAttribute("pCronograma", pCronograma);
            model.addAttribute("modulos", moduloService.listar());
            model.addAttribute("profesores", profesorService.listar());
            return "redirect:/agregarModulo/" + pCronograma.getIdPrograma();
        }

        return "redirect:/admCronograma";
    }
}
