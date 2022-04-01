/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.controller;

import com.ina.ProyectoJavaFabricioJose.domain.DiaAusente;
import com.ina.ProyectoJavaFabricioJose.domain.Motivo;
import com.ina.ProyectoJavaFabricioJose.domain.Profesor;
import com.ina.ProyectoJavaFabricioJose.domain.Usuario;
import com.ina.ProyectoJavaFabricioJose.services.DiaAusenteService;
import com.ina.ProyectoJavaFabricioJose.services.MotivoService;
import com.ina.ProyectoJavaFabricioJose.services.ProfesorService;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpSession;
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
    
    //Inyecciones de los dferentes servicios que nesecitará el controlador
    @Autowired
    private DiaAusenteService diaAusenteService;

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private MotivoService motivoService;
    
    /**
     * Función que se encarga de devolver una vista con una lista de ausencias
     * @param profesor Objeto profesro que ayuda a crear una lista de profesores para filtrar
     * @param model Objeto Model para agregar enviar variables hacia un modelo     
     * @param session Objeto HttpSession para acceder a nuesras variables de sesión
     * @return Una vista con una lista de profesores filtrada por un centro
     */
    @GetMapping("/diasAusentes")
    public String listaCliente(Profesor profesor, Model model, HttpSession session) {

        Calendar cal = Calendar.getInstance();
        List<Profesor> listaPro;
        List<DiaAusente> lista;
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        lista = diaAusenteService.listar();
        listaPro = profesorService.listar(usuario.getCentro().getIdCentro());
        model.addAttribute("listaPro", listaPro);
        model.addAttribute("diasAusentes", lista);
        return "listaDiasAusentes";

    }
    
    /**
     * Devuelve la misma vista pasada pero filtrada por un profesor escogido
     * @param profesor Objeto profesor que ayuda a crear una lista de profesores para filtrar
     * @param model Objeto Model para agregar enviar variables hacia un modelo     
     * @param redir Objeto RedirectAttributes para envíar flash attributes a un modelo
     * @param session Objeto HttpSession para acceder a nuesras variables de sesión
     * @return Una vista con una lista de aucensias filtrada por un centro y un profesor
     */
    @PostMapping("/filtrarAusentes")
    public String filtar(Profesor profesor, Model model, RedirectAttributes redir, HttpSession session) {

        List<DiaAusente> lista = diaAusenteService.listar(profesor.getIdProfesor());
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Profesor> listaPro = profesorService.listar(usuario.getCentro().getIdCentro());
        Profesor profesor2 = profesorService.obtenerProfesor(profesor.getIdProfesor());
        model.addAttribute("profesor", profesor2);

        model.addAttribute("listaPro", listaPro);
        model.addAttribute("diasAusentes", lista);
        redir.addFlashAttribute("listaPro", listaPro);

        redir.addFlashAttribute("diasAusentes", lista);

        return "listaDiasAusentes";
    }
    
    /**
     * Función que retorna un vista con un formulario para agregar una nueva ausencia de algún profesor.
     * @param diaAusente Objeto DiaAusente para identificar como se guardará el objeto en el formulario
     * @param model Objeto Model para agregar enviar variables hacia un modelo     
     * @param session Objeto HttpSession para acceder a nuesras variables de sesión
     * @return Una vista con un formulario para agregar la nueva ausencia
     */
    @GetMapping("/nuevoDiaAusente")
    public String nuevo(DiaAusente diaAusente, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Profesor> listaPr = profesorService.listar(usuario.getCentro().getIdCentro());

        List<Motivo> listaMot = motivoService.listar();

        model.addAttribute("profesores", listaPr);
        model.addAttribute("motivos", listaMot);

        return "diaAusente";
    }
    
    /**
     * En la vista para agregar días de ausencia el formlario trabaja con este mapeo que 
     * llama a una fucnción que hace unas respectivas validaciones y intenta guardar la ausencia
     * @param diaAusente Objeto DiaAusente para identificar como se guardará el objeto en el formulario
     * @param redir Objeto RedirectAttributes para envíar flash attributes a un modelo
     * @return Una vista listando días de ausencia junto con un aviso diciendo si se agregó o no el objeto ingresado
     */
    @PostMapping("/guardarAusencia")
    public String guardar(@Valid DiaAusente diaAusente, RedirectAttributes redir) {
        String msg = "";
        
        if (diaAusente.getFechaInicio().before(diaAusente.getFechaFin()) || diaAusente.getFechaInicio().equals(diaAusente.getFechaFin())  ) {
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

    /**
     * Similar a la función nuevo pero este carga una ausencia para poder editarla y guardarla
     * @param diaAusente Objeto DiaAusente que se llena para cargar al formulaio sus respectivos datos
     * @param model Objeto Model para agregar enviar variables hacia un modelo     
     * @param session session Objeto HttpSession para acceder a nuesras variables de sesión
     * @return Una vista con un formulario para agregar una ausencia pero con datos cargados
     */
    @GetMapping("/editarAusencia/{idDiaAusente}")
    public String editar(DiaAusente diaAusente, Model model, HttpSession session) {

        diaAusente = diaAusenteService.obtenerAusencia(diaAusente.getIdDiaAusente());
        if (diaAusente != null) {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            List<Profesor> listaPr = profesorService.listar(usuario.getCentro().getIdCentro());
            List<Motivo> listaMot = motivoService.listar();
            model.addAttribute("diaAusente", diaAusente);
            model.addAttribute("profesores", listaPr);
            model.addAttribute("motivos", listaMot);
            return "diaAusente";
        }
        String msg = "No se logró cargar la fecha de ausencia";

        return "redirect:/diasAusentes";
    }

    /**
     * Al realizar ciertas acciones en la vista lista de ausencias se llama a un modal para confirmar la eliminación de una ausencia y ejecutarla
     * @param idDiaAusente Id de ausencia que se envía al llamar a la función para buscar y obtener a una ausencia y eliminarla
     * @param model Objeto Model para agregar enviar variables hacia un modelo
     * @param redir Objeto RedirectAttributes para envíar flash attributes a un modelo
     * @return Una vista con ausencias y un mensaje diciendo si se ejecuto la acción
     */
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
