/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.controller;

import com.ina.ProyectoJavaFabricioJose.domain.Usuario;
import com.ina.ProyectoJavaFabricioJose.services.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping({"/", "/login"})
    public String log() {
        return "login";
    }

    @PostMapping("/index")
    public String inicio(String username, String password, Model model) {

        if (username != "") {
            long idUsuario = Long.parseLong(username);
                    Usuario usuario = usuarioService.login(idUsuario, password);
            if (usuario != null) {
                model.addAttribute("usuario", usuario);
                return "index";
            }
        }
        
        String msg = "Error al iniciar sesión";
        
        model.addAttribute("msg", msg);
        
        return "/login";
    }
}
