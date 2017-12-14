/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import javax.swing.JTextField;
import metier.Catalogue;
import metier.I_Catalogue;
import metier.Produit;

/**
 *
 * @author prax
 */
public class Creation_controleur {
    
    private static I_Catalogue Produits=new Catalogue();

    
    public I_Catalogue getProduits() {
        return Produits;
    }
   
    

    public boolean creerPdt(JTextField sNom, JTextField sPrixUnit, JTextField sQteStock) {
        String nom=sNom.getText();
        boolean prixValide;
        prixValide = verifierPrix(sPrixUnit);
        if(prixValide == true){
         if(produitExiste(nom)==false){
                float prix = Float.parseFloat(sPrixUnit.getText());
                int qte=Integer.parseInt(sQteStock.getText());
                Produit p = new Produit(qte,nom,prix);
                Produits.addProduit(p);
         }
        }
       return true;
    }

    public boolean verifierPrix(JTextField prixProduit) {
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
    
    public boolean produitExiste(String nom){
        boolean res=false;
        String[] nomPdt=Produits.getNomProduits();
        int i=0;
        while(i<nomPdt.length && res==false){
            if(nomPdt[i].equals(nom)){
                res=true;
            }
            i++;
        }
        return res;
    }

}
