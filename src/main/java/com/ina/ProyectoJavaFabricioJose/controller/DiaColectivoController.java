/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.controller;

import com.ina.ProyectoJavaFabricioJose.domain.Centro;
import com.ina.ProyectoJavaFabricioJose.domain.DiasAusentesColectivos;
import com.ina.ProyectoJavaFabricioJose.domain.Motivo;
import com.ina.ProyectoJavaFabricioJose.services.CentroService;
import com.ina.ProyectoJavaFabricioJose.services.DiaColectivoService;
import com.ina.ProyectoJavaFabricioJose.services.MotivoService;
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
public class DiaColectivoController {
    
    //Inyecciones de los dferentes servicios que nesecitará el controlador
    @Autowired
    private DiaColectivoService colectivoService;

    @Autowired
    private MotivoService motivoService;

    @Autowired
    private CentroService centroService;
    
    /**
     * Función que se encarga de devolver una vista con una lista de días colectivos
     * @param txtTexto Cuadro de texto por el que se va a filtrar los días  colectivos
     * @param model Objeto Model para agregar enviar variables hacia un modelo     
     * @return Una vista con una lista de días colectivos filtrada por un año
     */
    @GetMapping("/colectivos")
    public String listaColectivos(String txtTexto, Model model) {

        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        List<DiasAusentesColectivos> lista;
        lista = colectivoService.listar(String.valueOf(year));
        model.addAttribute("colectivos", lista);
        model.addAttribute("year", year);
        model.addAttribute("diaColectivo", null);
        return "listaDiasColectivos";
    }
    
    /**
     * Devuelve la misma vista pasada pero filtrada por un año
     * @param txtTexto Año por el que va a filtrar los días colectivos
     * @param model Objeto Model para agregar enviar y variables hacia un modelo
     * @return Una vista con una lista de días colectivos filtrada por un año
     */
    @PostMapping("/filtrarColectivos")
    public String filtar(String txtTexto, Model model) {
        List<DiasAusentesColectivos> lista = colectivoService.listar(txtTexto);
        model.addAttribute("colectivos", lista);
        return "listaDiasColectivos";
    }
    
    /**
     * Función que retorna una vista con un formulario para agregar un nuevo día colectivo de algún centro.
     * @param diaColectivo Objeto DiasAusentesColectivos para identificar como se guardará el objeto en el formulario
     * @param model Objeto Model para agregar enviar variables hacia un modelo
     * @return Una vista con un formulario para agregar el nuevo día colectivo
     */
    @GetMapping("/nuevoColectivo")
    public String nuevo(DiasAusentesColectivos diaColectivo, Model model) {

        List<Centro> listaCen = centroService.listar();

        List<Motivo> listaMot = motivoService.listar();

        model.addAttribute("centros", listaCen);
        model.addAttribute("motivos", listaMot);

        return "diasColectivos";
    }
    
    /**
     * En la vista para agregar un día colectivo el formlario trabaja con este mapeo que 
     * llama a una fucnción que hace unas respectivas validaciones y intenta guardar la fecha colectiva
     * @param diaColectivo Objeto DiasAusentesColectivos para identificar como se guardará el objeto en el formulario
     * @param redir Objeto RedirectAttributes para envíar flash attributes a un modelo
     * @return Una vista listando días colectivos junto con un aviso diciendo si se agregó o no el objeto ingresado
     */
    @PostMapping("/guardarColectivo")
    public String guardar(@Valid DiasAusentesColectivos diaColectivo, RedirectAttributes redir) {
        String msg = "";

        if (diaColectivo.getFechaInicio().before(diaColectivo.getFechaFin())) {
            if (colectivoService.guardar(diaColectivo) != 0) {
                msg = "Fecha colectiva insertada";
            } else {
                msg = "No se pudo insertar la fecha colectiva";
            }
        } else {
            msg = "No se pudo insertar la fecha colectiva porque la fecha de fin es primero que la fecha inicial";
        }

        redir.addFlashAttribute("msg", msg);

        return "redirect:/colectivos";
    }
    
    /**
     * Similar a la función nuevo pero este carga un día colectivo para poder editarlo y guardarlo
     * @param diaColectivo Objeto DiasAusentesColectivos que se llena para cargar al formulaio sus respectivos datos
     * @param model Objeto Model para agregar enviar variables hacia un modelo
     * @return Una vista con un formulario para agregar un día colectivo pero con datos cargados
     */
    @GetMapping("/editarColectivo/{idDia}")
    public String editar(DiasAusentesColectivos diaColectivo, Model model) {

        diaColectivo = colectivoService.obtenerColectivo(diaColectivo.getIdDia());
        if (diaColectivo != null) {
            List<Centro> listaCen = centroService.listar();
            List<Motivo> listaMot = motivoService.listar();
            model.addAttribute("centros", listaCen);
            model.addAttribute("motivos", listaMot);
            model.addAttribute("diasAusentesColectivos", diaColectivo);
            return "diasColectivos";
        }
        String msg = "No se logró cargar la fecha colectiva";

        return "redirect:/colectivos";
    }
    
    /**
     * Al realizar ciertas acciones en la vista lista de días colectivos se llama a un modal para confirmar la eliminación de un día colectivo y ejecutarla
     * @param idDia Id de día colectivo que se envía al llamar a la función para buscar y obtener a un día colectivo y eliminarlo
     * @param model Objeto Model para agregar enviar variables hacia un modelo
     * @param redir Objeto RedirectAttributes para envíar flash attributes a un modelo
     * @return Una vista con días colectivos y un mensaje diciendo si se ejecuto la acción
     */
    @RequestMapping(value = "/deleteCol", method = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT})
    public String eliminar(Integer idDia, Model model, RedirectAttributes redir) {
        int result = 0;
        String msg = "No se pudo eliminar la fecha colectiva";
        DiasAusentesColectivos diaColectivo = colectivoService.obtenerColectivo(idDia);
        model.addAttribute("diaColectivo", diaColectivo);
        result = colectivoService.eliminar(diaColectivo.getIdDia());

        if (result == 1) {
            msg = "Fecha colectiva eliminada con exito";
        }

        redir.addFlashAttribute("msg", msg);

        return "redirect:/colectivos";
    }

}
