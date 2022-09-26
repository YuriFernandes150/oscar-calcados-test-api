package com.oscar.oscarcalcadostest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_tamanho_calcado")
@Getter
@Setter
public class TamanhoCalcado implements Serializable {
    private static final long serialVersionUID = 5L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tamanho_calcado")
    private Integer idTamanhoCalcado;

    @Column(name = "descricao_tamanho_calcado", nullable = false)
    private String descricaoTamanhoCalcado;

    @Column(name = "data_cadastro_tamanho", nullable = false)
    private LocalDateTime dataCadastroTamanho;

}
