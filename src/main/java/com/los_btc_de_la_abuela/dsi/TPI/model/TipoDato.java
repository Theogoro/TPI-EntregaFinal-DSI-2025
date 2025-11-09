package com.los_btc_de_la_abuela.dsi.TPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Catálogo de tipos de datos que pueden registrarse en muestras sísmicas.
 * Ejemplos: velocidad, aceleración, desplazamiento, frecuencia, etc.
 */
@Entity
@Table(name = "tipo_dato")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoDato {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Nombre del tipo de dato (ej: "Velocidad", "Aceleración", "Desplazamiento")
     */
    @Column(nullable = false, length = 100, unique = true)
    private String nombre;
    
    /**
     * Unidad de medida (ej: "cm/s", "cm/s²", "Hz")
     */
    @Column(length = 50)
    private String unidad;
    
    /**
     * Descripción del tipo de dato
     */
    @Column(length = 500)
    private String descripcion;
    
    /**
     * Indica si este tipo de dato está activo
     */
    @Column(nullable = false)
    private Boolean activo = true;

    public boolean sosVelocidadLongitudFrecuencia() {
      return this.nombre.equalsIgnoreCase("Velocidad") ||
             this.nombre.equalsIgnoreCase("Longitud") ||
             this.nombre.equalsIgnoreCase("Frecuencia");
    }
}
