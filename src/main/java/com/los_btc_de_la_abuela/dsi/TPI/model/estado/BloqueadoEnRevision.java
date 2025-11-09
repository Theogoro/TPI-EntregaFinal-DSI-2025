package com.los_btc_de_la_abuela.dsi.TPI.model.estado;

import com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum;
import com.los_btc_de_la_abuela.dsi.TPI.model.CambioEstado;
import com.los_btc_de_la_abuela.dsi.TPI.model.EventoSismico;

import java.time.LocalDateTime;

/**
 * Estado BloqueadoEnRevision - el evento está siendo revisado y bloqueado para otros usuarios.
 * Puede transicionar a: Confirmado, Rechazado, Derivado, Anulado.
 */
public class BloqueadoEnRevision extends EstadoEvSismico {

    public BloqueadoEnRevision() {
        super("BloqueadoEnRevision");
    }

}
