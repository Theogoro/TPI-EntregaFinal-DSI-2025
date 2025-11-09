package com.los_btc_de_la_abuela.dsi.TPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Clasificación de un evento sísmico según su profundidad y características.
 * Ejemplos: superficial, intermedio, profundo, volcánico, tectónico, etc.
 */
@Entity
@Table(name = "clasificacion_sismo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClasificacionSismo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Nombre de la clasificación (ej: "Superficial", "Profundo", "Volcánico")
     */
    @Column(nullable = false, length = 100)
    private String clasificacion;
    
    /**
     * Descripción detallada de esta clasificación
     */
    @Column(length = 500)
    private String descripcion;
    
    /**
     * Profundidad mínima en km para esta clasificación (opcional)
     */
    @Column(name = "profundidad_min_km")
    private Double profundidadMinKm;
    
    /**
     * Profundidad máxima en km para esta clasificación (opcional)
     */
    @Column(name = "profundidad_max_km")
    private Double profundidadMaxKm;
    
    /**
     * Fecha de creación del registro
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
