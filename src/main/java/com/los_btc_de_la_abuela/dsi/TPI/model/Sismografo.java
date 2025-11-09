package com.los_btc_de_la_abuela.dsi.TPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Sismógrafo - Instrumento para registrar movimientos sísmicos en una estación.
 */
@Entity
@Table(name = "sismografo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sismografo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Código único del sismógrafo
     */
    @Column(nullable = false, unique = true, length = 50)
    private String codigo;
    
    /**
     * Nombre o identificador del sismógrafo
     */
    @Column(nullable = false, length = 200)
    private String nombre;
    
    /**
     * Número de serie del equipo
     */
    @Column(name = "nro_serie", length = 100)
    private String nroSerie;
    
    /**
     * Modelo del sismógrafo
     */
    @Column(length = 100)
    private String modelo;
    
    /**
     * Fabricante del equipo
     */
    @Column(length = 100)
    private String fabricante;
    
    /**
     * Fecha de adquisición del equipo
     */
    @Column(name = "fecha_adquisicion")
    private LocalDate fechaAdquisicion;
    
    /**
     * Características técnicas del sismógrafo
     */
    @Column(length = 1000)
    private String caracteristicas;
    
    /**
     * Indica si el sismógrafo está operativo
     */
    @Column(nullable = false)
    private Boolean activo = true;
    
    /**
     * Estación a la que pertenece este sismógrafo
     */
    @ManyToOne
    @JoinColumn(name = "estacion_id", nullable = false)
    private EstacionSismologica estacion;
    
    /**
     * Series temporales registradas por este sismógrafo
     */
    @OneToMany(mappedBy = "sismografo", cascade = CascadeType.ALL)
    private List<SerieTemporal> seriesTemporales = new ArrayList<>();

    public boolean sosDeSerieTemporal(SerieTemporal serieTemporal) {
      return seriesTemporales.contains(serieTemporal);
    }

    public String buscarEstacionSismologica() {
      return this.estacion.getEstacion();
    }
}
