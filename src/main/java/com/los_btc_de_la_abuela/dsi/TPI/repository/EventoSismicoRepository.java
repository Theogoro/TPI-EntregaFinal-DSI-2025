package com.los_btc_de_la_abuela.dsi.TPI.repository;

import com.los_btc_de_la_abuela.dsi.TPI.enums.EstadoEnum;
import com.los_btc_de_la_abuela.dsi.TPI.model.EventoSismico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio JPA para la entidad EventoSismico.
 * Proporciona métodos de consulta personalizados para gestionar eventos sísmicos.
 */
@Repository
public interface EventoSismicoRepository extends JpaRepository<EventoSismico, Long> {
    /**
     * Busca todos los eventos sísmicos.
     * @return lista de todos los eventos
     */
    @Query("SELECT e FROM EventoSismico e")
    List<EventoSismico> getAllEventos();
}
