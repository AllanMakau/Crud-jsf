/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crud.mb;

import br.com.crud.dao.CidadeDao;
import br.com.crud.dao.EstadoDao;
import br.com.crud.entity.Cidade;
import br.com.crud.entity.Estado;
import br.com.crud.util.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alan
 */

@ManagedBean
@ViewScoped
public class CidadeMB implements Serializable {
    
    @ManagedProperty(value = "#{cidadeDao}")
    private CidadeDao cidadeDao;
    
    @ManagedProperty(value =  "#{estadoDao}")
    private EstadoDao estadoDao;
    
    private List<Estado> estados = new ArrayList<>();
    
    private Cidade cidade;
    
    
    @PostConstruct
     public void init(){
         estados = estadoDao.obterLista();
         String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
         if(id != null){
             cidade = cidadeDao.obterPorPK(Integer.parseInt(id));
         } else {
             
             cidade = new Cidade();
         }
    }

      public List<Cidade> listar(){
        
        return cidadeDao.obterLista();
       
    }
    
    
    public String cadastrarAtualizar(){
        try {
            if(cidade.getId() != null){
                cidadeDao.alterar(cidade);
                 Mensagem.lancar("Cargo atualizado com sucesso");
            } else {
                cidadeDao.inserir(cidade);
                Mensagem.lancar("Cargo Cadastro com sucesso"); 
            }
        } catch (Exception e) {
            Mensagem.lancar("Erro ao cadastrar ou atualizar um Cargo.");
        }
        return "lista.xhtml";
    }
    
    
    public String deletar(){
        try {
            cidadeDao.excluir(cidade);
            Mensagem.lancar("Cargo excluido com sucesso");;
        } catch (Exception e) {
            Mensagem.lancar("Erro ao excluir um Cargo.");
        }
        return "lista.xhtml";
    }
     
     
    public CidadeDao getCidadeDao() {
        return cidadeDao;
    }

    public void setCidadeDao(CidadeDao cidadeDao) {
        this.cidadeDao = cidadeDao;
    }

    public EstadoDao getEstadoDao() {
        return estadoDao;
    }

    public void setEstadoDao(EstadoDao estadoDao) {
        this.estadoDao = estadoDao;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }
     
     
    
}
