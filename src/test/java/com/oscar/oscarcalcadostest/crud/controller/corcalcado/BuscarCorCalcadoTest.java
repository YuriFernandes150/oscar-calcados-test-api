package com.oscar.oscarcalcadostest.crud.controller.corcalcado;

import com.oscar.oscarcalcadostest.controller.CorCalcadoController;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Order(7)
public class BuscarCorCalcadoTest {

    @Autowired
    CorCalcadoController corCalcadoController;

    @Test
    public void testBuscarTodasAsCoresController(){

        Pageable pageable = Pageable.ofSize(10);
        assertThat(corCalcadoController.buscarTodasAsCores(pageable).getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void testBuscarCoresDescricao(){
        assertThat(corCalcadoController.buscarCoresPorDescricao("COR12345").getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
