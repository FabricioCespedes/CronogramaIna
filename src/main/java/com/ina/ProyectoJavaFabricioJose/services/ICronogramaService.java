/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.domain.*;
import java.sql.Time;
import java.util.Date;

import java.util.List;

public interface ICronogramaService {

    public List<Cronograma> listarCronogramas(Integer idPrograma);

    public Cronograma obtenerCronograma(Integer idCronograma);

    public String guardar(Integer idModulo, Integer idPrograma, Long idProfesor, double horasDia, String horaInicio, String horaFin, String estado, Integer idCentro, String fechaInicio);

    public int eliminar(Integer idCronograma);

    public int ingresarDias(boolean lunes, boolean martes, boolean miercoles, boolean jueves, boolean viernes, boolean sabado, int idModulo, int idPrograma);

    public Iterable<String> obtenerFechaInicio(int idPrograma);

    public List<String> listaPorModulos(int idPrograma, int idModulo);

    public int actualizar( int idPrograma);

}
