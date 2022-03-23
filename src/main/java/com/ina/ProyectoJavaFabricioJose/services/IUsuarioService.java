/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.domain.Usuario;
import java.util.List;


public interface IUsuarioService {
    public void guardar (Usuario profesor);    
    public void eliminar(Usuario profesor);
    public List<Usuario> listar();
    public List<Usuario> listar(String nombre);
    public Usuario obtenerUsuario(Long idUsuario);
    public Usuario login(long username, String contrasenia);
}
