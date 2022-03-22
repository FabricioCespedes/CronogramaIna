/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.dao.IModuloDao;
import com.ina.ProyectoJavaFabricioJose.domain.Modulo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuloService implements IModuloService {

    @Autowired
    private IModuloDao moduloDao;

    @Override
    public void guardar(Modulo modulo) {

            moduloDao.save(modulo);

    }

    @Override
    public void eliminar(Modulo modulo) {

            moduloDao.delete(modulo);

    }

    @Override
    public List<Modulo> listar() {

            return moduloDao.findAll();

    }

    @Override
    public List<Modulo> listar(String nombreModulo) {
            return (List<Modulo>) moduloDao.findByNombreModuloContains(nombreModulo);

    }

    @Override
    public Modulo obtenerModulo(Integer idModulo) {
            return moduloDao.findById(idModulo).orElse(null);

    }

}
