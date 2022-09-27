package com.oscar.oscarcalcadostest.crud.controller.categoriacalcado;

import com.oscar.oscarcalcadostest.controller.CategoriaController;
import com.oscar.oscarcalcadostest.dto.CategoriaDTO;
import com.oscar.oscarcalcadostest.model.CategoriaCalcado;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Order(1)
public class CadastrarCategoriaTest {

    @Autowired
    CategoriaController categoriaController;

    @Test
    public void testSalvarController(){
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setDescricaoCategoria("CATESTE");

        ResponseEntity<Object> responseEntity = categoriaController.salvarCategoria(categoriaDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody() instanceof CategoriaCalcado).isTrue();
    }

}
