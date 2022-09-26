package com.oscar.oscarcalcadostest.service;

import com.oscar.oscarcalcadostest.model.Calcado;
import com.oscar.oscarcalcadostest.repository.CalcadoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalcadoService {

    final CalcadoRepository calcadoRepository;

    public CalcadoService(CalcadoRepository calcadoRepository){
        this.calcadoRepository = calcadoRepository;
    }

    public Optional<Calcado> findByCodCalcado(String codCalcado) {
        return calcadoRepository.findByCodCalcado(codCalcado);
    }

    public Calcado save(Calcado calcado) {
        return calcadoRepository.save(calcado);
    }

    public Optional<Calcado> findById(Integer idCalcado) {
        return calcadoRepository.findById(idCalcado);
    }

    public void delete(Calcado calcado) {
        calcadoRepository.delete(calcado);
    }

    public Page<Calcado> findAll(Pageable pageable) {
        return calcadoRepository.findAll(pageable);
    }

    public Page<Calcado> findAll(Specification<Calcado> specifications, Pageable pageable){

        return calcadoRepository.findAll(specifications, pageable);

    }

    public boolean existsByCodCalcado(String codCalcado) {
        return calcadoRepository.existsByCodCalcado(codCalcado);
    }
}
