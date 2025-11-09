package com.los_btc_de_la_abuela.dsi.TPI.model.estado;

import com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum;
import com.los_btc_de_la_abuela.dsi.TPI.model.CambioEstado;
import com.los_btc_de_la_abuela.dsi.TPI.model.EventoSismico;

import java.time.LocalDateTime;

/**
 * Estado Derivado - el evento ha sido derivado a otra entidad para su análisis.
 * Puede transicionar a: Confirmado, Rechazado, Anulado.
 */
public class Derivado extends EstadoEvSismico {

    public Derivado() {
        super("Derivado");
    }
    
}
