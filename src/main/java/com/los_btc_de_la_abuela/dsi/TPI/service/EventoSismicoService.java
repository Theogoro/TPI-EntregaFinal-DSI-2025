package com.los_btc_de_la_abuela.dsi.TPI.service;

import com.los_btc_de_la_abuela.dsi.TPI.dto.EventoSismicoDTO;
import com.los_btc_de_la_abuela.dsi.TPI.model.CambioEstado;
import com.los_btc_de_la_abuela.dsi.TPI.model.EventoSismico;
import com.los_btc_de_la_abuela.dsi.TPI.repository.CambioEstadoRepository;
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

    @Autowired
    private CambioEstadoRepository cambioEstadoRepository;

    // /**
    //  * Crea un nuevo evento sísmico en estado AutoDetectado.
    //  */
    // public EventoSismico crearEventoSismico(LocalDateTime fechaHora, String coordenadas, Float magnitud) {
    //     EventoSismico evento = new EventoSismico();
    //     evento.setFechaHoraOcurrencia(fechaHora);
    //     evento.setCoordenadas(coordenadas);
    //     evento.setValorMagnitud(magnitud);
    //     evento.setEstadoActual(EstadoEnum.AUTO_DETECTADO);

    //     // Crear el primer cambio de estado
    //     CambioEstado cambioInicial = new CambioEstado(EstadoEnum.AUTO_DETECTADO, null);
    //     evento.setCambioEstado(cambioInicial);

    //     return eventoSismicoRepository.save(evento);
    // }

    // /**
    //  * Obtiene un evento sísmico por ID.
    //  */
    // public Optional<EventoSismico> obtenerEventoPorId(Long id) {
    //     return eventoSismicoRepository.findById(id);
    // }

    // /**
    //  * Obtiene todos los eventos sísmicos.
    //  */
    // public List<EventoSismico> obtenerTodosLosEventos() {
    //     return eventoSismicoRepository.findAll();
    // }

    // /**
    //  * Obtiene eventos por estado.
    //  */
    // public List<EventoSismico> obtenerEventosPorEstado(EstadoEnum estado) {
    //     return eventoSismicoRepository.findByEstadoActual(estado);
    // }

  
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

    // /**
    //  * Cambia el estado de un evento sísmico.
    //  */
    // public EventoSismico cambiarEstado(Long eventoId, EstadoEnum nuevoEstado) {
    //     EventoSismico evento = eventoSismicoRepository.findById(eventoId)
    //             .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado: " + eventoId));

    //     // Validar transición de estado
    //     validarTransicion(evento.getEstadoActual(), nuevoEstado);

    //     // Cerrar el cambio de estado actual
    //     Optional<CambioEstado> cambioActual = cambioEstadoRepository.findCambioActual(evento);
    //     cambioActual.ifPresent(cambio -> {
    //         cambio.setFechaHoraFin(nuevoEstado);
    //         cambioEstadoRepository.save(cambio);
    //     });

    //     // Crear nuevo cambio de estado
    //     CambioEstado nuevoCambio = new CambioEstado(nuevoEstado, null);
    //     evento.setCambioEstado(nuevoCambio);
    //     evento.setEstadoActual(nuevoEstado);

    //     return eventoSismicoRepository.save(evento);
    // }

    // /**
    //  * Confirma un evento sísmico.
    //  */
    // public EventoSismico confirmarEvento(Long eventoId) {
    //     return cambiarEstado(eventoId, EstadoEnum.CONFIRMADO);
    // }

    // /**
    //  * Rechaza un evento sísmico.
    //  */
    // public EventoSismico rechazarEvento(Long eventoId) {
    //     return cambiarEstado(eventoId, EstadoEnum.RECHAZADO);
    // }

    // /**
    //  * Deriva un evento sísmico.
    //  */
    // public EventoSismico derivarEvento(Long eventoId) {
    //     return cambiarEstado(eventoId, EstadoEnum.DERIVADO);
    // }

    // /**
    //  * Anula un evento sísmico.
    //  */
    // public EventoSismico anularEvento(Long eventoId) {
    //     return cambiarEstado(eventoId, EstadoEnum.ANULADO);
    // }

    // /**
    //  * Cierra un evento sísmico.
    //  */
    // public EventoSismico cerrarEvento(Long eventoId) {
    //     return cambiarEstado(eventoId, EstadoEnum.CERRADO);
    // }

    // /**
    //  * Marca un evento como pendiente de revisión.
    //  */
    // public EventoSismico marcarPendienteRevision(Long eventoId) {
    //     return cambiarEstado(eventoId, EstadoEnum.PTE_DE_REVISION);
    // }

    // /**
    //  * Bloquea un evento para revisión.
    //  */
    // public EventoSismico bloquearParaRevision(Long eventoId) {
    //     return cambiarEstado(eventoId, EstadoEnum.BLOQUEADO_EN_REVISION);
    // }

    // /**
    //  * Obtiene el historial de cambios de estado de un evento.
    //  */
    // public List<CambioEstado> obtenerHistorialCambios(Long eventoId) {
    //     EventoSismico evento = eventoSismicoRepository.findById(eventoId)
    //             .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado: " + eventoId));
    //     return cambioEstadoRepository.findByEventoSismicoOrderByIdDesc(evento);
    // }

    // /**
    //  * Crea el objeto de estado concreto según el enum.
    //  */
    // private EstadoEvSismico crearEstado(EstadoEnum estado) {
    //     return switch (estado) {
    //         case AUTO_DETECTADO -> new AutoDetectado();
    //         case PTE_DE_REVISION -> new PteDeRevision();
    //         case BLOQUEADO_EN_REVISION -> new BloqueadoEnRevision();
    //         case CONFIRMADO -> new Confirmado();
    //         case AUTO_CONFIRMADO -> new AutoConfirmado();
    //         case RECHAZADO -> new Rechazado();
    //         case DERIVADO -> new Derivado();
    //         case PTE_DE_CIERRE -> new PteDeCierre();
    //         case CERRADO -> new Cerrado();
    //         case ANULADO -> new Anulado();
    //     };
    // }

    // /**
    //  * Valida si una transición de estado es válida.
    //  */
    // private void validarTransicion(EstadoEnum estadoActual, EstadoEnum nuevoEstado) {
    //     // Implementar lógica de validación según el diagrama de estados
    //     if (estadoActual == EstadoEnum.CERRADO || estadoActual == EstadoEnum.ANULADO) {
    //         throw new IllegalStateException("No se puede cambiar el estado de un evento cerrado o anulado");
    //     }
        
    //     // Agregar más validaciones según las reglas de negocio
    // }
}
