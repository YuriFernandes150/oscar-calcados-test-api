package com.oscar.oscarcalcadostest.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class CategoriaDTO {

    @NotBlank
    private String descricaoCategoria;

}
