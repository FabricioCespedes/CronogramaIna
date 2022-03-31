/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.dao.IAsignacionProfesorDao;
import com.ina.ProyectoJavaFabricioJose.domain.AsignacionProfesor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sergio
 */
@Service
public class AsignacionProfesorService implements IAsignacionProfesorService {

    private IAsignacionProfesorDao asignacionProfesorDao;

    @Override
    public List<AsignacionProfesor> listaPorModulos(int idModulo, int idPrograma) {

        try {
            return (List<AsignacionProfesor>) asignacionProfesorDao.listaPorModulos(idModulo, idPrograma);
        } catch (Exception e) {
            return null;
        }

    }

}
