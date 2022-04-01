/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.dao.IDiaColectivoDao;
import com.ina.ProyectoJavaFabricioJose.domain.DiasAusentesColectivos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaColectivoService implements IDiaColectivoService {

    @Autowired
    private IDiaColectivoDao colectivoDao;

    @Override
    public int guardar(DiasAusentesColectivos colectivo) {
        int result;
        try {
            colectivoDao.save(colectivo);
            result = 1;
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }

    @Override
    public int eliminar(Integer idDia) {
        int result;
        try {
            colectivoDao.deleteById(idDia);
            result = 1;
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }

    @Override
    public List<DiasAusentesColectivos> listar() {
        return colectivoDao.findAll();
    }

    @Override
    public DiasAusentesColectivos obtenerColectivo(int dia) {
        return colectivoDao.findById(dia).orElse(null);
    }

    @Override
    public List<DiasAusentesColectivos> listar(String anio) {
        return (List<DiasAusentesColectivos>) colectivoDao.buscarPorAnio(anio);
    }

}
