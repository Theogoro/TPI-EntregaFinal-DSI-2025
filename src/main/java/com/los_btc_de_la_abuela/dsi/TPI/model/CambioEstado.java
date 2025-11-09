package com.los_btc_de_la_abuela.dsi.TPI.model;

import com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum;
import com.los_btc_de_la_abuela.dsi.TPI.model.estado.EstadoEvSismico;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entidad que representa un cambio de estado de un evento sísmico.
 * Registra el historial de transiciones entre estados.
 */
@Entity
@Table(name = "cambio_estado")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CambioEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 50)
    private EstadoEnum estado;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_sismico_id", nullable = false)
    private EventoSismico eventoSismico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario responsable;


    /**
     * Constructor para crear un nuevo cambio de estado desde el objeto State.
     * @param estadoObj el objeto estado del patrón State
     * @param fechaInicio la fecha y hora de inicio del estado
     */
    public CambioEstado(EstadoEvSismico estadoObj, LocalDateTime fechaInicio, Usuario responsable) {
        this.estado = EstadoEvSismico.toEnum(estadoObj);
        this.fechaInicio = fechaInicio;
        this.responsable = responsable;
    }

    /**
     * Constructor para crear un nuevo cambio de estado desde el enum.
     * @param estado el enum del estado
     * @param fechaInicio la fecha y hora de inicio del estado
     */
    public CambioEstado(EstadoEnum estado, LocalDateTime fechaInicio, Usuario responsable) {
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.responsable = responsable;
    }

    /**
     * Constructor solo con fecha de inicio (el estado se establece después).
     * @param fechaInicio la fecha y hora de inicio del estado
     */
    public CambioEstado(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Verifica si este cambio de estado es el actual (no tiene fecha fin).
     * @return true si es el estado actual
     */
    public boolean sosActual() {
        return this.fechaFin == null;
    }

    /**
     * Verifica si este cambio de estado es "Pendiente de Revisión".
     * @return true si el estado es PTE_DE_REVISION
     */
    public boolean sosPteRevision() {
        return this.estado.sosPteRevision();
    }

    public void setFechaHoraFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }
}
