
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.controller;

import com.ina.ProyectoJavaFabricioJose.domain.Modulo;
import com.ina.ProyectoJavaFabricioJose.services.ModuloService;
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
public class ModuloController {
    
    //Inyecciones de los dferentes servicios que nesecitará el controlador
    @Autowired
    private ModuloService moduloService;
    
    /**
     * Función que se encarga de devolver una vista con una lista de módulos
     * @param model Objeto Model para agregar enviar variables hacia un modelo     
     * @return Una vista con una lista de módulos
     */
    @GetMapping("/modulos")
    public String listaCliente(Model model, @ModelAttribute("msg") String msg) {
        List<Modulo> lista = moduloService.listar();
        model.addAttribute("modulos", lista);
        return "listaModulos";
    }

    /**
     * Función que se encarga de devolver una vista con una lista de módulos filtrados
     * @param txtTexto Cuadro de texto por el que se va a filtrar los módulos
     * @param model Objeto Model para agregar enviar variables hacia un modelo     
     * @return Una vista con una lista de módulos filtrados
     */
    @PostMapping("/filtrarModulos")
    public String filtar(String txtTexto, Model model) {
        List<Modulo> lista = moduloService.listar(txtTexto);
        model.addAttribute("modulos", lista);
        return "listaModulos";
    }

    /**
     * Función que retorna una vista con un formulario para agregar un nuevo módulo.
     * @param modulo Objeto Modulo para identificar como se guardará el objeto en el formulario
     * @param model Objeto Model para agregar enviar variables hacia un modelo     
     * @return Una vista con un formulario para agregar el nuevo módulo
     */
    @GetMapping("/nuevoModulo")
    public String nuevo(Modulo modulo, Model model) {

        List<Modulo> lista = moduloService.listar();
        model.addAttribute("modulos", lista);

        return "modulo";
    }
    
    /**
     * En la vista para agregar un módulo, el formlario trabaja con este mapeo que 
     * llama a una fucnción que hace unas respectivas validaciones y intenta guardar el módulo
     * @param modulo Objeto Modulo para identificar como se guardará el objeto en el formulario
     * @param redir Objeto RedirectAttributes para envíar flash attributes a un modelo
     * @return Una vista listando módulos junto con un aviso diciendo si se agregó o no el objeto ingresado
     */
    @PostMapping("/guardarModulo")
    public String guardar(@Valid Modulo modulo, RedirectAttributes redir) {
        String msg = "";
        
        if (moduloService.guardar(modulo) == 1) {
            msg = "Módulo insertado insertado";
        }else{
            msg = "El módulo no se pudo insertar insertar";
        }
        
        redir.addFlashAttribute("msg", msg);

        return "redirect:/modulos";
    }
    
    /**
     * Similar a la función nuevo pero este carga un módulo para poder editarlo y guardarlo
     * @param modulo Objeto Modulo que se llena para cargar al formulaio sus respectivos datos
     * @param model Objeto Modulo para identificar como se guardará el objeto en el formulario
     * @param redir Objeto RedirectAttributes para envíar flash attributes a un modelo
     * @return Una vista con un formulario para agregar un módulo pero con datos cargados
     */
    @GetMapping("/editarModulo/{idModulo}")
    public String editar(Modulo modulo, Model model, RedirectAttributes redir) {

        modulo = moduloService.obtenerModulo(modulo.getIdModulo());
        if (modulo != null) {
            List<Modulo> lista = moduloService.listar();
            model.addAttribute("modulos", lista);
            model.addAttribute("modulo", modulo);
            return "modulo";
        }
        String msg = "No se logró cargar el modulo";

        redir.addFlashAttribute("msg", msg);

        return "redirect:/modulos";
    }
    
    /**
     * Al realizar ciertas acciones en la vista lista de módulos se llama a un modal para confirmar la eliminación de un módulo y ejecutarla
     * @param idModulo Id de módulo que se envía al llamar a la función para buscar y obtener a un módulo y eliminarlo
     * @param model Objeto Modulo para identificar como se guardará el objeto en el formulario
     * @param redir Objeto RedirectAttributes para envíar flash attributes a un modelo
     * @return 
     */
    @RequestMapping(value = "/deleteMod", method = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT})
    public String eliminar(Integer idModulo, Model model, RedirectAttributes redir) {
        String msg = "No se pudo eliminar el módulo";
        Modulo modulo = moduloService.obtenerModulo(idModulo);
        model.addAttribute("modulo", modulo);
        if (moduloService.eliminar(modulo) == 1) {
            msg = "Módulo eliminado";
        }
        redir.addFlashAttribute("msg", msg);
        return "redirect:/modulos";
    }
    

}
