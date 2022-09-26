package com.oscar.oscarcalcadostest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_cor_calcado")
@Getter
@Setter
public class CorCalcado implements Serializable {
    private static final long serialVersionUID = 3L;

    @Id
    @Column(name = "id_cor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCorCalcado;

    @Column(name = "descricao_cor", nullable = false)
    private String descricaoCorCalcado;

    @Column(name = "data_cadastro_cor", nullable = false)
    private LocalDateTime dataCadastroCor;

}
