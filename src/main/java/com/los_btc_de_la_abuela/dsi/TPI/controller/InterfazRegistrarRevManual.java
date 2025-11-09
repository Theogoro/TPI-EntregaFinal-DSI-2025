package com.los_btc_de_la_abuela.dsi.TPI.controller;

import com.los_btc_de_la_abuela.dsi.TPI.dto.DatosRegistradosDTO;
import com.los_btc_de_la_abuela.dsi.TPI.dto.EventoSismicoDTO;
import com.los_btc_de_la_abuela.dsi.TPI.dto.EventoSismicoSinRevisionDTO;
import com.los_btc_de_la_abuela.dsi.TPI.dto.SismogramaDTO;
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
@RequestMapping("/api/eventos")
public class InterfazRegistrarRevManual {

    @Autowired
    private GestorRegRevisionManual gestorRevision;

    @GetMapping("/sin-revision")
    public ResponseEntity<List<EventoSismicoSinRevisionDTO>> buscarEventosSinRevision() {
        List<EventoSismicoSinRevisionDTO> eventos = gestorRevision.buscarEventosSinRevision();
        return ResponseEntity.ok(eventos);
    }

    @PostMapping("/tomar-evento")
    public ResponseEntity<Void> tomarEvento(@RequestBody Map<String, Long> request) {
        try {
            gestorRevision.tomarEvento(request.get("eventoId"));
            return ResponseEntity.status(201).build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{eventoId}/datos-registrados")
    public ResponseEntity<DatosRegistradosDTO> obtenerDatosRegistrados(@PathVariable Long eventoId) {
        try {
            DatosRegistradosDTO datos = gestorRevision.obtenerDatosRegistrados(eventoId);
            return ResponseEntity.ok(datos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{eventoId}/sismogramas")
    public ResponseEntity<List<SismogramaDTO>> obtenerDatosPorEstacion(@PathVariable Long eventoId) {
        try {
            List<SismogramaDTO> datos = gestorRevision.obtenerDatosRegistradosParaSismogramas(eventoId);
            return ResponseEntity.ok(datos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{eventoId}/rechazar")
    public ResponseEntity<Void> rechazarEvento(@PathVariable Long eventoId) {
        try {
            gestorRevision.tomarRechazoEvento(eventoId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<EventoSismicoDTO>> saludos() {
        return ResponseEntity.ok(gestorRevision.obtenerTodosLosEventos());
    }
}
