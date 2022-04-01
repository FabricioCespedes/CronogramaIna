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
    @JoinColumn(name = "ID_PROGRAMA", nullable = false)
    @ManyToOne(optional = false)
    private Programa programa;

    @Id
    @JoinColumn(name = "ID_MODULO", nullable = false)
    @ManyToOne(optional = false)
    private Modulo modulo;

    @Id
    @JoinColumn(name = "ID_DIA", nullable = false)
    @ManyToOne(optional = false)
    private Dia diaC;

}
