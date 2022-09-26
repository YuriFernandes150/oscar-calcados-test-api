package com.oscar.oscarcalcadostest.repository;

import com.oscar.oscarcalcadostest.model.Calcado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalcadoRepository extends JpaRepository<Calcado, Integer>, JpaSpecificationExecutor<Calcado> {
    Optional<Calcado> findByCodCalcado(String codCalcado);

    boolean existsByCodCalcado(String codCalcado);
}
