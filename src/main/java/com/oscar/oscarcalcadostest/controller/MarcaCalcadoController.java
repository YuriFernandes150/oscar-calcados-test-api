package com.oscar.oscarcalcadostest.controller;

import com.oscar.oscarcalcadostest.dto.MarcaCalcadoDTO;
import com.oscar.oscarcalcadostest.model.MarcaCalcado;
import com.oscar.oscarcalcadostest.service.MarcaCalcadoService;
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
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

/**
 * Este controlador Rest é responsável pelos métodos CRUD da Entity MarcaCalcado.
 * Seu EndPoint base é htttp://uri:8080/marca-calcado
 *
 * @author YuriFernandes150
 * */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/marca-calcado")
public class MarcaCalcadoController {

    /**
     * O Service vai ser responsável pela comunicação com o Repository do JPA para as operações com o banco de dados.
     * Além da declaração no construtor, também é possível utilizar a anotação @Autowired.
     */
    final MarcaCalcadoService marcaCalcadoService;

    public MarcaCalcadoController(MarcaCalcadoService marcaCalcadoService){
        this.marcaCalcadoService = marcaCalcadoService;
    }

    /**
     * <h3>O método POST cadastra uma nova Marca no banco.</h3>
     * É necessário enviar um JSON válido para o EndPoint base do controlador.<br>
     * <b>Exemplo:</b>
     * <pre>
     * {
     *     "descricaoMarcaCalcado":"MOLEKA"
     * }
     * </pre>
     * Em caso de sucesso, a API retorna:
     * <pre>
     * {
     *     "idMarcaCalcado": 44,
     *     "descricaoMarcaCalcado": "MOLEKA",
     *     "dataCadastroMarca": "2022-09-26T21:08:05.603"
     * }
     * </pre>
     * @param marcaCalcadoDTO O objeto DTO de Marca, somente o campo descrição precisa ser preenchido.
     * @return Uma resposta com status de <pre>HttpStatus.CREATED</pre> ou <pre>HttpStatus.CONFLICT</pre> caso a propriedade descricaoMarcaCalcado já exista no banco de dados.
     */
    @PostMapping
    public ResponseEntity<Object> salvarMarca(@RequestBody @Valid MarcaCalcadoDTO marcaCalcadoDTO){

        if(marcaCalcadoService.existsByDescricaoMarca(marcaCalcadoDTO.getDescricaoMarcaCalcado())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Marca já existe.");
        }

        MarcaCalcado marcaCalcado = new MarcaCalcado();
        BeanUtils.copyProperties(marcaCalcadoDTO, marcaCalcado);
        marcaCalcado.setDataCadastroMarca(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(marcaCalcadoService.save(marcaCalcado));

    }

    /**
     * <h3>Método PUT atualiza uma Marca existente com novos dados.</h3><br>
     * Deve-se enviar o mesmo JSON de cadastro <br>
     * para o endpoint <b>/marca-calcado/{idMarca}</b> <br>
     * (Sendo <b>idMarca</b> o campo Integer identificador da Marca que deseja atualizar).<br>
     * <b>JSON Exemplo: para o endpoint ./marca-calcado/34</b>
     * <pre>
     * {
     *     "descricaoMarcaCalcado":"MOLEKINHA"
     * }
     * </pre>
     * Em caso de sucesso, a API retorna:
     * <pre>
     * {
     *     "idMarcaCalcado": 44,
     *     "descricaoMarcaCalcado": "MOLEKINHA",
     *     "dataCadastroMarca": "2022-09-26T21:08:05.603"
     * }
     * </pre>
     * @param idMarca Identificador Integer da Marca a ser atualizado (id_marca no banco de dados).
     * @param marcaCalcadoDTO O objeto DTO de Marca, somente o campo descrição precisa ser preenchido.
     * @return Uma resposta com status de <pre>HttpStatus.OK</pre> ou <pre>HttpStatus.NOT_FOUND</pre> caso nenhuma marca seja encontrada com o ID fornecido.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarMarca(@PathVariable(value = "id") Integer idMarca, @RequestBody @Valid MarcaCalcadoDTO marcaCalcadoDTO){

        Optional<MarcaCalcado> marcaCalcadoOptional = marcaCalcadoService.findById(idMarca);
        if(!marcaCalcadoOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Nenhuma marca encontrada");
        }

        MarcaCalcado novaMarcaCalcado = marcaCalcadoOptional.get();
        novaMarcaCalcado.setDescricaoMarcaCalcado(marcaCalcadoDTO.getDescricaoMarcaCalcado());

        return ResponseEntity.status(HttpStatus.OK).body(marcaCalcadoService.save(novaMarcaCalcado));

    }

    /**
     *
     * <h3>Método DELETE apaga um registro permanentemente do banco de dados, use com precaução.</h3><br>
     * Não é necessário enviar um JSON para esse método. <br>
     * Este método usa o endpoint <b>/marca-calcado/{idMarca}</b> <br>
     * (Sendo <b>idMarca</b> o campo Integer identificador da Marca que deseja deletar).<br>
     *
     * @param idMarca Identificador Integer da Marca a ser atualizada (id_marca no banco de dados).
     * @return Uma resposta com status de <pre>HttpStatus.OK</pre> ou <pre>HttpStatus.NOT_FOUND</pre> caso nenhuma marca seja encontrada com o ID fornecido.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarMarca(@PathVariable(value = "id") Integer idMarca){

        Optional<MarcaCalcado> marcaCalcadoOptional = marcaCalcadoService.findById(idMarca);
        if(!marcaCalcadoOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Nenhuma marca encontrada");
        }

        marcaCalcadoService.delete(marcaCalcadoOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Deletado com Sucesso!");
    }

    /**
     * h3>Esse método GET busca todas as marcas no banco e os separa em páginas.</h3><br>
     * <b>Exemplo de JSON de retorno:</b>
     * <pre>
     *     {
     *     "content": [
     *         {
     *             "idMarcaCalcado": 3,
     *             "descricaoMarcaCalcado": "HAVAIANA",
     *             "dataCadastroMarca": "2022-09-25T22:24:26.142"
     *         },
     *         {
     *             "idMarcaCalcado": 4,
     *             "descricaoMarcaCalcado": "VIZZANO",
     *             "dataCadastroMarca": "2022-09-25T22:24:59.084"
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
     *     "totalElements": 8,
     *     "size": 10,
     *     "number": 0,
     *     "sort": {
     *         "empty": false,
     *         "unsorted": false,
     *         "sorted": true
     *     },
     *     "numberOfElements": 8,
     *     "first": true,
     *     "empty": false
     * }
     * </pre>
     * @param pageable Objeto de paginação do Spring. Os seus parâmetros podem ser usados no Endpoint. <b>EX:</b> <code>./marca-calcado/?sort=descricaoMarcaCalcado,ASC&page=1&value=10</code>
     * @return Um HttpStatus.OK, com um JSON com os resultados (mostrado acima).
     */
    @GetMapping
    public ResponseEntity<Page<MarcaCalcado>> buscarTodasAsMarcas(@PageableDefault( sort = "idMarcaCalcado", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(marcaCalcadoService.findAll(pageable));
    }

    /**
     * <h3>Método GET para buscar uma única Marca através do ID</h3><br>
     * Nesse método é necessário utilizar o ID da marca através do Endpoint.<br>
     * <code>./marca-calcado/id/{idMarca}</code><br>
     * <b>Exemplo:</b> <code>./marca-calcado/id/3</code><br>
     * <b>JSON de Retorno:</b>
     * <pre>
     * {
     *     "idMarcaCalcado": 3,
     *     "descricaoMarcaCalcado": "HAVAIANA",
     *     "dataCadastroMarca": "2022-09-25T22:24:26.142"
     * }
     * </pre>
     * @param idMarca Identificador Integer da Marca (id_marca no banco de dados).
     * @return Um HttpStatus.OK, com um JSON com os resultados (mostrado acima).
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> buscarMarcasPorId(@PathVariable(value = "id") Integer idMarca){

        Optional<MarcaCalcado> marcaCalcadoOptional = marcaCalcadoService.findById(idMarca);
        if(!marcaCalcadoOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Nenhuma marca encontrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(marcaCalcadoOptional.get());

    }

    /**
     * <h3>Método GET para buscar todas as marcas através da descrição</h3><br>
     * Nesse método é necessário utilizar a descrição da cor através do Endpoint.<br>
     * <code>./marca-calcado/descricao/{descricaoMarca}</code><br>
     * <b>Exemplo:</b> <code>./marca-calcado/descricao/AR</code><br>
     * <b>JSON de Retorno:</b>
     * <pre>
     * [
     *     {
     *         "idMarcaCalcado": 5,
     *         "descricaoMarcaCalcado": "AREZZO",
     *         "dataCadastroMarca": "2022-09-25T22:25:03.531"
     *     },
     *     {
     *         "idMarcaCalcado": 6,
     *         "descricaoMarcaCalcado": "VIA MARTE",
     *         "dataCadastroMarca": "2022-09-25T22:25:09.485"
     *     }
     * ]
     * </pre>
     * @param descricaoMarca ‘String’ de descrição da Marca (descricao_marca no banco de dados).
     * @return Um HttpStatus.OK, com um JSON com os resultados (mostrado acima).
     */
    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<Object> buscarMarcasPorDescricao(@PathVariable(value = "descricao") String descricaoMarca){

        List<MarcaCalcado> marcaCalcadoList = marcaCalcadoService.findByDescription(descricaoMarca);
        if(marcaCalcadoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma marca encontrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(marcaCalcadoList);

    }

}
