package com.los_btc_de_la_abuela.dsi.TPI.service;

import com.los_btc_de_la_abuela.dsi.TPI.model.Usuario;
import com.los_btc_de_la_abuela.dsi.TPI.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {
  
  @Autowired
  private UsuarioRepository usuarioRepository;

  /**
   * Obtiene o crea un usuario mock para pruebas.
   * MOCK: En producción, esto debería obtener el usuario autenticado del contexto de seguridad.
   * 
   * @return Usuario persistido en la base de datos
   */
  public Usuario obtenerUsuarioMock() {
    // Buscar si ya existe el usuario mock
    return usuarioRepository.findByNombre("Analista de sismos")
        .orElseGet(() -> {
          // Si no existe, crearlo y persistirlo
          Usuario usuario = new Usuario();
          usuario.setNombre("Analista de sismos");
          usuario.setContrasena("password"); // En producción usar BCrypt
          usuario.setEmail("analista@sismos.com");
          usuario.setActivo(true);
          usuario.setFechaAlta(LocalDateTime.now());
          return usuarioRepository.save(usuario);
        });
  }

  /**
   * @deprecated Usar {@link #obtenerUsuarioMock()} en su lugar
   */
  @Deprecated
  public Usuario crearUsuarioMock() {
    return obtenerUsuarioMock();
  }
}
