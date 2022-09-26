package com.oscar.oscarcalcadostest.TestCrud;

import com.oscar.oscarcalcadostest.model.TamanhoCalcado;
import com.oscar.oscarcalcadostest.repository.TamanhoCalcadoRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TamanhoCalcadosTests {

    @Autowired
    TamanhoCalcadoRepository tamanhoCalcadoRepository;

    @Test
    @Order(1)
    public void testSalvarTamanho(){

        TamanhoCalcado tamanhoCalcado = new TamanhoCalcado();

        tamanhoCalcado.setDescricaoTamanhoCalcado("11");
        tamanhoCalcado.setDataCadastroTamanho(LocalDateTime.now());

        tamanhoCalcadoRepository.save(tamanhoCalcado);

        assertThat(tamanhoCalcadoRepository.existsByDescricaoTamanhoCalcado(tamanhoCalcado.getDescricaoTamanhoCalcado()));

    }
    @Test
    @Order(2)
    public void testBuscarTodosOsTamanhos(){
        List<TamanhoCalcado> tamanhoCalcadoList = tamanhoCalcadoRepository.findAll();
        assertThat(tamanhoCalcadoList).size().isGreaterThan(0);
    }

    @Test
    @Order(3)
    public void testBuscarTamanhosPorDescricao(){

        List<TamanhoCalcado> tamanhoCalcadoList = tamanhoCalcadoRepository.findAllByDescricaoTamanhoCalcadoContainsIgnoreCase("11");
        assertThat(tamanhoCalcadoList).size().isGreaterThan(0);

    }

    @Test
    @Order(4)
    public void testDelete(){

        Optional<TamanhoCalcado> ultimoTamanhoCalcado = tamanhoCalcadoRepository.findByDescricaoTamanhoCalcado("11");
        if(ultimoTamanhoCalcado.isPresent()){
            tamanhoCalcadoRepository.delete(ultimoTamanhoCalcado.get());
            Optional<TamanhoCalcado> ultimoTamanhoCalcadoAposDelete = tamanhoCalcadoRepository.findById(ultimoTamanhoCalcado.get().getIdTamanhoCalcado());
            assertThat(!ultimoTamanhoCalcadoAposDelete.isPresent());
        }



    }



}
