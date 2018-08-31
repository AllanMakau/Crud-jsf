/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crud.mb;

import br.com.crud.dao.CargoDao;
import br.com.crud.entity.Cargo;
import br.com.crud.util.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class CargoMB implements Serializable{
    
    @ManagedProperty(value = "#{cargoDao}")
    private CargoDao cargodao;

    private Cargo cargo;
    
    private List<Cargo> cargos = new ArrayList<>();
    
    @PostConstruct
    public void init(){
    
        String id =((Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()).get("id");
        
        if(id != null){
            cargo = cargodao.obterPorPK(Integer.parseInt(id));
        }else {
            cargo = new Cargo();
        }
    }
    
    public List<Cargo> listar(){
        
        cargos = cargodao.obterLista();
        return cargos;
    }
    
    public void obterLista(){
        
        cargodao.obterLista();
    }
    
    public void cadastrarAtualizar(){
        try {
            if(cargo.getId() != null){
                cargodao.alterar(cargo);
               // return "cadastro.xhtml";
            } else {
                cargodao.inserir(cargo);
              //  return "lista.xhtml";
            }
        } catch (Exception e) {
            Mensagem.lancar("Erro ao cadastrar ou atualizar um Cargo.");
        }
       // return "lista.xhtml";
    }
    
    public String deletar(){
        try {
            cargodao.excluir(cargo);
            return "lista.xhtml";
        } catch (Exception e) {
            Mensagem.lancar("Erro ao excluir um Cargo.");
        }
        return "lista.xhtml";
    }

    public CargoDao getCargodao() {
        return cargodao;
    }

    public void setCargodao(CargoDao cargodao) {
        this.cargodao = cargodao;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    
}
