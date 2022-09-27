package com.oscar.oscarcalcadostest.crud.controller.calcado;

import com.oscar.oscarcalcadostest.controller.CalcadoController;
import com.oscar.oscarcalcadostest.dto.CalcadoDTO;
import com.oscar.oscarcalcadostest.model.*;
import com.oscar.oscarcalcadostest.repository.CategoriaCalcadosRepository;
import com.oscar.oscarcalcadostest.repository.CorCalcadoRepository;
import com.oscar.oscarcalcadostest.repository.MarcaCalcadosRepository;
import com.oscar.oscarcalcadostest.repository.TamanhoCalcadoRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Order(17)
public class CadastrarCalcadoTest {

    @Autowired
    CalcadoController calcadoController;
    @Autowired
    MarcaCalcadosRepository marcaCalcadosRepository;
    @Autowired
    TamanhoCalcadoRepository tamanhoCalcadoRepository;
    @Autowired
    CategoriaCalcadosRepository categoriaCalcadosRepository;
    @Autowired
    CorCalcadoRepository corCalcadoRepository;

    @Test
    public void testSalvarCalcadoController(){

        CalcadoDTO calcadoDTO = new CalcadoDTO();
        CorCalcado corCalcado = new CorCalcado();
        TamanhoCalcado tamanhoCalcado = new TamanhoCalcado();
        CategoriaCalcado categoriaCalcado = new CategoriaCalcado();
        MarcaCalcado marcaCalcado = new MarcaCalcado();

        marcaCalcado.setDescricaoMarcaCalcado("TEST123");
        marcaCalcado.setDataCadastroMarca(LocalDateTime.now());
        marcaCalcadosRepository.save(marcaCalcado);
        Optional<MarcaCalcado> marcaCalcadoOptional = marcaCalcadosRepository.findFirstByOrderByIdMarcaCalcadoDesc();
        marcaCalcadoOptional.ifPresent(value -> marcaCalcado.setIdMarcaCalcado(value.getIdMarcaCalcado()));
        assertThat(marcaCalcado.getIdMarcaCalcado()).isNotNull();

        tamanhoCalcado.setDescricaoTamanhoCalcado("TEST123");
        tamanhoCalcado.setDataCadastroTamanho(LocalDateTime.now());
        tamanhoCalcadoRepository.save(tamanhoCalcado);
        Optional<TamanhoCalcado> tamanhoCalcadoOptional = tamanhoCalcadoRepository.findFirstByOrderByIdTamanhoCalcadoDesc();
        tamanhoCalcadoOptional.ifPresent(value -> tamanhoCalcado.setIdTamanhoCalcado(value.getIdTamanhoCalcado()));
        assertThat(tamanhoCalcado.getIdTamanhoCalcado()).isNotNull();

        corCalcado.setDescricaoCorCalcado("TEST123");
        corCalcado.setDataCadastroCor(LocalDateTime.now());
        corCalcadoRepository.save(corCalcado);
        Optional<CorCalcado> corCalcadoOptional = corCalcadoRepository.findFirstByOrderByIdCorCalcadoDesc();
        corCalcadoOptional.ifPresent(value -> corCalcado.setIdCorCalcado(value.getIdCorCalcado()));
        assertThat(corCalcado.getIdCorCalcado()).isNotNull();

        categoriaCalcado.setDescricaoCategoria("TEST123");
        categoriaCalcado.setDataCadastroCategoria(LocalDateTime.now());
        categoriaCalcadosRepository.save(categoriaCalcado);
        Optional<CategoriaCalcado> categoriaCalcadoOptional = categoriaCalcadosRepository.findFirstByOrderByIdCategoriaCalcadoDesc();
        categoriaCalcadoOptional.ifPresent(value -> categoriaCalcado.setIdCategoriaCalcado(value.getIdCategoriaCalcado()));
        assertThat(categoriaCalcado.getIdCategoriaCalcado()).isNotNull();

        calcadoDTO.setCodCalcado("00");
        calcadoDTO.setDescricaoCalcado("CALCTESTE123");
        calcadoDTO.setQtdEstoque(10.0);
        calcadoDTO.setPrecoCalcado(19.95);
        calcadoDTO.setCategoriaCalcado(categoriaCalcado);
        calcadoDTO.setCorCalcado(corCalcado);
        calcadoDTO.setMarcaCalcado(marcaCalcado);
        calcadoDTO.setTamanhoCalcado(tamanhoCalcado);

        ResponseEntity<Object> salvarCalcado = calcadoController.salvarCalcado(calcadoDTO);

        assertThat(salvarCalcado.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(salvarCalcado.getBody() instanceof Calcado).isTrue();

    }

}
