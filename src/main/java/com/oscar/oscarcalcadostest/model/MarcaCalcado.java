package com.oscar.oscarcalcadostest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="tbl_marca_calcado")
@Getter
@Setter
public class MarcaCalcado implements Serializable {
    private static final long serialVersionUID = 4L;

    @Id
    @Column(name = "id_marca_calcado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMarcaCalcado;

    @Column(name = "descricao_marca", nullable = false)
    private String descricaoMarcaCalcado;

    @Column(name = "data_cadastro_marca", nullable = false)
    private LocalDateTime dataCadastroMarca;

}
