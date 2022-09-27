package com.oscar.oscarcalcadostest.controller;


import com.oscar.oscarcalcadostest.dto.CorCalcadoDTO;
import com.oscar.oscarcalcadostest.model.CorCalcado;
import com.oscar.oscarcalcadostest.service.CorCalcadoService;
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
 * Este controlador Rest é responsável pelos métodos CRUD da Entity CorCalcado.
 * Seu EndPoint base é htttp://uri:8080/cor-calcado
 *
 * @author YuriFernandes150
 * */

@RestController
@CrossOrigin("*")
@RequestMapping("/cor-calcado")
public class CorCalcadoController {


    /**
     * O Service vai ser responsável pela comunicação com o Repository do JPA para as operações com o banco de dados.
     * Além da declaração no construtor, também é possível utilizar a anotação @Autowired.
     */
    final CorCalcadoService corCalcadoService;

    public CorCalcadoController(CorCalcadoService corCalcadoService){
        this.corCalcadoService = corCalcadoService;
    }


    /**
     * <h3>O método POST cadastra uma nova Cor no banco.</h3>
     * É necessário enviar um JSON válido para o EndPoint base do controlador.<br>
     * <b>Exemplo:</b>
     * <pre>
     * {
     *     "descricaoCorCalcado":"BEGE"
     * }
     * </pre>
     * Em caso de sucesso, a API retorna o seguinte JSON:
     * <pre>
     * {
     *     "idCorCalcado": 34,
     *     "descricaoCorCalcado": "BEGE",
     *     "dataCadastroCor": "2022-09-26T17:06:20.105"
     * }
     * </pre>
     * @param corCalcadoDTO O objeto DTO de Cor, somente o campo descrição precisa ser preenchido.
     * @return Uma resposta com status de <pre>HttpStatus.CREATED</pre> ou <pre>HttpStatus.CONFLICT</pre> caso a propriedade descricaoCor já exista no banco de dados.
     */
    @PostMapping
    public ResponseEntity<Object> salvarCorCalcado(@RequestBody @Valid CorCalcadoDTO corCalcadoDTO){

        if(corCalcadoService.existsByDescricaoCorCalcado(corCalcadoDTO.getDescricaoCorCalcado())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLITO: Essa cor já foi cadastrada.");
        }

        CorCalcado corCalcado = new CorCalcado();

        BeanUtils.copyProperties(corCalcadoDTO, corCalcado);

        corCalcado.setDataCadastroCor(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CREATED).body(corCalcadoService.save(corCalcado));

    }

