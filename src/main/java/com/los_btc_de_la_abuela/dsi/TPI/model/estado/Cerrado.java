package com.los_btc_de_la_abuela.dsi.TPI.model.estado;

import com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum;
import com.los_btc_de_la_abuela.dsi.TPI.model.CambioEstado;
import com.los_btc_de_la_abuela.dsi.TPI.model.EventoSismico;

import java.time.LocalDateTime;

/**
 * Estado Cerrado - estado final del evento sísmico.
 * No permite transiciones a otros estados.
 */
public class Cerrado extends EstadoEvSismico {

    public Cerrado() {
        super("Cerrado");
    }

}
