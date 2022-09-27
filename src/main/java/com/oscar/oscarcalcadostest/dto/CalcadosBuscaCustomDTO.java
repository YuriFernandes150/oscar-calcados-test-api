package com.oscar.oscarcalcadostest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Este DTO contém os parâmetros necessários para aplicar filtros para a classe Calcado.<br>
 * As Anotations @Getter e @Setter permitem a criação dos GetSet sem seração de código extra.
 * @author YuriFernandes150
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class CalcadosBuscaCustomDTO {

    private String descricaoCalcado;
    private String tamanhoCalcado;
    private String marcaCalcado;
    private String categoriaCalcado;
    private String corCalcado;
    private Double precoCalcado;
    private Double precoCalcadoGT;
    private Double precoCalcadoLT;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataCadastroGT;
    private LocalDateTime dataCadastroLT;

}
