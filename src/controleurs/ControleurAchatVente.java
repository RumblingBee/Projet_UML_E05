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
            int stock=Integer.parseInt(stockSaisi.getText());
            System.out.println("verif saisie et passage au catalogue");
            return produits.acheterStock(nomProduit,stock);
        }else{
            return false;
        }
    }
    
    private boolean saisieOk(JTextField stockSaisi){
        if(stockSaisi.getText().matches("^[0-9]*$")){
            int stock=Integer.parseInt(stockSaisi.getText());
            System.out.println("saisie ok");
            return stock > 0;
        }else {
            return false;
        }
    }
}
