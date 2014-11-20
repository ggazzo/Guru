/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guru.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author guilherme
 */
public class Attribute {
    private String type;
    private ArrayList<String> variables;
    public Attribute(String type){
        this.type = type;
        this.variables = new ArrayList<>();
    }
    public void addVariable(String var){
        variables.add(var);    
    }
    public String toString(){
        String retorno = this.type;
        Collections.sort(variables);
        for (Iterator<String> iterator = variables.iterator(); iterator.hasNext();) {
            String next = iterator.next();
            retorno += " "+next;
            if(iterator.hasNext()){
                retorno+=", ";
            }
        }
        
        
        
        return retorno+";";
    }
    
    public String getType(){
        return this.type;
    }
}
