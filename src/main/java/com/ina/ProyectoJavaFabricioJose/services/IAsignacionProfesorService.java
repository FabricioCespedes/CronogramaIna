/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.domain.AsignacionProfesor;
import java.util.List;

/**
 *
 * @author Sergio
 */
public interface IAsignacionProfesorService {
    public List<AsignacionProfesor> listaPorModulos(int idModulo, int idPrograma);

}
