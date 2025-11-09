package com.los_btc_de_la_abuela.dsi.TPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Detalle de una muestra sísmica - Contiene el valor medido de un tipo específico de dato.
 * Por ejemplo: velocidad en eje Z, aceleración en eje X, etc.
 */
@Entity
@Table(name = "detalle_muestra_sismica")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleMuestraSismica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Velocidad de la onda (si aplica)
     */
    @Column(name = "velocidad_onda")
    private Double velocidadOnda;
    
    /**
     * Frecuencia de la onda (si aplica)
     */
    @Column(name = "frecuencia_onda")
    private Double frecuenciaOnda;
    
    /**
     * Longitud de onda (si aplica)
     */
    @Column
    private Double longitud;
    
    /**
     * Muestra sísmica a la que pertenece este detalle
     */
    @ManyToOne
    @JoinColumn(name = "muestra_sismica_id", nullable = false)
    private MuestraSismica muestraSismica;
    
    /**
     * Tipo de dato que representa este detalle
     */
    @ManyToOne
    @JoinColumn(name = "tipo_dato_id", nullable = false)
    private TipoDato tipoDato;

    public boolean sosVelocidadLongitudFrecuencia() {
      return tipoDato.sosVelocidadLongitudFrecuencia();
    }
}
