package com.los_btc_de_la_abuela.dsi.TPI.service;

import com.los_btc_de_la_abuela.dsi.TPI.model.Usuario;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {
  public Usuario crearUsuarioMock() {
    Usuario usuario = new Usuario();
    usuario.setNombre("Analista de sismos");
    usuario.setContrasena("password"); // En producción usar BCrypt
    usuario.setEmail("analista@sismos.com");
    usuario.setActivo(true);
    usuario.setFechaAlta(LocalDateTime.now());
    
    return usuario;
  }
}
