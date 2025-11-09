package com.los_btc_de_la_abuela.dsi.TPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Representa una revisión manual realizada sobre un evento sísmico.
 * Registra quién, cuándo y qué evento fue revisado manualmente.
 */
@Entity
@Table(name = "revision_manual")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevisionManual {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Fecha y hora en que se realizó la revisión
     */
    @Column(name = "fecha_hora_revision", nullable = false)
    private LocalDateTime fechaHoraRevision;
    
    /**
     * Evento sísmico que fue revisado
     */
    @ManyToOne
    @JoinColumn(name = "evento_sismico_id", nullable = false)
    private EventoSismico eventoSismico;
    
    /**
     * Empleado que realizó la revisión
     */
    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;
    
    /**
     * Sesión en la cual se realizó la revisión
     */
    @ManyToOne
    @JoinColumn(name = "sesion_id")
    private Sesion sesion;
    
    /**
     * Observaciones o comentarios del revisor
     */
    @Column(length = 2000)
    private String observaciones;
    
    /**
     * Indica si la revisión fue aprobada
     */
    @Column
    private Boolean aprobada;
    
    @PrePersist
    protected void onCreate() {
        if (fechaHoraRevision == null) {
            fechaHoraRevision = LocalDateTime.now();
        }
    }
}
