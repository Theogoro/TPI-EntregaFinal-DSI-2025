package com.los_btc_de_la_abuela.dsi.TPI.repository;

import com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum;
import com.los_btc_de_la_abuela.dsi.TPI.model.CambioEstado;
import com.los_btc_de_la_abuela.dsi.TPI.model.EventoSismico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad CambioEstado.
 * Gestiona el historial de cambios de estado de los eventos sísmicos.
 */
@Repository
public interface CambioEstadoRepository extends JpaRepository<CambioEstado, Long> {

    // /**
    //  * Busca todos los cambios de estado de un evento sísmico específico.
    //  * @param eventoSismico el evento sísmico
    //  * @return lista de cambios de estado ordenados por fecha
    //  */
    // List<CambioEstado> findByEventoSismicoOrderByIdDesc(EventoSismico eventoSismico);

    // /**
    //  * Busca el cambio de estado actual de un evento (sin fecha de fin).
    //  * @param eventoSismico el evento sísmico
    //  * @return el cambio de estado actual, si existe
    //  */
    // @Query("SELECT c FROM CambioEstado c WHERE c.eventoSismico = :evento AND c.fin IS NULL")
    // Optional<CambioEstado> findCambioActual(@Param("evento") EventoSismico eventoSismico);

    // /**
    //  * Busca cambios de estado por estado inicial.
    //  * @param estado el estado inicial
    //  * @return lista de cambios de estado
    //  */
    // List<CambioEstado> findByInicio(EstadoEnum estado);

    // /**
    //  * Busca cambios de estado por estado final.
    //  * @param estado el estado final
    //  * @return lista de cambios de estado
    //  */
    // List<CambioEstado> findByFin(EstadoEnum estado);
}
