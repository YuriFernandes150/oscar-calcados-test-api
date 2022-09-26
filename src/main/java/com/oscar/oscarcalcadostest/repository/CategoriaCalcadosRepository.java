package com.oscar.oscarcalcadostest.repository;

import com.oscar.oscarcalcadostest.model.CategoriaCalcado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaCalcadosRepository extends JpaRepository<CategoriaCalcado, Integer> {
    boolean existsByDescricaoCategoria(String descricaoCategoria);

    List<CategoriaCalcado> findAllByDescricaoCategoriaContainsIgnoreCase(String descricaoCategoria);
}
