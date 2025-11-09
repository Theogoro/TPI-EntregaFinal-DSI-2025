package com.los_btc_de_la_abuela.dsi.TPI.repository;

import com.los_btc_de_la_abuela.dsi.TPI.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByDni(String dni);
    Optional<Empleado> findByUsuarioId(Long usuarioId);
}
