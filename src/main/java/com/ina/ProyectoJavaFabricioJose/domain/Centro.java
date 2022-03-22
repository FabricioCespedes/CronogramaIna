
package com.ina.ProyectoJavaFabricioJose.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "CENTROS")
public class Centro implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CENTRO")
    private int idCentro;
    
    @Column(name="NOMBRE", unique = true)
    @NotEmpty(message = "El nombre del centro es obligatorio")
    private String nombre;
    
    @Column(name="UBICACION", unique = true)
    @NotEmpty(message = "La ubicación del centro es obligatoria")
    private String ubicacion;
    
    //Relación de muchos centros a un distrito
    @JoinColumn(name = "ID_DISTRITO", nullable = false)
    @ManyToOne(optional = false)
    private Distrito distrito;
    
    //Relación de un centro a muchos profesores
    @OneToMany(mappedBy = "centro")
    private List<Profesor> profesores; 
    
    //Relación de un centro a un adminitrador
    @OneToMany(mappedBy = "centro")
    private List<Usuario> usuario;
    
    //Relación de un centro a muchos programas
    @OneToMany(mappedBy = "centro")
    private List<Programa> programa;
    
    //Relación de un centro a muchos días colectivos
    @OneToMany(mappedBy = "centro")
    private List<DiasAusentesColectivos> diaColectivo;
}
