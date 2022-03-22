/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.domain.Motivo;
import java.util.List;

/**
 *
 * @author josea
 */
public interface IMotivoService {
    public int guardar(Motivo motivo);
    public int eliminar(Motivo motivo);
    public List<Motivo> listar();
    public List<Motivo> listar(String justificacion);
    public Motivo obtenerMotivo(int idMotivo);
}
