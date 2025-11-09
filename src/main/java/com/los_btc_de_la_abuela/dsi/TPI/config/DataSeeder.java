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

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Usuario responsable por defecto para los cambios de estado
    private Usuario usuarioAnalista;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Solo crear datos si la base de datos está vacía
        if (eventoRepository.count() > 0) {
            System.out.println("⏭️  La base de datos ya tiene datos. Saltando inicialización.");
            return;
        }

        System.out.println("🌱 Iniciando carga de datos de prueba...");

        // 0. Crear usuario analista
        crearUsuarioAnalista();

        // 1. Crear catálogos básicos
        crearCatalogos();

        // 2. Crear estaciones sismológicas con sismógrafos y series temporales
        crearEstacionesSismologicas();

        // 3. Crear eventos sísmicos completos con datos registrados
        crearEventosSismicosCompletos();

        System.out.println("✅ Datos de prueba cargados exitosamente!");
        System.out.println("📝 Se crearon eventos sísmicos de prueba con estados:");
        System.out.println("   - PTE_DE_REVISION: para probar el endpoint /eventos-sin-revision");
        System.out.println("   - AUTO_DETECTADO: eventos recién detectados");
        System.out.println("   - CONFIRMADO: eventos ya procesados");
        System.out.println("   - Con estaciones sismológicas y datos registrados completos");
    }

    private void crearUsuarioAnalista() {
        System.out.println("👤 Creando usuario analista...");
        usuarioAnalista = new Usuario();
        usuarioAnalista.setNombre("Analista Sistema");
        usuarioAnalista.setContrasena("$2a$10$abcdefghijklmnopqrstuv"); // Mock BCrypt hash
        usuarioAnalista.setEmail("analista@sistema.com");
        usuarioAnalista.setActivo(true);
        usuarioAnalista.setFechaAlta(LocalDateTime.now());
        usuarioAnalista = usuarioRepository.save(usuarioAnalista);
        System.out.println("   ✓ Usuario analista creado");
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

        // Sismógrafo 1 para Estación 1
        Sismografo sismografo1 = new Sismografo();
        sismografo1.setCodigo("SIS-MDZ-001");
        sismografo1.setNombre("Sismógrafo Principal Mendoza");
        sismografo1.setModelo("SR-300");
        sismografo1.setFabricante("SeismoTech");
        sismografo1.setNroSerie("SR300-2020-001");
        sismografo1.setActivo(true);
        sismografo1.setEstacion(estacion1);
        sismografo1 = sismografoRepository.save(sismografo1);

        // Serie temporal 1 para Sismógrafo 1
        SerieTemporal serie1 = new SerieTemporal();
        serie1.setFechaHoraInicio(LocalDateTime.of(2024, 11, 1, 14, 30, 0));
        serie1.setFechaHoraAdquisicion(LocalDateTime.of(2024, 11, 1, 14, 35, 0));
        serie1.setFrecuenciaMuestreo(100.0);
        serie1.setSismografo(sismografo1);
        serie1 = serieTemporalRepository.save(serie1);
        crearMuestrasConDetalles(serie1, 10, 100.0);

        // Sismógrafo 2 para Estación 1 (secundario)
        Sismografo sismografo1b = new Sismografo();
        sismografo1b.setCodigo("SIS-MDZ-002");
        sismografo1b.setNombre("Sismógrafo Secundario Mendoza");
        sismografo1b.setModelo("SR-250");
        sismografo1b.setFabricante("SeismoTech");
        sismografo1b.setNroSerie("SR250-2021-003");
        sismografo1b.setActivo(true);
        sismografo1b.setEstacion(estacion1);
        sismografo1b = sismografoRepository.save(sismografo1b);

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
        crearMuestrasConDetalles(serie2, 15, 200.0);

        // Estación 3: La Rioja
        EstacionSismologica estacion3 = new EstacionSismologica();
        estacion3.setCodigo("EST-LR-001");
        estacion3.setNombre("Estación La Rioja Sur");
        estacion3.setLatitud(-29.4131);
        estacion3.setLongitud(-66.8558);
        estacion3.setAltitud(520.0);
        estacion3.setActiva(true);
        estacion3 = estacionRepository.save(estacion3);

        // Sismógrafo para Estación 3
        Sismografo sismografo3 = new Sismografo();
        sismografo3.setCodigo("SIS-LR-001");
        sismografo3.setNombre("Sismógrafo La Rioja");
        sismografo3.setModelo("SR-350");
        sismografo3.setFabricante("GeophysicsLab");
        sismografo3.setNroSerie("SR350-2021-005");
        sismografo3.setActivo(true);
        sismografo3.setEstacion(estacion3);
        sismografo3 = sismografoRepository.save(sismografo3);

        // Serie temporal para Sismógrafo 3
        SerieTemporal serie3 = new SerieTemporal();
        serie3.setFechaHoraInicio(LocalDateTime.of(2024, 11, 5, 16, 45, 0));
        serie3.setFechaHoraAdquisicion(LocalDateTime.of(2024, 11, 5, 16, 50, 0));
        serie3.setFrecuenciaMuestreo(150.0);
        serie3.setSismografo(sismografo3);
        serie3 = serieTemporalRepository.save(serie3);
        crearMuestrasConDetalles(serie3, 8, 150.0);

        // Estación 4: Catamarca
        EstacionSismologica estacion4 = new EstacionSismologica();
        estacion4.setCodigo("EST-CAT-001");
        estacion4.setNombre("Estación Catamarca Centro");
        estacion4.setLatitud(-28.4696);
        estacion4.setLongitud(-65.7795);
        estacion4.setAltitud(550.0);
        estacion4.setActiva(true);
        estacion4 = estacionRepository.save(estacion4);

        // Sismógrafo para Estación 4
        Sismografo sismografo4 = new Sismografo();
        sismografo4.setCodigo("SIS-CAT-001");
        sismografo4.setNombre("Sismógrafo Catamarca");
        sismografo4.setModelo("SR-400");
        sismografo4.setFabricante("SeismoTech");
        sismografo4.setNroSerie("SR400-2020-008");
        sismografo4.setActivo(true);
        sismografo4.setEstacion(estacion4);
        sismografo4 = sismografoRepository.save(sismografo4);

        // Serie temporal para Sismógrafo 4
        SerieTemporal serie4 = new SerieTemporal();
        serie4.setFechaHoraInicio(LocalDateTime.now().minusHours(2));
        serie4.setFechaHoraAdquisicion(LocalDateTime.now().minusHours(2).plusMinutes(5));
        serie4.setFrecuenciaMuestreo(180.0);
        serie4.setSismografo(sismografo4);
        serie4 = serieTemporalRepository.save(serie4);
        crearMuestrasConDetalles(serie4, 12, 180.0);

        System.out.println("   ✓ Creadas 4 estaciones sismológicas");
        System.out.println("   ✓ Creados 5 sismógrafos");
        System.out.println("   ✓ Creadas 4 series temporales");
        System.out.println("   ✓ Creadas múltiples muestras sísmicas con detalles completos");
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

            // Crear detalles para cada tipo de dato
            for (TipoDato tipo : tipos) {
                DetalleMuestraSismica detalle = new DetalleMuestraSismica();
                detalle.setMuestraSismica(muestra);
                detalle.setTipoDato(tipo);
                
                // Valores simulados más realistas con variación
                double variacion = Math.sin(i * 0.5) * 0.3; // Variación sinusoidal
                
                if (tipo.getNombre().equalsIgnoreCase("Velocidad")) {
                    detalle.setVelocidadOnda((i + 1) * 1.2 + frecuenciaBase / 400 + variacion);
                    detalle.setFrecuenciaOnda(frecuenciaBase + i * 3);
                    detalle.setLongitud(15.0 + i * 0.5);
                } else if (tipo.getNombre().equalsIgnoreCase("Aceleración")) {
                    detalle.setVelocidadOnda((i + 1) * 0.9 + frecuenciaBase / 300 + variacion * 1.5);
                    detalle.setFrecuenciaOnda(frecuenciaBase + i * 2.5);
                    detalle.setLongitud(12.0 + i * 0.8);
                } else if (tipo.getNombre().equalsIgnoreCase("Desplazamiento")) {
                    detalle.setVelocidadOnda((i + 1) * 0.6 + frecuenciaBase / 500 + variacion * 0.8);
                    detalle.setFrecuenciaOnda(frecuenciaBase + i * 1.5);
                    detalle.setLongitud(10.0 + i * 1.2);
                }
                
                detalleMuestraSismicaRepository.save(detalle);
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
            LocalDateTime.of(2024, 11, 1, 14, 35, 0),
            usuarioAnalista
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
            LocalDateTime.of(2024, 11, 3, 9, 16, 0),
            usuarioAnalista
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
            LocalDateTime.of(2024, 11, 5, 16, 50, 0),
            usuarioAnalista
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
            LocalDateTime.of(2024, 10, 28, 11, 25, 0),
            usuarioAnalista
        );
        cambio4a.setFechaFin(LocalDateTime.of(2024, 10, 28, 14, 30, 0));
        cambio4a.setEventoSismico(evento4);
        cambioEstadoRepository.save(cambio4a);

        // Segundo cambio: CONFIRMADO (actual)
        CambioEstado cambio4b = new CambioEstado(
            com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum.CONFIRMADO, 
            LocalDateTime.of(2024, 10, 28, 14, 30, 0),
            usuarioAnalista
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
            LocalDateTime.now().minusHours(2).plusMinutes(5),
            usuarioAnalista
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
