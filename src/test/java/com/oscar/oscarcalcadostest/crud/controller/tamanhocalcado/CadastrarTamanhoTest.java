package com.oscar.oscarcalcadostest.crud.controller.tamanhocalcado;

import com.oscar.oscarcalcadostest.controller.TamanhoCalcadoController;
import com.oscar.oscarcalcadostest.dto.TamanhoDTO;
import com.oscar.oscarcalcadostest.model.TamanhoCalcado;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Order(13)
public class CadastrarTamanhoTest {

    @Autowired
    TamanhoCalcadoController tamanhoCalcadoController;

    @Test
    public void testSalvarController(){

        TamanhoDTO tamanhoDTO = new TamanhoDTO();
        tamanhoDTO.setDescricaoTamanhoCalcado("87");

        ResponseEntity<Object> responseEntity = tamanhoCalcadoController.salvarTamanhoCalcado(tamanhoDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody() instanceof TamanhoCalcado).isTrue();

    }

}
