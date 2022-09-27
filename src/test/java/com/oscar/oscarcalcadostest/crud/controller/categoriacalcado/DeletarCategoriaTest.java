package com.oscar.oscarcalcadostest.crud.controller.categoriacalcado;

import com.oscar.oscarcalcadostest.controller.CategoriaController;
import com.oscar.oscarcalcadostest.model.CategoriaCalcado;
import com.oscar.oscarcalcadostest.repository.CategoriaCalcadosRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Order(4)
public class DeletarCategoriaTest {

    @Autowired
    CategoriaController categoriaController;
    @Autowired
    CategoriaCalcadosRepository categoriaCalcadosRepository;

    @Test
    public void testDeletarCategoriaController() {
        Optional<CategoriaCalcado> categoriaCadastradaOptional = categoriaCalcadosRepository.findFirstByOrderByIdCategoriaCalcadoDesc();
        assertThat(categoriaCadastradaOptional.isPresent()).isTrue();
        categoriaCadastradaOptional.ifPresent(categoriaCalcado -> {
            assertThat(categoriaController.deletarCategoria(categoriaCalcado.getIdCategoriaCalcado()).getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(categoriaController.buscarCategoriasPorId(categoriaCalcado.getIdCategoriaCalcado()).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        });
    }
}
