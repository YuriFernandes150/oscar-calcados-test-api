package com.oscar.oscarcalcadostest.crud.controller.categoriacalcado;

import com.oscar.oscarcalcadostest.controller.CategoriaController;
import com.oscar.oscarcalcadostest.dto.CategoriaDTO;
import com.oscar.oscarcalcadostest.model.CategoriaCalcado;
import com.oscar.oscarcalcadostest.repository.CategoriaCalcadosRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Order(2)
public class AtualizarCategoriaTest {
    @Autowired
    CategoriaController categoriaController;
    @Autowired
    CategoriaCalcadosRepository categoriaCalcadosRepository;


    @Test
    public void testAtualizarController(){
        Optional<CategoriaCalcado> categoriaCadastradaOptional = categoriaCalcadosRepository.findFirstByOrderByIdCategoriaCalcadoDesc();
        assertThat(categoriaCadastradaOptional.isPresent()).isTrue();
        categoriaCadastradaOptional.ifPresent(categoriaCalcado -> {

            CategoriaDTO categoriaDTO = new CategoriaDTO();
            categoriaDTO.setDescricaoCategoria("CATESTE2");

            ResponseEntity<Object> responseEntity = categoriaController.atualizarCategoria(categoriaCalcado.getIdCategoriaCalcado(), categoriaDTO);

            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(responseEntity.getBody() instanceof CategoriaCalcado).isTrue();

        });
    }

}

