/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crud.dao;

import br.com.crud.entity.Endereco;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Alan
 */

@ManagedBean
@ViewScoped
public class EnderecoDao extends GenericoDao<Endereco, Integer>{
    
    public EnderecoDao(){
        super(Endereco.class);
    }
}
