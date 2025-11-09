package com.los_btc_de_la_abuela.dsi.TPI.service;

import com.los_btc_de_la_abuela.dsi.TPI.dto.DatosRegistradosDTO;
import com.los_btc_de_la_abuela.dsi.TPI.dto.EventoSismicoDTO;
import com.los_btc_de_la_abuela.dsi.TPI.model.EventoSismico;

// import com.los_btc_de_la_abuela.dsi.TPI.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import java.util.List;

/**
 * Gestor de revisión manual de eventos sísmicos.
 * Coordina el flujo de trabajo de revisión manual según el UML.
 */
@Service
@Transactional
public class GestorRegRevisionManual {

    // @Autowired
    // private EventoSismicoRepository eventoSismicoRepository;

    @Autowired
    private EventoSismicoService eventoSismicoService;


    public List<EventoSismicoDTO> buscarEventosSinRevision() {
        List<EventoSismicoDTO> eventos = eventoSismicoService.buscarEventosSinRevision();
        if (!hayEventosSinRevision(eventos)) {
          return null; // No llega nunca porque ya lanza excepción en el service
        }
        return this.ordenarEventosPorFechaHoraOcurrencia(eventos);
    }

    private boolean hayEventosSinRevision(List<EventoSismicoDTO> eventos) {
        return eventos != null && !eventos.isEmpty();
    }

    private List<EventoSismicoDTO> ordenarEventosPorFechaHoraOcurrencia(List<EventoSismicoDTO> eventos) {
        eventos.sort((e1, e2) -> e1.getFechaHoraOcurrencia().compareTo(e2.getFechaHoraOcurrencia()));
        return eventos;
    }

    @Transactional
    public void tomarEvento(Long eventoId) {
      this.bloquear(eventoId);
    }

    @Transactional
    private void bloquear(Long eventoId) {
        EventoSismico evento = eventoSismicoService.findById(eventoId);
        evento.bloquear();
        eventoSismicoService.save(evento);
    }

    public DatosRegistradosDTO obtenerDatosRegistrados(Long eventoId) {
      EventoSismico evento = eventoSismicoService.findById(eventoId);
      return evento.obtenerDatosRegistrados();
    }
    
    // /**
    //  * Registra una revisión manual completa según el flujo del diagrama de secuencia.
    //  * Este método implementa el caso de uso "Registrar Revisión Manual".
    //  * 
    //  * @param codigoEvento código del evento sísmico a revisar
    //  * @param tokenSesion token de sesión del usuario actual (mock)
    //  * @param observaciones observaciones opcionales del revisor
    //  * @return la revisión manual creada
    //  */
    // @Transactional
    // public RevisionManual registrarRevisionManual(String codigoEvento, String tokenSesion, String observaciones) {
    //     // 1. Obtener sesión activa (mock)
    //     Sesion sesion = sesionRepository.findByTokenAndActivaTrue(tokenSesion)
    //             .orElseThrow(() -> new IllegalStateException("Sesión no válida o expirada"));
        
    //     // 2. Obtener empleado asociado al usuario de la sesión
    //     Empleado empleado = empleadoRepository.findByUsuarioId(sesion.getUsuario().getId())
    //             .orElseThrow(() -> new IllegalStateException("No se encontró empleado para este usuario"));
        
    //     // 3. Buscar evento sísmico por código (asumimos que el código es el ID como string)
    //     EventoSismico evento = eventoSismicoRepository.findById(Long.parseLong(codigoEvento))
    //             .orElseThrow(() -> new IllegalArgumentException("Evento sísmico no encontrado"));
        
    //     // 4. Obtener datos del evento (coordenadas, magnitud, clasificación, origen, alcance)
    //     MagnitudRichter magnitud = evento.getMagnitudRichter();
    //     ClasificacionSismo clasificacion = evento.getClasificacionSismo();
    //     OrigenDeGeneracion origen = evento.getOrigenGeneracion();
    //     AlcanceSismo alcance = evento.getAlcanceSismo();
        
    //     // 5. Obtener estación sismológica asociada
    //     EstacionSismologica estacion = evento.getEstacionSismologica();
    //     if (estacion != null && !estacion.getSismografos().isEmpty()) {
    //         // 6. Obtener sismógrafo de la estación
    //         Sismografo sismografo = estacion.getSismografos().get(0);
            
    //         // 7. Generar sismograma de las series temporales
    //         if (!sismografo.getSeriesTemporales().isEmpty()) {
    //             SerieTemporal serieTemporal = sismografo.getSeriesTemporales().get(0);
    //             // Generar sismograma (placeholder)
    //             sismogramaService.generarSismograma(serieTemporal);
    //             // En producción, aquí se mostraría el sismograma al usuario
    //         }
    //     }
        
