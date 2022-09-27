package com.oscar.oscarcalcadostest.specification;

import com.oscar.oscarcalcadostest.model.Calcado;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

/**
 * A Specification é usada pelo JPA para montar querys dinâmicas. Essa specification usa o Bean Calcado para montar queires com parâmetros opcionais;
 */
public class CalcadoSpecification {

    public static Specification<Calcado> temCodCalcado (String codCalcado){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("codCalcado"), codCalcado);
    }

    public static Specification<Calcado> contemDescricao(String descricaoCalcado){
        return  (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("descricaoCalcado")), "%"+descricaoCalcado.toLowerCase()+"%");
    }
    public static Specification<Calcado> temDataCadastro (LocalDateTime dataCadastro){
        return  (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("dataCadastro"), dataCadastro);
    }
    public static Specification<Calcado> dataCadastroMaiorQue(LocalDateTime dataCadastro){
        return  (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("dataCadastro"), dataCadastro);
    }
    public static Specification<Calcado> dataCadastroMenorQue (LocalDateTime dataCadastro){
        return  (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("dataCadastro"), dataCadastro);
    }

    public static Specification<Calcado> temPreco (Double precoCalcado){
        return  (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("precoCalcado"), precoCalcado);
    }

    public static Specification<Calcado> precoMaiorQue (Double precoCalcado){
        return  (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("precoCalcado"), precoCalcado);
    }

    public static Specification<Calcado> precoMenorQue (Double precoCalcado){
        return  (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("precoCalcado"), precoCalcado);
    }

    public static Specification<Calcado> temTamanho (String tamanhoCalcado){
        return  (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("tamanhoCalcado").get("descricaoTamanhoCalcado"), tamanhoCalcado);
    }

    public static Specification<Calcado> temMarca (String marcaCalcado){
        return  (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("marcaCalcado").get("descricaoMarcaCalcado"), marcaCalcado);
    }

    public static Specification<Calcado> temCategoria (String categoriaCalcado){
        return  (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("categoriaCalcado").get("descricaoCategoria"), categoriaCalcado);
    }
    public static Specification<Calcado> temCor (String corCalcado){
        return  (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("corCalcado").get("descricaoCorCalcado"), corCalcado);
    }




}
