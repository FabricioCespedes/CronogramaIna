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
    public int guardar(Modulo modulo) {
        int result;
        try {
            moduloDao.save(modulo);
            result = 1;
        } catch (Exception e) {
            result = 0;
        }
        return result;
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
    public List<Modulo> listar(String codigo) {
            return (List<Modulo>) moduloDao.findByCodigoContains(codigo);

    }

    @Override
    public Modulo obtenerModulo(Integer idModulo) {
            return moduloDao.findById(idModulo).orElse(null);

    }

}
