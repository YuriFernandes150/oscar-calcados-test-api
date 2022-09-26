package com.oscar.oscarcalcadostest.service;

import com.oscar.oscarcalcadostest.model.MarcaCalcado;
import com.oscar.oscarcalcadostest.repository.MarcaCalcadosRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MarcaCalcadoService {


    final MarcaCalcadosRepository marcaCalcadosRepository;

    public MarcaCalcadoService(MarcaCalcadosRepository marcaCalcadosRepository){
        this.marcaCalcadosRepository = marcaCalcadosRepository;
    }

    @Transactional
    public MarcaCalcado save(MarcaCalcado marcaCalcado) {
        return marcaCalcadosRepository.save(marcaCalcado);
    }

    public boolean existsByDescricaoMarca(String descricaoMarca) {
        return marcaCalcadosRepository.existsByDescricaoMarcaCalcado(descricaoMarca);
    }

    public Page<MarcaCalcado> findAll(Pageable pageable) {
        return marcaCalcadosRepository.findAll(pageable);
    }

    public Optional<MarcaCalcado> findById(Integer id) {
        return marcaCalcadosRepository.findById(id);
    }

    public void delete(MarcaCalcado marcaCalcado) {
        marcaCalcadosRepository.delete(marcaCalcado);
    }

    public List<MarcaCalcado> findByDescription(String descricaoMarca) {
        return marcaCalcadosRepository.findAllByDescricaoMarcaCalcadoContainingIgnoreCase(descricaoMarca);
    }
}
