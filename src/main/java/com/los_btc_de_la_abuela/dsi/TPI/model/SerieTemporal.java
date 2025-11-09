package com.los_btc_de_la_abuela.dsi.TPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.los_btc_de_la_abuela.dsi.TPI.dto.DatosSismografoDTO;
import com.los_btc_de_la_abuela.dsi.TPI.dto.VelocidadLongitudDeFrecuencia;

/**
 * Serie temporal de datos sísmicos registrados por un sismógrafo.
 * Contiene una secuencia de muestras sísmicas ordenadas temporalmente.
 */
@Entity
@Table(name = "serie_temporal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerieTemporal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Fecha y hora de inicio de la serie temporal
     */
    @Column(name = "fecha_hora_inicio", nullable = false)
    private LocalDateTime fechaHoraInicio;
    
    /**
     * Fecha y hora de adquisición/finalización de la serie
     */
    @Column(name = "fecha_hora_adquisicion")
    private LocalDateTime fechaHoraAdquisicion;
    
    /**
     * Frecuencia de muestreo en Hz
     */
    @Column(name = "frecuencia_muestreo")
    private Double frecuenciaMuestreo;
    
    /**
     * Sismógrafo que registró esta serie temporal
     */
    @ManyToOne
    @JoinColumn(name = "sismografo_id", nullable = false)
    private Sismografo sismografo;
    
    /**
     * Muestras sísmicas que componen esta serie temporal
     */
    @OneToMany(mappedBy = "serieTemporal", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("fechaHoraInicio ASC")
    private List<MuestraSismica> muestras = new ArrayList<>();

    @ManyToOne
    private EventoSismico eventoSismico;


    public DatosSismografoDTO buscarVelocidadLongitudFrecuencia() {
      List<VelocidadLongitudDeFrecuencia> resultados = new ArrayList<>();

      for (MuestraSismica muestra : muestras) {
          resultados.addAll(muestra.buscarVelocidadLongitudFrecuencia());
      }

      return new DatosSismografoDTO(this.sismografo.getId(), resultados);
    }
}
