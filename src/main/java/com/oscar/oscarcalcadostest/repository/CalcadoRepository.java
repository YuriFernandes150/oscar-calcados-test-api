package com.oscar.oscarcalcadostest.repository;

import com.oscar.oscarcalcadostest.model.Calcado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Este repositório do JPA é responsável por todas as operações com o banco de dados.<br>
 * Ele contém vários métodos prontos, além da possibilidade de criar as suas queries através <br>
 * de métodos nomeados.
 * @author YuriFernandes150
 */
@Repository
public interface CalcadoRepository extends JpaRepository<Calcado, Integer>, JpaSpecificationExecutor<Calcado> {
    /**
     * Buscar todos os calçados por (cod_calcado).
     * @param codCalcado Código do calçado na sua busca.
     * @return Retorna um Optional, que pode conter um objeto da classe Calcado.
     */
    Optional<Calcado> findByCodCalcado(String codCalcado);

    /**
     * Verifica se um calçado existe através do código.
     * @param codCalcado Código do calçado na sua busca.
     * @return Um simples booleano, se o calçado existe (true) ou não (false).
     */
    boolean existsByCodCalcado(String codCalcado);

    /**
     * Busca o ultimo calcado registrado no banco
     * @return Retorna um Optional, que pode conter um objeto da classe Calcado.
     */
    Optional<Calcado> findFirstByOrderByIdCalcadoDesc();
}
