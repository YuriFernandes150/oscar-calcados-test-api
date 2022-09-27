package com.oscar.oscarcalcadostest.service;

import com.oscar.oscarcalcadostest.model.MarcaCalcado;
import com.oscar.oscarcalcadostest.repository.MarcaCalcadosRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * O Service é a rota principal de comunicação entre a aplicação e os Repositories,
 * ele contém as chamadas dos métodos do repo e faz os Casts ncessários para realizar as operações nos Controllers.<br>
 * Esse Service representa os métodos do banco de dados da tbl_marca_calcado.
 */
@Service
public class MarcaCalcadoService {

    /**
     * O Repository é necessário para construir os métodos de comunicação com o banco.<br>
     * Também é possível utilizar a Anotation @Autowired.
     */
    final MarcaCalcadosRepository marcaCalcadosRepository;

    public MarcaCalcadoService(MarcaCalcadosRepository marcaCalcadosRepository){
        this.marcaCalcadosRepository = marcaCalcadosRepository;
    }

    /**
     * Salva uma nova marca no banco de dados.
     * @param marcaCalcado Objeto MarcaCalcado, com todos os campos devidamente preenchidos.
     * @return Um Objeto MarcaCalcado que foi registrado no banco.
     */
    @Transactional
    public MarcaCalcado save(MarcaCalcado marcaCalcado) {
        return marcaCalcadosRepository.save(marcaCalcado);
    }

    /**
     * Verifica se uma marca existe no banco de dados.
     * @param descricaoMarca Descrição da marca na busca.
     * @return verdadeiro se a marca existe, ou falso se não existe.
     */
    public boolean existsByDescricaoMarca(String descricaoMarca) {
        return marcaCalcadosRepository.existsByDescricaoMarcaCalcado(descricaoMarca);
    }

    /**
     * Busca todas as marcas existentes no banco de dados.
     * @param pageable Objeto do Spring que permite a separação em páginas e filtros de ordenação.
     * @return Um Objeto Page do Spring que pode conter uma lista de registros.
     */
    public Page<MarcaCalcado> findAll(Pageable pageable) {
        return marcaCalcadosRepository.findAll(pageable);
    }

    /**
     * Busca uma marca por um ID especifico.
     * @param id Id da marca na busca.
     * @return Um Optional que pode conter uma marca.
     */
    public Optional<MarcaCalcado> findById(Integer id) {
        return marcaCalcadosRepository.findById(id);
    }

    /**
     * Deleta uma marca permanetemente do banco de dados. <b>Use com precaução</b>
     * @param marcaCalcado Objeto MarcaCalcado a ser deletado.
     */
    public void delete(MarcaCalcado marcaCalcado) {
        marcaCalcadosRepository.delete(marcaCalcado);
    }

    /**
     * Busca todas as marcas por descrição.
     * @param descricaoMarca Descrição da marca fornecida na busca.
     * @return Uma lista de objetos MarcaCalcado.
     */
    public List<MarcaCalcado> findByDescription(String descricaoMarca) {
        return marcaCalcadosRepository.findAllByDescricaoMarcaCalcadoContainingIgnoreCase(descricaoMarca);
    }
}
