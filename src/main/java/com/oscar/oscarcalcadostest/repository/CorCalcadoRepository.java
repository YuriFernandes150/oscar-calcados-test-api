package com.oscar.oscarcalcadostest.repository;

import com.oscar.oscarcalcadostest.model.CorCalcado;
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
public interface CorCalcadoRepository extends JpaRepository<CorCalcado, Integer> {

    /**
     * Verifica se uma cor existe através da descrição.
     * @param descricaoCorCalcado Descrição da cor na sua busca.
     * @return Um simples booleano, se a cor existe (true) ou não (false).
     */
    boolean existsByDescricaoCorCalcado(String descricaoCorCalcado);

    /**
     * Buscar todas as cores por (descricao_cor).
     * @param descricaoCorCalcado Descrição da cor na sua busca.
     * @return Retorna uma lista contendo todas as cores encontradas.
     */
    List<CorCalcado> findAllByDescricaoCorCalcadoContainingIgnoreCase(String descricaoCorCalcado);

    /**
     * Buscar uma única cor por (descricao_cor).
     * @param descricaoCorCalcado Descrição da cor na sua busca.
     * @return Retorna um Optional, que pode conter um objeto CorCalcado.
     */
    Optional<CorCalcado> findByDescricaoCorCalcado(String descricaoCorCalcado);

    /**
     * Busca a ultima cor cadastrada
     * @return Retorna um Optional, que pode conter um objeto CorCalcado.
     */
    Optional<CorCalcado> findFirstByOrderByIdCorCalcadoDesc();
}
