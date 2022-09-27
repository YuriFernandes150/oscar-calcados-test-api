package com.oscar.oscarcalcadostest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Este Bean é uma Entity do JPA, responsável pelas operações na tabela tbl_categoria_calcado
 * @author YuriFernandes150
 */
@Entity
@Table(name = "tbl_categoria_calcado")
@Getter
@Setter
public class CategoriaCalcado implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @Column(name = "id_categoria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoriaCalcado;

    @Column(name = "descricao_categoria", nullable = false)
    private String descricaoCategoria;

    @Column(name = "data_cadastro_categoria", nullable = false)
    private LocalDateTime dataCadastroCategoria;

}
