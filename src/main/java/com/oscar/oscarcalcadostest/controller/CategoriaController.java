package com.oscar.oscarcalcadostest.controller;

import com.oscar.oscarcalcadostest.dto.CategoriaDTO;
import com.oscar.oscarcalcadostest.model.CategoriaCalcado;
import com.oscar.oscarcalcadostest.service.CategoriaCalcadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Este controlador Rest é responsável pelos métodos CRUD da Entity CategoriaCalcado.
 * Seu EndPoint base é htttp://uri:8080/categoria-calcado
 *
 * @author YuriFernandes150
 * */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/categoria-calcado")
public class CategoriaController {

    /**
     * O Service vai ser responsável pela comunicação com o Repository do JPA para as operações com o banco de dados.
     * Além da declaração no construtor, também é possível utilizar a anotação @Autowired.
     */
    final CategoriaCalcadoService categoriaCalcadoService;

    public CategoriaController(CategoriaCalcadoService categoriaCalcadoService){
        this.categoriaCalcadoService = categoriaCalcadoService;
    }


    /**
     * <h3>O método POST cadastra uma nova categoria no banco.</h3>
     * É necessário enviar um JSON válido para o EndPoint base do controlador.<br>
     * <b>Exemplo:</b>
     * <pre>
     *     {
     *          "descricaoCategoria":"ESPORT"
     *      }
     * </pre>
     * Em caso de sucesso, a API retorna o novo registro:
     * <pre>
     *     {
     *          "idCategoriaCalcado": 32,
     *          "descricaoCategoria": "ESPORT",
     *          "dataCadastroCategoria": "2022-09-26T16:40:47.319"
     *      }
     * </pre>
     * @param categoriaDTO O objeto DTO de Categoria, somente o campo descrição precisa ser preenchido.
     * @return Uma resposta com status de <pre>HttpStatus.CREATED</pre> ou <pre>HttpStatus.CONFLICT</pre> caso a propriedade descricaoCategoria já exista no banco de dados.
     */
    @PostMapping
    public ResponseEntity<Object> salvarCategoria(@RequestBody @Valid CategoriaDTO categoriaDTO){
        if(categoriaCalcadoService.existsByDescricaoCategoria(categoriaDTO.getDescricaoCategoria())){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLITO: Categoria com esse nome já existe");
        }

        CategoriaCalcado categoriaCalcado = new CategoriaCalcado();
        BeanUtils.copyProperties(categoriaDTO, categoriaCalcado);
        categoriaCalcado.setDataCadastroCategoria(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCalcadoService.save(categoriaCalcado));
    }

    /**
     * <h3>Método PUT atualiza uma Categoria existente com novos dados.</h3><br>
     * Deve-se enviar o mesmo JSON de cadastro <br>
     * para o endpoint <b>/categoria-calcado/{idCategoria}</b> <br>
     * (Sendo <b>idCategoria</b> o campo Integer identificador da Categoria que deseja atualizar).<br>
     * <b>JSON Exemplo: para o endpoint ./catgoria-calcado/32</b>
     * <pre>
     *  {
     *      "descricaoCategoria":"ESPORTIVO"
     *  }
     * </pre>
     *
     * Em caso de sucesso, o retorno será:
     * <pre>
     * {
     *     "idCategoriaCalcado": 32,
     *     "descricaoCategoria": "ESPORTIVO",
     *     "dataCadastroCategoria": "2022-09-26T16:40:47.319"
     * }
     * </pre>
     * @param idCategoria Identificador Integer da Categoria a ser atualizado (id_categoria no banco de dados).
     * @param categoriaDTO O objeto DTO de Categoria, somente o campo descrição precisa ser preenchido.
     * @return Uma resposta com status de <pre>HttpStatus.OK</pre> ou <pre>HttpStatus.NOT_FOUND</pre> caso nenhuma categoria seja encontrada com o ID fornecido.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarCategoria(@PathVariable(value = "id") Integer idCategoria, @RequestBody @Valid CategoriaDTO categoriaDTO){

        Optional<CategoriaCalcado> categoriaCalcadoOptional = categoriaCalcadoService.findById(idCategoria);

        if(!categoriaCalcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma categoria encontrada com esse ID");
        }

        CategoriaCalcado novaCategoriaCalcado = categoriaCalcadoOptional.get();

        novaCategoriaCalcado.setDescricaoCategoria(categoriaDTO.getDescricaoCategoria());

        return ResponseEntity.status(HttpStatus.OK).body(categoriaCalcadoService.save(novaCategoriaCalcado));

    }

    /**
     * <h3>Método DELETE apaga um registro permanentemente do banco de dados, use com precaução.</h3><br>
     * Não é necessário enviar um JSON para esse método.<br>
     * Este método usa o endpoint <b>/categoria-calcado/{idCategoria}</b> <br>
     * (Sendo <b>idCategoria</b> o campo Integer identificador da Categoria que deseja deletar).<br>
     * @param idCategoria Identificador Integer da Categoria a ser deletada (id_categoria no banco de dados).
     * @return Uma resposta com status de <pre>HttpStatus.OK</pre> ou <pre>HttpStatus.NOT_FOUND</pre> caso nenhuma categoria seja encontrada com o ID fornecido.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCategoria(@PathVariable(value = "id") Integer idCategoria){

        Optional<CategoriaCalcado> categoriaCalcadoOptional = categoriaCalcadoService.findById(idCategoria);
        if(!categoriaCalcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado");
        }

        categoriaCalcadoService.delete(categoriaCalcadoOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com Sucesso!");

    }

    /**
     * <h3>Esse método GET busca todos as categorias no banco e os separa em páginas.</h3><br>
     * <b>Exemplo de JSON de retorno:</b>
     * <pre>
     *     {
     *     "content": [
     *         {
     *             "idCategoriaCalcado": 1,
     *             "descricaoCategoria": "SALTO",
     *             "dataCadastroCategoria": "2022-09-25T15:55:49.273"
     *         },
     *         {
     *             "idCategoriaCalcado": 2,
     *             "descricaoCategoria": "SANDALIA",
     *             "dataCadastroCategoria": "2022-09-25T15:55:58.707"
     *         },[...]
     *     ],
     *     "pageable": {
     *         "sort": {
     *             "empty": false,
     *             "unsorted": false,
     *             "sorted": true
     *         },
     *         "offset": 0,
     *         "pageNumber": 0,
     *         "pageSize": 10,
     *         "paged": true,
     *         "unpaged": false
     *     },
     *     "last": true,
     *     "totalPages": 1,
     *     "totalElements": 7,
     *     "size": 10,
     *     "number": 0,
     *     "sort": {
     *         "empty": false,
     *         "unsorted": false,
     *         "sorted": true
     *     },
     *     "numberOfElements": 7,
     *     "first": true,
     *     "empty": false
     * }
     * </pre>
     * @param pageable Objeto de paginação do Spring. Os seus parâmetros podem ser usados no Endpoint. <b>EX:</b> <code>./categoria-calcado/?sort=descricaoCategoria,ASC&page=1&value=10</code>
     * @return Um HttpStatus.OK, com um JSON com os resultados (mostrado acima).
     */
    @GetMapping
    public ResponseEntity<Page<CategoriaCalcado>> buscarTodasAsCategorias(@PageableDefault(sort="idCategoriaCalcado", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaCalcadoService.findAll(pageable));
    }

