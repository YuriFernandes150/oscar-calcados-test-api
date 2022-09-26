package com.oscar.oscarcalcadostest.TestCrud;

import com.oscar.oscarcalcadostest.model.CorCalcado;
import com.oscar.oscarcalcadostest.repository.CorCalcadoRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CorCalcadoTests {

    @Autowired
    CorCalcadoRepository corCalcadoRepository;

    @Test
    @Order(1)
    public void testSalvarCor(){

        CorCalcado corCalcado = new CorCalcado();
        corCalcado.setDescricaoCorCalcado("1234");
        corCalcado.setDataCadastroCor(LocalDateTime.now());
        corCalcadoRepository.save(corCalcado);
        assertThat(corCalcadoRepository.existsByDescricaoCorCalcado(corCalcado.getDescricaoCorCalcado()));

    }

    @Test
    @Order(2)
    public void testBuscarTodasAsCores(){

        List<CorCalcado> corCalcadoList = corCalcadoRepository.findAll();
        assertThat(corCalcadoList).size().isGreaterThan(0);

    }

    @Test
    @Order(3)
    public void buscarCoresPorDescricao(){
        List<CorCalcado> corCalcadoList = corCalcadoRepository.findAllByDescricaoCorCalcadoContainsIgnoreCase("1234");
        assertThat(corCalcadoList).size().isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void testDelete(){

        Optional<CorCalcado> corTeste = corCalcadoRepository.findByDescricaoCorCalcado("1234");

        if(corTeste.isPresent()){
            corCalcadoRepository.delete(corTeste.get());
            Optional<CorCalcado> corAposDelete = corCalcadoRepository.findById(corTeste.get().getIdCorCalcado());
            assertThat(!corAposDelete.isPresent());

        }

    }

}
