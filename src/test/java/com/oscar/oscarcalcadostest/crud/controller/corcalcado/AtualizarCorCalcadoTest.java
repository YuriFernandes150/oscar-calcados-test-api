package com.oscar.oscarcalcadostest.crud.controller.corcalcado;

import com.oscar.oscarcalcadostest.controller.CorCalcadoController;
import com.oscar.oscarcalcadostest.dto.CorCalcadoDTO;
import com.oscar.oscarcalcadostest.model.CorCalcado;
import com.oscar.oscarcalcadostest.repository.CorCalcadoRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Order(6)
public class AtualizarCorCalcadoTest {

    @Autowired
    CorCalcadoController corCalcadoController;
    @Autowired
    CorCalcadoRepository corCalcadoRepository;

    @Test
    public void testAtualizarController(){
        Optional<CorCalcado> corCadastradaOptional = corCalcadoRepository.findFirstByOrderByIdCorCalcadoDesc();
        assertThat(corCadastradaOptional.isPresent()).isTrue();
        corCadastradaOptional.ifPresent(corCalcado -> {

            CorCalcadoDTO corCalcadoDTO = new CorCalcadoDTO();
            corCalcadoDTO.setDescricaoCorCalcado("COR12345");

            ResponseEntity<Object> responseEntity = corCalcadoController.atualizarCor(corCalcado.getIdCorCalcado(), corCalcadoDTO);

            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(responseEntity.getBody() instanceof CorCalcado).isTrue();

        });
    }

}
