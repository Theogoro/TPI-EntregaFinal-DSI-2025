package com.los_btc_de_la_abuela.dsi.TPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.los_btc_de_la_abuela.dsi.TPI.model.Sesion;
import com.los_btc_de_la_abuela.dsi.TPI.model.Usuario;

import java.time.LocalDateTime;

@Service
public class SesionService {

  @Autowired
  private UsuarioService usuarioService;

  /**
   * Obtiene una sesión activa mock.
   * MOCK: En producción, esto debería obtener la sesión del usuario autenticado
   * desde el contexto de seguridad (Spring Security).
   */
  public Sesion getSesionActiva() {
    // MOCK: Crear usuario y sesión de prueba
    Usuario usuario = usuarioService.crearUsuarioMock();
    
    Sesion sesion = new Sesion();
    sesion.setUsuario(usuario);
    sesion.setFechaHoraInicio(LocalDateTime.now());
    sesion.setActiva(true);
    sesion.setToken("MOCK_SESSION_TOKEN_" + System.currentTimeMillis());
    
    return sesion;
  }
}
