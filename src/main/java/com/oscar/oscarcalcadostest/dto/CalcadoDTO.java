package com.oscar.oscarcalcadostest.dto;

import com.oscar.oscarcalcadostest.model.CategoriaCalcado;
import com.oscar.oscarcalcadostest.model.CorCalcado;
import com.oscar.oscarcalcadostest.model.MarcaCalcado;
import com.oscar.oscarcalcadostest.model.TamanhoCalcado;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CalcadoDTO {

    @NotBlank
    private String codCalcado;

    @NotBlank
    private String descricaoCalcado;

    @NotNull
    private Double qtdEstoque;

    @NotNull
    private Double precoCalcado;
    @NotNull
    private CorCalcado corCalcado;
    @NotNull
    private MarcaCalcado marcaCalcado;
    @NotNull
    private CategoriaCalcado categoriaCalcado;
    @NotNull
    TamanhoCalcado tamanhoCalcado;

}
