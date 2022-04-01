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
    
    //Inyecciones de los dferentes servicios que nesecitará el controlador
    @Autowired
    private IFeriadoService feriadoService;

    @Autowired
    private IMotivoService motivoService;
    
    
    /**
     * Función que se encarga de devolver una vista con una lista de días feriados
     * @param txtTexto Cuadro de texto por el que se va a filtrar los días  feriados
     * @param model Objeto Model para agregar enviar variables hacia un modelo     
     * @return Una vista con una lista de días feriados filtrada por un año
     */
    @GetMapping("/feriados")
    public String listaFeriados(String txtTexto, Model model) {

        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        List<DiaFeriado> lista;
        lista = feriadoService.listar(String.valueOf(year));
        model.addAttribute("feriados", lista);
        model.addAttribute("year", year);
        model.addAttribute("diaFeriado", null);
        return "listaDiasFeriados";

    }

    /**
     * Devuelve la misma vista pasada pero filtrada por un año
     * @param txtTexto Año por el que va a filtrar los días feriados
     * @param model Objeto Model para agregar y enviar variables hacia un modelo
     * @return Una vista con una lista de días feriados filtrada por un año
     */
    @PostMapping("/filtrarFeriados")
    public String filtar(String txtTexto, Model model) {
        List<DiaFeriado> lista = feriadoService.listar(txtTexto);
        model.addAttribute("feriados", lista);
        return "listaDiasFeriados";
    }

    /**
     * Función que retorna una vista con un formulario para agregar un nuevo día feriado.
     * @param feriado Objeto DiaFeriado para identificar como se guardará el objeto en el formulario
     * @param model Objeto Model para agregar y enviar variables hacia un modelo
     * @return Una vista con un formulario para agregar el nuevo día feriado
     */
    @GetMapping("/nuevoFeriado")
    public String nuevo(DiaFeriado feriado, Model model) {

        List<Motivo> lista = motivoService.listar();
        model.addAttribute("motivos", lista);

        return "feriado";
    }

    /**
     * En la vista para agregar un día feriado el formlario trabaja con este mapeo que 
     * llama a una fucnción que hace unas respectivas validaciones y intenta guardar el día feriado
     * @param feriado Objeto DiaFeriado para identificar como se guardará el objeto en el formulario
     * @param redir Objeto RedirectAttributes para envíar flash attributes a un modelo
     * @return Una vista listando días feriados junto con un aviso diciendo si se agregó o no el objeto ingresado
     */
    @PostMapping("/guardarFeriado")
    public String guardar(@Valid DiaFeriado feriado, RedirectAttributes redir) {
        String msg = "";

        
            if (feriadoService.guardar(feriado) != 0) {
                msg = "Día feriado insertado";
            } else {
                msg = "No se pudo insertar el día feriado";
            }
      

        redir.addFlashAttribute("msg", msg);

        return "redirect:/feriados";
    }

    /**
     * Similar a la función nuevo pero este carga un día colectivo para poder editarlo y guardarlo
     * @param diaFeriado Objeto DiaFeriado que se llena para cargar al formulaio sus respectivos datos
     * @param model Objeto Model para agregar y enviar variables hacia un modelo
     * @return Una vista con un formulario para agregar un día feriado pero con datos cargados
     */
    @PostMapping("/editarFeriados/{idDia}")
    public String editar(DiaFeriado diaFeriado, Model model) {

        diaFeriado = feriadoService.obtenerFeriado(diaFeriado.getIdDia());
        if (diaFeriado != null) {
            model.addAttribute("diaFeriado", diaFeriado);
        }
        String msg = "No se logró cargar el feriado";

        return "redirect:/feriados";
    }

    /**
     * Al realizar ciertas acciones en la vista lista de días feriados se llama a un modal para confirmar la eliminación de un día feriado y ejecutarla
     * @param idDia Id de día colectivo que se envía al llamar a la función para buscar y obtener a un día colectivo y eliminarlo
     * @param model Objeto Model para agregar y enviar variables hacia un modelo
     * @param redir Objeto RedirectAttributes para envíar flash attributes a un modelo
     * @return Una vista con días feriados y un mensaje diciendo si se ejecuto la acción
     */
    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET,RequestMethod.PUT })
    public String eliminar(Integer idDia, Model model, RedirectAttributes redir) {
        String msg = "No se pudo eliminar el día feriado";
        DiaFeriado diaFeriado = feriadoService.obtenerFeriado(idDia);
        model.addAttribute("diaFeriado", diaFeriado);
        if (feriadoService.eliminar(idDia) == 1) {
            msg = "Día feriado eliminado";
        }
        
        redir.addFlashAttribute("msg", msg);
        return "redirect:/feriados";
    }

}
