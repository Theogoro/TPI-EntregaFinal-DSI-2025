package com.los_btc_de_la_abuela.dsi.TPI.dto;

import java.util.List;

public record SismogramaDTO(String estacion, List<VelocidadLongitudDeFrecuencia> datos) {
}
