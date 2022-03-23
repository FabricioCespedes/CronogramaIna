/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.dao.IDistritoDao;
import com.ina.ProyectoJavaFabricioJose.domain.Distrito;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistritoService implements IDistritoService {
    
    @Autowired
    private IDistritoDao distritoDao;
    
    @Override
    public List<Distrito> listar() {
        return distritoDao.findAll();
    }

    @Override
    public List<Distrito> listar(String nombreDis) {
        return (List<Distrito>) distritoDao.findByNombreDistritoContains(nombreDis);
    }
    
}
