package com.oscar.oscarcalcadostest.crud.controller.corcalcado;

import com.oscar.oscarcalcadostest.controller.CorCalcadoController;
import com.oscar.oscarcalcadostest.dto.CorCalcadoDTO;
import com.oscar.oscarcalcadostest.model.CorCalcado;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Order(5)
public class CadastrarCorTest {

    @Autowired
    CorCalcadoController corCalcadoController;

    @Test
    public void testSalvarController(){

        CorCalcadoDTO corCalcadoDTO = new CorCalcadoDTO();
        corCalcadoDTO.setDescricaoCorCalcado("COR1234");

        ResponseEntity<Object> responseEntity =corCalcadoController.salvarCorCalcado(corCalcadoDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody() instanceof CorCalcado).isTrue();

    }

}
