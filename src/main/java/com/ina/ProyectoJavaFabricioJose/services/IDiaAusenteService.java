/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.domain.DiaAusente;
import java.util.List;



public interface IDiaAusenteService {
    
    public int guardar(DiaAusente diaAusente);
    public int eliminar(int id);
    public List<DiaAusente> listar();
    public List<DiaAusente> listar(Long idProfesor);
    public DiaAusente obtenerAusencia(Integer idDiaAusente);
}
