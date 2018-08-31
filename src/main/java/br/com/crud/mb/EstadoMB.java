/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crud.mb;

import br.com.crud.dao.EstadoDao;
import br.com.crud.entity.Estado;
import br.com.crud.util.Mensagem;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import jdk.nashorn.internal.runtime.Context;

/**
 *
 * @author Alan
 */

@ManagedBean
@ViewScoped
public class EstadoMB implements Serializable{
    
    @ManagedProperty(value = "#{estadoDao}")
    private EstadoDao estadoDao;
    
    private Estado estado;

    
    @PostConstruct
    public void init(){
        
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        
        if(id != null){
            estado = estadoDao.obterPorPK(Integer.parseInt(id));
        }else{
            estado = new Estado();
        }    
    }
    
    public String cadastrarAtualizar(){
        try {
            if(estado.getId() != null){
                estadoDao.alterar(estado);
                Mensagem.lancar("Estado atualizado com sucesso.");
            } else {
                estadoDao.inserir(estado);
                Mensagem.lancar("Estado cadastrado com sucesso.");
            }
        } catch (Exception e) {
            
            Mensagem.lancar("Erro ao cadastrar um estado.");
            Mensagem.lancar(e.getMessage());
        }
        
        return "lista.xhtml";
    }
    
    public void deletar(){
        
        try {
            estadoDao.excluir(estado);
            Mensagem.lancar("Estado excluído com sucesso.");
        } catch (Exception e) {
            Mensagem.lancar("Erro ao excluir um estado.");
            Mensagem.lancar(e.getMessage());
        }
    }
    
    
    public List<Estado> listar(){
    
            return estadoDao.obterLista();
    }
    
    
    public EstadoDao getEstadoDao() {
        return estadoDao;
    }

    public void setEstadoDao(EstadoDao estadoDao) {
        this.estadoDao = estadoDao;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    
    
            
    
}
