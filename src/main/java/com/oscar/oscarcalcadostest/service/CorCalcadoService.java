package com.oscar.oscarcalcadostest.service;

import com.oscar.oscarcalcadostest.model.CorCalcado;
import com.oscar.oscarcalcadostest.repository.CorCalcadoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CorCalcadoService {

    final CorCalcadoRepository corCalcadoRepository;

    public CorCalcadoService(CorCalcadoRepository corCalcadoRepository){
        this.corCalcadoRepository = corCalcadoRepository;
    }


    public boolean existsByDescricaoCorCalcado(String descricaoCorCalcado) {
        return corCalcadoRepository.existsByDescricaoCorCalcado(descricaoCorCalcado);
    }

    public CorCalcado save(CorCalcado corCalcado) {
        return corCalcadoRepository.save(corCalcado);
    }

    public Optional<CorCalcado> findById(Integer idCor) {
        return corCalcadoRepository.findById(idCor);
    }

    public void delete(CorCalcado corCalcado) {
        corCalcadoRepository.delete(corCalcado);
    }

    public Page<CorCalcado> findAll(Pageable pageable) {
        return corCalcadoRepository.findAll(pageable);
    }

    public List<CorCalcado> findByDescricaoContainsIgnoreCase(String descricaoCorCalcado) {
        return corCalcadoRepository.findAllByDescricaoCorCalcadoContainsIgnoreCase(descricaoCorCalcado);
    }
}
