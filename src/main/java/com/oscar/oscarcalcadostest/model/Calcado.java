package com.oscar.oscarcalcadostest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Este Bean é uma Entity do JPA, responsável pelas operações na tabela tbl_calcados
 * @author YuriFernandes150
 */

@Entity
@Table(name = "tbl_calcados")
@Getter
@Setter
public class Calcado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_calcado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCalcado;

    @Column(name = "cod_calcado", nullable = false, unique = true)
    private String codCalcado;

    @Column(name = "descricao_calcado", nullable = false)
    private String descricaoCalcado;

    @Column(name = "qtd_estoque", nullable = false)
    private Double qtdEstoque;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "preco", nullable = false)
    private Double precoCalcado;

    @OneToOne
    @JoinColumn(name = "fk_cor", nullable = false)
    private CorCalcado corCalcado;

    @OneToOne
    @JoinColumn(name = "fk_marca", referencedColumnName = "id_marca_calcado", nullable = false)
    private MarcaCalcado marcaCalcado;

    @OneToOne
    @JoinColumn(name = "fk_categoria", referencedColumnName = "id_categoria", nullable = false)
    private CategoriaCalcado categoriaCalcado;

    @OneToOne
    @JoinColumn(name = "fk_tamanho", referencedColumnName = "id_tamanho_calcado", nullable = false)
    private TamanhoCalcado tamanhoCalcado;

}
