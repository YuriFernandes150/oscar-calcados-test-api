package com.oscar.oscarcalcadostest.TestCrud;

import com.oscar.oscarcalcadostest.model.CategoriaCalcado;
import com.oscar.oscarcalcadostest.repository.CategoriaCalcadosRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CategoriaCalcadosTests {

    @Autowired
    CategoriaCalcadosRepository categoriaCalcadosRepository;

    @Test
    @Order(1)
    public void testSalvarCategoria(){

        CategoriaCalcado categoriaCalcado = new CategoriaCalcado();
        categoriaCalcado.setDescricaoCategoria("CATESTE");
        categoriaCalcado.setDataCadastroCategoria(LocalDateTime.now());

        categoriaCalcadosRepository.save(categoriaCalcado);

        assertThat(categoriaCalcadosRepository.existsByDescricaoCategoria(categoriaCalcado.getDescricaoCategoria()));

    }

    @Test
    @Order(2)
    public void testBuscarTodasAsCategorias(){
        List<CategoriaCalcado> categoriaCalcadoList = categoriaCalcadosRepository.findAll();
        assertThat(categoriaCalcadoList).size().isGreaterThan(0);
    }

    @Test
    @Order(3)
    public void testBuscarCategoriasDescricao(){
        List<CategoriaCalcado> categoriaCalcadoList = categoriaCalcadosRepository.findAllByDescricaoCategoriaContainsIgnoreCase("CATESTE");
        assertThat(categoriaCalcadoList).size().isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void testDelete(){
        Optional<CategoriaCalcado> ultimaCategoria = categoriaCalcadosRepository.findByDescricaoCategoria("CATESTE");

        if(ultimaCategoria.isPresent()){

            categoriaCalcadosRepository.delete(ultimaCategoria.get());
            Optional<CategoriaCalcado> categoriaAposDelete = categoriaCalcadosRepository.findById(ultimaCategoria.get().getIdCategoriaCalcado());

            assertThat(!categoriaAposDelete.isPresent());

        }
    }

}
