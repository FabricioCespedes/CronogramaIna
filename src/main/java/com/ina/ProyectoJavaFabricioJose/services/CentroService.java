/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.dao.ICentroDao;
import com.ina.ProyectoJavaFabricioJose.domain.Centro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CentroService implements ICentroService {
    
    @Autowired
    private ICentroDao centroDao;
    
    @Override
    public int guardar(Centro centro) {
        int result;
        try {
            centroDao.save(centro);
            result = 1;
        } catch (Exception e) {
            result = 0;
        }
        
        return result;
    }

    @Override
    public void eliminar(Integer idCentro) {
        centroDao.deleteById(idCentro);
    }

    @Override
    public List<Centro> listar() {
        return centroDao.findAll();
     }

    @Override
    public List<Centro> listar(String nombre) {
        return (List<Centro>) centroDao.findByNombreContains(nombre);
    }

    @Override
    public Centro obtenerCentro(int idCentro) {
        return centroDao.findById(idCentro).orElse(null);
    }

    @Override
    public List<Centro> verificarRuta(String ubicacion) {
        return (List<Centro>) centroDao.findByUbicacion(ubicacion);
    }

    @Override
    public List<Centro> verificarNombre(String nombre) {
        return (List<Centro>) centroDao.findByNombre(nombre);
    }
    
}
