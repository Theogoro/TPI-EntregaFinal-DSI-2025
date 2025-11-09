package com.los_btc_de_la_abuela.dsi.TPI.controller;

import com.los_btc_de_la_abuela.dsi.TPI.dto.DatosRegistradosDTO;
import com.los_btc_de_la_abuela.dsi.TPI.dto.EventoSismicoDTO;
import com.los_btc_de_la_abuela.dsi.TPI.model.EventoSismico;
import com.los_btc_de_la_abuela.dsi.TPI.model.RevisionManual;
import com.los_btc_de_la_abuela.dsi.TPI.service.GestorRegRevisionManual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controlador REST para la interfaz de registro de revisión manual.
 * Representa el punto de entrada del caso de uso "Registrar Revisión Manual".
 */
@RestController
@RequestMapping("/api/revision-manual")
public class InterfazRegistrarRevManual {

    @Autowired
    private GestorRegRevisionManual gestorRevision;

    /**
     * Endpoint principal para registrar una revisión manual.
     * Implementa el flujo completo del diagrama de secuencia.
     * 
     * @param request datos de la solicitud de revisión
     * @return la revisión manual creada
     */
    // @PostMapping("/registrar")
    // public ResponseEntity<RevisionManual> registrarRevision(@RequestBody RegistroRevisionRequest request) {
    //     // try {
    //     //     RevisionManual revision = gestorRevision.registrarRevisionManual(
    //     //         request.getCodigoEvento(),
    //     //         request.getTokenSesion(),
    //     //         request.getObservaciones()
    //     //     );
    //     //     return ResponseEntity.ok(revision);
    //     // } catch (IllegalArgumentException e) {
    //     //     return ResponseEntity.badRequest().build();
    //     // } catch (IllegalStateException e) {
    //     //     return ResponseEntity.status(403).build();
    //     // }
    // }

    /**
     * 1.Obtiene eventos pendientes de revisión.
     */
    @GetMapping("/eventos-sin-revision")
    public ResponseEntity<List<EventoSismicoDTO>> buscarEventosSinRevision() {
        List<EventoSismicoDTO> eventos = gestorRevision.buscarEventosSinRevision();
        return ResponseEntity.ok(eventos);
    }

    /**
     * Bloquea un evento para iniciar su revisión.
     */
    @PostMapping("/tomar-evento")
    public ResponseEntity<DatosRegistradosDTO> tomarEvento(@RequestBody Map<String, Long> request) {
        try {
            gestorRevision.tomarEvento(request.get("eventoId"));
            return ResponseEntity.ok(this.buscarDatosRegistrados(request.get("eventoId")));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private DatosRegistradosDTO buscarDatosRegistrados(Long eventoId) {
      return gestorRevision.obtenerDatosRegistrados(eventoId);
    }

    // /**
    //  * Confirma un evento bajo revisión.
    //  */
    // @PostMapping("/confirmar")
    // public ResponseEntity<EventoSismico> confirmarEvento() {
    //     try {
    //         EventoSismico evento = gestorRevision.confirmarEvento();
    //         return ResponseEntity.ok(evento);
    //     } catch (IllegalStateException e) {
    //         return ResponseEntity.badRequest().build();
    //     }
    // }

    // /**
    //  * Rechaza un evento bajo revisión.
    //  */
    // @PostMapping("/rechazar")
    // public ResponseEntity<EventoSismico> rechazarEvento() {
    //     try {
    //         EventoSismico evento = gestorRevision.rechazarEvento();
    //         return ResponseEntity.ok(evento);
    //     } catch (IllegalStateException e) {
    //         return ResponseEntity.badRequest().build();
    //     }
    // }

    // /**
    //  * Deriva un evento bajo revisión.
    //  */
    // @PostMapping("/derivar")
    // public ResponseEntity<EventoSismico> derivarEvento() {
    //     try {
    //         EventoSismico evento = gestorRevision.derivarEvento();
    //         return ResponseEntity.ok(evento);
    //     } catch (IllegalStateException e) {
    //         return ResponseEntity.badRequest().build();
    //     }
    // }

    /**
     * DTO para la solicitud de registro de revisión.
     */
    // public static class RegistroRevisionRequest {
    //     private String codigoEvento;
    //     private String tokenSesion;
    //     private String observaciones;

    //     public String getCodigoEvento() {
    //         return codigoEvento;
    //     }

    //     public void setCodigoEvento(String codigoEvento) {
    //         this.codigoEvento = codigoEvento;
    //     }

    //     public String getTokenSesion() {
    //         return tokenSesion;
    //     }

    //     public void setTokenSesion(String tokenSesion) {
    //         this.tokenSesion = tokenSesion;
    //     }

    //     public String getObservaciones() {
    //         return observaciones;
    //     }

    //     public void setObservaciones(String observaciones) {
    //         this.observaciones = observaciones;
    //     }
    // }
}
