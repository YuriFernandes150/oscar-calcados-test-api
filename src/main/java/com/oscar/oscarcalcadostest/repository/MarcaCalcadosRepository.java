package com.oscar.oscarcalcadostest.repository;

import com.oscar.oscarcalcadostest.model.MarcaCalcado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarcaCalcadosRepository extends JpaRepository<MarcaCalcado, Integer> {

    boolean existsByDescricaoMarcaCalcado(String descricaoMarca);

    List<MarcaCalcado> findAllByDescricaoMarcaCalcadoContainingIgnoreCase(String descricaoMarca);

    Optional<MarcaCalcado> findTopByDescricaoMarcaCalcado(String descricaoMarca);
}
