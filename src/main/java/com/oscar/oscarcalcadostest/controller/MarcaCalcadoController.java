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

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/marca-calcado")
public class MarcaCalcadoController {

    final MarcaCalcadoService marcaCalcadoService;

    public MarcaCalcadoController(MarcaCalcadoService marcaCalcadoService){
        this.marcaCalcadoService = marcaCalcadoService;
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarMarca(@PathVariable(value = "id") Integer id){

        Optional<MarcaCalcado> marcaCalcadoOptional = marcaCalcadoService.findById(id);
        if(!marcaCalcadoOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Nenhuma marca encontrada");
        }

        marcaCalcadoService.delete(marcaCalcadoOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Deletado com Sucesso!");
    }

    @GetMapping
    public ResponseEntity<Page<MarcaCalcado>> buscarTodasAsMarcas(@PageableDefault( sort = "idMarcaCalcado", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(marcaCalcadoService.findAll(pageable));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> buscarMarcasPorId(@PathVariable(value = "id") Integer idMarca){

        Optional<MarcaCalcado> marcaCalcadoOptional = marcaCalcadoService.findById(idMarca);
        if(!marcaCalcadoOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Nenhuma marca encontrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(marcaCalcadoOptional.get());

    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<Object> buscarMarcasPorDescricao(@PathVariable(value = "descricao") String descricaoMarca){

        List<MarcaCalcado> marcaCalcadoList = marcaCalcadoService.findByDescription(descricaoMarca);
        if(marcaCalcadoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma marca encontrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(marcaCalcadoList);

    }

}
