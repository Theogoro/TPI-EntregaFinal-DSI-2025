package com.los_btc_de_la_abuela.dsi.TPI.config;

import com.los_btc_de_la_abuela.dsi.TPI.model.*;
import com.los_btc_de_la_abuela.dsi.TPI.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Componente para inicializar la base de datos con datos de prueba.
 * Se ejecuta automáticamente al iniciar la aplicación.
 * Activar con el perfil "dev" o comentar @Profile para que siempre se ejecute.
 */
@Component
// @Profile("dev") // Descomentar para ejecutar solo en perfil dev
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private MagnitudRichterRepository magnitudRepository;

    @Autowired
    private ClasificacionSismoRepository clasificacionRepository;

    @Autowired
    private OrigenDeGeneracionRepository origenRepository;

    @Autowired
    private AlcanceSismoRepository alcanceRepository;

    @Autowired
    private TipoDatoRepository tipoDatoRepository;

    @Autowired
    private EventoSismicoRepository eventoRepository;

    @Autowired
    private CambioEstadoRepository cambioEstadoRepository;

    @Autowired
    private EstacionSismologicaRepository estacionRepository;

    @Autowired
    private SismografoRepository sismografoRepository;

    @Autowired
    private SerieTemporalRepository serieTemporalRepository;

    @Autowired
    private MuestraSismicaRepository muestraSismicaRepository;

    @Autowired
    private DetalleMuestraSismicaRepository detalleMuestraSismicaRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Solo crear datos si la base de datos está vacía
        if (eventoRepository.count() > 0) {
            System.out.println("⏭️  La base de datos ya tiene datos. Saltando inicialización.");
            return;
        }

        System.out.println("🌱 Iniciando carga de datos de prueba...");

        // 1. Crear catálogos básicos
        crearCatalogos();

        // 2. Crear estaciones sismológicas con sismógrafos
        crearEstacionesSismologicas();

        // 3. Crear eventos sísmicos completos con datos registrados
        crearEventosSismicosCompletos();

        System.out.println("✅ Datos de prueba cargados exitosamente!");
        System.out.println("📝 Se crearon eventos sísmicos de prueba con estados:");
        System.out.println("   - PTE_DE_REVISION: para probar el endpoint /eventos-sin-revision");
        System.out.println("   - AUTO_DETECTADO: eventos recién detectados");
        System.out.println("   - CONFIRMADO: eventos ya procesados");
        System.out.println("   - Con estaciones sismológicas y datos registrados");
    }

    private void crearCatalogos() {
        System.out.println("📊 Creando catálogos...");

        // Magnitudes Richter
        if (magnitudRepository.count() == 0) {
            MagnitudRichter mag1 = new MagnitudRichter();
            mag1.setMagnitud(1.0);
            mag1.setDescripcion("Micro");
            mag1.setFechaHoraCalculo(LocalDateTime.now());
            magnitudRepository.save(mag1);

            MagnitudRichter mag2 = new MagnitudRichter();
            mag2.setMagnitud(3.0);
            mag2.setDescripcion("Menor");
            mag2.setFechaHoraCalculo(LocalDateTime.now());
            magnitudRepository.save(mag2);

            MagnitudRichter mag3 = new MagnitudRichter();
            mag3.setMagnitud(4.5);
            mag3.setDescripcion("Ligero");
            mag3.setFechaHoraCalculo(LocalDateTime.now());
            magnitudRepository.save(mag3);

            MagnitudRichter mag4 = new MagnitudRichter();
            mag4.setMagnitud(5.5);
            mag4.setDescripcion("Moderado");
            mag4.setFechaHoraCalculo(LocalDateTime.now());
            magnitudRepository.save(mag4);

            MagnitudRichter mag5 = new MagnitudRichter();
            mag5.setMagnitud(6.5);
            mag5.setDescripcion("Fuerte");
            mag5.setFechaHoraCalculo(LocalDateTime.now());
            magnitudRepository.save(mag5);
        }

        // Clasificaciones
        if (clasificacionRepository.count() == 0) {
            ClasificacionSismo clas1 = new ClasificacionSismo();
            clas1.setClasificacion("Tectónico");
            clas1.setDescripcion("Causado por movimiento de placas tectónicas");
            clasificacionRepository.save(clas1);

            ClasificacionSismo clas2 = new ClasificacionSismo();
            clas2.setClasificacion("Volcánico");
            clas2.setDescripcion("Asociado a actividad volcánica");
            clasificacionRepository.save(clas2);

            ClasificacionSismo clas3 = new ClasificacionSismo();
            clas3.setClasificacion("De Colapso");
            clas3.setDescripcion("Causado por colapso de cavernas");
            clasificacionRepository.save(clas3);
        }

        // Orígenes
        if (origenRepository.count() == 0) {
            OrigenDeGeneracion orig1 = new OrigenDeGeneracion();
            orig1.setOrigen("Natural");
            orig1.setDescripcion("Origen natural");
            origenRepository.save(orig1);

            OrigenDeGeneracion orig2 = new OrigenDeGeneracion();
            orig2.setOrigen("Artificial");
            orig2.setDescripcion("Origen artificial o inducido");
            origenRepository.save(orig2);
        }

        // Alcances
        if (alcanceRepository.count() == 0) {
            AlcanceSismo alc1 = new AlcanceSismo();
            alc1.setAlcance("Local");
            alc1.setRadioKm(100.0);
            alc1.setDescripcion("Alcance local hasta 100 km");
            alcanceRepository.save(alc1);

            AlcanceSismo alc2 = new AlcanceSismo();
            alc2.setAlcance("Regional");
            alc2.setRadioKm(1000.0);
            alc2.setDescripcion("Alcance regional hasta 1000 km");
            alcanceRepository.save(alc2);

            AlcanceSismo alc3 = new AlcanceSismo();
            alc3.setAlcance("Telesísmico");
            alc3.setRadioKm(20000.0);
            alc3.setDescripcion("Alcance telesísmico más de 1000 km");
            alcanceRepository.save(alc3);
        }

        // Tipos de Dato
        if (tipoDatoRepository.count() == 0) {
            TipoDato tipo1 = new TipoDato();
            tipo1.setNombre("Velocidad");
            tipo1.setUnidad("m/s");
            tipo1.setDescripcion("Velocidad sísmica");
            tipo1.setActivo(true);
            tipoDatoRepository.save(tipo1);

            TipoDato tipo2 = new TipoDato();
            tipo2.setNombre("Aceleración");
            tipo2.setUnidad("m/s²");
            tipo2.setDescripcion("Aceleración sísmica");
            tipo2.setActivo(true);
            tipoDatoRepository.save(tipo2);

            TipoDato tipo3 = new TipoDato();
            tipo3.setNombre("Desplazamiento");
            tipo3.setUnidad("m");
            tipo3.setDescripcion("Desplazamiento sísmico");
            tipo3.setActivo(true);
            tipoDatoRepository.save(tipo3);
        }
    }

    private void crearEventosSismicosSimples() {
        System.out.println("🌍 Creando eventos sísmicos de prueba...");

        // Obtener catálogos
        ClasificacionSismo clasificacion = clasificacionRepository.findAll().stream().findFirst().orElse(null);
        OrigenDeGeneracion origen = origenRepository.findAll().stream().findFirst().orElse(null);
        AlcanceSismo alcance = alcanceRepository.findAll().stream().findFirst().orElse(null);

        // Evento 1: Pendiente de Revisión
        EventoSismico evento1 = new EventoSismico();
        evento1.setFechaHoraOcurrencia(LocalDateTime.of(2024, 11, 1, 14, 30, 0));
        evento1.setCoordenadas("-32.8895,-68.8458");
        evento1.setValorMagnitud(5.2f);
        evento1.setEstadoActual(com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.PTE_DE_REVISION);
        evento1.setClasificacionSismo(clasificacion);
        evento1.setOrigenGeneracion(origen);
        evento1.setAlcanceSismo(alcance);
        evento1 = eventoRepository.save(evento1);

        CambioEstado cambio1 = new CambioEstado(
            com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.PTE_DE_REVISION, 
            LocalDateTime.of(2024, 11, 1, 14, 35, 0)
        );
        cambio1.setEventoSismico(evento1);
        cambioEstadoRepository.save(cambio1);

        // Evento 2: Auto-detectado
        EventoSismico evento2 = new EventoSismico();
        evento2.setFechaHoraOcurrencia(LocalDateTime.of(2024, 11, 3, 9, 15, 0));
        evento2.setCoordenadas("-31.5375,-68.5364");
        evento2.setValorMagnitud(4.8f);
        evento2.setEstadoActual(com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.AUTO_DETECTADO);
        evento2.setClasificacionSismo(clasificacion);
        evento2.setOrigenGeneracion(origen);
        evento2.setAlcanceSismo(alcance);
        evento2 = eventoRepository.save(evento2);

        CambioEstado cambio2 = new CambioEstado(
            com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.AUTO_DETECTADO, 
            LocalDateTime.of(2024, 11, 3, 9, 16, 0)
        );
        cambio2.setEventoSismico(evento2);
        cambioEstadoRepository.save(cambio2);

        // Evento 3: Pendiente de Revisión
        EventoSismico evento3 = new EventoSismico();
        evento3.setFechaHoraOcurrencia(LocalDateTime.of(2024, 11, 5, 16, 45, 0));
        evento3.setCoordenadas("-29.4131,-66.8558");
        evento3.setValorMagnitud(3.5f);
        evento3.setEstadoActual(com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.PTE_DE_REVISION);
        evento3.setClasificacionSismo(clasificacion);
        evento3.setOrigenGeneracion(origen);
        evento3.setAlcanceSismo(alcance);
        evento3 = eventoRepository.save(evento3);

        CambioEstado cambio3 = new CambioEstado(
            com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.PTE_DE_REVISION, 
            LocalDateTime.of(2024, 11, 5, 16, 50, 0)
        );
        cambio3.setEventoSismico(evento3);
        cambioEstadoRepository.save(cambio3);

        // Evento 4: Confirmado (ya procesado - con historial de cambios)
        EventoSismico evento4 = new EventoSismico();
        evento4.setFechaHoraOcurrencia(LocalDateTime.of(2024, 10, 28, 11, 20, 0));
        evento4.setCoordenadas("-32.5,-69.0");
        evento4.setValorMagnitud(6.1f);
        evento4.setEstadoActual(com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.CONFIRMADO);
        evento4.setClasificacionSismo(clasificacion);
        evento4.setOrigenGeneracion(origen);
        evento4.setAlcanceSismo(alcance);
        evento4 = eventoRepository.save(evento4);

        // Primer cambio: PTE_DE_REVISION (ya cerrado)
        CambioEstado cambio4a = new CambioEstado(
            com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.PTE_DE_REVISION, 
            LocalDateTime.of(2024, 10, 28, 11, 25, 0)
        );
        cambio4a.setFechaFin(LocalDateTime.of(2024, 10, 28, 14, 30, 0));
        cambio4a.setEventoSismico(evento4);
        cambioEstadoRepository.save(cambio4a);

        // Segundo cambio: CONFIRMADO (actual)
        CambioEstado cambio4b = new CambioEstado(
            com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.CONFIRMADO, 
            LocalDateTime.of(2024, 10, 28, 14, 30, 0)
        );
        cambio4b.setEventoSismico(evento4);
        cambioEstadoRepository.save(cambio4b);

        // Evento 5: Pendiente de Revisión (reciente)
        EventoSismico evento5 = new EventoSismico();
        evento5.setFechaHoraOcurrencia(LocalDateTime.now().minusHours(2));
        evento5.setCoordenadas("-33.0,-68.5");
        evento5.setValorMagnitud(4.2f);
        evento5.setEstadoActual(com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.PTE_DE_REVISION);
        evento5.setClasificacionSismo(clasificacion);
        evento5.setOrigenGeneracion(origen);
        evento5.setAlcanceSismo(alcance);
        evento5 = eventoRepository.save(evento5);

        CambioEstado cambio5 = new CambioEstado(
            com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.PTE_DE_REVISION, 
            LocalDateTime.now().minusHours(2).plusMinutes(5)
        );
        cambio5.setEventoSismico(evento5);
        cambioEstadoRepository.save(cambio5);

        System.out.println("   ✓ Creados 5 eventos sísmicos");
        System.out.println("   ✓ 3 eventos en estado PTE_DE_REVISION");
        System.out.println("   ✓ 1 evento AUTO_DETECTADO");
        System.out.println("   ✓ 1 evento CONFIRMADO");
    }

    private void crearEstacionesSismologicas() {
        System.out.println("🏢 Creando estaciones sismológicas...");

        // Estación 1: Mendoza
        EstacionSismologica estacion1 = new EstacionSismologica();
        estacion1.setCodigo("EST-MDZ-001");
        estacion1.setNombre("Estación Mendoza Centro");
        estacion1.setLatitud(-32.8895);
        estacion1.setLongitud(-68.8458);
        estacion1.setAltitud(800.0);
        estacion1.setActiva(true);
        estacion1 = estacionRepository.save(estacion1);

        // Sismógrafo para Estación 1
        Sismografo sismografo1 = new Sismografo();
        sismografo1.setCodigo("SIS-MDZ-001");
        sismografo1.setNombre("Sismógrafo Principal Mendoza");
        sismografo1.setModelo("SR-300");
        sismografo1.setFabricante("SeismoTech");
        sismografo1.setNroSerie("SR300-2020-001");
        sismografo1.setActivo(true);
        sismografo1.setEstacion(estacion1);
        sismografo1 = sismografoRepository.save(sismografo1);

        // Serie temporal para Sismógrafo 1
        SerieTemporal serie1 = new SerieTemporal();
        serie1.setFechaHoraInicio(LocalDateTime.of(2024, 11, 1, 14, 30, 0));
        serie1.setFechaHoraAdquisicion(LocalDateTime.of(2024, 11, 1, 14, 35, 0));
        serie1.setFrecuenciaMuestreo(100.0);
        serie1.setSismografo(sismografo1);
        serie1 = serieTemporalRepository.save(serie1);

    // Crear muestras para serie1
    crearMuestrasConDetalles(serie1, 3, 100.0);

        // Estación 2: San Juan
        EstacionSismologica estacion2 = new EstacionSismologica();
        estacion2.setCodigo("EST-SJ-001");
        estacion2.setNombre("Estación San Juan Norte");
        estacion2.setLatitud(-31.5375);
        estacion2.setLongitud(-68.5364);
        estacion2.setAltitud(650.0);
        estacion2.setActiva(true);
        estacion2 = estacionRepository.save(estacion2);

        // Sismógrafo para Estación 2
        Sismografo sismografo2 = new Sismografo();
        sismografo2.setCodigo("SIS-SJ-001");
        sismografo2.setNombre("Sismógrafo Principal San Juan");
        sismografo2.setModelo("SR-450");
        sismografo2.setFabricante("SeismoTech");
        sismografo2.setNroSerie("SR450-2019-002");
        sismografo2.setActivo(true);
        sismografo2.setEstacion(estacion2);
        sismografo2 = sismografoRepository.save(sismografo2);

        // Serie temporal para Sismógrafo 2
        SerieTemporal serie2 = new SerieTemporal();
        serie2.setFechaHoraInicio(LocalDateTime.of(2024, 11, 3, 9, 15, 0));
        serie2.setFechaHoraAdquisicion(LocalDateTime.of(2024, 11, 3, 9, 20, 0));
        serie2.setFrecuenciaMuestreo(200.0);
        serie2.setSismografo(sismografo2);
        serie2 = serieTemporalRepository.save(serie2);

    // Crear muestras para serie2
    crearMuestrasConDetalles(serie2, 4, 200.0);

        System.out.println("   ✓ Creadas 2 estaciones sismológicas");
        System.out.println("   ✓ Creados 2 sismógrafos");
        System.out.println("   ✓ Creadas 2 series temporales");
    }

    /**
     * Crea una cantidad de muestras con detalles (velocidad, frecuencia, longitud) para una serie temporal dada.
     * Cada muestra tendrá varios detalles asociados a los TipoDato existentes que aplican.
     */
    private void crearMuestrasConDetalles(SerieTemporal serie, int cantidadMuestras, double frecuenciaBase) {
        List<TipoDato> tipos = tipoDatoRepository.findAll();
        LocalDateTime inicioSerie = serie.getFechaHoraInicio();

        for (int i = 0; i < cantidadMuestras; i++) {
            MuestraSismica muestra = new MuestraSismica();
            muestra.setFechaHoraInicio(inicioSerie.plusSeconds(i * 5));
            muestra.setFechaHoraFin(inicioSerie.plusSeconds(i * 5 + 5));
            muestra.setFechaHoraInicioFk(muestra.getFechaHoraInicio());
            muestra.setSerieTemporal(serie);
            muestra = muestraSismicaRepository.save(muestra);

            // Crear detalles según tipos de dato relevantes
            for (TipoDato tipo : tipos) {
                if (tipo.sosVelocidadLongitudFrecuencia()) {
                    DetalleMuestraSismica detalle = new DetalleMuestraSismica();
                    detalle.setMuestraSismica(muestra);
                    detalle.setTipoDato(tipo);
                    // Valores simulados
                    detalle.setVelocidadOnda( (i + 1) * 0.8 + frecuenciaBase / 500 );
                    detalle.setFrecuenciaOnda( frecuenciaBase + i * 2 );
                    detalle.setLongitud( 10.0 + i );
                    detalleMuestraSismicaRepository.save(detalle);
                }
            }
        }
    }

    private void crearEventosSismicosCompletos() {
        System.out.println("🌍 Creando eventos sísmicos completos...");

        // Obtener catálogos
        ClasificacionSismo clasificacion = clasificacionRepository.findAll().stream().findFirst().orElse(null);
        OrigenDeGeneracion origen = origenRepository.findAll().stream().findFirst().orElse(null);
        AlcanceSismo alcance = alcanceRepository.findAll().stream().findFirst().orElse(null);

        // Evento 1: Pendiente de Revisión (con serie temporal de Mendoza)
        EventoSismico evento1 = new EventoSismico();
        evento1.setFechaHoraOcurrencia(LocalDateTime.of(2024, 11, 1, 14, 30, 0));
        evento1.setCoordenadas("-32.8895,-68.8458");
        evento1.setValorMagnitud(5.2f);
        evento1.setEstadoActual(com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.PTE_DE_REVISION);
        evento1.setClasificacionSismo(clasificacion);
        evento1.setOrigenGeneracion(origen);
        evento1.setAlcanceSismo(alcance);
        evento1 = eventoRepository.save(evento1);

        // Asociar serie temporal al evento
        SerieTemporal serie1 = serieTemporalRepository.findAll().stream()
                .filter(s -> s.getSismografo().getEstacion().getNombre().contains("Mendoza"))
                .findFirst().orElse(null);
        if (serie1 != null) {
            serie1.setEventoSismico(evento1);
            serieTemporalRepository.save(serie1);
        }

        CambioEstado cambio1 = new CambioEstado(
            com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.PTE_DE_REVISION, 
            LocalDateTime.of(2024, 11, 1, 14, 35, 0)
        );
        cambio1.setEventoSismico(evento1);
        cambioEstadoRepository.save(cambio1);

        // Evento 2: Auto-detectado (con estación San Juan)
        EventoSismico evento2 = new EventoSismico();
        evento2.setFechaHoraOcurrencia(LocalDateTime.of(2024, 11, 3, 9, 15, 0));
        evento2.setCoordenadas("-31.5375,-68.5364");
        evento2.setValorMagnitud(4.8f);
        evento2.setEstadoActual(com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.AUTO_DETECTADO);
        evento2.setClasificacionSismo(clasificacion);
        evento2.setOrigenGeneracion(origen);
        evento2.setAlcanceSismo(alcance);
        evento2 = eventoRepository.save(evento2);

        // Asociar serie temporal al evento
        SerieTemporal serie2 = serieTemporalRepository.findAll().stream()
                .filter(s -> s.getSismografo().getEstacion().getNombre().contains("San Juan"))
                .findFirst().orElse(null);
        if (serie2 != null) {
            serie2.setEventoSismico(evento2);
            serieTemporalRepository.save(serie2);
        }

        CambioEstado cambio2 = new CambioEstado(
            com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.AUTO_DETECTADO, 
            LocalDateTime.of(2024, 11, 3, 9, 16, 0)
        );
        cambio2.setEventoSismico(evento2);
        cambioEstadoRepository.save(cambio2);

        // Evento 3: Pendiente de Revisión
        EventoSismico evento3 = new EventoSismico();
        evento3.setFechaHoraOcurrencia(LocalDateTime.of(2024, 11, 5, 16, 45, 0));
        evento3.setCoordenadas("-29.4131,-66.8558");
        evento3.setValorMagnitud(3.5f);
        evento3.setEstadoActual(com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.PTE_DE_REVISION);
        evento3.setClasificacionSismo(clasificacion);
        evento3.setOrigenGeneracion(origen);
        evento3.setAlcanceSismo(alcance);
        evento3 = eventoRepository.save(evento3);

        CambioEstado cambio3 = new CambioEstado(
            com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.PTE_DE_REVISION, 
            LocalDateTime.of(2024, 11, 5, 16, 50, 0)
        );
        cambio3.setEventoSismico(evento3);
        cambioEstadoRepository.save(cambio3);

        // Evento 4: Confirmado (ya procesado - con historial de cambios)
        EventoSismico evento4 = new EventoSismico();
        evento4.setFechaHoraOcurrencia(LocalDateTime.of(2024, 10, 28, 11, 20, 0));
        evento4.setCoordenadas("-32.5,-69.0");
        evento4.setValorMagnitud(6.1f);
        evento4.setEstadoActual(com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.CONFIRMADO);
        evento4.setClasificacionSismo(clasificacion);
        evento4.setOrigenGeneracion(origen);
        evento4.setAlcanceSismo(alcance);
        evento4 = eventoRepository.save(evento4);

        // Primer cambio: PTE_DE_REVISION (ya cerrado)
        CambioEstado cambio4a = new CambioEstado(
            com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.PTE_DE_REVISION, 
            LocalDateTime.of(2024, 10, 28, 11, 25, 0)
        );
        cambio4a.setFechaFin(LocalDateTime.of(2024, 10, 28, 14, 30, 0));
        cambio4a.setEventoSismico(evento4);
        cambioEstadoRepository.save(cambio4a);

        // Segundo cambio: CONFIRMADO (actual)
        CambioEstado cambio4b = new CambioEstado(
            com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.CONFIRMADO, 
            LocalDateTime.of(2024, 10, 28, 14, 30, 0)
        );
        cambio4b.setEventoSismico(evento4);
        cambioEstadoRepository.save(cambio4b);

        // Evento 5: Pendiente de Revisión (reciente)
        EventoSismico evento5 = new EventoSismico();
        evento5.setFechaHoraOcurrencia(LocalDateTime.now().minusHours(2));
        evento5.setCoordenadas("-33.0,-68.5");
        evento5.setValorMagnitud(4.2f);
        evento5.setEstadoActual(com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.PTE_DE_REVISION);
        evento5.setClasificacionSismo(clasificacion);
        evento5.setOrigenGeneracion(origen);
        evento5.setAlcanceSismo(alcance);
        evento5 = eventoRepository.save(evento5);

        CambioEstado cambio5 = new CambioEstado(
            com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.PTE_DE_REVISION, 
            LocalDateTime.now().minusHours(2).plusMinutes(5)
        );
        cambio5.setEventoSismico(evento5);
        cambioEstadoRepository.save(cambio5);

        System.out.println("   ✓ Creados 5 eventos sísmicos completos");
        System.out.println("   ✓ 3 eventos en estado PTE_DE_REVISION");
        System.out.println("   ✓ 1 evento AUTO_DETECTADO");
        System.out.println("   ✓ 1 evento CONFIRMADO");
        System.out.println("   ✓ Todos con estaciones sismológicas asociadas");
    }
}
