package com.oscar.oscarcalcadostest.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * Este DTO é uma extensão da Entity CorCalcado, ele serve para executar operações com o banco de dados.<br>
 * As Anotations @Getter e @Setter permitem a criação dos GetSet sem seração de código extra.
 * @author YuriFernandes150
 */
@Getter
@Setter
public class CorCalcadoDTO {

    @NotBlank
    private String descricaoCorCalcado;

}
