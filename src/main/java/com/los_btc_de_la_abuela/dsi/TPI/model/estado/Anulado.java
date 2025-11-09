package com.los_btc_de_la_abuela.dsi.TPI.model.estado;

/**
 * Estado Anulado - estado final del evento sísmico cuando se anula.
 * No permite transiciones a otros estados.
 */
public class Anulado extends EstadoEvSismico {

    public Anulado() {
        super("Anulado");
    }

}
