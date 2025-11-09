package com.los_btc_de_la_abuela.dsi.TPI.model.estado;

import com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum;
import com.los_btc_de_la_abuela.dsi.TPI.model.CambioEstado;
import com.los_btc_de_la_abuela.dsi.TPI.model.EventoSismico;

import java.time.LocalDateTime;

/**
 * Estado Confirmado - el evento sísmico ha sido confirmado manualmente.
 * Puede transicionar a: PteDeCierre, Cerrado, Anulado.
 */
public class Confirmado extends EstadoEvSismico {

    public Confirmado() {
        super("Confirmado");
    }

}
