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

    // /**
    //  * Busca eventos sísmicos por estado actual.
    //  * @param estado el estado a buscar
    //  * @return lista de eventos con ese estado
    //  */
    // List<EventoSismico> findByEstadoActual(EstadoEnum estado);

    // /**
    //  * Busca eventos sísmicos en un rango de fechas.
    //  * @param inicio fecha de inicio
    //  * @param fin fecha de fin
    //  * @return lista de eventos en el rango
    //  */
    // @Query("SELECT e FROM EventoSismico e WHERE e.fechaHoraOcurrencia BETWEEN :inicio AND :fin")
    // List<EventoSismico> findByFechaRange(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

    // /**
    //  * Busca eventos sísmicos con magnitud mayor o igual a un valor.
    //  * @param magnitud magnitud mínima
    //  * @return lista de eventos con magnitud >= magnitud
    //  */
    // List<EventoSismico> findByValorMagnitudGreaterThanEqual(Float magnitud);

    // /**
    //  * Busca eventos sísmicos pendientes de revisión (AutoDetectado o PteDeRevision).
    //  * @return lista de eventos pendientes de revisión
    //  */
    // @Query("SELECT e FROM EventoSismico e WHERE e.estadoActual IN ('AUTO_DETECTADO', 'PTE_DE_REVISION')")
    // List<EventoSismico> findEventosPendientesRevision();

    // /**
    //  * Busca eventos sísmicos bloqueados en revisión por un revisor.
    //  * @return lista de eventos bloqueados en revisión
    //  */
    // @Query("SELECT e FROM EventoSismico e WHERE e.estadoActual = 'BLOQUEADO_EN_REVISION'")
    // List<EventoSismico> findEventosBloqueadosEnRevision();

    /**
     * Busca eventos sísmicos sin revisión (que no tienen ninguna RevisionManual asociada).
     * @return lista de eventos sin revisión
     */
    @Query("SELECT e FROM EventoSismico e WHERE e.id NOT IN (SELECT DISTINCT rm.eventoSismico.id FROM RevisionManual rm)")
    List<EventoSismico> buscarEventosSinRevision();
    
    /**
     * Busca todos los eventos sísmicos.
     * @return lista de todos los eventos
     */
    @Query("SELECT e FROM EventoSismico e")
    List<EventoSismico> getAllEventos();
}
