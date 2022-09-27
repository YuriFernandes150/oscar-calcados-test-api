package com.oscar.oscarcalcadostest.controller;

import com.oscar.oscarcalcadostest.dto.TamanhoDTO;
import com.oscar.oscarcalcadostest.model.TamanhoCalcado;
import com.oscar.oscarcalcadostest.service.TamanhoCalcadoService;
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
 * Este controlador Rest é responsável pelos métodos CRUD da Entity TamanhoCalcado.
 * Seu EndPoint base é htttp://uri:8080/tamanho-calcado
 *
 * @author YuriFernandes150
 * */

@RestController
@CrossOrigin("*")
@RequestMapping("/tamanho-calcado")
public class TamanhoCalcadoController {

    /**
     * O Service vai ser responsável pela comunicação com o Repository do JPA para as operações com o banco de dados.
     * Além da declaração no construtor, também é possível utilizar a anotação @Autowired.
     */
    TamanhoCalcadoService tamanhoCalcadoService;

    public TamanhoCalcadoController(TamanhoCalcadoService tamanhoCalcadoService){
        this.tamanhoCalcadoService = tamanhoCalcadoService;
    }


    /**
     * <h3>O método POST cadastra um novo Tamanho no banco.</h3>
     * É necessário enviar um JSON válido para o EndPoint base do controlador.<br>
     * <b>Exemplo:</b>
     * <pre>
     * {
     *     "descricaoTamanhoCalcado":"46"
     * }
     * </pre>
     * Em caso de sucesso, a API retorna:
     * <pre>
     * {
     *     "idTamanhoCalcado": 34,
     *     "descricaoTamanhoCalcado": "46",
     *     "dataCadastroTamanho": "2022-09-26T18:36:50.492"
     * }
     * </pre>
     * @param tamanhoDTO O objeto DTO de Tamanho, somente o campo descrição precisa ser preenchido.
     * @return Uma resposta com status de <pre>HttpStatus.CREATED</pre> ou <pre>HttpStatus.CONFLICT</pre> caso a propriedade descricaoTamanhoCalcado já exista no banco de dados.
     */
    @PostMapping
    public ResponseEntity<Object> salvarTamanhoCalcado(@RequestBody @Valid TamanhoDTO tamanhoDTO){

        if(tamanhoCalcadoService.existsByDescricaoTamanhoCalcado(tamanhoDTO.getDescricaoTamanhoCalcado())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLITO: Tamanho de calçado já existe.");
        }

        TamanhoCalcado tamanhoCalcado = new TamanhoCalcado();

        BeanUtils.copyProperties(tamanhoDTO, tamanhoCalcado);

        tamanhoCalcado.setDataCadastroTamanho(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CREATED).body(tamanhoCalcadoService.save(tamanhoCalcado));

    }
    /**
     * <h3>Método PUT atualiza um Tamanho existente com novos dados.</h3><br>
     * Deve-se enviar o mesmo JSON de cadastro <br>
     * para o endpoint <b>/tamanho-calcado/{idTamanho}</b> <br>
     * (Sendo <b>idTamanho</b> o campo Integer identificador do Tamanho que deseja atualizar).<br>
     * <b>JSON Exemplo: para o endpoint ./tamanho-calcado/34</b>
     * <pre>
     * {
     *     "descricaoTamanhoCalcado":"48"
     * }
     * </pre>
     * Em caso de sucesso, a API retorna:
     * <pre>
     * {
     *     "idTamanhoCalcado": 34,
     *     "descricaoTamanhoCalcado": "48",
     *     "dataCadastroTamanho": "2022-09-26T18:36:50.492"
     * }
     * </pre>
     * @param idTamanhoCalcado Identificador Integer do Tamanho a ser atualizado (id_tamanho_calcado no banco de dados).
     * @param tamanhoDTO O objeto DTO de Tamanho, somente o campo descrição precisa ser preenchido.
     * @return Uma resposta com status de <pre>HttpStatus.OK</pre> ou <pre>HttpStatus.NOT_FOUND</pre> caso nenhum tamanho seja encontrado com o ID fornecido.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarTamanhoCalcado(@PathVariable(value = "id") Integer idTamanhoCalcado,
                                                          @RequestBody @Valid TamanhoDTO tamanhoDTO){
        Optional<TamanhoCalcado> tamanhoCalcadoOptional = tamanhoCalcadoService.findById(idTamanhoCalcado);
        if(!tamanhoCalcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Não foi encontrado nenhum tamanho com esse ID");
        }

        TamanhoCalcado novoTamanhoCalcado = tamanhoCalcadoOptional.get();

        novoTamanhoCalcado.setDescricaoTamanhoCalcado(tamanhoDTO.getDescricaoTamanhoCalcado());

        return ResponseEntity.status(HttpStatus.OK).body(tamanhoCalcadoService.save(novoTamanhoCalcado));

    }

    /**
     * <h3>Método DELETE apaga um registro permanentemente do banco de dados, use com precaução.</h3><br>
     * Não é necessário enviar um JSON para esse método. <br>
     * Este método usa o endpoint <b>/tamanho-calcado/{idTamanho}</b> <br>
     * (Sendo <b>idTamanho</b> o campo Integer identificador do Tmamaho que deseja deletar).<br>
     *
     * @param idTamanhoCalcado Identificador Integer do Tamanho a ser atualizado (id_tamanho_calcado no banco de dados).
     * @return Uma resposta com status de <pre>HttpStatus.OK</pre> ou <pre>HttpStatus.NOT_FOUND</pre> caso nenhum tamanho seja encontrada com o ID fornecido.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarTamanho(@PathVariable(value = "id") Integer idTamanhoCalcado){

        Optional<TamanhoCalcado> tamanhoCalcadoOptional = tamanhoCalcadoService.findById(idTamanhoCalcado);
        if(!tamanhoCalcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Não foi encontrado nenhum tamanho com esse ID");
        }

        tamanhoCalcadoService.delete(tamanhoCalcadoOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Deletado com Sucesso!");

    }

/**
 * h3>Esse método GET busca todos os tamanhos no banco e os separa em páginas.</h3><br>
 * <b>Exemplo de JSON de retorno:</b>
 * <pre>
 * {
 *     "content": [
 *         {
 *             "idTamanhoCalcado": 22,
 *             "descricaoTamanhoCalcado": "11",
 *             "dataCadastroTamanho": "2022-09-26T09:16:56.192"
 *         },
 *         {
 *             "idTamanhoCalcado": 1,
 *             "descricaoTamanhoCalcado": "35",
 *             "dataCadastroTamanho": "2022-09-25T19:17:59.393"
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
 * @param pageable Objeto de paginação do Spring. Os seus parâmetros podem ser usados no Endpoint. <b>EX:</b> <code>./tamanho-calcado/?sort=descricaoTamanhoCalcado,ASC&page=1&value=10</code>
 * @return Um HttpStatus.OK, com um JSON com os resultados (mostrado acima).
 **/
    @GetMapping()
    public ResponseEntity<Page<TamanhoCalcado>> buscarTodosOsTamanhos(@PageableDefault(sort = "descricaoTamanhoCalcado", direction = Sort.Direction.ASC)Pageable pageable){

        return ResponseEntity.status(HttpStatus.OK).body(tamanhoCalcadoService.findAll(pageable));

    }

