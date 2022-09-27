package com.oscar.oscarcalcadostest.crud.controller.calcado;

import com.oscar.oscarcalcadostest.controller.CalcadoController;
import com.oscar.oscarcalcadostest.dto.CalcadosBuscaCustomDTO;
import com.oscar.oscarcalcadostest.model.Calcado;
import com.oscar.oscarcalcadostest.repository.CalcadoRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Order(19)
public class BuscarCalcadosTest {

    @Autowired
    CalcadoController calcadoController;
    @Autowired
    CalcadoRepository calcadoRepository;

    @Test
    public void testBuscarTodosCalcadosController(){
        Pageable pageable = Pageable.ofSize(10);
        assertThat(calcadoController.buscarTodosOsCalcados(pageable).getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    public void testBuscarCalcadosCodController(){
        assertThat(calcadoController.buscarCalcadosPorCod("00").getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @ParameterizedTest
    @MethodSource("parametrosCustom")
    public void testBuscarCalcadosCustom(CalcadosBuscaCustomDTO param){

        Optional<Calcado> calcadoOptional = calcadoRepository.findFirstByOrderByIdCalcadoDesc();
        assertThat(calcadoOptional.isPresent()).isTrue();
        calcadoOptional.ifPresent(calcado -> {
            Pageable pageable = Pageable.ofSize(10);
            assertThat(calcadoController.buscarCalcadosCustom(param, pageable).getStatusCode()).isEqualTo(HttpStatus.OK);
            Page<Calcado> calcadoPage = calcadoController.buscarCalcadosCustom(param, pageable).getBody();
            assertThat(calcadoPage).isNotNull();
            assertThat(calcadoPage.getContent().isEmpty()).isFalse();
        });

    }
    private static Stream<Arguments> parametrosCustom(){
        return Stream.of(
                //Busca somente por descricao
                Arguments.of(new CalcadosBuscaCustomDTO("CALC",null,null,null,
                        null,null,null,null,null,null,
                        null)),
                //Busca por FKs
                Arguments.of(new CalcadosBuscaCustomDTO(null,"TEST123","TEST123","TEST123",
                        "TEST123",null,null,null,null,null,
                        null)),
                //Busca por Precos maiores que
                Arguments.of(new CalcadosBuscaCustomDTO(null,null,null,null,
                        null,null,10.0,null,null,null,
                        null)),
                //Busca por Precos menores que
                Arguments.of(new CalcadosBuscaCustomDTO(null,null,null,null,
                        null,null,null,50.0,null,null,
                        null)),
                //Busca por Datas depois de
                Arguments.of(new CalcadosBuscaCustomDTO(null,null,null,null,
                        null,null,null,null,null, LocalDateTime.of(2022, Month.SEPTEMBER, 21,00,00),
                        null)),
                //Busca por Datas antes de
                Arguments.of(new CalcadosBuscaCustomDTO(null,null,null,null,
                        null,null,null,null,null,null,
                        LocalDateTime.now())),
                //Busca entre datas.
                Arguments.of(new CalcadosBuscaCustomDTO(null,null,null,null,
                        null,null,null,null,null,
                        LocalDateTime.of(2022, Month.SEPTEMBER, 21,00,00), LocalDateTime.now()))


        );

    }

}
