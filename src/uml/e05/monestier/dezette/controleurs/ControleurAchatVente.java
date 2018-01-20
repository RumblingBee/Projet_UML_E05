/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uml.e05.monestier.dezette.controleurs;

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
            return produits.acheterStock(nomProduit,stock);
        }else{
            return false;
        }
    }
    
    private boolean saisieOk(JTextField stockSaisi){
        if(stockSaisi.getText().matches("^[0-9]*$")){
            int stock=Integer.parseInt(stockSaisi.getText());
            return stock > 0;
        }else {
            return false;
        }
    }

    public boolean enregistrerVente(JTextField txtQuantite, String nomProduit, I_Catalogue produits) {
        if(saisieOk(txtQuantite)){
            int stock=Integer.parseInt(txtQuantite.getText());
            return produits.vendreStock(nomProduit,stock);
        }else{
            return false;
        }
    }
}
