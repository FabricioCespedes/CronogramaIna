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
    
    //Inyecciones de los dferentes servicios que nesecitará el controlador
    @Autowired
    private CentroService centroService;

    @Autowired
    private DistritoService distritoService;
    
    /**
     * Función que retorna una vista con una lista de los centros disponibles en la baase de datos.
     * @param model Objeto Model para agregar enviar variables hacia un modelo
     * @return Una vista con una lista de los centros disponibles
     */
    @GetMapping("/centros")
    public String listaCliente(Model model) {

        List<Centro> lista = centroService.listar();
        model.addAttribute("centros", lista);
        return "listaCentros";
    }

    /**
     * Función que hace que en la vista de la lista de centros se puedan filtrar según lo que se escriba en un cuadro determinado.
     * @param txtTexto
     * @param model Objeto Model para agregar enviar variables hacia un modelo
     * @return Una vista con una lista de los centros disponibles filtrados por un criterio
     */
    @PostMapping("/filtrarCentros")
    public String filtar(String txtTexto, Model model) {
        List<Centro> lista = centroService.listar(txtTexto);
        model.addAttribute("centros", lista);
        return "listaMotivos";
    }
    
    /**
     * Función que retorna un vista con un formulario para agregar un nuevo centro de formación.
     * @param centro Objeto Centro para identificar como se guardará el objeto en el formulario
     * @param model Objeto Model para agregar enviar variables hacia un modelo
     * @return Una vista con un formulario
     */
    @GetMapping("/nuevoCentro")
    public String nuevo(Centro centro, Model model) {

        List<Distrito> lista = distritoService.listar();
        model.addAttribute("distritos", lista);

        return "centro";
    }
    
    /**
     * En la vista para agregar centros el formlario trabaja con este mapeo que 
     * llama a una fucnción que hace unas respectivas validaciones y intenta guardar el centro de formación
     * @param centro Objeto Centro para identificar como se guardará el objeto en el formulario
     * @param redir Objeto RedirectAttributes para envíar flash attributes a un modelo
     * @return Una vista listando centros junto con un aviso diciendo si se agregó o no el objeto ingresado
     */
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
    
    /**
     * Similar a la función nuevo pero este carga un centro para poder editarlo y guardarlo
     * @param centro Objeto Centro que se llena para cargar al formulaio sus respectivos datos
     * @param model Objeto Model para agregar enviar variables hacia un modelo
     * @param redir Objeto RedirectAttributes para envíar flash attributes a un modelo
     * @return Una vista con un formulario para agregar un centro pero con datos cargados
     */
    @GetMapping("/editarCentro/{idCentro}")
    public String editar(Centro centro, Model model, RedirectAttributes redir) {

        centro = centroService.obtenerCentro(centro.getIdCentro());
        if (centro != null) {
             List<Distrito> lista = distritoService.listar();
            model.addAttribute("distritos", lista);
            model.addAttribute("centro", centro);
            return "centro";
        }
        String msg = "No se logró cargar el centro";

        redir.addFlashAttribute("msg", msg);

        return "redirect:/centros";
    }
    
    /**
     * Al realizar ciertas acciones en la vista lista de centros se llama a un modal para confirmar la eliminación de un centro y ejecutarla
     * @param idCentro Id de centro que se envía al llamar a la función para buscar y obtener a un centro y eliminarlo
     * @param model Objeto Model para agregar enviar variables hacia un modelo
     * @param redir Objeto RedirectAttributes para envíar flash attributes a un modelo
     * @return Una vista con centros disponibles y un mensaje diciendo si se ejecuto la acción
     */
    @RequestMapping(value = "/deleteCen", method = {RequestMethod.DELETE, RequestMethod.GET,RequestMethod.PUT })
    public String eliminar(Integer idCentro, Model model, RedirectAttributes redir) {
        String msg = "No se pudo eliminar el centro";
        Centro centro = centroService.obtenerCentro(idCentro);
        model.addAttribute("centro", centro);
        if (centroService.eliminar(idCentro)==1) {
            msg = "Centro Eliminado";
        }
        redir.addFlashAttribute("msg", msg);
        return "redirect:/centros";
    }
}
