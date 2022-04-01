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

    //Inyecciones de los dferentes servicios que nesecitará el controlador
    @Autowired
    private ProgramaService programaService;

    @Autowired
    private CentroService centroService;

    /**
     * Función que se encarga de devolver una vista con una lista de programas
     * @param model Objeto Model para agregar enviar variables hacia un modelo 
     * @return Una vista con una lista de programas
     */
    @GetMapping("/programas")
    public String listaCliente(Model model, @ModelAttribute("msg") String msg) {
        List<Programa> lista = programaService.listar();
        model.addAttribute("programas", lista);
        return "listaProgramas";
    }

    /**
     * Función que se encarga de devolver una vista con una lista de programas filtrados
     * @param txtTexto Cuadro de texto donde se envia el parámetro para filtrar
     * @param model Objeto Model para agregar enviar variables hacia un modelo 
     * @return Una vista con una lista de programas filtrados
     */
    @PostMapping("/filtrarProgramas")
    public String filtar(String txtTexto, Model model) {
        List<Programa> lista = programaService.listar(txtTexto);
        model.addAttribute("programas", lista);
        return "listaProgramas";
    }

    /**
     * Función que retorna una vista con un formulario para agregar un nuevo programa.
     * @param programa Objeto Programa para identificar como se guardará el objeto en el formulario
     * @param model Objeto Model para agregar enviar variables hacia un modelo 
     * @return Una vista con un formulario para agregar el nuevo programa 
     */
    @GetMapping("/nuevoPrograma")
    public String nuevo(Programa programa, Model model) {

        List<Centro> lista = centroService.listar();
        model.addAttribute("centros", lista);

        return "programa";
    }

    /**
     * En la vista para agregar un programa, el formlario trabaja con este mapeo que 
     * llama a una fucnción que hace unas respectivas validaciones y intenta guardar el programa
     * @param programa Objeto Programa para identificar como se guardará el objeto en el formulario
     * @param redir Objeto RedirectAttributes para envíar flash attributes a un modelo
     * @return  Una vista listando programas junto con un aviso diciendo si se agregó o no el objeto ingresado
     * @throws ParseException Esta exepción en caso de que falle el parseo para calcular la horas diarias
     */
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
        
        if(programa.getHorasDia()<6){
            if(programaService.guardar(programa) == 1){
                msg = "Programa insertado";
            }else{
                msg = "El programa no se pudo insertar";
            }
        }else{
            msg = "El programa no se pudo insertar porque tiene 6 o más hora diarias";
        }
        redir.addFlashAttribute("msg", msg);

        return "redirect:/programas";
    }

    /**
     * Similar a la función nuevo pero este carga un profesor para poder editarlo y guardarlo
     * @param programa Objeto Programa para identificar como se guardará el objeto en el formulario
     * @param model Objeto Model para agregar enviar variables hacia un modelo
     * @param redir Objeto RedirectAttributes para envíar flash attributes a un modelo
     * @return Una vista con un formulario para agregar un programa pero con datos cargados
     */
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

    /**
     * Al realizar ciertas acciones en la vista lista de programas se llama a un modal para confirmar la eliminación de un programa y ejecutarla
     * @param idPrograma Id de programa que se envía al llamar a la función para buscar y obtener a un programa y eliminarlo
     * @param model Objeto Model para agregar enviar variables hacia un modelo
     * @param redir Objeto RedirectAttributes para envíar flash attributes a un modelo
     * @return Una vista listando programas junto con un aviso diciendo si se elimino o no el objeto
     */
    @RequestMapping(value = "/deleteProg", method = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT})
    public String eliminar(Integer idPrograma, Model model, RedirectAttributes redir) {
        String msg = "No se pudo eliminar el programa";
        Programa programa = programaService.obtenerPrograma(idPrograma);
        model.addAttribute("programa", programa);
        if (programaService.eliminar(programa) == 1) {
            msg = "Programa eliminado";
        }
        return "redirect:/programas";
    }

}
