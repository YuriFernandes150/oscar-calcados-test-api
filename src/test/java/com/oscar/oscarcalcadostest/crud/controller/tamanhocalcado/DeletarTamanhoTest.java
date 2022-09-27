package com.oscar.oscarcalcadostest.crud.controller.tamanhocalcado;

import com.oscar.oscarcalcadostest.controller.TamanhoCalcadoController;
import com.oscar.oscarcalcadostest.model.TamanhoCalcado;
import com.oscar.oscarcalcadostest.repository.TamanhoCalcadoRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Order(16)
public class DeletarTamanhoTest {
    @Autowired
    TamanhoCalcadoController tamanhoCalcadoController;
    @Autowired
    TamanhoCalcadoRepository tamanhoCalcadoRepository;

    @Test
    public void testDeletarControler(){
        Optional<TamanhoCalcado> tamanhoCadastradoOptional = tamanhoCalcadoRepository.findFirstByOrderByIdTamanhoCalcadoDesc();
        assertThat(tamanhoCadastradoOptional.isPresent()).isTrue();
        tamanhoCadastradoOptional.ifPresent(tamanhoCalcado -> {
            assertThat(tamanhoCalcadoController.deletarTamanho(tamanhoCalcado.getIdTamanhoCalcado()).getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(tamanhoCalcadoController.buscarTamanhoPorId(tamanhoCalcado.getIdTamanhoCalcado()).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        });
    }

}
