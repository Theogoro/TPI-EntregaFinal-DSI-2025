package com.los_btc_de_la_abuela.dsi.TPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Representa una sesión de usuario en el sistema (MOCK simplificado).
 * En producción esto sería manejado por Spring Security o similar.
 */
@Entity
@Table(name = "sesion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sesion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Fecha y hora de inicio de sesión
     */
    @Column(name = "fecha_hora_inicio", nullable = false)
    private LocalDateTime fechaHoraInicio;
    
    /**
     * Fecha y hora de fin de sesión (null si está activa)
     */
    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;
    
    /**
     * Token de sesión (mock - en producción sería JWT o similar)
     */
    @Column(unique = true, length = 255)
    private String token;
    
    /**
     * Usuario asociado a esta sesión
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    /**
     * Indica si la sesión está activa
     */
    @Column(nullable = false)
    private Boolean activa = true;
    
    @PrePersist
    protected void onCreate() {
        if (fechaHoraInicio == null) {
            fechaHoraInicio = LocalDateTime.now();
        }
        if (token == null) {
            // Mock simple token generation
            token = "SESSION_" + System.currentTimeMillis() + "_" + (usuario != null ? usuario.getId() : "UNKNOWN");
        }
    }
    
    /**
     * Cierra la sesión
     */
    public void cerrar() {
        this.fechaHoraFin = LocalDateTime.now();
        this.activa = false;
    }
    
    /**
     * Verifica si la sesión está activa
     */
    public boolean estaActiva() {
        return activa && fechaHoraFin == null;
    }
}
