package com.los_btc_de_la_abuela.dsi.TPI.dto;

import java.time.LocalDateTime;

/**
 * DTO para transferir información básica de eventos sísmicos.
 * Utilizado principalmente para listar eventos pendientes de revisión.
 */
public record EventoSismicoDTO(
    Long id,
    LocalDateTime fechaHoraOcurrencia,
    Float valorMagnitud,
    String coordenadas
) {
    // make props read-only
    public Long getId() {
        return id;
    }
    public LocalDateTime getFechaHoraOcurrencia() {
        return fechaHoraOcurrencia;
    }
    public Float getValorMagnitud() {
        return valorMagnitud;
    }
}
