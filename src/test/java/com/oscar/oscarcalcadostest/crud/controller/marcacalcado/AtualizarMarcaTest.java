package com.oscar.oscarcalcadostest.crud.controller.marcacalcado;

import com.oscar.oscarcalcadostest.controller.MarcaCalcadoController;
import com.oscar.oscarcalcadostest.dto.MarcaCalcadoDTO;
import com.oscar.oscarcalcadostest.model.MarcaCalcado;
import com.oscar.oscarcalcadostest.repository.MarcaCalcadosRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Order(10)
public class AtualizarMarcaTest {

    @Autowired
    MarcaCalcadoController marcaCalcadoController;
    @Autowired
    MarcaCalcadosRepository marcaCalcadosRepository;


    @Test
    public void testAtualizarController(){
        Optional<MarcaCalcado> marcaCadastradaOptional = marcaCalcadosRepository.findFirstByOrderByIdMarcaCalcadoDesc();
        assertThat(marcaCadastradaOptional.isPresent()).isTrue();
        marcaCadastradaOptional.ifPresent(marcaCadastrada ->{
            MarcaCalcadoDTO marcaCalcadoDTO = new MarcaCalcadoDTO();
            marcaCalcadoDTO.setDescricaoMarcaCalcado("MAR12345");

            ResponseEntity<Object> responseEntity = marcaCalcadoController.atualizarMarca(marcaCadastrada.getIdMarcaCalcado(),marcaCalcadoDTO);

            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(responseEntity.getBody() instanceof MarcaCalcado).isTrue();
        });
    }

}
