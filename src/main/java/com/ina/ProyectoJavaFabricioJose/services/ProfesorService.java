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
    public int guardar(Profesor profesor) {
        int resultado = 1;
        try {
            profesorDao.save(profesor);
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }

    @Override
    public int eliminar(Profesor profesor) {
        int resultado = 1;
        try {
            profesorDao.deleteById(profesor.getIdProfesor());
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }

    @Override
    public List<Profesor> listar(int idCentro) {

        return (List<Profesor>) profesorDao.buscarProfesor(idCentro);

    }

    @Override
    public List<Profesor> listar(String nombre, int idCentro) {

        return (List<Profesor>) profesorDao.buscarProfesor(nombre, idCentro);

    }

    @Override
    public Profesor obtenerProfesor(Long idProfesor) {

        return profesorDao.findById(idProfesor).orElse(null);

    }
    
    @Override
    public List<Profesor> listar() {
 
        return profesorDao.findAll();
 
    }

}
