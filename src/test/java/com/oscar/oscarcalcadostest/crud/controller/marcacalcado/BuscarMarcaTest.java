package com.oscar.oscarcalcadostest.crud.controller.marcacalcado;

import com.oscar.oscarcalcadostest.controller.MarcaCalcadoController;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Order(11)
public class BuscarMarcaTest {

    @Autowired
    MarcaCalcadoController marcaCalcadoController;

    @Test
    public void testBuscarTodasAsMarcasController(){
        Pageable pageable = Pageable.ofSize(10);
        assertThat(marcaCalcadoController.buscarTodasAsMarcas(pageable).getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    public void testBuscarMarcasPorDescricaoController(){
        assertThat(marcaCalcadoController.buscarMarcasPorDescricao("12").getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
