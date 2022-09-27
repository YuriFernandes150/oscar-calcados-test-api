package com.oscar.oscarcalcadostest.crud.controller.marcacalcado;

import com.oscar.oscarcalcadostest.controller.MarcaCalcadoController;
import com.oscar.oscarcalcadostest.model.MarcaCalcado;
import com.oscar.oscarcalcadostest.repository.MarcaCalcadosRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Order(12)
public class DeletarMarcaTest {

    @Autowired
    MarcaCalcadoController marcaCalcadoController;
    @Autowired
    MarcaCalcadosRepository marcaCalcadosRepository;


    @Test
    public void testDeletarMarcaController(){
        Optional<MarcaCalcado> marcaCadastradaOptional = marcaCalcadosRepository.findFirstByOrderByIdMarcaCalcadoDesc();
        assertThat(marcaCadastradaOptional.isPresent()).isTrue();
        marcaCadastradaOptional.ifPresent(marcaCalcado -> {
            assertThat(marcaCalcadoController.deletarMarca(marcaCalcado.getIdMarcaCalcado()).getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(marcaCalcadoController.buscarMarcasPorId(marcaCalcado.getIdMarcaCalcado()).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        });
    }

}