    //     // 8. Crear registro de revisión manual
    //     RevisionManual revisionManual = new RevisionManual();
    //     revisionManual.setEventoSismico(evento);
    //     revisionManual.setEmpleado(empleado);
    //     revisionManual.setSesion(sesion);
    //     revisionManual.setFechaHoraRevision(LocalDateTime.now());
    //     revisionManual.setObservaciones(observaciones);
        
    //     // 9. Guardar la revisión
    //     revisionManual = revisionManualRepository.save(revisionManual);
        
    //     // 10. Cambiar estado del evento a "En Revisión" si corresponde
    //     if (evento.getEstadoActual() == EstadoEnum.PTE_DE_REVISION) {
    //         eventoSismicoService.bloquearParaRevision(evento.getId());
    //     }
        
    //     return revisionManual;
    // }

    // /**
    //  * Bloquea un evento para iniciar su revisión manual.
    //  */
    // public EventoSismico bloquear(Long eventoId) {
    //     EventoSismico evento = eventoSismicoRepository.findById(eventoId)
    //             .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));

    //     // Validar que el evento esté en estado pendiente de revisión
    //     if (evento.getEstadoActual() != EstadoEnum.PTE_DE_REVISION) {
    //         throw new IllegalStateException("Solo se pueden bloquear eventos pendientes de revisión");
    //     }

    //     this.eventoSeleccionado = evento;
    //     this.fechaHoraInicioRevision = LocalDateTime.now();
        
    //     return eventoSismicoService.bloquearParaRevision(eventoId);
    // }

    // public List<EventoSismico> buscarEventosSinRevision() {
    //     this.listaEventosSismicos = eventoSismicoRepository.findEventosSinRevision();
    //     return this.listaEventosSismicos;
    // }

    // /**
    //  * Selecciona un evento de la lista para revisar.
    //  */
    // public void seleccionarEvento(Long eventoId) {
    //     this.eventoSeleccionado = eventoSismicoRepository.findById(eventoId)
    //             .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));
    // }

    // /**
    //  * Finaliza la revisión registrando la fecha de fin.
    //  */
    // public void finalizarRevision() {
    //     if (this.eventoSeleccionado == null) {
    //         throw new IllegalStateException("No hay evento seleccionado para finalizar");
    //     }
    //     this.fechaHoraFinRevision = LocalDateTime.now();
    // }

    // /**
    //  * Confirma el evento bajo revisión.
    //  */
    // public EventoSismico confirmarEvento() {
    //     validarEventoSeleccionado();
    //     finalizarRevision();
    //     return eventoSismicoService.confirmarEvento(eventoSeleccionado.getId());
    // }

    // /**
    //  * Rechaza el evento bajo revisión.
    //  */
    // public EventoSismico rechazarEvento() {
    //     validarEventoSeleccionado();
    //     finalizarRevision();
    //     return eventoSismicoService.rechazarEvento(eventoSeleccionado.getId());
    // }

    // /**
    //  * Deriva el evento bajo revisión.
    //  */
    // public EventoSismico derivarEvento() {
    //     validarEventoSeleccionado();
    //     finalizarRevision();
    //     return eventoSismicoService.derivarEvento(eventoSeleccionado.getId());
    // }

    // /**
    //  * Anula el evento bajo revisión.
    //  */
    // public EventoSismico anularEvento() {
    //     validarEventoSeleccionado();
    //     finalizarRevision();
    //     return eventoSismicoService.anularEvento(eventoSeleccionado.getId());
    // }

    // /**
    //  * Valida que haya un evento seleccionado y bloqueado.
    //  */
    // private void validarEventoSeleccionado() {
    //     if (eventoSeleccionado == null) {
    //         throw new IllegalStateException("No hay evento seleccionado");
    //     }
    //     if (eventoSeleccionado.getEstadoActual() != EstadoEnum.BLOQUEADO_EN_REVISION) {
    //         throw new IllegalStateException("El evento debe estar bloqueado para ser revisado");
    //     }
    // }

    // // Getters
    // public EventoSismico getEventoSeleccionado() {
    //     return eventoSeleccionado;
    // }

    // public LocalDateTime getFechaHoraInicioRevision() {
    //     return fechaHoraInicioRevision;
    // }

    // public LocalDateTime getFechaHoraFinRevision() {
    //     return fechaHoraFinRevision;
    // }

    // public EstadoEnum getEstado() {
    //     return estado;
    // }
}
