/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import javax.swing.JTextField;

/**
 *
 * @author prax
 */
public class Creation_controleur {

    public boolean creerPdt(String nom, JTextField prixUnit, int qteStock) {
        boolean prixValide;
        prixValide = verifierPrix(prixUnit);
        if(prixValide == true){
            
        }
        return false;
    }

    public boolean verifierPrix(JTextField prixProduit) {
        float prix;
        try {
            prix = Float.parseFloat(prixProduit.getText());
        } catch (Exception e) {
            return false;
        }
        if (prix < 0) {
            return false;
        } else {
            return true;
        }
    }

}
