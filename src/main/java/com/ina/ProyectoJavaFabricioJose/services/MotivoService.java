/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.dao.IMotivoDao;
import com.ina.ProyectoJavaFabricioJose.domain.Motivo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotivoService implements IMotivoService {

    @Autowired
    private IMotivoDao motivoDao;

    @Override
    public int guardar(Motivo motivo) {
        int resultado = 1;
        try {
            motivoDao.save(motivo);
        } catch (Exception e) {
            resultado = 0;
        }
    
            return resultado;

    }

    @Override
    public int eliminar(Motivo motivo) {
        int resultado = 1;
        try {
            motivoDao.delete(motivo);
        } catch (Exception e) {
            resultado = 0;
        }

            return resultado;
        
    }

    @Override
    public List<Motivo> listar() {
        return motivoDao.findAll();
    }

    @Override
    public List<Motivo> listar(String justificacion) {
        return (List<Motivo>) motivoDao.findByJustificacionContains(justificacion);
    }

    @Override
    public Motivo obtenerMotivo(int idMotivo) {
        return motivoDao.getById(idMotivo);
    }

}
