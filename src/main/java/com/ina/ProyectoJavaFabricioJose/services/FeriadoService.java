/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.domain.DiaFeriado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ina.ProyectoJavaFabricioJose.dao.IFeriadoDao;
import java.util.Date;

@Service
public class FeriadoService implements IFeriadoService {

    @Autowired
    private IFeriadoDao feriadoDao;

    @Override
    public int guardar(DiaFeriado colectivo) {
        int resultado = 1;
        try {
            feriadoDao.save(colectivo);
        } catch (Exception e) {
            resultado = 0;
        }

        return resultado;
    }

    @Override
    public int eliminar(Integer idDia) {
        int resultado = 1;
        try {
            feriadoDao.deleteById(idDia);
        } catch (Exception e) {
            resultado = 0;
        }

        return resultado;
    }

    @Override
    public List<DiaFeriado> listar() {
        return feriadoDao.findAll();
    }

    @Override
    public List<DiaFeriado> listar(String anio) {
        return (List<DiaFeriado>) feriadoDao.buscarPorAnio(anio);
    }

    @Override
    public DiaFeriado obtenerFeriado(int idDia) {
        return feriadoDao.getById(idDia);
    }
    
    @Override
    public List<DiaFeriado> motivoRepetido(int idMotivo) {
        return  (List<DiaFeriado>) feriadoDao.motivoRepetido(idMotivo);
    }
    


}
