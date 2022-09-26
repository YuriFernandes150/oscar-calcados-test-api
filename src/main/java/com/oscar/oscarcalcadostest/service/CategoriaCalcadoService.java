package com.oscar.oscarcalcadostest.service;

import com.oscar.oscarcalcadostest.model.CategoriaCalcado;
import com.oscar.oscarcalcadostest.repository.CategoriaCalcadosRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaCalcadoService {
    final CategoriaCalcadosRepository categoriaCalcadosRepository;

    public CategoriaCalcadoService(CategoriaCalcadosRepository categoriaCalcadosRepository){
        this.categoriaCalcadosRepository = categoriaCalcadosRepository;
    }

    public boolean existsByDescricaoCategoria(String descricaoCategoria) {
        return categoriaCalcadosRepository.existsByDescricaoCategoria(descricaoCategoria);
    }

    public CategoriaCalcado save(CategoriaCalcado categoriaCalcado) {
        return categoriaCalcadosRepository.save(categoriaCalcado);
    }

    public Optional<CategoriaCalcado> findById(Integer idCategoria) {
        return categoriaCalcadosRepository.findById(idCategoria);
    }

    public void delete(CategoriaCalcado idCategoria) {
        categoriaCalcadosRepository.delete(idCategoria);
    }

    public Page<CategoriaCalcado> findAll(Pageable pageable) {
        return categoriaCalcadosRepository.findAll(pageable);
    }

    public List<CategoriaCalcado> findAllByDescricaoLikeIgnoreCase(String descricaoCategoria) {
        return categoriaCalcadosRepository.findAllByDescricaoCategoriaContainsIgnoreCase(descricaoCategoria);
    }
}
