package com.los_btc_de_la_abuela.dsi.TPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Representa el alcance geográfico o impacto espacial de un evento sísmico.
 * Indica qué tan extendida fue la percepción o efecto del sismo.
 */
@Entity
@Table(name = "alcance_sismo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlcanceSismo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Descripción del alcance (ej: "Local", "Regional", "Nacional", "Internacional")
     */
    @Column(nullable = false, length = 100)
    private String alcance;
    
    /**
     * Radio aproximado de percepción en kilómetros
     */
    @Column(name = "radio_km")
    private Double radioKm;
    
    /**
     * Descripción adicional del área afectada
     */
    @Column(length = 500)
    private String descripcion;
    
    /**
     * Fecha de adquisición/registro del dato
     */
    @Column(name = "fecha_hora_adquisicion")
    private LocalDateTime fechaHoraAdquisicion;
    
    @PrePersist
    protected void onCreate() {
        if (fechaHoraAdquisicion == null) {
            fechaHoraAdquisicion = LocalDateTime.now();
        }
    }
}
