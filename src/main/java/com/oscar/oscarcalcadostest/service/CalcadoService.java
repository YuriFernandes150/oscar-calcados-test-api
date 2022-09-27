package com.oscar.oscarcalcadostest.service;

import com.oscar.oscarcalcadostest.model.Calcado;
import com.oscar.oscarcalcadostest.repository.CalcadoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * O Service é a rota principal de comunicação entre a aplicação e os Repositories,
 * ele contém as chamadas dos métodos do repo e faz os Casts ncessários para realizar as operações nos Controllers.<br>
 * Esse Service representa os métodos do banco de dados da tbl_calcados.
 */
@Service
public class CalcadoService {

    /**
     * O Repository é necessário para construir os métodos de comunicação com o banco.<br>
     * Também é possível utilizar a Anotation @Autowired.
     */
    final CalcadoRepository calcadoRepository;

    public CalcadoService(CalcadoRepository calcadoRepository){
        this.calcadoRepository = calcadoRepository;
    }

    /**
     * Busca calçados no BD através do codCalcado (cod_calcado)
     * @param codCalcado Código do calçado na busca.
     * @return Um Optional que pode conter um objeto Calcado.
     */
    public Optional<Calcado> findByCodCalcado(String codCalcado) {
        return calcadoRepository.findByCodCalcado(codCalcado);
    }

    /**
     * Salva um calçado no bando de dados
     * @param calcado Um objeto calçado com todos os campos corretamente preenchidos.
     * @return O objeto calçado salvo.
     */
    public Calcado save(Calcado calcado) {
        return calcadoRepository.save(calcado);
    }

    /**
     * Busca um calçado através do idCalcado (id_calcado)
     * @param idCalcado ID Integer utilizado para fazer a busca.
     * @return Um Optional que pode conter um objeto Calcado.
     */
    public Optional<Calcado> findById(Integer idCalcado) {
        return calcadoRepository.findById(idCalcado);
    }

    /**
     * Deleta um calçado do bando permanentemente. <b>Use com precaução</b>
     * @param calcado Objeto Calcado devidamente preenchido.
     */
    public void delete(Calcado calcado) {
        calcadoRepository.delete(calcado);
    }

    /**
     * Busca todos os calçados registrados no banco, recebendo parametros de paginação do Spring.
     * @param pageable Objeto de paginação do Spring. Torna possível separação por páginas e também filtros de ordenação.
     * @return Objeto Page do Spring, que pode conter uma lista de registros.
     */
    public Page<Calcado> findAll(Pageable pageable) {
        return calcadoRepository.findAll(pageable);
    }

    /**
     * Busca todos os registros que batem com a Specification fornecida, permitindo filtragem por diferentes combinações de campo.
     * @param specifications Objeto de Especificação de query do JPA.
     * @param pageable Objeto de paginação do Spring. Torna possível separação por páginas e também filtros de ordenação.
     * @return Objeto Page do Spring, que pode conter uma lista de registros.
     */
    public Page<Calcado> findAll(Specification<Calcado> specifications, Pageable pageable){

        return calcadoRepository.findAll(specifications, pageable);

    }

    /**
     * Verifica se um calçado com o código fornecido existe.
     * @param codCalcado Código do calçado na busca.
     * @return verdadeiro ou falso dependendo se o calçado existe ou não.
     */
    public boolean existsByCodCalcado(String codCalcado) {
        return calcadoRepository.existsByCodCalcado(codCalcado);
    }
}
