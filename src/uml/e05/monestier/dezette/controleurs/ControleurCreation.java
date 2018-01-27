/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uml.e05.monestier.dezette.controleurs;

import uml.e05.monestier.dezette.metier.I_Catalogue;
import uml.e05.monestier.dezette.metier.Produit;

import javax.swing.*;


/**
 *
 * @author prax
 */
public class ControleurCreation {

    public boolean creerPdt(JTextField nomSaisi, JTextField prixUnitaireSaisi, JTextField quantiteSaisie, I_Catalogue produits) {
        int qte=Integer.parseInt(quantiteSaisie.getText());
        String nom=nomSaisi.getText();
        boolean prixValide;
        prixValide = verifierPrix(prixUnitaireSaisi);
        if(prixValide == true){
            if(qte > 0){
                 if(produitExiste(nom,produits)==false) {
                     float prix = Float.parseFloat(prixUnitaireSaisi.getText());

                     Produit produit = new Produit(qte, nom, prix);
                     produits.addProduit(produit);
                     return true;
           }
          }
        }
       return false;
    }

    private boolean verifierPrix(JTextField prixProduit) {
        float prix;
        try {
            prix = Float.parseFloat(prixProduit.getText());
        } catch (NumberFormatException e) {
            return false;
        }
        if (prix <= 0) {
            return false;
        } else {
            return true;
        }
    }
    
    private boolean produitExiste(String nom, I_Catalogue Produits){
        boolean produitExiste=false;
        String[] nomsProduits=Produits.getNomProduits();
        int i=0;
        while(i<nomsProduits.length && produitExiste==false){
            if(nomsProduits[i].equals(nom)){
                produitExiste=true;
            }
            i++;
        }
        return produitExiste;
    }

}
