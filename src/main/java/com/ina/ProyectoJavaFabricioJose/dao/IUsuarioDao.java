/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.dao;

import com.ina.ProyectoJavaFabricioJose.domain.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IUsuarioDao extends JpaRepository<Usuario, Long> {
    
    public Iterable<Usuario> findByNombreContains(String nombre);
     
    public Usuario findByCedulaAndContrasenia(long idUsuario, String contrasenia);
    
    
}
