package com.oscar.oscarcalcadostest.repository;

import com.oscar.oscarcalcadostest.model.TamanhoCalcado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TamanhoCalcadoRepository extends JpaRepository<TamanhoCalcado, Integer> {

    boolean existsByDescricaoTamanhoCalcado(String tamanhoCalcado);

    List<TamanhoCalcado> findAllByDescricaoTamanhoCalcadoContainsIgnoreCase(String tamanhoCalcado);
}
