package com.oscar.oscarcalcadostest.crud.controller.tamanhocalcado;

import com.oscar.oscarcalcadostest.controller.TamanhoCalcadoController;
import com.oscar.oscarcalcadostest.repository.TamanhoCalcadoRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Order(15)
public class BuscarTamanhosTest {

    @Autowired
    TamanhoCalcadoController tamanhoCalcadoController;
    @Autowired
    TamanhoCalcadoRepository tamanhoCalcadoRepository;

    @Test
    public void testBuscarTamanhosController(){
        Pageable pageable = Pageable.ofSize(10);
        assertThat(tamanhoCalcadoController.buscarTodosOsTamanhos(pageable).getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    public void testBuscarTamanhosDescricaoController(){
        assertThat(tamanhoCalcadoController.buscarTamanhosPorDescricao("8").getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
