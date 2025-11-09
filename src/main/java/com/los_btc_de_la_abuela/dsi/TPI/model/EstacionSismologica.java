package com.los_btc_de_la_abuela.dsi.TPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import com.los_btc_de_la_abuela.dsi.TPI.dto.VelocidadLongitudDeFrecuencia;

/**
 * Estación sismológica que contiene uno o más sismógrafos para registrar eventos sísmicos.
 */
@Entity
@Table(name = "estacion_sismologica")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstacionSismologica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Código único de la estación
     */
    @Column(nullable = false, unique = true, length = 50)
    private String codigo;
    
    /**
     * Nombre de la estación
     */
    @Column(nullable = false, length = 200)
    private String nombre;
    
    /**
     * Latitud de la ubicación de la estación
     */
    @Column(nullable = false)
    private Double latitud;
    
    /**
     * Longitud de la ubicación de la estación
     */
    @Column(nullable = false)
    private Double longitud;
    
    /**
     * Altitud en metros sobre el nivel del mar
     */
    @Column
    private Double altitud;
    
    /**
     * Indica si la estación está activa y operando
     */
    @Column(nullable = false)
    private Boolean activa = true;
    
    /**
     * Sismógrafos instalados en esta estación
     */
    @OneToMany(mappedBy = "estacion", cascade = CascadeType.ALL)
    private List<Sismografo> sismografos = new ArrayList<>();
    
    /**
     * Obtiene la ubicación como string
     */
    public String getUbicacion() {
        return String.format("Lat: %.4f, Lon: %.4f", latitud, longitud);
    }

    public String getEstacion() {
      return this.nombre;
    }
}
