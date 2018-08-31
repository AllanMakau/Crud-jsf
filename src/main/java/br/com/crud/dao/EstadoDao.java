/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crud.dao;

import br.com.crud.entity.Estado;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Alan
 */

@ManagedBean
@ViewScoped
public class EstadoDao  extends GenericoDao<Estado, Integer>{
    
    public EstadoDao(){
        super(Estado.class);
    }
}
