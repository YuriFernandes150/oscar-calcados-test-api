package com.oscar.oscarcalcadostest.service;

import com.oscar.oscarcalcadostest.model.TamanhoCalcado;
import com.oscar.oscarcalcadostest.repository.TamanhoCalcadoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TamanhoCalcadoService {

    final TamanhoCalcadoRepository tamanhoCalcadoRepository;

    public TamanhoCalcadoService(TamanhoCalcadoRepository tamanhoCalcadoRepository){
        this.tamanhoCalcadoRepository = tamanhoCalcadoRepository;
    }


    public boolean existsByDescricaoTamanhoCalcado(String descricaoTamanhoCalcado) {
        return tamanhoCalcadoRepository.existsByDescricaoTamanhoCalcado(descricaoTamanhoCalcado);
    }

    public Optional<TamanhoCalcado> findById(Integer idTamanhoCalcado) {
        return tamanhoCalcadoRepository.findById(idTamanhoCalcado);
    }

    public TamanhoCalcado save(TamanhoCalcado tamanhoCalcado) {
        return tamanhoCalcadoRepository.save(tamanhoCalcado);
    }

    public void delete(TamanhoCalcado tamanhoCalcado) {
        tamanhoCalcadoRepository.delete(tamanhoCalcado);
    }

    public Page<TamanhoCalcado> findAll(Pageable pageable) {
        return tamanhoCalcadoRepository.findAll(pageable);
    }

    public List<TamanhoCalcado> findAllByDescricaoTamanhoCalcado(String descricaoTamanho) {
        return tamanhoCalcadoRepository.findAllByDescricaoTamanhoCalcadoContainsIgnoreCase(descricaoTamanho);
    }
}
