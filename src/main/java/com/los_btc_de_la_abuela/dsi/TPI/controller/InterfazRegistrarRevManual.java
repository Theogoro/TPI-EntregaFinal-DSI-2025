package com.los_btc_de_la_abuela.dsi.TPI.controller;

import com.los_btc_de_la_abuela.dsi.TPI.dto.DatosRegistradosDTO;
import com.los_btc_de_la_abuela.dsi.TPI.dto.EventoSismicoDTO;
import com.los_btc_de_la_abuela.dsi.TPI.dto.SismogramaDTO;
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

    @GetMapping("/eventos-sin-revision")
    public ResponseEntity<List<EventoSismicoDTO>> buscarEventosSinRevision() {
        List<EventoSismicoDTO> eventos = gestorRevision.buscarEventosSinRevision();
        return ResponseEntity.ok(eventos);
    }

    @PostMapping("/tomar-evento")
    public ResponseEntity<Void> tomarEvento(@RequestBody Map<String, Long> request) {
        try {
            gestorRevision.tomarEvento(request.get("eventoId"));
            // DatosRegistradosDTO datos = this.buscarDatosRegistrados(request.get("eventoId"));
            return ResponseEntity.status(201).build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/evento/{eventoId}/datos-registrados")
    public ResponseEntity<DatosRegistradosDTO> obtenerDatosRegistrados(@PathVariable Long eventoId) {
        try {
            DatosRegistradosDTO datos = gestorRevision.obtenerDatosRegistrados(eventoId);
            return ResponseEntity.ok(datos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/evento/{eventoId}/sismogramas")
    public ResponseEntity<List<SismogramaDTO>> obtenerDatosPorEstacion(@PathVariable Long eventoId) {
        try {
            List<SismogramaDTO> datos = gestorRevision.obtenerDatosRegistradosParaSismogramas(eventoId);
            return ResponseEntity.ok(datos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/evento/{eventoId}/rechazar")
    public ResponseEntity<Void> rechazarEvento(@PathVariable Long eventoId) {
        try {
            gestorRevision.tomarRechazoEvento(eventoId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
