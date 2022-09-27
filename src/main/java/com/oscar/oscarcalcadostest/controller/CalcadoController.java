package com.oscar.oscarcalcadostest.controller;

import com.oscar.oscarcalcadostest.dto.CalcadoDTO;
import com.oscar.oscarcalcadostest.dto.CalcadosBuscaCustomDTO;
import com.oscar.oscarcalcadostest.model.Calcado;
import com.oscar.oscarcalcadostest.service.CalcadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.oscar.oscarcalcadostest.specification.CalcadoSpecification.*;

/**
 * Este controlador Rest é responsável pelos métodos CRUD da Entity Calcado.
 * Seu EndPoint base é htttp://uri:8080/calcado
 *
 * @author YuriFernandes150
 * */

@RestController
@CrossOrigin("*")
@RequestMapping("/calcado")
public class CalcadoController {

    /**
     * O Service vai ser responsável pela comunicação com o Repository do JPA para as operações com o banco de dados.
     * Além da declaração no construtor, também é possível utilizar a anotação @Autowired.
     */

    final CalcadoService calcadoService;

    public CalcadoController(CalcadoService calcadoService){
        this.calcadoService = calcadoService;
    }


    /**
     * <h3>O método POST cadastra um novo calçado no banco.</h3>
     * É necessário enviar um JSON válido para o EndPoint base do controlador.<br>
     * <b>Exemplo:</b>
     *  <pre>
     *      {
     *     "codCalcado":"05",
     *     "descricaoCalcado":"CHINELO",
     *     "qtdEstoque": 20.0,
     *     "precoCalcado": 18.50,
     *     "corCalcado": {
     *         "idCorCalcado":2,
     *         "descricaoCorCalcado":"PRETO",
     *         "dataCadastroCor":"2022-09-25T15:57:15.476"
     *     },
     *     "marcaCalcado":{
     *         "idMarcaCalcado": 4,
     *         "descricaoMarcaCalcado":"HAVAIANA",
     *         "dataCadastroMarca":"2022-09-25T22:24:26.142"
     *     },
     *     "categoriaCalcado":{
     *         "idCategoriaCalcado": 5,
     *         "descricaoCategoria":"CHINELO",
     *         "dataCadastroCategoria":"2022-09-25T15:56:03.613"
     *     },
     *     "tamanhoCalcado":{
     *         "idTamanhoCalcado": 3,
     *         "descricaoTamanhoCalcado":"42",
     *         "dataCadastroTamanho":"2022-09-25T19:18:12.247"
     *     }
     *
     * }
     *  </pre>
     *
     *
     *
     *
      * @param calcadoDTO O objeto DTO de Calçado, com os campos devidamente preenchidos.
     * @return Uma resposta com status de <pre>HttpStatus.CREATED</pre> ou <pre>HttpStatus.CONFLICT</pre> caso a propriedade codCalcado já exista no banco de dados.
     */
    @PostMapping
    public ResponseEntity<Object> salvarCalcado(@RequestBody @Valid CalcadoDTO calcadoDTO){

        if(calcadoService.existsByCodCalcado(calcadoDTO.getCodCalcado())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLITO: Já existe um calçado com esse código.");
        }

        Calcado calcado = new Calcado();
        BeanUtils.copyProperties(calcadoDTO, calcado);

        calcado.setDataCadastro(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CREATED).body(calcadoService.save(calcado));

    }

