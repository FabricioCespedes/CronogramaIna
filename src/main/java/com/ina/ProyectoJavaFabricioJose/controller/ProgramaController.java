/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.controller;

import com.ina.ProyectoJavaFabricioJose.domain.Centro;
import com.ina.ProyectoJavaFabricioJose.domain.Programa;
import com.ina.ProyectoJavaFabricioJose.services.CentroService;
import com.ina.ProyectoJavaFabricioJose.services.ProgramaService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
public class ProgramaController {

    @Autowired
    private ProgramaService programaService;

    @Autowired
    private CentroService centroService;

    @GetMapping("/programas")
    public String listaCliente(Model model, @ModelAttribute("msg") String msg) {
        List<Programa> lista = programaService.listar();
        model.addAttribute("programas", lista);
        return "listaProgramas";
    }

    @PostMapping("/filtrarProgramas")
    public String filtar(String txtTexto, Model model) {
        List<Programa> lista = programaService.listar(txtTexto);
        model.addAttribute("programas", lista);
        return "listaProgramas";
    }

    @GetMapping("/nuevoPrograma")
    public String nuevo(Programa programa, Model model) {

        List<Centro> lista = centroService.listar();
        model.addAttribute("centros", lista);

        return "programa";
    }

    @PostMapping("/guardarPrograma")
    public String guardar(@Valid Programa programa, RedirectAttributes redir) throws ParseException {
        String msg = "";
        long diff = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        
        LocalDate localDate = LocalDate.now();
        
        Date d1 = sdf.parse(localDate+" "+ programa.getHoraInicio());
        Date d2 = sdf.parse(localDate+" "+ programa.getHoraFin());

        diff = d2.getTime() - d1.getTime();

        programa.setHorasDia(diff / (60 * 60 * 1000));

        programaService.guardar(programa);

        msg = "Programa insertado";

        redir.addFlashAttribute("msg", msg);

        return "redirect:/programas";
    }

    @GetMapping("/editarPrograma/{idPrograma}")
    public String editar(Programa programa, Model model, RedirectAttributes redir) {

        programa = programaService.obtenerPrograma(programa.getIdPrograma());
        if (programa != null) {
            List<Centro> lista = centroService.listar();
            model.addAttribute("programa", programa);
            model.addAttribute("centros", lista);
            return "programa";
        }
        String msg = "No se logró cargar el programa";

        redir.addFlashAttribute("msg", msg);

        return "redirect:/programas";
    }

    @RequestMapping(value = "/deleteProg", method = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT})
    public String eliminar(Integer idPrograma, Model model) {
        Programa programa = programaService.obtenerPrograma(idPrograma);
        model.addAttribute("programa", programa);
        programaService.eliminar(programa);
        return "redirect:/programas";
    }

}
