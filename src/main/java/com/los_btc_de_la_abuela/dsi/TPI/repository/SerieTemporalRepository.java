package com.los_btc_de_la_abuela.dsi.TPI.repository;

import com.los_btc_de_la_abuela.dsi.TPI.model.SerieTemporal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieTemporalRepository extends JpaRepository<SerieTemporal, Long> {
}
