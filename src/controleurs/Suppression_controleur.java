package controleurs;

import uml.e05.monestier.dezette.metier.I_Catalogue;

import javax.swing.*;

public class Suppression_controleur {

    public boolean supprimerProduit(JComboBox pNomProduit, I_Catalogue produits){
        String nomProduit = String.valueOf(pNomProduit.getSelectedItem());

        return produits.removeProduit(nomProduit);
    }
}
