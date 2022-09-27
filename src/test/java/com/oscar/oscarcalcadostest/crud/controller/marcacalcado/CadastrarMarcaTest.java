package com.oscar.oscarcalcadostest.crud.controller.marcacalcado;

import com.oscar.oscarcalcadostest.controller.MarcaCalcadoController;
import com.oscar.oscarcalcadostest.dto.MarcaCalcadoDTO;
import com.oscar.oscarcalcadostest.model.MarcaCalcado;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Order(9)
public class CadastrarMarcaTest {

    @Autowired
    MarcaCalcadoController marcaCalcadoController;

    @Test
    public void testSalvarController(){

        MarcaCalcadoDTO marcaCalcadoDTO = new MarcaCalcadoDTO();
        marcaCalcadoDTO.setDescricaoMarcaCalcado("MAR1234");

        ResponseEntity<Object> responseEntity = marcaCalcadoController.salvarMarca(marcaCalcadoDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody() instanceof MarcaCalcado).isTrue();

    }

}
