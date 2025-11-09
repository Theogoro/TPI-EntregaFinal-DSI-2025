package com.los_btc_de_la_abuela.dsi.TPI.dto;

import java.util.List;
import java.util.Map;

public record DatosRegistradosDTO(
  String clasificacion,
  String origen,
  String alcance
  // Map<Long, List<VelocidadLongitudDeFrecuencia>> datosSismografo
  // VelocidadLongitudDeFrecuencia[] velocidadLongitudDeFrecuencia
) {
  // tenemos que devolver: Clasificacion, origen, alcance, velocidadLongitudDeFrecuencia: Array de longitud, frecuencia y velocidad

}