    /**
     * <h3>Método PUT atualiza um Calçado existente com novos dados.</h3><br>
     * Deve-se enviar o mesmo JSON de cadastro <br>para o endpoint <b>/calcado/{idCalcado}</b> <br>(Sendo <b>idCalcado</b> o campo Integer identificador do Calçado que deseja atualizar).<br>
     * <b>JSON Exemplo:</b>
     * <pre>{
     *     "codCalcado":"05",
     *     "descricaoCalcado":"CHINELO",
     *     "qtdEstoque": 20.0,
     *     "precoCalcado": 18.50,
     *     "corCalcado": {
     *         "idCorCalcado":2,
     *         "descricaoCorCalcado":"PRETO",
     *         "dataCadastroCor":"2022-09-25T15:57:15.476"
     *     },
     *     "marcaCalcado":{
     *         "idMarcaCalcado": 4,
     *         "descricaoMarcaCalcado":"HAVAIANA",
     *         "dataCadastroMarca":"2022-09-25T22:24:26.142"
     *     },
     *     "categoriaCalcado":{
     *         "idCategoriaCalcado": 5,
     *         "descricaoCategoria":"CHINELO",
     *         "dataCadastroCategoria":"2022-09-25T15:56:03.613"
     *     },
     *     "tamanhoCalcado":{
     *         "idTamanhoCalcado": 3,
     *         "descricaoTamanhoCalcado":"42",
     *         "dataCadastroTamanho":"2022-09-25T19:18:12.247"
     *     }
     *
     * }</pre>
     *
      * @param idCalcado Identificador Integer do Calçado a ser atualizado (id_calcado no banco de dados).
     * @param calcadoDTO O objeto DTO de Calçado, com os campos devidamente preenchidos igual no cadastro.
     * @return Uma resposta com status de <pre>HttpStatus.OK</pre> ou <pre>HttpStatus.NOT_FOUND</pre> caso nenhum calçado seja encontrado com o ID fornecido.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarCalcado(@PathVariable(value = "id") Integer idCalcado,@RequestBody @Valid CalcadoDTO calcadoDTO){

        Optional<Calcado> calcadoOptional = calcadoService.findById(idCalcado);
        if(!calcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Nenhum calçado encontrado com esse ID.");
        }

        Calcado novoCalcado = calcadoOptional.get();

        BeanUtils.copyProperties(calcadoDTO,novoCalcado);

        return ResponseEntity.status(HttpStatus.OK).body(calcadoService.save(novoCalcado));

    }

    /**
     * <h3>Método DELETE apaga um registro permanentemente do banco de dados, use com precaução.</h3><br>
     * Não é necessário enviar um JSON para esse método.<br>
     * Este método usa o endpoint <b>/calcado/{idCalcado}</b> <br>(Sendo <b>idCalcado</b> o campo Integer identificador do Calçado que deseja deletar).<br>
     *
     *
     * @param idCalcado Identificador Integer do Calçado a ser deletado (id_calcado no banco de dados).
     * @return Uma resposta com status de <pre>HttpStatus.OK</pre> ou <pre>HttpStatus.NOT_FOUND</pre> caso nenhum calçado seja encontrado com o ID fornecido.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCalcado(@PathVariable(value = "id") Integer idCalcado){

        Optional<Calcado> calcadoOptional = calcadoService.findById(idCalcado);
        if(!calcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Nenhum calçado encontrado com esse ID.");
        }

        calcadoService.delete(calcadoOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Deletado com Sucesso!");

    }

    /**
     * <h3>Esse método GET busca todos os calçados no banco e os separa em páginas.</h3><br>
     * <b>Exemplo de JSON de retorno:</b>
     *       <pre>
     *           {
     *           "content": [
     *               {
     *                   "idCalcado": 3,
     *                   "codCalcado": "01",
     *                   "descricaoCalcado": "CHINELO",
     *                   "qtdEstoque": 20.0,
     *                   "dataCadastro": "2022-09-25T20:23:34.184",
     *                   "precoCalcado": 18.5,
     *                   "corCalcado": {
     *                       "idCorCalcado": 2,
     *                       "descricaoCorCalcado": "PRETO",
     *                       "dataCadastroCor": "2022-09-25T15:57:20.811"
     *                   },
     *                   "marcaCalcado": {
     *                       "idMarcaCalcado": 3,
     *                       "descricaoMarcaCalcado": "HAVAIANA",
     *                       "dataCadastroMarca": "2022-09-25T22:24:26.142"
     *                   },
     *                   "categoriaCalcado": {
     *                       "idCategoriaCalcado": 3,
     *                       "descricaoCategoria": "CHINELO",
     *                       "dataCadastroCategoria": "2022-09-25T15:56:03.613"
     *                   },
     *                   "tamanhoCalcado": {
     *                       "idTamanhoCalcado": 3,
     *                       "descricaoTamanhoCalcado": "42",
     *                       "dataCadastroTamanho": "2022-09-25T19:18:12.247"
     *                   }
     *               }, [...]
     *           ],
     *           "pageable": {
     *               "sort": {
     *                   "empty": false,
     *                   "sorted": true,
     *                   "unsorted": false
     *               },
     *               "offset": 0,
     *               "pageSize": 10,
     *               "pageNumber": 0,
     *               "unpaged": false,
     *               "paged": true
     *           },
     *           "totalPages": 1,
     *           "totalElements": 6,
     *           "last": true,
     *           "size": 10,
     *           "number": 0,
     *           "sort": {
     *               "empty": false,
     *               "sorted": true,
     *               "unsorted": false
     *           },
     *           "numberOfElements": 6,
     *           "first": true,
     *           "empty": false
     *       }
     *
     *       </pre>
     *
     * @param pageable Objeto de paginação do Spring. Os seus parâmetros podem ser usados no Endpoint. <b>EX:</b> <code>./calcado/?sort=codCalcado&page=1&value=10</code>
     * @return Um HttpStatus.OK, com um JSON com os resultados (mostrado acima).
     *
     */

