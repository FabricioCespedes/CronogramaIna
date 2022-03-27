/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.dao.IDiaAusenteDao;
import com.ina.ProyectoJavaFabricioJose.domain.DiaAusente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaAusenteService implements IDiaAusenteService {

    @Autowired
    private IDiaAusenteDao diaAusenteDao;

    @Override
    public int guardar(DiaAusente diaAusente) {
        int resultado = 1;
        
        try {
            diaAusenteDao.save(diaAusente);
        } catch (Exception e) {
            resultado = 0;
        }

        return resultado;
    }

    @Override
    public int eliminar(int idDiaAusente) {
         int resultado = 1;
        try {
            diaAusenteDao.deleteById(idDiaAusente);
        } catch (Exception e) {
            resultado = 0;
        }

        return resultado;
    }

    @Override
    public List<DiaAusente> listar() {
       return  diaAusenteDao.findAll();
    }

    @Override
    public List<DiaAusente> listar(Long idProfesor) {
        return (List<DiaAusente>) diaAusenteDao.filtrarPorProfesor(idProfesor);
    }

    @Override
    public DiaAusente obtenerAusencia(Integer idDiaAusente) {
        return diaAusenteDao.findById(idDiaAusente).orElse(null);
    }

}
