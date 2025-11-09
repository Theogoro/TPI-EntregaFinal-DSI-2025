package com.los_btc_de_la_abuela.dsi.TPI.model.estado;

import com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum;
import com.los_btc_de_la_abuela.dsi.TPI.model.CambioEstado;
import com.los_btc_de_la_abuela.dsi.TPI.model.EventoSismico;

import java.time.LocalDateTime;

/**
 * Estado AutoConfirmado - el evento fue confirmado automáticamente por el sistema.
 * Puede transicionar a: PteDeCierre, Cerrado, Anulado.
 */
public class AutoConfirmado extends EstadoEvSismico {

    public AutoConfirmado() {
        super("AutoConfirmado");
    }
}