    @GetMapping
    public ResponseEntity<Page<Calcado>> buscarTodosOsCalcados(@PageableDefault(sort = "idCalcado", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(calcadoService.findAll(pageable));
    }


    /**
     * <h3>Método GET para buscar um único Calçado através do ID</h3><br>
     * Nesse método é necessário utilizar o ID do calçado através do Endpoint.<br>
     * <code>./calcado/id/{idCalcado}</code><br>
     * <b>Exemplo:</b> <code>./calcado/id/3</code><br>
     * <b>JSON de Retorno:</b>
     * <pre>
     *     {
     *     "idCalcado": 3,
     *     "codCalcado": "01",
     *     "descricaoCalcado": "CHINELO",
     *     "qtdEstoque": 20.0,
     *     "dataCadastro": "2022-09-25T20:23:34.184",
     *     "precoCalcado": 18.5,
     *     "corCalcado": {
     *         "idCorCalcado": 2,
     *         "descricaoCorCalcado": "PRETO",
     *         "dataCadastroCor": "2022-09-25T15:57:20.811"
     *     },
     *     "marcaCalcado": {
     *         "idMarcaCalcado": 3,
     *         "descricaoMarcaCalcado": "HAVAIANA",
     *         "dataCadastroMarca": "2022-09-25T22:24:26.142"
     *     },
     *     "categoriaCalcado": {
     *         "idCategoriaCalcado": 3,
     *         "descricaoCategoria": "CHINELO",
     *         "dataCadastroCategoria": "2022-09-25T15:56:03.613"
     *     },
     *     "tamanhoCalcado": {
     *         "idTamanhoCalcado": 3,
     *         "descricaoTamanhoCalcado": "42",
     *         "dataCadastroTamanho": "2022-09-25T19:18:12.247"
     *     }
     * }
     * </pre>
     *
     * @param idCalcado Identificador Integer do Calçado (id_calcado no banco de dados).
     * @return Um HttpStatus.OK, com um JSON com os resultados (mostrado acima).
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> buscarCalcadoPorId(@PathVariable(value = "id") Integer idCalcado){

        Optional<Calcado> calcadoOptional = calcadoService.findById(idCalcado);
        if(!calcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Nenhum calçado encontrado com esse ID.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(calcadoOptional);

    }

    /**
     * <h3>Método GET para buscar um único Calçado através do Código</h3><br>
     * Nesse método é necessário utilizar o código do calçado através do Endpoint.<br>
     * <code>./calcado/cod/{codCalcado}</code><br>
     * <b>Exemplo:</b> <code>./calcado/cod/01</code><br>
     * <b>JSON de Retorno:</b>
     *
     * <pre>
     *
     *     {
     *     "idCalcado": 3,
     *     "codCalcado": "01",
     *     "descricaoCalcado": "CHINELO",
     *     "qtdEstoque": 20.0,
     *     "dataCadastro": "2022-09-25T20:23:34.184",
     *     "precoCalcado": 18.5,
     *     "corCalcado": {
     *         "idCorCalcado": 2,
     *         "descricaoCorCalcado": "PRETO",
     *         "dataCadastroCor": "2022-09-25T15:57:20.811"
     *     },
     *     "marcaCalcado": {
     *         "idMarcaCalcado": 3,
     *         "descricaoMarcaCalcado": "HAVAIANA",
     *         "dataCadastroMarca": "2022-09-25T22:24:26.142"
     *     },
     *     "categoriaCalcado": {
     *         "idCategoriaCalcado": 3,
     *         "descricaoCategoria": "CHINELO",
     *         "dataCadastroCategoria": "2022-09-25T15:56:03.613"
     *     },
     *     "tamanhoCalcado": {
     *         "idTamanhoCalcado": 3,
     *         "descricaoTamanhoCalcado": "42",
     *         "dataCadastroTamanho": "2022-09-25T19:18:12.247"
     *     }
     * }
     * </pre>
     *
     * @param codCalcado Código String do Calçado (cod_calcado no banco de dados).
     * @return Um HttpStatus.OK, com um JSON com os resultados (mostrado acima).
     */

    @GetMapping("/cod/{cod}")
    public ResponseEntity<Object> buscarCalcadosPorCod(@PathVariable(value = "cod") String codCalcado){
        Optional<Calcado> calcadoOptional = calcadoService.findByCodCalcado(codCalcado);
        if(!calcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Nenhum calçado encontrado com esse ID.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(calcadoOptional);
    }

    /**
     * <h3>Método GET de busca totalmente customizada. Todos os parâmetros são opcionais.</h3>
     *
     * Nesse método, o endpoint usado será: <b>./calcado/buscar/{parametros}</b><br>
     * Todos os parâmetros são opcionais, parâmetros de paginação estão inclusos.<br>
     * <b>Exemplo de endpoint:</b>
     * ./calcado/buscar?desccricaoCalcado=CHINELO&marcaCalcado=HAVAIANA&sort=precoCalcado,DESC <br>
     * <b>Retornará o seguinte JSON:</b>
     * <pre>
     * {
     *     "content": [
     *         {
     *             "idCalcado": 3,
     *             "codCalcado": "01",
     *             "descricaoCalcado": "CHINELO",
     *             "qtdEstoque": 20.0,
     *             "dataCadastro": "2022-09-25T20:23:34.184",
     *             "precoCalcado": 18.5,
     *             "corCalcado": {
     *                 "idCorCalcado": 2,
     *                 "descricaoCorCalcado": "PRETO",
     *                 "dataCadastroCor": "2022-09-25T15:57:20.811"
     *             },
     *             "marcaCalcado": {
     *                 "idMarcaCalcado": 3,
     *                 "descricaoMarcaCalcado": "HAVAIANA",
     *                 "dataCadastroMarca": "2022-09-25T22:24:26.142"
     *             },
     *             "categoriaCalcado": {
     *                 "idCategoriaCalcado": 3,
     *                 "descricaoCategoria": "CHINELO",
     *                 "dataCadastroCategoria": "2022-09-25T15:56:03.613"
     *             },
     *             "tamanhoCalcado": {
     *                 "idTamanhoCalcado": 3,
     *                 "descricaoTamanhoCalcado": "42",
     *                 "dataCadastroTamanho": "2022-09-25T19:18:12.247"
     *             }
     *         },
     *         {
     *             "idCalcado": 4,
     *             "codCalcado": "02",
     *             "descricaoCalcado": "CHINELO",
     *             "qtdEstoque": 20.0,
     *             "dataCadastro": "2022-09-25T20:24:39.103",
     *             "precoCalcado": 18.5,
     *             "corCalcado": {
     *                 "idCorCalcado": 1,
     *                 "descricaoCorCalcado": "BRANCO",
     *                 "dataCadastroCor": "2022-09-25T15:57:15.476"
     *             },
     *             "marcaCalcado": {
     *                 "idMarcaCalcado": 3,
     *                 "descricaoMarcaCalcado": "HAVAIANA",
     *                 "dataCadastroMarca": "2022-09-25T22:24:26.142"
     *             },
     *             "categoriaCalcado": {
     *                 "idCategoriaCalcado": 3,
     *                 "descricaoCategoria": "CHINELO",
     *                 "dataCadastroCategoria": "2022-09-25T15:56:03.613"
     *             },
     *             "tamanhoCalcado": {
     *                 "idTamanhoCalcado": 4,
     *                 "descricaoTamanhoCalcado": "44",
     *                 "dataCadastroTamanho": "2022-09-25T19:18:18.971"
     *             }
     *         }
     *     ],
     *     "pageable": {
     *         "sort": {
     *             "empty": false,
     *             "sorted": true,
     *             "unsorted": false
     *         },
     *         "offset": 0,
     *         "pageNumber": 0,
     *         "pageSize": 10,
     *         "paged": true,
     *         "unpaged": false
     *     },
     *     "last": true,
     *     "totalPages": 1,
     *     "totalElements": 2,
     *     "size": 10,
     *     "number": 0,
     *     "sort": {
     *         "empty": false,
     *         "sorted": true,
     *         "unsorted": false
     *     },
     *     "numberOfElements": 2,
     *     "first": true,
     *     "empty": false
     * }
     *
     * </pre>
     *
     *
     * @param params Parametros Opcionais utilizados na busca
     * @param pageable Objeto de paginação do Spring. Os seus parâmetros podem ser usados no Endpoint. <b>EX:</b> <code>./calcado/?sort=codCalcado&page=1&value=10</code>
     * @return Um HttpStatus.OK, com um JSON com os resultados (mostrado acima).
     */
    @GetMapping("/buscar")
    public ResponseEntity<Page<Calcado>> buscarCalcadosCustom(CalcadosBuscaCustomDTO params, @PageableDefault(sort = "descricaoCalcado", direction = Sort.Direction.ASC)Pageable pageable){

        Specification<Calcado> specifications = Specification.where(null);

        if(params != null){
            if(params.getDescricaoCalcado() != null){
                specifications = specifications.and(contemDescricao(params.getDescricaoCalcado()));
            }
            if(params.getTamanhoCalcado() != null){
                specifications = specifications.and(temTamanho(params.getTamanhoCalcado()));
            }
            if(params.getMarcaCalcado() != null){
                specifications = specifications.and(temMarca(params.getMarcaCalcado()));
            }
            if(params.getCategoriaCalcado() != null){
                specifications = specifications.and(temCategoria(params.getCategoriaCalcado()));
            }
            if(params.getCorCalcado() != null){
                specifications = specifications.and(temCor(params.getCorCalcado()));
            }
            if(params.getPrecoCalcado() != null && params.getPrecoCalcadoGT() == null && params.getPrecoCalcadoLT() == null){
                specifications = specifications.and(temPreco(params.getPrecoCalcado()));
            }
            if(params.getPrecoCalcadoGT() != null && params.getPrecoCalcado() == null && params.getPrecoCalcadoLT() == null){
                specifications = specifications.and(precoMaiorQue(params.getPrecoCalcadoGT()));
            }
            if(params.getPrecoCalcadoLT() != null && params.getPrecoCalcadoGT() == null && params.getPrecoCalcado() == null){
                specifications = specifications.and(precoMenorQue(params.getPrecoCalcadoLT()));
            }
            if(params.getDataCadastro() != null && params.getDataCadastroGT() == null && params.getDataCadastroLT() == null){
                specifications = specifications.and(temDataCadastro(params.getDataCadastro()));
            }
            if(params.getDataCadastroGT() != null && params.getDataCadastro() == null && params.getDataCadastroLT() == null){
                specifications = specifications.and(dataCadastroMaiorQue(params.getDataCadastroGT()));
            }
            if(params.getDataCadastroLT() != null && params.getDataCadastroGT() == null && params.getDataCadastro() == null){
                specifications = specifications.and(dataCadastroMenorQue(params.getDataCadastroLT()));
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(calcadoService.findAll(specifications, pageable));

    }


}
