package com.oscar.oscarcalcadostest.repository;

import com.oscar.oscarcalcadostest.model.CorCalcado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorCalcadoRepository extends JpaRepository<CorCalcado, Integer> {

    boolean existsByDescricaoCorCalcado(String descricaoCorCalcado);

    List<CorCalcado> findAllByDescricaoCorCalcadoContainsIgnoreCase(String descricaoCorCalcado);
}
