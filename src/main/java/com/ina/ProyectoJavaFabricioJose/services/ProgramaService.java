/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.dao.IProgramaDao;
import com.ina.ProyectoJavaFabricioJose.domain.Programa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramaService implements IProgramaService{
    
    @Autowired
    private IProgramaDao programaDao;

    @Override
    public void guardar(Programa programa) {

            programaDao.save(programa);

    }

    @Override
    public void eliminar(Programa programa) {

            programaDao.delete(programa);

    }

    @Override
    public List<Programa> listar() {

            return programaDao.findAll();

    }

    @Override
    public List<Programa> listar(String nombrePrograma) {

            return (List<Programa>) programaDao.findByNombreProgramaContains(nombrePrograma);

    }

    @Override
    public Programa obtenerPrograma(Integer idPrograma) {

            return programaDao.findById(idPrograma).orElse(null);

    }

    @Override
    public List<Programa> listarProgramasConCronogramas() {
        return (List<Programa>) programaDao.listarProgrmasConCronogramas();
    }
    
}
