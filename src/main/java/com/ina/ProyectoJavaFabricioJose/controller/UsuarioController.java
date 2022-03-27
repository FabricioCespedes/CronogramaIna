/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.controller;

import com.ina.ProyectoJavaFabricioJose.domain.Centro;
import com.ina.ProyectoJavaFabricioJose.domain.Usuario;
import com.ina.ProyectoJavaFabricioJose.services.CentroService;
import com.ina.ProyectoJavaFabricioJose.services.UsuarioService;
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
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CentroService centroService;

    @GetMapping("/usuarios")
    public String listaCliente(Model model, @ModelAttribute("msg") String msg) {
        List<Usuario> lista = usuarioService.listar();
        model.addAttribute("usuarios", lista);
        return "listaUsuarios";
    }

    @PostMapping("/filtrarUsuarios")
    public String filtar(String txtTexto, Model model) {
        List<Usuario> lista = usuarioService.listar(txtTexto);
        model.addAttribute("usuarios", lista);
        return "listaUsuarios";
    }

    @GetMapping("/nuevoUsuario")
    public String nuevo(Usuario usuario, Model model) {

        List<Centro> lista = centroService.listar();
        model.addAttribute("centros", lista);

        return "usuario";
    }

    @PostMapping("/guardarUsuario")
    public String guardar(@Valid Usuario usuario, RedirectAttributes redir) {
        String msg = "";

        if (usuarioService.guardar(usuario) != 0) {

            msg = "Usuario insertado";

        } else {
            msg = "No se pudo insertar el usuario";
        }

        redir.addFlashAttribute("msg", msg);

        return "redirect:/usuarios";
    }

    @GetMapping("/editarUsuario/{idUsuario}")
    public String editar(Usuario usuario, Model model, RedirectAttributes redir) {

        usuario = usuarioService.obtenerUsuario(usuario.getIdUsuario());
        if (usuario != null) {
            List<Centro> lista = centroService.listar();
            model.addAttribute("centros", lista);
            model.addAttribute("usuario", usuario);
            return "usuario";
        }
        String msg = "No se logró cargar el usuario";

        redir.addFlashAttribute("msg", msg);

        return "redirect:/usuario";
    }

    @RequestMapping(value = "/deleteUsu", method = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT})
    public String eliminar(Long idUsuario, Model model) {
        Usuario usuario = usuarioService.obtenerUsuario(idUsuario);
        model.addAttribute("usuario", usuario);
        usuarioService.eliminar(usuario);
        return "redirect:/usuarios";
    }
}
