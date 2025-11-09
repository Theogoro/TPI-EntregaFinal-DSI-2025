package com.los_btc_de_la_abuela.dsi.TPI.model.estado;

import com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum;
import com.los_btc_de_la_abuela.dsi.TPI.model.CambioEstado;
import com.los_btc_de_la_abuela.dsi.TPI.model.EventoSismico;
import com.los_btc_de_la_abuela.dsi.TPI.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Estado BloqueadoEnRevision - el evento está siendo revisado y bloqueado para otros usuarios.
 * Puede transicionar a: Confirmado, Rechazado, Derivado, Anulado.
 */
public class BloqueadoEnRevision extends EstadoEvSismico {

    public BloqueadoEnRevision() {
        super("BloqueadoEnRevision");
    }

    @Override
    public void rechazar(EventoSismico eventoSismico, List<CambioEstado> cambiosEstado, Usuario usuarioActual) {
        LocalDateTime ahora = this.getFechaHoraActual();
        // 1. Cerrar el cambio de estado actual
        cambiosEstado.stream()
            .filter(ce -> ce.sosActual())
            .forEach(ce -> ce.setFechaFin(ahora));

        // 2. Crear el nuevo cambio de estado "Rechazado"
        EstadoEvSismico estadoRechazado = new Rechazado();
        CambioEstado nuevoCambio = this.crearCambioEstado(ahora, estadoRechazado, usuarioActual);
        nuevoCambio.setEventoSismico(eventoSismico);

        // 3. Agregar el nuevo cambio a la lista
        cambiosEstado.add(nuevoCambio);
        eventoSismico.setCambiosEstado(cambiosEstado);
        eventoSismico.setEstado(estadoRechazado);
    }
}
