package com.los_btc_de_la_abuela.dsi.TPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.los_btc_de_la_abuela.dsi.TPI.dto.VelocidadLongitudDeFrecuencia;

/**
 * Muestra sísmica - Punto de dato individual en una serie temporal.
 * Representa un registro de medición en un momento específico.
 */
@Entity
@Table(name = "muestra_sismica")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MuestraSismica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Fecha y hora de inicio de la muestra
     */
    @Column(name = "fecha_hora_inicio", nullable = false)
    private LocalDateTime fechaHoraInicio;
    
    /**
     * Fecha y hora de fin de la muestra
     */
    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;
    
    /**
     * Fecha y hora de adquisición de la muestra
     */
    @Column(name = "fecha_hora_inicio_fk")
    private LocalDateTime fechaHoraInicioFk;
    
    /**
     * Serie temporal a la que pertenece esta muestra
     */
    @ManyToOne
    @JoinColumn(name = "serie_temporal_id", nullable = false)
    private SerieTemporal serieTemporal;
    
    /**
     * Detalles de esta muestra (puede contener múltiples componentes o canales)
     */
    @OneToMany(mappedBy = "muestraSismica", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleMuestraSismica> detalles = new ArrayList<>();
    
    /**
     * Calcula la duración de la muestra en milisegundos
     */
    public Long getDuracionMillis() {
        if (fechaHoraInicio != null && fechaHoraFin != null) {
            return java.time.Duration.between(fechaHoraInicio, fechaHoraFin).toMillis();
        }
        return null;
    }

    public List<VelocidadLongitudDeFrecuencia> buscarVelocidadLongitudFrecuencia() {
      List<VelocidadLongitudDeFrecuencia> resultados = new ArrayList<>();
      for (DetalleMuestraSismica detalle : detalles) {
          if (detalle.sosVelocidadLongitudFrecuencia()) {
              resultados.add(new VelocidadLongitudDeFrecuencia(
                  detalle.getLongitud(),
                  detalle.getFrecuenciaOnda(),
                  detalle.getVelocidadOnda()
              ));
          }
      }
      return resultados;
  }
}
