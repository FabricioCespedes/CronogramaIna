package com.ina.ProyectoJavaFabricioJose.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CRONOGRAMAS_DIAS")
public class CronogramasDias implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JoinColumn(name = "ID_CRONOGRAMA", nullable = false)
    @ManyToOne(optional = false)
    private Cronograma cronograma; 
        
    @Id
    @JoinColumn(name = "ID_DIA", nullable = false)
    @ManyToOne(optional = false)
    private Dia diaC; 

}
