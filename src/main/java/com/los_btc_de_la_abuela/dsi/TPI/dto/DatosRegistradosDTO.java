package com.los_btc_de_la_abuela.dsi.TPI.dto;

import java.util.List;

public record DatosRegistradosDTO(
  String clasificacion,
  String origen,
  String alcance,
  List<DatosSismografoDTO> datosSismografo
  // VelocidadLongitudDeFrecuencia[] velocidadLongitudDeFrecuencia
) {
  // tenemos que devolver: Clasificacion, origen, alcance, velocidadLongitudDeFrecuencia: Array de longitud, frecuencia y velocidad

}
