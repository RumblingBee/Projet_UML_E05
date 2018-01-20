package uml.e05.monestier.dezette.controleurs;

import uml.e05.monestier.dezette.metier.I_Catalogue;

import javax.swing.*;

public class Suppression_controleur {

    public boolean supprimerProduit(JComboBox nomProduitSelectionne, I_Catalogue produits){
        String nomProduit = String.valueOf(nomProduitSelectionne.getSelectedItem());

        return produits.removeProduit(nomProduit);
    }
}
