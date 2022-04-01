/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.controller;

import com.ina.ProyectoJavaFabricioJose.domain.Usuario;
import com.ina.ProyectoJavaFabricioJose.services.UsuarioService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

    @Autowired
    UsuarioService usuarioService;
    //Este mapeo de controlador hace que a cualquiera de esa dos direcciones se retorne el formulario para hacer un logueo al sistema.
    @GetMapping({"/", "/login"})
    public String log() {
        return "login";
    }
    //Tras pasar correctamente el formulario de logueo se buscará si el usuario es válido, si es válido se creará una variable de sesión
    // si no, se devolverá al formulario de logueo y se indicará un error de inicio de sesión.
    @PostMapping("/index")
    public String inicio(String username, String password, Model model, HttpSession session) {

        if (username != "" && password != "") {
            long idUsuario = Long.parseLong(username);
            if (session.getAttribute("usuario") == null) {
                Usuario usuario = usuarioService.login(idUsuario, password);
                usuario.setContrasenia(null);
                session.setAttribute("usuario", usuario);
                return "index";
            }
        }

        String msg = "Error al iniciar sesión";

        model.addAttribute("msg", msg);

        return "/login";
    }
}
