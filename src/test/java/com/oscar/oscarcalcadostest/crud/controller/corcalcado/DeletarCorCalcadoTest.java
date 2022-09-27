package com.oscar.oscarcalcadostest.crud.controller.corcalcado;

import com.oscar.oscarcalcadostest.controller.CorCalcadoController;
import com.oscar.oscarcalcadostest.model.CorCalcado;
import com.oscar.oscarcalcadostest.repository.CorCalcadoRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Order(8)
public class DeletarCorCalcadoTest {

    @Autowired
    CorCalcadoController corCalcadoController;
    @Autowired
    CorCalcadoRepository corCalcadoRepository;


    @Test
    public void testDeletarCor(){
        Optional<CorCalcado> corCadastradaOptional = corCalcadoRepository.findFirstByOrderByIdCorCalcadoDesc();
        assertThat(corCadastradaOptional.isPresent()).isTrue();
        corCadastradaOptional.ifPresent(corCalcado -> {

            assertThat(corCalcadoController.deletarCor(corCalcado.getIdCorCalcado()).getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(corCalcadoController.buscarCorPorId(corCalcado.getIdCorCalcado()).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        });
    }

}
