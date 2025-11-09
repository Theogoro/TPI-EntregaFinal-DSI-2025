package com.los_btc_de_la_abuela.dsi.TPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Usuario del sistema con credenciales de acceso.
 */
@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Nombre de usuario único para login
     */
    @Column(nullable = false, unique = true, length = 50)
    private String nombre;
    
    /**
     * Contraseña hasheada (en producción usar BCrypt)
     */
    @Column(nullable = false)
    private String contrasena;
    
    /**
     * Email del usuario
     */
    @Column(unique = true, length = 100)
    private String email;
    
    /**
     * Indica si el usuario está activo
     */
    @Column(nullable = false)
    private Boolean activo = true;
    
    /**
     * Fecha de alta del usuario
     */
    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;
    
    /**
     * Última fecha de login
     */
    @Column(name = "ultimo_login")
    private LocalDateTime ultimoLogin;
    
    @PrePersist
    protected void onCreate() {
        if (fechaAlta == null) {
            fechaAlta = LocalDateTime.now();
        }
    }
}
