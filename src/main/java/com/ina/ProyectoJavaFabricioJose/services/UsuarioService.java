/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.dao.IUsuarioDao;
import com.ina.ProyectoJavaFabricioJose.domain.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;
    
    @Override
    public void guardar(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    @Override
    public void eliminar(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Override
    public List<Usuario> listar() {
        return usuarioDao.findAll();
    }

    @Override
    public List<Usuario> listar(String nombre) {
        return (List<Usuario>) usuarioDao.findByNombreContains(nombre);
    }

    @Override
    public Usuario obtenerUsuario(Long idUsuario) {
        return usuarioDao.findById(idUsuario).orElse(null);
    }

    @Override
    public Usuario login(long username, String contrasenia) {
        return usuarioDao.findByIdUsuarioAndContrasenia(username, contrasenia);
    }
    
}
