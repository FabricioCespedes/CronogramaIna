/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.domain.DiaFeriado;
import java.util.Date;
import java.util.List;

/**
 *
 * @author josea
 */
public interface IFeriadoService {
    public int guardar(DiaFeriado feriado);
    public int eliminar(Integer idDia);
    public List<DiaFeriado> listar();
    public List<DiaFeriado> listar(String anio);
    public DiaFeriado obtenerFeriado(int dia);
    public List<DiaFeriado> motivoRepetido(int idMotivo);
}
