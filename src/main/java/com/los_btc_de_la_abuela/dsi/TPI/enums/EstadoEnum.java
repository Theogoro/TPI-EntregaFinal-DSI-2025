package com.los_btc_de_la_abuela.dsi.TPI.enums;

/**
 * Enumeración de los estados posibles de un evento sísmico.
 * Se usa para persistencia en base de datos y facilitar consultas.
 */
public enum EstadoEnum {
    CERRADO("Cerrado"),
    ANULADO("Anulado"),
    CONFIRMADO("Confirmado"),
    DERIVADO("Derivado"),
    RECHAZADO("Rechazado"),
    PTE_DE_CIERRE("PteDeCierre"),
    AUTO_CONFIRMADO("AutoConfirmado"),
    PTE_DE_REVISION("PteDeRevision"),
    AUTO_DETECTADO("AutoDetectado"),
    BLOQUEADO_EN_REVISION("BloqueadoEnRevision");

    private final String nombre;

    EstadoEnum(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean sosPteRevision() {
      return this == PTE_DE_REVISION;
    }
}
