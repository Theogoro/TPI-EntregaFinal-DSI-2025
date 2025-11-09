package com.los_btc_de_la_abuela.dsi.TPI.repository;

import com.los_btc_de_la_abuela.dsi.TPI.model.CambioEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad CambioEstado.
 * Gestiona el historial de cambios de estado de los eventos sísmicos.
 */
@Repository
public interface CambioEstadoRepository extends JpaRepository<CambioEstado, Long> {
}
