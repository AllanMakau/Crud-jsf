/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crud.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Alan
 */
public  abstract class GenericoDao <T, I> implements Serializable {
    
    protected EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("crud");
    protected EntityManager entityManager = entityManagerFactory.createEntityManager();
    protected EntityTransaction transaction;
    private Class<T> objeto;
    
    protected GenericoDao() {
    }

    protected GenericoDao(Class<T> objeto) {
        this();
        this.objeto = objeto;
    }
    
    public void begin(){
        transaction  = entityManager.getTransaction();
        transaction.begin();
    }

    public void commit(){
        transaction.commit();
    }
    
    public void rollback(){
        transaction.rollback();
    }

    public T inserir(T obj) {
        begin();
        try{
            entityManager.persist(obj);
            entityManager.flush();
            commit();
        }catch(Exception e){
            e.printStackTrace();
            rollback();
        }
        return obj;
    }
    
    public T inserirSemTransacao(T obj) {
        entityManager.persist(obj);
        entityManager.flush();
        return obj;
    }

    public T alterar(T obj) {
        begin();
        try{
            entityManager.merge(obj);
            entityManager.flush();
            commit();
        }catch(Exception e){
            e.printStackTrace();
            rollback();
        }
        return obj;
    }

    public void excluir(T obj) {
        begin();
        try{
            entityManager.remove(obj);
            entityManager.flush();
            commit();
        }catch(Exception e){
            e.printStackTrace();
            rollback();
        }
    }
    
    public void excluirSemTransacao(T obj) {
        begin();
        try{
           Object c = entityManager.merge(obj);
            entityManager. remove(c);
            commit();
        }catch(Exception e){
            e.printStackTrace();
            rollback();
        }
    }

    public T obterPorPK(Integer id) {
        return entityManager.find(objeto, id);
    }

    public List<T> obterLista() {
        CriteriaQuery<T> criteria = entityManager.getCriteriaBuilder().createQuery(objeto);
        criteria.select(criteria.from(objeto));
        return entityManager.createQuery(criteria).getResultList();

    }
}
