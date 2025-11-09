package com.los_btc_de_la_abuela.dsi.TPI.model.estado;

import com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum;
import com.los_btc_de_la_abuela.dsi.TPI.model.CambioEstado;
import com.los_btc_de_la_abuela.dsi.TPI.model.EventoSismico;

import java.time.LocalDateTime;

/**
 * Estado AutoDetectado - el evento fue detectado automáticamente por el sistema.
 * Estado inicial de un evento. Puede transicionar a: PteDeRevision, AutoConfirmado, Anulado.
 */
public class AutoDetectado extends EstadoEvSismico {

    public AutoDetectado() {
        super("AutoDetectado");
    }
}
