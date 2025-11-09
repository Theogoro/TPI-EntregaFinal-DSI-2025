package com.los_btc_de_la_abuela.dsi.TPI.service;

import com.los_btc_de_la_abuela.dsi.TPI.dto.EventoSismicoDTO;
import com.los_btc_de_la_abuela.dsi.TPI.model.CambioEstado;
import com.los_btc_de_la_abuela.dsi.TPI.model.EventoSismico;
import com.los_btc_de_la_abuela.dsi.TPI.repository.EventoSismicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para gestionar eventos sísmicos y sus transiciones de estado.
 * Implementa la lógica de negocio para el manejo del patrón State.
 */
@Service
@Transactional
public class EventoSismicoService {

    @Autowired
    private EventoSismicoRepository eventoSismicoRepository;
  
    public List<EventoSismicoDTO> buscarEventosSinRevision() {
        List<EventoSismico> eventos = eventoSismicoRepository.getAllEventos();
        List<EventoSismicoDTO> eventosPendientes = new ArrayList<EventoSismicoDTO>();
        for (EventoSismico evento : eventos) {
            CambioEstado cambioActual = null;
            for (CambioEstado cambio : evento.getCambiosEstado()) {
                if (cambio.sosActual()) {
                    cambioActual = cambio;
                    break;
                }
            }
            
            if (cambioActual != null && cambioActual.sosPteRevision()) {
                LocalDateTime fechaHoraOcurrencia = evento.getFechaHoraOcurrencia();
                Float valorMagnitud = evento.getValorMagnitud();
                String coordenadas = evento.getCoordenadas();
                eventosPendientes.add(new EventoSismicoDTO(
                    evento.getId(),
                    fechaHoraOcurrencia,
                    valorMagnitud,
                    coordenadas
                ));
            }

        }

        return eventosPendientes;
    }

    public EventoSismico findById(Long eventoId) {
      return eventoSismicoRepository.findById(eventoId).orElseThrow(() -> new IllegalArgumentException("Evento no encontrado: " + eventoId));
    }

    public void save(EventoSismico evento) {
      eventoSismicoRepository.save(evento);
    }
}
