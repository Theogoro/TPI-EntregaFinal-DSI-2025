package com.los_btc_de_la_abuela.dsi.TPI.dto;

import com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum;
import java.time.LocalDateTime;

/**
 * DTO completo para transferir información de eventos sísmicos.
 * Incluye todos los detalles relevantes incluyendo el estado actual.
 * Utilizado para mostrar información detallada de eventos en listados y vistas.
 */
public record EventoSismicoDTO(
    Long id,
    LocalDateTime fechaHoraOcurrencia,
    String coordenadas,
    Float valorMagnitud,
    EstadoEnum estadoActual,
    String estadoDescripcion,
    String clasificacion,
    String origen,
    String alcance,
    Double radioAlcanceKm,
    LocalDateTime fechaUltimoCambioEstado
) {
    
    /**
     * Constructor simplificado para casos donde no se tiene toda la información
     */
    public EventoSismicoDTO(
        Long id,
        LocalDateTime fechaHoraOcurrencia,
        String coordenadas,
        Float valorMagnitud,
        EstadoEnum estadoActual
    ) {
        this(id, fechaHoraOcurrencia, coordenadas, valorMagnitud, estadoActual,
             null, null, null, null, null, null);
    }
    
    /**
     * Obtiene una descripción legible del estado
     */
    public String getEstadoTexto() {
        if (estadoDescripcion != null) {
            return estadoDescripcion;
        }
        return estadoActual != null ? estadoActual.getNombre() : "Sin estado";
    }
    
    /**
     * Verifica si el evento está pendiente de revisión
     */
    public boolean isPendienteRevision() {
        return estadoActual == EstadoEnum.PTE_DE_REVISION;
    }
    
    /**
     * Verifica si el evento fue auto-detectado
     */
    public boolean isAutoDetectado() {
        return estadoActual == EstadoEnum.AUTO_DETECTADO;
    }
    
    /**
     * Verifica si el evento está confirmado
     */
    public boolean isConfirmado() {
        return estadoActual == EstadoEnum.CONFIRMADO;
    }
    
    /**
     * Crea un DTO desde la entidad EventoSismico
     */
    public static EventoSismicoDTO fromEntity(com.los_btc_de_la_abuela.dsi.TPI.model.EventoSismico evento) {
        if (evento == null) {
            return null;
        }
        
        // Obtener la fecha del último cambio de estado
        LocalDateTime fechaUltimoCambio = evento.getCambiosEstado() != null && !evento.getCambiosEstado().isEmpty()
            ? evento.getCambiosEstado().get(evento.getCambiosEstado().size() - 1).getFechaInicio()
            : null;
        
        return new EventoSismicoDTO(
            evento.getId(),
            evento.getFechaHoraOcurrencia(),
            evento.getCoordenadas(),
            evento.getValorMagnitud(),
            evento.getEstadoActual(),
            evento.getEstadoActual() != null ? evento.getEstadoActual().getNombre() : null,
            evento.getClasificacionSismo() != null ? evento.getClasificacionSismo().getClasificacion() : null,
            evento.getOrigenGeneracion() != null ? evento.getOrigenGeneracion().getOrigen() : null,
            evento.getAlcanceSismo() != null ? evento.getAlcanceSismo().getAlcance() : null,
            evento.getAlcanceSismo() != null ? evento.getAlcanceSismo().getRadioKm() : null,
            fechaUltimoCambio
        );
    }
}
