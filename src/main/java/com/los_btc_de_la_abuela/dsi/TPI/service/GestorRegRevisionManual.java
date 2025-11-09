package com.los_btc_de_la_abuela.dsi.TPI.service;

import com.los_btc_de_la_abuela.dsi.TPI.dto.DatosRegistradosDTO;
import com.los_btc_de_la_abuela.dsi.TPI.dto.EventoSismicoDTO;
import com.los_btc_de_la_abuela.dsi.TPI.dto.SismogramaDTO;
import com.los_btc_de_la_abuela.dsi.TPI.dto.VelocidadLongitudDeFrecuencia;
import com.los_btc_de_la_abuela.dsi.TPI.model.EventoSismico;
import com.los_btc_de_la_abuela.dsi.TPI.model.SerieTemporal;
import com.los_btc_de_la_abuela.dsi.TPI.model.Sismografo;
import com.los_btc_de_la_abuela.dsi.TPI.model.Usuario;
import com.los_btc_de_la_abuela.dsi.TPI.repository.SismografoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gestor de revisión manual de eventos sísmicos.
 * Coordina el flujo de trabajo de revisión manual según el UML.
 */
@Service
@Transactional
public class GestorRegRevisionManual {

    // @Autowired
    // private EventoSismicoRepository eventoSismicoRepository;

    @Autowired
    private EventoSismicoService eventoSismicoService;

    @Autowired
    private SismografoRepository sismografoRepository;

    @Autowired
    private InterfazGenerarSismograma interfazGenerarSismograma;

    @Autowired
    private SesionService sesionService;

    public List<EventoSismicoDTO> buscarEventosSinRevision() {
        List<EventoSismicoDTO> eventos = eventoSismicoService.buscarEventosSinRevision();
        if (!hayEventosSinRevision(eventos)) {
          return null; // No llega nunca porque ya lanza excepción en el service
        }
        return this.ordenarEventosPorFechaHoraOcurrencia(eventos);
    }

    private boolean hayEventosSinRevision(List<EventoSismicoDTO> eventos) {
        return eventos != null && !eventos.isEmpty();
    }

    private List<EventoSismicoDTO> ordenarEventosPorFechaHoraOcurrencia(List<EventoSismicoDTO> eventos) {
        eventos.sort((e1, e2) -> e1.getFechaHoraOcurrencia().compareTo(e2.getFechaHoraOcurrencia()));
        return eventos;
    }

    @Transactional
    public void tomarEvento(Long eventoId) {
      this.bloquear(eventoId);
    }

    @Transactional
    private void bloquear(Long eventoId) {
        EventoSismico evento = eventoSismicoService.findById(eventoId);
        Usuario usuario = sesionService.getSesionActiva().getEmpleado();
        evento.bloquear(usuario);
        eventoSismicoService.save(evento);
    }

    public DatosRegistradosDTO obtenerDatosRegistrados(Long eventoId) {
      EventoSismico evento = eventoSismicoService.findById(eventoId);
      return evento.obtenerDatosRegistrados();
    }

    public List<SismogramaDTO> obtenerDatosRegistradosParaSismogramas(Long eventoId) {
      EventoSismico evento = eventoSismicoService.findById(eventoId);
      Map<SerieTemporal, List<VelocidadLongitudDeFrecuencia>> datos = new java.util.HashMap<>();

      evento.getSerieTemporal()
        .stream()
        .map(st -> st.buscarVelocidadLongitudFrecuencia())
        .forEachOrdered(mapa -> datos.putAll(mapa));

      Map<String, List<VelocidadLongitudDeFrecuencia>> datosPorEstacion = this.clasificarDatosPorEstacion(datos, evento);
      List<SismogramaDTO> sismogramas = this.generarSismogramaPorEstacion(datosPorEstacion);
      return sismogramas;
    }

    private Map<String, List<VelocidadLongitudDeFrecuencia>> clasificarDatosPorEstacion(Map<SerieTemporal, List<VelocidadLongitudDeFrecuencia>> datos, EventoSismico evento) {
      List<Sismografo> sismografos = sismografoRepository.findAll();
      Map<String, List<SerieTemporal>> seriePorEstacion = new HashMap<>();
      for (Sismografo sismografo : sismografos) {
        for (SerieTemporal serieTemporal : datos.keySet()) {
          if (sismografo.sosDeSerieTemporal(serieTemporal)) {
            String estacion = sismografo.buscarEstacionSismologica();
            seriePorEstacion.putIfAbsent(estacion, new ArrayList<>());
            seriePorEstacion.get(estacion).add(serieTemporal);
          }
        }
      }
      return this.clasificar(seriePorEstacion, datos);
    }

    // Clasifica los datos de velocidad y longitud por estación sismológica.
    private Map<String, List<VelocidadLongitudDeFrecuencia>> clasificar(Map<String, List<SerieTemporal>> seriePorEstacion, Map<SerieTemporal, List<VelocidadLongitudDeFrecuencia>> datos) {
      Map<String, List<VelocidadLongitudDeFrecuencia>> datosPorEstacion = new HashMap<>();
      for (String estacion : seriePorEstacion.keySet()) {
        List<VelocidadLongitudDeFrecuencia> velocidadesYLongitudes = new ArrayList<>();
        for (SerieTemporal serieTemporal : seriePorEstacion.get(estacion)) {
          velocidadesYLongitudes.addAll(datos.get(serieTemporal));
        }
        datosPorEstacion.put(estacion, velocidadesYLongitudes);
      }
      return datosPorEstacion;
    }


    private List<SismogramaDTO> generarSismogramaPorEstacion(Map<String,List<VelocidadLongitudDeFrecuencia>> datosPorEstacion) {
      List<SismogramaDTO> sismogramas = new ArrayList<>();
      for (String estacion : datosPorEstacion.keySet()) {
        SismogramaDTO sismograma = this.interfazGenerarSismograma.generarSismograma(estacion, datosPorEstacion.get(estacion));
        sismogramas.add(sismograma);
      }
      return sismogramas;
    }

    public void tomarRechazoEvento(Long eventoId) {
      EventoSismico evento = eventoSismicoService.findById(eventoId);
      Usuario usuario = sesionService.getSesionActiva().getEmpleado();
      evento.rechazar(usuario);
      eventoSismicoService.save(evento);
    }
}
