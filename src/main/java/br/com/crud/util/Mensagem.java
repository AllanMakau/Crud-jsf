/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crud.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alan
 */
public class Mensagem {
    
             public static void lancar(String mensagem){
            lancar(null,mensagem);
    }
        
        
        public static void lancar(String clientId,String mensagem){
            FacesContext.getCurrentInstance().addMessage(clientId ,new FacesMessage(mensagem));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }
}
