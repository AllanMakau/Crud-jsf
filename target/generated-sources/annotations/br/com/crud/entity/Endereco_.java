package br.com.crud.entity;

import br.com.crud.entity.Cidade;
import br.com.crud.entity.Funcionario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-03T08:39:08")
@StaticMetamodel(Endereco.class)
public class Endereco_ { 

    public static volatile SingularAttribute<Endereco, Cidade> cidade;
    public static volatile SingularAttribute<Endereco, String> complemento;
    public static volatile SingularAttribute<Endereco, String> endereco;
    public static volatile SingularAttribute<Endereco, String> numero;
    public static volatile SingularAttribute<Endereco, String> bairro;
    public static volatile SingularAttribute<Endereco, Integer> id;
    public static volatile SingularAttribute<Endereco, Funcionario> funcionario;
    public static volatile SingularAttribute<Endereco, String> cep;

}