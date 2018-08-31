/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crud.util;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Alan
 */

@FacesConverter(value = "genericConverter")
@ManagedBean
@ViewScoped
public class GenericConverter implements Converter,Serializable{

    
    private static Map<Object, String> entidade = new WeakHashMap<Object, String>();

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object entity) {
        synchronized (entidade) {
            if (!entidade.containsKey(entity)) {
                String uuid = UUID.randomUUID().toString();
                entidade.put(entity, uuid);
                return uuid;
            } else {
                return entidade.get(entity);
            }
        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String uuid) {
        for (Map.Entry<Object, String> entry : entidade.entrySet()) {
            if (entry.getValue().equals(uuid)) {
                return entry.getKey();
            }
        }
        return null;
    }

}