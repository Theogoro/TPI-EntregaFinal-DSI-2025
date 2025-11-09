package com.los_btc_de_la_abuela.dsi.TPI.controller;

import com.los_btc_de_la_abuela.dsi.TPI.model.SerieTemporal;
import com.los_btc_de_la_abuela.dsi.TPI.service.SismogramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la interfaz de generación de sismogramas.
 * Expone endpoints para generar visualizaciones de sismogramas.
 */
@RestController
@RequestMapping("/api/sismograma")
public class InterfazGenerarSismograma {

    @Autowired
    private SismogramaService sismogramaService;

    /**
     * Genera un sismograma a partir del ID de una serie temporal.
     * 
     * @param serieTemporalId ID de la serie temporal
     * @return placeholder del sismograma generado
     */
    @GetMapping("/generar/{serieTemporalId}")
    public ResponseEntity<SismogramaResponse> generarSismograma(@PathVariable Long serieTemporalId) {
        try {
            // En producción, aquí se buscaría la serie temporal en el repositorio
            // Por ahora retornamos un placeholder
            SismogramaResponse response = new SismogramaResponse(
                "Sismograma generado para serie " + serieTemporalId,
                "Pendiente de implementación - se generaría imagen del sismograma",
                "image/png"
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Genera un sismograma multi-componente a partir de múltiples series temporales.
     * 
     * @param request solicitud con IDs de series temporales
     * @return placeholder del sismograma generado
     */
    @PostMapping("/generar-multi")
    public ResponseEntity<SismogramaResponse> generarSismogramaMulti(@RequestBody GenerarMultiRequest request) {
        try {
            SismogramaResponse response = new SismogramaResponse(
                String.format("Sismograma multi-componente (%d canales)", request.getSerieTemporalIds().length),
                "Pendiente de implementación - se generaría imagen combinada",
                "image/png"
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * DTO para la respuesta de generación de sismograma.
     */
    public static class SismogramaResponse {
        private String descripcion;
        private String contenido;
        private String tipoContenido;

        public SismogramaResponse(String descripcion, String contenido, String tipoContenido) {
            this.descripcion = descripcion;
            this.contenido = contenido;
            this.tipoContenido = tipoContenido;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getContenido() {
            return contenido;
        }

        public void setContenido(String contenido) {
            this.contenido = contenido;
        }

        public String getTipoContenido() {
            return tipoContenido;
        }

        public void setTipoContenido(String tipoContenido) {
            this.tipoContenido = tipoContenido;
        }
    }

    /**
     * DTO para solicitud de generación multi-componente.
     */
    public static class GenerarMultiRequest {
        private Long[] serieTemporalIds;

        public Long[] getSerieTemporalIds() {
            return serieTemporalIds;
        }

        public void setSerieTemporalIds(Long[] serieTemporalIds) {
            this.serieTemporalIds = serieTemporalIds;
        }
    }
}
