package com.oscar.oscarcalcadostest.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TamanhoDTO {

    @NotBlank
    private String descricaoTamanhoCalcado;

}
