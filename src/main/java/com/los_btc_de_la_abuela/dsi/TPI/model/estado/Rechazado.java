package com.los_btc_de_la_abuela.dsi.TPI.model.estado;

import com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum;
import com.los_btc_de_la_abuela.dsi.TPI.model.CambioEstado;
import com.los_btc_de_la_abuela.dsi.TPI.model.EventoSismico;

import java.time.LocalDateTime;

/**
 * Estado Rechazado - el evento sísmico ha sido rechazado tras revisión.
 * Puede transicionar a: Cerrado, Anulado.
 */
public class Rechazado extends EstadoEvSismico {

    public Rechazado() {
        super("Rechazado");
    }
}