    /**
     * <h3>Método GET para buscar uma única Categoria através do ID</h3><br>
     * Nesse método é necessário utilizar o ID da categoria através do Endpoint.<br>
     * <code>./categoria-calcado/id/{idCategoria}</code><br>
     * <b>Exemplo:</b> <code>./categoria-calcado/id/3</code><br>
     * <b>JSON de Retorno:</b>
     * <pre>
     * {
     *     "idCategoriaCalcado": 3,
     *     "descricaoCategoria": "CHINELO",
     *     "dataCadastroCategoria": "2022-09-25T15:56:03.613"
     * }
     * </pre>
     * @param idCategoria Identificador Integer da Categoria (id_categoria no banco de dados).
     * @return Um HttpStatus.OK, com um JSON com os resultados (mostrado acima).
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> buscarCategoriasPorId(@PathVariable(value = "id") Integer idCategoria){

        Optional<CategoriaCalcado> categoriaCalcadoOptional = categoriaCalcadoService.findById(idCategoria);
        if(!categoriaCalcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Categoria não encontrada.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(categoriaCalcadoOptional.get());

    }


    /**
     * <h3>Método GET para buscar todas as Categorias que tem a descrição parecida com o parâmetro</h3><br>
     * Nesse método é necessário utilizar a descrição da categoria através do Endpoint.<br>
     * <code>./categoria-calcado/descricao/{descricaoCategoria}</code><br>
     * <b>Exemplo:</b> <code>./categoria-calcado/descricao/SA</code><br>
     * <b>JSON de Retorno:</b>
     * <pre>
     * [
     *     {
     *         "idCategoriaCalcado": 1,
     *         "descricaoCategoria": "SALTO",
     *         "dataCadastroCategoria": "2022-09-25T15:55:49.273"
     *     },
     *     {
     *         "idCategoriaCalcado": 2,
     *         "descricaoCategoria": "SANDALIA",
     *         "dataCadastroCategoria": "2022-09-25T15:55:58.707"
     *     },
     *     {
     *         "idCategoriaCalcado": 5,
     *         "descricaoCategoria": "SAPATO SOCIAL",
     *         "dataCadastroCategoria": "2022-09-25T15:56:24.26"
     *     }
     * ]
     * </pre>
     * @param descricaoCategoria String de descrição da Categoria (descricao_categoria no banco de dados).
     * @return Um HttpStatus.OK, com um JSON com os resultados (mostrado acima).
     */
    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<Object> buscarCategoriasPorDescricao(@PathVariable(value = "descricao") String descricaoCategoria){

        List<CategoriaCalcado> categoriaCalcadoList = categoriaCalcadoService.findAllByDescricaoLikeIgnoreCase(descricaoCategoria);

        if(categoriaCalcadoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: nenhuma categoria encontrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(categoriaCalcadoList);

    }

}
