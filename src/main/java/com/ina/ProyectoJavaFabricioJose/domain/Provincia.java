package com.ina.ProyectoJavaFabricioJose.domain;

import com.ina.ProyectoJavaFabricioJose.domain.Canton;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "PROVINCIAS")
public class Provincia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROVINCIA")
    private Long idProvincia;

    @NotEmpty(message = "Por favor, ingrese el nombre de la provincia")
    @Column(name = "NOMBRE_PROVINCIA")
    private String nombreProvincia;
    
    //Relación de una provincia a muchos cantones
    @OneToMany(mappedBy = "provincia")
    private List<Canton> cantones;

}
