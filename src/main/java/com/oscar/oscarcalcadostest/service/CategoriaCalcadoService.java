package com.oscar.oscarcalcadostest.service;

import com.oscar.oscarcalcadostest.model.CategoriaCalcado;
import com.oscar.oscarcalcadostest.repository.CategoriaCalcadosRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * O Service é a rota principal de comunicação entre a aplicação e os Repositories,
 * ele contém as chamadas dos métodos do repo e faz os Casts ncessários para realizar as operações nos Controllers.<br>
 * Esse Service representa os métodos do banco de dados da tbl_categoria_calcado.
 */
@Service
public class CategoriaCalcadoService {

    /**
     * O Repository é necessário para construir os métodos de comunicação com o banco.<br>
     * Também é possível utilizar a Anotation @Autowired.
     */
    final CategoriaCalcadosRepository categoriaCalcadosRepository;

    public CategoriaCalcadoService(CategoriaCalcadosRepository categoriaCalcadosRepository){
        this.categoriaCalcadosRepository = categoriaCalcadosRepository;
    }

    /**
     * Verifica se uma categoria existe através da descrição
     * @param descricaoCategoria Descrição da categoria a ser buscada
     * @return verdadeiro se a categoria existir, ou falso se não existir.
     */
    public boolean existsByDescricaoCategoria(String descricaoCategoria) {
        return categoriaCalcadosRepository.existsByDescricaoCategoria(descricaoCategoria);
    }

    /**
     * Salva uma nova categoria no banco de dados.
     * @param categoriaCalcado Objeto de categoria com os campos devidamente preenchidos.
     * @return O objeto de categoria
     */
    public CategoriaCalcado save(CategoriaCalcado categoriaCalcado) {
        return categoriaCalcadosRepository.save(categoriaCalcado);
    }

    /**
     * Busca uma categoria pelo ID fornecido
     * @param idCategoria ID fornecido para a busca.
     * @return Um Optional, que pode conter um objeto CategoriaCalcado
     */
    public Optional<CategoriaCalcado> findById(Integer idCategoria) {
        return categoriaCalcadosRepository.findById(idCategoria);
    }

    /**
     * Deleta a categoria permanentemente. <b>Use com precaução</b>
     * @param idCategoria Objeto Categoria devidamente preenchido.
     */
    public void delete(CategoriaCalcado idCategoria) {
        categoriaCalcadosRepository.delete(idCategoria);
    }

    /**
     * Busca todas as categorias no banco.
     * @param pageable Objeto do Spring que permite a separação em páginas e filtros de ordenação.
     * @return Um Objeto Page do Spring que pode conter uma lista de registros.
     */
    public Page<CategoriaCalcado> findAll(Pageable pageable) {
        return categoriaCalcadosRepository.findAll(pageable);
    }

    /**
     * Busca todas as categorias que possum uma descrição parecida com a busca.
     * @param descricaoCategoria Descrição fornecida para a busca.
     * @return Uma lista de CategoriaCalcado.
     */
    public List<CategoriaCalcado> findAllByDescricaoLikeIgnoreCase(String descricaoCategoria) {
        return categoriaCalcadosRepository.findAllByDescricaoCategoriaContainingIgnoreCase(descricaoCategoria);
    }
}
