/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import uml.e05.monestier.dezette.metier.I_Catalogue;

import javax.swing.*;

/**
 *
 * @author clement
 */
public class ControleurAchatVente {
    
    public boolean enregistrerAchat(JTextField stockSaisi, String nomProduit,I_Catalogue produits){
        if(saisieOk(stockSaisi)){
            int stock=Integer.parseInt(stockSaisi.toString());
            return produits.acheterStock(nomProduit,stock);
        }else{
            return false;
        }
    }
    
    private boolean saisieOk(JTextField stockSaisi){
        int stock;
        try{
            stock=Integer.parseInt(stockSaisi.toString());
        }catch(Exception e){
            return false;
        }
        return stock > 0;
    }
}