    /**
     * <h3>Método PUT atualiza uma Cor existente com novos dados.</h3><br>
     * Deve-se enviar o mesmo JSON de cadastro <br>
     * para o endpoint <b>/cor-calcado/{idCor}</b> <br>
     * (Sendo <b>idCor</b> o campo Integer identificador da Cor que deseja atualizar).<br>
     * <b>JSON Exemplo: para o endpoint ./cor-calcado/34</b>
     * <pre>
     *  {
     *      "descricaoCategoria":"BEIGE"
     *  }
     * </pre>
     *
     * Em caso de sucesso, a API retorna o seguinte JSON:
     * <pre>
     * {
     *      "idCorCalcado": 34,
     *      "descricaoCorCalcado": "BEIGE",
     *      "dataCadastroCor": "2022-09-26T17:06:20.105"
     *  }
     * </pre>
     * @param idCor Identificador Integer da Cor a ser atualizado (id_cor no banco de dados).
     * @param corCalcadoDTO O objeto DTO de Cor, somente o campo descrição precisa ser preenchido.
     * @return Uma resposta com status de <pre>HttpStatus.OK</pre> ou <pre>HttpStatus.NOT_FOUND</pre> caso nenhuma cor seja encontrada com o ID fornecido.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarCor(@PathVariable(value = "id") Integer idCor,@RequestBody @Valid CorCalcadoDTO corCalcadoDTO){

        Optional<CorCalcado> corCalcadoOptional = corCalcadoService.findById(idCor);
        if(!corCalcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Cor não encontrada com esse id.");
        }

        CorCalcado novaCorCalcado = corCalcadoOptional.get();

        novaCorCalcado.setDescricaoCorCalcado(corCalcadoDTO.getDescricaoCorCalcado());

        return ResponseEntity.status(HttpStatus.OK).body(corCalcadoService.save(novaCorCalcado));

    }

    /**
     * <h3>Método DELETE apaga um registro permanentemente do banco de dados, use com precaução.</h3><br>
     * Não é necessário enviar um JSON para esse método.<br>
     * Este método usa o endpoint <b>/cor-calcado/{idCor}</b> <br>
     * (Sendo <b>idCor</b> o campo Integer identificador da Cor que deseja deletar).<br>
     * @param idCor Identificador Integer da Cor a ser deletada (id_cor no banco de dados).
     * @return Uma resposta com status de <pre>HttpStatus.OK</pre> ou <pre>HttpStatus.NOT_FOUND</pre> caso nenhuma cor seja encontrada com o ID fornecido.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCor(@PathVariable(value = "id") Integer idCor){

        Optional<CorCalcado> corCalcadoOptional = corCalcadoService.findById(idCor);
        if(!corCalcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Cor não encontrada com esse id.");
        }

        corCalcadoService.delete(corCalcadoOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso!");

    }

    /**
     * <h3>Esse método GET busca todos as cores no banco e os separa em páginas.</h3><br>
     * <b>Exemplo de JSON de retorno:</b>
     * <pre>
     *
     *     {
     *     "content": [
     *         {
     *             "idCorCalcado": 1,
     *             "descricaoCorCalcado": "BRANCO",
     *             "dataCadastroCor": "2022-09-25T15:57:15.476"
     *         },
     *         {
     *             "idCorCalcado": 2,
     *             "descricaoCorCalcado": "PRETO",
     *             "dataCadastroCor": "2022-09-25T15:57:20.811"
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
     * @param pageable Objeto de paginação do Spring. Os seus parâmetros podem ser usados no Endpoint. <b>EX:</b> <code>./cor-calcado/?sort=descricaoCorCalcado,ASC&page=1&value=10</code>
     * @return Um HttpStatus.OK, com um JSON com os resultados (mostrado acima).
     */
    @GetMapping
    public ResponseEntity<Page<CorCalcado>> buscarTodasAsCores(@PageableDefault(sort = "idCorCalcado", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(corCalcadoService.findAll(pageable));
    }

    /**
     * <h3>Método GET para buscar uma única Cor através do ID</h3><br>
     * Nesse método é necessário utilizar o ID da cor através do Endpoint.<br>
     * <code>./cor-calcado/id/{idCor}</code><br>
     * <b>Exemplo:</b> <code>./cor-calcado/id/3</code><br>
     * <b>JSON de Retorno:</b>
     * <pre>
     * {
     *     "idCorCalcado": 3,
     *     "descricaoCorCalcado": "AZUL",
     *     "dataCadastroCor": "2022-09-25T15:57:30.02"
     * }
     * </pre>
     * @param idCor Identificador Integer da Cor a ser deletada (id_cor no banco de dados).
     * @return Um HttpStatus.OK, com um JSON com os resultados (mostrado acima).
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> buscarCorPorId(@PathVariable(value = "id") Integer idCor){

        Optional<CorCalcado> corCalcadoOptional = corCalcadoService.findById(idCor);
        if(!corCalcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Cor não encontrada com esse id.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(corCalcadoOptional.get());

    }

    /**
     * <h3>Método GET para buscar todas as cores através da descrição</h3><br>
     * Nesse método é necessário utilizar a descrição da cor através do Endpoint.<br>
     * <code>./cor-calcado/descricao/{descricaoCor}</code><br>
     * <b>Exemplo:</b> <code>./cor-calcado/descricao/A</code><br>
     * <b>JSON de Retorno:</b>
     * <pre>
     * [
     *     {
     *         "idCorCalcado": 1,
     *         "descricaoCorCalcado": "BRANCO",
     *         "dataCadastroCor": "2022-09-25T15:57:15.476"
     *     },
     *     {
     *         "idCorCalcado": 3,
     *         "descricaoCorCalcado": "AZUL",
     *         "dataCadastroCor": "2022-09-25T15:57:30.02"
     *     },
     *     {
     *         "idCorCalcado": 5,
     *         "descricaoCorCalcado": "AMARELO",
     *         "dataCadastroCor": "2022-09-25T15:57:37.274"
     *     }
     * ]
     * </pre>
     * @param descricaoCorCalcado ‘String’ de descrição da Cor (id_cor no banco de dados).
     * @return Um HttpStatus.OK, com um JSON com os resultados (mostrado acima).
     */
    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<Object> buscarCoresPorDescricao(@PathVariable(value = "descricao") String descricaoCorCalcado){

        List<CorCalcado> corCalcadoList = corCalcadoService.findByDescricaoContainsIgnoreCase(descricaoCorCalcado);
        if(corCalcadoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma cor encontrada.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(corCalcadoList);

    }

}
