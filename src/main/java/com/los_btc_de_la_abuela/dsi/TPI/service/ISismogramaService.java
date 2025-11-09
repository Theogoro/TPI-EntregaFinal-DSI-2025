package com.los_btc_de_la_abuela.dsi.TPI.service;

import com.los_btc_de_la_abuela.dsi.TPI.model.SerieTemporal;

/**
 * SERVICIO interno para la generación de sismogramas.
 * Esta es una interfaz de servicio - en producción generaría imágenes o gráficos visuales.
 * 
 * Nota: La interfaz de usuario/controller está en:
 * @see com.los_btc_de_la_abuela.dsi.TPI.controller.InterfazGenerarSismograma
 */
public interface ISismogramaService {
    
    /**
     * Genera un sismograma a partir de una serie temporal de datos sísmicos.
     * 
     * @param serieTemporal la serie temporal con los datos sísmicos
     * @return objeto que representa el sismograma generado (placeholder)
     */
    SismogramaPlaceholder generarSismograma(SerieTemporal serieTemporal);
    
    /**
     * Genera un sismograma para múltiples series temporales (multi-componente).
     * 
     * @param seriesTemporales array de series temporales
     * @return objeto que representa el sismograma generado
     */
    SismogramaPlaceholder generarSismogramaMulti(SerieTemporal... seriesTemporales);
    
    /**
     * Clase placeholder que representa un sismograma generado.
     * En producción, esto sería una imagen (BufferedImage, byte[], Path a archivo, etc.)
     */
    class SismogramaPlaceholder {
        private final String descripcion;
        private final int numeroMuestras;
        private final double duracionSegundos;
        
        public SismogramaPlaceholder(String descripcion, int numeroMuestras, double duracionSegundos) {
            this.descripcion = descripcion;
            this.numeroMuestras = numeroMuestras;
            this.duracionSegundos = duracionSegundos;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
        
        public int getNumeroMuestras() {
            return numeroMuestras;
        }
        
        public double getDuracionSegundos() {
            return duracionSegundos;
        }
        
        @Override
        public String toString() {
            return String.format("Sismograma[%s, %d muestras, %.2fs]", 
                descripcion, numeroMuestras, duracionSegundos);
        }
    }
}
