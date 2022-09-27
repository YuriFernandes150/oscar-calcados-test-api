package com.oscar.oscarcalcadostest.service;

import com.oscar.oscarcalcadostest.model.CorCalcado;
import com.oscar.oscarcalcadostest.repository.CorCalcadoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * O Service é a rota principal de comunicação entre a aplicação e os Repositories,
 * ele contém as chamadas dos métodos do repo e faz os Casts ncessários para realizar as operações nos Controllers.<br>
 * Esse Service representa os métodos do banco de dados da tbl_cor_calcado.
 */
@Service
public class CorCalcadoService {

    /**
     * O Repository é necessário para construir os métodos de comunicação com o banco.<br>
     * Também é possível utilizar a Anotation @Autowired.
     */
    final CorCalcadoRepository corCalcadoRepository;

    public CorCalcadoService(CorCalcadoRepository corCalcadoRepository){
        this.corCalcadoRepository = corCalcadoRepository;
    }

    /**
     * Verifica se uma cor existe através da descrição.
     * @param descricaoCorCalcado Descrição fornecida na busca.
     * @return verdadeiro se a cor existe ou falso se não existe.
     */
    public boolean existsByDescricaoCorCalcado(String descricaoCorCalcado) {
        return corCalcadoRepository.existsByDescricaoCorCalcado(descricaoCorCalcado);
    }

    /**
     * Salva uma nova cor no banco de dados.
     * @param corCalcado Objeto CorCalcado com todos os campos preenchidos.
     * @return O objeto CorCalcado que foi cadastrado.
     */
    public CorCalcado save(CorCalcado corCalcado) {
        return corCalcadoRepository.save(corCalcado);
    }

    /**
     * Busca uma cor através do ID fornecido.
     * @param idCor ID da cor fornecido na busca.
     * @return Um Optional que pode conter u objeto CorCalcado
     */
    public Optional<CorCalcado> findById(Integer idCor) {
        return corCalcadoRepository.findById(idCor);
    }

    /**
     * Deleta permanentemente uma cor do banco de dados. <b>Use com precaução.</b>
     * @param corCalcado Objeto Cor devidamente preenchido.
     */
    public void delete(CorCalcado corCalcado) {
        corCalcadoRepository.delete(corCalcado);
    }

    /**
     * Busca todas as cores no banco.
     * @param pageable Objeto do Spring que permite a separação em páginas e filtros de ordenação.
     * @return Um Objeto Page do Spring que pode conter uma lista de registros.
     */
    public Page<CorCalcado> findAll(Pageable pageable) {
        return corCalcadoRepository.findAll(pageable);
    }

    /**
     * Busca todas as cores por descrição.
     * @param descricaoCorCalcado Descrição fornecida para buscar as cores.
     * @return Uma lista de objheto CorCalcado.
     */
    public List<CorCalcado> findByDescricaoContainsIgnoreCase(String descricaoCorCalcado) {
        return corCalcadoRepository.findAllByDescricaoCorCalcadoContainingIgnoreCase(descricaoCorCalcado);
    }
}
