package com.oscar.oscarcalcadostest.controller;

import com.oscar.oscarcalcadostest.dto.CalcadoDTO;
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

@RestController
@CrossOrigin("*")
@RequestMapping("/calcado")
public class CalcadoController {

    final CalcadoService calcadoService;

    public CalcadoController(CalcadoService calcadoService){
        this.calcadoService = calcadoService;
    }



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

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCalcado(@PathVariable(value = "id") Integer idCalcado){

        Optional<Calcado> calcadoOptional = calcadoService.findById(idCalcado);
        if(!calcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Nenhum calçado encontrado com esse ID.");
        }

        calcadoService.delete(calcadoOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Deletado com Sucesso!");

    }

    @GetMapping
    public ResponseEntity<Page<Calcado>> buscarTodosOsCalcados(@PageableDefault(sort = "idCalcado", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(calcadoService.findAll(pageable));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> buscarCalcadoPorId(@PathVariable(value = "id") Integer idCalcado){

        Optional<Calcado> calcadoOptional = calcadoService.findById(idCalcado);
        if(!calcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Nenhum calçado encontrado com esse ID.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(calcadoOptional);

    }

    @GetMapping("/cod/{cod}")
    public ResponseEntity<Object> buscarCalcadosPorCod(@PathVariable(value = "cod") String codCalcado){
        Optional<Calcado> calcadoOptional = calcadoService.findByCodCalcado(codCalcado);
        if(!calcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Nenhum calçado encontrado com esse ID.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(calcadoOptional);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<Calcado>> buscarCalcadosCustom(@RequestParam(value = "descricao", required = false) String descricaoCalcado,
                                                              @RequestParam(value = "tamanho", required = false) String tamanhoCalcado,
                                                              @RequestParam(value = "marca", required = false) String marcaCalcado,
                                                              @RequestParam(value = "categoria", required = false) String categoriaCalcado,
                                                              @RequestParam(value = "cor", required = false) String corCalcado,
                                                              @RequestParam(value = "preco", required = false) Double precoCalcado,
                                                              @RequestParam(value = "precogt", required = false) Double precoCalcadoGT,
                                                              @RequestParam(value = "precolt", required = false) Double precoCalcadoLT,
                                                              @RequestParam(value = "datacadastro", required = false) LocalDateTime dataCadastro,
                                                              @PageableDefault(sort = "descricaoCalcado", direction = Sort.Direction.ASC)Pageable pageable){

        Specification<Calcado> specifications = Specification.where(null);

        if(descricaoCalcado != null){
            specifications = specifications.and(contemDescricao(descricaoCalcado));
        }
        if(tamanhoCalcado != null){
            specifications = specifications.and(temTamanho(tamanhoCalcado));
        }
        if(marcaCalcado != null){
            specifications = specifications.and(temMarca(marcaCalcado));
        }
        if(categoriaCalcado != null){
            specifications = specifications.and(temCategoria(categoriaCalcado));
        }
        if(corCalcado != null){
            specifications = specifications.and(temCor(corCalcado));
        }
        if(precoCalcado != null){
            specifications = specifications.and(temPreco(precoCalcado));
        }
        if(precoCalcadoGT != null){
            specifications = specifications.and(precoMaiorQue(precoCalcadoGT));
        }
        if(precoCalcadoLT != null){
            specifications = specifications.and(precoMenorQue(precoCalcadoLT));
        }
        if(dataCadastro != null){
            specifications = specifications.and(temDataCadastro(dataCadastro));
        }

        return ResponseEntity.status(HttpStatus.OK).body(calcadoService.findAll(specifications, pageable));

    }


}
