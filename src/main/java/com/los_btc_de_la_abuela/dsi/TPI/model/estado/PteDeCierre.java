package com.los_btc_de_la_abuela.dsi.TPI.model.estado;

import com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum;
import com.los_btc_de_la_abuela.dsi.TPI.model.CambioEstado;
import com.los_btc_de_la_abuela.dsi.TPI.model.EventoSismico;

import java.time.LocalDateTime;

/**
 * Estado PteDeCierre - el evento está pendiente de cierre.
 * Puede transicionar a: Cerrado.
 */
public class PteDeCierre extends EstadoEvSismico {

    public PteDeCierre() {
        super("PteDeCierre");
    }
}
