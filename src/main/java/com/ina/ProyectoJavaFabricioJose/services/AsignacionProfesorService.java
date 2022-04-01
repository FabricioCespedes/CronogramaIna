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

    @Autowired
    private IAsignacionProfesorDao asignacionProfesorDao;

    @Override
    public List<AsignacionProfesor> listar(int idModulo, int idPrograma) {
            return (List<AsignacionProfesor>) asignacionProfesorDao.listaPorModulos(idModulo, idPrograma);

//        try {
//        } catch (Exception e) {
//            return null;
//        }

    }

    @Override
    public int guardar(AsignacionProfesor asignacionProfesor) {
        int resultado = 1;
        try {
            asignacionProfesorDao.save(asignacionProfesor);
        } catch (Exception e) {
            resultado = 0;
        }

        return resultado;    }

    @Override
    public int eliminar(Integer idAsignacionProfesor) {
        int resultado = 1;
        try {
            asignacionProfesorDao.deleteById(idAsignacionProfesor);
        } catch (Exception e) {
            resultado = 0;
        }

        return resultado;    }

    @Override
    public AsignacionProfesor obtener(Integer idAsignacionProfesor) {
        return asignacionProfesorDao.getById(idAsignacionProfesor);
    }

    @Override
    public List<AsignacionProfesor> listar(Long idProfesor) {
        return (List<AsignacionProfesor>) asignacionProfesorDao.listaPorProfesor(idProfesor);
    }

}
