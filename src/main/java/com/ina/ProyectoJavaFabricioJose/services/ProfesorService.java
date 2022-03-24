/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.dao.IProfesorDao;
import com.ina.ProyectoJavaFabricioJose.domain.Profesor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorService implements IProfesorService {

    @Autowired
    private IProfesorDao profesorDao;

    @Override
    public void guardar(Profesor profesor) {
        profesorDao.save(profesor);
    }

    @Override
    public void eliminar(Profesor profesor) {

        profesorDao.deleteById(profesor.getIdProfesor());

    }

    @Override
    public List<Profesor> listar() {

        return profesorDao.findAll();

    }

    @Override
    public List<Profesor> listar(String nombre) {

        return (List<Profesor>) profesorDao.findByNombreContains(nombre);

    }

    @Override
    public Profesor obtenerProfesor(Long idProfesor) {

        return profesorDao.findById(idProfesor).orElse(null);

    }

}
