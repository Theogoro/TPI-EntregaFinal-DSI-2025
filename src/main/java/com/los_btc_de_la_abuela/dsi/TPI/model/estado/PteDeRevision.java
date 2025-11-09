package com.los_btc_de_la_abuela.dsi.TPI.model.estado;

import com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum;
import com.los_btc_de_la_abuela.dsi.TPI.model.CambioEstado;
import com.los_btc_de_la_abuela.dsi.TPI.model.EventoSismico;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Estado PteDeRevision - el evento está pendiente de revisión manual.
 * Puede transicionar a: BloqueadoEnRevision, Confirmado, Rechazado, Derivado, Anulado.
 */
public class PteDeRevision extends EstadoEvSismico {

    public PteDeRevision() {
        super("PteDeRevision");
    }

    @Override
    public void bloquear(EventoSismico eventoSismico, List<CambioEstado> cambiosEstado) {
        LocalDateTime ahora = this.getFechaHoraActual();
        
        // 1. Cerrar el cambio de estado actual
        cambiosEstado.stream()
            .filter(ce -> ce.sosActual())
            .forEach(ce -> ce.setFechaFin(ahora));
        
        // 2. Crear el nuevo cambio de estado "Bloqueado en Revisión"
        EstadoEvSismico estadoBloqueado = new BloqueadoEnRevision();
        CambioEstado nuevoCambio = this.crearCambioEstado(ahora, estadoBloqueado);
        nuevoCambio.setEventoSismico(eventoSismico); // ESTO PARA LA DB
        
        // 3. Agregar el nuevo cambio a la lista
        cambiosEstado.add(nuevoCambio);
        eventoSismico.setCambiosEstado(cambiosEstado);
        eventoSismico.setEstado(estadoBloqueado);
    }

    @Override
    public boolean sosPteRevision() {
      return true;
    }
}
