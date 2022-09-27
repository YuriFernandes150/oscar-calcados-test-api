package com.oscar.oscarcalcadostest.crud.controller.calcado;

import com.oscar.oscarcalcadostest.controller.CalcadoController;
import com.oscar.oscarcalcadostest.model.*;
import com.oscar.oscarcalcadostest.repository.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Order(20)
public class DeletarCalcadoTest {

    @Autowired
    CalcadoController calcadoController;
    @Autowired
    CalcadoRepository calcadoRepository;
    @Autowired
    MarcaCalcadosRepository marcaCalcadosRepository;
    @Autowired
    TamanhoCalcadoRepository tamanhoCalcadoRepository;
    @Autowired
    CategoriaCalcadosRepository categoriaCalcadosRepository;
    @Autowired
    CorCalcadoRepository corCalcadoRepository;

    @Test
    public void testDeletarCalcadoController(){
        Optional<Calcado> calcadoCadastrado = calcadoRepository.findByCodCalcado("00");
        calcadoCadastrado.ifPresent(calcado -> {

            assertThat(calcadoController.deletarCalcado(calcado.getIdCalcado()).getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(calcadoController.buscarCalcadoPorId(calcado.getIdCalcado()).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

            marcaCalcadosRepository.delete(calcado.getMarcaCalcado());
            categoriaCalcadosRepository.delete(calcado.getCategoriaCalcado());
            tamanhoCalcadoRepository.delete(calcado.getTamanhoCalcado());
            corCalcadoRepository.delete(calcado.getCorCalcado());

            Optional<MarcaCalcado> marcaDeletada = marcaCalcadosRepository.findById(calcado.getMarcaCalcado().getIdMarcaCalcado());
            assertThat(marcaDeletada.isPresent()).isFalse();

            Optional<CategoriaCalcado> categoriaDeletada = categoriaCalcadosRepository.findById(calcado.getCategoriaCalcado().getIdCategoriaCalcado());
            assertThat(categoriaDeletada.isPresent()).isFalse();

            Optional<TamanhoCalcado> tamanhoDeletado = tamanhoCalcadoRepository.findById(calcado.getTamanhoCalcado().getIdTamanhoCalcado());
            assertThat(tamanhoDeletado.isPresent()).isFalse();

            Optional<CorCalcado> corDeletada = corCalcadoRepository.findById(calcado.getCorCalcado().getIdCorCalcado());
            assertThat(corDeletada.isPresent()).isFalse();
        });
    }

}
