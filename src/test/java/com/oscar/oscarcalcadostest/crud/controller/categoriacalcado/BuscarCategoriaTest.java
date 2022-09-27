package com.oscar.oscarcalcadostest.crud.controller.categoriacalcado;

import com.oscar.oscarcalcadostest.controller.CategoriaController;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Order(3)
public class BuscarCategoriaTest {

    @Autowired
    CategoriaController categoriaController;

    @Test
    public void testBuscarTodasCategoriasController(){
        Pageable pageable = Pageable.ofSize(10);
        assertThat(categoriaController.buscarTodasAsCategorias(pageable).getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testBuscarCategoriasDescricaoController(){
        assertThat(categoriaController.buscarCategoriasPorDescricao("CATESTE2").getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
