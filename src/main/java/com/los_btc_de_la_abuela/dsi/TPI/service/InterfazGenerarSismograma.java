package com.los_btc_de_la_abuela.dsi.TPI.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.los_btc_de_la_abuela.dsi.TPI.dto.SismogramaDTO;
import com.los_btc_de_la_abuela.dsi.TPI.dto.VelocidadLongitudDeFrecuencia;

@Service
public class InterfazGenerarSismograma {

  public SismogramaDTO generarSismograma(String estacion, List<VelocidadLongitudDeFrecuencia> list) {
    // Metodo de mockup para generar un sismograma, aca deberiamos de conectarnos con una API que genere sismogramas y devuelva los datos en base64 para poder mostrarlos en el front, pero en nuestro caso lo vamos a simular devovliendo un DTO con los datos recibidos y dibujando el sismograma en el front.
    return new SismogramaDTO(estacion, list);
  }
  
}
