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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/categoria-calcado")
public class CategoriaController {

    final CategoriaCalcadoService categoriaCalcadoService;

    public CategoriaController(CategoriaCalcadoService categoriaCalcadoService){
        this.categoriaCalcadoService = categoriaCalcadoService;
    }


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

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCategoria(@PathVariable(value = "id") Integer idCategoria){

        Optional<CategoriaCalcado> categoriaCalcadoOptional = categoriaCalcadoService.findById(idCategoria);
        if(!categoriaCalcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado");
        }

        categoriaCalcadoService.delete(categoriaCalcadoOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com Sucesso!");

    }

    @GetMapping
    public ResponseEntity<Page<CategoriaCalcado>> buscarTodasAsCategorias(@PageableDefault(sort="idCategoriaCalcado", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaCalcadoService.findAll(pageable));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> buscarCategoriasPorId(@PathVariable(value = "id") Integer idCategoria){

        Optional<CategoriaCalcado> categoriaCalcadoOptional = categoriaCalcadoService.findById(idCategoria);
        if(!categoriaCalcadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Categoria não encontrada.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(categoriaCalcadoOptional.get());

    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<Object> buscarCategoriasPorDescricao(@PathVariable(value = "descricao") String descricaoCategoria){

        List<CategoriaCalcado> categoriaCalcadoList = categoriaCalcadoService.findAllByDescricaoLikeIgnoreCase(descricaoCategoria);

        if(categoriaCalcadoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: nenhuma categoria encontrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(categoriaCalcadoList);

    }

}
