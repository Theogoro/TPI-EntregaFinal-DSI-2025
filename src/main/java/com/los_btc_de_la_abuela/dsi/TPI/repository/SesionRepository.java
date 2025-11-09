package com.los_btc_de_la_abuela.dsi.TPI.repository;

import com.los_btc_de_la_abuela.dsi.TPI.model.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SesionRepository extends JpaRepository<Sesion, Long> {
    Optional<Sesion> findByTokenAndActivaTrue(String token);
    Optional<Sesion> findTopByUsuarioIdAndActivaTrueOrderByFechaHoraInicioDesc(Long usuarioId);
}
