package com.los_btc_de_la_abuela.dsi.TPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa un empleado del sistema sismológico.
 * Un empleado está asociado a un usuario para el acceso al sistema.
 */
@Entity
@Table(name = "empleado")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * DNI del empleado
     */
    @Column(nullable = false, unique = true, length = 20)
    private String dni;
    
    /**
     * Nombre completo del empleado
     */
    @Column(nullable = false, length = 100)
    private String nombre;
    
    /**
     * Apellido del empleado
     */
    @Column(nullable = false, length = 100)
    private String apellido;
    
    /**
     * Título o profesión del empleado
     */
    @Column(length = 100)
    private String titulo;
    
    /**
     * Teléfono de contacto
     */
    @Column(length = 20)
    private String telefono;
    
    /**
     * Usuario asociado para acceder al sistema
     */
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    /**
     * Obtiene el nombre completo del empleado
     */
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}
