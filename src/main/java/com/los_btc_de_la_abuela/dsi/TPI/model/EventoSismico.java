package com.los_btc_de_la_abuela.dsi.TPI.model;

import com.los_btc_de_la_abuela.dsi.TPI.dto.DatosRegistradosDTO;
import com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum;
import com.los_btc_de_la_abuela.dsi.TPI.model.estado.EstadoEvSismico;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa un evento sísmico detectado.
 * Contiene información de ubicación, magnitud y el historial de cambios de estado.
 */
@Entity
@Table(name = "evento_sismico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoSismico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaHoraOcurrencia;

    @Column(nullable = false, length = 500)
    private String coordenadas;

    @Column(nullable = false)
    private Float valorMagnitud;

    /**
     * Estado actual del evento (persistido en BD)
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EstadoEnum estadoActual;

    /**
     * Objeto State pattern para manejar el comportamiento del estado (no persistido)
     */
    @Transient
    private EstadoEvSismico estadoEvSismico;

    // /**
    // TODO: REVISAR SI NECESITAMOS ESTO
    //  * Magnitud en escala Richter del evento
    //  */
    // @ManyToOne
    // @JoinColumn(name = "magnitud_richter_id")
    // private MagnitudRichter magnitudRichter;

    /**
     * Clasificación del evento sísmico
     */
    @ManyToOne
    @JoinColumn(name = "clasificacion_sismo_id")
    private ClasificacionSismo clasificacionSismo;

    /**
     * Origen de generación del evento
     */
    @ManyToOne
    @JoinColumn(name = "origen_generacion_id")
    private OrigenDeGeneracion origenGeneracion;

    /**
     * Alcance o impacto del evento sísmico
     */
    @ManyToOne
    @JoinColumn(name = "alcance_sismo_id")
    private AlcanceSismo alcanceSismo;

    @OneToMany(mappedBy = "eventoSismico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CambioEstado> cambiosEstado = new ArrayList<>();

    /**
     * Revisiones manuales realizadas sobre este evento
     */
    @OneToMany(mappedBy = "eventoSismico", cascade = CascadeType.ALL)
    private List<RevisionManual> revisionesManual = new ArrayList<>();

    @OneToMany(mappedBy = "eventoSismico", cascade = CascadeType.ALL)
    private List<SerieTemporal> serieTemporal = new ArrayList<>();

    /**
     * Inicializa el objeto State pattern basado en el estado actual.
     * Debe llamarse después de cargar la entidad de la BD.
     */
    @PostLoad
    private void initEstadoEvSismico() {
        if (estadoActual != null) {
            this.estadoEvSismico = EstadoEvSismico.fromEnum(estadoActual);
        }
    }

    /**
     * Bloquea el evento sísmico según las reglas del estado actual.
     */
    public void bloquear(Usuario usuarioActual) {
        if (this.estadoEvSismico == null) {
            initEstadoEvSismico();
        }
        this.estadoEvSismico.bloquear(this, this.cambiosEstado, usuarioActual);
    }

    /**
     * Establece el estado del evento (actualiza tanto el enum como el objeto State).
     */
    public void setEstado(EstadoEvSismico estadoNuevo) {
        this.estadoEvSismico = estadoNuevo;
        this.estadoActual = EstadoEvSismico.toEnum(estadoNuevo);
    }

    /**
     * Establece el estado actual por enum (actualiza ambos).
     */
    public void setEstadoActual(EstadoEnum estado) {
        this.estadoActual = estado;
        this.estadoEvSismico = EstadoEvSismico.fromEnum(estado);
    }

    public DatosRegistradosDTO obtenerDatosRegistrados() {
      return new DatosRegistradosDTO(
        this.clasificacionSismo.getClasificacion(),
        this.origenGeneracion.getOrigen(),
        this.alcanceSismo.getAlcance()
      );
    }

    public void rechazar(Usuario usuarioActual) {
        this.estadoEvSismico.rechazar(this, this.cambiosEstado, usuarioActual);
    }



    // /**
    //  * Bloquea el evento sísmico cambiando su estado.
    //  */
    // public void bloquear() {
    //     if (estadoEvSismico != null) {
    //         estadoEvSismico.bloquear();
    //     }
    // }

    // /**
    //  * Establece un nuevo cambio de estado y registra la transición.
    //  * @param cambioEstado el nuevo cambio de estado
    //  */
    // public void setCambioEstado(CambioEstado cambioEstado) {
    //     if (cambioEstado != null) {
    //         cambioEstado.setEventoSismico(this);
    //         this.cambiosEstado.add(cambioEstado);
    //         this.estadoActual = cambioEstado.getInicio();
    //     }
    // }

    // /**
    //  * Establece el estado actual del evento.
    //  * @param estado el nuevo estado
    //  */
    // public void setEstado(EstadoEnum estado) {
    //     this.estadoActual = estado;
    // }
}
