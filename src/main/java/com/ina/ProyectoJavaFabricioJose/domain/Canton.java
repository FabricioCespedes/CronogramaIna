/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "CANTONES")
public class Canton implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CANTON")
    private Long idCanton;

    @NotEmpty(message = "Por favor, ingrese el nombre de la provincia")
    @Column(name = "NOMBRE_CANTON")
    private String nombreCanton;

    //Relación de muchos cantones a una provincia
    @JoinColumn(name = "ID_PROVINCIA", nullable = false)
    @ManyToOne(optional = false)
    private Provincia provincia;

    //RelaciÃ³n de un cantÃ³n a muchos distritos
    @OneToMany(mappedBy = "canton")
    private List<Distrito> distritos;

}
