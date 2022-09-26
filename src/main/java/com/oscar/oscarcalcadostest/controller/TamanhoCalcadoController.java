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


@RestController
@CrossOrigin("*")
@RequestMapping("/tamanho-calcado")
public class TamanhoCalcadoController {

    TamanhoCalcadoService tamanhoCalcadoService;

    public TamanhoCalcadoController(TamanhoCalcadoService tamanhoCalcadoService){
        this.tamanhoCalcadoService = tamanhoCalcadoService;
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarTamanho(@PathVariable(value = "id") Integer idTamanhoCalcado){

        Optional<TamanhoCalcado> tamanhoCalcadoOptional = tamanhoCalcadoService.findById(idTamanhoCalcado);
        if(!tamanhoCalcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Não foi encontrado nenhum tamanho com esse ID");
        }

        tamanhoCalcadoService.delete(tamanhoCalcadoOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Deletado com Sucesso!");

    }

    @GetMapping()
    public ResponseEntity<Page<TamanhoCalcado>> buscarTodosOsTamanhos(@PageableDefault(sort = "descricaoTamanhoCalcado", direction = Sort.Direction.ASC)Pageable pageable){

        return ResponseEntity.status(HttpStatus.OK).body(tamanhoCalcadoService.findAll(pageable));

    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> buscarTamanhoPorId(@PathVariable(value = "id") Integer idTamanhoCalcado){

        Optional<TamanhoCalcado> tamanhoCalcadoOptional = tamanhoCalcadoService.findById(idTamanhoCalcado);
        if(!tamanhoCalcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Não foi encontrado nenhum tamanho com esse ID");
        }

        return ResponseEntity.status(HttpStatus.OK).body(tamanhoCalcadoOptional);

    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<Object> buscarTamanhosPorDescricao(@PathVariable(value = "descricao") String descricaoTamanho){

        List<TamanhoCalcado> tamanhoCalcadoList = tamanhoCalcadoService.findAllByDescricaoTamanhoCalcado(descricaoTamanho);
        if(tamanhoCalcadoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum tamanho encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tamanhoCalcadoList);

    }

}
