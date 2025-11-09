package com.los_btc_de_la_abuela.dsi.TPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Representa la magnitud de un evento sísmico en la escala de Richter.
 * Incluye el valor de magnitud y metadata sobre cuándo/cómo fue calculada.
 */
@Entity
@Table(name = "magnitud_richter")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagnitudRichter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Valor de magnitud en escala Richter (ej: 4.5, 7.2)
     */
    @Column(nullable = false)
    private Double magnitud;
    
    /**
     * Descripción textual de la magnitud (ej: "Leve", "Moderado", "Fuerte")
     */
    @Column(length = 100)
    private String descripcion;
    
    /**
     * Fecha y hora de cálculo de la magnitud
     */
    @Column(name = "fecha_hora_calculo")
    private LocalDateTime fechaHoraCalculo;
    
    /**
     * Método usado para calcular la magnitud (ej: "ML", "Mw", "Ms")
     */
    @Column(length = 50)
    private String metodoCalculo;
    
    /**
     * Constructor para crear una magnitud con valor básico
     */
    public MagnitudRichter(Double magnitud) {
        this.magnitud = magnitud;
        this.fechaHoraCalculo = LocalDateTime.now();
        this.descripcion = obtenerDescripcion(magnitud);
    }
    
    /**
     * Obtiene descripción basada en el valor de magnitud
     */
    private String obtenerDescripcion(Double magnitud) {
        if (magnitud < 2.0) return "Micro";
        if (magnitud < 4.0) return "Menor";
        if (magnitud < 5.0) return "Ligero";
        if (magnitud < 6.0) return "Moderado";
        if (magnitud < 7.0) return "Fuerte";
        if (magnitud < 8.0) return "Mayor";
        return "Gran terremoto";
    }
}