    /**
     * <h3>Método GET para buscar um único Tamanho através do ID</h3><br>
     * Nesse método é necessário utilizar o ID do tamanho através do Endpoint.<br>
     * <code>./tamanho-calcado/id/{idTamanho}</code><br>
     * <b>Exemplo:</b> <code>./tamanho-calcado/id/3</code><br>
     * <b>JSON de Retorno:</b>
     * <pre>
     * {
     *     "idTamanhoCalcado": 3,
     *     "descricaoTamanhoCalcado": "42",
     *     "dataCadastroTamanho": "2022-09-25T19:18:12.247"
     * }
     * </pre>
     * @param idTamanhoCalcado Identificador Integer do Tamanho (id_tamanho_calcado no banco de dados).
     * @return Um HttpStatus.OK, com um JSON com os resultados (mostrado acima).
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> buscarTamanhoPorId(@PathVariable(value = "id") Integer idTamanhoCalcado){

        Optional<TamanhoCalcado> tamanhoCalcadoOptional = tamanhoCalcadoService.findById(idTamanhoCalcado);
        if(!tamanhoCalcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Não foi encontrado nenhum tamanho com esse ID");
        }

        return ResponseEntity.status(HttpStatus.OK).body(tamanhoCalcadoOptional);

    }

    /**
     * <h3>Método GET para buscar todos os Tamanhos através da descrição.</h3><br>
     * Nesse método é necessário utilizar a descrição do tamanho através do Endpoint.<br>
     * <code>./tamanho-calcado/descricao/{descricaoTamanho}</code><br>
     * <b>Exemplo:</b> <code>./tamanho-calcado/descricao/4</code><br>
     * <b>JSON de Retorno:</b>
     * <pre>
     * [
     *     {
     *         "idTamanhoCalcado": 3,
     *         "descricaoTamanhoCalcado": "42",
     *         "dataCadastroTamanho": "2022-09-25T19:18:12.247"
     *     },
     *     {
     *         "idTamanhoCalcado": 4,
     *         "descricaoTamanhoCalcado": "44",
     *         "dataCadastroTamanho": "2022-09-25T19:18:18.971"
     *     },
     *     {
     *         "idTamanhoCalcado": 34,
     *         "descricaoTamanhoCalcado": "48",
     *         "dataCadastroTamanho": "2022-09-26T18:36:50.492"
     *     }
     * ]
     * </pre>
     * @param descricaoTamanho ‘String’ de descrição do Tamanho (descricao_tamanho_calcado no banco de dados).
     * @return Um HttpStatus.OK, com um JSON com os resultados (mostrado acima).
     */
    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<Object> buscarTamanhosPorDescricao(@PathVariable(value = "descricao") String descricaoTamanho){

        List<TamanhoCalcado> tamanhoCalcadoList = tamanhoCalcadoService.findAllByDescricaoTamanhoCalcado(descricaoTamanho);
        if(tamanhoCalcadoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum tamanho encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tamanhoCalcadoList);

    }

}
