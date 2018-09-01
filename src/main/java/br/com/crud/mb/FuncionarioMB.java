/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crud.mb;

import br.com.crud.dao.CargoDao;
import br.com.crud.dao.EnderecoDao;
import br.com.crud.dao.FuncionarioDao;
import br.com.crud.entity.Cargo;
import br.com.crud.entity.Endereco;
import br.com.crud.entity.Funcionario;
import br.com.crud.enums.Sexo;
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
public class FuncionarioMB implements Serializable{
    
    
    @ManagedProperty(value = "#{funcionarioDao}")
    private FuncionarioDao funcionarioDao;
    
    @ManagedProperty(value = "#{cargoDao}")
    private CargoDao cargoDao;
    
    @ManagedProperty(value = "#{EnderecoDao}")
    private EnderecoDao enderecoDao;
    
    private Endereco endereco;
    
    private List<Cargo> cargos;
    
    
    private Funcionario funcionario;
    
    
    @PostConstruct
    public void init(){
    
        cargos = cargoDao.obterLista();
        
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        
        if(id != null){
        
            funcionario = funcionarioDao.obterPorPK(Integer.parseInt(id));
        } else {
            funcionario = new Funcionario();
        }
    }
    
     public String cadastrarAtualizar(){
        try {
            if(funcionario.getId() != null){
                funcionarioDao.alterar(funcionario);
                Mensagem.lancar("Funcionario atualizado com sucesso.");
            } else {
                funcionarioDao.inserir(funcionario);
                Mensagem.lancar("Funcionario cadastrado com sucesso.");
            }
        } catch (Exception e) {
            
            Mensagem.lancar("Erro ao cadastrar um Funcionario.");
            Mensagem.lancar(e.getMessage());
        }
        
        return "lista.xhtml";
    }
    
    public void deletar(){
        
        try {
            funcionarioDao.excluir(funcionario);
            Mensagem.lancar("Funcionario excluído com sucesso.");
        } catch (Exception e) {
            Mensagem.lancar("Erro ao excluir um Funcionario.");
            Mensagem.lancar(e.getMessage());
        }
    }
    
    
    public List<Funcionario> listar(){
    
            return funcionarioDao.obterLista();
    }
    
    
    public void novoEndereco(){
        
        endereco = new Endereco();
    }
    
    public void adicionarEndereco(){
        
        endereco.setFuncionario(funcionario);
        
        if(funcionario.getEnderecos() == null){
            this.funcionario.setEnderecos(new ArrayList<Endereco>());
        }
        
       funcionario.getEnderecos().add(endereco);
        Mensagem.lancar("O item será adicionado permanentemente somente quando clicar em salvar!");
    }

    public FuncionarioDao getFuncionarioDao() {
        return funcionarioDao;
    }

    public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
        this.funcionarioDao = funcionarioDao;
    }

    public CargoDao getCargoDao() {
        return cargoDao;
    }

    public void setCargoDao(CargoDao cargoDao) {
        this.cargoDao = cargoDao;
    }

    public EnderecoDao getEnderecoDao() {
        return enderecoDao;
    }

    public void setEnderecoDao(EnderecoDao enderecoDao) {
        this.enderecoDao = enderecoDao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }

    public Sexo[] listaSexo(){
        
        return Sexo.values();
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    
    
   
}
