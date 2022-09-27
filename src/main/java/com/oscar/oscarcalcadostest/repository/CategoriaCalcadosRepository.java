package com.oscar.oscarcalcadostest.repository;

import com.oscar.oscarcalcadostest.model.CategoriaCalcado;
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
public interface CategoriaCalcadosRepository extends JpaRepository<CategoriaCalcado, Integer> {
    /**
     * Verifica se uma categoria existe através da descrição.
     * @param descricaoCategoria Descrição da categoria na sua busca.
     * @return Um simples booleano, se a categoria existe (true) ou não (false).
     */
    boolean existsByDescricaoCategoria(String descricaoCategoria);

    /**
     * Buscar todas as categorias por (descricao_categoria).
     * @param descricaoCategoria Descrição da categoria na sua busca.
     * @return Retorna uma lista contendo todas as categorias encontradas.
     */
    List<CategoriaCalcado> findAllByDescricaoCategoriaContainingIgnoreCase(String descricaoCategoria);

    /**
     * Buscar uma única categoria por (descricao_categoria).
     * @param descricaoCategoria Descrição da categoria na sua busca.
     * @return Retorna um Optional, que pode conter um objeto CategoriaCalcado.
     */
    Optional<CategoriaCalcado> findByDescricaoCategoria(String descricaoCategoria);

    /**
     * Buscar a ultima categoria cadastrada
     * @return Retorna um Optional, que pode conter um objeto CategoriaCalcado.
     */
    Optional<CategoriaCalcado> findFirstByOrderByIdCategoriaCalcadoDesc();
}
