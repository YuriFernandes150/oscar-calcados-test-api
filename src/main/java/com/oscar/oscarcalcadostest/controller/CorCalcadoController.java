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

@RestController
@CrossOrigin("*")
@RequestMapping("/cor-calcado")
public class CorCalcadoController {


    final CorCalcadoService corCalcadoService;

    public CorCalcadoController(CorCalcadoService corCalcadoService){
        this.corCalcadoService = corCalcadoService;
    }


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

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCor(@PathVariable(value = "id") Integer idCor){

        Optional<CorCalcado> corCalcadoOptional = corCalcadoService.findById(idCor);
        if(!corCalcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Cor não encontrada com esse id.");
        }

        corCalcadoService.delete(corCalcadoOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso!");

    }

    @GetMapping
    public ResponseEntity<Page<CorCalcado>> buscarTodasAsCores(@PageableDefault(sort = "idCorCalcado", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(corCalcadoService.findAll(pageable));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> buscarCorPorId(@PathVariable(value = "id") Integer idCor){

        Optional<CorCalcado> corCalcadoOptional = corCalcadoService.findById(idCor);
        if(!corCalcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Cor não encontrada com esse id.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(corCalcadoOptional.get());

    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<Object> buscarCoresPorDescricao(@PathVariable(value = "descricao") String descricaoCorCalcado){

        List<CorCalcado> corCalcadoList = corCalcadoService.findByDescricaoContainsIgnoreCase(descricaoCorCalcado);
        if(corCalcadoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma cor encontrada.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(corCalcadoList);

    }

}
