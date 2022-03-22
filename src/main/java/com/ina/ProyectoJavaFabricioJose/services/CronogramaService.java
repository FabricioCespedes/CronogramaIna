/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.dao.ICronogramaDao;
import com.ina.ProyectoJavaFabricioJose.domain.Cronograma;
import com.ina.ProyectoJavaFabricioJose.domain.Programa;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CronogramaService implements ICronogramaService{

    @Autowired
    private ICronogramaDao cronogramaDao;
   
    @Override
    public Cronograma generarCronograma(Cronograma cronograma) {

            int retorno = 0;
            HashMap resultado = cronogramaDao.generarCronograma(cronograma.getModuloCronograma().getIdModulo(), cronograma.getProgramaCronograma().getIdPrograma(), cronograma.getHorasDia(),
                    cronograma.getHorasInicio(), cronograma.getHoraFin(), cronograma.getEstado(), cronograma.getProgramaCronograma().getCentro().getIdCentro(), cronograma.getFechaInicio(), cronograma.getRetorno());                 
            cronograma.setFechaInicio((Date)resultado.get("FECHA_INICIO"));
            cronograma.setRetorno((Integer)resultado.get("retorno"));         
        return cronograma;
    }

    @Override
    public List<Cronograma> listarCronogramas(Programa programa) {
            List<Cronograma> lista = (List<Cronograma>) cronogramaDao.findByProgramaContains(programa);
            return lista;       
    }
    
    


    @Override
    public Cronograma obtenerCronograma(int idCronograma) {
        return cronogramaDao.findById(idCronograma).orElse(null);
    }

    
    
}
