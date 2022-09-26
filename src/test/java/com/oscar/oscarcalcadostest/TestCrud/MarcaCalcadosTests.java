package com.oscar.oscarcalcadostest.TestCrud;

import com.oscar.oscarcalcadostest.model.MarcaCalcado;
import com.oscar.oscarcalcadostest.repository.MarcaCalcadosRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MarcaCalcadosTests {

	@Autowired
	MarcaCalcadosRepository marcaCalcadosRepository;

	@Test
	@Order(1)
	public void testSalvarMarca(){

		MarcaCalcado marcaCalcado = new MarcaCalcado();
		marcaCalcado.setDescricaoMarcaCalcado("1234");
		marcaCalcado.setDataCadastroMarca(LocalDateTime.now());

		marcaCalcadosRepository.save(marcaCalcado);

		assertThat(marcaCalcadosRepository.existsByDescricaoMarcaCalcado(marcaCalcado.getDescricaoMarcaCalcado()));

	}

	@Test
	@Order(2)
	public void testBuscarTodasMarcas(){

		List<MarcaCalcado> marcaCalcadoList = marcaCalcadosRepository.findAll();

		assertThat(marcaCalcadoList).size().isGreaterThan(0);

	}

	@Test
	@Order(3)
	public void testBuscarMarcaPorDescricao(){

		List<MarcaCalcado> marcaCalcadoList = marcaCalcadosRepository.findAllByDescricaoMarcaCalcadoContainingIgnoreCase("1234");

		assertThat(marcaCalcadoList).size().isGreaterThan(0);

	}

	@Test
	@Order(4)
	public void testDelete(){

		Optional<MarcaCalcado> marcaCalcado = marcaCalcadosRepository.findTopByDescricaoMarcaCalcado("1234");
		if(marcaCalcado.isPresent()){
			marcaCalcadosRepository.delete(marcaCalcado.get());
			Optional<MarcaCalcado> marcaCalcadoOptional = marcaCalcadosRepository.findById(marcaCalcado.get().getIdMarcaCalcado());
			assertThat(!marcaCalcadoOptional.isPresent());
		}



	}



}
