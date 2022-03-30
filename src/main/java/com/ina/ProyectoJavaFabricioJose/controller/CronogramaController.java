/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.controller;

import com.ina.ProyectoJavaFabricioJose.domain.Cronograma;
import com.ina.ProyectoJavaFabricioJose.domain.Modulo;
import com.ina.ProyectoJavaFabricioJose.domain.PCronograma;
import com.ina.ProyectoJavaFabricioJose.domain.Programa;
import com.ina.ProyectoJavaFabricioJose.domain.ProgramaFecha;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String crear(ProgramaFecha programaFecha, Model model) {
        List<Programa> programasConCronograma = programaService.listar();

        model.addAttribute("programas", programasConCronograma);
        return "cronograma";
    }

    @GetMapping("/agregarModulo")
    public String agregarModulo(@RequestParam Integer idPro, @RequestParam String fecha, Model model, RedirectAttributes redir) {
        String msg;
        if (fecha.isEmpty()) {
            List<Programa> programasConCronograma = programaService.listar();
            msg = "Debe ingresar la fecha de inicio";
//            redir.addFlashAttribute("programa", programaFecha.getPrograma());

            redir.addFlashAttribute("msg", msg);
            model.addAttribute("programas", programasConCronograma);
            return "redirect:/admCronograma";
        }

        PCronograma pCronograma = new PCronograma();
        pCronograma.setIdPrograma(idPro);
        pCronograma.setFechaInicio(fecha);
        model.addAttribute("pCronograma", pCronograma);
        model.addAttribute("modulos", moduloService.listar(idPro));
        model.addAttribute("profesores", profesorService.listar());
        return "insertarModulos";
    }

    @PostMapping("/seleccionarCronograma")
    public String generarCronograma(ProgramaFecha programaFecha, Model model, RedirectAttributes redir) {
        String msg = "";
        if (programaFecha.getPrograma().getCodigo().isEmpty()) {
            msg = "Debe seleccionar un programa";
            redir.addFlashAttribute("msg", msg);
            return "redirect:/admCronograma";
        }
        List<Cronograma> cronogramas = cronogramaService.listarCronogramas(programaFecha.getPrograma().getIdPrograma());
        Programa programa = programaService.obtenerPrograma(programaFecha.getPrograma().getIdPrograma());

        List<String> lista = (List<String>) cronogramaService.obtenerFechaInicio(programaFecha.getIdPro());
        if (!lista.isEmpty()) {
            programaFecha.setFechaInicio(lista.get(0));
        }
        programaFecha.setPrograma(programa);
        model.addAttribute("cronogramas", cronogramas);
        model.addAttribute("programaFecha", programaFecha);
        redir.addFlashAttribute("cronogramas", cronogramas);
        redir.addFlashAttribute("programaFecha", programaFecha);
        redir.addFlashAttribute("programa", programa);
        redir.addFlashAttribute("msg", msg);
        return "redirect:/admCronograma";
    }

    @PostMapping("/guardarModulo")
    public String guardarModulo(@Valid PCronograma pCronograma, Errors er, Model model, RedirectAttributes redir) {
        String msg = "";
        String fechaInicioR = "";
        if (pCronograma.getIdModulo() == null || pCronograma.getIdPrograma() == null || pCronograma.getHoraInicio().isEmpty() || pCronograma.getHoraFin().isEmpty() || pCronograma.getHorasDia() == null) {
            msg = "Por favor, incluya todos los datos";
            redir.addFlashAttribute("msg", msg);

            pCronograma.setIdPrograma(pCronograma.getIdPrograma());
            redir.addFlashAttribute("pCronograma", pCronograma);
            redir.addFlashAttribute("modulos", moduloService.listar());
            redir.addFlashAttribute("profesores", profesorService.listar());
            return "redirect:/agregarModulo?idPro=" + pCronograma.getIdPrograma().toString() + "&fecha=" + pCronograma.getFechaInicio();
        }
        if (!pCronograma.isLunes() && !pCronograma.isMartes() && !pCronograma.isMiercoles() && !pCronograma.isJueves() && !pCronograma.isViernes() && !pCronograma.isSabado()) {
            msg = "Por favor, seleccione como mínimo un día en el cuál se brindará el curso";
            redir.addFlashAttribute("msg", msg);

            pCronograma.setIdPrograma(pCronograma.getIdPrograma());
            model.addAttribute("pCronograma", pCronograma);
            model.addAttribute("modulos", moduloService.listar());
            model.addAttribute("profesores", profesorService.listar());
            return "redirect:/agregarModulo?idPro=" + pCronograma.getIdPrograma().toString() + "&fecha=" + pCronograma.getFechaInicio();
        }

        if (pCronograma == null) {
            msg = "Ha ocurrido un error inesperado";
            redir.addFlashAttribute("msg", msg);

            pCronograma.setIdPrograma(pCronograma.getIdPrograma());
            model.addAttribute("pCronograma", pCronograma);
            model.addAttribute("modulos", moduloService.listar());
            model.addAttribute("profesores", profesorService.listar());
            return "redirect:/agregarModulo?idPro=" + pCronograma.getIdPrograma().toString() + "&fecha=" + pCronograma.getFechaInicio();
        }
        if (cronogramaService.ingresarDias(pCronograma.isLunes(), pCronograma.isMartes(), pCronograma.isMiercoles(), pCronograma.isJueves(), pCronograma.isViernes(), pCronograma.isSabado(), (int) pCronograma.getIdModulo(), (int) pCronograma.getIdPrograma()) == 0) {
            Programa programa = programaService.obtenerPrograma(pCronograma.getIdPrograma());

            fechaInicioR = cronogramaService.guardar(pCronograma.getIdModulo(),
                    pCronograma.getIdPrograma(),
                    pCronograma.getIdProfesor(),
                    pCronograma.getHorasDia(),
                    pCronograma.getHoraInicio(),
                    pCronograma.getHoraFin(),
                    pCronograma.getEstado(),
                    programa.getCentro().getIdCentro(),
                    pCronograma.getFechaInicio()
            );
            String[] arreglosFecha = fechaInicioR.split("-");
            String anio = arreglosFecha[0];//Se va a descomponer la cadena de la fecha inicial del modulo para extraer su año de inicio.
            if (anio == "0001") {
                msg = "Error en la conexión a la base de datos2";
                redir.addFlashAttribute("msg", msg);

                pCronograma.setIdPrograma(pCronograma.getIdPrograma());
                model.addAttribute("pCronograma", pCronograma);
                model.addAttribute("modulos", moduloService.listar());
                model.addAttribute("profesores", profesorService.listar());
                return "redirect:/agregarModulo?idPro=" + pCronograma.getIdPrograma().toString() + "&fecha=" + pCronograma.getFechaInicio();
            }
        } else {
            msg = "Error en la conexión a la base de datos";
            redir.addFlashAttribute("msg", msg);

            pCronograma.setIdPrograma(pCronograma.getIdPrograma());
            model.addAttribute("pCronograma", pCronograma);
            model.addAttribute("modulos", moduloService.listar());
            model.addAttribute("profesores", profesorService.listar());
            return "redirect:/agregarModulo?idPro=" + pCronograma.getIdPrograma().toString() + "&fecha=" + pCronograma.getFechaInicio();
        }
        Programa programa = programaService.obtenerPrograma(pCronograma.getIdPrograma());
        ProgramaFecha programaFecha = new ProgramaFecha();
        programaFecha.setPrograma(programa);
        programaFecha.setIdPro(pCronograma.getIdPrograma());

        List<String> lista = (List<String>) cronogramaService.obtenerFechaInicio(pCronograma.getIdPrograma());
        programaFecha.setFechaInicio(lista.get(0));
        redir.addFlashAttribute("programaFecha", programaFecha);
        List<Cronograma> cronogramas = cronogramaService.listarCronogramas(programaFecha.getPrograma().getIdPrograma());
        redir.addFlashAttribute("cronogramas", cronogramas);
        redir.addFlashAttribute("programa", programa);
        return "redirect:/admCronograma";
    }
}
