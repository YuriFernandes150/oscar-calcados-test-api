package com.oscar.oscarcalcadostest.repository;

import com.oscar.oscarcalcadostest.model.MarcaCalcado;
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
public interface MarcaCalcadosRepository extends JpaRepository<MarcaCalcado, Integer> {

    /**
     * Verifica se uma marca existe através da descrição.
     * @param descricaoMarca Descrição da marca na sua busca.
     * @return Um simples booleano, se a marca existe (true) ou não (false).
     */
    boolean existsByDescricaoMarcaCalcado(String descricaoMarca);

    /**
     * Buscar todas as marcas por (descricao_marca).
     * @param descricaoMarca Descrição da marca na sua busca.
     * @return Retorna uma lista contendo todas as marcas encontradas.
     */
    List<MarcaCalcado> findAllByDescricaoMarcaCalcadoContainingIgnoreCase(String descricaoMarca);

    /**
     * Buscar a última marca cadastrada.
     * @return Retorna um Optional, que pode conter um objeto MarcaCalcado.
     */
    Optional<MarcaCalcado> findFirstByOrderByIdMarcaCalcadoDesc();
}
