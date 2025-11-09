package com.los_btc_de_la_abuela.dsi.TPI.model.estado;

import com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum;
import com.los_btc_de_la_abuela.dsi.TPI.model.CambioEstado;
import com.los_btc_de_la_abuela.dsi.TPI.model.EventoSismico;
import com.los_btc_de_la_abuela.dsi.TPI.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase abstracta que define el comportamiento común de los estados de un evento sísmico.
 * Implementa el patrón State para manejar las transiciones de estado.
 */
public abstract class EstadoEvSismico {

    protected String nombre;

    public EstadoEvSismico(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Bloquea el evento sísmico según las reglas del estado actual.
     * @param eventoSismico el evento a bloquear
     * @param cambioEstado el cambio de estado asociado
     */
    public void bloquear(EventoSismico eventoSismico, List<CambioEstado> cambioEstado, Usuario usuarioActual) {
      throw new UnsupportedOperationException("Operación de bloqueo no soportada en estado: " + nombre);
    }

    /**
     * Valida si el estado puede autodetectarse.
     */
    public void validarAutodetectado() {  
        throw new UnsupportedOperationException("Operación de validación de autodetección no soportada en estado: " + nombre);
    }

    /**
     * Valida si hay revisiones pendientes.
     */
    public void pendienteDeRevision() {
        throw new UnsupportedOperationException("Operación de marcar como pendiente de revisión no soportada en estado: " + nombre);
    }

    /**
     * Valida el pendiente de revisión en el contexto del estado actual.
     */
    public void validarPendienteDeRevision() {
        throw new UnsupportedOperationException("Operación de validación de pendiente de revisión no soportada en estado: " + nombre);
    }

    /**
     * Anula el evento sísmico.
     */
    public void anular() {
        throw new UnsupportedOperationException("Operación de anulación no soportada en estado: " + nombre);
    }

    /**
     * Rechaza el evento sísmico.
     * @param cambiosEstado 
     * @param eventoSismico 
     * @param usuarioActual 
     */
    public void rechazar(EventoSismico eventoSismico, List<CambioEstado> cambiosEstado, Usuario usuarioActual) {
        throw new UnsupportedOperationException("Operación de rechazo no soportada en estado: " + nombre);
    }

    /**
     * Deriva el evento sísmico.
     */
    public void derivar() {
        throw new UnsupportedOperationException("Operación de derivación no soportada en estado: " + nombre);
    }

    /**
     * Confirma el evento sísmico.
     */
    public void confirmar() {
        throw new UnsupportedOperationException("Operación de confirmación no soportada en estado: " + nombre);
    }

    /**
     * Adquiere datos adicionales del evento.
     */
    public void adquirirDatos() {
        throw new UnsupportedOperationException("Operación de adquisición de datos no soportada en estado: " + nombre);
    }

    /**
     * Cierra el evento sísmico.
     */
    public void cerrar() {
        throw new UnsupportedOperationException("Operación de cierre no soportada en estado: " + nombre);
    }

    /**
     * Obtiene la fecha y hora actual del evento.
     * @return la fecha y hora actual
     */
    public LocalDateTime getFechaHoraActual() {
        return LocalDateTime.now();
    }

    /**
     * Crea un cambio de estado con fecha de inicio.
     * @param inicio el estado de inicio
     * @return el cambio de estado creado
     */
    public CambioEstado crearCambioEstado(LocalDateTime inicio, EstadoEvSismico estado, Usuario responsable) {
         return new CambioEstado(estado, inicio, responsable);
    }

    /**
     * Valida si se puede autoconfirmar.
     */
    public void validarAutoConfirmado() {
        throw new UnsupportedOperationException("Operación de autoconfirmación no soportada en estado: " + nombre);
    }

    /**
     * Verifica si el estado es un estado final (cerrado o anulado).
     * @return true si es un estado final
     */
    public boolean esEstadoFinal() {
        return false;
    }

    public boolean sosPteRevision() {
      return false;
    }

    /**
     * Crea una instancia del estado correspondiente al enum.
     * @param estadoEnum el enum del estado
     * @return la instancia del estado
     */
    public static EstadoEvSismico fromEnum(EstadoEnum estadoEnum) {
        if (estadoEnum == null) {
            return null;
        }
        
        return switch (estadoEnum) {
            case AUTO_DETECTADO -> new AutoDetectado();
            case PTE_DE_REVISION -> new PteDeRevision();
            case BLOQUEADO_EN_REVISION -> new BloqueadoEnRevision();
            case CONFIRMADO -> new Confirmado();
            case AUTO_CONFIRMADO -> new AutoConfirmado();
            case RECHAZADO -> new Rechazado();
            case DERIVADO -> new Derivado();
            case PTE_DE_CIERRE -> new PteDeCierre();
            case CERRADO -> new Cerrado();
            case ANULADO -> new Anulado();
        };
    }

    /**
     * Obtiene el enum correspondiente a la instancia del estado.
     * @param estado la instancia del estado
     * @return el enum correspondiente
     */
    public static EstadoEnum toEnum(EstadoEvSismico estado) {
        if (estado == null) {
            return null;
        }
        
        return switch (estado.getNombre()) {
            case "AutoDetectado" -> EstadoEnum.AUTO_DETECTADO;
            case "PteDeRevision" -> EstadoEnum.PTE_DE_REVISION;
            case "BloqueadoEnRevision" -> EstadoEnum.BLOQUEADO_EN_REVISION;
            case "Confirmado" -> EstadoEnum.CONFIRMADO;
            case "AutoConfirmado" -> EstadoEnum.AUTO_CONFIRMADO;
            case "Rechazado" -> EstadoEnum.RECHAZADO;
            case "Derivado" -> EstadoEnum.DERIVADO;
            case "PteDeCierre" -> EstadoEnum.PTE_DE_CIERRE;
            case "Cerrado" -> EstadoEnum.CERRADO;
            case "Anulado" -> EstadoEnum.ANULADO;
            default -> null;
        };
    }
}
