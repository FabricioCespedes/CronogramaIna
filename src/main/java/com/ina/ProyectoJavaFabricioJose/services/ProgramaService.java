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
public class ProgramaService implements IProgramaService {

    @Autowired
    private IProgramaDao programaDao;

    @Override
    public int guardar(Programa programa) {
        int result;
        try {
            programaDao.save(programa);
            result = 1;
        } catch (Exception e) {
            result = 0;
        }
        return result;

    }

    @Override
    public int eliminar(Programa programa) {
        int result;
        try {
            programaDao.delete(programa);
            result = 1;
        } catch (Exception e) {
            result = 0;
        }
        return result;
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

    @Override
    public List<Programa> listarProgramasSinCronogramas() {
        return (List<Programa>) programaDao.listarProgrmasSinCronogramas();
    }

}
