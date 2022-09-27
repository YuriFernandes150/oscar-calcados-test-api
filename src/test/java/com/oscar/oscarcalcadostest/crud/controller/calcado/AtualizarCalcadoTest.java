package com.oscar.oscarcalcadostest.crud.controller.calcado;

import com.oscar.oscarcalcadostest.controller.CalcadoController;
import com.oscar.oscarcalcadostest.dto.CalcadoDTO;
import com.oscar.oscarcalcadostest.model.Calcado;
import com.oscar.oscarcalcadostest.repository.CalcadoRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Order(18)
public class AtualizarCalcadoTest {

    @Autowired
    CalcadoController calcadoController;
    @Autowired
    CalcadoRepository calcadoRepository;

    @Test
    public void testAtualizarCalcadoController(){
        Optional<Calcado> calcadoOptional = calcadoRepository.findFirstByOrderByIdCalcadoDesc();
        assertThat(calcadoOptional.isPresent()).isTrue();

        CalcadoDTO calcadoDTO = new CalcadoDTO();

        calcadoDTO.setCodCalcado("00");
        calcadoDTO.setDescricaoCalcado("CALCTESTE1234");
        calcadoDTO.setQtdEstoque(10.0);
        calcadoDTO.setPrecoCalcado(19.95);
        calcadoOptional.ifPresent(calcado -> {
            calcadoDTO.setCategoriaCalcado(calcado.getCategoriaCalcado());
            calcadoDTO.setCorCalcado(calcado.getCorCalcado());
            calcadoDTO.setMarcaCalcado(calcado.getMarcaCalcado());
            calcadoDTO.setTamanhoCalcado(calcado.getTamanhoCalcado());

            ResponseEntity<Object> atualizarCalcado = calcadoController.atualizarCalcado(calcado.getIdCalcado(), calcadoDTO);
            assertThat(atualizarCalcado.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(atualizarCalcado.getBody() instanceof Calcado).isTrue();
        });
    }

}
