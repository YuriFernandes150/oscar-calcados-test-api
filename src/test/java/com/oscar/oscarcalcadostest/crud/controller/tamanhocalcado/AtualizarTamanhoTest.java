package com.oscar.oscarcalcadostest.crud.controller.tamanhocalcado;

import com.oscar.oscarcalcadostest.controller.TamanhoCalcadoController;
import com.oscar.oscarcalcadostest.dto.TamanhoDTO;
import com.oscar.oscarcalcadostest.model.TamanhoCalcado;
import com.oscar.oscarcalcadostest.repository.TamanhoCalcadoRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Order(14)
public class AtualizarTamanhoTest {

    @Autowired
    TamanhoCalcadoController tamanhoCalcadoController;
    @Autowired
    TamanhoCalcadoRepository tamanhoCalcadoRepository;

    @Test
    public void testAtualizarController(){
        Optional<TamanhoCalcado> tamanhoCadastradoOptional = tamanhoCalcadoRepository.findFirstByOrderByIdTamanhoCalcadoDesc();
        assertThat(tamanhoCadastradoOptional.isPresent()).isTrue();
        tamanhoCadastradoOptional.ifPresent(tamanhoCadastrado ->{
            TamanhoDTO tamanhoDTO = new TamanhoDTO();
            tamanhoDTO.setDescricaoTamanhoCalcado("88");

            ResponseEntity<Object> responseEntity = tamanhoCalcadoController.atualizarTamanhoCalcado(tamanhoCadastrado.getIdTamanhoCalcado(),tamanhoDTO);

            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(responseEntity.getBody() instanceof TamanhoCalcado).isTrue();
        });
    }

}
