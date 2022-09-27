package com.oscar.oscarcalcadostest.service;

import com.oscar.oscarcalcadostest.model.TamanhoCalcado;
import com.oscar.oscarcalcadostest.repository.TamanhoCalcadoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * O Service é a rota principal de comunicação entre a aplicação e os Repositories,
 * ele contém as chamadas dos métodos do repo e faz os Casts ncessários para realizar as operações nos Controllers.<br>
 * Esse Service representa os métodos do banco de dados da tbl_tamanho_calcado.
 */
@Service
public class TamanhoCalcadoService {

    /**
     * O Repository é necessário para construir os métodos de comunicação com o banco.<br>
     * Também é possível utilizar a Anotation @Autowired.
     */
    final TamanhoCalcadoRepository tamanhoCalcadoRepository;

    public TamanhoCalcadoService(TamanhoCalcadoRepository tamanhoCalcadoRepository){
        this.tamanhoCalcadoRepository = tamanhoCalcadoRepository;
    }


    /**
     * Verifica se um tamanho existe no banco de dados pela descrição.
     * @param descricaoTamanhoCalcado Descrição do tamanho na busca.
     * @return verdadeiro se o tamanho existe, ou falso se não existe.
     */
    public boolean existsByDescricaoTamanhoCalcado(String descricaoTamanhoCalcado) {
        return tamanhoCalcadoRepository.existsByDescricaoTamanhoCalcado(descricaoTamanhoCalcado);
    }

    /**
     * Busca um tamanho através do ID especificado.
     * @param idTamanhoCalcado ID do tamanho na busca.
     * @return Um Optional que pode conter um objeto TamanhoCalcado.
     */
    public Optional<TamanhoCalcado> findById(Integer idTamanhoCalcado) {
        return tamanhoCalcadoRepository.findById(idTamanhoCalcado);
    }

    /**
     * Salva um tamanho no banco de dados.
     * @param tamanhoCalcado Objeto TamanhoCalcado, com os campos devidamente preenchidos.
     * @return Um Objeto TamanhoCalcado que foi salvo no banco.
     */
    public TamanhoCalcado save(TamanhoCalcado tamanhoCalcado) {
        return tamanhoCalcadoRepository.save(tamanhoCalcado);
    }

    /**
     * Deleta permanentemente um tamanho do banco de dados. <b>Use com precaução</b>
     * @param tamanhoCalcado Objeto TamanhoCalcado a ser deletado.
     */
    public void delete(TamanhoCalcado tamanhoCalcado) {
        tamanhoCalcadoRepository.delete(tamanhoCalcado);
    }

    /**
     *  Busca todos os tamanhos no banco.
     * @param pageable Objeto do Spring que permite a separação em páginas e filtros de ordenação.
     * @return Um Objeto Page do Spring que pode conter uma lista de registros.
     */
    public Page<TamanhoCalcado> findAll(Pageable pageable) {
        return tamanhoCalcadoRepository.findAll(pageable);
    }

    /**
     * Busca todos os tamanhos com a descrição especificada.
     * @param descricaoTamanho Descrição de tamanho para a busca.
     * @return Uma lista de TamanhoCalcado.
     */
    public List<TamanhoCalcado> findAllByDescricaoTamanhoCalcado(String descricaoTamanho) {
        return tamanhoCalcadoRepository.findAllByDescricaoTamanhoCalcadoContainingIgnoreCase(descricaoTamanho);
    }
}
