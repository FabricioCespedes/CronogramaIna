/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.dao.ICronogramaDao;
import com.ina.ProyectoJavaFabricioJose.domain.Cronograma;
import com.ina.ProyectoJavaFabricioJose.domain.Programa;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CronogramaService implements ICronogramaService{

    @Autowired
    private ICronogramaDao cronogramaDao;
   

    @Override
    public List<Cronograma> listarCronogramas(Integer idPrograma) {
            List<Cronograma> lista = (List<Cronograma>) cronogramaDao.listar(idPrograma);
            return lista;       
    }
    
    


    @Override
    public Cronograma obtenerCronograma(Integer idCronograma) {
        return cronogramaDao.findById(idCronograma).orElse(null);
    }




    @Override
    public int eliminar(Integer idCronograma) {
        int resultado = 1;
        try {
            cronogramaDao.deleteById(idCronograma);
        } catch (Exception e) {
            resultado = 0;
        }

        return resultado;
    }

    @Override
    public int ingresarDias(boolean lunes, boolean martes, boolean miercoles, boolean jueves, boolean viernes, boolean sabado, Integer idModulo, Integer idPrograma, int retorno) {
        int resultado = -1;
        try {
           resultado = cronogramaDao.ingresarDias((lunes ? 1 : 0), (martes ? 1 : 0), (miercoles ? 1 : 0), (jueves ? 1 : 0), (viernes ? 1 : 0), (sabado ? 1 : 0), idModulo, idPrograma, retorno);
        } catch (Exception e) {
            resultado = 1;
        }

        return resultado;
    }

    @Override
    public String guardar(Integer idModulo, Integer idPrograma, Long idProfesor, double horasDia, String horaInicio, String horaFin, String estado, Integer idCentro, Date fechaInicio) {
        String fechaInicioR;
        try {
           return cronogramaDao.generarCronograma(idModulo, idPrograma, horasDia, horaInicio, horaFin, estado, idCentro, fechaInicio);
                   
        } catch (Exception e) {
          fechaInicioR ="0";
        }

        return fechaInicioR;
        
    }
    
    
}
