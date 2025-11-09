package com.los_btc_de_la_abuela.dsi.TPI.repository;

import com.los_btc_de_la_abuela.dsi.TPI.model.RevisionManual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RevisionManualRepository extends JpaRepository<RevisionManual, Long> {
    List<RevisionManual> findByEventoSismicoId(Long eventoSismicoId);
    List<RevisionManual> findByEmpleadoId(Long empleadoId);
}
