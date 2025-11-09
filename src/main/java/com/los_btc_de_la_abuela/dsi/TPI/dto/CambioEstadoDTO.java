package com.los_btc_de_la_abuela.dsi.TPI.dto;

import com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO para solicitar un cambio de estado de un evento sísmico.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CambioEstadoDTO {
    
    private EstadoEnum inicio;
    private EstadoEnum fin;
    private Long eventoSismicoId;
}
