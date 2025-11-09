package com.los_btc_de_la_abuela.dsi.TPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Origen o causa de generación del evento sísmico.
 * Ejemplos: tectónico, volcánico, colapso, explosión, inducido, etc.
 */
@Entity
@Table(name = "origen_de_generacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrigenDeGeneracion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Nombre del origen (ej: "Tectónico", "Volcánico", "Antrópico")
     */
    @Column(nullable = false, length = 100)
    private String origen;
    
    /**
     * Descripción detallada del tipo de origen
     */
    @Column(length = 500)
    private String descripcion;
    
    /**
     * Indica si es un origen natural o inducido por actividad humana
     */
    @Column(name = "es_natural")
    private Boolean esNatural;
    
    /**
     * Fecha de registro/adquisición del dato
     */
    @Column(name = "fecha_hora_adquisicion")
    private LocalDateTime fechaHoraAdquisicion;
    
    @PrePersist
    protected void onCreate() {
        if (fechaHoraAdquisicion == null) {
            fechaHoraAdquisicion = LocalDateTime.now();
        }
        if (esNatural == null) {
            esNatural = true; // Por defecto asumimos origen natural
        }
    }
}
