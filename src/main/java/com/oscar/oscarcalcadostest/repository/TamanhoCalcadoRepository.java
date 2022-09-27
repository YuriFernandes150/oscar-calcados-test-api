package com.oscar.oscarcalcadostest.repository;

import com.oscar.oscarcalcadostest.model.TamanhoCalcado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Este repositório do JPA é responsável por todas as operações com o banco de dados.<br>
 * Ele contém vários métodos prontos, além da possibilidade de criar as suas queries através <br>
 * de métodos nomeados.
 * @author YuriFernandes150
 */
@Repository
public interface TamanhoCalcadoRepository extends JpaRepository<TamanhoCalcado, Integer> {

    /**
     * Verifica se um tamanho existe através da descrição.
     * @param tamanhoCalcado Descrição do tamanho na sua busca.
     * @return Um simples booleano, se o tamanho existe (true) ou não (false).
     */
    boolean existsByDescricaoTamanhoCalcado(String tamanhoCalcado);

    /**
     * Buscar todos os tamanhos por (descricao_tamanho_calcado).
     * @param tamanhoCalcado Descrição do tamanho na sua busca.
     * @return Retorna uma lista contendo todos os tamanhos encontrados.
     */
    List<TamanhoCalcado> findAllByDescricaoTamanhoCalcadoContainingIgnoreCase(String tamanhoCalcado);

    /**
     * Buscar um único tamanho por (descricao_tamanho_calcado).
     * @param descricaoTamanhoCalcado Descrição do tamanho na sua busca.
     * @return Retorna um Optional, que pode conter um objeto TamanhoCalcado.
     */
    Optional<TamanhoCalcado> findByDescricaoTamanhoCalcado(String descricaoTamanhoCalcado);

    Optional<TamanhoCalcado> findFirstByOrderByIdTamanhoCalcadoDesc();

}
