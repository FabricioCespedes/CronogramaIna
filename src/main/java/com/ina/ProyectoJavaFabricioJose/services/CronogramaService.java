/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.dao.ICronogramaDao;
import com.ina.ProyectoJavaFabricioJose.domain.Cronograma;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CronogramaService implements ICronogramaService {

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
    public int ingresarDias(boolean lunes, boolean martes, boolean miercoles, boolean jueves, boolean viernes, boolean sabado, int idModulo, int idPrograma) {
        int resultado = -1;
        try {
            resultado = cronogramaDao.ingresarDias(lunes, martes, miercoles, jueves, viernes, sabado, idModulo, idPrograma);
        } catch (Exception e) {
            resultado = 1;
        }

        return resultado;
    }

    @Override
    public String guardar(Integer idModulo, Integer idPrograma, Long idProfesor, double horasDia, String horaInicio, String horaFin, String estado, Integer idCentro, String fechaInicio) {
        String fechaInicioR;
        try {
            DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = fechaHora.parse(fechaInicio);
            return cronogramaDao.generarCronograma(idModulo, idPrograma, idProfesor, horasDia, horaInicio, horaFin, estado, idCentro, fecha);

        } catch (Exception e) {
            fechaInicioR = "0";
        }

        return fechaInicioR;

    }

    @Override
    public List<String> obtenerFechaInicio(int idPrograma) {
        try {
            return (List<String>) cronogramaDao.obtenerFechaInicio(idPrograma);

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<String> listaPorModulos(int idPrograma, int idModulo) {
        try {
            return (List<String>) cronogramaDao.listaPorModulos(idPrograma, idModulo);

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public int actualizar(int idPrograma) {
        try {
            return cronogramaDao.actualizar(idPrograma);

        } catch (Exception e) {
            return 1;
        }
    }

    @Override
    public List<Cronograma> listarProfesor(Integer idModulo, Integer idPrograma) {
        List<Cronograma> lista = (List<Cronograma>) cronogramaDao.listarProfesor(idModulo,idPrograma);
        return lista;    }

}
